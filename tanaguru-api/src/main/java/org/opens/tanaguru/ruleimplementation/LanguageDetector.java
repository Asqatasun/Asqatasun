/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.ruleimplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author jkowalczyk
 */
public class LanguageDetector {

    private static final Logger LOGGER = Logger.getLogger(LanguageDetector.class);
    private static final String RESPONSE_STATUS_KEY = "responseStatus";
    private static final String RESPONSE_DATA_KEY = "responseData";
    private static final String LANGUAGE_KEY = "language";
    private static final String IS_RELIABLE_KEY = "isReliable";
    private static final String UTF8_ENCODING_KEY = "UTF-8";
    private static final int MAX_SIZE_TEXT = 1700;

    private String serviceUrl;
    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    private String version;
    public void setVersion(String version) {
        this.version = version;
    }

    private String userKey;
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    private String textKey;
    public void setTextKey(String textKey) {
        this.textKey = textKey;
    }

    private String refererKey;
    public void setRefererKey(String refererKey) {
        this.refererKey = refererKey;
    }

    private String refererValue;
    public void setRefererValue(String refererValue) {
        this.refererValue = refererValue;
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
        try {
            text = java.net.URLEncoder.encode(text, UTF8_ENCODING_KEY);
            boolean isTextTruncated = false;
            if (text.length() > MAX_SIZE_TEXT) {
                text = text.substring(0, MAX_SIZE_TEXT).trim();
                //to avoid the split of an encoded character
                if (text.charAt(text.length()-1) == '%') {
                    text=text.substring(0, text.length()-1);
                } else if (text.charAt(text.length()-2) == '%') {
                    text=text.substring(0, text.length()-2);
                } else if (text.charAt(text.length()-3) == '%') {
                    text=text.substring(0, text.length()-3);
                }
                isTextTruncated = true;
            }
            URL url = new URL(serviceUrl + version + textKey + text + userKey);
            LOGGER.debug("Json url request " +url.toString());
            URLConnection connection = url.openConnection();
            connection.addRequestProperty(refererKey, refererValue);
            String line;
            StringBuilder builder = new StringBuilder();
            inputStreamReader = new InputStreamReader(connection.getInputStream());
            reader = new BufferedReader(inputStreamReader);
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            JSONObject json = new JSONObject(builder.toString());
            LOGGER.debug("Json response status " +json.get(RESPONSE_STATUS_KEY));
            if (json.get(RESPONSE_STATUS_KEY).equals(HttpStatus.SC_OK)) {
                JSONObject jsonResponse = (JSONObject) json.get(RESPONSE_DATA_KEY);
                LOGGER.debug("Confidence detection result " +jsonResponse.get("confidence").toString());
                // if the text is truncated, the result is seen as unreliable
                // coz' the test is done on a part of the text.
                if (isTextTruncated) {
                    return new LanguageDetectionResult(jsonResponse.get(LANGUAGE_KEY).toString(),
                        false);
                } else {
                    return new LanguageDetectionResult(jsonResponse.get(LANGUAGE_KEY).toString(),
                        Boolean.valueOf(jsonResponse.get(IS_RELIABLE_KEY).toString()));
                }
            }
            return null;
        } catch (MalformedURLException ex) {
            LOGGER.warn(null, ex);
        } catch (IOException ex) {
            LOGGER.warn(null, ex);
        } catch (JSONException ex) {
            LOGGER.warn(null, ex);
        } catch (Exception ex) {
            LOGGER.warn(null, ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException ex) {
                LOGGER.warn(ex);
            }
        }
        return null;
    }

}