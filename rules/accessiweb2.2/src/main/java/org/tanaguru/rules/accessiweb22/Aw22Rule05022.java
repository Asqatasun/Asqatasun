/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.tanaguru.rules.accessiweb22;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.tanaguru.rules.elementchecker.text.TextEmptinessChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.SUMMARY_ATTR;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.TABLE_WITH_SUMMARY_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.MarkerStore.DATA_TABLE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.PRESENTATION_TABLE_MARKER;
import static org.tanaguru.rules.keystore.RemarkMessageStore.*;
import org.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 5.2.2 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-5-2-2">the rule 5.2.2 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-5-2-2"> 5.2.2 rule specification</a>
 *
 */
public class Aw22Rule05022 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Aw22Rule05022() {
        super(
                new SimpleElementSelector(TABLE_WITH_SUMMARY_CSS_LIKE_QUERY),

                // the presentation tables are part of the scope
                PRESENTATION_TABLE_MARKER,
                
                // the data tables are not part of the scope
                DATA_TABLE_MARKER,

                // checker for elements identified by marker
                new TextEmptinessChecker(
                    // the attribute to check
                    new TextAttributeOfElementBuilder(SUMMARY_ATTR), 
                    // passed when attribute is found
                    TestSolution.PASSED, 
                    // failed when attribute is not found
                    TestSolution.FAILED, 
                    // no message created when attribute is found
                    null, 
                    // message associated with element when attribute is not empty
                    NOT_EMPTY_SUMMARY_MSG, 
                    // evidence elements
                    SUMMARY_ATTR),
                
                // checker for elements identified by marker
                new TextEmptinessChecker(
                    // the attribute to check
                    new TextAttributeOfElementBuilder(SUMMARY_ATTR), 
                    // nmi when attribute is empty
                    TestSolution.NEED_MORE_INFO, 
                    // nmi when attribute is not empty
                    TestSolution.NEED_MORE_INFO, 
                    // message associated with element when attribute is empty
                    CHECK_NATURE_OF_TABLE_WITH_EMPTY_SUMMARY_MSG, 
                    // message associated with element when attribute is not empty  
                    CHECK_NATURE_OF_TABLE_WITH_NOT_EMPTY_SUMMARY_MSG, 
                    // evidence elements
                    SUMMARY_ATTR)
            );
    }

}