/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
 *
 * This program is free software: you can redistribute it and/or modify
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
package org.opens.tanaguru.rules.seo;


import org.apache.commons.collections.CollectionUtils;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.pertinence.TextPertinenceChecker;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.HEADINGS_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.NOT_PERTINENT_HEADING_MSG;
import org.opens.tanaguru.rules.textbuilder.DeepTextElementBuilder;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 * Test whether a not empty H1 tag is present on the page
 * 
 * @author jkowalczyk
 */
public class SeoRule07013 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    private static final String ELEMENT_NAME_VALUE_KEY= "headings";

    /**
     * Default constructor
     */
    public SeoRule07013() {
        super(
                new SimpleElementSelector(HEADINGS_CSS_LIKE_QUERY), 
                new TextPertinenceChecker(
                    new DeepTextElementBuilder(),
                    // check emptiness
                    true, 
                    // no comparison with other attribute
                    null, 
                    // no blacklist comparison
                    null, 
                    // not pertinent message
                    NOT_PERTINENT_HEADING_MSG, 
                    // manual check message
                    CHECK_HEADING_PERTINENCE_MSG,
                    // evidence elements
                    TEXT_ELEMENT2
                )
            );
    }

    @Override
    protected void check(
            SSPHandler sspHandler, 
            ElementHandler elementHandler, 
            TestSolutionHandler testSolutionHandler) {
        super.check(sspHandler, elementHandler, testSolutionHandler);
        ProcessRemarkService prs = sspHandler.getProcessRemarkService();
        if (CollectionUtils.isNotEmpty(prs.getRemarkList())) {
            for (ProcessRemark pr : prs.getRemarkList()) {
                addElementNameEvidenceElementToOverrideTarget(prs, pr);
            }
        }
    }
    
    /**
     * This method adds an evidence element of type DEFAULT_EVIDENCE to 
     * override the default behaviour when grouping message. 
     * 
     * @param prs
     * @param pr 
     */
    private void addElementNameEvidenceElementToOverrideTarget(
            ProcessRemarkService prs,
            ProcessRemark pr) {
        EvidenceElement ee = prs.getEvidenceElement(
                    ProcessRemarkService.DEFAULT_EVIDENCE, 
                    ELEMENT_NAME_VALUE_KEY);
        pr.addElement(ee);
    }

}