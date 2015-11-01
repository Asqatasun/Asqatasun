/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.tanaguru.webapp.report.layout.builder;

import java.util.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.tanaguru.webapp.presentation.data.AuditStatistics;

/**
 *
 * @author jkowalczyk
 */
public class SubtitleBuilderImpl implements TitleBuilder {

    private static final char PERCENT_KEY = '%';
    private static final String SEPARATOR_KEY = "  -  ";
    private static final String DOUBLE_DOT_KEY = " : ";
    private static final char SPACE_KEY = ' ';

    private static final String MARK_KEY = "export-report.mark";
//    private String weightedMarkKey = "export-report.weightedMark";
    private static final String REF_KEY = "referential";
    private static final String LEVEL_KEY = "level";
    
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

    private String refBundleName;
    public String getRefBundleName() {
        return refBundleName;
    }

    public void setRefBundleName(String refBundleName) {
        this.refBundleName = refBundleName;
    }
    
    private final List<String> refAndLevelValueBundleNameList = new ArrayList();
    public List<String> getRefAndLevelValueBundleList() {
        return refAndLevelValueBundleNameList;
    }

    public void setRefAndLevelValueBundleList(List<String> refAndLevelValueBundleNameList) {
        this.refAndLevelValueBundleNameList.addAll(refAndLevelValueBundleNameList);
    }
    
    public void addRefAndLevelValueBundleList(String refAndLevelValueBundleName) {
        this.refAndLevelValueBundleNameList.add(refAndLevelValueBundleName);
    }

    @Override
    public String getTitle(AuditStatistics auditStatistics, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
        StringBuilder subTitle = new StringBuilder();
        subTitle.append(bundle.getString(MARK_KEY));
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
        Collection<ResourceBundle> refAndlevelValueBundleList = new ArrayList();
        for (String bundle: refAndLevelValueBundleNameList) {
            refAndlevelValueBundleList.add(ResourceBundle.getBundle(bundle, locale));
        }
        ResourceBundle levelBundle = ResourceBundle.getBundle(levelBundleName, locale);
        StringBuilder refAndLevel = new StringBuilder();
        refAndLevel.append(StringEscapeUtils.unescapeHtml4(refBundle.getString(REF_KEY)));
        refAndLevel.append(DOUBLE_DOT_KEY);
        refAndLevel.append(StringEscapeUtils.unescapeHtml4(
                retrieveI18nValue(
                    auditStatistics.getParametersMap().get(REF_KEY),
                    refAndlevelValueBundleList)));
        refAndLevel.append(SEPARATOR_KEY);
        refAndLevel.append(StringEscapeUtils.unescapeHtml4(levelBundle.getString(LEVEL_KEY)));
        refAndLevel.append(DOUBLE_DOT_KEY);
        refAndLevel.append(StringEscapeUtils.unescapeHtml4(
                retrieveI18nValue(
                    auditStatistics.getParametersMap().get(LEVEL_KEY).replace(";", "-"),
                    refAndlevelValueBundleList)));
        return refAndLevel.toString();
    }

    /**
     * Retrieve a i18n among the Collection of resourceBundle associated with
     * the instance
     * 
     * @param key
     * @param resourceBundleList
     * @return 
     */
    private String retrieveI18nValue(String key, Collection<ResourceBundle> resourceBundleList) {
        for (ResourceBundle rb: resourceBundleList) {
            if (rb.containsKey(key)) {
                return rb.getString(key);
            }
        }
        return key;
    }
}