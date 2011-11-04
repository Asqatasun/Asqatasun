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
package org.opens.tgol.report.expression;

import ar.com.fdvs.dj.domain.CustomExpression;
import org.opens.tgol.report.expression.retriever.KeyRetriever;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author jkowalczyk
 */
public class I18nExpression implements CustomExpression {

    private static final long serialVersionUID = 1174999656431046383L;

    private ResourceBundle resourceBundle = null;
    private KeyRetriever keyRetriever = null;
    private boolean escapeHtml = false;
    
    /**
     * Default constructor
     */
    public I18nExpression(
            String bundleName,
            KeyRetriever keyRetriever,
            boolean escapeHtml,
            Locale locale) {
        if (bundleName != null) {
            this.resourceBundle = ResourceBundle.getBundle(bundleName, locale);
        }
        this.keyRetriever = keyRetriever;
        this.escapeHtml = escapeHtml;
    }

    public Object evaluate(Map fields, Map variables, Map parameters) {
        String key = keyRetriever.retrieveKey(fields, variables, parameters);
        if (resourceBundle != null) {
            String i18nValue = resourceBundle.getString(key);
            if (escapeHtml) {
                return StringEscapeUtils.escapeHtml(i18nValue);
            }
            return i18nValue;
        }
        return key;
    }

    @Override
    public String getClassName() {
        return String.class.getName();
    }

}