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

import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.rules.seo.length.AbstractElementLengthControlPageRuleImplementation;
import org.opens.tanaguru.rules.seo.util.UniqueElementChecker;

/**
 * Test whether the Title tag of the page exceeds 70 characters?
 * 
 * @author jkowalczyk
 */
public class SeoRule06031 extends AbstractElementLengthControlPageRuleImplementation {

    public static final String MESSAGE_CODE = "TitleTagLengthExceedLimit";
    public static final String TITLE_EVIDENCE_NAME = "Title";
    public static final String MORE_THAN_ONE_TITLE_MESSAGE_CODE = "MoreThanOneTitleTag";
    private static final int URL_MAX_LENGTH = 70;
    private static final String CSS_EXPR = "head title";

    public SeoRule06031() {
        super();
        setMessageCode(MESSAGE_CODE);
        setLength(URL_MAX_LENGTH);
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        Elements elements = sspHandler.beginCssLikeSelection().
                domCssLikeSelectNodeSet(CSS_EXPR).getSelectedElements();
        if (elements.isEmpty()) {
            // if the page has no title tag, the result is NA.
            return definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                TestSolution.NOT_APPLICABLE,
                sspHandler.getRemarkList());
        } else if (elements.size() == 1) {
            setElement(elements.get(0).text());
            return super.processImpl(sspHandler);
        } else {
            return definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                TestSolution.FAILED,
                UniqueElementChecker.getNotUniqueElementProcessRemarkCollection(
                    sspHandler,
                    elements,
                    MORE_THAN_ONE_TITLE_MESSAGE_CODE,
                    TITLE_EVIDENCE_NAME));
        }
    }

}