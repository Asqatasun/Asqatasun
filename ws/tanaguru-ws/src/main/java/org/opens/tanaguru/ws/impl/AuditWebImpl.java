package org.opens.tanaguru.ws.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.service.AuditService;
import org.opens.tanaguru.ws.AuditWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.oceaneconsulting.tanaguru.bean.AuditInputs;
import com.oceaneconsulting.tanaguru.bean.AuditResult;
import com.oceaneconsulting.tanaguru.enumerations.AuditLevel;
import com.oceaneconsulting.tanaguru.enumerations.AuditType;
import com.oceaneconsulting.tanaguru.service.AccesibiliteService;
import com.oceaneconsulting.tanaguru.util.ParameterInputs;
import com.oceaneconsulting.tanaguru.util.ParameterUtils;

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
@Produces(MediaType.APPLICATION_JSON)
public class AuditWebImpl implements AuditWeb {

	private static final Logger LOGGER = Logger.getLogger(AuditWebImpl.class);

	@Autowired
	private AuditService auditService;

	@Autowired
	ParameterDataService parameterDataService;

	@Autowired
	@Qualifier(value = "accesibiliteServiceImpl#oceane")
	AccesibiliteService accesibiliteService;

	public AuditWebImpl() {
		super();
	}

	@GET
	@Path("/audit")
	public Response audit(@Context UriInfo info,
			@Context HttpServletRequest request) {
		String host = request.getRemoteHost();
		String ipAddress = request.getRemoteAddr();

		LOGGER.debug("host" + host + "| ipAddress" + ipAddress);

		// get params

		List<String> params = null;
		String inputs = info.getQueryParameters().getFirst("params");
		if (inputs != null) {
			params = Arrays.asList(inputs.split("[\\|,]"));
		}
		// create map params
		Map<String, String> mapValues = new HashMap<String, String>();
		for (String input : params) {
			String[] myParams = input.split("=");
			String key = myParams[0];
			String value = myParams.length > 1 ? myParams[1] : null;
			mapValues.put(key.toUpperCase(), value);
		}

		AuditInputs auditInputs = new AuditInputs();
		// fix all inputs

		// audittype
		auditInputs.setAuditType(AuditType.valueOf(mapValues
				.get(ParameterInputs.AUDIT_TYPE.toUpperCase())));

		// audit level
		String level = mapValues.get(ParameterInputs.AUDIT_LEVEL.toUpperCase());
		if(level == null){
			// no level specified
			auditInputs.setLevel(AuditLevel.AR.getValue());
		}else {
			AuditLevel auditLevel = AuditLevel.valueOf(level);
			if(auditLevel != null){
				auditInputs.setLevel(auditLevel.getValue());
			}
					
		}
		
		// pageUrl
		auditInputs.setPageUrl(mapValues.get(ParameterInputs.PAGE_URL
				.toUpperCase()));

		// pageUrls
		List<String> urls = null;
		String pageUrls = mapValues
				.get(ParameterInputs.PAGE_URLS.toUpperCase());
		if (StringUtils.isNotEmpty(pageUrls)) {
			urls = Arrays.asList(pageUrls.split("[;,]"));
		}
		auditInputs.setPageUrls(urls);

		// scenario name
		auditInputs
				.setScenarioName(mapValues.get(ParameterInputs.SCENARIO_NAME.toUpperCase()));

		// scenario
		auditInputs.setScenarioName(mapValues.get(ParameterInputs.SCENARIO.toUpperCase()));

		// set parameters
		auditInputs.setParameters(mapValues);

		// CALL the audi service
		AuditResult auditResult = accesibiliteService.audit(auditInputs);

		// return response
		return Response.status(200).entity("Page audit was launched").build();
	}

	@GET
	@Path("/auditPage")
	public Response auditPage(@QueryParam("url") String pageURL,
			@QueryParam("level") String level,
			@Context HttpServletRequest request) {

		String host = request.getRemoteHost();
		String ipAddress = request.getRemoteAddr();

		LOGGER.debug("host" + host + "| ipAddress" + ipAddress);

		// get parameters
		ParameterUtils.initParametersMap(parameterDataService);

		// Get default set of parameters
		Set<Parameter> parameters = ParameterUtils.getDefaultParametersForPA();

		// define level if necessary : supposing it's not mondatory
		if (level != null && !level.isEmpty()) {
			parameters.add(ParameterUtils.createParameter("LEVEL", level));
		}

		// launch ws (unused result variable)
		Audit audit = auditService.auditPage(pageURL, parameters);

		// return response
		return Response.status(200).entity("Page audit was launched").build();
	}

	@POST
	@Path("/auditScenario")
	public Response auditScenario(
			@FormParam("scenarioName") String scenarioName,
			@FormParam("scenarioText") String scenarioText,
			@FormParam("level") String level) {

		//
		// Set<Parameter> parameters =
		// ParameterUtils.getDefaultParametersForScenario();
		//
		// //define level if necessary : supposing it's not mondatory
		// if(level != null && !level.isEmpty()){
		// parameters.add(ParameterUtils.createParameter(5l, "LEVEL", level));
		// }
		// //TODO : adding security a
		//
		// Audit audit = auditService.auditScenario(scenarioName, scenarioText,
		// parameters);
		//
		// //return response
		return Response.status(200).entity("Scenario audit was launched")
				.build();
	}

	public Response auditSite(@FormParam("url") String siteURL,
			@FormParam("level") String level) {
		// TODO Auto-generated method stub
		return null;
	}

}
