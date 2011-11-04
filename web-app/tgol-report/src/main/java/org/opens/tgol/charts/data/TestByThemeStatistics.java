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

import org.opens.tgol.presentation.data.ResultCounter;
import de.laures.cewolf.DatasetProducer;
import de.laures.cewolf.links.CategoryItemLinkGenerator;
import de.laures.cewolf.tooltips.CategoryToolTipGenerator;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.opens.tanaguru.entity.reference.Theme;

/**
 * This class handles displayable audit statistics data
 * @author jkowalczyk
 */
public class TestByThemeStatistics implements
        DatasetProducer, CategoryToolTipGenerator, CategoryItemLinkGenerator {

    private static final long serialVersionUID = -5711192551200288686L;
    
    private static final String ANCHOR_LINK_PREFIX = "#theme";
    private static final String SHORT_LABEL_PREFIX = "Th";
    private static final String THEME_BUNDLE_NAME = "theme-I18N";
    private static final String RULE_RESULT_BUNDLE_NAME = "rule-result-I18N";
    private static final int PASSED_INDEX = 0;
    private static final int FAILED_INDEX = 1;
    private static final int NMI_INDEX = 2;
    private static final int NA_INDEX = 3;

    private String[] series = {"passed", "failed", "nmi", "na"};
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

    private String[] categoriesI18NLabel;
    /**
     *
     * @return
     */
    public String[] getCategoriesI18NLabel() {
        return Arrays.copyOf(categoriesI18NLabel,categoriesI18NLabel.length);
    }

    private boolean isTestByThemeStaticsSet = false;
    private boolean isLocaleSet = false;

    /**
     * The locale and the data have to be set to internationalise the labels
     */
    private void setI18NNames(){
        if (isLocaleSet && isTestByThemeStaticsSet) {
            setCategoriesName();
            setSeriesName();
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

    private void setCategoriesName() {
        ResourceBundle themeResourceBundle;
        if (locale != null) {
            themeResourceBundle = ResourceBundle.getBundle(THEME_BUNDLE_NAME, locale);
        } else {
            themeResourceBundle = ResourceBundle.getBundle(THEME_BUNDLE_NAME);
        }
        for (int i = 0; i < categoriesI18NLabel.length; i++) {
            categoriesI18NLabel[i] = themeResourceBundle.getString(categoriesI18NLabel[i]);
        }
        for (int i = 0; i < categories.length; i++) {
            categories[i] = SHORT_LABEL_PREFIX+(i+1);
        }
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

    /**
     * Prepares the data to be rendered through the chart
     * @param testByThemeStatics
     */
    public void setTestByThemeStatics(Map<Theme, ResultCounter> testByThemeStatics) {
        categories = new String[testByThemeStatics.size()];
        categoriesI18NLabel = new String[testByThemeStatics.size()];
        startValues = new Integer[series.length][categories.length];
        endValues = new Integer[series.length][categories.length];
        int categoryIndex = 0;
        for (Map.Entry<Theme, ResultCounter> entrySet : testByThemeStatics.entrySet()) {
            categoriesI18NLabel[categoryIndex] = entrySet.getKey().getCode();
            ResultCounter resultCounter = entrySet.getValue();
            endValues[PASSED_INDEX][categoryIndex] =
                    Integer.valueOf(resultCounter.getPassedCount());
            endValues[FAILED_INDEX][categoryIndex] =
                    Integer.valueOf(resultCounter.getFailedCount());
            endValues[NMI_INDEX][categoryIndex] =
                    Integer.valueOf(resultCounter.getNmiCount());
            endValues[NA_INDEX][categoryIndex] =
                    Integer.valueOf(resultCounter.getNaCount());
            categoryIndex++;
        }
        isTestByThemeStaticsSet = true;
        setI18NNames();
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    public Object produceDataset(Map params) {
        DefaultIntervalCategoryDataset ds = new DefaultIntervalCategoryDataset(
                series,
                categories,
                startValues,
                endValues);
        return ds;
    }

    /**
     *
     * @return
     */
    @Override
    public String getProducerId() {
        return "CategoryDataProducer";
    }

    /**
     *
     * @param params
     * @param since
     * @return
     */
    @Override
    public boolean hasExpired(Map params, Date since) {
        return false;
    }

    /**
     * 
     * @param cd
     * @param serieIndex
     * @param index
     * @return
     */
    @Override
    public String generateToolTip(CategoryDataset cd, int serieIndex, int index) {
        return categoriesI18NLabel[index] +
                " : " +
                cd.getValue(serieIndex, index) + 
                " "+
                series[serieIndex];
    }

    /**
     *
     * @param o
     * @param index
     * @param o1
     * @return
     */
    @Override
    public String generateLink(Object o, int index, Object o1) {
        int i = 0;
        for (i=0;i<categories.length;i++) {
            if (o1 instanceof String) {
                if (categories[i].equalsIgnoreCase((String)o1)) {
                    break;
                }
            }
        }
        return ANCHOR_LINK_PREFIX+(i+1);
    }

}
