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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.AreaElementSelector;
import org.asqatasun.rules.elementselector.ImageElementSelector;
import org.jsoup.nodes.Element;

import java.util.stream.Collectors;

import static org.asqatasun.entity.audit.TestSolution.*;
import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.EvidenceStore.COMPUTED_LINK_TITLE;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

/**
 * Implementation of rule 1.2.2 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/01.Images/Rule-1-2-2.md">rule 1.2.2 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-2-2">1.2.2 rule specification</a>
 */
public class Rgaa40Rule010202 extends AbstractMarkerPageRuleImplementation {

    /**
     * The elements identified with the markers with aria hidden attributes
     */
    private final ElementHandler<Element> areaAriaHiddenWithMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements not identified by the markers with aria hidden attributes
     */
    private final ElementHandler<Element> areaAriaHiddenWithoutMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements identified with the markers with empty alternatives
     */
    private final ElementHandler<Element> areaWithEmptyAlternativeWithMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements not identified by the markers with empty alternatives
     */
    private final ElementHandler<Element> areaWithEmptyAlternativeWithoutMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements not identified by the markers with empty alternatives
     */
    private final ElementHandler<Element> areaWithNotEmptyAlternativeWithMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements not identified by the markers with not empty alternatives
     */
    private final ElementHandler<Element> areaWithNotEmptyAlternativeWithoutMarkerHandler = new ElementHandlerImpl();

    String[] eeList = new String[]{ALT_ATTR, ARIA_LABEL_ATTR, COMPUTED_LINK_TITLE, ROLE_ATTR, HREF_ATTR};

    /**
     * Default constructor
     */
    public Rgaa40Rule010202() {
        super(
            // the decorative images are not part of the scope
            DECORATIVE_IMAGE_MARKER,
            // the informative images are part of the scope
            INFORMATIVE_IMAGE_MARKER);
        setElementSelector(new ImageElementSelector(new AreaElementSelector(), true, true));
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        super.select(sspHandler);

        areaAriaHiddenWithMarkerHandler.addAll(getSelectionWithMarkerHandler().get().stream()
            .filter(Rgaa40Rule010202::ariaHiddenAttrPresent)
            .collect(Collectors.toList()));
        areaAriaHiddenWithoutMarkerHandler.addAll(getSelectionWithoutMarkerHandler().get().stream()
            .filter(Rgaa40Rule010202::ariaHiddenAttrPresent)
            .collect(Collectors.toList()));
        areaWithEmptyAlternativeWithMarkerHandler.addAll(getSelectionWithMarkerHandler().get().stream()
            .filter(e -> !ariaHiddenAttrPresent(e))
            .filter(Rgaa40Rule010202::isEmptyAlternative)
            .collect(Collectors.toList()));
        areaWithEmptyAlternativeWithoutMarkerHandler.addAll(getSelectionWithoutMarkerHandler().get().stream()
            .filter(e -> !ariaHiddenAttrPresent(e))
            .filter(Rgaa40Rule010202::isEmptyAlternative)
            .collect(Collectors.toList()));
        areaWithNotEmptyAlternativeWithMarkerHandler.addAll(getSelectionWithMarkerHandler().get().stream()
            .filter(e -> !ariaHiddenAttrPresent(e))
            .filter(e -> !isEmptyAlternative(e))
            .collect(Collectors.toList()));
        areaWithNotEmptyAlternativeWithoutMarkerHandler.addAll(getSelectionWithoutMarkerHandler().get().stream()
            .filter(e -> !ariaHiddenAttrPresent(e))
            .filter(e -> !isEmptyAlternative(e))
            .collect(Collectors.toList()));
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
            areaAriaHiddenWithMarkerHandler,
            testSolutionHandler);

        ElementChecker areaWithNotEmptyAlternativeWithMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(FAILED, DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG),
            new ImmutablePair<>(NOT_APPLICABLE, ""),
            eeList);
        areaWithNotEmptyAlternativeWithMarkerChecker.check(
            sspHandler,
            areaWithNotEmptyAlternativeWithMarkerHandler,
            testSolutionHandler);

        passedOnPresenceChecker.check(
            sspHandler,
            areaWithEmptyAlternativeWithMarkerHandler,
            testSolutionHandler);

        ElementChecker areaWithNotEmptyAlternativeWithoutMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(NEED_MORE_INFO, CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG),
            new ImmutablePair<>(NOT_APPLICABLE, ""),
            eeList);
        areaWithNotEmptyAlternativeWithoutMarkerChecker.check(
            sspHandler,
            areaWithNotEmptyAlternativeWithoutMarkerHandler,
            testSolutionHandler);

        ElementChecker areaAriaHiddenWithoutMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(NEED_MORE_INFO, CHECK_NATURE_OF_IMAGE_HIDDEN_WITH_ARIA_MSG),
            new ImmutablePair<>(NOT_APPLICABLE, ""),
            eeList);
        areaAriaHiddenWithoutMarkerChecker.check(
            sspHandler,
            areaAriaHiddenWithoutMarkerHandler,
            testSolutionHandler);

        ElementChecker areaWithEmptyAlternativeWithoutMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(NEED_MORE_INFO, CHECK_NATURE_OF_IMAGE_WITHOUT_TEXTUAL_ALTERNATIVE_MSG),
            new ImmutablePair<>(NOT_APPLICABLE, ""),
            eeList);
        areaWithEmptyAlternativeWithoutMarkerChecker.check(
            sspHandler,
            areaWithEmptyAlternativeWithoutMarkerHandler,
            testSolutionHandler);

    }
    
    private static boolean ariaHiddenAttrPresent(Element element) {
        return element.attr(ROLE_ATTR).equalsIgnoreCase("presentation") ||
            element.attr(ARIA_HIDDEN_ATTR).equalsIgnoreCase("true");
    }

    private static boolean isEmptyAlternative(Element element) {
        return StringUtils.isEmpty(element.attr(ALT_ATTR)) &&
                !element.hasAttr(ARIA_LABEL_ATTR) && !element.hasAttr(ARIA_LABELLEDBY_ATTR) && !element.hasAttr(TITLE_ATTR);
    }

    @Override
    public int getSelectionSize() {
        return this.areaWithNotEmptyAlternativeWithoutMarkerHandler.get().size()
            + this.areaWithNotEmptyAlternativeWithMarkerHandler.get().size()
            + this.areaWithEmptyAlternativeWithoutMarkerHandler.get().size()
            + this.areaWithEmptyAlternativeWithMarkerHandler.get().size()
            + this.areaAriaHiddenWithoutMarkerHandler.get().size()
            + this.areaAriaHiddenWithMarkerHandler.get().size() ;
    }
}
