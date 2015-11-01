/*
 * Tanaguru - Automated webpage assessment
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
package org.tanaguru.rules.rgaa30;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.tanaguru.rules.elementchecker.element.ChildElementPresenceChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import org.tanaguru.rules.keystore.HtmlElementStore;
import static org.tanaguru.rules.keystore.HtmlElementStore.TABLE_ELEMENT;
import static org.tanaguru.rules.keystore.MarkerStore.COMPLEX_TABLE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.DATA_TABLE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.PRESENTATION_TABLE_MARKER;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_TABLE_WITHOUT_CAPTION_IS_NOT_COMPLEX_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_TABLE_WITH_CAPTION_IS_COMPLEX_MSG;

/**
 * Implementation of the rule 5.1.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-5-1-1">the rule 5.1.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-5-1-1"> 5.1.1 rule specification</a>
 *
 */
public class Rgaa30Rule050101 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule050101() {
        super(
                new SimpleElementSelector(TABLE_ELEMENT), 

                // the complex tables are part of the scope
                new String[]{COMPLEX_TABLE_MARKER},

                // the data and presentation tables are not part of the scope
                new String[]{PRESENTATION_TABLE_MARKER, DATA_TABLE_MARKER},

                // checker for elements identified by marker
                new ChildElementPresenceChecker(
                    HtmlElementStore.CAPTION_ELEMENT, 
                    1,
                    // passed when child element is found
                    new ImmutablePair(TestSolution.PASSED, ""),
                    // failed when child element is not found
                    new ImmutablePair(TestSolution.FAILED, RemarkMessageStore.CAPTION_MISSING_ON_COMPLEX_TABLE_MSG)
                ),
                
                // checker for elements not identified by marker
                new ChildElementPresenceChecker(
                    HtmlElementStore.CAPTION_ELEMENT, 
                    // the child element is supposed to appear at least once
                    1,
                    // nmi when attribute is found
                    new ImmutablePair(TestSolution.NEED_MORE_INFO, CHECK_TABLE_WITH_CAPTION_IS_COMPLEX_MSG ),
                    // nmi when attribute is not found
                    new ImmutablePair(TestSolution.NEED_MORE_INFO, CHECK_TABLE_WITHOUT_CAPTION_IS_NOT_COMPLEX_MSG)
                    )
            );
    }

}