/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */

package com.oceaneconsulting.tanaguru.ws.impl;

import com.oceaneconsulting.tanaguru.decorator.WebResourceDataServiceDecorator;
import com.oceaneconsulting.tanaguru.ws.types.AuditResult;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author jkowalczyk
 */
@Component
@Path("/webresource")
public class WebResourceImplRESTFacade {
 
    private static final Logger LOGGER = Logger.getLogger(WebResourceImplRESTFacade.class);
    
    @Autowired
    @Qualifier("webResourceDataServiceDecorator")
    WebResourceDataServiceDecorator webResourceDataServiceDecorator;

    @Resource(name = "messages")
    Properties messages;
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void webresource(@javax.ws.rs.PathParam("id") Long id, @Suspended final AsyncResponse response) throws IOException {
//        WebResource webResource =  webResourceDataServiceDecorator.getWebResourceFullDeep(id);
        ObjectMapper mapper = new ObjectMapper();
        response.setTimeoutHandler(new TimeoutHandler() {
            @Override
            public void handleTimeout(AsyncResponse asyncResponse) {
                LOGGER.error("Timout error in audit page");
                AuditResult auditResult = new AuditResult();
                auditResult.setMessage((String) messages.get("global.timeout.error"));
                asyncResponse.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(auditResult).build());
            }
        });
        //Time out is fixed to 60 seconds
        response.setTimeout(60, TimeUnit.SECONDS);
//        response.resume(mapper.writeValueAsString(webResource));
    }
}