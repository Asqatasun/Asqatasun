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
package org.asqatasun.rules.rgaa30;

import java.util.Collections;
import org.apache.commons.lang3.tuple.ImmutablePair;
import static org.asqatasun.entity.audit.TestSolution.*;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.rules.elementchecker.CompositeChecker;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.attribute.AttributePresenceChecker;
import org.asqatasun.rules.elementchecker.text.TextEmptinessChecker;
import org.asqatasun.rules.elementselector.ImageElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.ALT_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.SRC_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.IMG_WITH_ALT_NOT_IN_LINK_WITHOUT_LONGDESC_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import org.asqatasun.rules.keystore.RemarkMessageStore;
import static org.asqatasun.rules.keystore.RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.DECORATIVE_ELEMENT_WITH_TITLE_ATTR_MSG;
import org.asqatasun.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 1.2.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a
 * href="http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-2-1.html">the rule 1.2.1
 * design page.</a>
 *
 * @see <a
 * href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-2-1">
 * 1.2.1 rule specification</a>
 *
 */
public class Rgaa30Rule010201  extends AbstractMarkerPageRuleImplementation {

    public Rgaa30Rule010201() {
        super(
                // the decorative images are part of the scope
                DECORATIVE_IMAGE_MARKER,
                // the informative images are not part of the scope
                INFORMATIVE_IMAGE_MARKER);
                setElementSelector(new ImageElementSelector(IMG_WITH_ALT_NOT_IN_LINK_WITHOUT_LONGDESC_CSS_LIKE_QUERY, true, false));
                setMarkerElementChecker(getMarkerElementChecker());
                setRegularElementChecker(getLocalRegularElementChecker());
    }
    
    /**
     * 
     * @return the checker user for marked elements
     */
    private ElementChecker getMarkerElementChecker() {
        CompositeChecker ec = new CompositeChecker(
                    new TextEmptinessChecker(
                        new TextAttributeOfElementBuilder(ALT_ATTR),
                        new ImmutablePair(PASSED,""),
                        new ImmutablePair(FAILED,DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG),
                        ALT_ATTR,
                        TITLE_ATTR,
                        SRC_ATTR),
                    new AttributePresenceChecker(
                        TITLE_ATTR,
                        new ImmutablePair(FAILED,DECORATIVE_ELEMENT_WITH_TITLE_ATTR_MSG),
                        new ImmutablePair(PASSED,""),
                        ALT_ATTR,
                        TITLE_ATTR,
                        SRC_ATTR));
        ec.setIsOrCombinaison(false);
        return ec;
    }
    
    /**
     * 
     * @return the checker user for not marked elements
     */
    private ElementChecker getLocalRegularElementChecker() {
        
        CompositeChecker compositeChecker = new CompositeChecker(ALT_ATTR, TITLE_ATTR, SRC_ATTR);
        
        compositeChecker.addChecker(
                    new TextEmptinessChecker(
                        new TextAttributeOfElementBuilder(ALT_ATTR),
                        new ImmutablePair(PASSED,""),
                        new ImmutablePair(FAILED,"")));
        compositeChecker.addChecker(
                    new AttributePresenceChecker(
                        TITLE_ATTR,
                        new ImmutablePair(FAILED,""),
                        new ImmutablePair(PASSED,"")));
        
        compositeChecker.setIsOrCombinaison(false);
        compositeChecker.addCheckMessageFromSolution(
                PASSED,
                Collections.singletonMap(
                        NEED_MORE_INFO, 
                        RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_EMPTY_ALT_MSG));
        compositeChecker.addCheckMessageFromSolution(
                FAILED,
                Collections.singletonMap(
                        NEED_MORE_INFO, 
                        RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_EMPTY_ALT_MSG));
        
        return compositeChecker;
    }
}