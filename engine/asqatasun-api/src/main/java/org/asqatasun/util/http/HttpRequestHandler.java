/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2019  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.asqatasun.util.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.*;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.mozilla.universalchardet.UniversalDetector;

/**
 *
 * @author jkowalczyk
 */
public class HttpRequestHandler {
    
    private static final String ASQATASUN_USER_AGENT = "asqatasun";
    private static final Logger LOGGER  = Logger.getLogger(HttpRequestHandler.class);

    protected static String defaultProtocolCharset = "UTF-8";
    protected static String defaultDocumentCharset = null;
    protected static String defaultDocumentCharsetByLocale = null;
    protected static String defaultDocumentCharsetByPlatform = null;
    protected static final BitSet uric;
    protected static final BitSet allowed_query;
    protected static final BitSet reserved;
    protected static final BitSet alphanum;
    protected static final BitSet mark;
    protected static final BitSet unreserved;
    protected static final BitSet escaped;
    protected static final char[] rootPath;
    protected static final BitSet percent;
    protected static final BitSet digit;
    protected static final BitSet alpha;
    protected static final BitSet hex;

    private String proxyPort;
    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }
    
    private String proxyHost;
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }
    
    private String proxyUser;
    public void setProxyUser(String proxyUser) {
        this.proxyUser = proxyUser;
    }
    
    private String proxyPassword;
    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }
    
    private boolean bypassCheck = false;
    public void setBypassCheck(String bypassCheck) {
        this.bypassCheck = Boolean.valueOf(bypassCheck);
    }
    
    /**
     * Multiple Url can be set through a unique String separated by ;
     */
    private final List<String> proxyExclusionUrlList = new ArrayList<>();
    public List<String> getProxyExclusionUrlList() {
        return proxyExclusionUrlList;
    }

    public void setProxyExclusionUrl(String proxyExclusionUrl) {
        if (StringUtils.isNotBlank(proxyExclusionUrl.trim())) {
            proxyExclusionUrlList.addAll(Arrays.asList(proxyExclusionUrl.split(";")));
        }
    }

    private int connectionTimeout = 3000;
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
    
    private int socketTimeout = 3000;
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }
    
    /**
     * The holder that handles the unique instance of HttpRequestHandler
     */
    private static class HttpRequestHandlerHolder {
        private static final HttpRequestHandler INSTANCE = new HttpRequestHandler();
    }
    
    /**
     * Private constructor
     */
    private HttpRequestHandler() {}
    
    /**
     * Singleton pattern based on the "Initialization-on-demand 
     * holder idiom". See @http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * @return the unique instance of HttpRequestHandler
     */
    public static HttpRequestHandler getInstance() {
        return HttpRequestHandlerHolder.INSTANCE;
    }

    /**
     * 
     * @param url
     * @return whether the given Url is accessible or not
     */
    public boolean isUrlAccessible (String url) {
        if (bypassCheck) {
            LOGGER.debug("check on Url is bypassed by configuration");
            return true;
        }
        try {
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
        } catch (IOException ex) {
            LOGGER.debug(ex.getMessage());
            LOGGER.debug("IOException on " + url);
            return false;
        }
    }
    
    public int getHttpStatus (String url) throws IOException {
        String encodedUrl = getEncodedUrl(url);
        CloseableHttpClient httpClient = getHttpClient(encodedUrl);
        HttpHead head = new HttpHead(encodedUrl);
        try {
            LOGGER.debug("executing head request to retrieve page status on " + head.getURI());
            HttpResponse response = httpClient.execute(head);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("received " + response.getStatusLine().getStatusCode() + " from head request");
                for (Header h : head.getAllHeaders()) {
                    LOGGER.debug("header : " + h.getName() + " " + h.getValue());
                }
            }
            
            return response.getStatusLine().getStatusCode();
        } catch (UnknownHostException uhe) {
            LOGGER.warn("UnknownHostException on " + encodedUrl);
            return HttpStatus.SC_NOT_FOUND;
        } catch (IllegalArgumentException iae) {
            LOGGER.warn("IllegalArgumentException on " + encodedUrl);
            return HttpStatus.SC_NOT_FOUND;
        } catch (IOException ioe) {
            LOGGER.warn("IOException on " + encodedUrl);
            return HttpStatus.SC_NOT_FOUND;
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            head.releaseConnection();
            httpClient.close();
        }
    }
    
    public String getHttpContent (String url) throws URISyntaxException, UnknownHostException, IOException, IllegalCharsetNameException {
        if (StringUtils.isEmpty(url)){
            return "";
        }
        String encodedUrl = getEncodedUrl(url);
        LOGGER.info(encodedUrl);
        CloseableHttpClient httpClient = getHttpClient(encodedUrl);
        HttpGet get = new HttpGet(encodedUrl);
        try {
            LOGGER.info("executing request to retrieve content on " + get.getURI());
            HttpResponse response = httpClient.execute(get);
            LOGGER.info("received " + response.getStatusLine().getStatusCode() + " from get request");
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                LOGGER.info("status == HttpStatus.SC_OK " );
                return EntityUtils.toString(response.getEntity(), Charset.defaultCharset());
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
            httpClient.close();
        }
    }
    
    public int getHttpStatusFromGet (String url) throws IOException {
        String encodedUrl = getEncodedUrl(url);
        CloseableHttpClient httpClient = getHttpClient(encodedUrl);
        HttpGet get = new HttpGet(encodedUrl);
        try {
            LOGGER.debug("executing get request to retrieve status on " + get.getURI());
            HttpResponse status = httpClient.execute(get);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("received " + status + " from get request");
                for (Header h : get.getAllHeaders()) {
                    LOGGER.debug("header : " + h.getName() + " " +h.getValue());
                }
            }
            return status.getStatusLine().getStatusCode();
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
            httpClient.close();
        }
    }
    
    private CloseableHttpClient getHttpClient(String url) {
        RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(socketTimeout)
			.setConnectTimeout(connectionTimeout)
			.build();
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setDefaultRequestConfig(requestConfig);
        httpClientBuilder.setConnectionManager(new PoolingHttpClientConnectionManager());
        httpClientBuilder.setUserAgent(ASQATASUN_USER_AGENT);
        if (isProxySet(url)) {
            LOGGER.debug(("Set proxy with " + proxyHost + " and " + proxyPort));
            httpClientBuilder.setProxy(new HttpHost(proxyHost, Integer.valueOf(proxyPort)));
            if (isProxyCredentialSet()) {
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(
                        new AuthScope(proxyHost, Integer.valueOf(proxyPort)),
                        new UsernamePasswordCredentials(proxyUser, proxyPassword));
                httpClientBuilder.setDefaultCredentialsProvider(credsProvider);
                LOGGER.debug(("Set proxy credentials " + proxyHost + " and " + proxyPort + " and " + proxyUser + " and " + proxyPassword));
            }
        }
        return httpClientBuilder.build();
    }
    
    /**
     * 
     * @param url
     * @return 
     */
    public boolean isProxySet(String url) {
        for (String excludedUrl : proxyExclusionUrlList) {
            if (url.contains(excludedUrl) && StringUtils.isNotBlank(excludedUrl)) {
                LOGGER.debug("Proxy Not Set due to exclusion with : " + excludedUrl);
                return false;
            }
        }
        LOGGER.debug("isProxySet:  " + (StringUtils.isNotEmpty(proxyHost) && StringUtils.isNotEmpty(proxyPort)));
        return StringUtils.isNotEmpty(proxyHost) && StringUtils.isNotEmpty(proxyPort);
    }

    /**
     *
     * @return whether the proxy credential is set
     */
    private boolean isProxyCredentialSet() {
        LOGGER.debug("isProxyCredentialSet" + (StringUtils.isNotEmpty(proxyUser) && StringUtils.isNotEmpty(proxyPassword)));
        return StringUtils.isNotEmpty(proxyUser) && StringUtils.isNotEmpty(proxyPassword);
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
    
    public String getEncodedUrl(String url) {
        try {
            return HttpRequestHandler.encodeQuery(HttpRequestHandler.decode(url));
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("Exception on encoding " + url + " "  + e.getMessage());
            return url;
        } catch (DecoderException e) {
            LOGGER.warn("Exception on encoding " + url + " "  + e.getMessage());
            return url;
        }
    }

    /**
     * This method extracts the charset from the html source code.
     * If the charset is not specified, it is set to UTF-8 by default
     * @param is
     * @return
     */
    public static String extractCharset(InputStream is) throws java.io.IOException {
        byte[] buf = new byte[4096];
        UniversalDetector detector = new UniversalDetector(null);
        int nread;
        while ((nread = is.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        detector.dataEnd();

        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            LOGGER.debug("Detected encoding = " + encoding);
        } else {
            LOGGER.debug("No encoding detected.");
        }

        detector.reset();
        if (encoding != null && isValidCharset(encoding)) {
            return encoding;
        } else {
            return defaultProtocolCharset;
        }
    }

    /**
     * This methods tests if a charset is valid regarding the charset nio API.
     * @param charset
     * @return
     */
    public static boolean isValidCharset(String charset) {
        try {
            Charset.forName(charset);
        } catch (UnsupportedCharsetException e) {
            return false;
        }
        return true;
    }

    /**
     * Escape and encode a string regarded as the query component of an URI with
     * the default protocol charset.
     * When a query string is not misunderstood the reserved special characters
     * ("&amp;", "=", "+", ",", and "$") within a query component, this method
     * is recommended to use in encoding the whole query.
     *
     * @param unescaped an unescaped string
     * @return the escaped string
     *
     *
     */
    private static String encodeQuery(String unescaped) throws UnsupportedEncodingException {
        byte[] rawData = URLCodec.encodeUrl(allowed_query, HttpRequestHandler.getBytes(unescaped, defaultProtocolCharset));
        return HttpRequestHandler.getAsciiString(rawData);
    }

    /**
     * Converts the specified string to a byte array.  If the charset is not supported the
     * default system charset is used.
     *
     * @param data the string to be encoded
     * @param charset the desired character encoding
     * @return The resulting byte array.
     *
     */
    private static byte[] getBytes(final String data, String charset) {

        if (data == null) {
            throw new IllegalArgumentException("data may not be null");
        }

        if (charset == null || charset.length() == 0) {
            throw new IllegalArgumentException("charset may not be null or empty");
        }

        try {
            return data.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("Unsupported encoding: " + charset + ". System encoding used.");
            return data.getBytes();
        }
    }

    /**
     * Converts the byte array of ASCII characters to a string. This method is
     * to be used when decoding content of HTTP elements (such as response
     * headers)
     *
     * @param data the byte array to be encoded
     * @return The string representation of the byte array
     *
     * @since 3.0
     */
    private static String getAsciiString(final byte[] data) throws UnsupportedEncodingException {

        if (data == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        }

        return new String(data, 0, data.length, "US-ASCII");
    }

    /**
     * Unescape and decode a given string regarded as an escaped string with the
     * default protocol charset.
     *
     * @param escaped a string
     * @return the unescaped string
     *
     *
     */
    public static String decode(String escaped) throws UnsupportedEncodingException, DecoderException {
        byte[] rawdata = URLCodec.decodeUrl(HttpRequestHandler.getAsciiBytes(escaped));
        return HttpRequestHandler.getString(rawdata, defaultProtocolCharset);
    }

    /**
     * Converts the byte array of HTTP content characters to a string. If
     * the specified charset is not supported, default system encoding
     * is used.
     *
     * @param data the byte array to be encoded
     * @param charset the desired character encoding
     * @return The result of the conversion.
     *
     * @since 3.0
     */
    private static String getString(
        final byte[] data,
        String charset
    ) {

        if (data == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        }

        if (charset == null || charset.length() == 0) {
            throw new IllegalArgumentException("charset may not be null or empty");
        }

        try {
            return new String(data, 0, data.length, charset);
        } catch (UnsupportedEncodingException e) {

            LOGGER.warn("Unsupported encoding: " + charset + ". System encoding used");
            return new String(data, 0, data.length);
        }
    }

    /**
     * Converts the specified string to byte array of ASCII characters.
     *
     * @param data the string to be encoded
     * @return The string as a byte array.
     *
     * @since 3.0
     */
    private static byte[] getAsciiBytes(final String data) throws UnsupportedEncodingException {

        if (data == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        }

        return data.getBytes("US-ASCII");
    }

    static {
        Locale locale = Locale.getDefault();
        if (locale != null) {
            defaultDocumentCharsetByLocale = LocaleToCharsetMap.getCharset(locale);
            defaultDocumentCharset = defaultDocumentCharsetByLocale;
        }

        try {
            defaultDocumentCharsetByPlatform = System.getProperty("file.encoding");
        } catch (SecurityException var2) {
        }

        if (defaultDocumentCharset == null) {
            defaultDocumentCharset = defaultDocumentCharsetByPlatform;
        }

        rootPath = new char[]{'/'};
        percent = new BitSet(256);
        percent.set(37);
        digit = new BitSet(256);

        int i;
        for(i = 48; i <= 57; ++i) {
            digit.set(i);
        }

        alpha = new BitSet(256);

        for(i = 97; i <= 122; ++i) {
            alpha.set(i);
        }

        for(i = 65; i <= 90; ++i) {
            alpha.set(i);
        }

        alphanum = new BitSet(256);
        alphanum.or(alpha);
        alphanum.or(digit);
        hex = new BitSet(256);
        hex.or(digit);

        for(i = 97; i <= 102; ++i) {
            hex.set(i);
        }

        for(i = 65; i <= 70; ++i) {
            hex.set(i);
        }

        escaped = new BitSet(256);
        escaped.or(percent);
        escaped.or(hex);
        mark = new BitSet(256);
        mark.set(45);
        mark.set(95);
        mark.set(46);
        mark.set(33);
        mark.set(126);
        mark.set(42);
        mark.set(39);
        mark.set(40);
        mark.set(41);
        unreserved = new BitSet(256);
        unreserved.or(alphanum);
        unreserved.or(mark);
        reserved = new BitSet(256);
        reserved.set(59);
        reserved.set(47);
        reserved.set(63);
        reserved.set(58);
        reserved.set(64);
        reserved.set(38);
        reserved.set(61);
        reserved.set(43);
        reserved.set(36);
        reserved.set(44);
        uric = new BitSet(256);
        uric.or(reserved);
        uric.or(unreserved);
        uric.or(escaped);
        allowed_query = new BitSet(256);
        allowed_query.or(uric);
        allowed_query.clear(37);
    }

    public static class LocaleToCharsetMap {
        private static final Hashtable LOCALE_TO_CHARSET_MAP = new Hashtable();

        public LocaleToCharsetMap() {
        }

        public static String getCharset(Locale locale) {
            String charset = (String)LOCALE_TO_CHARSET_MAP.get(locale.toString());
            if (charset != null) {
                return charset;
            } else {
                charset = (String)LOCALE_TO_CHARSET_MAP.get(locale.getLanguage());
                return charset;
            }
        }

        static {
            LOCALE_TO_CHARSET_MAP.put("ar", "ISO-8859-6");
            LOCALE_TO_CHARSET_MAP.put("be", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("bg", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("ca", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("cs", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("da", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("de", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("el", "ISO-8859-7");
            LOCALE_TO_CHARSET_MAP.put("en", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("es", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("et", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("fi", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("fr", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("hr", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("hu", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("is", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("it", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("iw", "ISO-8859-8");
            LOCALE_TO_CHARSET_MAP.put("ja", "Shift_JIS");
            LOCALE_TO_CHARSET_MAP.put("ko", "EUC-KR");
            LOCALE_TO_CHARSET_MAP.put("lt", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("lv", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("mk", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("nl", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("no", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("pl", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("pt", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("ro", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("ru", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("sh", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("sk", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("sl", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("sq", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("sr", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("sv", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("tr", "ISO-8859-9");
            LOCALE_TO_CHARSET_MAP.put("uk", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("zh", "GB2312");
            LOCALE_TO_CHARSET_MAP.put("zh_TW", "Big5");
        }
    }
}
