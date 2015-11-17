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

package org.asqatasun.rules.rgaa22;

import java.util.ArrayList;
import java.util.Collection;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractCompositePageRuleMarkupImplementation;
import org.asqatasun.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.rules.elementchecker.attribute.AttributePresenceChecker;
import org.asqatasun.rules.elementselector.AreaElementSelector;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.FORM_BUTTON_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.HtmlElementStore.APPLET_ELEMENT;
import static org.asqatasun.rules.keystore.HtmlElementStore.IMG_ELEMENT;
import static org.asqatasun.rules.keystore.RemarkMessageStore.ALT_MISSING_MSG;
    
/**
 * Implementation of the rule 4.1 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-4-1">the rule 4.1 design page.</a>
 * @see <a href="http://rgaa.net/Presence-de-l-attribut-alt.html"> 4.1 rule specification </a>
 *
 */
public class Rgaa22Rule04011 extends AbstractCompositePageRuleMarkupImplementation {

    /**
     * Default constructor
     */
    public Rgaa22Rule04011() {
        super();
        Collection<AbstractPageRuleMarkupImplementation> ruleCheckers =
                new ArrayList<AbstractPageRuleMarkupImplementation>();
        ruleCheckers.add(new AltPresenceForImageRuleMarkupImplementation());
        ruleCheckers.add(new AltPresenceForAreaRuleMarkupImplementation());
        ruleCheckers.add(new AltPresenceForInputImageRuleMarkupImplementation());
        ruleCheckers.add(new AltPresenceForAppletRuleMarkupImplementation());
        setInnerRuleCheckers(ruleCheckers);
    }
    
    /**
     * Inner class that checks the presence of the alt attribute for the
     * <img> tags
     */
    private class AltPresenceForImageRuleMarkupImplementation 
                extends AbstractPageRuleWithSelectorAndCheckerImplementation {

        /**
         * Default constructor
         */
        public AltPresenceForImageRuleMarkupImplementation () {
            super(
                    new SimpleElementSelector(IMG_ELEMENT),
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
                        SRC_ATTR));
        }
    }
    
    /**
     * Inner class that checks the presence of the alt attribute for the
     * <area> tags
     */
    private class AltPresenceForAreaRuleMarkupImplementation 
                extends AbstractPageRuleWithSelectorAndCheckerImplementation {

        /**
        * Default constructor
        */
        public AltPresenceForAreaRuleMarkupImplementation() {
            super(
                new AreaElementSelector(), 
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
                        // evidence elements,
                        HREF_ATTR)
            );
        }
    }
    
    /**
     * Inner class that checks the presence of the alt attribute for the
     * <input type"image"> tags
     */
    private class AltPresenceForInputImageRuleMarkupImplementation 
                extends AbstractPageRuleWithSelectorAndCheckerImplementation {
        /**
        * Default constructor
        */
        public AltPresenceForInputImageRuleMarkupImplementation() {
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
    
    /**
     * Inner class that checks the presence of the alt attribute for the
     * <applet> tags
     */
    private class AltPresenceForAppletRuleMarkupImplementation 
                extends AbstractPageRuleWithSelectorAndCheckerImplementation {
        /**
         * Default constructor
         */
        public AltPresenceForAppletRuleMarkupImplementation() {
            super(
                new SimpleElementSelector(APPLET_ELEMENT), 

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
                        CODE_ATTR)
            );
        }
    }
    
}