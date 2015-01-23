/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
package org.opens.tanaguru.rules.rgaa30;

import java.util.Iterator;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.opens.tanaguru.rules.elementchecker.pertinence.AttributePertinenceChecker;
import org.opens.tanaguru.rules.elementselector.ImageElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.*;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.IMG_WITH_ALT_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.opens.tanaguru.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.*;
import org.opens.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 1.3.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="https://github.com/Tanaguru/Tanaguru-rules-RGAA-3-doc/wiki/Rule-1-3-1">the rule 1.3.1 design page.</a>
 * @see <a href="https://references.modernisation.gouv.fr/sites/default/files/RGAA3/referentiel_technique.htm#test-1-2-5"> 1.3.1 rule specification</a>
 *
 */
public class Rgaa30Rule010301 extends AbstractMarkerPageRuleImplementation {

    /** The name of the nomenclature that handles the image file extensions */
    private static final String IMAGE_FILE_EXTENSION_NOM = "ImageFileExtensions";

    public Rgaa30Rule010301() {
        super(
                new ImageElementSelector(IMG_WITH_ALT_CSS_LIKE_QUERY, true, false),

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
                    // evidence elements
                    ALT_ATTR, 
                    SRC_ATTR),
                
                // checker for elements not identified by marker
                new AttributePertinenceChecker(
                    ALT_ATTR,
                    // check emptiness
                    true,
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
                    // evidence elements
                    ALT_ATTR, 
                    SRC_ATTR)
            );
    }
    
    @Override
    protected void select(SSPHandler sspHandler) {
        super.select(sspHandler);
        Iterator<Element> iter = getSelectionWithoutMarkerHandler().get().iterator();
        // The elements with a longdesc attribute are seen as informative. 
        // They are added to the selection with marker
        while (iter.hasNext()) {
            Element el = iter.next();
            if (el.hasAttr(LONGDESC_ATTR)){
                iter.remove();
                getSelectionWithMarkerHandler().add(el);
            }
        }
    }

}
