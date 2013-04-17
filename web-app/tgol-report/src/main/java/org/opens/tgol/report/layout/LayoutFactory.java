/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
package org.opens.tgol.report.layout;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import org.opens.tgol.presentation.data.AuditStatistics;
import org.opens.tgol.report.layout.builder.LayoutBuilder;
import java.util.Locale;

/**
 *
 * @author jkowalczyk
 */
public class LayoutFactory {

    private LayoutBuilder layoutBuilder;
    public LayoutBuilder getLayoutBuilder() {
        return layoutBuilder;
    }

    public void setLayoutBuilder(LayoutBuilder layoutBuilder) {
        this.layoutBuilder = layoutBuilder;
    }

    private static LayoutFactory layoutFactory;

    /**
     * Private constructor
     */
    private LayoutFactory() {
    }

    public static synchronized LayoutFactory getInstance() {
        if (layoutFactory == null) {
            layoutFactory = new LayoutFactory();
        }
        return layoutFactory;
    }

    /**
     * 
     * @param locale
     * @param auditStatistics
     * @param format
     * @return
     * @throws ColumnBuilderException
     * @throws ClassNotFoundException
     */
    public DynamicReport buildReportLayout(Locale locale, AuditStatistics auditStatistics, String format)
            throws ColumnBuilderException, ClassNotFoundException {
        // Build an empty layout and return it
        return layoutBuilder.getDynamicReportBuilder(auditStatistics, locale, format).build();
    }

}
