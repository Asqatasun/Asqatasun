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

package org.opens.tanaguru.rules.rgaa30;

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
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.CONTENT_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.META_WITH_REFRESH_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.NOT_IMMEDIATE_REDIRECT_VIA_META_MSG;

/**
 * Implementation of the rule 13.1.2 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-13-1-2">the rule 13.1.2 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-html5aria-liste-deployee.html#test-13-1-2"> 13.1.2 rule specification</a>
 *
 */
public class Rgaa30Rule130102 extends AbstractPageRuleMarkupImplementation {

    private static final String URL_STR = "url";
    private static final String SEMI_COLON_CHAR = ";";
    
    ElementHandler notImmediateRedirectMeta = new ElementHandlerImpl();
    
    /**
     * Default constructor
     */
    public Rgaa30Rule130102 () {
        super();
    }

    @Override
    protected void select(SSPHandler sspHandler, ElementHandler<Element> elementHandler) {
        ElementSelector elementsSelector = 
                new SimpleElementSelector(META_WITH_REFRESH_CSS_LIKE_QUERY);
        elementsSelector.selectElements(sspHandler, elementHandler);
        for (Element el : elementHandler.get()) {
            if (!isImmediateRedirection(el)) {
                notImmediateRedirectMeta.add(el);
            }
        }
    }
    
    @Override
    protected void check(
            SSPHandler sspHandler, 
            ElementHandler selectionHandler, 
            TestSolutionHandler testSolutionHandler) {

        if (selectionHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        if (notImmediateRedirectMeta.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.PASSED);
            return;
        }
        ElementChecker ec= new ElementPresenceChecker(
                        TestSolution.FAILED,
                        TestSolution.PASSED,
                        NOT_IMMEDIATE_REDIRECT_VIA_META_MSG, 
                        null);
        ec.check(sspHandler, selectionHandler, testSolutionHandler);
    }
    
    /**
     * @param element
     * @return whether the given element is an immediate redirection
     */
    private boolean isImmediateRedirection(Element element) {
        String contentAttributeContent = element.attr(CONTENT_ATTR);
        String[] contentAttributeValues = contentAttributeContent.split(SEMI_COLON_CHAR);
        if (contentAttributeValues != null && 
                contentAttributeValues.length == 2 && 
                Integer.valueOf(StringUtils.trim(contentAttributeValues[0]))>0 && 
                contentAttributeValues[1].toLowerCase().startsWith(URL_STR)) {
            return false;
        }
        return true;
    }

}
