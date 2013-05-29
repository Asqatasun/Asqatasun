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
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;

/**
 *
 * @author jkowalczyk
 */
public class HttpRequestHandler {
    
    private static final String UTF8_ENCODING_KEY = "UTF-8";
    private static HttpRequestHandler httpRequestHandler; 
    
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

    private int connectionTimeout = 3000;
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
    
    private int socketTimeout = 3000;
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }
    
    private HttpRequestHandler() {}
    
    public static synchronized HttpRequestHandler getInstance() {
        if (httpRequestHandler == null) {
            httpRequestHandler = new HttpRequestHandler();
        }
        return httpRequestHandler;
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
        DefaultHttpClient httpclient = getHttpClient(encodedUrl);
        try {
            HttpHead httphead = new HttpHead(encodedUrl);
            Logger.getLogger(this.getClass()).info("executing head request to retrieve page status on " + httphead.getURI());
            // Create a response handler
            HttpResponse responseBody = httpclient.execute(httphead);
            Logger.getLogger(this.getClass()).warn("received " + responseBody.getStatusLine().getStatusCode() + "from head request");
            return Integer.valueOf(responseBody.getStatusLine().getStatusCode());
        } catch (UnknownHostException uhe) {
            Logger.getLogger(this.getClass()).warn("UnknownHostException on " + encodedUrl);
            return HttpStatus.SC_NOT_FOUND;
        } catch (IOException ioe) {
            Logger.getLogger(this.getClass()).warn("IOException on " + encodedUrl);
            ioe.fillInStackTrace();
            return HttpStatus.SC_NOT_FOUND;
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }
    
    public String getHttpContent (String url) throws URISyntaxException, UnknownHostException, IOException, IllegalCharsetNameException {
        if (StringUtils.isEmpty(url)){
            return "";
        }
        
        String encodedUrl = getEncodedUrl(url);
        DefaultHttpClient httpclient = getHttpClient(encodedUrl);
        try {
            HttpGet httpget = new HttpGet(encodedUrl);
            Logger.getLogger(this.getClass()).debug("executing request to retrieve content on " + httpget.getURI());
            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpget, responseHandler);
            return responseBody.toString();
        } catch (NullPointerException ioe) {
            return "";
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }
    
    public int getHttpStatusFromGet (String url) {
        String encodedUrl = getEncodedUrl(url);
        DefaultHttpClient httpclient = getHttpClient(encodedUrl);
        try {
            HttpGet httpget = new HttpGet(encodedUrl);
            Logger.getLogger(this.getClass()).debug("executing get request to retrieve status on " + httpget.getURI());
            HttpResponse response = httpclient.execute(httpget);
            Logger.getLogger(this.getClass()).debug("received " + response.getStatusLine().getStatusCode() + "from get request");
            return response.getStatusLine().getStatusCode();
        } catch (UnknownHostException uhe) {
            return HttpStatus.SC_NOT_FOUND;
        } catch (IOException ioe) {
            return HttpStatus.SC_NOT_FOUND;
        }finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }
    
    private DefaultHttpClient getHttpClient(String url) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
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
        return httpclient;
    }
    
    private void setTimeouts(HttpParams params) {
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
            return URIUtil.encodeQuery(url) ;
        } catch (URIException ue) {
            Logger.getLogger(this.getClass()).warn("URIException on " + url);
            return url;
        }
    }

}