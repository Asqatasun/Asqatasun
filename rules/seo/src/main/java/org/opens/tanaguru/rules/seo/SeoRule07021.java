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
 * This rule tests if each page has one and only one &lt;h1&gt; tag
 * @author jkowalczyk
 */
public class SeoRule07021 extends AbstractTagDetectionPageRuleImplementation {

    public static final String ERROR_MESSAGE_CODE = "H1TagMissing";
    private static final String TAG_DETECTION_XPATH_EXPR =
            "body h1";
    public static final String H1_EVIDENCE_NAME = "H1";
    public static final String MORE_THAN_ONE_H1_MSG_CODE = "MoreThanOneH1Tag";

    public SeoRule07021() {
        super();
        setMessageCode(ERROR_MESSAGE_CODE);
        setSelectionExpression(TAG_DETECTION_XPATH_EXPR);
        setDetectedSolution(TestSolution.PASSED);
        setNotDetectedSolution(TestSolution.FAILED);
        setIsRemarkCreatedOnDetection(false);
        setHasElementToBeUnique(true);
        setNotUniqueMessage(MORE_THAN_ONE_H1_MSG_CODE);
        setNotUniqueEvidenceElement(H1_EVIDENCE_NAME);
    }

}
