package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import com.adex.sdk.entity.dao.GenericDAO;
import java.util.List;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.SSP;
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
     * @param start
     * @param chunkSize
     * @return
     */
    List<? extends Content> findSSPContentWithRelatedContent(
            Audit audit,
            int start,
            int chunkSize);

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
    boolean hasContent(Audit audit);

    /**
     * 
     * @param audit
     * @return
     */
    boolean hasAdaptedSSP(Audit audit);

    /**
     * 
     * @param webResource
     * @param uri
     * @return
     */
    RelatedContent findRelatedContentFromUriWithParentContent(
            WebResource webResource,
            String uri);

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
     * @param uri
     * @return
     */
    RelatedContent findRelatedContent(WebResource webResource, String uri);

    /**
     * 
     * @param webResource
     * @return
     */
    Long findNumberOfSSPFromWebResource(WebResource webResource);

    /**
     * 
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    List<Content> findContentWithRelatedContentFromWebResource(
            WebResource webResource,
            int start,
            int chunkSize);

    /**
     * 
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    List<? extends SSP> findSSPList(
            WebResource webResource,
            int start,
            int chunkSize);

    /**
     * 
     * @param webResource
     * @return
     */
    Long findNumberOfRelatedContentFromWebResource(WebResource webResource);

    /**
     * 
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    List<? extends RelatedContent> findRelatedContentList(
            WebResource webResource,
            int start,
            int chunkSize);

    /**
     * 
     */
    void saveContentRelationShip(SSP ssp);
}
