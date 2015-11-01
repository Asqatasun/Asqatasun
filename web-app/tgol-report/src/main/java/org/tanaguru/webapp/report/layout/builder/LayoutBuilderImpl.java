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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.webapp.report.layout.builder;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Page;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.tanaguru.webapp.presentation.data.AuditStatistics;
import org.tanaguru.webapp.report.layout.column.builder.ElementColumnBuilder;

/**
 *
 * @author jkowalczyk
 */
public class LayoutBuilderImpl implements LayoutBuilder {

    private TitleBuilder titleBuilder;
    public TitleBuilder getTitleBuilder() {
        return titleBuilder;
    }

    public void setTitleBuilder(TitleBuilder titleBuilder) {
        this.titleBuilder = titleBuilder;
    }

    private Style titleStyle;
    public Style getTitleStyle() {
        return titleStyle;
    }

    public void setTitleStyle(Style titleStyle) {
        this.titleStyle = titleStyle;
    }

    private TitleBuilder subtitleBuilder;
    public TitleBuilder getSubtitleBuilder() {
        return subtitleBuilder;
    }

    public void setSubtitleBuilder(TitleBuilder subtitleBuilder) {
        this.subtitleBuilder = subtitleBuilder;
    }

    private Style subtitleStyle;
    public Style getSubtitleStyle() {
        return subtitleStyle;
    }

    public void setSubtitleStyle(Style subtitleStyle) {
        this.subtitleStyle = subtitleStyle;
    }

    private List<ElementColumnBuilder> columnBuilderList;
    public List<ElementColumnBuilder> getColumnBuilderList() {
        return columnBuilderList;
    }

    public void setColumnBuilderList(List<ElementColumnBuilder> columnBuilderList) {
        this.columnBuilderList = columnBuilderList;
    }
    
    private Map<String, List<ElementColumnBuilder>> formatSpecificColumnBuilderList;
    public Map<String, List<ElementColumnBuilder>> getFormatSpecificColumnBuilderList() {
        return formatSpecificColumnBuilderList;
    }

    public void setFormatSpecificColumnBuilderList(Map<String, List<ElementColumnBuilder>> formatSpecificColumnBuilderList) {
        this.formatSpecificColumnBuilderList = formatSpecificColumnBuilderList;
    }

    private Map<String, String> fieldMap = new HashMap<String, String>();
    public void setFieldMap(Map<String, String> fieldMap) {
        for (Map.Entry<String,String> entry : fieldMap.entrySet()) {
            this.fieldMap.put(entry.getKey(), entry.getValue());
        }
    }

    private boolean printColumnNames = false;
    public boolean isPrintColumnNames() {
        return printColumnNames;
    }

    public void setPrintColumnNames(boolean printColumnNames) {
        this.printColumnNames = printColumnNames;
    }

    boolean ignorePagination = false;
    public boolean isIgnorePagination() {
        return ignorePagination;
    }

    public void setIgnorePagination(boolean ignorePagination) {
        this.ignorePagination = ignorePagination;
    }

    boolean useFullPageWidth = false;
    public boolean isUseFullPageWidth() {
        return useFullPageWidth;
    }

    public void setUseFullPageWidth(boolean useFullPageWidth) {
        this.useFullPageWidth = useFullPageWidth;
    }

    private int topMargin = 0;
    private static final int TOP_MARGIN_INDEX = 0;
    private int bottomMargin = 0;
    private static final int BOTTOM_MARGIN_INDEX = 1;
    private int leftMargin = 0;
    private static final int LEFT_MARGIN_INDEX = 2;
    private int rightMargin = 0;
    private static final int RIGHT_MARGIN_INDEX = 3;

    public void setMargin(String margin) {
        String[] margins = margin.split(":");
        if (margins[TOP_MARGIN_INDEX] != null) {
            topMargin = Integer.valueOf(margins[TOP_MARGIN_INDEX]);
        }
        if (margins[BOTTOM_MARGIN_INDEX] != null) {
            bottomMargin = Integer.valueOf(margins[BOTTOM_MARGIN_INDEX]);
        }
        if (margins[LEFT_MARGIN_INDEX] != null) {
            leftMargin = Integer.valueOf(margins[LEFT_MARGIN_INDEX]);
        }
        if (margins[RIGHT_MARGIN_INDEX] != null) {
            rightMargin = Integer.valueOf(margins[RIGHT_MARGIN_INDEX]);
        }
    }

    private Page pageOrientation;
    public Page getPageOrientation() {
        return pageOrientation;
    }

    public void setPageOrientation(Page pageOrientation) {
        this.pageOrientation = pageOrientation;
    }

    @Override
    public DynamicReportBuilder getDynamicReportBuilder(
            AuditStatistics auditStatistics,
            Locale locale, 
            String format) {
        DynamicReportBuilder report = new FastReportBuilder();
        report.setTitle(titleBuilder.getTitle(auditStatistics, locale));
        report.setTitleStyle(titleStyle);
        report.setSubtitle(subtitleBuilder.getTitle(auditStatistics, locale));
        report.setSubtitleStyle(subtitleStyle);
        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            report.addField(entry.getKey(), entry.getValue());
        }
        for (ElementColumnBuilder ecb : columnBuilderList) {
            report.addColumn(ecb.getElementColumn(locale));
        }
        if (formatSpecificColumnBuilderList.containsKey(format)) {
            for (ElementColumnBuilder ecb : formatSpecificColumnBuilderList.get(format)) {
                report.addColumn(ecb.getElementColumn(locale));
            }
        }
        report.setPrintColumnNames(printColumnNames);
        report.setIgnorePagination(ignorePagination);
        report.setUseFullPageWidth(useFullPageWidth);
        report.setMargins(topMargin, bottomMargin, leftMargin, rightMargin);
        if (pageOrientation != null) {
            report.setPageSizeAndOrientation(pageOrientation);
            report.setPageSizeAndOrientation(Page.Page_A4_Landscape());
        }
        return report;
    }

}
