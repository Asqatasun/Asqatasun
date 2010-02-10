package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.subject.Page;
import com.adex.sdk.entity.factory.GenericFactory;
import java.util.Date;
import org.opens.tanaguru.entity.audit.JavascriptContent;
import org.opens.tanaguru.entity.audit.StylesheetContent;

/**
 * 
 * @author ADEX
 */
public interface ContentFactory extends GenericFactory<Content> {

    SSP createSSP(Date dateOfLoading, String uri);

    SSP createSSP(Date dateOfLoading, String uri, String sourceCode, Page page);

    StylesheetContent createStylesheetContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode);

    JavascriptContent createJavascriptContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode);
}
