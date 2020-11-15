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
package org.asqatasun.webapp.dto.factory;

import java.util.*;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.reference.Theme;
import org.asqatasun.entity.service.reference.CriterionDataService;
import org.asqatasun.entity.service.statistics.CriterionStatisticsDataService;
import org.asqatasun.entity.statistics.CriterionStatistics;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.webapp.dto.CriterionResult;
import org.asqatasun.webapp.dto.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * @author jkowalczyk
 */
@Component("criterionResultFactory")
public final class CriterionResultFactory {

    private final CriterionStatisticsDataService criterionStatisticsDataService;
    private final CriterionDataService criterionDataService;


    /**
     * Default private constructor
     */
    @Autowired
    public CriterionResultFactory(CriterionStatisticsDataService criterionStatisticsDataService, CriterionDataService criterionDataService){
        this.criterionStatisticsDataService = criterionStatisticsDataService;
        this.criterionDataService = criterionDataService;
    }

    /**
     *
     * @return
     */
    public TestResult getTestResult() {
        return new TestResult();
    }

    /**
     * 
     * @param criterionStatistics
     * @return
     */
    public CriterionResult getCriterionResult(
            CriterionStatistics criterionStatistics) {
        CriterionResult criterionResult = new CriterionResult();
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
        List<CriterionStatistics> criterionStatList = new ArrayList<>();
        criterionStatList.addAll(
                criterionStatisticsDataService.getCriterionStatisticsByWebResource(
                    webresource,
                    theme, 
                    testSolution));
        return prepareThemeResultMap(criterionStatList);
    }

    /**
     * 
     * @param criterionStatList
     * @return
     */
    private Map<Theme, List<CriterionResult>> prepareThemeResultMap(
            List<CriterionStatistics> criterionStatList) {
        // Map that associates a list of results with a theme
        Map<Theme, List<CriterionResult>> criterionResultMap = new LinkedHashMap<>();
        sortCollection(criterionStatList);
        for (CriterionStatistics crs : criterionStatList) {
                CriterionResult testResult = getCriterionResult(crs);
                Theme theme = crs.getCriterion().getTheme();
                if (criterionResultMap.containsKey(theme)) {
                    criterionResultMap.get(theme).add(testResult);
                } else {
                    List<CriterionResult> criterionResultList = new ArrayList<>();
                    criterionResultList.add(testResult);
                    criterionResultMap.put(theme, criterionResultList);
                }
        }
        return criterionResultMap;
    }

    /**
     * This method sorts the processResult elements
     * @param criterionStatisticsList
     */
    private void sortCollection(List<CriterionStatistics> criterionStatisticsList) {
        criterionStatisticsList.sort(Comparator.comparingInt(o -> o.getCriterion().getRank()));
    }

    /**
     * This method sets the resultToLower attribute
     * according to the result value and sets the element counter attribute
     */
    private String setResultToLowerCase(
            TestSolution testSolution) {
        switch (testSolution) {
            case FAILED:
                return TestResult.FAILED_LOWER;
            case PASSED:
                return TestResult.PASSED_LOWER;
            case NEED_MORE_INFO:
                return TestResult.NEED_MORE_INFO_LOWER;
            case NOT_APPLICABLE:
                return TestResult.NOT_APPLICABLE_LOWER;
            case NOT_TESTED:
                return TestResult.NOT_TESTED_LOWER;
            default:
                return null;
        }
    }

}
