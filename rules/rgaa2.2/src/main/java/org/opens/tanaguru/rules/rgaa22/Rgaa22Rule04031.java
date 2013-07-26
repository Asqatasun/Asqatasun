/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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

package org.opens.tanaguru.rules.rgaa22;

import org.opens.tanaguru.ruleimplementation.AbstractNotTestedRuleImplementation;

/**
 * Implementation of the rule 4.3 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-4-3">the rule 4.3 design page.</a>
 * @see <a href="http://rgaa.net/Pertinence-de-l-alternative,37.html"> 4.3 rule specification
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule04031 extends AbstractNotTestedRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa22Rule04031 () {
        super();
    }
    
//    public static String FAILED_MESSAGE_CODE = "textualAlternativeNotPertinent";
//    public static String MANUAL_CHECK_MESSAGE_CODE = "checkTextualAlternativePertinence";
//    private static String JQUERY_EXPR = ""
//            + "area[alt], "
//            + "input[type=image][alt]";
//    
//    /**
//     * Default constructor
//     */
//    public Rgaa22Rule04031 () {
//        super();
//    }
//
//    /**
//     * Concrete implementation of the 4.2 rule.
//     * @param sspHandler
//     * @return
//     *      the processResult of the test
//     */
//    @Override
//    protected ProcessResult processImpl(SSPHandler sspHandler) {
//        super.processImpl(sspHandler);
//        
//        sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(JQUERY_EXPR);
//        
//        DefiniteResult result = definiteResultFactory.create(
//                test,
//                sspHandler.getSSP().getPage(),
//                computeResult(sspHandler),
//                sspHandler.getRemarkList());
//        
//        result.setElementCounter(sspHandler.getSelectedElements().size());
//        
//        cleanUpRule();
//        return result;
//    }
//
//    /**
//     * Process regarding the selection.
//     * @param sspHandler
//     * @return 
//     *      The solution of the test
//     */
//    private TestSolution computeResult (SSPHandler sspHandler){
//        Elements elements = sspHandler.getSelectedElements();
//        if (elements.isEmpty()) {
//            return TestSolution.NOT_APPLICABLE;
//        }
//        
//        Map<Element,String> elementsMap = new LinkedHashMap<Element, String>();
//        for (Element el : elements) {
//            elementsMap.put(el,el.attr(ALT_ATTR).trim());
//        }
//        
//        return RuleCheckHelper.checkTextElementPertinence(
//                elementsMap, 
//                null, 
//                FAILED_MESSAGE_CODE, 
//                MANUAL_CHECK_MESSAGE_CODE);
//    }

}