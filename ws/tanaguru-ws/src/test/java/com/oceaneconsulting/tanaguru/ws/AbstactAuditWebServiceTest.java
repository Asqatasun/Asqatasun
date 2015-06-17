package com.oceaneconsulting.tanaguru.ws;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import com.oceaneconsulting.tanaguru.ws.impl.AuditWebImpl;

/**
 * Unit test initialization abstract class.
 *
 * @author shamdi at oceaneconsulting com
 *
 */
public abstract class AbstactAuditWebServiceTest extends JerseyTest {

    private static final Logger LOGGER = Logger.getLogger(AbstactAuditWebServiceTest.class);

    public AbstactAuditWebServiceTest() {

    }

    /**
     * Register specific context parameters
     * @return 
     */
    @Override
    protected ResourceConfig configure() {

        // logging enabled
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        // Create specific test parameters for WSApplication instance
        return new WSApplication().register(AuditWebImpl.class) //root resource
                .property("contextConfigLocation", "classpath:test-beans-ws.xml"); // application context configuration file
    }

//	@Before
//	public void init() {
//
//		try {
//			jersyTest = new JerseyTest(new WebAppDescriptor.Builder(
//					"org.tanaguru.ws.impl")
//					.contextPath("tanaguru-ws")
//					.contextParam("contextConfigLocation",
//							"classpath:beans-ws.xml")
//					.servletClass(SpringServlet.class)
//					.contextListenerClass(ContextLoaderListener.class)
//					.requestListenerClass(RequestContextListener.class).build()) {
//
//			};
//
//			jersyTest.setUp(); //start test container
//		} catch (Exception e) {
//			LOGGER.error("An error occured while initializing test environment"
//					+ ExceptionUtils.getFullStackTrace(e));
//		}
//
//	}
//    @Before
//    public void setUp() throws Exception {
//    	super.setUp();
//    }
//    
//    
//	@After
//	public void tearDown() throws Exception {
//		super.tearDown(); //stop test containter
//	}
//
//	/**
//	 * Getting a web resource configured for test.
//	 * 
//	 * @return web resource
//	 */
//	public WebResource resource() {
//		return jersyTest.resource();
//	}
//
//	public Client client() {
//		return jersyTest.client();
//	}
}
