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
package org.tanaguru.webapp.report.pagination.localization;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.displaytag.localization.I18nSpringAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * This class overrides the display tag I18nSpringAdapter to get the Locale.
 *
 * @author jkowalczyk
 */
public class TgolI18nSpringAdapter extends I18nSpringAdapter {

    /**
     * Default constructor
     */
    public TgolI18nSpringAdapter() {
        super();
    }

    /**
     * 
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        return RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
    }

}
