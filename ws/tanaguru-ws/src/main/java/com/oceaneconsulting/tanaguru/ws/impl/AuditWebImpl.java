package com.oceaneconsulting.tanaguru.ws.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
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
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.AuditStatus;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.entity.service.parameterization.ParameterDataService;
import org.tanaguru.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.oceaneconsulting.tanaguru.decorator.WebResourceDataServiceDecorator;
import com.oceaneconsulting.tanaguru.entity.WsInvocation;
import com.oceaneconsulting.tanaguru.entity.impl.WsInvocationImpl;
import com.oceaneconsulting.tanaguru.entity.impl.WsUserImpl;
import com.oceaneconsulting.tanaguru.enumerations.AuditLevel;
import com.oceaneconsulting.tanaguru.service.WsInvocationService;
import com.oceaneconsulting.tanaguru.service.WsUserService;
import com.oceaneconsulting.tanaguru.util.ParameterInputs;
import com.oceaneconsulting.tanaguru.util.ParameterUtils;
import com.oceaneconsulting.tanaguru.ws.AuditWeb;
import com.oceaneconsulting.tanaguru.ws.types.AuditResult;
import com.oceaneconsulting.tanaguru.ws.types.AuditScenarioOrder;
import com.oceaneconsulting.tanaguru.ws.types.AuditLaunchResult;
import com.oceaneconsulting.tanaguru.ws.types.AuditSiteOrder;
import com.oceaneconsulting.tanaguru.ws.types.GlobalStatsOrder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Webservice implementation class.
 *
 * This class defines all exposed webservice's operations.
 *
 * @author shamdi at oceaneconsulting dot com
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

    @Autowired
    WsUserService wsUserService;

    @Resource(name = "messages")
    private Properties messages;

    public AuditWebImpl() {
        super();
    }

    @GET
    @Path("/auditPage")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
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
                LOGGER.error("Timout error in audit page");
                AuditResult auditResult = new AuditResult();
                auditResult.setMessage((String) messages.get("global.timeout.error"));
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
                LOGGER.debug("Run audit page thread");

                AuditResult auditResult = new AuditResult();

                LOGGER.info("Validate mandatory input parameters...");

                //validate parameters
                if (pageURL == null || pageURL.isEmpty() || level == null || level.isEmpty()) {
                    auditResult.setMessage((String) messages.get("input.validation.urlNlevel.mondatory"));
                    return auditResult;
                } else if (!AuditLevel.contains(level)) {
                    auditResult.setMessage((String) messages.get("input.validation.level.error"));
                    return auditResult;
                }

                LOGGER.info("Initialize parameters...");
                //get parameters from DB
                ParameterUtils.initParametersMap(parameterDataService);
                //get default set of parameters
                Set<Parameter> parameters = ParameterUtils.getDefaultParametersForPA();
                //set option values
                ParameterUtils.initializePAInputOptions(level, tblMarker, prTblMarker, dcrImgMarker, infImgMarker, parameters);

                LOGGER.info("Launch audit page service...");
                //launch ws 
                Audit audit = auditService.auditPage(pageURL, parameters);

                LOGGER.debug("Getting audit statistics for audit with identifier [" + audit.getId() + "] for page [" + pageURL + "] ...");
                Boolean loop = Boolean.TRUE;
                int loopCount = 1;
                long timeToSleep = 5000l;
                while (loop) {
                    try {

                        Thread.sleep(timeToSleep);

                        auditResult = webResourceDataServiceDecorator.findWeightedMarkAndStatusByAuditId(audit.getId());

                        LOGGER.debug(auditResult.toString());

                        if (AuditStatus.COMPLETED.toString().equals(auditResult.getStatus())) {
                            loop = Boolean.FALSE;
                        } else if (AuditStatus.ERROR.toString().equals(auditResult.getStatus())) {
                            loop = Boolean.FALSE;
                            LOGGER.error("A problem occured during test (wrong URL ...)");
                            auditResult.setMessage((String) messages.get("input.parameters.error"));
                        } else if (AuditStatus.PENDING.toString().equals(auditResult.getStatus())) {
                            loop = Boolean.FALSE;
                            LOGGER.error("Excecuting thread is in a pending status.");
                            auditResult.setMessage((String) messages.get("global.status.pending"));
                        } else if (AuditStatus.SCENARIO_LOADING.toString().equals(auditResult.getStatus())) {
                            //If any problems occurs with webdriver
                            if (loopCount > 12) {
                                loop = Boolean.FALSE;
                                LOGGER.error("Scenario loading takes a long time.");
                                auditResult.setMessage((String) messages.get("global.runtime.error"));
                            }
                            ++loopCount;
                        } else {
                            //intermediate status
                        }

                    } catch (InterruptedException e) {
                        LOGGER.error(ExceptionUtils.getStackTrace(e));
                        loop = Boolean.FALSE;
                        auditResult.setMessage((String) messages.get("global.runtime.error"));
                    }
                }
                return auditResult;
            }

        }).start();

    }

    @POST
    @Path("/secure/launchAuditSite")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response launchAuditSite(AuditSiteOrder auditSiteOrder, @Context SecurityContext securityContext) {

        AuditLaunchResult auditResult = new AuditLaunchResult();

        LOGGER.info("Validate mandatory input parameters...");
        // validate parameters
        if (auditSiteOrder.getSiteURL() == null || auditSiteOrder.getSiteURL().isEmpty() || auditSiteOrder.getLevel() == null || auditSiteOrder.getLevel().isEmpty()) {
            auditResult.setMessage((String) messages.get("input.validation.urlNlevel.mondatory"));
        } else if (!AuditLevel.contains(auditSiteOrder.getLevel())) {
            auditResult.setMessage((String) messages.get("input.validation.level.error"));
        }

        if (!auditResult.getMessage().isEmpty()) {// send error to user
            LOGGER.error(auditResult.getMessage());
            return Response.status(HttpServletResponse.SC_OK).entity(auditResult).build();
        }

        LOGGER.info("Initialize parameters...");
        // init parameters
        // get parameters from DB
        ParameterUtils.initParametersMap(parameterDataService);
        // get default set of parameters
        Set<Parameter> parameters = ParameterUtils.getDefaultParametersForSite();
        // set option values
        ParameterUtils.initializeInputOptions(auditSiteOrder, parameters);

        LOGGER.info("Launch audit site service...");
        // launch audit site service
        Audit audit = auditService.auditSite(auditSiteOrder.getSiteURL(), parameters);

        LOGGER.info("Save audit site references...");
        LOGGER.debug("Audit site identifier [" + audit.getId() + "] for page [" + auditSiteOrder.getSiteURL() + "] ...");
        // save audit references
        if (securityContext != null && securityContext.getUserPrincipal() != null) {
            LOGGER.debug("User with login =" + securityContext.getUserPrincipal().getName());
            WsInvocation wsInvocation = new WsInvocationImpl();
            wsInvocation.setAuditType(1);//Constant
            wsInvocation.setDateInvocation(new Date());
//			wsInvocation.setHostIp(((org.springframework.security.web.authentication.WebAuthenticationDetails)
//					(((org.springframework.security.authentication.UsernamePasswordAuthenticationToken)securityContext.getUserPrincipal())).getDetails()).getRemoteAddress());//to be deleted
            wsInvocation.setUser((WsUserImpl) wsUserService.getUser(securityContext.getUserPrincipal().getName()));
            wsInvocation.setHostName("");
            wsInvocation.setHostIp("");
            wsInvocation.setAuditId(audit.getId());

            //Data based on request properties : we must study automation possibility with tools like Temis
            wsInvocation.setCountry(auditSiteOrder.getCountry());
            wsInvocation.setCategory(auditSiteOrder.getCategory());

            //Set audit identifier for webservice response
            auditResult.setIdAudit(audit.getId());

            LOGGER.debug("Save invocation information.");
            wsInvocationService.create(wsInvocation);
        }

        return Response.status(HttpServletResponse.SC_OK).entity(auditResult).build();
    }

    @POST
    @Path("/secure/launchAuditScenario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response launchAuditScenario(AuditScenarioOrder auditScenarioOrder, @Context SecurityContext securityContext) {
        AuditLaunchResult auditResult = new AuditLaunchResult();

        LOGGER.info("Validate mandatory input parameters...");
        // validate parameters
        if (auditScenarioOrder.getScenarioText() == null || auditScenarioOrder.getScenarioText().isEmpty() || auditScenarioOrder.getLevel() == null || auditScenarioOrder.getLevel().isEmpty()) {
            auditResult.setMessage((String) messages.get("input.validation.scenarioNlevel.mondatory"));
        } else if (!AuditLevel.contains(auditScenarioOrder.getLevel())) {
            auditResult.setMessage((String) messages.get("input.validation.level.error"));
        }

        if (!auditResult.getMessage().isEmpty()) {// send error to user
            LOGGER.error(auditResult.getMessage());
            return Response.status(HttpServletResponse.SC_OK).entity(auditResult).build();
        }

        LOGGER.info("Initialize parameters...");
		// init parameters
        // get parameters from DB
        ParameterUtils.initParametersMap(parameterDataService);
        // get default set of parameters
        Set<Parameter> parameters = ParameterUtils.getDefaultParametersForScenario();
        // set option values
        ParameterUtils.initializeInputOptions(auditScenarioOrder, parameters);

        LOGGER.info("Launch audit scenario service...");
        // launch audit scenario service
        Audit audit = auditService.auditScenario(auditScenarioOrder.getScenarioLabel(), auditScenarioOrder.getScenarioText(), parameters);

        LOGGER.info("Save audit scenario references...");
        LOGGER.debug("Audit scenario identifier [" + audit.getId() + "] for scenario label [" + auditScenarioOrder.getScenarioLabel() + "] ...");
        // save audit references
        if (securityContext != null && securityContext.getUserPrincipal() != null) {
            LOGGER.debug("User with login =" + securityContext.getUserPrincipal().getName());
            WsInvocation wsInvocation = new WsInvocationImpl();
            wsInvocation.setAuditType(2);//Constant
            wsInvocation.setDateInvocation(new Date());
            wsInvocation.setUser((WsUserImpl) wsUserService.getUser(securityContext.getUserPrincipal().getName()));
            wsInvocation.setHostName("");
            wsInvocation.setHostIp("");
            wsInvocation.setAuditId(audit.getId());

            //Set audit identifier for webservice response
            auditResult.setIdAudit(audit.getId());

            LOGGER.debug("Save invocation information.");
            wsInvocationService.create(wsInvocation);
        }

        return Response.status(HttpServletResponse.SC_OK).entity(auditResult).build();
    }

    @POST
    @Path("/secure/globalStats")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response globalStats(GlobalStatsOrder globalStatsOrder) {
        List<AuditResult> auditResults = new ArrayList();
        try {
            LOGGER.debug("Executing global stats query...");
            //Executing global stats query
            auditResults = webResourceDataServiceDecorator.findWeightedMarkAndStatus(globalStatsOrder);
        } catch (Exception e) {
            LOGGER.error("An error occured during getting stats operation...");
            AuditResult auditResult = new AuditResult();
            auditResult.setMessage((String) messages.get("global.stats.error"));
            auditResults.add(auditResult);
        }
        return Response.status(HttpServletResponse.SC_OK).entity(auditResults).build();
    }

}
