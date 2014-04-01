package org.opens.tanaguru.ws;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

/**
 * Unit test initialization abstract class.
 * 
 * @author shamdi
 */
public abstract class AbstactAuditWebServiceTest {

	private static final Logger LOGGER = Logger
			.getLogger(AbstactAuditWebServiceTest.class);

	protected static JerseyTest jersyTest;

	@Before
	public void init() {

		try {
			jersyTest = new JerseyTest(new WebAppDescriptor.Builder(
					"org.opens.tanaguru.ws.impl")
					.contextPath("tanaguru-ws")
					.contextParam("contextConfigLocation",
							"classpath:beans-ws.xml")
					.servletClass(SpringServlet.class)
					.contextListenerClass(ContextLoaderListener.class)
					.requestListenerClass(RequestContextListener.class).build()) {

			};

			jersyTest.setUp(); //start test container
		} catch (Exception e) {
			LOGGER.error("An error occured while initializing test environment"
					+ ExceptionUtils.getFullStackTrace(e));
		}

	}

	@After
	public void tearDown() throws Exception {
		jersyTest.tearDown(); //stop test containter
	}

	/**
	 * Getting a web resource configured for test.
	 * 
	 * @return web resource
	 */
	public WebResource resource() {
		return jersyTest.resource();
	}

	public Client client() {
		return jersyTest.client();
	}

}
