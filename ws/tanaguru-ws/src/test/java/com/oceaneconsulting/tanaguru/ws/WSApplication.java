package com.oceaneconsulting.tanaguru.ws;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.ContainerRequestFilter;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.TestProperties;

/**
 * 
 * @author shamdi at oceaneconsulting dot com
 *
 */
@ApplicationPath("/")
public class WSApplication extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public WSApplication () {
        
    	// Register JAX-RS ContainerRequestFilter. 
        register(ContainerRequestFilter.class);
        
        //Register Jackson feature for JSON
        register(JacksonFeature.class);
        
        // Default test factory container
        property(TestProperties.CONTAINER_FACTORY, org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory.class);
    }
}