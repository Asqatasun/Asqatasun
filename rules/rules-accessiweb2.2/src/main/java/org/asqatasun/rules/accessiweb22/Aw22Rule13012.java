/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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

package org.asqatasun.rules.accessiweb22;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.CONTENT_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.META_WITH_REFRESH_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.NOT_IMMEDIATE_REDIRECT_VIA_META_MSG;

/**
 * Implementation of the rule 13.1.2 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/40_Rules/accessiweb2.2/13.Consultation/Rule-13.1.2.html">the rule 13.1.2 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-13-1-2"> 13.1.2 rule specification</a>
 *
 */
public class Aw22Rule13012 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    private static final String URL_STR = "url";
    private static final String SEMI_COLON_CHAR = ";";
    
    ElementHandler<Element> notImmediateRedirectMeta = new ElementHandlerImpl();
    
    /**
     * Default constructor
     */
    public Aw22Rule13012 () {
        super(
                new SimpleElementSelector(META_WITH_REFRESH_CSS_LIKE_QUERY),
                new ElementPresenceChecker(
                        TestSolution.FAILED,
                        TestSolution.PASSED,
                        NOT_IMMEDIATE_REDIRECT_VIA_META_MSG, 
                        null)
        );
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        super.select(sspHandler);
        
        for (Element el : getElements().get()) {
            if (!isImmediateRedirection(el)) {
                notImmediateRedirectMeta.add(el);
            }
        }
    }
    
    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {

        if (getElements().isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        if (notImmediateRedirectMeta.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.PASSED);
            return;
        }
        super.check(sspHandler, testSolutionHandler);
    }
    
    /**
     * @param element
     * @return whether the given element is an immediate redirection
     */
    private boolean isImmediateRedirection(Element element) {
        String contentAttributeContent = element.attr(CONTENT_ATTR);
        String[] contentAttributeValues = contentAttributeContent.split(SEMI_COLON_CHAR);
        return !(contentAttributeValues != null && 
                contentAttributeValues.length == 2 &&
                Integer.valueOf(StringUtils.trim(contentAttributeValues[0]))>0 &&
                contentAttributeValues[1].toLowerCase().startsWith(URL_STR));
    }

}