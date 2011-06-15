package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import com.adex.sdk.entity.dao.GenericDAO;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 * @version 1.0.0
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
    Long findNumberOfSSPFromWebResource(WebResource webResource);

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
     * @return
     */
    Content readWithRelatedContent(Long id);

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
}
