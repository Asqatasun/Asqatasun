package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.JavascriptContent;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import com.adex.sdk.entity.service.GenericDataService;
import java.util.List;
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
     * @param start
     * @param chunkSize
     * @return
     */
    public List<? extends Content> findSSPContentWithRelatedContent(Audit audit, int start, int chunkSize);

    /**
     *
     * @param audit
     * @return
     */
    public Long findNumberOfSSPContentFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    public boolean hasContent(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    public boolean hasAdaptedSSP(Audit audit);

    /**
     * 
     * @param webResource
     * @param uri
     * @return
     */
    public RelatedContent getRelatedContentFromUriWithParentContent(
            WebResource webResource,
            String uri);

    /**
     *
     * @param webResource
     * @return
     */
    public Long getNumberOfOrphanContent(WebResource webResource);

    /**
     *
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    public List<Content> getOrphanContentList(
            WebResource webResource,
            int start,
            int chunkSize);

    /**
     *
     * @param webResource
     * @return
     */
    public Long getNumberOfOrphanRelatedContent(WebResource webResource);

    /**
     *
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    public List<Content> getOrphanRelatedContentList(
            WebResource webResource,
            int start,
            int chunkSize);

    /**
     *
     * @param webResource
     * @param uri
     * @return
     */
    public RelatedContent getRelatedContent(WebResource webResource, String uri);

    /**
     *
     * @param webResource
     * @return
     */
    public Long getNumberOfSSPFromWebResource(WebResource webResource);

    /**
     *
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    public List<Content> getContentWithRelatedContentFromWebResource(
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
    public List<? extends SSP> getSSPList(WebResource webResource,int start,int chunkSize);

    /**
     *
     * @param webResource
     * @return
     */
    public Long getNumberOfRelatedContentFromWebResource(WebResource webResource);

    /**
     *
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    public List<? extends RelatedContent> getRelatedContentList(
            WebResource webResource,
            int start,
            int chunkSize);
}
