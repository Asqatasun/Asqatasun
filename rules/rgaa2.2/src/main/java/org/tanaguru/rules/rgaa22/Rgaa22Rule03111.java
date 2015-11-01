/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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

package org.tanaguru.rules.rgaa22;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementChecker;
import org.tanaguru.rules.elementchecker.attribute.AttributePresenceChecker;
import org.tanaguru.rules.elementchecker.pertinence.AttributePertinenceChecker;
import org.tanaguru.rules.elementselector.ElementSelector;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import org.tanaguru.rules.elementselector.builder.CssLikeSelectorBuilder;
import static org.tanaguru.rules.keystore.AttributeStore.FOR_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.FORM_ELEMENT_WITH_ID_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.HtmlElementStore.LABEL_ELEMENT;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_TITLE_OF_FORM_ELEMENT_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.FORM_ELEMENT_WITHOUT_LABEL_MSG;

/**
 * Implementation of the rule 3.11 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-3-11">the rule 3.11 design page.</a>
 * @see <a href="http://rgaa.net/Absence-d-element-de-formulaire,29.html"> 3.11 rule specification </a>
 *
 */
public class Rgaa22Rule03111 extends AbstractPageRuleWithSelectorAndCheckerImplementation {
    
    /** The element selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new SimpleElementSelector(FORM_ELEMENT_WITH_ID_CSS_LIKE_QUERY);
    
    /** The attribute presence checker */
    private static final ElementChecker ATTRIBUTE_PRESENCE_CHECKER = 
                new AttributePresenceChecker(
                        TITLE_ATTR, 
                        TestSolution.NOT_APPLICABLE, 
                        TestSolution.FAILED, 
                        null, 
                        FORM_ELEMENT_WITHOUT_LABEL_MSG);
    
    /** The attribute pertinence checker */    
    private static final ElementChecker ATTRIBUTE_PERTINENCE_CHECKER = 
                new AttributePertinenceChecker(
                        // the attribute to check
                        TITLE_ATTR, 
                        // test emptiness
                        true, 
                        // no attribute to compare with   
                        null, 
                        // no extension comparison with blacklist
                        null, 
                        // not pertinent message code   
                        FORM_ELEMENT_WITHOUT_LABEL_MSG, 
                        // check pertinence message code
                        CHECK_TITLE_OF_FORM_ELEMENT_MSG, 
                        // evidence elements
                        TITLE_ATTR);
        
    /**
     * The element handler that handles elements without label
     */
    private final ElementHandler<Element> elementsWithoutLabel = 
            new ElementHandlerImpl();
    
    /**
     * Default constructor
     */
    public Rgaa22Rule03111(){
        super();
        setElementSelector(ELEMENT_SELECTOR);
    }
    
    @Override
    protected void select(SSPHandler sspHandler) {
        
        super.select(sspHandler);
        
        if (getElements().isEmpty()) {
            return;
        }
        
        Elements elementsWithUniqueId = new Elements();
        // From the selected form elements, only keep the one with a unique id
        // on the page
        for (Element el : getElements().get()) {
            if (StringUtils.isNotEmpty(el.id().trim()) &&
                    CssLikeSelectorBuilder.getNumberOfElements(sspHandler, CssLikeSelectorBuilder.buildSelectorFromId(el.id())) == 1) {
                elementsWithUniqueId.add(el);
            }
        }
        // add the subset to the global selection
        getElements().clean().addAll(elementsWithUniqueId);
        
        if (elementsWithUniqueId.isEmpty()) {
            return;
        }

        for (Element el : elementsWithUniqueId ) {
            String labelSelector = CssLikeSelectorBuilder.
                    buildSelectorFromElementsAndAttributeValue(LABEL_ELEMENT, FOR_ATTR, el.id());
            if (CssLikeSelectorBuilder.getNumberOfElements(sspHandler, labelSelector) == 0) {
                this.elementsWithoutLabel.add(el);
            }
        }

    }

    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {
        // if the page have no form elements, the test is not applicable
        if (getElements().isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        
        // if the set of elements without label is empty, the test is PASSED
        if (elementsWithoutLabel.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.PASSED);
            return;
        }

        // check the presence of title attribute for elements without unique id
        ATTRIBUTE_PRESENCE_CHECKER.check(
                sspHandler, 
                elementsWithoutLabel, 
                testSolutionHandler);

        // check the pertinence of title attribute for elements without unique id
        ATTRIBUTE_PERTINENCE_CHECKER.check(
                sspHandler, 
                elementsWithoutLabel, 
                testSolutionHandler);
    }

}