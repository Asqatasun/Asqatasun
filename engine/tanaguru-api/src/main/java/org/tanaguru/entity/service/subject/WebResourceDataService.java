/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.entity.service.subject;

import org.tanaguru.entity.subject.Page;
import org.tanaguru.entity.subject.Site;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.service.GenericDataService;
import java.util.List;

/**
 * 
 * @author jkowalczyk
 */
public interface WebResourceDataService extends
        GenericDataService<WebResource, Long> {

    /**
     *
     * @param pageURL
     * @return
     */
    Page createPage(String pageURL);

    /**
     *
     * @param siteURL
     * @return
     */
    Site createSite(String siteURL);

    /**
     *
     * @param url
     *            the url of the web resource to find
     * @return the web resource found or null
     */
    WebResource getByUrl(String url);

    /**
     * 
     * @param url
     * @param webResource
     * @return
     */
    WebResource getByUrlAndParentWebResource(String url, WebResource webResource);

    /**
     * 
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    List<WebResource> getWebResourceFromItsParent(WebResource webResource,
            int start,
            int chunkSize);

    /**
     * 
     * @param webResource
     * @return
     */
    Long getNumberOfChildWebResource(WebResource webResource);

    /**
     * 
     * @param webResourceId
     * @return 
     */
    WebResource ligthRead(Long webResourceId);
    
    /**
     * 
     * @param webResourceId
     * @return 
     */
    WebResource deepRead(Long webResourceId);
    
    /**
     * 
     * @param parentWebResource
     * @return 
     */
    Long getChildWebResourceCount(WebResource parentWebResource);
}