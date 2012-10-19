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
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.statistics.TestStatisticsDataService;
import org.opens.tanaguru.entity.service.statistics.ThemeStatisticsDataService;
import org.opens.tanaguru.entity.service.statistics.WebResourceStatisticsDataService;
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
     * the set of audit parameters that handles some overridden values for
     * test weight (needed to compute the raw mark)
     */
    private Collection<Parameter> paramSet;

    private static BigDecimal ZERO = BigDecimal.valueOf(Double.valueOf(0.0));
    
    public AnalyserImpl(
            AuditDataService auditDataService,
            TestStatisticsDataService testStatisticsDataService,
            ThemeStatisticsDataService themeStatisticsDataService,
            WebResourceStatisticsDataService webResourceStatisticsDataService,
            WebResource webResource,
            Collection<Parameter> paramSet) {
        this.auditDataService = auditDataService;
        this.testStatisticsDataService = testStatisticsDataService;
        this.themeStatisticsDataService = themeStatisticsDataService;
        this.webResourceStatisticsDataService = webResourceStatisticsDataService;
        this.setWebResource(webResource);
        this.paramSet = paramSet;
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
        wrStats = computeAuditStatistics(wrStats);
        wrStats = computeMark(wrStats);
        wrStats = computeRawMark(wrStats);
        wrStats = computeNumberOfFailedOccurrences(wrStats);
        wrStats = computeThemeStatistics(wrStats);
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
        
        if (nbOfFailed+nbOfNa+nbOfNmi+nbOfPassed == 0) {
            nbOfFailed = nbOfNa = nbOfNmi = nbOfPassed = -1;
        }
        if (weightedFailed.add(weightedNa).add(weightedPassed).add(weightedFailed).equals(BigDecimal.ZERO)) {
            nbOfFailed = nbOfNa = nbOfNmi = nbOfPassed = -1;
        }
        
        wrStatistics.setNbOfFailed(nbOfFailed);
        wrStatistics.setNbOfInvalidTest(nbOfFailed);
        wrStatistics.setNbOfPassed(nbOfPassed);
        wrStatistics.setNbOfNmi(nbOfNmi);
        wrStatistics.setNbOfNa(nbOfNa);
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
        Collection<Theme> themeSet = extractThemeFromAudit();
        for (Theme theme : themeSet) {
            ThemeStatistics themeStatistics = themeStatisticsDataService.create();
            themeStatistics.setTheme(theme);
            themeStatistics.setNbOfFailed(themeStatisticsDataService.
                    getResultCountByResultTypeAndTheme(webResource, TestSolution.FAILED, theme).intValue());
            themeStatistics.setNbOfNa(themeStatisticsDataService.
                    getResultCountByResultTypeAndTheme(webResource, TestSolution.NOT_APPLICABLE, theme).intValue());
            themeStatistics.setNbOfPassed(themeStatisticsDataService.
                    getResultCountByResultTypeAndTheme(webResource, TestSolution.PASSED, theme).intValue());
            themeStatistics.setNbOfNmi(themeStatisticsDataService.
                    getResultCountByResultTypeAndTheme(webResource, TestSolution.NEED_MORE_INFO, theme).intValue());
            wrStatistics.addThemeStatistics(themeStatistics);
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
        Collection<? extends Test> testSet = extractTestFromAudit();
        for (Test test : testSet) {
            TestStatistics testStatistics = testStatisticsDataService.create();
            testStatistics.setTest(test);
            testStatistics.setNbOfFailed(testStatisticsDataService.
                    getResultCountByResultTypeAndTest(webResource, TestSolution.FAILED, test).intValue());
            testStatistics.setNbOfNa(testStatisticsDataService.
                    getResultCountByResultTypeAndTest(webResource, TestSolution.NOT_APPLICABLE, test).intValue());
            testStatistics.setNbOfPassed(testStatisticsDataService.
                    getResultCountByResultTypeAndTest(webResource, TestSolution.PASSED, test).intValue());
            testStatistics.setNbOfNmi(testStatisticsDataService.
                    getResultCountByResultTypeAndTest(webResource, TestSolution.NEED_MORE_INFO, test).intValue());
            wrStatistics.addTestStatistics(testStatistics);
        }
        return wrStatistics;
    }

    /**
     * This method extracts a collection of themes for a given audit
     * @return
     */
    private Collection<Theme> extractThemeFromAudit() {
        Audit auditWithTest = auditDataService.getAuditWithTest(this.audit.getId());
        Set<Theme> themeSet = new HashSet<Theme>();
        for (Test test : auditWithTest.getTestList()) {
            themeSet.add(test.getCriterion().getTheme());
        }
        return themeSet;
    }

    /**
     * This method extracts a collection of tests for a given audit
     * @return
     */
    private Collection<Test> extractTestFromAudit() {
        Audit auditWithTest = auditDataService.getAuditWithTest(this.audit.getId());
        return auditWithTest.getTestList();
    }

}