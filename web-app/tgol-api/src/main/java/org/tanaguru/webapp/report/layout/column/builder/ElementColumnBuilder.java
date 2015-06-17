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
package org.tanaguru.webapp.report.layout.column.builder;

import ar.com.fdvs.dj.domain.CustomExpression;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.conditionalStyle.ConditionalStyle;
import org.tanaguru.webapp.report.expression.builder.AbstractGenericConditionalStyleBuilder;
import org.tanaguru.webapp.report.expression.builder.AbstractGenericCustomExpressionBuilder;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author jkowalczyk
 */
public interface ElementColumnBuilder {

    /**
     *
     * @return
     */
    AbstractGenericCustomExpressionBuilder<? extends CustomExpression> getCustomExpressionBuilder();

    /**
     *
     * @param customExpressionBuilder
     */
    void setCustomExpressionBuilder(AbstractGenericCustomExpressionBuilder<? extends CustomExpression> customExpressionBuilder);

    /**
     *
     * @return
     */
    List<AbstractGenericConditionalStyleBuilder<? extends ConditionalStyle> > getConditionalStyleBuilderList();

    /**
     *
     * @param conditionalStyleBuilderList
     */
    void setConditionalStyleBuilderList(
            List<AbstractGenericConditionalStyleBuilder<? extends ConditionalStyle>> conditionalStyleBuilderList);

    /**
     *
     * @return
     */
    String getColumnTitleKey();

    /**
     *
     * @param columnTitleKey
     */
    void setColumnTitleKey(String columnTitleKey);

    /**
     *
     * @param columnTitleBundleName
     */
    void setColumnTitleBundleName(String columnTitleBundleName);

    /**
     *
     * @return
     */
    int getColumnWidth();

    /**
     *
     * @param columnWidth
     */
    void setColumnWidth(int columnWidth);

    /**
     *
     * @return
     */
    Style getStyle();

    /**
     *
     * @param style
     */
    void setStyle(Style style);

    /**
     *
     * @return
     */
    Style getHeaderStyle();

    /**
     *
     * @param headerStyle
     */
    void setHeaderStyle(Style headerStyle);

    /**
     *
     * @return
     */
    Style getColumnStyle();

    /**
     *
     * @param columnStyle
     */
    void setColumnStyle(Style columnStyle);

    /**
     * 
     * @param locale
     * @return
     */
    AbstractColumn getElementColumn(Locale locale);

}