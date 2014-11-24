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

import com.oceaneconsulting.tanaguru.enumerations.AuditLevel;
import com.oceaneconsulting.tanaguru.util.ParameterInputs;
import com.oceaneconsulting.tanaguru.ws.AbstactAuditWebServiceTest;
import com.oceaneconsulting.tanaguru.ws.types.AuditResult;
import static junit.framework.Assert.assertTrue;

/**
 * This class tests different scenarios for calling audit page operation.
 *
 * @author shamdi at oceaneconsulting dot com
 *
 */
public class AuditPageOperationsTest extends AbstactAuditWebServiceTest {

    private static final Logger LOGGER = Logger.getLogger(AuditPageOperationsTest.class);

    /**
     * TEST CASE 01 :
     *
     * Inputs url : http://accessibility.psu.edu/tablecomplexhtml level :
     * Accessiweb 2.2
     *
     * Outputs :
     *
     * url :
     * http://localhost:8080/tgol-web-app/home/contract/page-result.html?type=false&ma=false&wr=${id_web_resource}
     * mark : 76.4172
     */
    public void testAuditPageAsyncCase01() {
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://localhost:9998/").path("auditPage") //basic url
                    .queryParam(ParameterInputs.PAGE_URL, "accessibility.psu.edu/tablecomplexhtml") //input parameter
                    .queryParam(ParameterInputs.AUDIT_LEVEL, AuditLevel.AW22_AR.getValue()); // input parameter

            Future<AuditResult> result = target.request(MediaType.APPLICATION_JSON).async().get(AuditResult.class);

            LOGGER.info(result.get());

            assertTrue("Score test is not OK for the given parameters.", new Float("76.4172").equals(result.get().getScore()));

        } catch (Exception e) {
            LOGGER.info(ExceptionUtils.getFullStackTrace(e));
        }
    }

    /**
     * TEST CASE 02 :
     *
     * Inputs url : http://accessibility.psu.edu/tablecomplexhtml level :
     * Accessiweb 2.2 DATA_TABLE_MARKER : chart Outputs :
     *
     * url :
     * http://localhost:8080/tgol-web-app/home/contract/page-result.html?type=false&ma=false&wr=${id_web_resource}
     * mark : 74.4445
     */
    public void testAuditPageAsyncCase02() {
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://localhost:9998/").path("auditPage") //basic url
                    .queryParam(ParameterInputs.PAGE_URL, "accessibility.psu.edu/tablecomplexhtml") //input parameter
                    .queryParam(ParameterInputs.AUDIT_LEVEL, AuditLevel.AW22_AR.getValue()) // input parameter
                    .queryParam(ParameterInputs.DATA_TABLE_MARKER_PARAM, "chart"); // input parameter

            Future<AuditResult> result = target.request(MediaType.APPLICATION_JSON).async().get(AuditResult.class);

            LOGGER.info(result.get());

            assertTrue("Score test is not OK for the given parameters.", new Float("74.4445").equals(result.get().getScore()));

        } catch (Exception e) {
            LOGGER.info(ExceptionUtils.getFullStackTrace(e));
        }
    }

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
//	@Test
//	public void auditPageAsyncCase03() {
//		
////
////			Client client = Client.create();
////			WebResource webResource = client.resource(restURL);
////			ClientResponse response = webResource.post(ClientResponse.class, "payload");
//		try {
//			Client client = ClientBuilder.newClient();
//			WebTarget target = client.target("http://localhost:9998/").path("auditPage").queryParam("url","oceaneconsulting.com");
//			 
//			Future<String> result = target.request(MediaType.APPLICATION_JSON).async().get(String.class);
//			
//			LOGGER.info(result.get());
//			
//		} catch (Exception e) {
//			LOGGER.info(ExceptionUtils.getFullStackTrace(e));
//		}
//	} 
	//launch page audit with only level parameter
}
