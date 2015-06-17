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
package org.tanaguru.security.tokenmanagement;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.owasp.esapi.crypto.CryptoToken;
import org.owasp.esapi.errors.EncryptionException;
import org.owasp.esapi.errors.ValidationException;

/**
 *
 * @author jkowalczyk
 */
public final class TokenManager {

    private String esapiPropertyName = null;

    public void setEsapiPropertyName(String esapiPropertyName) {
        this.esapiPropertyName = esapiPropertyName;
        setSystemProperty();
    }
    private String esapiPropertyValue = null;

    public void setEsapiPropertyValue(String esapiPropertyValue) {
        this.esapiPropertyValue = esapiPropertyValue;
        setSystemProperty();
    }
    private int tokenDurationValidity = 3600;

    public void setTokenDurationValidity(int tokenDurationValidity) {
        this.tokenDurationValidity = tokenDurationValidity;
    }
    private Map<String, Boolean> tokenUsage = new HashMap<String, Boolean>();

    /**
     * Default constructor
     */
    public TokenManager() {
    }

    /**
     *
     */
    private void setSystemProperty() {
        if (esapiPropertyName != null && esapiPropertyValue != null) {
            System.setProperty(esapiPropertyName, esapiPropertyValue);
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
        } catch (EncryptionException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
            return "";
        } catch (ValidationException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
            return "";
        }
    }
    
    /**
     *
     * @param userAccountName
     * @return
     */
    public String getTokenUser(String userAccountName) {
        try {
            CryptoToken cryptoToken = new CryptoToken();
            cryptoToken.setUserAccountName(userAccountName);
            cryptoToken.setExpiration(tokenDurationValidity);
            String token = cryptoToken.getToken();
            tokenUsage.put(token, Boolean.FALSE);
            return token;
        } catch (EncryptionException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
            return "";
        } catch (ValidationException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
            return "";
        }
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
                Logger.getLogger(this.getClass()).info("user == null");
                return false;
            }
            if (!StringUtils.equalsIgnoreCase(userAccountName, cryptoToken.getUserAccountName())) {
                Logger.getLogger(this.getClass()).info(
                        "!user.getEmail1().equalsIgnoreCase(cryptoToken.getUserAccountName() "
                        + userAccountName
                        + " "
                        + cryptoToken.getUserAccountName());
                return false;
            }
            if (Calendar.getInstance().getTime().after(cryptoToken.getExpirationDate())) {
                Logger.getLogger(this.getClass()).info(
                        "Calendar.getInstance().getTime().after(cryptoToken.getExpirationDate() "
                        + cryptoToken.getExpirationDate());
                return false;
            }
            if (!tokenUsage.containsKey(token)
                    || tokenUsage.get(token).booleanValue()) {
                Logger.getLogger(this.getClass()).info(
                        "!tokenUsage.containsKey(token) || "
                        + " tokenUsage.get(token).booleanValue() ");
                return false;
            }
            return true;
        } catch (EncryptionException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
            return false;
        }
    }

    /**
     *
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        try {
            CryptoToken cryptoToken = new CryptoToken(token);
            return cryptoToken.getUserAccountName();
        } catch (EncryptionException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
            return StringUtils.EMPTY;
        }
    }
    
    /**
     *
     * @param token
     * @param attributeName
     * @return
     */
    public String getAttributeValueFromToken(String token, String attributeName) {
        try {
            CryptoToken cryptoToken = new CryptoToken(token);
            return cryptoToken.getAttribute(attributeName);
        } catch (EncryptionException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
            return StringUtils.EMPTY;
        }
    }

    /**
     *
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token) {
        try {
            CryptoToken cryptoToken = new CryptoToken(token);
            if (Calendar.getInstance().getTime().after(cryptoToken.getExpirationDate())) {
                Logger.getLogger(this.getClass()).info(
                        "Calendar.getInstance().getTime().after(cryptoToken.getExpirationDate() "
                        + cryptoToken.getExpirationDate());
                return true;
            }
            return false;
        } catch (EncryptionException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
            return true;
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            Logger.getLogger(this.getClass()).warn(aioobe);
            return true;
        }
    }

    public void setTokenUsed(String token) {
        tokenUsage.put(token, Boolean.TRUE);
    }
}