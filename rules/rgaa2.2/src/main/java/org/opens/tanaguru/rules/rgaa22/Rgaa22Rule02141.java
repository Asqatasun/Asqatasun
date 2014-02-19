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

package org.opens.tanaguru.rules.rgaa22;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.opens.tanaguru.rules.elementselector.MultipleElementSelector;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.FORM_BUTTON_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.*;

/**
 * Implementation of the rule 2.14 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-2-14">the rule 2.14 design page.</a>
 * @see <a href="http://rgaa.net/Valeur-du-rapport-de-contraste-du,16.html"> 2.14 rule specification </a>
 *
 */
public class Rgaa22Rule02141 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa22Rule02141 () {
        super(
            new MultipleElementSelector(
                    IMG_ELEMENT, 
                    FORM_BUTTON_CSS_LIKE_QUERY, 
                    APPLET_ELEMENT,
                    OBJECT_ELEMENT,
                    EMBED_ELEMENT),
                TestSolution.NOT_TESTED,
                TestSolution.NOT_APPLICABLE,
                null,
                null);
    }

}