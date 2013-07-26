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
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * * This rule tests if all the iframe tags have a "title" attribute
 * @author jkowalczyk
 */
public class SeoRule03012 extends AbstractPageRuleImplementation {

    public static final String SRC_EVIDENCE_ELEMENT = "src";
    public static final String CSS_EXPR = "iframe";
    public static final String TITLE_ATTRIBUTE = "title";

    public SeoRule03012() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        Elements elements = sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(
                CSS_EXPR).getSelectedElements();
        TestSolution checkResult = TestSolution.PASSED;
        if (elements.isEmpty()) {
            checkResult = TestSolution.NOT_APPLICABLE;
        }
        for (Element el : elements) {
            if (!el.hasAttr(TITLE_ATTRIBUTE)) {
                sspHandler.getProcessRemarkService().addSourceCodeRemarkOnElement(checkResult, el, "attributeMissing");
                checkResult = TestSolution.FAILED;
            }
        }
        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                sspHandler.getRemarkList());

        result.setElementCounter(sspHandler.getSelectedElements().size());
        return result;
    }
}
