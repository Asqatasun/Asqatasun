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

package org.tanaguru.ws.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.AuditImpl;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;

/**
 *
 * @author jkowalczyk
 */
public class AuditWebServiceClient {

    private static final Logger LOGGER = Logger.getLogger(AuditWebServiceClient.class);
    private static final String TANAGURU_WS_URL = "http://localhost:8080/tanaguru-ws/";

    /**
     * 
     * @param id
     * @return a POJO Audit handling all the audit result for the given id
     */
    public Audit getAuditFromId(Long id) {
        return getAuditFromId(id, TANAGURU_WS_URL);
    }

    /**
     * 
     * @param id
     * @param tanaguruWsUrl
     * @return a POJO Audit handling all the audit result for the given id
     */
    public Audit getAuditFromId(Long id, String tanaguruWsUrl) {
        Client client = ClientBuilder.newBuilder().register(AuditMessageBodyReader.class).build();

        WebTarget target = client.target(tanaguruWsUrl).path("audit/" + id.toString()); // input parameter

        Response resultAudit = target.request(MediaType.APPLICATION_JSON_TYPE).get();
        Audit audit = resultAudit.readEntity(AuditImpl.class);
        LOGGER.debug(audit.getSubject().getURL());
        for (ProcessResult pr : audit.getSubject().getProcessResultList()) {
            LOGGER.debug(pr.getTest().getCode() + " : " + pr.getValue());
            for (ProcessRemark prk : pr.getRemarkSet()) {
                if (prk instanceof SourceCodeRemark) {
                    LOGGER.debug(prk.getIssue() + " : " + prk.getMessageCode());
                    LOGGER.debug(((SourceCodeRemark) prk).getSnippet());
                }
            }
        }
        return audit;
    }
    
    /**
     * Inner class that reads the body from the service call and return 
     * a POJO Audit 
     */
    private static class AuditMessageBodyReader implements MessageBodyReader<AuditImpl> {

        @Override
        public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
            LOGGER.info("Cheking if content is readable or not");
            return type == AuditImpl.class && !mt.isWildcardType()
                    && !mt.isWildcardSubtype()
                    && mt.isCompatible(MediaType.valueOf("application/json"));
        }

        @Override
        public AuditImpl readFrom(
                Class<AuditImpl> type,
                Type type1,
                Annotation[] antns,
                MediaType mt,
                MultivaluedMap<String, String> mm,
                InputStream in) throws IOException, WebApplicationException {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(responseStrBuilder.toString(), AuditImpl.class);
        }

    }
}
