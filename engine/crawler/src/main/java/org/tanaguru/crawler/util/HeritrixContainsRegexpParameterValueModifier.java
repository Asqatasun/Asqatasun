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
package org.tanaguru.crawler.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Overriding in this class the buildRegexp method to format a regular
 * expression. This will be check if the url contains the regexp.
 *
 * @author alingua
 */
public class HeritrixContainsRegexpParameterValueModifier extends HeritrixRegexpParameterValueModifier {

    private static final String BEGIN_REGEXP_EXCLUSION = "(?i)(.*";

    public HeritrixContainsRegexpParameterValueModifier() {
        super();
    }

    @Override
    protected String buildRegexp(String rawRegexp, String url) {
        if (StringUtils.isNotBlank(rawRegexp)) {
            StringBuilder strb = new StringBuilder();
            strb.append(BEGIN_REGEXP_EXCLUSION);
            strb.append(rawRegexp);
            strb.append(END_REGEXP);
            return strb.toString();
        }
        return rawRegexp.toString();
    }
}