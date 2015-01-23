/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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

package org.opens.tanaguru.rules.accessiweb21;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.accessiweb21.detection.AbstractTagDetectionPageRuleImplementation;

/**
 * Implementation of the rule 5.7.3 of the referential Accessiweb 2.1.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw21-rule-5-7-3">the rule 5.7.3 design page.</a>
 * @see <a href="http://www.braillenet.org/accessibilite/referentiel-aw21-en/index.php#test-5-7-3"> 5.7.3 rule specification</a>
 *
 * @author jkowalczyk
 */

public class Aw21Rule05073 extends AbstractTagDetectionPageRuleImplementation {

    public static final String MESSAGE_CODE = "ManualCheckOnElements";
    private static final String TAG_DETECTION_XPATH_EXPR ="//TABLE[descendant::TH]";

    /**
     * Default constructor
     */
    public Aw21Rule05073 () {
        super();
        setMessageCode(MESSAGE_CODE);
        setSelectionExpression(TAG_DETECTION_XPATH_EXPR);
        setDetectedSolution(TestSolution.NEED_MORE_INFO);
        setNotDetectedSolution(TestSolution.NOT_APPLICABLE);
        setIsRemarkCreatedOnDetection(true);
    }

}