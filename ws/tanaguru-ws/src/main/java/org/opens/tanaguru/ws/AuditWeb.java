package org.opens.tanaguru.ws;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;


/**
 * Webservice interface definition.
 * 
 * This interface defines all exposed webservice's operations. 
 *
 */
public interface AuditWeb {
	
	// Launching audits operations

	

    public Response auditPage(String pageURL, String level, HttpServletRequest request);
	
	//As a first step, json file is defined as a simple string. This may change in future versions.
	public Response auditScenario( String scenarioName, String scenarioText , String level);
	
	public Response auditSite(String siteURL, String level);
	
	//results
}
