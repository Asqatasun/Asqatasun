/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.entity.service.audit;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.tanaguru.entity.audit.*;
import org.tanaguru.entity.subject.Page;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public interface ContentDataService extends GenericDataService<Content, Long> {

    /**
     *
     * @param audit
     *            the audit to use as a reference.
     * @param uri
     *            the uri to find.
     * @return the {@link JavascriptContent} instance found.
     */
    JavascriptContent findJavascriptContent(Audit audit, String uri);


    /**
     *
     * @param webresource
     *            the webresource to use as a reference.
     * @param uri
     *            the uri to find.
     * @return the {@link SSP} instance found
     */
    SSP findSSP(WebResource webresource, String uri);

    /**
     *
     * @param audit
     * @return
     */
    Long findNumberOfSSPContentFromAudit(Audit audit);

    /**
     * 
     * @param audit
     * @return
     */
    boolean hasAdaptedSSP(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    boolean hasContent(Audit audit);

    /**
     *
     * @param webResource
     * @return
     */
    Long getNumberOfOrphanContent(WebResource webResource);

    /**
     *
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    List<Content> getOrphanContentList(
            WebResource webResource,
            int start,
            int chunkSize);

    /**
     *
     * @param webResource
     * @return
     */
    Long getNumberOfOrphanRelatedContent(WebResource webResource);

    /**
     *
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    List<Content> getOrphanRelatedContentList(
            WebResource webResource,
            int start,
            int chunkSize);

    /**
     *
     * @param webResource
     * @return
     */
    Long getNumberOfSSPFromWebResource(WebResource webResource, int httpStatusCode);

    /**
     *
     * @param webResource
     * @return
     */
    Long getNumberOfRelatedContentFromWebResource(WebResource webResource); 

    /**
     * 
     * @param ssp
     * @param relatedContentIdSet
     */
    void saveContentRelationShip(SSP ssp, Set<Long> relatedContentIdSet); 

    /**
     *
     * @param idContent
     * @param idAudit
     */
    void saveAuditToContent(Long idContent, Long idAudit );  

    /**
     *
     * @param webResourceId
     * @param start
     * @param chunkSize
     * @return
     */
    Collection<Long> getSSPIdsFromWebResource(  
            Long webResourceId,
            int httpStatusCode,
            int start,
            int chunkSize);
    
    /**
     * 
     * @param webResourceId
     * @param start
     * @param chunkSize
     * @return 
     */
    Collection<Long> getRelatedContentIdsFromWebResource( 
            Long webResourceId,
            int start,
            int chunkSize);
    
    /**
     * 
     * @param webResourceId
     * @param startValue
     * @param windowSize
     * @param acceptContentWithNullDom
     * @return 
     */
    Collection<Content> getSSPFromWebResource(
            Long webResourceId, 
            Long startValue, 
            int windowSize,
            boolean acceptContentWithNullDom);
    
    /**
     * 
     * @param webResourceId
     * @param startValue
     * @param windowSize
     * @param acceptContentWithNullDom
     * @return 
     */
    Collection<Content> getSSPWithRelatedContentFromWebResource(
            Long webResourceId, 
            Long startValue, 
            int windowSize,
            boolean acceptContentWithNullDom);

    /**
     * 
     * @param id
     * @param isFetchParameters
     * @return
     */
    Content readWithRelatedContent(Long id,  boolean isFetchParameters);

    /**
     * 
     * @param audit
     * @return
     */
    Collection<StylesheetContent> getExternalStylesheetFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Collection<RelatedContent> getRelatedContentFromAudit(Audit audit);

    /**
     * 
     * @param relatedContentId
     */
    void deleteContentRelationShip(Long relatedContentId);
    
    /**
     * 
     * @param content 
     */
    void deleteRelatedContentFromContent(Content content);
    
    /**
     *
     * @param uri
     * @return
     */
    SSP getSSP(String uri);

    /**
     *
     * @param uri
     * @param page
     * @return
     */
    SSP getSSP(String uri,Page page);

    /**
     *
     * @param dateOfLoading
     * @param uri
     * @return
     */
    SSP getSSP(Date dateOfLoading, String uri);

    /**
     *
     * @param dateOfLoading
     * @param uri
     * @param sourceCode
     * @param page
     * @return
     */
    SSP getSSP(Date dateOfLoading, String uri, String sourceCode, Page page);
    
    /**
     *
     * @param dateOfLoading
     * @param uri
     * @param httpStatusCode
     * @return
     */
    SSP getSSP(Date dateOfLoading, String uri, int httpStatusCode);

    /**
     *
     * @param dateOfLoading
     * @param uri
     * @param sourceCode
     * @param page
     * @param httpStatusCode
     * @return
     */
    SSP getSSP(Date dateOfLoading, String uri, String sourceCode, Page page, int httpStatusCode);
    
    /**
     *
     * @param dateOfLoading
     * @param uri
     * @param sourceCode
     * @param audit
     * @param page
     * @param httpStatusCode
     * @return
     */
    SSP getSSP(Date dateOfLoading, String uri, String sourceCode, Audit audit, Page page, int httpStatusCode);

    /**
     *
     * @param uri
     * @param ssp
     * @return
     */
    StylesheetContent getStylesheetContent(String uri, SSP ssp);

    /**
     *
     * @param dateOfLoading
     * @param uri
     * @param ssp
     * @param sourceCode
     * @return
     */
    StylesheetContent getStylesheetContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode);

    /**
     *
     * @param dateOfLoading
     * @param uri
     * @param ssp
     * @param sourceCode
     * @param httpStatusCode
     * @return
     */
    StylesheetContent getStylesheetContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode, int httpStatusCode);

    /**
     *
     * @param dateOfLoading
     * @param uri
     * @param ssp
     * @param sourceCode
     * @return
     */
    JavascriptContent getJavascriptContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode);

    /**
     *
     * @param dateOfLoading
     * @param uri
     * @param ssp
     * @param sourceCode
     * @param httpStatusCode
     * @return
     */
    JavascriptContent getJavascriptContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode, int httpStatusCode);

    /**
     *
     * @param dateOfLoading
     * @param uri
     * @param ssp
     * @param binaryContent
     * @return
     */
    ImageContent getImageContent(Date dateOfLoading, String uri, SSP ssp, byte[] binaryContent);

    /**
     *
     * @param uri
     * @param ssp
     * @return
     */
    ImageContent getImageContent(String uri, SSP ssp);

    /**
     *
     * @param dateOfLoading
     * @param uri
     * @param ssp
     * @param binaryContent
     * @param httpStatusCode
     * @return
     */
    ImageContent getImageContent(Date dateOfLoading, String uri, SSP ssp, byte[] binaryContent, int httpStatusCode);

    /**
     * 
     * @param uri
     * @param ssp
     * @return
     */
    RelatedContent getRelatedContent(String uri, SSP ssp);

}