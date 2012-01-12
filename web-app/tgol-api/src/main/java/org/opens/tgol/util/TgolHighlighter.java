/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tgol.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author jkowalczyk
 */
public final class TgolHighlighter {

    private static final Logger LOGGER = Logger.getLogger(TgolHighlighter.class);
    private static final String BEGIN_LINE_STR_OF_HIGHLIGHTED_CODE =
            "<li class=\"li";
    private static final char CARRIAGE_RETURN_CHAR = '\n';
    private static final char EMPTY_CHAR = ' ';
    private static final String POST_REQUEST_CONTENT_TYPE_PARAMETER =
            "Content-Type";
    private static final String POST_REQUEST_CONTENT_TYPE_VALUE =
            "application/x-www-form-urlencoded;charset=UTF-8";
    private static final String HIGHLIGHTER_SUBMIT_PARAMETER = "submit";
    private static final String HIGHLIGHTER_SUBMIT_VALUE = "0";
    private static final String HIGHLIGHTER_SOURCE_PARAMETER = "source";
    private static final String HIGHLIGHTER_LANGUAGE_PARAMETER = "language";
    private static final String HIGHLIGHTER_LANGUAGE_VALUE = "html4strict";
    private static final String HIGHLIGHTER_SOCKET_TIMEOUT = "http.socket.timeout";
    private static final String HIGHLIGHTER_CONNECTION_TIMEOUT =
            "http.connection.timeout";
    private static final int TIMEOUT = 3000;

    private String highlighterUrl;
    public String getHighlighterUrl() {
        return highlighterUrl;
    }

    public void setHighlighterUrl(String highlighterUrl) {
        this.highlighterUrl = highlighterUrl;
    }

    private String httpProxyHost;
    public String getProxyHost() {
        return httpProxyHost;
    }

    public void setProxyHost(String httpProxyHost) {
        this.httpProxyHost = httpProxyHost;
    }

    private String httpProxyPort;
    public String getProxyPort() {
        return httpProxyPort;
    }

    public void setProxyPort(String httpProxyPort) {
        this.httpProxyPort = httpProxyPort;
    }

    /**
     * The unique instance of LinkRulesHandler (singleton pattern)
     */
    private static TgolHighlighter tgolHighlighter;
    
    /**
     * Default private constructor
     */
    private TgolHighlighter(){}

    /**
     *
     * @return
     */
    public static synchronized TgolHighlighter getInstance() {
        if (tgolHighlighter == null){
            tgolHighlighter = new TgolHighlighter();
        }
        return tgolHighlighter;
    }

    public String highlightSourceCode
            (String doctype, String adaptedContent) throws IOException {
        
        StringBuilder highlightedSourceCode = new StringBuilder();
        highlightedSourceCode.append(
                highlighterHttpRequest(doctype,adaptedContent));
        return highlightedSourceCode.toString();
    }

        /**
     *
     * @param doctype
     * @param sourceCode
     * @param charset
     * @return
     */
    private String highlighterHttpRequest(
            String doctype,
            String sourceCode) throws IOException{

        StringBuilder source = new StringBuilder();
        if (doctype != null && !doctype.isEmpty()) {
            source.append(doctype.trim().
                    replace(CARRIAGE_RETURN_CHAR,EMPTY_CHAR).
                    replace('\n', EMPTY_CHAR));
            source.append(CARRIAGE_RETURN_CHAR);
//            hasSourceCodeWithDoctype = true;
        }
        source.append(sourceCode);
        HttpClient httpclient = new HttpClient();
        httpclient.getParams().setParameter(
            HIGHLIGHTER_SOCKET_TIMEOUT, TIMEOUT);
        httpclient = setProxy(httpclient);
        httpclient.getParams().setParameter(
            HIGHLIGHTER_CONNECTION_TIMEOUT, TIMEOUT);
        PostMethod post = new PostMethod(highlighterUrl);
        //Geshi needs 3 parameters to process :
        // the "submit" parameter to emulate the form validation
        // the "source" parameter that is the source code to hightlight
        // the "language" parameter that is the highlighter style to apply
        post.addRequestHeader(POST_REQUEST_CONTENT_TYPE_PARAMETER,
                POST_REQUEST_CONTENT_TYPE_VALUE);
        post.addParameter(HIGHLIGHTER_SUBMIT_PARAMETER, HIGHLIGHTER_SUBMIT_VALUE);
        post.addParameter(HIGHLIGHTER_SOURCE_PARAMETER,
                StringEscapeUtils.unescapeXml(source.toString()));
        post.addParameter(HIGHLIGHTER_LANGUAGE_PARAMETER,
                HIGHLIGHTER_LANGUAGE_VALUE);
        StringBuilder highlightedCode = new StringBuilder();
        BufferedReader br = null;
        try {
            httpclient.executeMethod(post);
            br = new BufferedReader(
                    new InputStreamReader(post.getResponseBodyAsStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                highlightedCode.append(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(this.getClass()).
                    warn(ex.getMessage());
//            hasSourceCodeWithDoctype = false;
            return "";
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return addReturnChariotToHighlightedSourceCode(highlightedCode.toString());
    }

    /**
     * The source code returned by the highlighter is serialized. This method
     * adds a Carriage return at the end of each line. To do so, we search the
     * "&lt;li class=\"li" pattern that indicated the begin of a line. We add
     * the carriage return character before each occurrence.
     * @param sourceCode
     * @return
     */
    private String addReturnChariotToHighlightedSourceCode (String sourceCode) {
        return sourceCode.replaceAll(BEGIN_LINE_STR_OF_HIGHLIGHTED_CODE,
                CARRIAGE_RETURN_CHAR+BEGIN_LINE_STR_OF_HIGHLIGHTED_CODE);
    }

    /**
     *
     * @param httpClient
     * @return
     */
    private HttpClient setProxy (HttpClient httpClient) {
        if (StringUtils.isNotEmpty(httpProxyHost) && StringUtils.isNotEmpty(httpProxyPort)) {
            try {
                httpClient.getHostConfiguration().setProxy(httpProxyHost, Integer.valueOf(httpProxyPort));
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Calling highlighter with proxy parameters " + httpProxyHost + " : " + httpProxyPort);
                }
            } catch (NumberFormatException nfe) {
                Logger.getLogger(this.getClass()).warn("Incorrect value of proxy Port : "+ httpProxyPort);
            }
        }
        return httpClient;
    }

}
