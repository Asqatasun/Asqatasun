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
package org.asqatasun.rules.rgaa32016;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.rules.elementchecker.element.ChildElementPresenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import org.asqatasun.rules.keystore.HtmlElementStore;
import static org.asqatasun.rules.keystore.HtmlElementStore.TABLE_ELEMENT;
import static org.asqatasun.rules.keystore.MarkerStore.DATA_TABLE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.COMPLEX_TABLE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.PRESENTATION_TABLE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

/**
 * Implementation of the rule 5.4.1 of the referential RGAA 3.2016
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.2016/05.Tables/Rule-5-4-1.html">the rule 5.4.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-5-4-1">5.4.1 rule specification</a>
 *
 */
public class Rgaa32016Rule050401 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa32016Rule050401 () {
        super(
                new SimpleElementSelector(TABLE_ELEMENT), 

                // the data tables are part of the scope
                new String[]{DATA_TABLE_MARKER},

                // the presentation and complex tables are not part of the scope
                new String[]{PRESENTATION_TABLE_MARKER, COMPLEX_TABLE_MARKER},

                // checker for elements identified by marker
                new ChildElementPresenceChecker(
                    HtmlElementStore.CAPTION_ELEMENT, 
                    1,
                    // passed when child element is found
                    new ImmutablePair(TestSolution.PASSED, ""),
                    // failed when child element is not found
                    new ImmutablePair(TestSolution.FAILED, CAPTION_MISSING_MSG)
                ),
                
                // checker for elements not identified by marker
                new ChildElementPresenceChecker(
                    HtmlElementStore.CAPTION_ELEMENT, 
                    // the child element is supposed to appear at least once
                    1,
                    // nmi when attribute is found
                    new ImmutablePair(TestSolution.NEED_MORE_INFO, CHECK_NATURE_OF_TABLE_WITH_CAPTION_MSG),
                    // nmi when attribute is not found
                    new ImmutablePair(TestSolution.NEED_MORE_INFO, CHECK_NATURE_OF_TABLE_WITHOUT_CAPTION_MSG)
                    )
            );
    }

}
