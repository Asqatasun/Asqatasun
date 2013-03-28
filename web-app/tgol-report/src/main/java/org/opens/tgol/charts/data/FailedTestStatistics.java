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
package org.opens.tgol.charts.data;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tgol.presentation.data.ResultCounter;

/**
 * This class handles displayable audit statistics data
 * @author jkowalczyk
 */
public class FailedTestStatistics {

    private static final long serialVersionUID = -6801992004288144815L;

    private static final String THEME_BUNDLE_NAME="theme-I18N";
    private static final String RULE_RESULT_BUNDLE_NAME = "rule-result-I18N";
    private static final int FAILED_INDEX = 0;
    private static final int MAX_NUMBER_OF_CATEGORIES = 5;

    private Integer[][] startValues;
    /**
     *
     * @return
     */
    public Integer[][] getStartValues() {
        return Arrays.copyOf(startValues,startValues.length);
    }

    private Integer[][] endValues;
    /**
     *
     * @return
     */
    public Integer[][] getEndValues() {
        return Arrays.copyOf(endValues,endValues.length);
    }

    private String[] series = {"failed"};
    /**
     *
     * @return
     */
    public String[] getSeries() {
        return Arrays.copyOf(series,series.length);
    }
    
    private String[] categories = null;
    /**
     *
     * @return
     */
    public String[] getCategories() {
        return Arrays.copyOf(categories,categories.length);
    }

    private String[] categoriesI18NLabel;
    /**
     *
     * @return
     */
    public String[] getCategoriesI18NLabel() {
        return Arrays.copyOf(categoriesI18NLabel,endValues.length);
    }

    private boolean isFailedTestStaticsSet = false;
    private boolean isLocaleSet = false;

    /**
     * The locale and the data have to be set to internationalise the labels
     */
    private void setI18NNames(){
        if (isLocaleSet && isFailedTestStaticsSet) {
            setSeriesName();
            setValuesAndCategoriesName();
        }
    }

    private Locale locale = null;
    /**
     *
     * @param locale
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        isLocaleSet = true;
        setI18NNames();
    }

    private void setSeriesName() {
        ResourceBundle ruleResultResourceBundle;
        if (locale != null) {
            ruleResultResourceBundle = ResourceBundle.getBundle(RULE_RESULT_BUNDLE_NAME, locale);
        } else {
            ruleResultResourceBundle = ResourceBundle.getBundle(RULE_RESULT_BUNDLE_NAME);
        }
        for (int i = 0; i < series.length; i++) {
            series[i] = ruleResultResourceBundle.getString(series[i]);
        }
    }

    private void setValuesAndCategoriesName() {
        ResourceBundle themeResourceBundle;
        if (locale != null) {
            themeResourceBundle = ResourceBundle.getBundle(THEME_BUNDLE_NAME, locale);
        } else {
            themeResourceBundle = ResourceBundle.getBundle(THEME_BUNDLE_NAME);
        }
        int i = 0;
        for (Map.Entry<Theme, ResultCounter> entry : testByThemeStatics.entrySet()) {
            int categoryIndex = i;
            endValues[FAILED_INDEX][categoryIndex] =
                    Integer.valueOf(entry.getValue().getFailedCount());
            categories[i] = themeResourceBundle.getString(entry.getKey().getCode());
            i++;
            // we just want the 5 themes with the max failed elements
            if (i == testByThemeStatics.size()) {
                break;
            }
        }
    }

    private Map<Theme, ResultCounter> testByThemeStatics;
    /**
     * Prepares the data to be rendered through the chart
     * @param testByThemeStatics
     */
    public void setFailedTestStatics(final Map<Theme, ResultCounter> testByThemeStatics) {
        categories = new String[MAX_NUMBER_OF_CATEGORIES];
        startValues = new Integer[series.length][MAX_NUMBER_OF_CATEGORIES];
        endValues = new Integer[series.length][MAX_NUMBER_OF_CATEGORIES];
        this.testByThemeStatics = testByThemeStatics;
        isFailedTestStaticsSet = true;
        setI18NNames();
    }
    
}