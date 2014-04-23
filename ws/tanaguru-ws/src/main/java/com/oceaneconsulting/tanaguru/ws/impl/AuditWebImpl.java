package com.oceaneconsulting.tanaguru.ws.impl;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.oceaneconsulting.tanaguru.decorator.WebResourceDataServiceDecorator;
import com.oceaneconsulting.tanaguru.enumerations.AuditLevel;
import com.oceaneconsulting.tanaguru.service.WsInvocationService;
import com.oceaneconsulting.tanaguru.util.ParameterInputs;
import com.oceaneconsulting.tanaguru.util.ParameterUtils;
import com.oceaneconsulting.tanaguru.ws.AuditWeb;
import com.oceaneconsulting.tanaguru.ws.types.AuditResult;



/**
 * Webservice implementation class.
 * 
 * This class defines all exposed webservice's operations.
 *  
 * @author shamdi
 *
 */
@Component
@Path("/")
public class AuditWebImpl implements AuditWeb {

	private static final Logger LOGGER = Logger.getLogger(AuditWebImpl.class);
	
	@Autowired
    private AuditService auditService;
	
	
	@Autowired
	@Qualifier("webResourceDataServiceDecorator")
	WebResourceDataServiceDecorator webResourceDataServiceDecorator;
	
	@Autowired
	ParameterDataService parameterDataService;
	
	@Autowired
	WsInvocationService wsInvocationService;
	
	@Resource(name="messages")
	private Properties messages;


    public AuditWebImpl() {
        super();
    }
    
    
	@GET
	@Path("/auditPage")
	@Produces(MediaType.APPLICATION_JSON) 
    public void auditPage(@QueryParam(ParameterInputs.PAGE_URL) final String pageURL, 
    		@QueryParam(ParameterInputs.AUDIT_LEVEL) final String level, 
    		@QueryParam(ParameterInputs.DATA_TABLE_MARKER_PARAM) final String tblMarker, 
    		@QueryParam(ParameterInputs.PRESENTATION_TABLE_MARKER) final String prTblMarker, 
    		@QueryParam(ParameterInputs.DECORATIVE_IMAGE_MARKER) final String dcrImgMarker, 
    		@QueryParam(ParameterInputs.INFORMATIVE_IMAGE_MARKER) final String infImgMarker,
    		@Suspended final AsyncResponse response) {

		 
		//Time out response if the fixed waiting time exceeded
		response.setTimeoutHandler(new TimeoutHandler() {
	        @Override
	        public void handleTimeout(AsyncResponse asyncResponse) {
	        	AuditResult auditResult = new AuditResult();
	        	auditResult.setMessage((String)messages.get("global.timeout.error") );
	            asyncResponse.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(auditResult).build());
	        }
	    });
		
		//Time out is fixed to 60 seconds
		response.setTimeout(60, TimeUnit.SECONDS);
		
//		response.register(new CompletionCallback() {
//			@Override
//			public void onComplete(Throwable throwable) {
//
//				if (throwable == null) {
//					//response.resume(Response.status(200).entity("Execution ended !").build());
//				} else {
//		        	AuditResult auditResult = new AuditResult();
//		        	auditResult.setUrl(pageURL);
//		        	auditResult.setMessage("Operation time out.");
//		            response.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(auditResult).build());
//				}
//			}
//		});
	 
		//Run audit in a thread
	    new Thread(new Runnable() {
	    	
	        @Override
	        public void run() {
	            response.resume(auditPage());
	        }	 
	        private AuditResult auditPage() {
	        	LOGGER.debug("Run audit thread");
	        	
	        	AuditResult auditResult = new AuditResult(); 
	        	
	    		LOGGER.debug("Initialize parameters...");
	        	//get parameters
	    		ParameterUtils.initParametersMap(parameterDataService);
	        	
	        	LOGGER.debug("Validate parameters...");
	    		//get default set of parameters
	    		Set<Parameter> parameters =  ParameterUtils.getDefaultParametersForPA();
	    		
	 	    	//validate parameters
	    		if(pageURL == null || pageURL.isEmpty() || level == null || level.isEmpty()){
		        	auditResult.setMessage((String)messages.get("input.validation.urlNlevel.mondatory"));
		            return auditResult;
	    		} else if(!AuditLevel.contains(level)){
		        	auditResult.setMessage((String)messages.get("input.validation.level.error"));
		            return auditResult;
	    		}
	    		
	    		initializeParams(level, tblMarker, prTblMarker, dcrImgMarker, infImgMarker, parameters);
	    		
	    		LOGGER.debug("Launch audit...");
	    		//launch ws (unused result variable) 
	        	Audit audit = auditService.auditPage(pageURL, parameters);
	        	
	        	LOGGER.debug("Getting audit statistics for audit with identifier ["+audit.getId() + "] for page [" +pageURL +"] ...");
	        	Boolean loop = Boolean.TRUE;
	        	
	        	while(loop){
	        		try {
	        			long timeToSleep = 5000l;
	    				Thread.sleep(timeToSleep);

	    				auditResult = webResourceDataServiceDecorator.findWeightedMarkAndStatusByAuditId(audit.getId());
	    				
	    				LOGGER.debug(auditResult.toString());
	    				
	    				if("COMPLETED".equals(auditResult.getStatus())){
		    	    		loop = Boolean.FALSE;	
		    	    		
	    	    		} else if( "ERROR".equals(auditResult.getStatus())){
	    		    	    loop = Boolean.FALSE;	
	    		    	    LOGGER.info( "A problem occured during test (wrong URL ...)");
	    		    	    auditResult.setMessage((String)messages.get("input.parameters.error")); 
	    		    	} else {
	    		    		//Other intermediate status
	    		    	}
	    				

	    			} catch (InterruptedException e) {
	    				loop = Boolean.FALSE;
	    				auditResult.setMessage((String)messages.get("global.runtime.error")); 
	    			}
	        	}
	    		
	        	return auditResult;
	        }
			private void initializeParams(String level, String tblMarker, String prTblMarker, String dcrImgMarker, String infImgMarker, Set<Parameter> parameters) {
				if(level != null && !level.isEmpty()){
	    			parameters.add(ParameterUtils.createParameter(ParameterInputs.LEVEL, level));
	    		} 

	    		for(Parameter parameter : parameters){
	    			
	    			if(parameter != null && parameter.getParameterElement() != null && parameter.getParameterElement().getParameterElementCode() != null){
	
			    		
		    			if(tblMarker != null){
			    			if(ParameterInputs.DATA_TABLE_MARKER.equals(parameter.getParameterElement().getParameterElementCode()) ){
			    				parameter.setValue(tblMarker);
			    			}
		    			}
		    			if(prTblMarker != null){ 
			    			if(ParameterInputs.PRESENTATION_TABLE_MARKER.equals(parameter.getParameterElement().getParameterElementCode()) ){
			    				parameter.setValue(prTblMarker);
			    			}
		    			}
		    			if(dcrImgMarker != null){
			    			if(ParameterInputs.DECORATIVE_IMAGE_MARKER.equals(parameter.getParameterElement().getParameterElementCode()) ){
			    				parameter.setValue(dcrImgMarker);
			    			}
		    			}
		    			if(infImgMarker != null){
			    			if(ParameterInputs.INFORMATIVE_IMAGE_MARKER.equals(parameter.getParameterElement().getParameterElementCode()) ){
			    				parameter.setValue(infImgMarker);
			    			}
		    			}

	    			LOGGER.debug(parameter.getParameterElement().getShortLabel() +  " = "+ parameter.getValue());
	    			}
	    		}
			}
	    }).start();
	    
    }
	
	
	
	@POST
	@Path("/auditScenario")
	public Response auditScenario(@FormParam("scenarioName") String scenarioName, @FormParam("scenarioText") String scenarioText , @FormParam("level")String level){
		
//		
//		Set<Parameter> parameters =  ParameterUtils.getDefaultParametersForScenario();
//		
//		//define level if necessary : supposing it's not mandatory
//		if(level != null && !level.isEmpty()){
//			parameters.add(ParameterUtils.createParameter(5l, "LEVEL", level));
//		}
//		//TODO : adding security a
//		
//		Audit audit = auditService.auditScenario(scenarioName, scenarioText, parameters);
//    	
//		//return response
    	return Response.status(200).entity("Scenario audit was launched").build();
	}
	

	public Response auditSite(@FormParam("url") String siteURL, @FormParam("level")String level) {
		// TODO Auto-generated method stub
		return null;
	}





    

}


