package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.JavascriptContent;
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
    public List<? extends Content> findSSPContentWithRelatedContent(Audit audit, int start, int chunkSize){
        return (List<SSPImpl>)
                ((ContentDAO) entityDao).retrieveSSPContentWithRelatedContent(audit, start, chunkSize);
    }

    @Override
    public Long findNumberOfSSPContentFromAudit(Audit audit){
        return (Long)
                ((ContentDAO) entityDao).retrieveNumberOfSSPContentFromAudit(audit);
    }
}
