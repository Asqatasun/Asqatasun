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

import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;
import de.laures.cewolf.tooltips.CategoryToolTipGenerator;
import java.text.SimpleDateFormat;
import java.util.*;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.opens.tgol.presentation.data.ActInfo;

/**
 * This class handles displayable audit statistics data
 * @author jkowalczyk
 */
public class ActStatistics implements DatasetProducer, CategoryToolTipGenerator {

    private static final long serialVersionUID = -6148366503395125638L;

    private static final String CONTRACT_PAGE_BUNDLE_NAME="contract-page-I18N";
    private SimpleDateFormat defaultFormater = new SimpleDateFormat("MM/dd/yy hh:mm:ss a");
    private SimpleDateFormat frFormater = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

    private String[] seriesI18NLabel = new String[2];
    /**
     *
     * @return
     */
    public String[] getSeriesI18NLabel() {
        return Arrays.copyOf(seriesI18NLabel,seriesI18NLabel.length);
    }

    // index 0 = rawMark ; index 1 = weightedMark
//    private String[] series = {
//        "contract.rawMark",
//        "contract.weightedMark"};
    private String[] series = {"contract.rawMark"};
    /**
     *
     * @return
     */
    public String[] getSeries() {
        return Arrays.copyOf(series,series.length);
    }

    private String[] categoriesI18NLabel = null;
    /**
     *
     * @return
     */
    public String[] getCategoriesI18NLabel() {
        return Arrays.copyOf(categoriesI18NLabel,categoriesI18NLabel.length);
    }

    private Date[] categories = null;
    /**
     *
     * @return
     */
    public Date[] getCategories() {
        return Arrays.copyOf(categories,categories.length);
    }

    private boolean isActSetSet = false;
    private boolean isLocaleSet = false;

    /**
     * The locale and the data have to be set to internationalise the labels
     */
    private void setI18NNames(){
        if (isLocaleSet && isActSetSet) {
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

    private Integer[][] values;
    /**
     *
     * @return
     */
    public Integer[][] getValues() {
        return Arrays.copyOf(values,values.length);
    }

    private void setCategoriesName() {
        for (int i = 0; i < categories.length; i++) {
            SimpleDateFormat formatter = null;
            if (locale != null && (locale.equals(Locale.FRANCE) || locale.equals(Locale.FRENCH))) {
                formatter = frFormater;
            } else {
                formatter = defaultFormater;
            }
            categoriesI18NLabel[i] = formatter.format(categories[i]);
        }
    }

    private void setSeriesName() {
        ResourceBundle resourceBundle;
        if (locale != null) {
            resourceBundle = ResourceBundle.getBundle(CONTRACT_PAGE_BUNDLE_NAME, locale);
        } else {
            resourceBundle = ResourceBundle.getBundle(CONTRACT_PAGE_BUNDLE_NAME);
        }
        for (int i = 0; i < series.length; i++) {
            seriesI18NLabel[i] = resourceBundle.getString(series[i]);
        }
    }

    /**
     * Prepares the data to be rendered through the chart
     * @param actSet
     */
    public void setActSet(Collection<ActInfo> actSet) {
        int nbOfActs = 0;
        for (ActInfo act : actSet) {
            if (act.getRawMark() != 0) {
                nbOfActs++;
            }
        }
        categories = new Date[nbOfActs++];
        categoriesI18NLabel = new String[nbOfActs++];
        values = new Integer[series.length][categories.length];
        int actIndex = 0;
        for (ActInfo act : actSet) {
            if (act.getRawMark() != 0 && act.getWeightedMark() != 0) {
                values[0][actIndex] = act.getRawMark();
//                values[1][actIndex] =act.getWeightedMark();
                categories[actIndex]=act.getDate();
                actIndex++;
            }
        }
        isActSetSet = true;
        setI18NNames();
    }

    /**
     *
     * @return
     */
    @Override
    public String getProducerId() {
        return "XYDataProducer";
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
     * @param map
     * @return
     * @throws DatasetProduceException
     */
    @Override
    public Object produceDataset(Map map) throws DatasetProduceException {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        for (int i=0;i<categories.length;i++) {
            for (int j=0;j<series.length;j++) {
                ds.addValue(values[j][i], seriesI18NLabel[j], categoriesI18NLabel[i]);
            }
        }
        return ds;
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
                seriesI18NLabel[serieIndex] + " -> "+
                cd.getValue(serieIndex, index) +"% ";
    }

}