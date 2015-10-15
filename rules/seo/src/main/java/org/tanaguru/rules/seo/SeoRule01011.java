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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.seo;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import org.tanaguru.rules.keystore.AttributeStore;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.META_DESC_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.RemarkMessageStore.META_DESC_MISSING_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.MORE_THAN_ONE_META_DESC_MSG;

/**
 * Test whether a meta description tag is present on the page
 * 
 * @author jkowalczyk
 */
public class SeoRule01011 extends AbstractPageRuleWithSelectorAndCheckerImplementation {
    
    /**
     * Default constructor
     */
    public SeoRule01011(){
        super(
                new SimpleElementSelector(META_DESC_CSS_LIKE_QUERY), 
                
                new ElementPresenceChecker(
                    // check element has to be unique
                    true,
                    // solution when detected
                    TestSolution.PASSED,
                    // solution when not detected
                    TestSolution.FAILED,
                    // no message on detection
                    null,
                    // message when not detected
                    META_DESC_MISSING_MSG, 
                    // message when multiple elements detected
                    MORE_THAN_ONE_META_DESC_MSG, 
                    // evidence elements
                    AttributeStore.CONTENT_ATTR)
            );
    }
}