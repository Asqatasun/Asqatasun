/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2011  Open-S Company
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.util.http;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.log4j.Logger;

/**
 *
 * @author jkowalczyk
 */
public class HttpRequestHandler {
    
    private static final String TANAGURU_USER_AGENT = "tanaguru";
    private static final Logger LOGGER  = Logger.getLogger(HttpRequestHandler.class);

    /**
     * The holder that handles the unique instance of LanguageDetector
     */
    private static class HttpRequestHandlerHolder {
        public static HttpRequestHandler INSTANCE = new HttpRequestHandler();
    }
    
    private String proxyPort;
    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }
    
    private String proxyHost;
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }
    
    /**
     * Multiple Url can be set through a unique String separated by ;
     */
    private List<String> proxyExclusionUrlList = new ArrayList<String>();
    public List<String> getProxyExclusionUrlList() {
        return proxyExclusionUrlList;
    }

    public void setProxyExclusionUrl(String proxyExclusionUrl) {
        proxyExclusionUrlList.addAll(Arrays.asList(proxyExclusionUrl.split(";")));
    }

    private int connectionTimeout = 20000;
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
    
    private int socketTimeout = 20000;
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }
    
    /**
     * Private constructor, singleton pattern
     */
    private HttpRequestHandler() {
        if (HttpRequestHandlerHolder.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }
    
    public static synchronized HttpRequestHandler getInstance() {
        return HttpRequestHandlerHolder.INSTANCE;
    }

    public boolean isUrlAccessible (String url) {
        int statusFromHead = computeStatus(getHttpStatus(url));
        switch (statusFromHead) {
            case 1 : 
                return true;
            case 0 : 
                int statusFromGet = computeStatus(getHttpStatusFromGet(url));
                switch (statusFromGet) {
                    case 0 : 
                        return false;
                    case 1 : 
                        return true;
                }
        }
        return false;
    }
    
    public int getHttpStatus (String url) {
        String encodedUrl = getEncodedUrl(url);
        HttpClient httpClient = getHttpClient(encodedUrl);
        HeadMethod head = new HeadMethod(encodedUrl);
        try {
            LOGGER.debug("executing head request to retrieve page status on " + head.getURI());
            int status = httpClient.executeMethod(head);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("received " + status + " from head request");
                for (Header h : head.getResponseHeaders()) {
                    LOGGER.debug("header : " + h.toExternalForm());
                }
            }
            
            return status;
        } catch (UnknownHostException uhe) {
            LOGGER.warn("UnknownHostException on " + encodedUrl);
            return HttpStatus.SC_NOT_FOUND;
        } catch (IllegalArgumentException iae) {
            LOGGER.warn("IllegalArgumentException on " + encodedUrl);
            return HttpStatus.SC_NOT_FOUND;
        } catch (IOException ioe) {
            LOGGER.warn("IOException on " + encodedUrl);
            ioe.fillInStackTrace();
            return HttpStatus.SC_NOT_FOUND;
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            head.releaseConnection();
        }
    }
    
    public String getHttpContent (String url) throws URISyntaxException, UnknownHostException, IOException, IllegalCharsetNameException {
        if (StringUtils.isEmpty(url)){
            return "";
        }
        
        String encodedUrl = getEncodedUrl(url);
        HttpClient httpClient = getHttpClient(encodedUrl);
        GetMethod get = new GetMethod(encodedUrl);
        try {
            LOGGER.debug("executing request to retrieve content on " + get.getURI());
            int status = httpClient.executeMethod(get);
            LOGGER.debug("received " + status + " from get request");
            if (status == HttpStatus.SC_OK) {
                LOGGER.debug("status == HttpStatus.SC_OK " );
                
                byte[] responseBody = get.getResponseBody();
                return new String(responseBody);
            } else {
                LOGGER.debug("status != HttpStatus.SC_OK " );
                return "";
            }
            
        } catch (NullPointerException ioe) {
            LOGGER.debug("NullPointerException");
            return "";
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            get.releaseConnection();
            LOGGER.debug("finally");
        }
    }
    
    public int getHttpStatusFromGet (String url) {
        String encodedUrl = getEncodedUrl(url);
        HttpClient httpClient = getHttpClient(encodedUrl);
        GetMethod get = new GetMethod(encodedUrl);
        try {
            LOGGER.debug("executing get request to retrieve status on " + get.getURI());
            int status = httpClient.executeMethod(get);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("received " + status + " from get request");
                for (Header h : get.getResponseHeaders()) {
                    LOGGER.debug("header : " + h.toExternalForm());
                }
            }
            return status;
        } catch (UnknownHostException uhe) {
            LOGGER.warn("UnknownHostException on " + encodedUrl);
            return HttpStatus.SC_NOT_FOUND;
        } catch (IOException ioe) {
            LOGGER.warn("IOException on " + encodedUrl);
            return HttpStatus.SC_NOT_FOUND;
        }finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            get.releaseConnection();
        }
    }
    
    private HttpClient getHttpClient(String url) {
        HttpClient httpclient = new HttpClient();
        boolean isExcludedUrl=false;
        for (String excludedUrl : proxyExclusionUrlList) {
            if (url.contains(excludedUrl)) {
                isExcludedUrl=true;
            }
        }
        if (StringUtils.isNotEmpty(proxyPort) && StringUtils.isNotEmpty(proxyPort) && !isExcludedUrl) {
            HttpHost proxy = new HttpHost(proxyHost, Integer.valueOf(proxyPort));
            httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        }
        setTimeouts(httpclient.getParams());
        httpclient.getParams().setParameter(
                CoreProtocolPNames.USER_AGENT, 
                TANAGURU_USER_AGENT);
        return httpclient;
    }
    
    private void setTimeouts(HttpClientParams params) {
        params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 
            connectionTimeout);
        params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, socketTimeout);
    }

    private int computeStatus(int status) {
        switch (status) { 
            case HttpStatus.SC_FORBIDDEN:
            case HttpStatus.SC_METHOD_NOT_ALLOWED:
            case HttpStatus.SC_BAD_REQUEST:
            case HttpStatus.SC_UNAUTHORIZED:
            case HttpStatus.SC_PAYMENT_REQUIRED:
            case HttpStatus.SC_NOT_FOUND:
            case HttpStatus.SC_NOT_ACCEPTABLE:
            case HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED:
            case HttpStatus.SC_REQUEST_TIMEOUT:
            case HttpStatus.SC_CONFLICT:
            case HttpStatus.SC_GONE:
            case HttpStatus.SC_LENGTH_REQUIRED:
            case HttpStatus.SC_PRECONDITION_FAILED:
            case HttpStatus.SC_REQUEST_TOO_LONG:
            case HttpStatus.SC_REQUEST_URI_TOO_LONG:
            case HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE:
            case HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE:
            case HttpStatus.SC_EXPECTATION_FAILED:
            case HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE:
            case HttpStatus.SC_METHOD_FAILURE:
            case HttpStatus.SC_UNPROCESSABLE_ENTITY:
            case HttpStatus.SC_LOCKED:
            case HttpStatus.SC_FAILED_DEPENDENCY:
            case HttpStatus.SC_INTERNAL_SERVER_ERROR:
            case HttpStatus.SC_NOT_IMPLEMENTED:
            case HttpStatus.SC_BAD_GATEWAY:
            case HttpStatus.SC_SERVICE_UNAVAILABLE:
            case HttpStatus.SC_GATEWAY_TIMEOUT:
            case HttpStatus.SC_HTTP_VERSION_NOT_SUPPORTED:
            case HttpStatus.SC_INSUFFICIENT_STORAGE:
                return 0;
            case HttpStatus.SC_CONTINUE:
            case HttpStatus.SC_SWITCHING_PROTOCOLS:
            case HttpStatus.SC_PROCESSING:
            case HttpStatus.SC_OK:
            case HttpStatus.SC_CREATED:
            case HttpStatus.SC_ACCEPTED:
            case HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION:
            case HttpStatus.SC_NO_CONTENT:
            case HttpStatus.SC_RESET_CONTENT:
            case HttpStatus.SC_PARTIAL_CONTENT:
            case HttpStatus.SC_MULTI_STATUS:
            case HttpStatus.SC_MULTIPLE_CHOICES:
            case HttpStatus.SC_MOVED_PERMANENTLY:
            case HttpStatus.SC_MOVED_TEMPORARILY:
            case HttpStatus.SC_SEE_OTHER:
            case HttpStatus.SC_NOT_MODIFIED:
            case HttpStatus.SC_USE_PROXY:
            case HttpStatus.SC_TEMPORARY_REDIRECT:
                return 1;
            default : 
                return 1;
        }
    }
    
    private String getEncodedUrl(String url) {
        try {
            return URIUtil.encodeQuery(URIUtil.decode(url));
        } catch (URIException ue) {
            LOGGER.warn("URIException on " + url);
            return url;
        }
    }

}