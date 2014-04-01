package org.opens.tanaguru.ws.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.opens.tanaguru.ws.AbstactAuditWebServiceTest;

import com.sun.jersey.api.client.WebResource;

/**
 * This class tests differents scenarios for calling audit page operations.
 * 
 * @author shamdi
 *
 */
public class AuditPageOperationsTest extends AbstactAuditWebServiceTest {
	

	//launch page audit with url and level parameters

	//launch page audit without url and level parameters
	
	//launch page audit with only url parameter
	@Test
	public void auditPageCase03() {
		
        WebResource resource = resource();
        String audit = resource.path( "auditPage" ).queryParam("url", "oceaneconsulting.com")
//            .accept( MediaType.TEXT_PLAIN )
            .get( String.class );
        assertNotNull( audit );
        assertEquals( audit, "Page audit was launched" );

	} 
	
	
	//launch page audit with only level parameter
}
