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
package org.opens.tanaguru.ruleimplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author jkowalczyk
 */
public class LanguageDetector {

    private static final Logger LOGGER = Logger.getLogger(LanguageDetector.class);
    private static final String LANGUAGE_KEY = "language";
    private static final String DATA_KEY = "data";
    private static final String DETECTIONS_KEY = "detections";
    private static final String IS_RELIABLE_KEY = "isReliable";
    private static final String CONFIDENCE_KEY = "confidence";
    private static final String UTF8_ENCODING_KEY = "UTF-8";
    private static final int MAX_SIZE_TEXT = 3000;

    private String proxyPort;
    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    private String proxyHost;
    public String getProxyHost() {
        return proxyPort;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    private String serviceUrl;
    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    private String version;
    public void setVersion(String version) {
        this.version = version;
    }

    private String textKey;
    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }

    /**
     * The unique instance of LanguageDetector
     */
    private static LanguageDetector languageDetector = null;

    /**
     * Private constructor
     */
    private LanguageDetector() {}

    /**
     * 
     * @return
     */
    public static synchronized LanguageDetector getInstance() {
        if (languageDetector == null) {
            languageDetector = new LanguageDetector();
        }
        return languageDetector;
    }

    public LanguageDetectionResult detectLanguage(String text) {
        BufferedReader reader = null;
        InputStreamReader inputStreamReader = null;
//        try {
//            text = java.net.URLEncoder.encode(text, UTF8_ENCODING_KEY);
//            boolean isTextTruncated = false;
//            if (text.length() > MAX_SIZE_TEXT) {
//                text = text.substring(0, MAX_SIZE_TEXT).trim();
//                //to avoid the split of an encoded character
//                if (text.charAt(text.length()-1) == '%') {
//                    text=text.substring(0, text.length()-1);
//                } else if (text.charAt(text.length()-2) == '%') {
//                    text=text.substring(0, text.length()-2);
//                } else if (text.charAt(text.length()-3) == '%') {
//                    text=text.substring(0, text.length()-3);
//                }
//                isTextTruncated = true;
//            }
//            URL url = new URL(serviceUrl + version + textKey + text);
//            LOGGER.debug("Json url request " +url.toString());
//            URLConnection connection = null;
//            if (!StringUtils.isEmpty(proxyHost) && !StringUtils.isEmpty(proxyPort)) {
//                LOGGER.debug("Launch request through proxy with values " +proxyHost + " : " +proxyPort);
//                SocketAddress sa = new InetSocketAddress(proxyHost,Integer.valueOf(proxyPort));
//                Proxy proxy = new Proxy(Proxy.Type.HTTP, sa);
//                connection = url.openConnection(proxy);
//            } else {
//                LOGGER.debug("Launch direct");
//                connection = url.openConnection();
//            }
//            String line;
//            StringBuilder builder = new StringBuilder();
//            inputStreamReader = new InputStreamReader(connection.getInputStream());
//            reader = new BufferedReader(inputStreamReader);
//            while ((line = reader.readLine()) != null) {
//                builder.append(line);
//            }
//            reader.close();
//            JSONObject json = new JSONObject(builder.toString());
//            JSONObject jdata = (JSONObject) json.get(DATA_KEY);
//            JSONArray jdetections = (JSONArray) jdata.get(DETECTIONS_KEY);
//            JSONObject langObject = extractBestResultObject(jdetections);
//            // if the text is truncated, the result is seen as unreliable
//            // coz' the test is done on a part of the text.
//            if (isTextTruncated) {
//                return new LanguageDetectionResult(langObject.get(LANGUAGE_KEY).toString(),
//                    false);
//            } else {
//                return new LanguageDetectionResult(((JSONObject)jdetections.get(0)).get(LANGUAGE_KEY).toString(),
//                    Boolean.valueOf(((JSONObject)jdetections.get(0)).get(IS_RELIABLE_KEY).toString()));
//            }
//        } catch (MalformedURLException ex) {
//            LOGGER.warn(null, ex);
//        } catch (IOException ex) {
//            LOGGER.warn(null, ex);
//        } catch (JSONException ex) {
//            LOGGER.warn(null, ex);
//        } catch (Exception ex) {
//            LOGGER.warn(null, ex);
//        } finally {
//            try {
//                if (reader != null) {
//                    reader.close();
//                }
//                if (inputStreamReader != null) {
//                    inputStreamReader.close();
//                }
//            } catch (IOException ex) {
//                LOGGER.warn(ex);
//            }
//        }
        return null;
    }

    /**
     * Multiple results are returned in a tab format. This method parses the
     * different results and keeps the best regarding the relevancy value.
     * @param jdetections
     * @return
     */
    private JSONObject extractBestResultObject(JSONArray jdetections) throws JSONException {
        JSONObject result = null;
        double bestRelevancy = -1;
        for (int i=0; i<jdetections.length();i++) {
            LOGGER.info(" bestRelevancy " + bestRelevancy);
            LOGGER.info("((JSONObject)jdetections.get(i)).getDouble(CONFIDENCE_KEY) " + ((JSONObject)jdetections.get(i)).getDouble(CONFIDENCE_KEY));
            LOGGER.info("((JSONObject)jdetections.get(i)).get(LANGUAGE_KEY) " + ((JSONObject)jdetections.get(i)).get(LANGUAGE_KEY));
            if (((JSONObject)jdetections.get(i)).getDouble(CONFIDENCE_KEY) > bestRelevancy) {
                result = (JSONObject)jdetections.get(i);
                bestRelevancy = ((JSONObject)jdetections.get(i)).getDouble(CONFIDENCE_KEY);
            }
        }
        return result;
    }

}