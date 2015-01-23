/*
 * Tanaguru - Automated webpage assessment
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */

package org.opens.tanaguru.rules.rgaa22;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.attribute.AttributePresenceChecker;
import org.opens.tanaguru.rules.elementchecker.pertinence.AttributePertinenceChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ID_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.FORM_ELEMENT_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_TITLE_OF_FORM_ELEMENT_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.FORM_ELEMENT_WITHOUT_IDENTIFIER_MSG;
import org.opens.tanaguru.rules.elementselector.builder.CssLikeSelectorBuilder;

/**
 * Implementation of the rule 3.10 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-3-10">the rule 3.10 design page.</a>
 * @see <a href="http://rgaa.net/Absence-d-element-de-formulaire.html"> 3.10 rule specification </a>
 *
 */
public class Rgaa22Rule03101 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /** The element selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new SimpleElementSelector(FORM_ELEMENT_CSS_LIKE_QUERY);
    
    /** The attribute Presence Checker */
    private static final ElementChecker ATTRIBUTE_PRESENCE_CHECKER = 
                new AttributePresenceChecker(
                        TITLE_ATTR, 
                        TestSolution.NOT_APPLICABLE, 
                        TestSolution.FAILED, 
                        null, 
                        FORM_ELEMENT_WITHOUT_IDENTIFIER_MSG);
        
    /** The attribute Pertinence Checker */
    private static final ElementChecker ATTRIBUTE_PERTINENCE_CHECKER = 
                new AttributePertinenceChecker(
                        TITLE_ATTR, 
                        // test emptiness
                        true, 
                        // no attribute to compare with
                        null, 
                        // no extension comparison with blacklist
                        null, 
                        // not pertinent message code
                        FORM_ELEMENT_WITHOUT_IDENTIFIER_MSG, 
                        // check pertinence message code
                        CHECK_TITLE_OF_FORM_ELEMENT_MSG,
                        // evidence elements
                        TITLE_ATTR);
    
    /**
     * The element handler that handles elements without id or with a 
     * not unique id
     */
    private final ElementHandler<Element> elementsWithoutUniqueId = 
            new ElementHandlerImpl();
    
    /**
     * Default constructor
     */
    public Rgaa22Rule03101(){
        super();
        setElementSelector(ELEMENT_SELECTOR);
    }
    
    @Override
    protected void select(SSPHandler sspHandler) {
        super.select(sspHandler);
        // From the selected form elements, only keep the one without id
        // or with an id not unique on the page
        for (Element el : getElements().get()) {
            if (!el.hasAttr(ID_ATTR) || 
                StringUtils.isEmpty(el.id().trim()) ||
                    CssLikeSelectorBuilder.getNumberOfElements(sspHandler, CssLikeSelectorBuilder.buildSelectorFromId(el.id())) >= 2) {
                elementsWithoutUniqueId.add(el);
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
        // If all the form elements have an id, the test is passed
        if (elementsWithoutUniqueId.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.PASSED);
            return;
        }
        
        // check the presence of title attribute for elements without unique id
        ATTRIBUTE_PRESENCE_CHECKER.check(
                sspHandler, 
                elementsWithoutUniqueId, 
                testSolutionHandler);
        
        // check the pertinence of title attribute for elements without unique id
        ATTRIBUTE_PERTINENCE_CHECKER.check(
                sspHandler, 
                elementsWithoutUniqueId, 
                testSolutionHandler);
    }

}