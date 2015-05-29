/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.opens.tanaguru.entity.dao.subject;

import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import java.util.List;
import org.opens.tanaguru.entity.audit.Audit;

/**
 * 
 * @author jkowalczyk
 */
public interface WebResourceDAO extends GenericDAO<WebResource, Long> {

    /**
     *
     * @param url
     *            the url of the web resource to find
     * @return the web resource found or null
     */
    WebResource findByUrl(String url);

    /**
     * 
     * @param audit
     * @param url
     * @return
     */
    WebResource findByAuditAndUrl(Audit audit, String url);

    /**
     *
     * @param url
     * @param webResource
     * @return
     */
    WebResource findByUrlAndParentWebResource(String url, WebResource webResource);

    /**
     * 
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    List<WebResource> findWebResourceFromItsParent(WebResource webResource,
            int start,
            int chunkSize);

    /**
     * 
     * @param webResource
     * @return
     */
    Long findNumberOfChildWebResource(WebResource webResource);

    /**
     * 
     * @param webResourceId
     * @return 
     */
    WebResource ligthRead(Long webResourceId);

    /**
     * 
     * @param webresourceId
     * @return 
     */
    Long findParentWebResourceId(Long webresourceId);
    
    /**
     * 
     * @param webresourceId
     * @return 
     */
    Long findChildWebResourceCount(WebResource webresourceId);
    
}