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
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.rules.elementchecker.attribute.AttributePresenceChecker;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.SRC_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.FORM_BUTTON_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.ALT_MISSING_MSG;

/**
 * Does each form button (input tag with the type="image" attribute)
 * have an alt attribute?
 * @author jkowalczyk
 */
public class SeoRule02013 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /**
     * Default constructor
     */
    public SeoRule02013() {
        super(
                new SimpleElementSelector(FORM_BUTTON_CSS_LIKE_QUERY), 
                new AttributePresenceChecker(
                        ALT_ATTR, 
                        // passed when attribute is found
                        TestSolution.PASSED, 
                        // failed when attribute is not found
                        TestSolution.FAILED, 
                        // no message created when attribute is found
                        null, 
                        // message associated with element when attribute is not found
                        ALT_MISSING_MSG, 
                        // evidence elements
                        SRC_ATTR)
            );
    }

}