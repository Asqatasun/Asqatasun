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
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.ImageElementSelector;
import org.jsoup.nodes.Element;

import static org.asqatasun.entity.audit.TestSolution.*;
import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.*;
import static org.asqatasun.rules.keystore.EvidenceStore.COMPUTED_LINK_TITLE;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

/**
 * Implementation of rule 1.2.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://doc.asqatasun.org/v5/en/Business-rules/RGAA-v4/01.Images/Rule-1-2-1/">rule 1.2.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-2-1">1.2.1 rule specification</a>
 */
public class Rgaa40Rule010201 extends AbstractMarkerPageRuleImplementation {

    /**
     * The elements identified with the markers with aria hidden attributes
     */
    private final ElementHandler<Element> imgAriaHiddenWithMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements not identified by the markers with aria hidden attributes
     */
    private final ElementHandler<Element> imgAriaHiddenWithoutMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements identified with the markers with empty alternatives
     */
    private final ElementHandler<Element> imgWithEmptyAlternativeWithMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements not identified by the markers with empty alternatives
     */
    private final ElementHandler<Element> imgWithEmptyAlternativeWithoutMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements not identified by the markers with empty alternatives
     */
    private final ElementHandler<Element> imgWithNotEmptyAlternativeWithMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements not identified by the markers with not empty alternatives
     */
    private final ElementHandler<Element> imgWithNotEmptyAlternativeWithoutMarkerHandler = new ElementHandlerImpl();

    private String[] eeList = new String[]{ALT_ATTR, TITLE_ATTR, ARIA_LABEL_ATTR, COMPUTED_LINK_TITLE, ROLE_ATTR, SRC_ATTR};

    /**
     * Default constructor
     */
    public Rgaa40Rule010201() {
        super(
            // the decorative images are not part of the scope
            DECORATIVE_IMAGE_MARKER,
            // the informative images are part of the scope
            INFORMATIVE_IMAGE_MARKER);
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        extractMarkerListFromAuditParameter(sspHandler);

        new ImageElementSelector(IMG_WITH_EMPTY_ALTERNATIVE_CSS_LIKE_QUERY, true, true).
            selectElements(sspHandler, imgWithEmptyAlternativeWithoutMarkerHandler);
        sortMarkerElements(imgWithEmptyAlternativeWithMarkerHandler, imgWithEmptyAlternativeWithoutMarkerHandler);

        new ImageElementSelector(IMG_WITH_NOT_EMPTY_ALTERNATIVE_CSS_LIKE_QUERY, true, true).
            selectElements(sspHandler, imgWithNotEmptyAlternativeWithoutMarkerHandler);
        sortMarkerElements(imgWithNotEmptyAlternativeWithMarkerHandler, imgWithNotEmptyAlternativeWithoutMarkerHandler);

        new ImageElementSelector(IMG_ARIA_HIDDEN_CSS_LIKE_QUERY, true, true).
            selectElements(sspHandler, imgAriaHiddenWithoutMarkerHandler);
        sortMarkerElements(imgAriaHiddenWithMarkerHandler, imgAriaHiddenWithoutMarkerHandler);
    }

    @Override
    protected void check(
        SSPHandler sspHandler,
        TestSolutionHandler testSolutionHandler) {

        ElementChecker passedOnPresenceChecker = new ElementPresenceChecker(
            new ImmutablePair<>(PASSED, ""),
            new ImmutablePair<>(NOT_APPLICABLE, ""),
            eeList);
        passedOnPresenceChecker.check(
            sspHandler,
            imgAriaHiddenWithMarkerHandler,
            testSolutionHandler);

        ElementChecker imgWithNotEmptyAlternativeWithMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(FAILED, DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG),
            new ImmutablePair<>(NOT_APPLICABLE, ""),
            eeList);
        imgWithNotEmptyAlternativeWithMarkerChecker.check(
            sspHandler,
            imgWithNotEmptyAlternativeWithMarkerHandler,
            testSolutionHandler);

        passedOnPresenceChecker.check(
            sspHandler,
            imgWithEmptyAlternativeWithMarkerHandler,
            testSolutionHandler);

        ElementChecker imgWithNotEmptyAlternativeWithoutMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(NEED_MORE_INFO, CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG),
            new ImmutablePair<>(NOT_APPLICABLE, ""),
            eeList);
        imgWithNotEmptyAlternativeWithoutMarkerChecker.check(
            sspHandler,
            imgWithNotEmptyAlternativeWithoutMarkerHandler,
            testSolutionHandler);

        ElementChecker imgAriaHiddenWithoutMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(NEED_MORE_INFO, CHECK_NATURE_OF_IMAGE_HIDDEN_WITH_ARIA_MSG),
            new ImmutablePair<>(NOT_APPLICABLE, ""),
            eeList);
        imgAriaHiddenWithoutMarkerChecker.check(
            sspHandler,
            imgAriaHiddenWithoutMarkerHandler,
            testSolutionHandler);

        ElementChecker imgWithEmptyAlternativeWithoutMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(NEED_MORE_INFO, CHECK_NATURE_OF_IMAGE_WITHOUT_TEXTUAL_ALTERNATIVE_MSG),
            new ImmutablePair<>(NOT_APPLICABLE, ""),
            eeList);
        imgWithEmptyAlternativeWithoutMarkerChecker.check(
            sspHandler,
            imgWithEmptyAlternativeWithoutMarkerHandler,
            testSolutionHandler);

    }

    @Override
    public int getSelectionSize() {
        return this.imgWithNotEmptyAlternativeWithoutMarkerHandler.get().size()
            + this.imgWithNotEmptyAlternativeWithMarkerHandler.get().size()
            + this.imgWithEmptyAlternativeWithoutMarkerHandler.get().size()
            + this.imgWithEmptyAlternativeWithMarkerHandler.get().size()
            + this.imgAriaHiddenWithoutMarkerHandler.get().size()
            + this.imgAriaHiddenWithMarkerHandler.get().size() ;
    }

}
