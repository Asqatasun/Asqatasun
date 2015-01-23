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
package org.opens.tanaguru.rules.accessiweb22;

import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.attribute.AttributePresenceChecker;
import org.opens.tanaguru.rules.elementchecker.attribute.IdUnicityChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextEmptinessChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.InputFormElementWithExplicitLabelSelector;
import org.opens.tanaguru.rules.elementselector.InputFormElementWithInplicitLabelSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ID_ATTR;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.ID_MISSING_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.ID_NOT_UNIQUE_MSG;
import org.opens.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 11.1.2 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-11-1-2">the rule 11.1.2 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-1-2"> 11.1.2 rule specification</a>
 *
 */
public class Aw22Rule11012 extends AbstractPageRuleMarkupImplementation {

    /* the links without target */
    private final ElementHandler<Element> labels = new ElementHandlerImpl();
    
    /**
     * Default constructor
     */
    public Aw22Rule11012() {
        super();
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        ElementSelector explicitLabelSelector = 
                new InputFormElementWithExplicitLabelSelector();
        explicitLabelSelector.selectElements(
                        sspHandler, 
                        labels);
        ElementSelector inplicitLabelSelector = 
                new InputFormElementWithInplicitLabelSelector();
        inplicitLabelSelector.selectElements(
                        sspHandler, 
                        labels);
    }

    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {
        
        // If the page has no input form element, the test is not applicable
        if (labels.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }

        /* The attribute Presence Checker */
        ElementChecker attributePresenceChecker = 
                new AttributePresenceChecker(
                        ID_ATTR, 
                        TestSolution.PASSED, 
                        TestSolution.FAILED, 
                        null, 
                        ID_MISSING_MSG);
        attributePresenceChecker.check(sspHandler, labels, testSolutionHandler);

        /* The attribute Emptiness Checker. Keep default value i.e failed 
         when attribute is empty
         */
        ElementChecker attributeEmptinessChecker = 
                new TextEmptinessChecker(
                        new TextAttributeOfElementBuilder(ID_ATTR), 
                        ID_MISSING_MSG, 
                        null);
        attributeEmptinessChecker.check(sspHandler, labels, testSolutionHandler);
        
        /* The id unicityChecker */
        ElementChecker idUnicityChecker = new IdUnicityChecker(ID_NOT_UNIQUE_MSG);
        idUnicityChecker.check(sspHandler, labels, testSolutionHandler);
    }

    @Override
    public int getSelectionSize() {
        return labels.size();
    }

}