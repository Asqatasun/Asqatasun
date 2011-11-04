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
import de.laures.cewolf.tooltips.PieToolTipGenerator;
import java.util.Date;
import java.util.Map;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * This class handles displayable audit statistics data
 * @author jkowalczyk
 */
public class TestStatistics implements DatasetProducer, PieToolTipGenerator {

    private static final long serialVersionUID = -7355119874195234288L;
    
    private static final int PASSED_INDEX = 0;
    private static final int FAILED_INDEX = 1;
    private static final int NMI_INDEX = 2;
    private static final int NA_INDEX = 3;

    private String[] categories = {"Passed", "Failed", "NMI", "NA"};
    /**
     *
     * @return
     */
    public String[] getCategories() {
        return categories.clone();
    }

    /**
     * The number of tests whom result is passed
     */
    private String passedCount;
    /**
     *
     * @param passedCount
     */
    public void setPassedCount(String passedCount) {
        this.passedCount = passedCount;
    }

    /**
     * The number of tests whose result is failed
     */
    private String failedCount;
    /**
     *
     * @param failedCount
     */
    public void setFailedCount(String failedCount) {
        this.failedCount = failedCount;
    }

    /**
     * The number of tests whose result is NA
     */
    private String naCount;
    /**
     *
     * @param naCount
     */
    public void setNaCount(String naCount) {
        this.naCount = naCount;
    }

    /**
     * The number of tests whose result is nmi
     */
    private String nmiCount;
    /**
     *
     * @param nmiCount
     */
    public void setNmiCount(String nmiCount) {
        this.nmiCount = nmiCount;
    }

    /**
     *
     * @param map
     * @return
     * @throws DatasetProduceException
     */
    @Override
    public Object produceDataset(Map map) throws DatasetProduceException {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.insertValue(PASSED_INDEX, categories[PASSED_INDEX],
                Integer.valueOf(passedCount));
        dataset.insertValue(FAILED_INDEX, categories[FAILED_INDEX],
                Integer.valueOf(failedCount));
        dataset.insertValue(NMI_INDEX, categories[NMI_INDEX],
                Integer.valueOf(nmiCount));
        dataset.insertValue(NA_INDEX, categories[NA_INDEX],
                Integer.valueOf(naCount));
        return dataset;
    }

    /**
     * 
     * @param map
     * @param date
     * @return
     */
    @Override
    public boolean hasExpired(Map map, Date date) {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public String getProducerId() {
        return "AuditStatistics DatasetProducer";
    }

    /**
     *
     * @param pd
     * @param cmprbl
     * @param i
     * @return
     */
    @Override
    public String generateToolTip(PieDataset pd, Comparable cmprbl, int i) {
        return categories[i] + " : " + String.valueOf(pd.getValue(i).intValue());
    }

}