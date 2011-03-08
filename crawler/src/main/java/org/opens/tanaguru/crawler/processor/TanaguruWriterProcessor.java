package org.opens.tanaguru.crawler.processor;

import java.io.IOException;
import java.util.regex.Pattern;
import org.archive.io.RecordingInputStream;
import org.archive.modules.CrawlURI;
import org.archive.modules.Processor;
import org.archive.net.UURI;
import org.opens.tanaguru.crawler.ContentWriter;
import org.opens.tanaguru.crawler.extractor.listener.ExtractorCSSListener;
import org.opens.tanaguru.crawler.extractor.listener.ExtractorHTMLListener;

/**
Processor module that convert the results of successful fetches to
tanaguru-like Web-Resources and Contents.
@author jkowalczyk
 */
public class TanaguruWriterProcessor extends Processor
        implements ExtractorHTMLListener, ExtractorCSSListener {

    private static final long serialVersionUID = -4411002064139498785L;
    private static final String HTTP_PREFIX = "http";
    private static final String HTTPS_PREFIX = "https";
    private static final int HTTP_SUCCESS_RETURN_CODE = 200;

    private ExtractorCSSListener extractorCSSListener;
    public ExtractorCSSListener getExtractorCSSListener() {
        return extractorCSSListener;
    }

    public void setExtractorCSSListener(ExtractorCSSListener extractorCSSListener) {
        this.extractorCSSListener = extractorCSSListener;
    }

    private ExtractorHTMLListener extractorHTMLListener;
    public ExtractorHTMLListener getExtractorHTMLListener() {
        return extractorHTMLListener;
    }

    public void setExtractorHTMLListener(ExtractorHTMLListener extractorListener) {
        this.extractorHTMLListener = extractorListener;
    }

    private ContentWriter contentWriter;
    public ContentWriter getContentWriter() {
        return contentWriter;
    }

    public void setContentWriter(ContentWriter contentWriter) {
        this.contentWriter = contentWriter;
    }

    private Pattern cssFilePattern;

    /**
     *
     * @return the css File Pattern
     */
    public Pattern getCssFilePattern() {
        if (cssFilePattern == null) {
            cssFilePattern = Pattern.compile(cssRegexp);
        }
        return cssFilePattern;
    }
    private String cssRegexp;

    /**
     * Set the css Regexp
     * @param cssRegexp
     */
    public void setCssRegexp(String cssRegexp) {
        this.cssRegexp = cssRegexp;
    }
    private Pattern htmlFilePattern;

    /**
     * 
     * @return the html File Pattern
     */
    public Pattern getHtmlFilePattern() {
        if (htmlFilePattern == null) {
            htmlFilePattern = Pattern.compile(htmlRegexp);
        }
        return htmlFilePattern;
    }
    private String htmlRegexp;

    /**
     * the regular expression used to discover an html content based-on its
     * extension
     * @param htmlRegexp
     */
    public void setHtmlRegexp(String htmlRegexp) {
        this.htmlRegexp = htmlRegexp;
    }

    /**
     * @param name Name of this processor.
     */
    public TanaguruWriterProcessor() {
    }

    @Override
    protected boolean shouldProcess(CrawlURI curi) {
        return isSuccess(curi);
    }

    @Override
    protected void innerProcess(CrawlURI curi) {
        UURI uuri = curi.getUURI(); // Current URI.

        // Only http and https schemes are supported.
        String scheme = uuri.getScheme();
        if (!HTTP_PREFIX.equalsIgnoreCase(scheme)
                && !HTTPS_PREFIX.equalsIgnoreCase(scheme)) {
            return;
        }
        RecordingInputStream recis = curi.getRecorder().getRecordedInput();
        if (0L == recis.getResponseContentLength()) {
            return;
        }

        if (curi.getFetchStatus() != HTTP_SUCCESS_RETURN_CODE) {
            contentWriter.computeAndPersistUnsuccessfullFetchedResource(curi);
            return;
        }

        try {
            contentWriter.computeAndPersistSuccessfullFetchedResource(curi, recis);
        } catch (IOException e) {
            curi.getNonFatalFailures().add(e);
        }
    }

    /**
     * 
     * @param curi
     */
    @Override
    public synchronized void computeResource(CrawlURI curi) {
        extractorHTMLListener.computeResource(curi);
    }

    /**
     * In case of css extractred from another css, we combine the child
     * content with its css parents. Each CSS resource has to be associated with
     * a HTML content. By keeping this relation, we can combine a child CSS
     * with the HTML contents combined with its parents.
     * @param curi
     */
    @Override
    public synchronized void computeCSSResource(CrawlURI curi) {
        this.extractorCSSListener.computeCSSResource(curi);
    }

}
