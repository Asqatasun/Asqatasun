/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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

package org.tanaguru.ruleimplementation;

import java.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.tanaguru.entity.audit.*;
import org.tanaguru.entity.subject.Site;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.rules.elementselector.ElementSelector;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import org.tanaguru.rules.keystore.AttributeStore;
import org.tanaguru.rules.keystore.CssLikeQueryStore;
import org.tanaguru.rules.textbuilder.TextElementBuilder;
import org.tanaguru.service.ProcessRemarkService;

/**
 * This abstract class checks the unicity of each element selected from a css-like
 * expression at the site level. If two elements are identical and one 
 * doesn't point to the other thanks to the "rel=canonical" mechanism, a 
 * sourceCodeRemark is created, and the final result is false, true instead.
 * 
 * For a given page, the first occurence of the selection is used to extract the 
 * data to test, the other occurences are ignored.
 * 
 * @author jkowalczyk
 */
public class AbstractUniqueElementSiteRuleImplementation 
                extends AbstractSiteRuleWithPageResultImplementation {

    private static final String REL_CAN_VALUE_REMARK_MSG = "relCanonicalValue";
    /* the page level message code*/
    private final String pageLevelMessageCode;
    /* the site level message code*/
    private final String siteLevelMessageCode;
    /* the elementSelector*/
    private final ElementSelector elementSelector;
    /* the textElementBuilder*/
    private final TextElementBuilder textElementBuilder;
    /* canonical testing*/
    private final boolean canonicalTesting;
    /* the processRemarkService*/
    private ProcessRemarkService prs;

    /**
     * 
     * @param elementSelector
     * @param textElementBuilder
     * @param pageLevelMessageCode
     * @param siteLevelMessageCode 
     * @param canonicalTesting
     */
    public AbstractUniqueElementSiteRuleImplementation(
            ElementSelector elementSelector,
            TextElementBuilder textElementBuilder,
            String pageLevelMessageCode,
            String siteLevelMessageCode, 
            boolean canonicalTesting) {
        super();
        this.elementSelector = elementSelector;
        this.textElementBuilder = textElementBuilder;
        this.pageLevelMessageCode = pageLevelMessageCode;
        this.siteLevelMessageCode = siteLevelMessageCode;
        this.canonicalTesting = canonicalTesting;
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        prs = sspHandler.getProcessRemarkService();
        prs.resetService();
        
        ElementHandler<Element> eh = new ElementHandlerImpl();
        elementSelector.selectElements(sspHandler, eh);
        String text = null;
        if (!eh.isEmpty()) {
            Element element = eh.get().iterator().next();
            text = textElementBuilder.buildTextFromElement(element);
        }
        Collection<ProcessRemark> remarks = null;
        if (canonicalTesting) {
            remarks = new ArrayList<>();
            extractRelCanonical(
                    sspHandler, 
                    prs, 
                    remarks);
        }
        IndefiniteResult result = 
                    processResultDataService.getIndefiniteResult(
                        test,
                        sspHandler.getPage(), 
                        text, 
                        remarks); // may be null
        return result;
    }
    
    @Override
    protected List<DefiniteResult> consolidateSiteImpl(
            Site group,
            List<ProcessResult> groupedGrossResultList,
            ProcessRemarkService processRemarkService) {

        prs = processRemarkService; 
        processRemarkService.resetService();
        
        /* set solution as NOT_APPLICABLE */
        TestSolution testSolution = TestSolution.NOT_APPLICABLE;

        List<DefiniteResult> netResultList = new ArrayList<>();
        
        int elementCounter = 0;
        
        if (!groupedGrossResultList.isEmpty()) {
            // if some grossResult have been collected during process phasis, 
            // we have elements to compare and the test result is set to 
            // passed.
            testSolution = TestSolution.PASSED;
            
            Map<String, List<ProcessResult>> previousText = new HashMap<>();
            
            // we parse all the result to populate a map where the key is an 
            // encountered textual element and the value is a collection of pages 
            // where that textual element has been found. If for a given page, 
            // the textual element could not have been extracted (the element
            // is absent), a result is created with the NOT_APPLICABLE result
            for (ProcessResult grossResult : groupedGrossResultList) {
                if (grossResult.getValue() != null) {
                    elementCounter++;
                    String text = (String) grossResult.getValue();
                    if (previousText.containsKey(text)) {
                        previousText.get(text).add(grossResult);
                    } else {
                        List<ProcessResult> urlList = new ArrayList<>();
                        urlList.add(grossResult);
                        previousText.put(text, urlList);
                    }
                } else {
                    netResultList.add(
                        processResultDataService.getDefiniteResult(
                            test, 
                            grossResult.getSubject(),
                            TestSolution.NOT_APPLICABLE, 
                            null));
                }
            }

            // if all the elements are null
            if (previousText.isEmpty()) {
                testSolution = TestSolution.NOT_APPLICABLE;
            } else {
                Iterator<Map.Entry<String, List<ProcessResult>>> iter = previousText.entrySet().iterator();
                List<ProcessResult> tmpElementList;
                while (iter.hasNext()) {
                    // if the same element has been found twice
                    Map.Entry<String, List<ProcessResult>> entry = iter.next();
                    tmpElementList = entry.getValue();
                    String tmpTagValue = entry.getKey();

                    if (tmpElementList.size() > 1 )  {
                        TestSolution ts = computeResultAndCreateRemarks(
                                tmpElementList, 
                                netResultList, 
                                tmpTagValue, 
                                elementCounter);
                        if (ts.equals(TestSolution.FAILED)) {
                            testSolution = TestSolution.FAILED;
                        }
                    } else {
                        // at page level, the result is passed
                        netResultList.add(
                            createResultAtPageLevel(
                                tmpElementList.iterator().next().getSubject(), 
                                TestSolution.PASSED,
                                0, 
                                null));
                    }
                }
            }
        }

        netResultList.add(
                processResultDataService.getDefiniteResult(
                    test, 
                    group,
                    testSolution, 
                    elementCounter,
                    processRemarkService.getRemarkList()));
        return netResultList;
    }

    /**
     * This methods creates failed remarks at page scope and site scope when 
     * duplicated are found.
     * @param netResultList
     * @param urlOnError 
     */
    private TestSolution computeResultAndCreateRemarks(
            List<ProcessResult> processResultList,
            List<DefiniteResult> netResultList, 
            String elementValue,
            int elementCounter) {
        
        Collection<WebResource> wrsOnError = 
                            createUrlListFromProcessResultList(
                                    processResultList,
                                    netResultList);
        
        TestSolution testSolution = TestSolution.PASSED;
        
        if (CollectionUtils.isNotEmpty(wrsOnError)) {
            
            for (WebResource wr : wrsOnError) {
                
                testSolution = TestSolution.FAILED;
                
                prs.addConsolidationRemark(
                        TestSolution.FAILED,
                        siteLevelMessageCode,
                        elementValue,
                        wr.getURL());

                Collection<ProcessRemark> processRemarkList = 
                        createProcessRemarkListForPageOnError(
                            elementValue,
                            wrsOnError);

                netResultList.add(
                        createResultAtPageLevel(
                            wr, 
                            TestSolution.FAILED, 
                            elementCounter,
                            processRemarkList));
            }
            // TO DO : set Passed the pages that have a correct rel=canonical 
            // definition
        } else {
            netResultList.addAll(
                createResultAtPageLevel(
                    wrsOnError, 
                    TestSolution.PASSED,
                    0, 
                    null));
        }
        return testSolution;
    }
    
    /**
     * 
     * @param processResultList
     * @return 
     */
    private Collection<DefiniteResult> createResultAtPageLevel(
            Collection<WebResource> webResourceList, 
            TestSolution testSolution, 
            int elementCounter,
            Collection<ProcessRemark> processRemarkList) {
        
        Collection<DefiniteResult> definiteResults = new ArrayList<>();
        
        for (WebResource wr : webResourceList) {
            definiteResults.add(
                    createResultAtPageLevel(
                            wr, 
                            testSolution, 
                            elementCounter, 
                            processRemarkList));
        }
        return definiteResults;
    }
    
    /**
     * 
     * @param processResultList
     * @return 
     */
    private DefiniteResult createResultAtPageLevel(
            WebResource wr, 
            TestSolution testSolution, 
            int elementCounter,
            Collection<ProcessRemark> processRemarkList) {
        
        DefiniteResult result = 
                processResultDataService.getDefiniteResult(
                        test, 
                        wr,
                        testSolution, 
                        processRemarkList);
            
        if (elementCounter > 0) {
            result.setElementCounter(elementCounter);
        }
            
        return result;
    }
    
    /**
     * For the element with a correct rel=canonical definition, a passed result
     * is thrown on the fly and added to the netResultList
     * 
     * @param processResultList
     * @param netResultList
     * @return the webResource that have a duplicate element
     */
    private Collection<WebResource> createUrlListFromProcessResultList(
            Collection<ProcessResult> processResultList, 
            List<DefiniteResult> netResultList) {
        
        Collection<WebResource> pagesWithDuplicate = new HashSet<>();
        Map<String, Collection<WebResource>> urlListWithRelCanonical = new HashMap<>();

        // extraction
        for (ProcessResult processResult : processResultList) {
            String canonicalValue = getCanonicalValue(processResult);

            WebResource wr = processResult.getSubject();
            
            if (StringUtils.isNotBlank(canonicalValue)) {
                if (urlListWithRelCanonical.containsKey(canonicalValue)) {
                    urlListWithRelCanonical.get(canonicalValue).add(wr);
                } else {
                    Collection<WebResource> wrs = new ArrayList<>();
                    wrs.add(wr);
                    urlListWithRelCanonical.put(canonicalValue, wrs);
                }
            } else {
                pagesWithDuplicate.add(wr);
            }
        }
        
        // process
        if (pagesWithDuplicate.size() == 1 ) {
            
            String canonicalUrl = pagesWithDuplicate.iterator().next().getURL();
            if (urlListWithRelCanonical.size() == 1) {

                String canonicalValue = 
                        urlListWithRelCanonical.keySet().iterator().next();

                if (StringUtils.equalsIgnoreCase(canonicalUrl, canonicalValue)) {
                    // if all the pages with the rel canonical point to 
                    // a unique page defined by the href value, a new empty list
                    // is returned and the test is passed
                    netResultList.addAll(
                            createResultAtPageLevel(
                                urlListWithRelCanonical.get(canonicalValue), 
                                TestSolution.PASSED, 
                                0, 
                                null));
                    netResultList.add(
                            createResultAtPageLevel(
                                pagesWithDuplicate.iterator().next(), 
                                TestSolution.PASSED, 
                                0, 
                                null));
                    return Collections.<WebResource>emptyList();
                } else {
                    // if all the pages with the rel canonical don't point to 
                    // a unique page defined by the href value, all is on error
                    pagesWithDuplicate.addAll(urlListWithRelCanonical.get(canonicalValue));
                }
            } else {

                for (String entry : urlListWithRelCanonical.keySet()) {
                    // the pages with a rel canonical that don't point to 
                    // a unique page defined by the href value, are set on error
                    if (!StringUtils.equalsIgnoreCase(entry, canonicalUrl)) {

                        pagesWithDuplicate.addAll(urlListWithRelCanonical.get(entry));
                    } else {
                        netResultList.addAll(
                            createResultAtPageLevel(
                                urlListWithRelCanonical.get(entry), 
                                TestSolution.PASSED, 
                                0, 
                                null));
                    }
                }
            }
        } else {

            // if a rel canonical value hasn't been encountered, the related 
            // pages are set as duplication
            Collection<String> urlsWithDuplicate = 
                    getUrlsFromWebResources(pagesWithDuplicate);
            for (String entry : urlListWithRelCanonical.keySet()) {
                if (!urlsWithDuplicate.contains(entry)) {
                    pagesWithDuplicate.addAll(urlListWithRelCanonical.get(entry));
                } else {
                    netResultList.addAll(
                            createResultAtPageLevel(
                                urlListWithRelCanonical.get(entry), 
                                TestSolution.PASSED, 
                                0, 
                                null));
                }
            }
        }
        return pagesWithDuplicate;
    }
    
    /**
     * 
     * @param processResult
     * @return the extracted canonical value when it exists, null instead.
     */
    private String getCanonicalValue(ProcessResult processResult) {
        Collection<ProcessRemark> processRemarks = 
                processRemarkDataService.findProcessRemarksFromProcessResult(processResult, -1); /*-1 means no limit*/
        if (CollectionUtils.isEmpty(processRemarks) || 
                processRemarks.size() > 1){
            return null;
        }
        ProcessRemark prk = processRemarks.iterator().next();
        if (prk.getIssue().equals(TestSolution.PASSED) && 
                prk.getMessageCode().equals(REL_CAN_VALUE_REMARK_MSG) ) {
            for (EvidenceElement ee : prk.getElementList()) {
                if (ee.getEvidence().getCode().equals(ProcessRemarkService.DEFAULT_EVIDENCE)) {
                    return ee.getValue();
                }
            }
        }
        return null;
    }
            
    /**
     * 
     * @param webResources
     * @return the collection of urls regarding the webresources
     */
    private Collection<String> getUrlsFromWebResources(Collection<WebResource> webResources) {
        Collection<String> urls = new ArrayList<>();
        for (WebResource wr : webResources) {
            urls.add(wr.getURL());
        }
        return urls;
    }
    
    /**
     * 
     * @param tagValue
     * @param urlList
     * @param processRemarkService
     * @return 
     */
    private Collection<ProcessRemark> createProcessRemarkListForPageOnError(
            String tagValue,
            Collection<WebResource> wrList) {
        Collection<ProcessRemark> processRemarkList = new ArrayList<>();
        for (WebResource wr : wrList) {
            processRemarkList.add(
                    prs.createConsolidationRemark(
                        TestSolution.FAILED, 
                        pageLevelMessageCode, 
                        tagValue, 
                        wr.getURL()));
        }
        return processRemarkList;
    }
    
    /**
     * 
     * @param sspHandler
     * @param prs
     * @param remarks 
     */
    private void extractRelCanonical(
            SSPHandler sspHandler,
            ProcessRemarkService prs, 
            Collection<ProcessRemark> remarks) {
        ElementSelector relCanonicalSelector = 
                new SimpleElementSelector(CssLikeQueryStore.REL_CANONICAL_CSS_LIKE_QUERY);
        ElementHandler<Element> relCan = new ElementHandlerImpl();
        relCanonicalSelector.selectElements(sspHandler, relCan);
        if (relCan.get().size() != 1) {
            return;
        }
        String relValue = ((Element)relCan.get().iterator().next()).absUrl(AttributeStore.HREF_ATTR);

        if (!StringUtils.equalsIgnoreCase(relValue, sspHandler.getSSP().getURI())) {
            remarks.add(
                prs.createConsolidationRemark(
                    TestSolution.PASSED, 
                    REL_CAN_VALUE_REMARK_MSG, 
                    relValue, 
                    sspHandler.getSSP().getURI()));
        }
    }

}