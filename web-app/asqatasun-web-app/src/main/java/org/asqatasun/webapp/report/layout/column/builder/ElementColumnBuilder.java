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
package org.asqatasun.webapp.report.layout.column.builder;

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
import org.asqatasun.webapp.report.expression.builder.AbstractGenericConditionalStyleBuilder;
import org.asqatasun.webapp.report.expression.builder.AbstractGenericCustomExpressionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jkowalczyk
 */
public class ElementColumnBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElementColumnBuilder.class);
    private static final int DEFAULT_COLUMN_WIDTH = 70;

    private AbstractGenericCustomExpressionBuilder<? extends CustomExpression> customExpressionBuilder = null;

    public AbstractGenericCustomExpressionBuilder<? extends CustomExpression> getCustomExpressionBuilder() {
        return customExpressionBuilder;
    }

    public void setCustomExpressionBuilder(AbstractGenericCustomExpressionBuilder<? extends CustomExpression> customExpressionBuilder) {
        this.customExpressionBuilder = customExpressionBuilder;
    }
    
    private List<AbstractGenericConditionalStyleBuilder<? extends ConditionalStyle>> conditionalStyleBuilderList = null;
    public List<AbstractGenericConditionalStyleBuilder<? extends ConditionalStyle>> getConditionalStyleBuilderList() {
        return conditionalStyleBuilderList;
    }

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
    public String getColumnTitleKey() {
        return columnTitleKey;
    }
    public void setColumnTitleKey(String columnTitleKey) {
        this.columnTitleKey = columnTitleKey;
    }

    private String columnTitleBundleName = null;
    public void setColumnTitleBundleName(String columnTitleBundleName) {
        this.columnTitleBundleName = columnTitleBundleName;
    }

    private int columnWidth = DEFAULT_COLUMN_WIDTH;
    public int getColumnWidth() {
        return columnWidth;
    }
    public void setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
    }

    private Style style;
    public Style getStyle() {
        return style;
    }
    public void setStyle(Style style) {
        this.style = style;
    }

    private Style headerStyle;
    public Style getHeaderStyle() {
        return headerStyle;
    }
    public void setHeaderStyle(Style headerStyle) {
        this.headerStyle = headerStyle;
    }

    private Style columnStyle;
    public Style getColumnStyle() {
        return columnStyle;
    }
    public void setColumnStyle(Style columnStyle) {
        this.columnStyle = columnStyle;
    }

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
            LOGGER.error(ex.getMessage());
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
