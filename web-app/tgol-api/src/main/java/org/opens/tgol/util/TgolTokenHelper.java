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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.opens.tgol.entity.user.User;
import org.owasp.esapi.crypto.CryptoToken;
import org.owasp.esapi.errors.EncryptionException;
import org.owasp.esapi.errors.ValidationException;

/**
 *
 * @author jkowalczyk
 */
public final class TgolTokenHelper {

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
     * The unique instance of TgolTokenHelper
     */
    private static TgolTokenHelper tokenHelper = null;

    /**
     * Private constructor
     */
    private TgolTokenHelper() {}

    /**
     *
     * @return
     */
    public static synchronized TgolTokenHelper getInstance() {
        if (tokenHelper == null) {
            tokenHelper = new TgolTokenHelper();
        }
        return tokenHelper;
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
     * @param user
     * @return
     */
    public String getTokenUser(User user) {
        try {
            CryptoToken cryptoToken = new CryptoToken();
            cryptoToken.setUserAccountName(user.getEmail1());
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
     * @param user
     * @param token
     * @return
     */
    public boolean checkUserToken(User user, String token) {
        CryptoToken cryptoToken = null;
        try {
            cryptoToken = new CryptoToken(token);
        } catch (EncryptionException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
            return false;
        }
        if (user == null) {
            Logger.getLogger(this.getClass()).info("user == null");
            return false;
        }
        if (!user.getEmail1().equalsIgnoreCase(cryptoToken.getUserAccountName())) {
            Logger.getLogger(this.getClass()).info(
                    "!user.getEmail1().equalsIgnoreCase(cryptoToken.getUserAccountName() " 
                    + user.getEmail1() 
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
        if (!tokenUsage.containsKey(token) || 
            tokenUsage.get(token).booleanValue()) {
            Logger.getLogger(this.getClass()).info(
                    "!tokenUsage.containsKey(token) || "
                    + " tokenUsage.get(token).booleanValue() " );
            return false;
        }
        return true;
    }
    
    public void setTokenUsed(String token) {
        tokenUsage.put(token,Boolean.TRUE);   
    }

}