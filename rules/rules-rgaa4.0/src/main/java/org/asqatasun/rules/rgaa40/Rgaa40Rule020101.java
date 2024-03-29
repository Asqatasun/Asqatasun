/**
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.rules.rgaa40;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.rules.elementchecker.attribute.AttributePresenceChecker;
import org.asqatasun.rules.elementselector.MultipleElementSelector;

import static org.asqatasun.rules.keystore.AttributeStore.SRC_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.HtmlElementStore.FRAME_ELEMENT;
import static org.asqatasun.rules.keystore.HtmlElementStore.IFRAME_ELEMENT;
import static org.asqatasun.rules.keystore.RemarkMessageStore.TITLE_ATTR_MISSING_MSG;

/**
 * Implementation of rule 2.1.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://doc.asqatasun.org/v5/en/Business-rules/RGAA-v4/02.Frames/Rule-2-1-1/">rule 2.1.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-2-1-1">2.1.1 rule specification</a>
 */
public class Rgaa40Rule020101 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /**
     * Default constructor
     */
    public Rgaa40Rule020101() {
        super(
            new MultipleElementSelector(IFRAME_ELEMENT, FRAME_ELEMENT),

            new AttributePresenceChecker(
                TITLE_ATTR,
                // passed when attribute is found, empty message
                new ImmutablePair(TestSolution.PASSED, ""),
                // failed when attribute is not found, titleAttrMissing Message
                new ImmutablePair(TestSolution.FAILED, TITLE_ATTR_MISSING_MSG),
                // evidence elements
                SRC_ATTR)
        );
    }

}
