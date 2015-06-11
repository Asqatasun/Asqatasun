/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.CompositeChecker;
import org.opens.tanaguru.rules.elementchecker.pertinence.AttributePertinenceChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextNotIdenticalToAttributeChecker;
import org.opens.tanaguru.rules.elementselector.AreaElementSelector;
import org.opens.tanaguru.rules.elementselector.ImageElementSelector;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.*;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.HREF_ATTR;
import static org.opens.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.opens.tanaguru.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import org.opens.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 1.3.2 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a
 * href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-1-3-2">the rule 1.3.2
 * design page.</a>
 *
 * @see <a
 * href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-3-2">
 * 1.3.2 rule specification</a>
 *
 */
public class Rgaa30Rule010302 extends AbstractMarkerPageRuleImplementation {

    /**
     * The name of the nomenclature that handles the image file extensions
     */
    private static final String IMAGE_FILE_EXTENSION_NOM = "ImageFileExtensions";
    private final ElementHandler<Element> elemWithMarkerWithTitle = 
            new ElementHandlerImpl();
    private final ElementHandler<Element> elemWithoutMarkerWithTitle = 
            new ElementHandlerImpl();

    /**
     * Constructor
     */
    public Rgaa30Rule010302() {
        super(
                // the informative images are part of the scope
                INFORMATIVE_IMAGE_MARKER,
                // the decorative images are not part of the scope
                DECORATIVE_IMAGE_MARKER);
        setElementSelector(
                new ImageElementSelector(
                        new AreaElementSelector(true, false, false), true, false));

        // checker for elements identified by marker
        setMarkerElementChecker(
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
                    HREF_ATTR));
        
        // checker for elements not identified by marker
        setElementChecker(
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
                    HREF_ATTR));
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        super.select(sspHandler);
        iterateOnElementHandler(getSelectionWithMarkerHandler(), elemWithMarkerWithTitle);
        iterateOnElementHandler(getSelectionWithoutMarkerHandler(), elemWithoutMarkerWithTitle);
    }

    /**
     * We parse the elements of the original element handler to extract the ones
     * with the title attribute, add them to a new ElementHandler and remove
     * them from the original ElementHandler (if none element has a title
     * attribute, the new element handler will be empty).
     *
     * @param elementHandler
     * @param elementWithTitleHandler
     */
    private void iterateOnElementHandler(
            ElementHandler<Element> elementHandler,
            ElementHandler<Element> elementWithTitleHandler) {
        Iterator<Element> iterator = elementHandler.get().iterator();
        Element el;
        while (iterator.hasNext()) {
            el = iterator.next();
            if (el.hasAttr(TITLE_ATTR)) {
                elementWithTitleHandler.add(el);
                iterator.remove();
            }
        }
    }

    @Override
    protected void check(SSPHandler sspHandler, TestSolutionHandler testSolutionHandler) {
        super.check(sspHandler,  testSolutionHandler);
        CompositeChecker compositeCheckerForMarkedElements = 
                new CompositeChecker(
                    new TextNotIdenticalToAttributeChecker(
                        new TextAttributeOfElementBuilder(ALT_ATTR),
                        new TextAttributeOfElementBuilder(TITLE_ATTR),
                        new ImmutablePair(TestSolution.FAILED,""),
                        new ImmutablePair(TestSolution.PASSED,"")
                    ),
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
                        HREF_ATTR));
        
        CompositeChecker compositeCheckerForNotMarkedElements =
                new CompositeChecker(
                    new TextNotIdenticalToAttributeChecker(
                        new TextAttributeOfElementBuilder(ALT_ATTR),
                        new TextAttributeOfElementBuilder(TITLE_ATTR),
                        new ImmutablePair(TestSolution.NEED_MORE_INFO,""),
                        new ImmutablePair(TestSolution.NEED_MORE_INFO,"")
                    ),
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
                        HREF_ATTR));
                
        compositeCheckerForMarkedElements.check(
                sspHandler, 
                elemWithMarkerWithTitle, 
                testSolutionHandler);
        compositeCheckerForNotMarkedElements.check(
                sspHandler, 
                elemWithoutMarkerWithTitle, 
                testSolutionHandler);
    }

    @Override
    public int getSelectionSize() {
        return super.getSelectionSize()
                + elemWithMarkerWithTitle.get().size()
                + elemWithoutMarkerWithTitle.get().size();
    }
    
}
