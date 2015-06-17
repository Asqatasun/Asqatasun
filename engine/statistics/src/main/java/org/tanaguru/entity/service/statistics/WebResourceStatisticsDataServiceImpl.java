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
package org.tanaguru.entity.service.statistics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.dao.statistics.CriterionStatisticsDAO;
import org.tanaguru.entity.dao.statistics.ThemeStatisticsDAO;
import org.tanaguru.entity.dao.statistics.WebResourceStatisticsDAO;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.entity.reference.Criterion;
import org.tanaguru.entity.reference.Theme;
import org.tanaguru.entity.statistics.CriterionStatistics;
import org.tanaguru.entity.statistics.CriterionStatisticsImpl;
import org.tanaguru.entity.statistics.ThemeStatistics;
import org.tanaguru.entity.statistics.ThemeStatisticsImpl;
import org.tanaguru.entity.statistics.WebResourceStatistics;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class WebResourceStatisticsDataServiceImpl extends
        AbstractGenericDataService<WebResourceStatistics, Long> implements
        WebResourceStatisticsDataService {

    private static final BigDecimal ZERO = BigDecimal.valueOf(Double.valueOf(0.0));

    private CriterionStatisticsDAO criterionStatisticsDAO;

    private ThemeStatisticsDAO themeStatisticsDAO;

    private CriterionStatisticsDataService criterionStatisticsDataService;

    private ThemeStatisticsDataService themeStatisticsDataService;

    public ThemeStatisticsDataService getThemeStatisticsDataService() {
        return themeStatisticsDataService;
    }

    @Autowired
    public void setThemeStatisticsDataService(
            ThemeStatisticsDataService themeStatisticsDataService) {
        this.themeStatisticsDataService = themeStatisticsDataService;
    }

    public CriterionStatisticsDataService getCriterionStatisticsDataService() {
        return criterionStatisticsDataService;
    }

    @Autowired
    public void setCriterionStatisticsDataService(
            CriterionStatisticsDataService criterionStatisticsDataService) {
        this.criterionStatisticsDataService = criterionStatisticsDataService;
    }

    @Override
    public Long getResultCountByResultType(Long webresourceId,
            TestSolution testSolution) {
        return ((WebResourceStatisticsDAO) entityDao)
                .findResultCountByResultType(webresourceId, testSolution);
    }

    @Override
    public BigDecimal getWeightedResultByResultType(Long webresourceId,
            Collection<Parameter> paramSet, TestSolution testSolution,
            boolean isManualAudit) {
        return ((WebResourceStatisticsDAO) entityDao)
                .findWeightedResultCountByResultType(webresourceId, paramSet,
                        testSolution, isManualAudit);
    }

    @Override
    public Long getNumberOfOccurrencesByWebResourceAndResultType(
            Long webresourceId, TestSolution testSolution, boolean isManualAudit) {
        return ((WebResourceStatisticsDAO) entityDao)
                .findNumberOfOccurrencesByWebResourceAndResultType(
                        webresourceId, testSolution, isManualAudit);
    }

    @Override
    public Integer getHttpStatusCodeByWebResource(Long webresourceId) {
        return ((WebResourceStatisticsDAO) entityDao)
                .findHttpStatusCodeByWebResource(webresourceId);
    }

    @Override
    public WebResourceStatistics getWebResourceStatisticsByWebResource(
            WebResource webResource) {
        return ((WebResourceStatisticsDAO) entityDao)
                .findWebResourceStatisticsByWebResource(webResource);
    }

    /**
     * This method compute the mark of the audit. Here is the algorithm formula
     * : ((1-ratioNMI) * passed/(passed+failed) + ratioNMI *
     * needMoreInfo/(passed+failed+needMoreInfo)) *100f where ratioNMI =
     * needMoreInfo / (passed+failed+needMoreInfo)
     *
     * @param wrStatistics
     * @return
     */
    public WebResourceStatistics computeMark(WebResourceStatistics wrStatistics) {
        float passed = wrStatistics.getNbOfPassed();
        // page on error, mark set to -1
        if (passed == -1) {
            wrStatistics.setRawMark(Float.valueOf(-1));
            return wrStatistics;
        }
        float failed = wrStatistics.getNbOfFailed();
        float needMoreInfo = wrStatistics.getNbOfNmi();
        if (failed == 0 && passed == 0) {
            wrStatistics.setMark(Float.valueOf(0));
            return wrStatistics;
        }
        float ratioNMI = needMoreInfo / (passed + failed + needMoreInfo);
        float result = ((1 - ratioNMI) * passed / (passed + failed) + ratioNMI
                * needMoreInfo / (passed + failed + needMoreInfo)) * 100f;
        wrStatistics.setMark(result);
        return wrStatistics;
    }

    /**
     * This method compute the raw mark of the audit. Here is the algorithm
     * formula : passed/(passed+failed)
     *
     * @param wrStatistics
     * @return
     */
    public WebResourceStatistics computeRawMark(
            WebResourceStatistics wrStatistics) {
        float passed = wrStatistics.getNbOfPassed();
        // page on error, mark set to -1
        if (passed == -1) {
            wrStatistics.setRawMark(Float.valueOf(-1));
            return wrStatistics;
        }
        BigDecimal weightedPassed = wrStatistics.getWeightedPassed();
        BigDecimal weightedFailed = wrStatistics.getWeightedFailed();
        if ((weightedFailed.equals(BigDecimal.ZERO) || weightedFailed
                .equals(ZERO))
                && (weightedPassed.equals(BigDecimal.ZERO) || weightedPassed
                .equals(ZERO))) {
            wrStatistics.setRawMark(Float.valueOf(0));
            return wrStatistics;
        }

        float result = weightedPassed.divide(
                weightedPassed.add(weightedFailed), 4, RoundingMode.HALF_UP)
                .floatValue() * 100f;
        wrStatistics.setRawMark(result);
        return wrStatistics;
    }

    /**
     * Gather the number of failed occurrence for a given web resource.
     *
     * @param wrStatistics
     * @param webResource
     * @param isManualAudit
     * @return
     */
    private WebResourceStatistics computeNumberOfFailedOccurrences(
            WebResourceStatistics wrStatistics, WebResource webResource, boolean isManualAudit) {
        int nbOfFailedOccurences = this
                .getNumberOfOccurrencesByWebResourceAndResultType(
                        webResource.getId(), TestSolution.FAILED, isManualAudit).intValue();
        wrStatistics.setNbOfFailedOccurences(nbOfFailedOccurences);
        return wrStatistics;
    }

    /**
     *
     * @param wrStatistics
     */
    private void setWeightedResult(WebResourceStatistics wrStatistics,
            WebResource webResource) {
        // TODO: Collection à initialiser
        Collection<Parameter> paramSet = new ArrayList<>();
        BigDecimal weightedPassed = this.getWeightedResultByResultType(
                webResource.getId(), paramSet, TestSolution.PASSED, true);

        BigDecimal weightedFailed = this.getWeightedResultByResultType(
                webResource.getId(), paramSet, TestSolution.FAILED, true);

        BigDecimal weightedNa = this.getWeightedResultByResultType(
                webResource.getId(), paramSet, TestSolution.NOT_APPLICABLE, true);

        BigDecimal weightedNmi = this.getWeightedResultByResultType(
                webResource.getId(), paramSet, TestSolution.NEED_MORE_INFO, true);
        wrStatistics.setWeightedFailed(weightedFailed);
        wrStatistics.setWeightedPassed(weightedPassed);
        wrStatistics.setWeightedNmi(weightedNmi);
        wrStatistics.setWeightedNa(weightedNa);
    }

    @Override
    public WebResourceStatistics createWebResourceStatisticsForManualAudit(
            Audit audit, WebResource webResource,
            List<ProcessResult> netResultList) {
        boolean isNewWebRs = false;

        WebResourceStatistics wrStats = ((WebResourceStatisticsDAO) entityDao).findWebResourceStatisticsByWebResource(webResource, true);

        if (wrStats == null) {
            wrStats = this.create();
            isNewWebRs = true;
        }

        Map<Criterion, CriterionStatistics> csMap = new HashMap<>();
        Map<Theme, ThemeStatistics> tsMap = new HashMap<>();
//        Map<Theme, List<ProcessResult>> mapProcessResultBytheme = new HashMap<Theme, List<ProcessResult>>();

        int nbOfPassed = 0;
        int nbOfFailed = 0;
        int nbOfNmi = 0;
        int nbOfNa = 0;
        int nbOfDetected = 0;
        int nbOfSuspected = 0;
        int nbOfNt = 0;

        for (ProcessResult pr : netResultList) {
            // l'audit est manual
            if (pr.getManualValue() != null) {
                TestSolution prResult = (TestSolution) pr.getManualValue();
                switch (prResult) {
                    case PASSED:
                        nbOfPassed++;
                        break;
                    case FAILED:
                        nbOfFailed++;
                        break;
                    case NOT_APPLICABLE:
                        nbOfNa++;
                        break;
                    case NEED_MORE_INFO:
                    case DETECTED:
                    case SUSPECTED_FAILED:
                    case SUSPECTED_PASSED:
                        nbOfNmi++;
                        break;
                    case NOT_TESTED:
                        nbOfNt++;
                        break;
                }
                addResultToCriterionCounterMap(prResult, pr.getTest().getCriterion(), wrStats, csMap);
                addResultToThemeCounterMap(prResult, pr.getTest().getCriterion().getTheme(), wrStats, tsMap);
            } else {
                addResultToCriterionCounterMap(TestSolution.NOT_TESTED, pr.getTest().getCriterion(), wrStats, csMap);
                addResultToThemeCounterMap(TestSolution.NOT_TESTED, pr.getTest().getCriterion().getTheme(), wrStats, tsMap);
                nbOfNt++;
            }

        }
        wrStats.setNbOfFailed(wrStats.getNbOfFailed() + nbOfFailed);
        wrStats.setNbOfInvalidTest(wrStats.getNbOfInvalidTest() + nbOfFailed);
        wrStats.setNbOfPassed(wrStats.getNbOfPassed() + nbOfPassed);
        wrStats.setNbOfNmi(wrStats.getNbOfNmi() + nbOfNmi);
        wrStats.setNbOfNa(wrStats.getNbOfNa() + nbOfNa);
        wrStats.setNbOfDetected(wrStats.getNbOfDetected() + nbOfDetected);
        wrStats.setNbOfSuspected(wrStats.getNbOfSuspected() + nbOfSuspected);
        wrStats.setNbOfNotTested(wrStats.getNbOfNotTested() + nbOfNt);

        setWeightedResult(wrStats, webResource);
        wrStats.setHttpStatusCode(getHttpStatusCodeByWebResource(webResource
                .getId()));

        wrStats = computeMark(wrStats);
        wrStats = computeRawMark(wrStats);
        wrStats = computeNumberOfFailedOccurrences(wrStats, webResource, true);

        wrStats.setAudit(audit);
        wrStats.setWebResource(webResource);
        wrStats.setIsManualAuditStatistics(1);

        // Compute criterion Result for each criterion and link each 
        // criterionStatistics to the current webResourceStatistics
        if (isNewWebRs) {
            for (CriterionStatistics cs : csMap.values()) {
                computeCriterionResult(cs);
                wrStats.addCriterionStatistics(cs);
            }
            // Link each themeStatistics to the current webResourceStatistics
            for (ThemeStatistics ts : tsMap.values()) {
                wrStats.addThemeStatistics(ts);
            }
        } else {
            //recuperer les Crtiterion à partir de wrstat et id_criterion pour MAJ
            for (CriterionStatistics css : csMap.values()) {
                CriterionStatistics criterionStatisticsFromMap = csMap.get(css.getCriterion());

                CriterionStatistics criterionStatisticsDb = criterionStatisticsDAO.findCriterionStatisticsByWebResource(css.getCriterion(), wrStats);
                if (criterionStatisticsDb == null) {
                    criterionStatisticsDb = criterionStatisticsDataService.create();
                    criterionStatisticsDb.setWebResourceStatistics(wrStats);
                    criterionStatisticsDb.setCriterion(css.getCriterion());
                }

                populateCriterionStatistics(criterionStatisticsDb, criterionStatisticsFromMap);
                computeCriterionResult(criterionStatisticsDb);
                criterionStatisticsDAO.saveOrUpdate(criterionStatisticsDb);
            }

            for (ThemeStatistics ts : tsMap.values()) {
                ThemeStatistics themeStatisticsFromMap = tsMap.get(ts.getTheme());

                ThemeStatistics themeStatisticsDb = themeStatisticsDAO.findThemeStatisticsByWebResource(ts.getTheme(), wrStats);
                if (themeStatisticsDb == null) {
                    themeStatisticsDb = themeStatisticsDataService.create();
                    themeStatisticsDb.setWebResourceStatistics(wrStats);
                    themeStatisticsDb.setTheme(ts.getTheme());
                }

                populateThemeStatistics(themeStatisticsDb, themeStatisticsFromMap);
                themeStatisticsDAO.saveOrUpdate(themeStatisticsDb);
            }
        }

        this.saveOrUpdate(wrStats);

        return wrStats;
    }

    /**
     * populateThemeStatistics
     *
     * @param themeStatisticsDb
     * @param themeStatisticsFromMap
     */
    private void populateThemeStatistics(ThemeStatistics themeStatisticsDb,
            ThemeStatistics themeStatisticsFromMap) {

        themeStatisticsDb.setNbOfDetected(themeStatisticsFromMap.getNbOfDetected());
        themeStatisticsDb.setNbOfFailed(themeStatisticsFromMap.getNbOfFailed());
        themeStatisticsDb.setNbOfNa(themeStatisticsFromMap.getNbOfNa());
        themeStatisticsDb.setNbOfNmi(themeStatisticsFromMap.getNbOfNmi());
        themeStatisticsDb.setNbOfNotTested(themeStatisticsFromMap.getNbOfNotTested());
        themeStatisticsDb.setNbOfPassed(themeStatisticsFromMap.getNbOfPassed());
        themeStatisticsDb.setNbOfSuspected(themeStatisticsFromMap.getNbOfSuspected());
    }

    /**
     * populateCriterionStatistics
     *
     * @param cssDb
     * @param criterionStatisticsFromMap
     */
    private void populateCriterionStatistics(CriterionStatistics cssDb,
            CriterionStatistics criterionStatisticsFromMap) {

        cssDb.setNbOfDetected(criterionStatisticsFromMap.getNbOfDetected());
        cssDb.setNbOfFailed(criterionStatisticsFromMap.getNbOfFailed());
        cssDb.setNbOfNa(criterionStatisticsFromMap.getNbOfNa());
        cssDb.setNbOfNmi(criterionStatisticsFromMap.getNbOfNmi());
        cssDb.setNbOfNotTested(criterionStatisticsFromMap.getNbOfNotTested());
        cssDb.setNbOfPassed(criterionStatisticsFromMap.getNbOfPassed());
        cssDb.setNbOfSuspected(criterionStatisticsFromMap.getNbOfSuspected());
    }

    /**
     * This computation is based on the priority of the results : - priority 1 :
     * Failed - priority 2 : NMI - priority 3 : Not Tested - priority 4 : Passed
     * - priority 5 : NA
     *
     * If at least one of the result type is found regarding the priority
     * definition, the criterion result is the result type
     *
     * @param crs
     * @param criterionTestListSize
     */
    private void computeCriterionResult(CriterionStatistics crs) {

        if (crs.getNbOfFailed() > 0) {  // at least one test is failed, the criterion is failed
            crs.setCriterionResult(TestSolution.FAILED);
        } else if (crs.getNbOfNmi() > 0) { // at least one test is nmi and no failed test encountered, the criterion is nmi
            crs.setCriterionResult(TestSolution.NEED_MORE_INFO);
        } else if (crs.getNbOfNotTested() > 0) {
            crs.setCriterionResult(TestSolution.NOT_TESTED);
        } else if (crs.getNbOfPassed() > 0) {
            crs.setCriterionResult(TestSolution.PASSED);
        } else if (crs.getNbOfNa() > 0) {
            crs.setCriterionResult(TestSolution.NOT_APPLICABLE);
        } else {
            crs.setCriterionResult(TestSolution.NEED_MORE_INFO);
        }
    }

    /**
     *
     * @param testSolution
     * @param criterion
     * @param wrs
     */
    private void addResultToCriterionCounterMap(
            TestSolution testSolution,
            Criterion criterion,
            WebResourceStatistics wrs,
            Map<Criterion, CriterionStatistics> csMap) {
        if (csMap.containsKey(criterion)) {
            CriterionStatistics cs = csMap.get(criterion);
            incrementCriterionCounterFromTestSolution(cs, testSolution);
        } else {
            CriterionStatistics cs = new CriterionStatisticsImpl();
            cs.setCriterion(criterion);
            incrementCriterionCounterFromTestSolution(cs, testSolution);
            csMap.put(criterion, cs);
        }
    }

    /**
     *
     * @param testSolution
     * @param criterion
     * @param wrs
     */
    private void addResultToThemeCounterMap(
            TestSolution testSolution,
            Theme theme,
            WebResourceStatistics wrs,
            Map<Theme, ThemeStatistics> tsMap) {
        if (tsMap.containsKey(theme)) {
            ThemeStatistics ts = tsMap.get(theme);
            incrementThemeCounterFromTestSolution(ts, testSolution);
        } else {
            ThemeStatistics ts = new ThemeStatisticsImpl();
            ts.setTheme(theme);
            incrementThemeCounterFromTestSolution(ts, testSolution);
            tsMap.put(theme, ts);
        }
    }

    /**
     *
     * @param ts
     * @param testSolution
     */
    private void incrementThemeCounterFromTestSolution(
            ThemeStatistics ts,
            TestSolution testSolution) {
        switch (testSolution) {
            case PASSED:
                ts.setNbOfPassed(ts.getNbOfPassed() + 1);
                break;
            case FAILED:
                ts.setNbOfFailed(ts.getNbOfFailed() + 1);
                break;
            case NOT_APPLICABLE:
                ts.setNbOfNa(ts.getNbOfNa() + 1);
                break;
            case NEED_MORE_INFO:
            case DETECTED:
            case SUSPECTED_FAILED:
            case SUSPECTED_PASSED:
                ts.setNbOfNmi(ts.getNbOfNmi() + 1);
                break;
            case NOT_TESTED:
                ts.setNbOfNotTested(ts.getNbOfNotTested() + 1);
                break;
        }
    }

    /**
     *
     * @param cs
     * @param testSolution
     */
    private void incrementCriterionCounterFromTestSolution(
            CriterionStatistics cs,
            TestSolution testSolution) {
        switch (testSolution) {
            case PASSED:
                cs.setNbOfPassed(cs.getNbOfPassed() + 1);
                break;
            case FAILED:
                cs.setNbOfFailed(cs.getNbOfFailed() + 1);
                break;
            case NOT_APPLICABLE:
                cs.setNbOfNa(cs.getNbOfNa() + 1);
                break;
            case NEED_MORE_INFO:
            case DETECTED:
            case SUSPECTED_FAILED:
            case SUSPECTED_PASSED:
                cs.setNbOfNmi(cs.getNbOfNmi() + 1);
                break;
            case NOT_TESTED:
                cs.setNbOfNotTested(cs.getNbOfNotTested() + 1);
                break;
        }
    }

    public CriterionStatisticsDAO getCriterionStatisticsDAO() {
        return criterionStatisticsDAO;
    }

    public void setCriterionStatisticsDAO(
            CriterionStatisticsDAO criterionStatisticsDAO) {
        this.criterionStatisticsDAO = criterionStatisticsDAO;
    }

    public ThemeStatisticsDAO getThemeStatisticsDAO() {
        return themeStatisticsDAO;
    }

    public void setThemeStatisticsDAO(ThemeStatisticsDAO themeStatisticsDAO) {
        this.themeStatisticsDAO = themeStatisticsDAO;
    }
}
