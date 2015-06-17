/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.webapp.report.expression;

import ar.com.fdvs.dj.domain.entities.conditionalStyle.ConditionStyleExpression;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author jkowalczyk
 */
public class ResultStyleExpression extends ConditionStyleExpression {

    private static final long serialVersionUID = -4839459906641930176L;
    private final String result;

    public ResultStyleExpression(String result, String bundleName, Locale locale) {
        if (bundleName != null) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName, locale);
            this.result = StringEscapeUtils.unescapeHtml4(resourceBundle.getString(result));
        } else {
            this.result = result;
        }
    }

    @Override
    public Object evaluate(Map fields, Map variables, Map parameters) {
        Object currentValue = getCurrentValue();
        if (currentValue == null) {
            return null;
        }
        String currentResult = (String) currentValue;
        return StringUtils.equals(result, currentResult);
    }

    @Override
    public String getClassName() {
        return Boolean.class.getName();
    }

}