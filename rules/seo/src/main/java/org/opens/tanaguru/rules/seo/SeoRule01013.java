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
 * Test whether the meta description of the page exceeds 255 characters?
 * 
 * @author jkowalczyk
 */
public class SeoRule01013 extends AbstractElementLengthControlPageRuleImplementation {

    public static final String MESSAGE_CODE = "MetaDescriptionTagLengthExceedLimit";
    public static final String META_EVIDENCE_NAME = "MetaDescription";
    public static final String MORE_THAN_ONE_META_MESSAGE_CODE = "MoreThanOneMetaTag";
    private static final int URL_MAX_LENGTH = 255;
    private static final String TAG_DETECTION_XPATH_EXPR =
            "head meta[name=description][content]";

    public SeoRule01013() {
        super();
        setMessageCode(MESSAGE_CODE);
        setLength(URL_MAX_LENGTH);
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        Elements elements = sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(TAG_DETECTION_XPATH_EXPR).getSelectedElements();
        if (elements.isEmpty()) {
            // if the page has no title tag, the result is NA.
            return definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                TestSolution.NOT_APPLICABLE,
                sspHandler.getRemarkList());
        } else if (elements.size() == 1) {
            setElement(elements.get(0).attr("content"));
            return super.processImpl(sspHandler);
        } else {
            return definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                TestSolution.FAILED,
                UniqueElementChecker.getNotUniqueElementProcessRemarkCollection(
                    sspHandler,
                    elements,
                    MORE_THAN_ONE_META_MESSAGE_CODE,
                    META_EVIDENCE_NAME));
        }
    }

}