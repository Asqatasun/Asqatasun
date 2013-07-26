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

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.opens.tanaguru.rules.seo.util.UniqueElementChecker;

/**
 * Test whether the text content of the Title tag is different from the text
 * content if the H1 tag
 * 
 * @author jkowalczyk
 */
public class SeoRule07051 extends AbstractPageRuleImplementation {

    public static final String ERROR_MESSAGE_CODE = "IdenticalH1AndTitleTags";
    private static final String TITLE_EVIDENCE_NAME = "Title";
    private static final String H1_EVIDENCE_NAME = "H1";
    public static final String MORE_THAN_ONE_TITLE_MESSAGE_CODE =
            "MoreThanOneTitleTag";
    public static final String MORE_THAN_ONE_H1_MESSAGE_CODE =
            "MoreThanOneH1Tag";
    private static final String TITLE_DETECTION_CSS_EXPR = 
            "head title";
    private static final String H1_DETECTION_CSS_EXPR =
            "body h1";

    public SeoRule07051() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        
        Collection<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();
        Elements elements = sspHandler.beginCssLikeSelection().
                domCssLikeSelectNodeSet(TITLE_DETECTION_CSS_EXPR).getSelectedElements();
        
        TestSolution testSolution = TestSolution.PASSED;
        String titleTextContent = null;
        if (elements.size() == 1) {
            titleTextContent = elements.get(0).text().trim();
        } else if (elements.isEmpty()) {
            // if the page has no title tag, the result is NA.
            return definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                TestSolution.NOT_APPLICABLE,
                sspHandler.getRemarkList());
        } else {
            testSolution = TestSolution.FAILED;
            processRemarkList.addAll(UniqueElementChecker.getNotUniqueElementProcessRemarkCollection(
                    sspHandler,
                    elements,
                    MORE_THAN_ONE_TITLE_MESSAGE_CODE,
                    TITLE_EVIDENCE_NAME));
        }
        elements = sspHandler.domCssLikeSelectNodeSet(H1_DETECTION_CSS_EXPR).getSelectedElements();
        String h1TextContent = null;
        if (elements.size() == 1) {
            h1TextContent = elements.get(0).text().trim();
        } else if (elements.isEmpty()) {
            // if the page has no title tag, the result is NA.
            return definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                TestSolution.NOT_APPLICABLE,
                sspHandler.getRemarkList());
        } else {
            testSolution = TestSolution.FAILED;
            processRemarkList.addAll(UniqueElementChecker.getNotUniqueElementProcessRemarkCollection(
                    sspHandler,
                    elements,
                    MORE_THAN_ONE_H1_MESSAGE_CODE,
                    H1_EVIDENCE_NAME));
        }
        if (testSolution.equals(TestSolution.PASSED) &&
                StringUtils.equalsIgnoreCase(h1TextContent, titleTextContent)) {
            testSolution = TestSolution.FAILED;
            processRemarkList.add(sspHandler.getProcessRemarkService().
                    createProcessRemark(testSolution,ERROR_MESSAGE_CODE));
        }
        return definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                testSolution,
                processRemarkList);
    }

}