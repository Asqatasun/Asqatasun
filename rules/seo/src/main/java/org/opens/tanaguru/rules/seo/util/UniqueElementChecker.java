/*
 * @(#)UniqueElementChecker.java
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

package org.opens.tanaguru.rules.seo.util;

import java.util.Collection;
import java.util.HashSet;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 *
 * @author jkowalczyk
 */
public final class UniqueElementChecker {

    /**
     * Default private constructor
     */
    private UniqueElementChecker() {}

    /**
     * This methods creates an appropriate ProcessRemark with the number of
     * encoutered title tags, and with the text content of each.
     * @param sspHandler
     * @param nodeList
     * @return
     */
    public static Collection<ProcessRemark> getNotUniqueElementProcessRemarkCollection (
            SSPHandler sspHandler,
            Elements elements,
            String messageCode,
            String evidenceName) {
        ProcessRemarkService processRemarkService = sspHandler.getProcessRemarkService();
        Collection<ProcessRemark> processRemarkSet = new HashSet<ProcessRemark>();
        for(Element element : elements) {
            Collection<EvidenceElement> evidenceElementSet = new HashSet<EvidenceElement>();
            // The default evidence element is the number of encoutered elements
            evidenceElementSet.add(
                processRemarkService.getEvidenceElement(
                    ProcessRemarkService.DEFAULT_EVIDENCE,
                    String.valueOf(elements.size())));

            // for each element, we keep its text content
            evidenceElementSet.add(
                    processRemarkService.getEvidenceElement(
                    evidenceName,
                    TextContentExtractor.buildTextContentFromNodeElements(element)));
        
            processRemarkSet.add(
                processRemarkService.createProcessRemark(
                    TestSolution.FAILED,
                    messageCode,
                    evidenceElementSet));
        }
        return processRemarkSet;
    }

}
