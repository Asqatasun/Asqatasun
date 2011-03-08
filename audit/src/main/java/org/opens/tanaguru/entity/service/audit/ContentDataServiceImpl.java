package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.JavascriptContent;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.entity.dao.audit.ContentDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;
import java.util.List;
import org.opens.tanaguru.entity.audit.SSPImpl;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public class ContentDataServiceImpl extends AbstractGenericDataService<Content, Long> implements ContentDataService {

    public ContentDataServiceImpl() {
        super();
    }

    @Override
    public JavascriptContent findJavascriptContent(Audit audit, String uri) {
        return (JavascriptContent) ((ContentDAO) entityDao).find(audit, uri);
    }

    @Override
    public SSP findSSP(Audit audit, String uri) {
        return (SSP) ((ContentDAO) entityDao).find(audit, uri);
    }

    @Override
    public SSP findSSP(WebResource webresource, String uri) {
        return (SSP) ((ContentDAO) entityDao).find(webresource, uri);
    }

    @Override
    public StylesheetContent findStylesheetContent(Audit audit, String uri) {
        return (StylesheetContent) ((ContentDAO) entityDao).find(audit, uri);
    }

    @Override
    public List<? extends Content> findSSPContentWithRelatedContent(
            Audit audit,
            int start,
            int chunkSize){
        return (List<SSPImpl>)
                ((ContentDAO) entityDao).findSSPContentWithRelatedContent(
                    audit,
                    start,
                    chunkSize);
    }

    @Override
    public Long findNumberOfSSPContentFromAudit(Audit audit){
        return ((ContentDAO) entityDao).findNumberOfSSPContentFromAudit(audit);
    }

    /**
     *
     * @param audit
     * @return
     */
    public boolean hasContent(Audit audit) {
        return ((ContentDAO) entityDao).hasContent(audit);
    }

    /**
     *
     * @param audit
     * @return
     */
    public boolean hasAdaptedSSP(Audit audit) {
        return ((ContentDAO) entityDao).hasAdaptedSSP(audit);
    }

    @Override
    public RelatedContent getRelatedContentFromUriWithParentContent(
            WebResource webResource,
            String uri) {
        return ((ContentDAO) entityDao).
                findRelatedContentFromUriWithParentContent(webResource, uri);
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
    public RelatedContent getRelatedContent(WebResource webResource, String uri) {
        return ((ContentDAO) entityDao).findRelatedContent(webResource, uri);
    }

    @Override
    public Long getNumberOfSSPFromWebResource(WebResource webResource) {
        return ((ContentDAO) entityDao).findNumberOfSSPFromWebResource(webResource);
    }

    @Override
    public List<Content> getContentWithRelatedContentFromWebResource(
            WebResource webResource,
            int start,
            int chunkSize) {
        return ((ContentDAO) entityDao).
                findContentWithRelatedContentFromWebResource(
                    webResource,
                    start,
                    chunkSize);
    }

    @Override
    public List<? extends SSP> getSSPList(WebResource webResource, int start, int chunkSize) {
        return ((ContentDAO) entityDao).findSSPList(webResource, start, chunkSize);
    }

    @Override
    public Long getNumberOfRelatedContentFromWebResource(WebResource webResource) {
        return ((ContentDAO) entityDao).
                findNumberOfRelatedContentFromWebResource(webResource);
    }

    @Override
    public List<? extends RelatedContent> getRelatedContentList(WebResource webResource, int start, int chunkSize) {
        return ((ContentDAO) entityDao).findRelatedContentList(
                webResource,
                start,
                chunkSize);
    }

}
