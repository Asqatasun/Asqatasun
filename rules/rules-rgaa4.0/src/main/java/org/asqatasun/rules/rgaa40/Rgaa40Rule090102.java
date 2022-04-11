/**
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.rules.rgaa40;

import java.util.Iterator;
import java.util.regex.Pattern;
import org.apache.commons.collections.CollectionUtils;
import org.jsoup.nodes.Element;
import org.asqatasun.entity.audit.EvidenceElement;
import org.asqatasun.entity.audit.ProcessRemark;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.pertinence.TextPertinenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.ARIA_HEADINGS_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.HEADINGS_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.NOT_PERTINENT_HEADING_MSG;
import org.asqatasun.rules.textbuilder.DeepTextElementBuilder;
import org.asqatasun.service.ProcessRemarkService;

/**
 * Implementation of rule 9.1.2 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://doc.asqatasun.org/v5/en/Business-rules/RGAA-v4/09.Structure_of_information/Rule-9-1-2/">rule 9.1.2 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-9-1-2">9.1.2 rule specification</a>
 */
public class Rgaa40Rule090102 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    private static final String ELEMENT_NAME_VALUE_KEY= "headings";
    private static final Pattern PATTERN = Pattern.compile("[1-9][0-9]?");

    /**
     * Default constructor
     */
    public Rgaa40Rule090102() {
        super(
                new SimpleElementSelector(HEADINGS_CSS_LIKE_QUERY + ", " + ARIA_HEADINGS_CSS_LIKE_QUERY), 
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
    protected void select(SSPHandler sspHandler) {
        super.select(sspHandler);
        Iterator<Element> elementsIterator = getElements().get().iterator();
        while (elementsIterator.hasNext()) {
            Element element = elementsIterator.next();
            if (element.hasAttr("aria-level")) {
                if (!PATTERN.matcher(element.attr("aria-level")).matches()) {
                    elementsIterator.remove();
                }
            }
        }
    }
    
    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {
        super.check(sspHandler, testSolutionHandler);
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
