package com.oceaneconsulting.tanaguru.ws.impl;

import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.Response;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import com.oceaneconsulting.tanaguru.ws.AbstactAuditWebServiceTest;

/**
 * This class tests differents scenarios for calling audit page operations.
 * 
 * @author shamdi
 *
 */
public class AuditPageOperationsTest extends AbstactAuditWebServiceTest {
	
	private static final Logger LOGGER = Logger.getLogger(AuditPageOperationsTest.class);
	
	
	//launch page audit with url and level parameters

	//launch page audit without url and level parameters
	
	//launch page audit with only url parameter
//	@Test
//	public void auditPageCase03() {
//		
//        WebResource resource = 
//        		resource();
//        String audit = resource.path( "auditPage" ).queryParam("url", "oceaneconsulting.com")
////            .accept( MediaType.TEXT_PLAIN )
//            .get( String.class );
//        assertNotNull( audit );
//        assertEquals( audit, "Page audit was launched" );
//
//	} 
	@Test
	public void auditPageAsyncCase03() {
		
//
//			Client client = Client.create();
//			WebResource webResource = client.resource(restURL);
//			ClientResponse response = webResource.post(ClientResponse.class, "payload");
		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("http://localhost:9998/").path("auditPage").queryParam("url","oceaneconsulting.com");
			 
			Future<String> result = target.request(MediaType.APPLICATION_JSON).async().get(String.class);
			
			LOGGER.info(result.get());
			
		} catch (Exception e) {
			LOGGER.info(ExceptionUtils.getFullStackTrace(e));
		}
	} 
	
	
	//launch page audit with only level parameter
}
