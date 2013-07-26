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
 * Test whether a not empty H1 tag is present on the page
 * 
 * @author jkowalczyk
 */
public class SeoRule07013 extends AbstractTagDetectionPageRuleImplementation {

    private static final String TAG_DETECTION_XPATH_EXPR = "//h1";
    public static final String ERROR_MESSAGE_CODE = "notRelevantH1Tag";
    public static final String CHECK_ELEMENT_MESSAGE_CODE = "checkRelevancyH1Tag";
    

    public SeoRule07013() {
        super();
        setMessageCode(ERROR_MESSAGE_CODE);
        setSelectionExpression(TAG_DETECTION_XPATH_EXPR);
        setDetectedSolution(TestSolution.NEED_MORE_INFO);
        setNotDetectedSolution(TestSolution.NOT_APPLICABLE);
        setIsRemarkCreatedOnDetection(true);
        setHasElementToBeUnique(false);
        setHasElementToBeNotNullAndContainsAlphanumericalContent(true);
        setElementName(null);
        setCheckElementMessageCode(CHECK_ELEMENT_MESSAGE_CODE);
    }

}
