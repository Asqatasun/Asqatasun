package com.oceaneconsulting.tanaguru.ws.impl;

import org.apache.log4j.Logger;
import com.oceaneconsulting.tanaguru.ws.AbstactAuditWebServiceTest;

/**
 * This class tests different scenarios for calling audit site operations.
 *
 * @author shamdi at oceaneconsulting dot com
 *
 */
public class AuditSiteOperationsTest extends AbstactAuditWebServiceTest {

    private static final Logger LOGGER = Logger.getLogger(AuditSiteOperationsTest.class);

    public void testAuditSiteCase01() {

//		AuditSiteOrder auditOrder = new AuditSiteOrder();
//		auditOrder.setSiteURL("oceaneconsulting.com");
//		auditOrder.setLevel(AuditLevel.AW22_AR.getValue());
//		
//		try {
//			Client client = ClientBuilder.newClient();
//			Response response = client.target("http://localhost:9998/").path("secure/launchAuditSite") //basic url
//					.request(MediaType.APPLICATION_JSON).post(Entity.entity(auditOrder, MediaType.APPLICATION_JSON)); // input parameter
//			//Verify response execution status 
//			assertTrue(HttpServletResponse.SC_OK == response.getStatus());
//			
//		} catch (Exception e) {
//			LOGGER.info(ExceptionUtils.getFullStackTrace(e));
//		}
    }
}
