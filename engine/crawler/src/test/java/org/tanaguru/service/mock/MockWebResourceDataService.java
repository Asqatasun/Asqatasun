/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */

package org.tanaguru.service.mock;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.service.subject.WebResourceDataService;
import org.tanaguru.entity.subject.*;
import org.tanaguru.sdk.entity.dao.GenericDAO;
import org.tanaguru.sdk.entity.factory.GenericFactory;

/**
 *
 * @author jkowalczyk
 */
public class MockWebResourceDataService implements WebResourceDataService{

    private final Map<Long, WebResource> wrMap = new LinkedHashMap<>();

    private Long id = 1l;
    
    @Override
    public Page createPage(String pageUrl) {
        return new PageImpl(pageUrl);
    }

    @Override
    public Site createSite(String siteUrl) {
        return new SiteImpl(siteUrl);
    }

    @Override
    public WebResource getByUrl(String url) {
        for (WebResource wr : wrMap.values()) {
            if (StringUtils.equals(wr.getURL(),url)) {
                return wr;
            }
        }
        return null;
    }

    @Override
    public WebResource getByUrlAndParentWebResource(String url, WebResource webResource) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<WebResource> getWebResourceFromItsParent(WebResource webResource, int start, int chunkSize) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getNumberOfChildWebResource(WebResource webResource) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WebResource create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(WebResource entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(WebResource entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Collection<WebResource> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<WebResource> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WebResource read(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WebResource saveOrUpdate(WebResource entity) {
        entity.setId(id);
        wrMap.put(entity.getId(), entity);
        id ++;
        return entity;
    }

    @Override
    public Collection<WebResource> saveOrUpdate(Collection<WebResource> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<WebResource, Long> dao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<WebResource> factory) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WebResource update(WebResource entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WebResource ligthRead(Long webResourceId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WebResource deepRead(Long webResourceId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getChildWebResourceCount(WebResource parentWebResource) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
