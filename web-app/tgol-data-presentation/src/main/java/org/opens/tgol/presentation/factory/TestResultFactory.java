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
package org.opens.tgol.presentation.factory;

import java.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.*;
import org.opens.tanaguru.entity.factory.audit.ProcessResultFactory;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.reference.CriterionDataService;
import org.opens.tanaguru.entity.service.reference.TestDataService;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tgol.entity.decorator.tanaguru.subject.WebResourceDataServiceDecorator;
import org.opens.tgol.presentation.data.RemarkInfos;
import org.opens.tgol.presentation.data.TestResult;
import org.opens.tgol.presentation.data.TestResultImpl;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author jkowalczyk
 */
public final class TestResultFactory {

    private static final String NOT_TESTED_STR = "NOT_TESTED";
    
    private final ResourceBundle representationBundle =
            ResourceBundle.getBundle(TestResult.REPRESENTATION_BUNDLE_NAME);

    private WebResourceDataServiceDecorator webResourceDataService;
    @Autowired
    public void setWebResourceDataService(WebResourceDataServiceDecorator webResourceDataServiceDecorator) {
        this.webResourceDataService = webResourceDataServiceDecorator;
    }
    
    private AuditDataService auditDataService;
    @Autowired
    public void setAuditDataService(AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }
    
    private CriterionDataService criterionDataService;
    @Autowired
    public void setCriterionDataService(CriterionDataService criterionDataService) {
        this.criterionDataService = criterionDataService;
    }
    
    private TestDataService testDataService;
    @Autowired
    public void setTestDataService(TestDataService testDataService) {
        this.testDataService = testDataService;
    }
    
    private ProcessResultFactory processResultFactory;
    @Autowired
    public void setProcessResultFactory(ProcessResultFactory processResultFactory) {
        this.processResultFactory = processResultFactory;
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
    private TestResultFactory(){}

    public static synchronized TestResultFactory getInstance(){
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
     * @param test
     * @param hasSourceCodeADoctype
     * @param hasResultDetails
     * @return
     */
    public TestResult getTestResult(
            ProcessResult processResult,
            boolean hasSourceCodeWithDoctype,
            boolean hasResultDetails) {
        TestResult testResult = new TestResultImpl(hasSourceCodeWithDoctype);
        testResult.setTestUrl(processResult.getTest().getDescription());
        testResult.setTestShortLabel(processResult.getTest().getLabel());
        testResult.setTestCode(processResult.getTest().getCode());
        testResult.setLevelCode(processResult.getTest().getLevel().getCode());
        testResult.setResult( processResult.getValue().toString());
        testResult.setResultCode(setResultToLowerCase(processResult, testResult));
        testResult.setRuleDesignUrl(processResult.getTest().getRuleDesignUrl());
        testResult.setResultCounter(ResultCounterFactory.getInstance().getResultCounter());
        testResult.setTestRepresentation(Integer.valueOf(representationBundle.
                getString(testResult.getTestCode()+TestResult.REPRESENTATION_SUFFIX_KEY)));
        testResult.setTest(processResult.getTest());
        try {
            testResult.setTestEvidenceRepresentationOrder(representationBundle.
                getString(testResult.getTestCode()+TestResult.REPRESENTATION_ORDER_SUFFIX_KEY));
        } catch (MissingResourceException mre) {
            Logger.getLogger(this.getClass()).warn(mre);
        }
        if (hasResultDetails) {
            setRemarkInfosMap(testResult, processResult);
        }
        return testResult;
    }

    /**
     * 
     * @param webresource
     * @param scope
     * @param hasSourceCodeWithDoctype
     * @param hasResultDetails
     * @param theme
     * @param testSolutionList
     * @return 
     */
    public Map<Theme, List<TestResult>> getTestResultSortedByThemeMap(
            WebResource webresource,
            Scope scope,
            boolean hasSourceCodeWithDoctype,
            boolean hasResultDetails,
            String theme,
            Collection<String> testSolutionList){
        
        List<ProcessResult> netResultList = (List<ProcessResult>)
                webResourceDataService.
                getProcessResultListByWebResourceAndScope(webresource, scope, theme, testSolutionList);
        // The not tested tests are not persisted but deduced from the testResultList
        // If the not_tested solution is requested to be displayed, we add fake
        // processResult to the current list.
        if (testSolutionList.contains(NOT_TESTED_STR)) {
            netResultList.addAll(addNotTestedProcessResult(getTestListFromWebResource(webresource), theme, netResultList));
        }
        
        return prepareThemeResultMap(
                netResultList, 
                hasSourceCodeWithDoctype, 
                hasResultDetails);
    }

    /**
     * 
     * @param netResultList
     * @param hasSourceCodeWithDoctype
     * @param hasResultDetails
     * @return
     */
    private Map<Theme, List<TestResult>> prepareThemeResultMap(
            List<ProcessResult> netResultList,
            boolean hasSourceCodeWithDoctype,
            boolean hasResultDetails) {
        
        // Map that associates a list of results with a theme
        Map<Theme, List<TestResult>> testResultMap =
                new LinkedHashMap<Theme, List<TestResult>>();

        sortCollection(netResultList);
        for (ProcessResult processResult : netResultList) {
            if (processResult instanceof DefiniteResult) {
                TestResult testResult = getTestResult(
                        processResult,
                        hasSourceCodeWithDoctype,
                        hasResultDetails);
                Theme theme =
                        processResult.getTest().getCriterion().getTheme();
                
                if (testResultMap.containsKey(theme)) {
                    testResultMap.get(theme).add(testResult);
                } else {
                    List<TestResult> testResultList = new ArrayList<TestResult>();
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
     * @param hasSourceCodeWithDoctype
     * @param hasResultDetails
     * @param locale
     * @return
     *      the test result list without user filter (used for the export function)
     */
    public List<TestResult> getTestResultList(
            WebResource webresource,
            Scope scope,
            boolean hasSourceCodeWithDoctype,
            boolean hasResultDetails,
            Locale locale) {
        // Map that associates a list of results with a theme
        List<TestResult> testResultList = new LinkedList<TestResult>();
        List<ProcessResult> netResultList = (List<ProcessResult>)
                webResourceDataService.
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
                        hasSourceCodeWithDoctype,
                        hasResultDetails);
                testResultList.add(testResult);
            }
        }
        return testResultList;
    }
    
    /**
     * 
     * @param webresource
     * @param scope
     * @param hasSourceCodeWithDoctype
     * @param hasResultDetails
     * @param locale
     * @return
     *      the test result list without user filter (used for the export function)
     */
    public Map<Theme, List<TestResult>> getTestResultListFromCriterion(
            WebResource webresource,
            boolean hasSourceCodeWithDoctype,
            Long criterionId) {
        // Map that associates a list of results with a theme
        List<TestResult> testResultList = new LinkedList<TestResult>();
        Criterion crit = criterionDataService.read(criterionId);
        List<ProcessResult> netResultList = (List<ProcessResult>)
                webResourceDataService.
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
                        hasSourceCodeWithDoctype,
                        true);
                testResultList.add(testResult);
            }
        }
        Map<Theme, List<TestResult>> testResultMap = new HashMap<Theme, List<TestResult>>();
        testResultMap.put(crit.getTheme(), testResultList);
        return testResultMap;
    }

    /**
     * This method sorts the processResult elements
     * @param processResultList
     */
    private void sortCollection(List<? extends ProcessResult> processResultList) {
        Collections.sort(processResultList, new Comparator<ProcessResult>() {
            @Override
            public int compare(ProcessResult o1, ProcessResult o2) {
                return String.CASE_INSENSITIVE_ORDER.compare(
                        o1.getTest().getCode(),
                        o2.getTest().getCode());
            }
        });
    }

    /**
     * This method sets the resultToLower attribute
     * according to the result value and sets the element counter attribute
     */
    private String setResultToLowerCase(
            ProcessResult processResult,
            TestResult testResult) {
        if (testResult.getResult().equalsIgnoreCase(TestResult.FAILED)) {
            if (processResult.getElementCounter() != 0) {
                testResult.setElementCounter(processResult.getElementCounter());
            }
            return TestResult.FAILED_LOWER;
        } else if (testResult.getResult().equalsIgnoreCase(TestResult.PASSED)) {
            return TestResult.PASSED_LOWER;
        } else if (testResult.getResult().equalsIgnoreCase(TestResult.NEED_MORE_INFO)) {
            if (processResult.getElementCounter() != 0) {
               testResult.setElementCounter(processResult.getElementCounter());
            }
            return TestResult.NEED_MORE_INFO_LOWER;
        } else if (testResult.getResult().equalsIgnoreCase(TestResult.NOT_APPLICABLE)) {
            return TestResult.NOT_APPLICABLE_LOWER;
        } else if (testResult.getResult().equalsIgnoreCase(TestResult.NOT_TESTED)) {
            return TestResult.NOT_TESTED_LOWER;
        }
        return null;
    }

    /**
     * @param result
     */
    @SuppressWarnings("unchecked")
    public void setRemarkInfosMap(TestResult testResult, ProcessResult processResult) {
        for (ProcessRemark remark : sortRemarkSet((Collection<ProcessRemark>)processResult.getRemarkSet())) {
            RemarkInfos currentRemarkInfos =
                     getRemarkInfo(testResult, remark.getMessageCode(), extractRemarkTarget(remark), remark);
            currentRemarkInfos.setRemarkResult(addElementToCounter(testResult,remark.getIssue()));
            addElementsToRemark(testResult, currentRemarkInfos, remark);
         }
    }

    /**
     * This method sorts the processResult elements in ascending order
     * @param processResultList
     */
    private List<ProcessRemark> sortRemarkSet(Collection<ProcessRemark> processRemarkSet) {
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();
        processRemarkList.addAll(processRemarkSet);
        Collections.sort(processRemarkList, new Comparator<ProcessRemark>() {
            @Override
            public int compare(ProcessRemark o1, ProcessRemark o2) {
                if (o1.getId() < o2.getId()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        return processRemarkList;
    }

    /**
     *
     * @param remark
     * @return
     */
    private String extractRemarkTarget(ProcessRemark remark) {
        for (EvidenceElement evidenceElement :
            remark.getElementList()) {
            if (evidenceElement.getEvidence().getCode().
                    equalsIgnoreCase(TestResult.ELEMENT_NAME_KEY)) {
                return evidenceElement.getValue().toLowerCase();
            }
        }
        return null;
    }

     /**
     * This methods converts raw processRemark data to displayable RemarkInfosImpl
     * data
     * @param messageCode
     * @return
     */
    private RemarkInfos getRemarkInfo(
            TestResult testResult,
            String messageCode,
            String remarkTarget,
            ProcessRemark remark) {
        for (RemarkInfos remarkInfos : testResult.getRemarkInfosList()) {
            if (remarkInfos.getMessageCode().equalsIgnoreCase(messageCode) &&
                    (
                    (remarkTarget != null  && remarkInfos.getRemarkTarget() != null && remarkInfos.getRemarkTarget().equalsIgnoreCase(remarkTarget)) ||
                    (remarkTarget == null && remarkInfos.getRemarkTarget() == null)
                    )) {
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
     * @param testResult
     * @param remarkResult
     * @return
     */
    private String addElementToCounter(TestResult testResult, TestSolution remarkResult) {
        switch (remarkResult) {
            case PASSED:
                return TestResult.PASSED_LOWER;
            case NOT_APPLICABLE:
                return TestResult.NOT_APPLICABLE_LOWER;
            case FAILED:
                if (testResult.getResultCounter().getFailedCount() == -1) {
                    testResult.getResultCounter().setFailedCount(1);
                } else {
                    testResult.getResultCounter().setFailedCount(testResult.getResultCounter().getFailedCount()+1);
                }
                return TestResult.FAILED_LOWER;
            case NEED_MORE_INFO:
                if (testResult.getResultCounter().getNmiCount() == -1) {
                    testResult.getResultCounter().setNmiCount(1);
                } else {
                    testResult.getResultCounter().setNmiCount(testResult.getResultCounter().getNmiCount()+1);
                }
                return TestResult.NEED_MORE_INFO_LOWER;
            case NOT_TESTED:
                return TestResult.NOT_TESTED_LOWER;
            default:
                return "";
        }
    }

    /**
     *
     * @param currentRemarkInfos
     * @param remark
     */
    private void addElementsToRemark(
            TestResult testResult,
            RemarkInfos currentRemarkInfos,
            ProcessRemark remark) {
        Map <String, String> elementMap = new HashMap<String, String>();
        if (remark instanceof SourceCodeRemark) {
            int lineNumber = computeLineNumber(testResult,(SourceCodeRemark)remark);
            if (lineNumber > 0) {
                elementMap.put(TestResult.LINE_NUMBER_KEY, String.valueOf(lineNumber));
            }
        }
        for (EvidenceElement evidenceElement : remark.getElementList()) {
            if (!evidenceElement.getEvidence().getCode().
                    equalsIgnoreCase(TestResult.ELEMENT_NAME_KEY)) {
                elementMap.put(
                        evidenceElement.getEvidence().getCode(),
                        evidenceElement.getValue());
            }
        }
        // Has to be removerd when decide to generalise to all representations
        if (testResult.getTestRepresentationType() == TestResult.TABULAR_REPRESENTATION) {
            elementMap = sortEvidenceElementMap(testResult,elementMap);
        }
        if (!elementMap.isEmpty()) {
            currentRemarkInfos.addEvidenceElement(elementMap);
        }
    }

    /**
     *
     * @param remark
     * @param testResult
     * @return
     */
    private int computeLineNumber(
            TestResult testResult,
            SourceCodeRemark remark) {
        int lineNumber;
        // The doctype is added when the result is displayed.
        // When the doctype is not null, each line remark has to be
        // one-line-shifted
        if (testResult.hasSourceCodeADoctype()) {
            lineNumber = remark.getLineNumber() + 1;
        } else {
            lineNumber = remark.getLineNumber();
        }
        return lineNumber;
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
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        String tmpKey;
        for(int i=0;i<testResult.getTestEvidenceRepresentationOrder().length;i++){
            tmpKey = testResult.getTestEvidenceRepresentationOrder()[i];
            if (evidenceElementMap.containsKey(tmpKey)) {
                sortedMap.put(tmpKey, evidenceElementMap.get(tmpKey));
            } else {
                Logger.getLogger(this.getClass()).info("The key " + testResult.getTestEvidenceRepresentationOrder()[i] +
                        " is expected for the representation but not present in the"
                        + " result evidence elements");
                return evidenceElementMap;
            }
        }
        return sortedMap;
    }

    /**
     * Some tests may have not ProcessResult, but are needed to be displayed
     * as not tested test. For each test without ProcessResult, we create 
     * a new ProcessResult with NOT_TESTED as the result. 
     * 
     * @param testList
     * @param themeCode
     * @param netResultList
     * @return 
     */
    private Collection<ProcessResult> addNotTestedProcessResult(
            Collection<Test>testList, 
            String themeCode,
            List<ProcessResult> netResultList) {
        
        Collection<Test> testedTestList = new ArrayList<Test>();
        for (ProcessResult pr : netResultList) {
            testedTestList.add(pr.getTest());
        }
        
        Collection<ProcessResult> notTestedProcessResult = new ArrayList<ProcessResult>();
        
        for (Test test : testList) {
            
            // if the test has no ProcessResult and its theme is part of the user
            // selection, a NOT_TESTED result ProcessRemark is created
            if (!testedTestList.contains(test) && (
                    StringUtils.equalsIgnoreCase(test.getCriterion().getTheme().getCode(), themeCode) ||
                    themeCode == null || 
                    StringUtils.equalsIgnoreCase(selectAllThemeKey, themeCode))) {
                ProcessResult pr = processResultFactory.createDefiniteResult();
                pr.setTest(test);
                pr.setValue(TestSolution.NOT_TESTED);
                notTestedProcessResult.add(pr);
                pr.setRemarkSet(new ArrayList<ProcessRemark>());
            }
        }
        return notTestedProcessResult;
    }
    
    /**
     * Return the list of tests for a given webResource. If the webresource has 
     * a parent, we pass through the parent linked with the audit to retrieve 
     * the test list.
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