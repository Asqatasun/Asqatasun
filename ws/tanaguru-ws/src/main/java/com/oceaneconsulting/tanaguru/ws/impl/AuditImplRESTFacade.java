/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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

import com.oceaneconsulting.tanaguru.decorator.AuditDataServiceDecorator;
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
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.AuditImpl;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author jkowalczyk
 */
@Component
@Path("/audit")
public class AuditImplRESTFacade {
 
    private static final Logger LOGGER = Logger.getLogger(AuditImplRESTFacade.class);
    
    @Autowired
    @Qualifier("decoratorAuditDataService")
    AuditDataServiceDecorator auditDataService;

    @Resource(name = "messages")
    Properties messages;
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void audit(@javax.ws.rs.PathParam("id") Long id, @Suspended final AsyncResponse response) {
        Audit audit = auditDataService.getAuditFullDeep(id);
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
        try {
            String auditJson = mapper.writeValueAsString(audit);
            response.resume(auditJson);
            Audit resultAudit = mapper.readValue(auditJson, AuditImpl.class);
            LOGGER.debug(resultAudit.getId());
            LOGGER.debug(resultAudit.getSubject().getURL());
            for (ProcessResult pr : resultAudit.getSubject().getProcessResultList()) {
                LOGGER.debug(pr.getTest().getCode() +" : "+pr.getValue());
                for (ProcessRemark prk : pr.getRemarkSet()) {
                    if (prk instanceof SourceCodeRemark) {
                        LOGGER.debug(prk.getIssue() + " : " +prk.getMessageCode());
                        LOGGER.debug(((SourceCodeRemark)prk).getSnippet());
                    }
                }
            }
        } catch (IOException ex) {
            LOGGER.error("exception " + ex.getMessage());
        }
    }
}