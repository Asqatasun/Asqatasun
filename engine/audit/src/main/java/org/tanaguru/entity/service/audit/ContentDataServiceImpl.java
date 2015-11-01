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
package org.tanaguru.entity.service.audit;

import java.util.*;
import org.apache.http.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.tanaguru.entity.audit.*;
import org.tanaguru.entity.dao.audit.ContentDAO;
import org.tanaguru.entity.factory.audit.ContentFactory;
import org.tanaguru.entity.subject.Page;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public class ContentDataServiceImpl extends AbstractGenericDataService<Content, Long>
        implements ContentDataService {

    private static final Logger LOGGER = Logger.getLogger(ContentDataServiceImpl.class);

    public ContentDataServiceImpl() {
        super();
    }

    @Override
    public JavascriptContent findJavascriptContent(Audit audit, String uri) {
        return (JavascriptContent) ((ContentDAO) entityDao).find(audit, uri);
    }

    @Override
    public SSP findSSP(WebResource webresource, String uri) {
        return (SSP) ((ContentDAO) entityDao).find(webresource, uri);
    }

    @Override
    public Long findNumberOfSSPContentFromAudit(Audit audit){
        return ((ContentDAO) entityDao).findNumberOfSSPContentFromAudit(audit);
    }
    
    @Override
    public boolean hasAdaptedSSP(Audit audit) {
        return ((ContentDAO) entityDao).hasAdaptedSSP(audit);
    }

    @Override
    public boolean hasContent(Audit audit) {
        return ((ContentDAO) entityDao).hasContent(audit);
    }

    @Override
    public Long getNumberOfOrphanContent(WebResource webResource) {
        return ((ContentDAO) entityDao).
                findNumberOfOrphanContentFromWebResource(webResource);
    }

    @Override
    public List<Content> getOrphanContentList(
            WebResource webResource,
            int start,
            int chunkSize) {
        return ((ContentDAO) entityDao).findOrphanContentList(
                    webResource,
                    start,
                    chunkSize);
    }

    @Override
    public Long getNumberOfOrphanRelatedContent(WebResource webResource) {
        return ((ContentDAO) entityDao).
                findNumberOfOrphanRelatedContentFromWebResource(webResource);
    }

    @Override
    public List<Content> getOrphanRelatedContentList(
            WebResource webResource,
            int start,
            int chunkSize) {
        return ((ContentDAO) entityDao).findOrphanRelatedContentList(
                    webResource,
                    start,
                    chunkSize);
    }

    @Override
    public Long getNumberOfSSPFromWebResource(WebResource webResource, int httpStatusCode) {
        return ((ContentDAO) entityDao).findNumberOfSSPFromWebResource(webResource, httpStatusCode);
    }

    @Override
    public Long getNumberOfRelatedContentFromWebResource(WebResource webResource) {
        return ((ContentDAO) entityDao).
                findNumberOfRelatedContentFromWebResource(webResource);
    }

    @Override
    public void saveContentRelationShip(SSP ssp, Set<Long> relatedContentIdSet) {
        ((ContentDAO) entityDao).saveContentRelationShip(ssp, relatedContentIdSet);
    }

    @Override
    public void saveAuditToContent(Long idContent, Long idAudit ) {
        ((ContentDAO) entityDao).saveAuditToContent(idContent, idAudit);
    }

    @Override
    public Collection<Long> getSSPIdsFromWebResource(
            Long webResourceId,
            int httpStatusCode,
            int start,
            int chunkSize) {
        return ((ContentDAO) entityDao).getSSPFromWebResource(
                webResourceId,
                httpStatusCode,
                start,
                chunkSize);
    }

    @Override
    public Collection<Long> getRelatedContentIdsFromWebResource(
            Long webResourceId,
            int start,
            int chunkSize) {
        return ((ContentDAO) entityDao).getRelatedContentFromWebResource(
                webResourceId,
                start,
                chunkSize);
    }
    
    @Override
    public Content readWithRelatedContent(Long id, boolean isFetchParameters) {
        return ((ContentDAO) entityDao).readWithRelatedContent(id, isFetchParameters);
    }

    @Override
    public Collection<StylesheetContent> getExternalStylesheetFromAudit(Audit audit) {
        return ((ContentDAO) entityDao).findExternalStylesheetFromAudit(audit);
    }

    @Override
    public Collection<RelatedContent> getRelatedContentFromAudit(Audit audit) {
        return ((ContentDAO) entityDao).findRelatedContentFromAudit(audit);
    }

    @Override
    public void deleteContentRelationShip(Long relatedContentId) {
        ((ContentDAO) entityDao).deleteContentRelationShip(relatedContentId);
    }
    
    @Override
    public void deleteRelatedContentFromContent(Content content) {
        ((ContentDAO) entityDao).deleteRelatedContentFromContent(content);
    }

    @Override
    public Collection<Content> getSSPFromWebResource(
            Long webResourceId, 
            Long startValue, 
            int windowSize,
            boolean acceptContentWithNullDom) {
        return getContentList(webResourceId, startValue, windowSize, false,acceptContentWithNullDom);
    }
    
    @Override
    public Collection<Content> getSSPWithRelatedContentFromWebResource(
            Long webResourceId, 
            Long startValue, 
            int windowSize,
            boolean acceptContentWithNullDom) {
        return getContentList(webResourceId, startValue, windowSize, true,acceptContentWithNullDom);
    }
    
    /**
     * 
     * @param webResourceId
     * @param startValue
     * @param windowSize
     * @param beginProcessDate
     * @param getContentWithRelatedContent
     * @param getContentWithNullDom
     * @return 
     */
    private List<Content> getContentList(
            Long webResourceId, 
            Long startValue, 
            int windowSize,
            boolean getContentWithRelatedContent, 
            boolean getContentWithNullDom) {

        Date beginProcessDate = Calendar.getInstance().getTime();
        List<Content> contentList = new ArrayList<>();
        
        // First we retrieve a list of Ids
        Collection<Long> contentIdList = this.getSSPIdsFromWebResource(
                                webResourceId,
                                HttpStatus.SC_OK,
                                startValue.intValue(),
                                windowSize);
        
        // we retrieve each content from its ID and add it to the contentList 
        // that will be returned
        for (Long id : contentIdList) {
            Content content;
            if (getContentWithRelatedContent) {
                content = this.readWithRelatedContent(id, true);
            } else {
                content = this.read(id);
            }
            if (content != null && 
                    ( getContentWithNullDom || 
                        (!getContentWithNullDom 
                            && content instanceof SSP 
                            && StringUtils.isNotEmpty(((SSP)content).getDOM())))) {
                contentList.add(content);
            }
        }
        
        if (LOGGER.isDebugEnabled()) {
            long length = 0;
            int nbOfResources = 0;
            for (Content content : contentList) {
                if (((SSP) content).getDOM() != null) {
                    length += ((SSP) content).getDOM().length();
                    if (getContentWithRelatedContent) {
                        nbOfResources += ((SSP) content).getRelatedContentSet().size();
                    }
                }
            }
            StringBuilder debugMessage = new StringBuilder("Retrieving  ")
                        .append(contentList.size())
                        .append(" SSP took ")
                        .append(Calendar.getInstance().getTime().getTime() - beginProcessDate.getTime())
                        .append(" ms and working on ")
                        .append(length)
                        .append(" characters");
            if (getContentWithRelatedContent) {
                debugMessage.append(" and ");
                debugMessage.append(nbOfResources);
                debugMessage.append(" relatedContent ");
            }
            LOGGER.debug(debugMessage.toString());
        }
        return contentList;
    }

    @Override
    public SSP getSSP(String uri) {
        return ((ContentFactory) entityFactory).createSSP(uri);
    }

    @Override
    public SSP getSSP(String uri, Page page) {
        return ((ContentFactory) entityFactory).createSSP(uri, page);
    }

    @Override
    public SSP getSSP(Date dateOfLoading, String uri) {
        return ((ContentFactory) entityFactory).createSSP(dateOfLoading, uri);
    }

    @Override
    public SSP getSSP(Date dateOfLoading, String uri, String sourceCode, Page page) {
        SSP ssp = ((ContentFactory) entityFactory).createSSP(dateOfLoading, uri, sourceCode, page);
        saveOrUpdate(ssp);
        return ssp;
    }
    
    @Override
    public SSP getSSP(Date dateOfLoading, String uri, int httpStatusCode) {
        return ((ContentFactory) entityFactory).createSSP(dateOfLoading, uri, httpStatusCode);
    }

    @Override
    public SSP getSSP(Date dateOfLoading, String uri, String sourceCode, Page page, int httpStatusCode) {
        return ((ContentFactory) entityFactory).createSSP(dateOfLoading, uri, sourceCode, page, httpStatusCode);
    }
    
    @Override
    public SSP getSSP(Date dateOfLoading, String uri, String sourceCode, Audit audit, Page page, int httpStatusCode) {
        SSP ssp = ((ContentFactory) entityFactory).createSSP(dateOfLoading, uri, sourceCode, page, httpStatusCode);
        ssp.setAudit(audit);
        ssp = (SSP)saveOrUpdate(ssp);
        return ssp;
    }

    @Override
    public StylesheetContent getStylesheetContent(String uri, SSP ssp) {
        return ((ContentFactory) entityFactory).createStylesheetContent(uri, ssp);
    }

    @Override
    public StylesheetContent getStylesheetContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode) {
        return ((ContentFactory) entityFactory).createStylesheetContent(dateOfLoading, uri, ssp, sourceCode);
    }

    @Override
    public StylesheetContent getStylesheetContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode, int httpStatusCode) {
        return ((ContentFactory) entityFactory).createStylesheetContent(dateOfLoading, uri, ssp, sourceCode, httpStatusCode);
    }

    @Override
    public JavascriptContent getJavascriptContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode) {
        return ((ContentFactory) entityFactory).createJavascriptContent(dateOfLoading, uri, ssp, sourceCode);
    }

    @Override
    public JavascriptContent getJavascriptContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode, int httpStatusCode) {
        return ((ContentFactory) entityFactory).createJavascriptContent(dateOfLoading, uri, ssp, sourceCode, httpStatusCode);
    }

    @Override
    public ImageContent getImageContent(Date dateOfLoading, String uri, SSP ssp, byte[] binaryContent) {
        return ((ContentFactory) entityFactory).createImageContent(dateOfLoading, uri, ssp, binaryContent);
    }

    @Override
    public ImageContent getImageContent(String uri, SSP ssp) {
        return ((ContentFactory) entityFactory).createImageContent(uri, ssp);
    }

    @Override
    public ImageContent getImageContent(Date dateOfLoading, String uri, SSP ssp, byte[] binaryContent, int httpStatusCode) {
        return ((ContentFactory) entityFactory).createImageContent(dateOfLoading, uri, ssp, binaryContent, httpStatusCode);
    }

    @Override
    public RelatedContent getRelatedContent(String uri, SSP ssp) {
        return ((ContentFactory) entityFactory).createRelatedContent(uri, ssp);
    }
    
}