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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.accessiweb22;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.InputFormElementWithExplicitLabelSelector;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.FOR_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.FORM_ELEMENT_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.FORM_LABEL_WITH_INNER_FORM_ELEMENT_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.INVALID_LABEL_MSG;

/**
 * Implementation of the rule 11.1.3 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-11-1-3">the rule 11.1.3 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-1-3"> 11.1.3 rule specification</a>
 *
 */
public class Aw22Rule11013 extends AbstractPageRuleMarkupImplementation {

    /** The explicit label elements */
    private final ElementHandler<Element> explicitLabelElements = 
            new ElementHandlerImpl();
    
    /** The label elements with inner control*/
    private final ElementHandler<Element> innerControlLabelElements = 
            new ElementHandlerImpl();

    /**
     * Default constructor
     */
    public Aw22Rule11013() {
        super();
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        ElementSelector elementSelector = 
                new SimpleElementSelector(
                    FORM_LABEL_WITH_INNER_FORM_ELEMENT_CSS_LIKE_QUERY);
        elementSelector.selectElements(sspHandler, innerControlLabelElements);

        ElementSelector explicitLabelSelector = 
                new InputFormElementWithExplicitLabelSelector();
        explicitLabelSelector.selectElements(
                        sspHandler, 
                        explicitLabelElements);
    }

    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {
        
        if (explicitLabelElements.isEmpty() && innerControlLabelElements.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        // if all the label are explicitely defined, the test is passed
        if (innerControlLabelElements.isEmpty()){
            // add the explicit label element to main handler for counter
            // purpose
            innerControlLabelElements.addAll(explicitLabelElements.get());
            testSolutionHandler.addTestSolution(TestSolution.PASSED);
            return;
        }
        ElementHandler<Element> labelOnError = new ElementHandlerImpl();
        for (Element el :innerControlLabelElements.get()) {
            if (!isForAttributeOfLabelEqualsToIdAttributeOfFormField(
                    el, 
                    el.attr(FOR_ATTR)))  {
                labelOnError.add(el);
            }
        }
        // use this checker to create sourceCodeRemark when needed
        ElementChecker checker = new ElementPresenceChecker(INVALID_LABEL_MSG, null);
        checker.check(sspHandler, labelOnError, testSolutionHandler);
    }
    
    /**
     * This methods checks whether the value of the for attribute of a label
     * node corresponds to the value of the id attribute of any child form field.
     * 
     * @param childNodes
     * @param forAttributeValue
     * @return
     */
    private boolean isForAttributeOfLabelEqualsToIdAttributeOfFormField(
            Element element, 
            String forAttributeValue) {
        if (StringUtils.isBlank(forAttributeValue)) {
            return false;
        }
        for (Element el : element.children().select(FORM_ELEMENT_CSS_LIKE_QUERY)) {
            if (StringUtils.equalsIgnoreCase(forAttributeValue, el.id())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getSelectionSize() {
        return innerControlLabelElements.size();
    }

}