/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.crawler.processor;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.archive.io.RecordingInputStream;
import org.archive.modules.CrawlURI;
import org.archive.modules.Processor;
import org.archive.net.UURI;
import org.tanaguru.crawler.ContentWriter;
import org.tanaguru.crawler.extractor.listener.ExtractorCSSListener;
import org.tanaguru.crawler.extractor.listener.ExtractorHTMLListener;

/**
 * Processor module that convert the results of successful fetches to
 * tanaguru-like Web-Resources and Contents.
 *
 * @author jkowalczyk
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

    private Collection<String> authorizedMimeTypes = Collections.EMPTY_LIST;
    public Collection<String> getAuthorizedMimeTypes() {
        return authorizedMimeTypes;
    }
    
    public void setAuthorizedMimeTypes(Collection<String> authorizedMimeTypes) {
        this.authorizedMimeTypes = authorizedMimeTypes;
    }
    
    /**
     * Default constructor
     */
    public TanaguruWriterProcessor() {
        super();
    }

    @Override
    protected boolean shouldProcess(CrawlURI curi) {
        boolean isSuccess = isSuccess(curi);
        Logger.getLogger(this.getClass()).debug("should process? " + curi.getURI() + " with mime type " + curi.getContentType() + " " + isSuccess);
        return isSuccess;
    }
    
    @Override
    protected void innerProcess(CrawlURI curi) {
        Logger.getLogger(this.getClass()).debug("inner process? " + curi.getURI() );
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
        IOUtils.closeQuietly(recis);
    }

    /**
     * 
     * @param curi
     */
    @Override
    public synchronized void computeResource(CrawlURI curi) {
        Logger.getLogger(this.getClass()).debug("compute resource? " + curi.getURI());
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