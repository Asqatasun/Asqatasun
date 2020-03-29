/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.webapp.report.layout;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import java.util.Locale;

import org.asqatasun.webapp.dto.AuditStatistics;
import org.asqatasun.webapp.report.layout.builder.LayoutBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jkowalczyk
 */
@Component
public class LayoutFactory {

    private final LayoutBuilder layoutBuilder;

    public LayoutFactory(LayoutBuilder layoutBuilder) {
        this.layoutBuilder = layoutBuilder;
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
