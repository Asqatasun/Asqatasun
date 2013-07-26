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
package org.opens.tanaguru.rules.seo;

import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * Does each form button (input tag with the type="image" attribute)
 * have an alt attribute?
 * @author jkowalczyk
 */
public class SeoRule02013 extends AbstractPageRuleImplementation {

    private static final String MESSAGE_CODE = "AttributeMissing";

    private static final String ALT_ATTR = "alt";

//    private static final String XPATH_EXPR =
//            "//input[(@type = 'image') and @alt]";
    private static final String CSS_EXPR =
            "input[type=image][alt]";

//    private static final String XPATH_EXPR2 =
//            "//input[(@type = 'image') and not(@alt)]";
    private static final String CSS_EXPR2 =
            "input[type=image]:not([alt])";

    public SeoRule02013(){
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        int testedElementCounter = 0;
        
        TestSolution checkResult = TestSolution.NOT_APPLICABLE;

        sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(CSS_EXPR);
        testedElementCounter += sspHandler.getSelectedElements().size();
        if (!sspHandler.getSelectedElements().isEmpty()) {
            checkResult = TestSolution.PASSED;
        }

        sspHandler.domCssLikeSelectNodeSet(CSS_EXPR2);
        if (!sspHandler.getSelectedElements().isEmpty()) {
            testedElementCounter += sspHandler.getSelectedElements().size();
            checkResult = TestSolution.FAILED;
            for (Element el : sspHandler.getSelectedElements()){
                sspHandler.getProcessRemarkService().addSourceCodeRemarkOnElement(
                        checkResult,
                        el,
                        MESSAGE_CODE);
            }
        }

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                sspHandler.getRemarkList());

        result.setElementCounter(testedElementCounter);

        return result;
    }
}
