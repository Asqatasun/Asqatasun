package org.opens.tanaguru.entity.factory.audit;

import java.util.Date;

import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ImageContent;
import org.opens.tanaguru.entity.audit.ImageContentImpl;
import org.opens.tanaguru.entity.audit.JavascriptContent;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.entity.subject.Page;

import org.opens.tanaguru.entity.audit.JavascriptContentImpl;
import org.opens.tanaguru.entity.audit.RelatedContentImpl;
import org.opens.tanaguru.entity.audit.SSPImpl;
import org.opens.tanaguru.entity.audit.StylesheetContentImpl;

/**
 * 
 * @author ADEX
 */
public class ContentFactoryImpl implements ContentFactory {

    public ContentFactoryImpl() {
        super();
    }

    @Override
    public Content create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SSP createSSP(String uri) {
        return new SSPImpl(uri);
    }

    @Override
    public SSP createSSP(String uri, Page page) {
        return new SSPImpl(uri, page);
    }

    @Override
    public SSP createSSP(Date dateOfLoading, String uri) {
        return new SSPImpl(dateOfLoading, uri);
    }

    @Override
    public SSP createSSP(Date dateOfLoading, String uri, String sourceCode,
            Page page) {
        return new SSPImpl(dateOfLoading, uri, sourceCode, page);
    }

    @Override
    public SSP createSSP(Date dateOfLoading, String uri, int httpStatusCode) {
        return new SSPImpl(dateOfLoading, uri, httpStatusCode);
    }

    @Override
    public SSP createSSP(Date dateOfLoading, String uri, String sourceCode,
            Page page, int httpStatusCode) {
        return new SSPImpl(dateOfLoading, uri, sourceCode, page, httpStatusCode);
    }

    @Override
    public StylesheetContent createStylesheetContent(
            Date dateOfLoading,
            String uri,
            SSP ssp,
            String sourceCode) {
        return new StylesheetContentImpl(dateOfLoading, uri, ssp, sourceCode);
    }

    @Override
    public StylesheetContent createStylesheetContent(String uri,SSP ssp) {
        return new StylesheetContentImpl(uri, ssp);
    }

    @Override
    public JavascriptContent createJavascriptContent(
            Date dateOfLoading,
            String uri,
            SSP ssp,
            String sourceCode) {
        return new JavascriptContentImpl(dateOfLoading, uri, ssp, sourceCode);
    }

    @Override
    public ImageContent createImageContent(
            Date dateOfLoading,
            String uri,
            SSP ssp,
            byte[] binaryContent) {
        return new ImageContentImpl(dateOfLoading, uri, ssp, binaryContent);
    }

    @Override
    public StylesheetContent createStylesheetContent(
            Date dateOfLoading,
            String uri,
            SSP ssp,
            String sourceCode,
            int httpStatusCode) {
        return new StylesheetContentImpl(dateOfLoading, uri, ssp, sourceCode, httpStatusCode);
    }

    @Override
    public JavascriptContent createJavascriptContent(
            Date dateOfLoading,
            String uri,
            SSP ssp,
            String sourceCode,
            int httpStatusCode) {
        return new JavascriptContentImpl(dateOfLoading, uri, ssp, sourceCode, httpStatusCode);
    }

    @Override
    public ImageContent createImageContent(
            Date dateOfLoading,
            String uri,
            SSP ssp,
            byte[] binaryContent,
            int httpStatusCode) {
        return new ImageContentImpl(dateOfLoading, uri, ssp, binaryContent, httpStatusCode);
    }

    @Override
    public ImageContent createImageContent(String uri, SSP ssp) {
        return new ImageContentImpl(uri, ssp);
    }

    @Override
    public RelatedContent createRelatedContent(String uri, SSP ssp) {
        return new RelatedContentImpl(uri, ssp);
    }

}
