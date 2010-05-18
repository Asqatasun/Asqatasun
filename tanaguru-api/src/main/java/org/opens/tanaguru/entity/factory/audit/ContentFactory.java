package org.opens.tanaguru.entity.factory.audit;


import com.adex.sdk.entity.factory.GenericFactory;
import java.util.Date;

import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ImageContent;
import org.opens.tanaguru.entity.audit.JavascriptContent;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;

/**
 * 
 * @author ADEX
 */
public interface ContentFactory extends GenericFactory<Content> {

    SSP createSSP(Date dateOfLoading, String uri);

    SSP createSSP(Date dateOfLoading, String uri, String sourceCode, Page page);

    SSP createSSP(Date dateOfLoading, String uri, int httpStatusCode);

    SSP createSSP(Date dateOfLoading, String uri, String sourceCode, Page page, int httpStatusCode);

    StylesheetContent createStylesheetContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode);

    StylesheetContent createStylesheetContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode, int httpStatusCode);

    JavascriptContent createJavascriptContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode);

    JavascriptContent createJavascriptContent(Date dateOfLoading, String uri, SSP ssp, String sourceCode, int httpStatusCode);

    ImageContent createImageContent(Date dateOfLoading, String uri, SSP ssp, byte[] binaryContent);

    ImageContent createImageContent(Date dateOfLoading, String uri, SSP ssp, byte[] binaryContent, int httpStatusCode);
}
