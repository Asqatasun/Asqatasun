package com.oceaneconsulting.tanaguru.ws;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.oceaneconsulting.tanaguru.ws.types.AuditSiteOrder;




/**
 * Webservice interface definition.
 * 
 * This interface defines all exposed webservice's operations. 
 *
 * @author shamdi at oceaneconsulting dot com
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
	
	public Response launchAuditSite(AuditSiteOrder auditSiteOrder, @Context SecurityContext securityContext);
	
	//As a first step, json file is defined as a simple string. This may change in future versions.
	public Response auditScenario( String scenarioName, String scenarioText , String level);

	
}
