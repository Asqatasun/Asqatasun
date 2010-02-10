package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.JavascriptContent;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.SSPImpl;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.entity.subject.Page;
import java.util.Date;
import org.opens.tanaguru.entity.audit.JavascriptContentImpl;
import org.opens.tanaguru.entity.audit.StylesheetContentImpl;

/**
 * 
 * @author ADEX
 */
public class ContentFactoryImpl implements ContentFactory {

    public ContentFactoryImpl() {
        super();
    }

    public Content create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public SSP createSSP(Date dateOfLoading, String uri) {
        return new SSPImpl(dateOfLoading, uri);
    }

    public SSP createSSP(Date dateOfLoading, String uri, String sourceCode,
            Page page) {
        return new SSPImpl(dateOfLoading, uri, sourceCode, page);
    }

    public StylesheetContent createStylesheetContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode) {
        return new StylesheetContentImpl(dateOfLoading, uri, ssp, sourceCode);
    }

    public JavascriptContent createJavascriptContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode) {
        return new JavascriptContentImpl(dateOfLoading, uri, ssp, sourceCode);
    }
}
