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

import java.util.ArrayList;
import java.util.Collection;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.ruleimplementation.AbstractCompositePageRuleMarkupImplementation;
import org.opens.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.opens.tanaguru.rules.elementchecker.pertinence.AttributePertinenceChecker;
import org.opens.tanaguru.rules.elementselector.AreaElementSelector;
import org.opens.tanaguru.rules.elementselector.ImageElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.*;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.FORM_BUTTON_WITH_ALT_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.opens.tanaguru.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.*;
import org.opens.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 4.3 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-4-3">the rule 4.3 design page.</a>
 * @see <a href="http://rgaa.net/Pertinence-de-l-alternative,37.html"> 4.3 rule specification
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule04031 extends AbstractCompositePageRuleMarkupImplementation {

    /**
     * The name of the nomenclature that handles the image file extensions
     */
    private static final String IMAGE_FILE_EXTENSION_NOM = "ImageFileExtensions";

    /**
     * Default constructor
     */
    public Rgaa22Rule04031() {
        super();
        Collection<AbstractPageRuleMarkupImplementation> ruleCheckers =
                new ArrayList<AbstractPageRuleMarkupImplementation>();
        ruleCheckers.add(new InformativeInputImageRuleMarkupImplementation());
        ruleCheckers.add(new InformativeAreaRuleMarkupImplementation());
        setInnerRuleCheckers(ruleCheckers);
    }
    
    /**
     * Inner class that works on "marked as informative" <input type="image"> tags
     */
    private class InformativeInputImageRuleMarkupImplementation extends AbstractMarkerPageRuleImplementation {

        /**
        * Constructor
        */
        public InformativeInputImageRuleMarkupImplementation() {
            super(
                new ImageElementSelector(FORM_BUTTON_WITH_ALT_CSS_LIKE_QUERY, true, false),

                // the informative images are part of the scope
                INFORMATIVE_IMAGE_MARKER, 

                // the decorative images are not part of the scope
                DECORATIVE_IMAGE_MARKER, 

                // checker for elements identified by marker
                new AttributePertinenceChecker(
                    ALT_ATTR,
                    // check emptiness
                    true,
                    // compare with src attribute
                    new TextAttributeOfElementBuilder(SRC_ATTR),
                    // compare attribute value with nomenclature
                    IMAGE_FILE_EXTENSION_NOM,
                    // not pertinent message
                    NOT_PERTINENT_ALT_MSG,
                    // manual check message
                    CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG,
                    // evidence elemeents
                    ALT_ATTR, 
                    SRC_ATTR),

                // checker for elements not identified by marker
                new AttributePertinenceChecker(
                    ALT_ATTR,
                    // no emptiness check 
                    false,
                    // compare with src attribute
                    new TextAttributeOfElementBuilder(SRC_ATTR),
                    // compare attribute value with nomenclature
                    IMAGE_FILE_EXTENSION_NOM,
                    // override not pertinent result
                    TestSolution.NEED_MORE_INFO,
                    // not pertinent message
                    CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG,
                    // manual check message
                    CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG,
                    // evidence elemeents
                    ALT_ATTR, 
                    SRC_ATTR)
            );
        }
    }

    /**
     * Inner class that works on "marked as informative" <area> tags
     */
    private class InformativeAreaRuleMarkupImplementation extends AbstractMarkerPageRuleImplementation {

        /**
         * Constructor
         */
        public InformativeAreaRuleMarkupImplementation() {
            super(
                new ImageElementSelector(new AreaElementSelector(true, false, false), true, false),

                // the informative images are part of the scope
                INFORMATIVE_IMAGE_MARKER, 

                // the decorative images are not part of the scope
                DECORATIVE_IMAGE_MARKER, 

                // checker for elements identified by marker
                new AttributePertinenceChecker(
                    ALT_ATTR,
                    // check emptiness
                    true,
                    // compare with src attribute
                    new TextAttributeOfElementBuilder(HREF_ATTR),
                    // compare attribute value with nomenclature
                    IMAGE_FILE_EXTENSION_NOM,
                    // not pertinent message
                    NOT_PERTINENT_ALT_MSG,
                    // manual check message
                    CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG,
                    // evidence elements
                    ALT_ATTR, 
                    HREF_ATTR),

                // checker for elements not identified by marker
                new AttributePertinenceChecker(
                    ALT_ATTR,
                    // no emptiness check
                    false,
                    // compare with src attribute
                    new TextAttributeOfElementBuilder(HREF_ATTR),
                    // compare attribute value with nomenclature
                    IMAGE_FILE_EXTENSION_NOM,
                    // override not pertinent result
                    TestSolution.NEED_MORE_INFO,
                    // not pertinent message
                    CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG,
                    // manual check message
                    CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG,
                    // evidence elements
                    ALT_ATTR, 
                    HREF_ATTR)
            );
        }
    }
}