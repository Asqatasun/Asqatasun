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
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.conditionalStyle.ConditionalStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.tanaguru.webapp.report.expression.builder.AbstractGenericConditionalStyleBuilder;
import org.tanaguru.webapp.report.expression.builder.AbstractGenericCustomExpressionBuilder;

/**
 *
 * @author jkowalczyk
 */
public class ElementColumnBuilderImpl implements ElementColumnBuilder{

    private static final Logger LOGGER = Logger.getLogger(ElementColumnBuilderImpl.class);
    private static final int DEFAULT_COLUMN_WIDTH = 70;

    private AbstractGenericCustomExpressionBuilder<? extends CustomExpression> customExpressionBuilder = null;
    @Override
    public AbstractGenericCustomExpressionBuilder<? extends CustomExpression> getCustomExpressionBuilder() {
        return customExpressionBuilder;
    }

    @Override
    public void setCustomExpressionBuilder(AbstractGenericCustomExpressionBuilder<? extends CustomExpression> customExpressionBuilder) {
        this.customExpressionBuilder = customExpressionBuilder;
    }
    
    private List<AbstractGenericConditionalStyleBuilder<? extends ConditionalStyle>> conditionalStyleBuilderList = null;
    @Override
    public List<AbstractGenericConditionalStyleBuilder<? extends ConditionalStyle>> getConditionalStyleBuilderList() {
        return conditionalStyleBuilderList;
    }

    @Override
    public void setConditionalStyleBuilderList(
            List<AbstractGenericConditionalStyleBuilder<? extends ConditionalStyle>> conditionalStyleBuilderList) {
        this.conditionalStyleBuilderList =
                conditionalStyleBuilderList;
    }

    private String propertyName;
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    private String valueClassName;
    public String getValueClassName() {
        return valueClassName;
    }

    public void setValueClassName(String valueClassName) {
        this.valueClassName = valueClassName;
    }
    
    private String columnTitleKey;
    @Override
    public String getColumnTitleKey() {
        return columnTitleKey;
    }

    @Override
    public void setColumnTitleKey(String columnTitleKey) {
        this.columnTitleKey = columnTitleKey;
    }

    private String columnTitleBundleName = null;

    @Override
    public void setColumnTitleBundleName(String columnTitleBundleName) {
        this.columnTitleBundleName = columnTitleBundleName;
    }

    private int columnWidth = DEFAULT_COLUMN_WIDTH;
    @Override
    public int getColumnWidth() {
        return columnWidth;
    }

    @Override
    public void setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
    }

    private Style style;
    @Override
    public Style getStyle() {
        return style;
    }

    @Override
    public void setStyle(Style style) {
        this.style = style;
    }

    private Style headerStyle;
    @Override
    public Style getHeaderStyle() {
        return headerStyle;
    }

    @Override
    public void setHeaderStyle(Style headerStyle) {
        this.headerStyle = headerStyle;
    }

    private Style columnStyle;
    @Override
    public Style getColumnStyle() {
        return columnStyle;
    }

    @Override
    public void setColumnStyle(Style columnStyle) {
        this.columnStyle = columnStyle;
    }

    @Override
    public AbstractColumn getElementColumn(Locale locale) {
        ColumnBuilder columnBuilder = ColumnBuilder.getNew();
        columnBuilder.setWidth(columnWidth);
        if (propertyName != null && valueClassName != null) {
            columnBuilder.setColumnProperty(propertyName, valueClassName);
        }
        if (customExpressionBuilder != null) {
            columnBuilder.setCustomExpression(customExpressionBuilder.build(locale));
        }
        if (style != null) {
            columnBuilder.setStyle(style);
        }
        if (headerStyle != null) {
            columnBuilder.setHeaderStyle(headerStyle);
        }
        if (columnTitleBundleName != null) {
            ResourceBundle bundle = ResourceBundle.getBundle(columnTitleBundleName, locale);
            if (columnTitleKey != null) {
                columnBuilder.setTitle(StringEscapeUtils.unescapeHtml4(bundle.getString(columnTitleKey)));
            }
        }
        if (conditionalStyleBuilderList != null &&
                !conditionalStyleBuilderList.isEmpty()) {
            columnBuilder.addConditionalStyles(buildConditionStyleList(locale));
        }
        AbstractColumn elementColumn = null;
        try {
            elementColumn = columnBuilder.build();
        } catch (ColumnBuilderException ex) {
            LOGGER.error(ex);
        }
        return elementColumn;
    }

    /**
     *
     * @param locale
     * @return
     */
    private List<ConditionalStyle> buildConditionStyleList(Locale locale) {
        List<ConditionalStyle> cseList = new ArrayList();
        for (AbstractGenericConditionalStyleBuilder<? extends ConditionalStyle> csb : conditionalStyleBuilderList) {
            cseList.add(csb.build(locale));
        }
        return cseList;
    }

}