/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asqatasun.crawler;

import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import org.asqatasun.crawler.mock.MockSSP;
import org.asqatasun.entity.audit.SSP;

/**
 *
 * @author alingua
 */
public class CrawlerImplTest extends TestCase {

    private static final String URL = "http://test.asqatasun.org";

    public CrawlerImplTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of isRelCanonicalPage method, of class CrawlerImpl. In this test,
     * the rel canonical is not set.
     */
    public void testIsRelCanonicalPageWithoutRelCanonical() {
        System.out.println("isRelCanonicalPageWithoutRelCanonical");
        SSP ssp = new MockSSP();
        ssp.setURI(URL);
        StringBuilder strb = new StringBuilder();
        strb.append("<html>");
        strb.append("<head>");
        strb.append("</head>");
        strb.append("</html>");
        ssp.setSource(strb.toString());
        CrawlerImpl instance = new CrawlerImpl();
        boolean result = instance.isRelCanonicalPage(ssp);
        assertFalse(result);
    }

    /**
     * Test of isRelCanonicalPage method, of class CrawlerImpl. In this test,
     * rel canonical is set with the complete url.
     */
    public void testIsRelCanonicalPageWithRelCanonical() {
        System.out.println("isRelCanonicalPageWithRelCanonical");
        SSP ssp = new MockSSP();
        ssp.setURI(URL);
        StringBuilder strb = new StringBuilder();
        strb.append("<html>");
        strb.append("<head>");
        strb.append("<link rel=\"canonical\" href=\"http://test.asqatasun.org/lol.html\" />");
        strb.append("</head>");
        strb.append("</html>");
        ssp.setSource(strb.toString());
        CrawlerImpl instance = new CrawlerImpl();
        boolean result = instance.isRelCanonicalPage(ssp);
        assertTrue(result);
    }

    /**
     * Test of isRelCanonicalPage method, of class CrawlerImpl. In this test,
     * rel canonical is set starting with "/"
     */
    public void testIsRelCanonicalPageStartingWithSlash() {
        System.out.println("isRelCanonicalPage");
        SSP ssp = new MockSSP();
        ssp.setURI(URL);
        StringBuilder strb = new StringBuilder();
        strb.append("<html>");
        strb.append("<head>");
        strb.append("<link rel=\"canonical\" href=\"/lol.html\" />");
        strb.append("</head>");
        strb.append("</html>");
        ssp.setSource(strb.toString());
        CrawlerImpl instance = new CrawlerImpl();
        boolean result = instance.isRelCanonicalPage(ssp);
        assertTrue(result);
    }

    /**
     * Test of isRelCanonicalPage method, of class CrawlerImpl. In this test,
     * Base Html element is set.
     */
    public void testIsRelCanonicalPageWithBaseElement() {
        System.out.println("isRelCanonicalPageWithBaseElement");
        SSP ssp = new MockSSP();
        ssp.setURI(URL);
        StringBuilder strb = new StringBuilder();
        strb.append("<html>");
        strb.append("<head>");
        strb.append("<base href=\"http://test.asqatasun.org/images\" />");
        strb.append("<link rel=\"canonical\" href=\"/lol.html\" />");
        strb.append("</head>");
        strb.append("</html>");
        ssp.setSource(strb.toString());
        CrawlerImpl instance = new CrawlerImpl();
        assertTrue(instance.isRelCanonicalPage(ssp));
    }
    
    /**
     * Test of isRelCanonicalPage method, of class CrawlerImpl. In this test,
     * Base Html element is set.
     */
    public void testIsRelCanonicalPageWithBaseElement1() {
        System.out.println("isRelCanonicalPageWithBaseElement");
        SSP ssp = new MockSSP();
        ssp.setURI(URL+"lol.html");
        StringBuilder strb = new StringBuilder();
        strb.append("<html>");
        strb.append("<head>");
        strb.append("<base href=\"http://test.asqatasun.org/\" />");
        strb.append("<link rel=\"canonical\" href=\"/lol.html\" />");
        strb.append("</head>");
        strb.append("</html>");
        ssp.setSource(strb.toString());
        CrawlerImpl instance = new CrawlerImpl();
        assertFalse(instance.isRelCanonicalPage(ssp));
    }
}
