/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
package org.opens.tanaguru.rules.test;

import java.util.*;
import org.opens.tanaguru.entity.audit.*;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;

/**
 *
 * @author jkowalczyk
 */
public class ContentDataServiceMock implements ContentDataService{

    private int i=0;
    private Map<Long, RelatedContent> relatedContentMap = new HashMap<Long, RelatedContent>();
    
    @Override
    public JavascriptContent findJavascriptContent(Audit audit, String uri) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SSP findSSP(WebResource webresource, String uri) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long findNumberOfSSPContentFromAudit(Audit audit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasAdaptedSSP(Audit audit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasContent(Audit audit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getNumberOfOrphanContent(WebResource webResource) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Content> getOrphanContentList(WebResource webResource, int start, int chunkSize) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getNumberOfOrphanRelatedContent(WebResource webResource) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Content> getOrphanRelatedContentList(WebResource webResource, int start, int chunkSize) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getNumberOfSSPFromWebResource(WebResource webResource, int httpStatusCode) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getNumberOfRelatedContentFromWebResource(WebResource webResource) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveContentRelationShip(SSP ssp, Set<Long> relatedContentIdSet) {
        for (Long id : relatedContentIdSet) {
            ssp.addRelatedContent(relatedContentMap.get(id));
        }
    }

    @Override
    public void saveAuditToContent(Long idContent, Long idAudit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Long> getSSPIdsFromWebResource(Long webResourceId, int httpStatusCode, int start, int chunkSize) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Long> getRelatedContentIdsFromWebResource(Long webResourceId, int start, int chunkSize) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Content readWithRelatedContent(Long id, boolean isFetchParameters) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<StylesheetContent> getExternalStylesheetFromAudit(Audit audit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<RelatedContent> getRelatedContentFromAudit(Audit audit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteContentRelationShip(Long relatedContentId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Content create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(Content entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Content entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Collection<Content> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Content> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Content read(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Content saveOrUpdate(Content entity) {
        entity.setId(Long.valueOf(i));
        if (entity instanceof RelatedContent) {
            relatedContentMap.put(Long.valueOf(i), (RelatedContent)entity);
        }
        i++;
        return entity;
    }

    @Override
    public Collection<Content> saveOrUpdate(Collection<Content> entitySet) {
        return null;
    }

    @Override
    public void setEntityDao(GenericDAO<Content, Long> dao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<Content> factory) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Content update(Content entity) {
        return null;
    }

    @Override
    public void deleteRelatedContentFromContent(Content content) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Content> getSSPFromWebResource(Long webResourceId, Long startValue, int windowSize, boolean acceptContentWithNullDom) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Content> getSSPWithRelatedContentFromWebResource(Long webResourceId, Long startValue, int windowSize, boolean acceptContentWithNullDom) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}