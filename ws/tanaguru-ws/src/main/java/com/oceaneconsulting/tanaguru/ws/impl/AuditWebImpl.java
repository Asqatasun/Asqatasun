package com.oceaneconsulting.tanaguru.ws.impl;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.statistics.WebResourceStatistics;
import org.opens.tanaguru.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.oceaneconsulting.tanaguru.decorator.WebResourceDataServiceDecorator;
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
@Consumes(MediaType.APPLICATION_JSON)
public class AuditWebImpl implements AuditWeb {

	private static final Logger LOGGER = Logger.getLogger(AuditWebImpl.class);
	
	@Autowired
    private AuditService auditService;
	
	
	@Autowired
	@Qualifier("webResourceDataServiceDecorator")
	WebResourceDataServiceDecorator webResourceDataServiceDecorator;
	
	@Autowired
	ParameterDataService parameterDataService;

	
    public AuditWebImpl() {
        super();
    }
    

	@GET
	@Path("/auditPageSync")
    public Response auditPageSync(@QueryParam("url")String pageURL, @QueryParam("level")String level, @Context HttpServletRequest request) {
		
		String host = request.getRemoteHost();
		String ipAddress = request.getRemoteAddr();

		LOGGER.debug("host" + host + "| ipAddress" + ipAddress);
		
		//get parameters
		ParameterUtils.initParametersMap(parameterDataService);
		
		
		//Get default set of parameters
		Set<Parameter> parameters =  ParameterUtils.getDefaultParametersForPA();
		
		//define level if necessary : supposing it's not mondatory
		if(level != null && !level.isEmpty()){
			parameters.add(ParameterUtils.createParameter("LEVEL", level));
		}
		
		//launch ws (unused result variable) 
    	Audit audit = auditService.auditPage(pageURL, parameters);
    	
      return Response.status(200).entity("Page audit was launched").build();
    }
	
	@GET
	@Path("/auditPage")
	@Produces(MediaType.APPLICATION_JSON) 
    public void auditPage(@QueryParam("url") final String pageURL, @QueryParam("level") final String level, @Suspended final AsyncResponse response) {
    		/*,  @Context final HttpServletRequest request*/ 
		
		//Time out response if 
		response.setTimeoutHandler(new TimeoutHandler() {
	        @Override
	        public void handleTimeout(AsyncResponse asyncResponse) {
	        	AuditResult auditResult = new AuditResult();
	        	auditResult.setUrl(pageURL);
	        	auditResult.setMessage("Operation time out.");
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
//					response.resume(Response.status(200).entity("Execution ended !").build());
//				} else {
//					response.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Operation time out.").build());
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
	        	
//	    		//security parameters
//	    		LOGGER.debug("Security parameters...");
//	    		String host = request.getRemoteHost();
//	    		String ipAddress = request.getRemoteAddr();
//	    		LOGGER.debug("host" + host + "| ipAddress" + ipAddress);
	    		
	    		LOGGER.debug("Initialize parameters...");
	        	//get parameters
	    		ParameterUtils.initParametersMap(parameterDataService);
	        	
	        	LOGGER.debug("Validate parameters...");
	    		//Get default set of parameters
	    		Set<Parameter> parameters =  ParameterUtils.getDefaultParametersForPA();
	    		
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
	    		    	    auditResult.setMessage("A problem occured during test (wrong URL ...)");
	    		    	} else {
	    		    		//Other intermediate status
	    		    	}
	    				
	    		    	auditResult.setUrl(pageURL);
	    				    	    	

	    			} catch (InterruptedException e) {
	    				loop = Boolean.FALSE;
	    				auditResult.setMessage("Runtime problem occured !");
	    			}
	        	}
	    		
	        	return auditResult;
	        }
	    }).start();
	    
	    
    }
	
	
	
	@POST
	@Path("/auditScenario")
	public Response auditScenario(@FormParam("scenarioName") String scenarioName, @FormParam("scenarioText") String scenarioText , @FormParam("level")String level){
		
//		
//		Set<Parameter> parameters =  ParameterUtils.getDefaultParametersForScenario();
//		
//		//define level if necessary : supposing it's not mondatory
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




//
//	@Override
//	public Response auditPage(String pageURL, String level,
//			HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		return null;
//	}

    

}


