/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.webapp.security.tokenmanagement;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.owasp.esapi.crypto.CryptoToken;
import org.owasp.esapi.errors.EncryptionException;
import org.owasp.esapi.errors.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 *
 * @author jkowalczyk
 */
@Service("tokenManager")
public final class TokenManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenManager.class);
    private static final String ESAPI_PROPERTY_NAME = "org.owasp.esapi.resources";

    @Value("${app.webapp.security.owasp.esapiPropertyValue}")
    private String esapiPropertyValue;
    @Value("${app.webapp.security.owasp.tokenDurationValidity}")
    private int tokenDurationValidity;

    private Map<String, Boolean> tokenUsage = new HashMap<>();

    /**
     * Default constructor
     */
    public TokenManager() {
    }

    /**
     *
     */
    @PostConstruct
    private void setSystemProperty() {
        if (esapiPropertyValue != null) {
            System.setProperty(ESAPI_PROPERTY_NAME, esapiPropertyValue);
        }
    }

    /**
     *
     * @param userAccountName
     * @param additionalParameters
     * @return
     */
    public String getTokenUser(String userAccountName, Map<String, String> additionalParameters) {
        try {
            CryptoToken cryptoToken = new CryptoToken();
            cryptoToken.setUserAccountName(userAccountName);
            cryptoToken.setExpiration(tokenDurationValidity);
            cryptoToken.addAttributes(additionalParameters);
            String token = cryptoToken.getToken();
            tokenUsage.put(token, Boolean.FALSE);
            return token;
        } catch (EncryptionException | ValidationException ex) {
            LOGGER.warn(ex.getMessage());
            return "";
        }
    }
    
    /**
     *
     * @param userAccountName
     * @return
     */
    public String getTokenUser(String userAccountName) {
        return getTokenUser(userAccountName, Collections.emptyMap());
    }

    /**
     *
     * @param userAccountName
     * @param token
     * @return
     */
    public boolean checkUserToken(String userAccountName, String token) {
        try {
            CryptoToken cryptoToken = new CryptoToken(token);
            if (StringUtils.isBlank(userAccountName)) {
                LOGGER.info("user == null");
                return false;
            }
            if (!StringUtils.equalsIgnoreCase(userAccountName, cryptoToken.getUserAccountName())) {
                LOGGER.info(
                        "!user.getEmail1().equalsIgnoreCase(cryptoToken.getUserAccountName() "
                        + userAccountName
                        + " "
                        + cryptoToken.getUserAccountName());
                return false;
            }
            if (Calendar.getInstance().getTime().after(cryptoToken.getExpirationDate())) {
                LOGGER.info(
                        "Calendar.getInstance().getTime().after(cryptoToken.getExpirationDate() "
                        + cryptoToken.getExpirationDate());
                return false;
            }
            if (!tokenUsage.containsKey(token) || tokenUsage.get(token)) {
                LOGGER.info("!tokenUsage.containsKey(token) || " + " tokenUsage.get(token).booleanValue() ");
                return false;
            }
            return true;
        } catch (EncryptionException ex) {
            LOGGER.warn(ex.getMessage());
            return false;
        }
    }

    public void setTokenUsed(String token) {
        tokenUsage.put(token, Boolean.TRUE);
    }
}
