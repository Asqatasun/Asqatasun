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
package org.tanaguru.webapp.report.layout;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import java.util.Locale;
import org.tanaguru.webapp.presentation.data.AuditStatistics;
import org.tanaguru.webapp.report.layout.builder.LayoutBuilder;

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

    /**
     * The holder that handles the unique instance of LayoutFactory
     */
    private static class LayoutFactoryHolder {
        private static final LayoutFactory INSTANCE = new LayoutFactory();
    }
    
    /**
     * Private constructor
     */
    private LayoutFactory() {}
    
    /**
     * Singleton pattern based on the "Initialization-on-demand 
     * holder idiom". See @http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * @return the unique instance of LayoutFactory
     */
    public static LayoutFactory getInstance() {
        return LayoutFactoryHolder.INSTANCE;
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
