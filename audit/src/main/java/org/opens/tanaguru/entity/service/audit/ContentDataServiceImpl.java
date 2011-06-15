package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.JavascriptContent;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.entity.dao.audit.ContentDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public class ContentDataServiceImpl extends AbstractGenericDataService<Content, Long>
        implements ContentDataService {

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
    public Long getNumberOfSSPFromWebResource(WebResource webResource) {
        return ((ContentDAO) entityDao).findNumberOfSSPFromWebResource(webResource);
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
    public List<Long> getSSPFromWebResource(
            Long webResourceId,
            int start,
            int chunkSize) {
        return ((ContentDAO) entityDao).getSSPFromWebResource(
                webResourceId,
                start,
                chunkSize);
    }

    @Override
    public List<Long> getRelatedContentFromWebResource(
            Long webResourceId,
            int start,
            int chunkSize) {
        return ((ContentDAO) entityDao).getRelatedContentFromWebResource(
                webResourceId,
                start,
                chunkSize);
    }

    @Override
    public Content readWithRelatedContent(Long id) {
        return ((ContentDAO) entityDao).readWithRelatedContent(id);
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
}