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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.entity.service.subject;

import java.util.List;
import org.tanaguru.entity.dao.subject.WebResourceDAO;
import org.tanaguru.entity.factory.subject.WebResourceFactory;
import org.tanaguru.entity.subject.Page;
import org.tanaguru.entity.subject.Site;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.tanaguru.util.FileNaming;

/**
 * 
 * @author jkowalczyk
 */
public class WebResourceDataServiceImpl extends AbstractGenericDataService<WebResource, Long> implements
        WebResourceDataService {

    public WebResourceDataServiceImpl() {
        super();
    }

    @Override
    public Page createPage(String url) {
        return ((WebResourceFactory) entityFactory).createPage(FileNaming.addProtocolToUrl(url));
    }

    @Override
    public Site createSite(String url) {
        return ((WebResourceFactory) entityFactory).createSite(url);
    }

    @Override
    public WebResource getByUrl(String url) {
        return ((WebResourceDAO) entityDao).findByUrl(url);
    }

    @Override
    public WebResource read(Long key) {
        WebResource entity = super.read(key);
        return entity;
    }

    @Override
    public WebResource getByUrlAndParentWebResource(String url, WebResource webResource) {
        return ((WebResourceDAO) entityDao).findByUrlAndParentWebResource(url, webResource);
    }

    @Override
    public List<WebResource> getWebResourceFromItsParent(WebResource webResource, int start, int chunkSize) {
        return ((WebResourceDAO) entityDao).findWebResourceFromItsParent(
                webResource,
                start,
                chunkSize);
    }

    @Override
    public Long getNumberOfChildWebResource(WebResource webResource) {
        return ((WebResourceDAO) entityDao).findNumberOfChildWebResource(webResource);
    }

    @Override
    public WebResource ligthRead(Long webResourceId) {
        return ((WebResourceDAO) entityDao).ligthRead(webResourceId);
    }
    
    @Override
    public WebResource deepRead(Long webResourceId) {
        return ((WebResourceDAO) entityDao).read(webResourceId);
    }

    @Override
    public Long getChildWebResourceCount(WebResource parentWebResource) {
        return ((WebResourceDAO) entityDao).findChildWebResourceCount(parentWebResource);
    }

}