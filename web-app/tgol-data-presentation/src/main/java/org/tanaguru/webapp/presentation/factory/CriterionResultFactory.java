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
package org.tanaguru.webapp.presentation.factory;

import java.util.*;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.Theme;
import org.tanaguru.entity.service.reference.CriterionDataService;
import org.tanaguru.entity.service.statistics.CriterionStatisticsDataService;
import org.tanaguru.entity.statistics.CriterionStatistics;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.webapp.presentation.data.CriterionResult;
import org.tanaguru.webapp.presentation.data.CriterionResultImpl;
import org.tanaguru.webapp.presentation.data.TestResult;
import org.tanaguru.webapp.presentation.data.TestResultImpl;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author jkowalczyk
 */
public final class CriterionResultFactory {

    private CriterionStatisticsDataService criterionStatisticsDataService;
    @Autowired
    public void setCriterionStatisticsDataService(CriterionStatisticsDataService criterionStatisticsDataService) {
        this.criterionStatisticsDataService = criterionStatisticsDataService;
    }
    
    private CriterionDataService criterionDataService;
    @Autowired
    public void setCriterionDataService(CriterionDataService criterionDataService) {
        this.criterionDataService = criterionDataService;
    }

    /**
     * The unique shared instance of CriterionResultFactory
     */
    private static CriterionResultFactory criterionResultFactory;

    /**
     * Default private constructor
     */
    private CriterionResultFactory(){}

    public static synchronized CriterionResultFactory getInstance(){
        if (criterionResultFactory == null) {
            criterionResultFactory = new CriterionResultFactory();
        }
        return criterionResultFactory;
    }

    /**
     *
     * @return
     */
    public TestResult getTestResult() {
        return new TestResultImpl();
    }

    /**
     * 
     * @param criterionStatistics
     * @return
     */
    public CriterionResult getCriterionResult(
            CriterionStatistics criterionStatistics) {
        CriterionResult criterionResult = new CriterionResultImpl();
        criterionResult.setCriterion(criterionStatistics.getCriterion());
        criterionResult.setCriterionCode(criterionStatistics.getCriterion().getCode());
        criterionResult.setLevelCode(criterionDataService.getCriterionLevel(criterionStatistics.getCriterion()).getCode());
        criterionResult.setResult(criterionStatistics.getCriterionResult().toString());
        criterionResult.setResultCode(setResultToLowerCase(criterionStatistics.getCriterionResult()));
        return criterionResult;
    }

    /**
     * 
     * @param webresource
     * @param theme
     * @param testSolution
     * @return 
     *      A map of themes with for each theme the list of CriterionResult
     */
    public Map<Theme, List<CriterionResult>> getCriterionResultSortedByThemeMap(
            WebResource webresource,
            String theme,
            Collection<String> testSolution){
        List<CriterionStatistics> criterionStatList = new ArrayList<CriterionStatistics>();
        criterionStatList.addAll(
                criterionStatisticsDataService.getCriterionStatisticsByWebResource(
                    webresource,
                    theme, 
                    testSolution));
        return prepareThemeResultMap(criterionStatList);
    }

    /**
     * 
     * @param netResultList
     * @return
     */
    private Map<Theme, List<CriterionResult>> prepareThemeResultMap(
            List<CriterionStatistics> criterionStatList) {
        // Map that associates a list of results with a theme
        Map<Theme, List<CriterionResult>> criterionResultMap =
                new LinkedHashMap<Theme, List<CriterionResult>>();
        sortCollection(criterionStatList);
        for (CriterionStatistics crs : criterionStatList) {
                CriterionResult testResult = getCriterionResult(
                        crs);
                Theme theme = crs.getCriterion().getTheme();
                if (criterionResultMap.containsKey(theme)) {
                    criterionResultMap.get(theme).add(testResult);
                } else {
                    List<CriterionResult> criterionResultList = new ArrayList<CriterionResult>();
                    criterionResultList.add(testResult);
                    criterionResultMap.put(theme, criterionResultList);
                }
        }
        return criterionResultMap;
    }

    /**
     * This method sorts the processResult elements
     * @param processResultList
     */
    private void sortCollection(List<CriterionStatistics> criterionStatisticsList) {
        Collections.sort(criterionStatisticsList, new Comparator<CriterionStatistics>() {
            @Override
            public int compare(CriterionStatistics o1, CriterionStatistics o2) {
                return Integer.compare(
                        o1.getCriterion().getRank(),
                        o2.getCriterion().getRank());
            }
        });
    }

    /**
     * This method sets the resultToLower attribute
     * according to the result value and sets the element counter attribute
     */
    private String setResultToLowerCase(
            TestSolution testSolution) {
        if (testSolution.equals(TestSolution.FAILED)) {
            return TestResult.FAILED_LOWER;
        } else if (testSolution.equals(TestSolution.PASSED)) {
            return TestResult.PASSED_LOWER;
        } else if (testSolution.equals(TestSolution.NEED_MORE_INFO)) {
            return TestResult.NEED_MORE_INFO_LOWER;
        } else if (testSolution.equals(TestSolution.NOT_APPLICABLE)) {
            return TestResult.NOT_APPLICABLE_LOWER;
        } else if (testSolution.equals(TestSolution.NOT_TESTED)) {
            return TestResult.NOT_TESTED_LOWER;
        }
        return null;
    }

}