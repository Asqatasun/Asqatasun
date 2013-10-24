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

import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.pertinence.AttributePertinenceChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.MultipleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.SRC_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.FRAME_WITH_NOT_EMPTY_TITLE_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.IFRAME_WITH_NOT_EMPTY_TITLE_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_TITLE_OF_FRAME_PERTINENCE_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.NOT_PERTINENT_TITLE_OF_FRAME_MSG;


/**
 * Implementation of the rule 1.2 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-1-2">the rule 1.2 design page.</a>
 * @see <a href="http://rgaa.net/Pertinence-des-titres-donnes-aux.html"> 1.2 rule specification
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule01021 extends AbstractPageRuleWithSelectorAndCheckerImplementation {
    
    /** The element selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new MultipleElementSelector(
                    FRAME_WITH_NOT_EMPTY_TITLE_CSS_LIKE_QUERY, 
                    IFRAME_WITH_NOT_EMPTY_TITLE_CSS_LIKE_QUERY);
    
    /** The element checker */
    private static final ElementChecker ELEMENT_CHECKER = 
              new AttributePertinenceChecker(
                    // the attribute to check
                    TITLE_ATTR, 
                    // the selection keep elements with not empty title
                    false, 
                    // compare title with src attribute
                    SRC_ATTR, 
                    // no black list comparison
                    null, 
                    //  message associated with element when title is not pertinent
                    NOT_PERTINENT_TITLE_OF_FRAME_MSG, 
                    // message associated with element when pertinence cannot be determined
                    CHECK_TITLE_OF_FRAME_PERTINENCE_MSG, 
                    // evidence elements 
                TITLE_ATTR);

    public Rgaa22Rule01021() {
        super(ELEMENT_SELECTOR, ELEMENT_CHECKER);
    }

}