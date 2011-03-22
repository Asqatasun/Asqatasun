package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.JavascriptContent;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import com.adex.sdk.entity.service.GenericDataService;
import java.util.List;
import java.util.Set;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 * @version 1.0.0
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
     * @param audit
     *            the audit to use as a reference.
     * @param uri
     *            the uri to find.
     * @return the {@link SSP} instance found
     */
    SSP findSSP(Audit audit, String uri);

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
     *            the audit to use as a reference.
     * @param uri
     *            the uri to find.
     * @return the {@link StylesheetContent} instance found.
     */
    StylesheetContent findStylesheetContent(Audit audit, String uri);

    /**
     *
     * @param audit
     * @return
     */
    Long findNumberOfSSPContentFromAudit(Audit audit); //OK

    /**
     *
     * @param audit
     * @return
     */
    boolean hasContent(Audit audit);   //OK

    /**
     * 
     * @param webResource
     * @param uri
     * @return
     */
    RelatedContent getRelatedContentFromUriWithParentContent(   //OK
            WebResource webResource,
            String uri);

    /**
     *
     * @param webResource
     * @return
     */
    Long getNumberOfOrphanContent(WebResource webResource);  //OK

    /**
     *
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    List<Content> getOrphanContentList(   //OK
            WebResource webResource,
            int start,
            int chunkSize);

    /**
     *
     * @param webResource
     * @return
     */
    Long getNumberOfOrphanRelatedContent(WebResource webResource); //OK

    /**
     *
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    List<Content> getOrphanRelatedContentList( //OK
            WebResource webResource,
            int start,
            int chunkSize);

    /**
     *
     * @param webResource
     * @param uri
     * @return
     */
    RelatedContent getRelatedContent(WebResource webResource, String uri); //OK

    /**
     *
     * @param webResource
     * @return
     */
    Long getNumberOfSSPFromWebResource(WebResource webResource); //OK

    /**
     *
     * @param webResource
     * @return
     */
    Long getNumberOfRelatedContentFromWebResource(WebResource webResource); //OK

    /**
     * 
     * @param ssp
     * @param relatedContentIdSet
     */
    void saveContentRelationShip(SSP ssp, Set<Long> relatedContentIdSet); //OK

    /**
     *
     * @param idContent
     * @param idAudit
     */
    void saveAuditToContent(Long idContent, Long idAudit );  //OK

    /**
     *
     * @param webResourceId
     * @param start
     * @param chunkSize
     * @return
     */
    List<Long> getSSPFromWebResource(  //OK
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
    List<Long> getRelatedContentFromWebResource( //OK
            Long webResourceId,
            int start,
            int chunkSize);

    /**
     *
     * @param id
     * @return
     */
    Content readWithRelatedContent(Long id); //OK

    /**
     * 
     * @param uri
     * @param webResourceParent
     * @return
     */
    boolean checkSSPExist (String uri, WebResource webResourceParent); //OK

    /**
     *
     * @param webResource
     * @param uri
     * @return
     */
    Long getRelatedContentId(WebResource webResource, String uri); //OK

}