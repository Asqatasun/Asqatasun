package com.oceaneconsulting.tanaguru.ws.impl;

import static org.junit.Assert.assertTrue;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.oceaneconsulting.tanaguru.enumerations.AuditLevel;
import com.oceaneconsulting.tanaguru.ws.AbstactAuditWebServiceTest;
import com.oceaneconsulting.tanaguru.ws.types.AuditScenarioOrder;
import com.oceaneconsulting.tanaguru.ws.types.GlobalStatsOrder;

/**
 * This class tests different scenarios for calling audit site operations.
 * 
 * @author shamdi at oceaneconsulting dot com
 *
 */
public class AuditScenarioOperationsTest  extends AbstactAuditWebServiceTest {
	
	private static final Logger LOGGER = Logger.getLogger(AuditSiteOperationsTest.class);
	
	
	@Test ///no security provider
	public void auditScenarioCase01() {
		
		AuditScenarioOrder auditOrder = new AuditScenarioOrder();
		auditOrder.setScenarioLabel("visit_oceane_website");
		auditOrder.setScenarioText("{ \"type\": \"script\",  \"seleniumVersion\": \"2\",  \"formatVersion\": 1,  \"steps\": [    {      \"type\": \"get\",      \"url\": \"http://www.oceaneconsulting.com/\"    }]}");
		auditOrder.setLevel(AuditLevel.AW22_AR.getValue());
		
		try {
			Client client = ClientBuilder.newClient();
			Response response = client.target("http://localhost:9998/").path("secure/launchAuditScenario") //basic url
					.request(MediaType.APPLICATION_JSON).post(Entity.entity(auditOrder, MediaType.APPLICATION_JSON)); // input parameter
			//Verify response execution status 
			assertTrue(HttpServletResponse.SC_OK == response.getStatus());
			
		} catch (Exception e) {
			LOGGER.info(ExceptionUtils.getFullStackTrace(e));
		}
	}
	
	@Test ///no security provider
	public void auditScenarioCase02() {
		//Get all stats of audit scenario 
		GlobalStatsOrder statsOrder = new GlobalStatsOrder();
		statsOrder.setAuditType(2);
		
		try {
			Client client = ClientBuilder.newClient();
			Response response = client.target("http://localhost:9998/").path("secure/globalStats") //basic url
					.request(MediaType.APPLICATION_JSON).post(Entity.entity(statsOrder, MediaType.APPLICATION_JSON)); // input parameter
			//Verify response execution status 
			assertTrue(HttpServletResponse.SC_OK == response.getStatus());
			
		} catch (Exception e) {
			LOGGER.info(ExceptionUtils.getFullStackTrace(e));
		}
	}
}
