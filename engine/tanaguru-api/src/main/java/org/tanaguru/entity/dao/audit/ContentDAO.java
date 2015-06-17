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
package org.tanaguru.entity.dao.audit;

import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.Content;
import org.tanaguru.sdk.entity.dao.GenericDAO;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.tanaguru.entity.audit.RelatedContent;
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.entity.audit.StylesheetContent;
import org.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author jkowalczyk
 */
public interface ContentDAO extends GenericDAO<Content, Long> {

    /**
     *
     * @param audit
     *            the audit to find from
     * @param uri
     *            the URI to find
     * @return the content
     */
    Content find(Audit audit, String uri);

    /**
     *
     * @param webresource
     *            the webresource to find from
     * @param uri
     *            the URI to find
     * @return the content
     */
    Content find(WebResource webresource, String uri);

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
     * @param start
     * @param chunkSize
     * @return
     */
    List<Content> findOrphanContentList(
            WebResource webResource,
            int start,
            int chunkSize);

    /**
     * 
     * @param webResource
     * @return
     */
    Long findNumberOfOrphanContentFromWebResource(WebResource webResource);

    /**
     * 
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    List<Content> findOrphanRelatedContentList(
            WebResource webResource,
            int start,
            int chunkSize);

    /**
     *
     * @param webResource
     * @return
     */
    Long findNumberOfOrphanRelatedContentFromWebResource(
            WebResource webResource);

    /**
     * 
     * @param webResource
     * @return
     */
    Long findNumberOfSSPFromWebResource(WebResource webResource, int httpStatusCode);

    /**
     * 
     * @param webResource
     * @return
     */
    Long findNumberOfRelatedContentFromWebResource(WebResource webResource);

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
    List<Long> getSSPFromWebResource(
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
    List<Long> getRelatedContentFromWebResource(
            Long webResourceId,
            int start,
            int chunkSize);

    /**
     * 
     * @param id
     * @param isFetchParameters
     * @return
     */
    Content readWithRelatedContent(Long id, boolean isFetchParameters);

    /**
     * 
     * @param audit
     * @return
     */
    Collection<StylesheetContent> findExternalStylesheetFromAudit(Audit audit);

    /**
     * 
     * @param audit
     * @return
     */
    Collection<RelatedContent> findRelatedContentFromAudit(Audit audit);

    /**
     * 
     * @param relatedContentId
     */
    void deleteContentRelationShip(Long relatedContentId);
    
    /**
     * 
     * @param content
     */
    void deleteRelatedContentFromContent(Content content) ;

}