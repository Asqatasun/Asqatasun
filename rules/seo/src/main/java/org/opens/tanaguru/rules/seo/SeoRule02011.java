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
 * This rule tests if all images have an empty "alt" attribute
 * @author jkowalczyk
 */
public class SeoRule02011 extends AbstractPageRuleImplementation {

    public static final String CSS_EXPR =
            "img:not(a img)";
    public static final String ALT_ATTRIBUTE = "alt";
    public static final String SRC_EVIDENCE_ELEMENT = "src";

    public SeoRule02011() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(CSS_EXPR);
        TestSolution testSolution = TestSolution.PASSED;
        if (sspHandler.getSelectedElements().isEmpty()) {
            testSolution = TestSolution.NOT_APPLICABLE;
        }
        for (Element element : sspHandler.getSelectedElements()) {
            if (!element.hasAttr(ALT_ATTRIBUTE)) {
                testSolution = TestSolution.FAILED;
                sspHandler.getProcessRemarkService().addSourceCodeRemarkOnElement(testSolution, element, "altMissing");
            }
        }
        sspHandler.getProcessRemarkService().addEvidenceElement(SRC_EVIDENCE_ELEMENT);

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                testSolution,
                sspHandler.getRemarkList());
        result.setElementCounter(sspHandler.getSelectedElementNumber());
        return result;
    }
}
