/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
package org.opens.tgol.report.layout.builder;

import org.opens.tgol.presentation.data.AuditStatistics;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author jkowalczyk
 */
public class SubtitleBuilderImpl implements TitleBuilder {

    private static final char PERCENT_KEY = '%';
    private static final String SEPARATOR_KEY = "  -  ";
    private static final String DOUBLE_DOT_KEY = " : ";
    private static final char SPACE_KEY = ' ';

    private String markKey = "export-report.mark";
//    private String weightedMarkKey = "export-report.weightedMark";
    private String refKey = "referential";
    private String levelKey = "level";
    
    private String levelParamKey = "LEVEL";
    public String getLevelParamKey() {
        return levelParamKey;
    }

    public void setLevelParamKey(String levelParamKey) {
        this.levelParamKey = levelParamKey;
    }

    private String bundleName;
    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    private String levelBundleName;
    public String getLevelBundleName() {
        return levelBundleName;
    }

    public void setLevelBundleName(String levelBundleName) {
        this.levelBundleName = levelBundleName;
    }

    private String levelValueBundleName;
    public String getLevelValueBundleName() {
        return levelValueBundleName;
    }

    public void setLevelValueBundleName(String levelValueBundleName) {
        this.levelValueBundleName = levelValueBundleName;
    }
    
    private String refBundleName;
    public String getRefBundleName() {
        return refBundleName;
    }

    public void setRefBundleName(String refBundleName) {
        this.refBundleName = refBundleName;
    }
    
    private String refValueBundleName;
    public String getRefValueBundleName() {
        return refValueBundleName;
    }

    public void setRefValueBundleName(String refValueBundleName) {
        this.refValueBundleName = refValueBundleName;
    }

    @Override
    public String getTitle(AuditStatistics auditStatistics, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
        StringBuilder subTitle = new StringBuilder();
        subTitle.append(bundle.getString(markKey));
        subTitle.append(SPACE_KEY);
        subTitle.append(auditStatistics.getRawMark());
        subTitle.append(PERCENT_KEY);
        subTitle.append(SEPARATOR_KEY);
//        subTitle.append(bundle.getString(weightedMarkKey));
//        subTitle.append(SPACE_KEY);
//        subTitle.append(auditStatistics.getWeightedMark());
//        subTitle.append(PERCENT_KEY);
//        subTitle.append(SEPARATOR_KEY);
        subTitle.append(getRefAndLevel(auditStatistics, locale));
        return subTitle.toString();
    }

    private String getRefAndLevel(AuditStatistics auditStatistics, Locale locale) {
        ResourceBundle refBundle = ResourceBundle.getBundle(refBundleName, locale);
        ResourceBundle refValueBundle = ResourceBundle.getBundle(refValueBundleName, locale);
        ResourceBundle levelValueBundle = ResourceBundle.getBundle(levelValueBundleName, locale);
        ResourceBundle levelBundle = ResourceBundle.getBundle(levelBundleName, locale);
        StringBuilder refAndLevel = new StringBuilder();
        refAndLevel.append(StringEscapeUtils.unescapeHtml(refBundle.getString(refKey)));
        refAndLevel.append(DOUBLE_DOT_KEY);
        refAndLevel.append(StringEscapeUtils.unescapeHtml(refValueBundle.getString(auditStatistics.getParametersMap().get(refKey))));
        refAndLevel.append(SEPARATOR_KEY);
        refAndLevel.append(StringEscapeUtils.unescapeHtml(levelBundle.getString(levelKey)));
        refAndLevel.append(DOUBLE_DOT_KEY);
        refAndLevel.append(StringEscapeUtils.unescapeHtml(levelValueBundle.getString(auditStatistics.getParametersMap().get(levelKey))));
        return refAndLevel.toString();
    }

}