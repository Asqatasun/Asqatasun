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

import org.opens.tgol.entity.decorator.tanaguru.subject.WebResourceDataServiceDecorator;
import org.opens.tgol.presentation.data.RemarkInfos;
import org.opens.tgol.presentation.data.TestResult;
import org.opens.tgol.presentation.data.TestResultImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.subject.WebResource;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author jkowalczyk
 */
public final class TestResultFactory {

    private final ResourceBundle representationBundle =
            ResourceBundle.getBundle(TestResult.REPRESENTATION_BUNDLE_NAME);

    private WebResourceDataServiceDecorator webResourceDataService;
    @Autowired
    public void setWebResourceDataService(WebResourceDataServiceDecorator webResourceDataServiceDecorator) {
        this.webResourceDataService = webResourceDataServiceDecorator;
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
     * This methods prepare test results to be displayed
     * @param webresource
     * @param scope
     * @param hasSourceCodeADoctype
     * @param hasResultDetails
     * @return
     */
    public Map<Theme, List<TestResult>> getTestResultSortedByThemeMap(
            WebResource webresource,
            Scope scope,
            boolean hasSourceCodeWithDoctype,
            boolean hasResultDetails) {
        // Map that associates a list of results with a theme
        Map<Theme, List<TestResult>> testResultMap =
                new LinkedHashMap<Theme, List<TestResult>>();
        List<ProcessResult> netResultList = (List<ProcessResult>)
                webResourceDataService.
                getProcessResultListByWebResourceAndScope(webresource, scope);
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
                return evidenceElement.getValue();
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

}