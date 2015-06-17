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
package org.tanaguru.webapp.report.layout.builder;

import org.tanaguru.webapp.presentation.data.AuditStatistics;
import java.text.DateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author jkowalczyk
 */
public class TitleBuilderImpl implements TitleBuilder {

    private static final String AUDIT_REPORT_KEY = "export-report.auditReport";
    private static final String FROM_KEY = "export-report.from";
    private static final String OF_KEY = "export-report.of";
    private static final char SPACE_KEY = ' ';

    private String bundleName;
    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    @Override
    public String getTitle(AuditStatistics auditStatistics, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
        StringBuilder title = new StringBuilder();
        title.append(bundle.getString(AUDIT_REPORT_KEY));
        title.append(SPACE_KEY);
        title.append(bundle.getString(FROM_KEY));
        title.append(SPACE_KEY);
        title.append(DateFormat.getDateInstance(DateFormat.LONG, locale).format(auditStatistics.getDate()));
        title.append(SPACE_KEY);
        title.append(bundle.getString(OF_KEY));
        title.append(SPACE_KEY);
        title.append(auditStatistics.getUrl());
        return title.toString();
    }

}