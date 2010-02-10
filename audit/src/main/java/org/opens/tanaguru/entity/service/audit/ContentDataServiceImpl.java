package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.JavascriptContent;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.entity.dao.audit.ContentDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public class ContentDataServiceImpl extends AbstractGenericDataService<Content, Long> implements ContentDataService {

    public ContentDataServiceImpl() {
        super();
    }

    public JavascriptContent findJavascriptContent(Audit audit, String uri) {
        return (JavascriptContent) ((ContentDAO) entityDao).find(audit, uri);
    }

    public SSP findSSP(Audit audit, String uri) {
        return (SSP) ((ContentDAO) entityDao).find(audit, uri);
    }

    public StylesheetContent findStylesheetContent(Audit audit, String uri) {
        return (StylesheetContent) ((ContentDAO) entityDao).find(audit, uri);
    }
}
