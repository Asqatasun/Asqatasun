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

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.seo.detection.AbstractTagDetectionPageRuleImplementation;

/**
 * Test whether a meta description tag is present on the page
 * 
 * @author jkowalczyk
 */
public class SeoRule01011 extends AbstractTagDetectionPageRuleImplementation {

    public static final String ERROR_MESSAGE_CODE = "MetaDescriptionTagMissing";
    private static final String TAG_DETECTION_CSS_EXPR =
            "head meta[name=description][content]";
    public static final String META_DESC_EVIDENCE_NAME = "MetaDescription";
    public static final String MORE_THAN_ONE_META_DESC_MSG_CODE = "MoreThanOneMetaDescriptionTag";

    public SeoRule01011() {
        super();
        setMessageCode(ERROR_MESSAGE_CODE);
        setSelectionExpression(TAG_DETECTION_CSS_EXPR);
        setDetectedSolution(TestSolution.PASSED);
        setNotDetectedSolution(TestSolution.FAILED);
        setIsRemarkCreatedOnDetection(false);
        setHasElementToBeUnique(true);
        setNotUniqueMessage(MORE_THAN_ONE_META_DESC_MSG_CODE);
        setNotUniqueEvidenceElement(META_DESC_EVIDENCE_NAME);
    }

}