/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.opens.tanaguru.analyser;

import java.math.BigDecimal;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.opens.tanaguru.entity.factory.statistics.WebResourceStatisticsFactory;
import org.opens.tanaguru.entity.factory.statistics.WebResourceStatisticsFactoryImpl;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.statistics.WebResourceStatisticsDataService;
import org.opens.tanaguru.entity.service.statistics.WebResourceStatisticsDataServiceImpl;
import org.opens.tanaguru.entity.statistics.WebResourceStatistics;

/**
 *
 * @author jkowalczyk
 */
public class AnalyserImplTest extends TestCase {
    
    public AnalyserImplTest(String testName) {
        super(testName);
    }

    public void testComputeMark() {
        WebResourceStatisticsFactory webResourceStatisticsFactory = new WebResourceStatisticsFactoryImpl();
        WebResourceStatisticsDataService webResourceStatisticsDataService =
                new WebResourceStatisticsDataServiceImpl();
        webResourceStatisticsDataService.setEntityFactory(webResourceStatisticsFactory);
        AnalyserImpl analyser = new AnalyserImpl(null, null, null, webResourceStatisticsDataService, null, null, null, null, new ArrayList<Parameter>(),1);

        WebResourceStatistics wrStats = analyser.getWebResourceStatisticsDataService().create();
        wrStats.setNbOfPassed(89330);
        wrStats.setNbOfFailed(84541);
        wrStats.setNbOfNmi(97541);
        assertEquals(Double.valueOf(45.828712).floatValue(), analyser.computeMark(wrStats).getMark().floatValue());
        wrStats.setNbOfPassed(0);
        wrStats.setNbOfFailed(0);
        wrStats.setNbOfNmi(0);
        assertEquals(Double.valueOf(0).floatValue(), analyser.computeMark(wrStats).getMark().floatValue());
    }

    public void testComputeRawMark() {
        WebResourceStatisticsFactory webResourceStatisticsFactory =
                new WebResourceStatisticsFactoryImpl();
        WebResourceStatisticsDataService webResourceStatisticsDataService =
                new WebResourceStatisticsDataServiceImpl();
        webResourceStatisticsDataService.setEntityFactory(webResourceStatisticsFactory);
        AnalyserImpl analyser = new AnalyserImpl(null, null,  null, webResourceStatisticsDataService, null, null, null, null, new ArrayList<Parameter>(),1);

        WebResourceStatistics wrStats = analyser.getWebResourceStatisticsDataService().create();
        wrStats.setNbOfPassed(0);
        wrStats.setNbOfFailed(0);
        wrStats.setNbOfNmi(0);
//        wrStats.setWeightedPassed(BigDecimal.valueOf(Double.valueOf("0.0")));
        wrStats.setWeightedFailed(BigDecimal.valueOf(Double.valueOf("0.0")));
        wrStats.setWeightedNmi(BigDecimal.valueOf(Double.valueOf("21.1")));
        wrStats.setWeightedNa(BigDecimal.valueOf(Double.valueOf("114.2")));
        assertEquals(Double.valueOf(0).floatValue(), analyser.computeRawMark(wrStats).getRawMark().floatValue());
        
//        wrStats.setWeightedPassed(BigDecimal.valueOf(Double.valueOf("44.0")));
//        assertEquals(Double.valueOf(100).floatValue(), analyser.computeRawMark(wrStats).getRawMark().floatValue());
        
//        wrStats.setWeightedPassed(BigDecimal.valueOf(Double.valueOf("0")));
        wrStats.setWeightedFailed(BigDecimal.valueOf(Double.valueOf("44.0")));
        assertEquals(Double.valueOf(0).floatValue(), analyser.computeRawMark(wrStats).getRawMark().floatValue());
        
        wrStats.setNbOfPassed(45);
        wrStats.setNbOfFailed(84541);
        wrStats.setNbOfNmi(97541);
//        wrStats.setWeightedPassed(BigDecimal.valueOf(Double.valueOf("10.4")));
        wrStats.setWeightedFailed(BigDecimal.valueOf(Double.valueOf("44.8")));
        wrStats.setWeightedNmi(BigDecimal.valueOf(Double.valueOf("21.1")));
        wrStats.setWeightedNa(BigDecimal.valueOf(Double.valueOf("114.2")));
        assertEquals(Double.valueOf(50.11).floatValue(), analyser.computeRawMark(wrStats).getRawMark().floatValue());
        // the nmi value is not taken into account
        wrStats.setWeightedNmi(BigDecimal.valueOf(Double.valueOf("1.1")));
        wrStats.setWeightedNa(BigDecimal.valueOf(Double.valueOf("134.4")));
        assertEquals(Double.valueOf(50.11).floatValue(), analyser.computeRawMark(wrStats).getRawMark().floatValue());

        wrStats.setWeightedPassed(BigDecimal.valueOf(Double.valueOf("50.4")));
        wrStats.setWeightedFailed(BigDecimal.valueOf(Double.valueOf("24.8")));
        assertEquals(Double.valueOf(64.47).floatValue(), analyser.computeRawMark(wrStats).getRawMark().floatValue());
    }

}