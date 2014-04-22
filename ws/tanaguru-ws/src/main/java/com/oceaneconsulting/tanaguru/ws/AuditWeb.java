package com.oceaneconsulting.tanaguru.ws;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;





/**
 * Webservice interface definition.
 * 
 * This interface defines all exposed webservice's operations. 
 *
 */
public interface AuditWeb {
	

	public void auditPage(final String pageURL, 
    				  	  final String level, 
    				  	  final String tblMarker, 
    				  	  final String prTblMarker, 
    				  	  final String dcrImgMarker,	 
    				  	  final String infImgMarker,
    				  	  final AsyncResponse response);
	
	//As a first step, json file is defined as a simple string. This may change in future versions.
	public Response auditScenario( String scenarioName, String scenarioText , String level);
	
	public Response auditSite(String siteURL, String level);
	
}
