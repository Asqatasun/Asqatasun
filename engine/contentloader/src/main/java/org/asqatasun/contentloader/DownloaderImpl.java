/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.contentloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import javax.net.ssl.SSLPeerUnverifiedException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

/**
 * 
 * @author jkowalczyk
 */
public class DownloaderImpl implements Downloader {
    
    private static final Logger LOGGER = Logger.getLogger(DownloaderImpl.class);
    
    protected String result;
    protected String url;
    private final String HTTP_PROTOCOL_PREFIX = "http://";
    private final String HTTPS_PROTOCOL_PREFIX = "https://";
    private final String FILE_PROTOCOL_PREFIX = "file:/";

    public DownloaderImpl() {
        super();
    }

    private String load(String url) {
        BufferedReader in = null;
        try {
            StringBuilder urlContent = new StringBuilder();
            String thisLine;
            URL u = new URL(url);
            in = new BufferedReader(new InputStreamReader(u.openStream(), Charset.forName("UTF-8")));
            while ((thisLine = in.readLine()) != null) {
                //Correction of #34 bug
                urlContent.append(thisLine);
                urlContent.append("\r");
            }
            return urlContent.toString();
        } catch (IOException ex) {
            LOGGER.warn(ex);
            return "";
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                LOGGER.warn(ex);
                throw new RuntimeException(ex);
            }
        }
    }

    private String download(String url) {
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet httpget = new HttpGet(url);

        httpclient.getParams().setParameter(
                "http.socket.timeout", Integer.valueOf(10000));
        httpclient.getParams().setParameter(
                "http.connection.timeout", Integer.valueOf(10000));

        // Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = null;
        try {
            responseBody = httpclient.execute(httpget, responseHandler);
        } catch (HttpResponseException ex) {
            LOGGER.warn(ex.getMessage() + " " +url);
            return "";
        } catch (UnknownHostException ex ) {
            LOGGER.warn(ex.getMessage() + " " +url);
            return "";
        } catch (SSLPeerUnverifiedException ex) {
            LOGGER.warn(ex.getMessage() + " " +url);
            return "";
        } catch (IOException ex) {
            LOGGER.warn(ex.getMessage() + " " +url);
            return "";
        }
        // When HttpClient instance is no longer needed,
        // shut down the connection manager to ensure
        // immediate deallocation of all system resources
        httpclient.getConnectionManager().shutdown();
        return responseBody;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public void run() {
        if (url.startsWith(HTTP_PROTOCOL_PREFIX)
                || url.startsWith(HTTPS_PROTOCOL_PREFIX)) {
            LOGGER.debug("Download resource "  + url);
            result = download(url);
        } else if (url.startsWith(FILE_PROTOCOL_PREFIX)) {
            LOGGER.debug("Load resource "  + url);
            result = load(url);
        }
    }

    @Override
    public void setURL(String url) {
        this.url = url;
        }

}