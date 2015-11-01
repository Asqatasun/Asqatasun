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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.tanaguru.entity.audit.*;
import org.tanaguru.entity.reference.Criterion;
import org.tanaguru.entity.reference.Scope;
import org.tanaguru.entity.reference.Test;
import org.tanaguru.entity.reference.Theme;
import org.tanaguru.entity.service.audit.AuditDataService;
import org.tanaguru.entity.service.audit.ProcessRemarkDataService;
import org.tanaguru.entity.service.audit.ProcessResultDataService;
import org.tanaguru.entity.service.reference.TestDataService;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.webapp.presentation.data.ManualResult;
import org.tanaguru.webapp.presentation.data.RemarkInfos;
import org.tanaguru.webapp.presentation.data.TestResult;
import org.tanaguru.webapp.presentation.data.TestResultImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public final class TestResultFactory {

    private static final String NOT_TESTED_STR = "NOT_TESTED";

    private final ResourceBundle representationBundle =
            ResourceBundle.getBundle(TestResult.REPRESENTATION_BUNDLE_NAME);

    private ProcessRemarkDataService processRemarkDataService;
    @Autowired
    public void setProcessRemarkDataService(ProcessRemarkDataService processRemarkDataService) {
        this.processRemarkDataService = processRemarkDataService;
    }

    private AuditDataService auditDataService;
    @Autowired
    public void setAuditDataService(AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }

    private ProcessResultDataService processResultDataService;
    @Autowired
    public void setProcessResultDataService(ProcessResultDataService processDataService) {
        this.processResultDataService = processDataService;
    }

    private TestDataService testDataService;
    @Autowired
    public void setTestDataService(TestDataService testDataService) {
        this.testDataService = testDataService;
    }
    
    private String selectAllThemeKey;
    public String getSelectAllThemeKey() {
        return selectAllThemeKey;
    }

    public void setSelectAllThemeKey(String selectAllThemeKey) {
        this.selectAllThemeKey = selectAllThemeKey;
    }
    /**
     * The unique shared instance of TestResultFactory
     */
    private static TestResultFactory testResultFactory;

    /**
     * Default private constructor
     */
    private TestResultFactory() {
    }

    public static synchronized TestResultFactory getInstance() {
        if (testResultFactory == null) {
            testResultFactory = new TestResultFactory();
        }
        return testResultFactory;
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
     * @param processResult
     * @param hasResultDetails
     * @param truncatable
     * @return an instance of ProcessResult
     */
    public TestResult getTestResult(
            ProcessResult processResult,
            boolean hasResultDetails,
            boolean truncatable) {
        TestResult testResult = new TestResultImpl();
        testResult.setTestUrl(processResult.getTest().getDescription());
        testResult.setTestShortLabel(processResult.getTest().getLabel());
        testResult.setTestCode(processResult.getTest().getCode());
        testResult.setLevelCode(processResult.getTest().getLevel().getCode());
        testResult.setResult(processResult.getValue().toString());
        testResult.setResultCode(setResultToLowerCase(processResult, testResult));
        testResult.setRuleDesignUrl(processResult.getTest().getRuleDesignUrl());
        testResult.setResultCounter(ResultCounterFactory.getInstance().getResultCounter());
        testResult.setTest(processResult.getTest());
        testResult.setHistory(constructHistoryChanges(processResult));
        if (processResult instanceof DefiniteResult && processResult.getManualValue() != null) {
            testResult.setComment(((DefiniteResult) processResult).getManualAuditComment());
            switch ((TestSolution)processResult.getManualValue()) {
                case FAILED : 
                    testResult.setManualResult(TestResult.FAILED_LOWER);
                    break;
                case PASSED : 
                    testResult.setManualResult(TestResult.PASSED_LOWER);
                    break;
                case NOT_APPLICABLE :
                    testResult.setManualResult(TestResult.NOT_APPLICABLE_LOWER);
                    break;
            } 
        }
        try {
            testResult.setTestRepresentation(Integer.valueOf(representationBundle.
                    getString(testResult.getTestCode() + TestResult.REPRESENTATION_SUFFIX_KEY)));
        } catch (MissingResourceException mre) {
            Logger.getLogger(this.getClass()).warn(mre);
        }
        try {
            testResult.setTestEvidenceRepresentationOrder(representationBundle.
                    getString(testResult.getTestCode() + TestResult.REPRESENTATION_ORDER_SUFFIX_KEY));
        } catch (MissingResourceException mre) {
            Logger.getLogger(this.getClass()).warn(mre);
        }
        try {
            testResult.setColorTestContrastRatio(representationBundle.
                    getString(testResult.getTestCode() + TestResult.CONTRAST_RATIO_SUFFIX_KEY));
        } catch (MissingResourceException mre) {
            Logger.getLogger(this.getClass()).warn(mre);
        }
        if (hasResultDetails
                && (testResult.getResult().equalsIgnoreCase(TestSolution.FAILED.toString())
                || testResult.getResult().equalsIgnoreCase(TestSolution.NEED_MORE_INFO.toString()))) {
            addRemarkInfosMapToTestResult(testResult, processResult, truncatable);
        }
        return testResult;
    }

    private List<DefiniteResult> constructHistoryChanges(
            ProcessResult processResult) {

        List<DefiniteResult> histoyChanges = processResultDataService.getHistoyChanges(processResult);
        return histoyChanges;

    }

    /**
     *
     * @param webresource
     * @param scope
     * @param theme
     * @param testSolutionList
     * @return A map of themes with for each theme the list of TestResult
     */
    public Map<Theme, List<TestResult>> getTestResultSortedByThemeMap(
            WebResource webresource,
            Scope scope,
            String theme,
            Collection<String> testSolutionList) {

        List<ProcessResult> effectiveNetResultList = (List<ProcessResult>) processResultDataService.
                getProcessResultListByWebResourceAndScope(webresource, scope, theme, testSolutionList);
        // The not tested tests are not persisted but deduced from the testResultList
        // If the not_tested solution is requested to be displayed, we add fake
        // processResult to the current list.

        if (testSolutionList.contains(NOT_TESTED_STR)) {
            List<ProcessResult> netResultList = (List<ProcessResult>) processResultDataService.
                    getProcessResultListByWebResourceAndScope(webresource, scope);
            effectiveNetResultList.addAll(addNotTestedProcessResult(getTestListFromWebResource(webresource), theme, netResultList));
        }

        return prepareThemeResultMap(effectiveNetResultList);
    }

    /**
     *
     * @param netResultList
     * @return
     */
    private Map<Theme, List<TestResult>> prepareThemeResultMap(
            List<ProcessResult> netResultList) {

        // Map that associates a list of results with a theme
        Map<Theme, List<TestResult>> testResultMap = new LinkedHashMap();

        sortCollection(netResultList);
        for (ProcessResult processResult : netResultList) {
            if (processResult instanceof DefiniteResult) {
                TestResult testResult = getTestResult(
                        processResult,
                        true,
                        true);
                Theme theme
                        = processResult.getTest().getCriterion().getTheme();

                if (testResultMap.containsKey(theme)) {
                    testResultMap.get(theme).add(testResult);
                } else {
                    List<TestResult> testResultList = new ArrayList();
                    testResultList.add(testResult);
                    testResultMap.put(theme, testResultList);
                }
            }
        }
        return testResultMap;
    }

    /**
     *
     * @param webresource
     * @param scope
     * @param locale
     * @return the test result list without user filter (used for the export
     * function)
     */
    public List<TestResult> getTestResultList(
            WebResource webresource,
            Scope scope,
            Locale locale) {
        // Map that associates a list of results with a theme
        List<TestResult> testResultList = new LinkedList();
        List<ProcessResult> netResultList = (List<ProcessResult>) processResultDataService.
                getProcessResultListByWebResourceAndScope(webresource, scope);

        netResultList.addAll(
                addNotTestedProcessResult(
                        getTestListFromWebResource(webresource),
                        selectAllThemeKey,
                        netResultList));

        sortCollection(netResultList);
        for (ProcessResult processResult : netResultList) {
            if (processResult instanceof DefiniteResult) {
                TestResult testResult = getTestResult(
                        processResult,
                        true,
                        false);
                testResultList.add(testResult);
            }
        }
        return testResultList;
    }

    // TO DO : determine the difference between this method and the next one
    // The code seems to be a duplication
    public List<ProcessResult> getProcessResultListFromTestsResult(Map<String, ManualResult> overriddenResultMap, WebResource webResource) {
        List<ProcessResult> processResultList = new LinkedList();
        Audit audit = webResource.getAudit();
        
        for (Map.Entry<String, ManualResult> entry : overriddenResultMap.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue().getResult())) {
                Test test = testDataService.getTestFromAuditAndLabel(audit, entry.getKey());
                List<ProcessResult> prList = ((List<ProcessResult>) processResultDataService.getProcessResultListByWebResourceAndTest(webResource, test));
                /**
                 * If returned process result list doesn't contains elements
                 * that means the test is NOT_TESTED and has to be created to
                 * persist the manual audit value
                 */
                ProcessResult processResult;
                if (prList.isEmpty()) {
                    processResult = createNotTestedProcessResult(test, webResource);
                } else {
                    processResult = prList.get(0);
                }
                processResult.setManualValue(
                        entry.getValue().getResult().equals(TestResult.FAILED_LOWER) ? TestSolution.FAILED :
                        (entry.getValue().getResult().equals(TestResult.PASSED_LOWER) ? TestSolution.PASSED :
                        TestSolution.NOT_APPLICABLE));
                if (StringUtils.isNotBlank(entry.getValue().getComment())) {
                    ((DefiniteResult) processResult).setManualAuditComment(entry.getValue().getComment());
                }
                processResultList.add(processResult);
            }
        }
        return processResultList;
    }

    /**
     * 
     * @param overriddenResultMap
     * @param webResource
     * @return 
     */
    public List<ProcessResult> getAllProcessResultListFromTestsResult(
            Map<String, ManualResult> overriddenResultMap, 
            WebResource webResource) {
        List<ProcessResult> processResultList = new LinkedList();
        Audit audit = webResource.getAudit();
        for (Map.Entry<String, ManualResult> entry : overriddenResultMap.entrySet()) {

            Test test = testDataService.getTestFromAuditAndLabel(audit, entry.getKey());
            List<ProcessResult> prList = ((List<ProcessResult>) processResultDataService.getProcessResultListByWebResourceAndTest(webResource, test));
            /**
             * If returned process result list doesn't contains elements that
             * means the test is NOT_TESTED and has to be created to persist the
             * manual audit value
             */
            ProcessResult processResult;
            if (prList.isEmpty()) {
                processResult = createNotTestedProcessResult(test, webResource);
            } else {
                processResult = prList.get(0);
            }
            if (StringUtils.isNotBlank(entry.getValue().getResult())) {
                processResult.setManualValue(
                        entry.getValue().getResult().equals(TestResult.FAILED_LOWER) ? TestSolution.FAILED :
                        (entry.getValue().getResult().equals(TestResult.PASSED_LOWER) ? TestSolution.PASSED :
                        TestSolution.NOT_APPLICABLE));
                if (StringUtils.isNotBlank(entry.getValue().getComment())) {
                    ((DefiniteResult) processResult).setManualAuditComment(entry.getValue().getComment());
                }
            }

            processResultList.add(processResult);
        }

        return processResultList;
    }

    /**
     *
     * @param webresource
     * @param scope
     * @param theme
     * @param testSolutionList
     * @return the test result map, use of test short label as key
     */
    public Map<String, TestResult> getTestResultMap(
            WebResource webresource,
            Scope scope,
            String theme,
            Collection<String> testSolutionList) {
        // Map that associates a list of results with a theme
        Map<String, TestResult> testResultMap = new HashMap();
        List<ProcessResult> effectiveNetResultList = (List<ProcessResult>) processResultDataService.
                getProcessResultListByWebResourceAndScope(webresource, scope, theme, testSolutionList);

        if (testSolutionList.contains(NOT_TESTED_STR)) {
            List<ProcessResult> netResultList = (List<ProcessResult>) processResultDataService.
                    getProcessResultListByWebResourceAndScope(webresource, scope);
            effectiveNetResultList.addAll(addNotTestedProcessResult(getTestListFromWebResource(webresource), theme, netResultList));
        }

        for (ProcessResult processResult : effectiveNetResultList) {
            testResultMap.put(
                    processResult.getTest().getLabel(), 
                    getTestResult(processResult, true, false));
        }
        return testResultMap;
    }

    /**
     *
     * @param webresource
     * @param crit
     * @return the test result list without user filter (used for the export
     * function)
     */
    public Map<Theme, List<TestResult>> getTestResultListFromCriterion(
            WebResource webresource,
            Criterion crit) {
        // Map that associates a list of results with a theme
        List<TestResult> testResultList = new LinkedList();
        List<ProcessResult> netResultList = (List<ProcessResult>) processResultDataService.
                getProcessResultListByWebResourceAndCriterion(
                        webresource,
                        crit);

        netResultList.addAll(
                addNotTestedProcessResult(
                        testDataService.findAllByCriterion(crit),
                        crit.getTheme().getCode(),
                        netResultList));

        sortCollection(netResultList);
        for (ProcessResult processResult : netResultList) {
            if (processResult instanceof DefiniteResult) {
                TestResult testResult = getTestResult(
                        processResult,
                        true,
                        false);
                testResultList.add(testResult);
            }
        }
        Map<Theme, List<TestResult>> testResultMap = new HashMap();
        testResultMap.put(crit.getTheme(), testResultList);
        return testResultMap;
    }

    /**
     *
     * @param webresource
     * @param test
     * @return the test result list without user filter (used for the export
     * function)
     */
    public Map<Theme, List<TestResult>> getTestResultListFromTest(
            WebResource webresource,
            Test test) {
        // Map that associates a list of results with a theme
        List<TestResult> testResultList = new LinkedList();
        List<ProcessResult> netResultList = (List<ProcessResult>) processResultDataService.
                getProcessResultListByWebResourceAndTest(
                        webresource,
                        test);

        Collection testList = new ArrayList();
        testList.add(test);

        netResultList.addAll(
                addNotTestedProcessResult(
                        testList,
                        test.getCriterion().getTheme().getCode(),
                        netResultList));

        sortCollection(netResultList);
        for (ProcessResult processResult : netResultList) {
            if (processResult instanceof DefiniteResult) {
                TestResult testResult = getTestResult(
                        processResult,
                        true,
                        false);
                testResultList.add(testResult);
            }
        }
        Map<Theme, List<TestResult>> testResultMap = new HashMap();
        testResultMap.put(test.getCriterion().getTheme(), testResultList);
        return testResultMap;
    }

    /**
     * This method sorts the processResult elements
     *
     * @param processResultList
     */
    private void sortCollection(List<? extends ProcessResult> processResultList) {
        Collections.sort(processResultList, new Comparator<ProcessResult>() {
            @Override
            public int compare(ProcessResult o1, ProcessResult o2) {
                return Integer.compare(
                        o1.getTest().getRank(),
                        o2.getTest().getRank());
            }
        });
    }

    /**
     * This method sets the resultToLower attribute according to the result
     * value and sets the element counter attribute
     */
    private String setResultToLowerCase(
            ProcessResult processResult,
            TestResult testResult) {
        if (testResult.getResult().equalsIgnoreCase(TestSolution.FAILED.name())) {
            if (processResult.getElementCounter() != 0) {
                testResult.setElementCounter(processResult.getElementCounter());
            }
            return TestResult.FAILED_LOWER;
        } else if (testResult.getResult().equalsIgnoreCase(TestSolution.PASSED.name())) {
            return TestResult.PASSED_LOWER;
        } else if (testResult.getResult().equalsIgnoreCase(TestSolution.NEED_MORE_INFO.name())) {
            if (processResult.getElementCounter() != 0) {
                testResult.setElementCounter(processResult.getElementCounter());
            }
            return TestResult.NEED_MORE_INFO_LOWER;
        } else if (testResult.getResult().equalsIgnoreCase(TestSolution.NOT_APPLICABLE.name())) {
            return TestResult.NOT_APPLICABLE_LOWER;
        } else if (testResult.getResult().equalsIgnoreCase(TestSolution.NOT_TESTED.name())) {
            return TestResult.NOT_TESTED_LOWER;
        }
        return null;
    }

    /**
     *
     * @param testResult
     * @param processResult
     * @param truncatable
     */
    private void addRemarkInfosMapToTestResult(
            TestResult testResult,
            ProcessResult processResult,
            boolean truncatable) {
        addElementToCounter(testResult, processResult);
        Collection<ProcessRemark> remarks;

        if (!truncatable) {
            testResult.setTruncated(false);
            remarks = processRemarkDataService.findProcessRemarksFromProcessResult(
                    processResult,
                    -1); /* means all*/

        } else if (testResult.getResultCounter().getFailedCount() > TestResult.MAX_REMARK_INFO) {
            testResult.setTruncated(true);
            remarks = processRemarkDataService.findProcessRemarksFromProcessResultAndTestSolution(
                    processResult,
                    TestSolution.FAILED,
                    TestResult.MAX_REMARK_INFO);
        } else if ((testResult.getResultCounter().getFailedCount() + testResult.getResultCounter().getNmiCount()) > TestResult.MAX_REMARK_INFO) {
            testResult.setTruncated(true);
            remarks = new LinkedHashSet();
            remarks.addAll(processRemarkDataService.findProcessRemarksFromProcessResultAndTestSolution(
                    processResult,
                    TestSolution.FAILED,
                    -1));
            remarks.addAll(processRemarkDataService.findProcessRemarksFromProcessResultAndTestSolution(
                    processResult,
                    TestSolution.NEED_MORE_INFO,
                    TestResult.MAX_REMARK_INFO - testResult.getResultCounter().getFailedCount()));
        } else {
            testResult.setTruncated(false);
            remarks = processRemarkDataService.findProcessRemarksFromProcessResult(
                    processResult,
                    -1); /* means all*/

        }

        for (ProcessRemark remark : remarks) {
            RemarkInfos currentRemarkInfos
                    = getRemarkInfo(testResult, remark.getMessageCode(), remark);
            currentRemarkInfos.setRemarkResult(getDisplayableResultKey(remark.getIssue()));
            addElementsToRemark(testResult, currentRemarkInfos, remark);
        }
    }

    /**
     * If an evidence element is created with Element_Name code, the value of
     * the target is the value of the evidence element. Instead, the target
     * attribute of the SourceCodeRemark is returned when it exists.
     *
     * @param remark
     * @return
     */
    private String extractRemarkTarget(ProcessRemark remark) {
        for (EvidenceElement evidenceElement : remark.getElementList()) {
            if (evidenceElement.getEvidence().getCode().
                    equalsIgnoreCase(TestResult.ELEMENT_NAME_KEY)) {
                return evidenceElement.getValue().toLowerCase();
            }
        }
        if (remark instanceof SourceCodeRemark
                && StringUtils.isNotBlank(((SourceCodeRemark) remark).getTarget())) {
            return ((SourceCodeRemark) remark).getTarget();
        }
        return null;
    }

    /**
     * This methods converts raw processRemark data to displayable
     * RemarkInfosImpl data
     *
     * @param testResult
     * @param messageCode
     * @param remark
     * @return
     */
    private RemarkInfos getRemarkInfo(
            TestResult testResult,
            String messageCode,
            ProcessRemark remark) {
        String remarkTarget = extractRemarkTarget(remark);

        for (RemarkInfos remarkInfos : testResult.getRemarkInfosList()) {
            if (remarkInfos.getMessageCode().equalsIgnoreCase(messageCode)
                    && ((remarkTarget != null && remarkInfos.getRemarkTarget() != null && remarkInfos.getRemarkTarget().equalsIgnoreCase(remarkTarget))
                    || (remarkTarget == null && remarkInfos.getRemarkTarget() == null))) {
                return remarkInfos;
            }
        }
        RemarkInfos newRemarkInfo;
        if (remarkTarget != null) {
            newRemarkInfo = RemarkInfosFactory.getInstance().getRemarksInfo(messageCode, remarkTarget);
        } else {
            newRemarkInfo = RemarkInfosFactory.getInstance().getRemarksInfo(messageCode);
        }
        if (remark.getIssue().equals(TestSolution.FAILED)) {
            testResult.getRemarkInfosList().add(0, newRemarkInfo);
        } else {
            testResult.getRemarkInfosList().add(newRemarkInfo);
        }
        return newRemarkInfo;
    }

    /**
     *
     * @param testResult
     * @param processResult
     */
    private void addElementToCounter(TestResult testResult, ProcessResult processResult) {
        int failed = processRemarkDataService.
                findNumberOfProcessRemarksFromProcessResultAndTestSolution(
                        processResult,
                        TestSolution.FAILED);
        if (failed > 0) {
            testResult.getResultCounter().setFailedCount(failed);
        }
        int nmi = processRemarkDataService.
                findNumberOfProcessRemarksFromProcessResultAndTestSolution(
                        processResult,
                        TestSolution.NEED_MORE_INFO);
        if (nmi > 0) {
            testResult.getResultCounter().setNmiCount(nmi);
        }
    }

    /**
     *
     * @param remarkResult
     * @return
     */
    private String getDisplayableResultKey(TestSolution remarkResult) {
        switch (remarkResult) {
            case PASSED:
                return TestResult.PASSED_LOWER;
            case NOT_APPLICABLE:
                return TestResult.NOT_APPLICABLE_LOWER;
            case FAILED:
                return TestResult.FAILED_LOWER;
            case NEED_MORE_INFO:
                return TestResult.NEED_MORE_INFO_LOWER;
            case NOT_TESTED:
                return TestResult.NOT_TESTED_LOWER;
            default:
                return "";
        }
    }

    /**
     *
     * @param testResult
     * @param currentRemarkInfos
     * @param remark
     * @param eeList
     */
    private void addElementsToRemark(
            TestResult testResult,
            RemarkInfos currentRemarkInfos,
            ProcessRemark remark) {
        Map<String, String> elementMap = new LinkedHashMap();

        for (EvidenceElement evidenceElement : sortEvidenceElementSet(remark.getElementList())) {
            if (!evidenceElement.getEvidence().getCode().
                    equalsIgnoreCase(TestResult.ELEMENT_NAME_KEY)) {
                elementMap.put(
                        evidenceElement.getEvidence().getCode(),
                        evidenceElement.getValue());
            } else if (remark instanceof SourceCodeRemark
                    && StringUtils.isNotBlank(((SourceCodeRemark) remark).getTarget())) {
                elementMap.put(TestResult.TAG_KEY, ((SourceCodeRemark) remark).getTarget());
            }
        }
        if (remark instanceof SourceCodeRemark) {
            if (StringUtils.isNotBlank(((SourceCodeRemark) remark).getSnippet())) {
                elementMap.put(TestResult.SNIPPET, ((SourceCodeRemark) remark).getSnippet());
            }
            int lineNumber = ((SourceCodeRemark) remark).getLineNumber();
            if (lineNumber > 0) {
                elementMap.put(TestResult.LINE_NUMBER_KEY, String.valueOf(lineNumber));
            }
        }
        // Has to be removerd when decide to generalise to all representations
        if (testResult.getTestRepresentationType() != 0
                && testResult.getTestRepresentationType() == TestResult.TABULAR_REPRESENTATION) {
            elementMap = sortEvidenceElementMap(testResult, elementMap);
        }
        if (!elementMap.isEmpty()) {
            currentRemarkInfos.addEvidenceElement(elementMap);
        }
    }

    /**
     * This method sorts the evidence elements in ascending order
     *
     * @param processResultList
     */
    private List<EvidenceElement> sortEvidenceElementSet(Collection<EvidenceElement> eeSet) {
        List<EvidenceElement> evidenceElementList = new ArrayList();
        evidenceElementList.addAll(eeSet);
        Collections.sort(evidenceElementList, new Comparator<EvidenceElement>() {
            @Override
            public int compare(EvidenceElement o1, EvidenceElement o2) {
                if (o1.getId() < o2.getId()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        return evidenceElementList;
    }

    /**
     *
     * @param testResult
     * @param evidenceElementMap
     * @return
     */
    private Map<String, String> sortEvidenceElementMap(
            TestResult testResult,
            Map<String, String> evidenceElementMap) {
        Map<String, String> sortedMap = new LinkedHashMap();
        String tmpKey;
        for (String testEvidenceRepresentationOrder : testResult.getTestEvidenceRepresentationOrder()) {
            tmpKey = testEvidenceRepresentationOrder;
            if (evidenceElementMap.containsKey(tmpKey)) {
                sortedMap.put(tmpKey, evidenceElementMap.get(tmpKey));
            } else {
                Logger.getLogger(this.getClass()).info("The key " + testEvidenceRepresentationOrder + " is expected for the representation but not present in the" + " result evidence elements");
                return evidenceElementMap;
            }
        }
        return sortedMap;
    }

    /**
     * Some tests may have not ProcessResult, but are needed to be displayed as
     * not tested test. For each test without ProcessResult, we create a new
     * ProcessResult with NOT_TESTED as the result.
     *
     * @param testList
     * @param themeCode
     * @param netResultList
     * @return
     */
    private Collection<ProcessResult> addNotTestedProcessResult(
            Collection<Test> testList,
            String themeCode,
            List<ProcessResult> netResultList) {

        Collection<Test> testedTestList = new ArrayList();
        Map<String, TestSolution> manualValues = new HashMap();
        for (ProcessResult pr : netResultList) {
            /**
             * NOT_TESTED tests may have been created by manual audit during
             * audit result overriding and has to be part of NOT_TESTED tests
             * and the overridden value is stored in map to be retrieved later
             */
            if ((pr instanceof DefiniteResult) && (((DefiniteResult) pr).getDefiniteValue().equals(TestSolution.NOT_TESTED))) {
                testedTestList.add(pr.getTest());
                manualValues.put(pr.getTest().getLabel(), ((DefiniteResult) pr).getManualDefiniteValue());
                continue;
            }
            testedTestList.add(pr.getTest());
        }

        Collection<ProcessResult> notTestedProcessResult = new ArrayList();

        for (Test test : testList) {

            // if the test has no ProcessResult and its theme is part of the user
            // selection, a NOT_TESTED result ProcessRemark is created
            if (!testedTestList.contains(test) && (StringUtils.equalsIgnoreCase(test.getCriterion().getTheme().getCode(), themeCode)
                    || themeCode == null
                    || StringUtils.equalsIgnoreCase(selectAllThemeKey, themeCode))) {
                ProcessResult pr = 
                        processResultDataService.getDefiniteResult(
                                test, 
                                TestSolution.NOT_TESTED);

                pr.setRemarkSet(new ArrayList<ProcessRemark>());
                if (manualValues.containsKey(test.getLabel())) {
                    ((DefiniteResult) pr).setManualDefiniteValue(manualValues.get(test.getLabel()));
                }
                notTestedProcessResult.add(pr);
            }
        }
        return notTestedProcessResult;
    }

    /**
     * If test is not tested and if it has been overridden during the manual
     * audit we create new process result to be persisted in DB with NOT_TESTED
     * state in the automatic audit and the new state value for the manual
     * audit.
     *
     * @param test
     * @param wr
     * @param pr
     * @return
     */
    private ProcessResult createNotTestedProcessResult(
            Test test,
            WebResource wr) {

        ProcessResult pr = 
                processResultDataService.getDefiniteResult(
                        test, 
                        wr, 
                        TestSolution.NOT_TESTED, 
                        1,  //TODO set value to 1 or 0?
                        new ArrayList<ProcessRemark>());
        
        pr.setNetResultAudit(wr.getAudit());
        pr.setGrossResultAudit(wr.getAudit());
        return pr;
    }

    /**
     * Return the list of tests for a given webResource. If the webresource has
     * a parent, we pass through the parent linked with the audit to retrieve
     * the test list.
     *
     * @param wr
     * @return
     */
    private Collection<Test> getTestListFromWebResource(WebResource wr) {
        Audit audit;
        if (wr.getParent() == null && wr.getAudit() != null) {
            audit = wr.getAudit();
        } else {
            audit = wr.getParent().getAudit();
        }
        return auditDataService.getAuditWithTest(audit.getId()).getTestList();
    }

}
