/*
 * @(#)AbstractTagUnicitySiteRuleImplementation.java
 *
 * Copyright  2010 SAS OPEN-S. All rights reserved.
 * OPEN-S PROPRIETARY/CONFIDENTIAL.  Use is subject to license terms.
 *
 * This file is  protected by the  intellectual  property rights
 * in  France  and  other  countries, any  applicable  copyrights  or
 * patent rights, and international treaty provisions. No part may be
 * reproduced  in  any  form  by  any  mean  without   prior  written
 * authorization of OPEN-S.
 */

package org.opens.tanaguru.ruleimplementation;

import java.util.*;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.*;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.textbuilder.TextElementBuilder;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 * This abstract class checks the unicity of each element selected from a css-like
 * expression. If two elements are identical, a sourceCodeRemark is
 * created, and the final result is false, true instead.
 *
 * @author jkowalczyk
 */
public class AbstractUniqueElementSiteRuleImplementation 
                extends AbstractSiteRuleWithPageResultImplementation {

    /* the page level message code*/
    private String pageLevelMessageCode;
    /* the site level message code*/
    private String siteLevelMessageCode;
    /* the elementSelector*/
    private ElementSelector elementSelector;
    /* the textElementBuilder*/
    private TextElementBuilder textElementBuilder;
    
    /**
     * 
     * @param elementSelector
     * @param textElementBuilder
     * @param pageLevelMessageCode
     * @param siteLevelMessageCode 
     */
    public AbstractUniqueElementSiteRuleImplementation(
            ElementSelector elementSelector,
            TextElementBuilder textElementBuilder,
            String pageLevelMessageCode,
            String siteLevelMessageCode) {
        super();
        this.elementSelector = elementSelector;
        this.textElementBuilder = textElementBuilder;
        this.pageLevelMessageCode = pageLevelMessageCode;
        this.siteLevelMessageCode = siteLevelMessageCode;
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.getProcessRemarkService().resetService();
        ElementHandler<Element> eh = new ElementHandlerImpl();
        elementSelector.selectElements(sspHandler, eh);
        String text = null;
        if (!eh.isEmpty()) {
            Element element = eh.get().iterator().next();
            text = textElementBuilder.buildTextFromElement(element);
        }
        IndefiniteResult result = indefiniteResultFactory.create(test,
                sspHandler.getPage(), text);
        return result;
    }
    
    @Override
    protected List<DefiniteResult> consolidateSiteImpl(Site group,
            List<ProcessResult> groupedGrossResultList,
            ProcessRemarkService processRemarkService) {
        TestSolution testSolution = TestSolution.NOT_APPLICABLE;
        processRemarkService.resetService();
        List<DefiniteResult> netResultList = new ArrayList<DefiniteResult>();
        if (!groupedGrossResultList.isEmpty()) {
            testSolution = TestSolution.PASSED;
            Map<String, List<WebResource>> previousTitles = new HashMap<String, List<WebResource>>();
            for (ProcessResult grossResult : groupedGrossResultList) {
                Object titleObj = grossResult.getValue();
                if (titleObj != null) {
                    String title = (String) titleObj;
                    if (previousTitles.containsKey(title)) {
                        previousTitles.get(title).add(grossResult.getSubject());
                    } else {
                        List<WebResource> urlList = new ArrayList<WebResource>();
                        urlList.add(grossResult.getSubject());
                        previousTitles.put(title, urlList);
                    }
                } else {
                    netResultList.add(
                        definiteResultFactory.create(
                            test, 
                            grossResult.getSubject(),
                            TestSolution.NOT_APPLICABLE, 
                            null));
                }
            }
            // if all the elements are null
            if (previousTitles.isEmpty()) {
                testSolution = TestSolution.NOT_APPLICABLE;
            } else {
                Iterator<Map.Entry<String, List<WebResource>>> iter = previousTitles.entrySet().iterator();
                List<WebResource> tmpElementList;
                while (iter.hasNext()) {
                    // if the same element has been found twice
                    Map.Entry<String, List<WebResource>> entry = iter.next();
                    tmpElementList = entry.getValue();
                    String tmpTagValue = entry.getKey();
                    if (tmpElementList.size() > 1 )  {
                        for (WebResource webResource : tmpElementList) {
                            testSolution = TestSolution.FAILED;
                            processRemarkService.addConsolidationRemark(
                                testSolution,
                                siteLevelMessageCode,
                                tmpTagValue,
                                webResource.getURL());
                        }
                    }
                    netResultList.addAll(
                            createTestSolutionAtPageLevel(
                                tmpElementList,
                                tmpTagValue,
                                processRemarkService));
                }
            }
        }

        netResultList.add(
                definiteResultFactory.create(
                    test, 
                    group,
                    testSolution, 
                    processRemarkService.getRemarkList()));
        return netResultList;
    }

    /**
     * 
     * @param webResourceList
     * @param tagValue
     * @param processRemarkService
     * @return 
     */
    private List<DefiniteResult> createTestSolutionAtPageLevel(
            List<WebResource> webResourceList, 
            String tagValue,
            ProcessRemarkService processRemarkService) {
        
        List<DefiniteResult> definiteResultList = new ArrayList<DefiniteResult>();

        // if the size of the collection of webResource is equal to 1, the tag
        // value has been encountered once. So the result for this unique page is 
        // passed.
        if (webResourceList.size() == 1 ) { // the webResourceList cannot be empty
            definiteResultList.add(
                    definiteResultFactory.create(
                        test, 
                        webResourceList.iterator().next(),
                        TestSolution.PASSED, 
                        new ArrayList<ProcessRemark>()));
        } else {
            List<String> urlList = 
                    createUrlListFromWebResourceList(webResourceList);
            
            for (WebResource webResource : webResourceList) {
                Collection<ProcessRemark> processRemarkList = 
                        createProcessRemarkListForPageOnError(
                            tagValue,
                            webResource.getURL(),
                            urlList,
                            processRemarkService);
                definiteResultList.add(
                    definiteResultFactory.create(
                        test, 
                        webResource,
                        TestSolution.FAILED, 
                        processRemarkList));
            }
        }
        return definiteResultList;
    }

    /**
     * 
     * @param webResourceList
     * @return 
     */
    private List<String> createUrlListFromWebResourceList(List<WebResource> webResourceList) {
       List<String> urlList = new ArrayList<String>();
       for (WebResource webResource : webResourceList) {
           urlList.add(webResource.getURL());
       }
       return urlList;
    }
    
    /**
     * 
     * @param tagValue
     * @param currentUrl
     * @param urlList
     * @param processRemarkService
     * @return 
     */
    private Collection<ProcessRemark> createProcessRemarkListForPageOnError(
            String tagValue,
            String currentUrl,
            List<String> urlList, 
            ProcessRemarkService processRemarkService) {
        Collection<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();
        for (String url : urlList) {
            if (!url.equalsIgnoreCase(currentUrl)) {
                processRemarkList.add(
                        processRemarkService.createConsolidationRemark(
                            TestSolution.FAILED, 
                            pageLevelMessageCode, 
                            tagValue, 
                            url));
            }
        }
        return processRemarkList;
    }
    
}