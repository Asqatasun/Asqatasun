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
package org.opens.tanaguru.analyser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.reference.TestDataService;
import org.opens.tanaguru.entity.service.statistics.CriterionStatisticsDataService;
import org.opens.tanaguru.entity.service.statistics.TestStatisticsDataService;
import org.opens.tanaguru.entity.service.statistics.ThemeStatisticsDataService;
import org.opens.tanaguru.entity.service.statistics.WebResourceStatisticsDataService;
import org.opens.tanaguru.entity.statistics.CriterionStatistics;
import org.opens.tanaguru.entity.statistics.TestStatistics;
import org.opens.tanaguru.entity.statistics.ThemeStatistics;
import org.opens.tanaguru.entity.statistics.WebResourceStatistics;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author jkowalczyk
 */
public class AnalyserImpl implements Analyser {

    /**
     * The webResource used to extract statistics
     */
    private WebResource webResource;

    /**
     * The audit used to extract statistics
     */
    private Audit audit;

    /**
     * The auditStatisticsDataService instance needed to retrieve and save
     * auditStatistics instances
     */
    private WebResourceStatisticsDataService webResourceStatisticsDataService;
    public WebResourceStatisticsDataService getWebResourceStatisticsDataService() {
        return webResourceStatisticsDataService;
    }

    public void setWebResourceStatisticsDataService(
            WebResourceStatisticsDataService webResourceStatisticsDataService) {
        this.webResourceStatisticsDataService = webResourceStatisticsDataService;
    }

    /**
     * The ThemeStatisticsDataService instance
     */
    private ThemeStatisticsDataService themeStatisticsDataService;
    public ThemeStatisticsDataService getThemeStatisticsDataService() {
        return themeStatisticsDataService;
    }

    public void setThemeStatisticsDataService(
            ThemeStatisticsDataService themeStatisticsDataService) {
    }
    
    /**
     * The CriterionStatisticsDataService instance
     */
    private CriterionStatisticsDataService criterionStatisticsDataService;
    public CriterionStatisticsDataService getCriterionStatisticsDataService() {
        return criterionStatisticsDataService;
    }

    public void setCriterionStatisticsDataService(
            CriterionStatisticsDataService criterionStatisticsDataService) {
    }

    /**
     * THe testStatisticsDataService instance
     */
    private TestStatisticsDataService testStatisticsDataService;
    public TestStatisticsDataService getTestStatisticsDataService() {
        return testStatisticsDataService;
    }

    public void setTestStatisticsDataService(
            TestStatisticsDataService testStatisticsDataService) {
    }

    /**
     * The auditDataService instance
     */
    private AuditDataService auditDataService;
    public AuditDataService getAuditDataService() {
        return auditDataService;
    }

    public void setAuditDataService(AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }
    
    /**
     * The testDataService instance
     */
    private TestDataService testDataService;
    public TestDataService getTestDataService() {
        return testDataService;
    }

    public void setTestDataService(TestDataService testDataService) {
        this.testDataService = testDataService;
    }

    private Map<Criterion, Integer> criterionMap;
    private Map<Theme, Integer> themeMap;
    private Collection<Test> testSet;

    /**
     * This attribute is used to compute the number of not tested tests. In case 
     * of Page audit, this value is supposed to be 1 and thus, the not tested value
     * is computed from the difference between the total number of tests for the given 
     * level and the qualified results.
     * In case of Site audit, the total number of tests (or criterions) has to 
     * multiplied by this value before substracting the qualified results.
     * 
     */
    private int nbOfWr=0;
    
    /**
     * the set of audit parameters that handles some overridden values for
     * test weight (needed to compute the raw mark)
     */
    private Collection<Parameter> paramSet;

    private static BigDecimal ZERO = BigDecimal.valueOf(Double.valueOf(0.0));

    
    public AnalyserImpl(
            AuditDataService auditDataService,
            TestDataService testDataService,
            TestStatisticsDataService testStatisticsDataService,
            ThemeStatisticsDataService themeStatisticsDataService,
            WebResourceStatisticsDataService webResourceStatisticsDataService,
            CriterionStatisticsDataService criterionStatisticsDataService,
            WebResource webResource,
            Collection<Parameter> paramSet, 
            int nbOfWr) {
        this.auditDataService = auditDataService;
        this.testDataService = testDataService;
        this.testStatisticsDataService = testStatisticsDataService;
        this.themeStatisticsDataService = themeStatisticsDataService;
        this.webResourceStatisticsDataService = webResourceStatisticsDataService;
        this.criterionStatisticsDataService = criterionStatisticsDataService;
        this.setWebResource(webResource);
        this.paramSet = paramSet;
        this.nbOfWr = nbOfWr;
    }

    @Override
    public List<ProcessResult> getNetResultList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getResult() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void run() {
        WebResourceStatistics wrStats = webResourceStatisticsDataService.create();
        extractTestSet();
        
        wrStats = computeAuditStatistics(wrStats);
        wrStats = computeMark(wrStats);
        wrStats = computeRawMark(wrStats);
        wrStats = computeNumberOfFailedOccurrences(wrStats);
        wrStats = computeThemeStatistics(wrStats);
        wrStats = computeCriterionStatistics(wrStats);

        if (webResource instanceof Site) {
            wrStats = computeTestStatistics(wrStats);
        } else {
            wrStats = computeHttpStatusCode(wrStats);
        }
        wrStats.setAudit(audit);
        wrStats.setWebResource(webResource);
        webResourceStatisticsDataService.saveOrUpdate(wrStats);
    }

    @Override
    public void setNetResultList(List<ProcessResult> netResultList) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public final void setWebResource(WebResource webResource) {
        this.webResource = webResource;
        if (webResource instanceof Site) {
            this.audit = webResource.getAudit();
        } else if (webResource instanceof Page) {
            if (webResource.getAudit() != null) {
                this.audit = webResource.getAudit();
            } else if (webResource.getParent() != null) {
                this.audit = webResource.getParent().getAudit();
            }
        }
    }

    @Override
    public WebResource getWebResource() {
        return webResource;
    }

    /**
     * Gather the Http status code for a given web resource.
     *
     * @param wrStatistics
     * @return
     */
    private WebResourceStatistics computeHttpStatusCode (WebResourceStatistics wrStatistics) {
        wrStatistics.setHttpStatusCode(webResourceStatisticsDataService.
                getHttpStatusCodeByWebResource(webResource.getId()));
        return wrStatistics;
    }

    /**
     * Gather the audit statistics informations :
     * - Number of passed results
     * - Number of failed results
     * - Number of need_more_information results
     * - Number of not applicable results
     * - Number of failed tests
     *
     * @param wrStatistics
     * @return
     */
    private WebResourceStatistics computeAuditStatistics (WebResourceStatistics wrStatistics) {
        int nbOfPassed = webResourceStatisticsDataService.
                    getResultCountByResultType(webResource.getId(),
                    TestSolution.PASSED).intValue();

        int nbOfFailed = webResourceStatisticsDataService.
                    getResultCountByResultType(webResource.getId(),
                    TestSolution.FAILED).intValue();

        int nbOfNmi = webResourceStatisticsDataService.
                    getResultCountByResultType(webResource.getId(),
                    TestSolution.NEED_MORE_INFO).intValue();
        
        int nbOfNa = webResourceStatisticsDataService.
                    getResultCountByResultType(webResource.getId(),
                    TestSolution.NOT_APPLICABLE).intValue();
        
        int nbOfDetected = webResourceStatisticsDataService.
                    getResultCountByResultType(webResource.getId(),
                    TestSolution.DETECTED).intValue();
        
        int nbOfSuspected = webResourceStatisticsDataService.
                    getResultCountByResultType(webResource.getId(),
                    TestSolution.SUSPECTED_FAILED).intValue() + 
                    webResourceStatisticsDataService.
                    getResultCountByResultType(webResource.getId(),
                    TestSolution.SUSPECTED_PASSED).intValue();
        
        BigDecimal weightedPassed = webResourceStatisticsDataService.
                    getWeightedResultByResultType(
                        webResource.getId(),
                        paramSet,
                        TestSolution.PASSED);
        
        BigDecimal weightedFailed = webResourceStatisticsDataService.
                    getWeightedResultByResultType(
                        webResource.getId(),
                        paramSet,
                        TestSolution.FAILED);
        
        BigDecimal weightedNa = webResourceStatisticsDataService.
                    getWeightedResultByResultType(
                        webResource.getId(),
                        paramSet,
                        TestSolution.NOT_APPLICABLE);
        
        BigDecimal weightedNmi = webResourceStatisticsDataService.
                    getWeightedResultByResultType(
                        webResource.getId(),
                        paramSet,
                        TestSolution.NEED_MORE_INFO);
        // if no test have been processed for any reason, mostly cause the source
        // code couldn't have been adapted, all theses values are set to -1
        if (nbOfFailed+nbOfNa+nbOfNmi+nbOfPassed+nbOfDetected+nbOfSuspected == 0) {
            nbOfFailed = nbOfNa = nbOfNmi = nbOfPassed = nbOfSuspected = nbOfDetected = -1;
        }

        if (weightedFailed.add(weightedNa).add(weightedPassed).add(weightedNmi).equals(BigDecimal.ZERO)) {
            nbOfFailed = nbOfNa = nbOfNmi = nbOfPassed = -1;
        }

        wrStatistics.setNbOfFailed(nbOfFailed);
        wrStatistics.setNbOfInvalidTest(nbOfFailed);
        wrStatistics.setNbOfPassed(nbOfPassed);
        wrStatistics.setNbOfNmi(nbOfNmi);
        wrStatistics.setNbOfNa(nbOfNa);
        wrStatistics.setNbOfDetected(nbOfDetected);
        wrStatistics.setNbOfSuspected(nbOfSuspected);
        wrStatistics.setNbOfNotTested(
                testSet.size()*nbOfWr 
                - nbOfDetected 
                - nbOfSuspected 
                - nbOfFailed 
                - nbOfNa 
                - nbOfNmi 
                - nbOfPassed);
        wrStatistics.setWeightedFailed(weightedFailed);
        wrStatistics.setWeightedPassed(weightedPassed);
        wrStatistics.setWeightedNmi(weightedNmi);
        wrStatistics.setWeightedNa(weightedNa);
        
        wrStatistics.setAudit(audit);
        return wrStatistics;
    }

    /**
     * This method compute the mark of the audit.
     * Here is the algorithm formula :
     * ((1-ratioNMI) * passed/(passed+failed) + ratioNMI * needMoreInfo/(passed+failed+needMoreInfo)) *100f
     * where ratioNMI = needMoreInfo / (passed+failed+needMoreInfo)
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
        if (needMoreInfo == 0 && failed ==0 && passed==0) {
            wrStatistics.setMark(Float.valueOf(0));
            return wrStatistics;
        }
        float ratioNMI = needMoreInfo / (passed+failed+needMoreInfo);
        float result = ((1-ratioNMI) * passed/(passed+failed) + ratioNMI * needMoreInfo/(passed+failed+needMoreInfo)) *100f;
        wrStatistics.setMark(result);
        return wrStatistics;
    }

    /**
     * This method compute the raw mark of the audit.
     * Here is the algorithm formula :
     * passed/(passed+failed)
     * @param wrStatistics
     * @return
     */
    public WebResourceStatistics computeRawMark(WebResourceStatistics wrStatistics) {
        float passed = wrStatistics.getNbOfPassed();
        // page on error, mark set to -1
        if (passed == -1) {
            wrStatistics.setRawMark(Float.valueOf(-1));
            return wrStatistics;
        }
        BigDecimal weightedPassed = wrStatistics.getWeightedPassed();
        BigDecimal weightedFailed = wrStatistics.getWeightedFailed();
        if ((weightedFailed.equals(BigDecimal.ZERO) || weightedFailed.equals(ZERO))
                && (weightedPassed.equals(BigDecimal.ZERO) || weightedPassed.equals(ZERO))) {
            wrStatistics.setRawMark(Float.valueOf(0));
            return wrStatistics;
        }

        float result = weightedPassed.divide(weightedPassed.add(weightedFailed), 4, RoundingMode.HALF_UP).floatValue() *100f;
        wrStatistics.setRawMark(result);
        return wrStatistics;
    }

    /**
     * Gather the number of failed occurrence for a given web resource.
     * @param wrStatistics
     * @return
     */
    private WebResourceStatistics computeNumberOfFailedOccurrences(WebResourceStatistics wrStatistics) {
        int nbOfFailedOccurences =
                webResourceStatisticsDataService.getNumberOfOccurrencesByWebResourceAndResultType(
                    webResource.getId(),
                    TestSolution.FAILED).intValue();
        wrStatistics.setNbOfFailedOccurences(nbOfFailedOccurences);
        return wrStatistics;
    }

    /**
     *
     * @param wrStatistics
     * @return
     */
    private WebResourceStatistics computeThemeStatistics(WebResourceStatistics wrStatistics) {
        for (Theme theme : themeMap.keySet()) {
            ThemeStatistics themeStatistics = themeStatisticsDataService.create();
            themeStatistics.setTheme(theme);
            
            int nbOfFailed = themeStatisticsDataService.
                    getResultCountByResultTypeAndTheme(webResource, TestSolution.FAILED, theme).intValue();
            themeStatistics.setNbOfFailed(nbOfFailed);
            
            int nbOfPassed = themeStatisticsDataService.
                    getResultCountByResultTypeAndTheme(webResource, TestSolution.PASSED, theme).intValue();
            themeStatistics.setNbOfPassed(nbOfPassed);
            
            int nbOfNa = themeStatisticsDataService.
                    getResultCountByResultTypeAndTheme(webResource, TestSolution.NOT_APPLICABLE, theme).intValue();
            themeStatistics.setNbOfNa(nbOfNa);
            
            int nbOfNmi = themeStatisticsDataService.
                    getResultCountByResultTypeAndTheme(webResource, TestSolution.NEED_MORE_INFO, theme).intValue();
            nbOfNmi += themeStatisticsDataService.
                    getResultCountByResultTypeAndTheme(webResource, TestSolution.SUSPECTED_FAILED, theme).intValue();
            nbOfNmi += themeStatisticsDataService.
                    getResultCountByResultTypeAndTheme(webResource, TestSolution.SUSPECTED_PASSED, theme).intValue();
            themeStatistics.setNbOfNmi(nbOfNmi);
            
            int themeTestListSize = themeMap.get(theme);
            
            themeStatistics.setNbOfNotTested(
                    themeTestListSize*nbOfWr 
                    - nbOfFailed 
                    - nbOfNa 
                    - nbOfNmi 
                    - nbOfPassed);
            
            wrStatistics.addThemeStatistics(themeStatistics);
        }
        return wrStatistics;
    }
    
    /**
     *
     * @param wrStatistics
     * @return
     */
    private WebResourceStatistics computeCriterionStatistics(WebResourceStatistics wrStatistics) {
        for (Criterion cr : criterionMap.keySet()) {
            CriterionStatistics criterionStatistics = criterionStatisticsDataService.create();
            criterionStatistics.setCriterion(cr);
            
            int nbOfFailed = criterionStatisticsDataService.
                    getResultCountByResultTypeAndCriterion(webResource, TestSolution.FAILED, cr).intValue();
            criterionStatistics.setNbOfFailed(nbOfFailed);
            
            int nbOfNa = criterionStatisticsDataService.
                    getResultCountByResultTypeAndCriterion(webResource, TestSolution.NOT_APPLICABLE, cr).intValue();
            criterionStatistics.setNbOfNa(nbOfNa);
            
            int nbOfPassed = criterionStatisticsDataService.
                    getResultCountByResultTypeAndCriterion(webResource, TestSolution.PASSED, cr).intValue();
            criterionStatistics.setNbOfPassed(nbOfPassed);
            
            int nbOfNmi = criterionStatisticsDataService.
                    getResultCountByResultTypeAndCriterion(webResource, TestSolution.NEED_MORE_INFO, cr).intValue();
            nbOfNmi += criterionStatisticsDataService.
                    getResultCountByResultTypeAndCriterion(webResource, TestSolution.SUSPECTED_FAILED, cr).intValue();
            nbOfNmi += criterionStatisticsDataService.
                    getResultCountByResultTypeAndCriterion(webResource, TestSolution.SUSPECTED_PASSED, cr).intValue();
            nbOfNmi += criterionStatisticsDataService.
                    getResultCountByResultTypeAndCriterion(webResource, TestSolution.DETECTED, cr).intValue();
            criterionStatistics.setNbOfNmi(nbOfNmi);
            
            int criterionTestListSize = criterionMap.get(cr);
            criterionStatistics.setNbOfNotTested(
                    criterionTestListSize*nbOfWr 
                    - nbOfFailed 
                    - nbOfNa 
                    - nbOfNmi 
                    - nbOfPassed);
            computeCriterionResult(criterionStatistics, criterionTestListSize);
            wrStatistics.addCriterionStatistics(criterionStatistics);
        }
        return wrStatistics;
    }

    /**
     * * Gather the following statistics informations for each theme:
     * - Number of passed results
     * - Number of failed results
     * - Number of need_more_information results
     * - Number of not applicable results
     * - Number of failed tests
     * @param wrStatistics
     * @return
     */
    private WebResourceStatistics computeTestStatistics(WebResourceStatistics wrStatistics) {
        for (Test test : testSet) {
            TestStatistics testStatistics = testStatisticsDataService.create();
            testStatistics.setTest(test);
            
            int nbOfFailed = testStatisticsDataService.
                    getResultCountByResultTypeAndTest(webResource, TestSolution.FAILED, test).intValue();
            testStatistics.setNbOfFailed(nbOfFailed);
            
            int nbOfPassed = testStatisticsDataService.
                    getResultCountByResultTypeAndTest(webResource, TestSolution.PASSED, test).intValue();
            testStatistics.setNbOfFailed(nbOfPassed);
            
            int nbOfNmi = testStatisticsDataService.
                    getResultCountByResultTypeAndTest(webResource, TestSolution.NEED_MORE_INFO, test).intValue();
            testStatistics.setNbOfNmi(nbOfNmi);
            
            int nbOfNa = testStatisticsDataService.
                    getResultCountByResultTypeAndTest(webResource, TestSolution.NOT_APPLICABLE, test).intValue();
            testStatistics.setNbOfNa(nbOfNa);

            testStatistics.setNbOfNotTested(nbOfWr - nbOfFailed - nbOfPassed - nbOfNmi - nbOfNa);

            wrStatistics.addTestStatistics(testStatistics);
        }
        return wrStatistics;
    }

    /**
     * This method extracts a collection of themes for a given audit
     * 
     * @return
     */
    private void extractThemeAndCriterionSet() {
        themeMap = new HashMap<Theme, Integer>();
        criterionMap = new HashMap<Criterion, Integer>();
        for (Test test : testSet) {
            
            //Collect criterions given the set of tests for the audit, and keep
            // the number of tests for each criterion (needed to calculate the
            // not tested
            Criterion criterion = test.getCriterion();
            if (criterionMap.containsKey(criterion)) {
                Integer testCounter = criterionMap.get(criterion)+1;
                criterionMap.put(criterion, testCounter);
            } else {
                criterionMap.put(criterion, Integer.valueOf(1));
            }
            
            //Collect themes given the set of tests for the audit, and keep
            // the number of tests for each criterion (needed to calculate the
            // not tested
            Theme theme = criterion.getTheme();
            if (themeMap.containsKey(theme)) {
                Integer testCounter = themeMap.get(theme)+1;
                themeMap.put(theme, testCounter);
            } else {
                themeMap.put(theme, Integer.valueOf(1));
            }

        }
    }

    /**
     * This method extracts a collection of tests for a given audit
     * @return
     */
    private void extractTestSet() {
        testSet = new HashSet<Test>();
        testSet.addAll(auditDataService.getAuditWithTest(this.audit.getId()).getTestList());
        extractThemeAndCriterionSet();
    }

    /**
     * 
     * @param crs
     * @param criterionTestListSize 
     */
    private void computeCriterionResult (CriterionStatistics crs, int criterionTestListSize) {
        if (crs.getNbOfFailed() > 0) {
            crs.setCriterionResult(TestSolution.FAILED);
        } else if (crs.getNbOfNmi() > 0) {
            crs.setCriterionResult(TestSolution.NEED_MORE_INFO);
        } else if (crs.getNbOfPassed() == criterionTestListSize) {
            crs.setCriterionResult(TestSolution.PASSED);
        } else if (crs.getNbOfNa() == criterionTestListSize) {
            crs.setCriterionResult(TestSolution.NOT_APPLICABLE);
        } else if (crs.getNbOfNotTested() == criterionTestListSize) {
            crs.setCriterionResult(TestSolution.NOT_TESTED);
        } else {
            crs.setCriterionResult(TestSolution.NEED_MORE_INFO);
        }
    }

}