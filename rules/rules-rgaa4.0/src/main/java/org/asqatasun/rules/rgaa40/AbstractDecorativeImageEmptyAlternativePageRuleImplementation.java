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

import java.util.Collection;

import static org.asqatasun.entity.audit.TestSolution.*;
import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

public class AbstractDecorativeImageEmptyAlternativePageRuleImplementation extends AbstractMarkerPageRuleImplementation {

    /**
     * The elements identified with the markers with empty alternatives
     */
    private final ElementHandler<Element> elementWithEmptyAlternativeWithMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements not identified by the markers with empty alternatives
     */
    private final ElementHandler<Element> elementWithEmptyAlternativeWithoutMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements not identified by the markers with empty alternatives
     */
    private final ElementHandler<Element> elementWithNotEmptyAlternativeWithMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements not identified by the markers with not empty alternatives
     */
    private final ElementHandler<Element> elementWithNotEmptyAlternativeWithoutMarkerHandler = new ElementHandlerImpl();

    private final String passedElementQuery;
    private final String globalElementQuery;
    private final String[] eeList;

    public AbstractDecorativeImageEmptyAlternativePageRuleImplementation(
        String passedElementQuery,
        String globalElementQuery,
        String... eeList) {
        super(
            // the decorative images are not part of the scope
            DECORATIVE_IMAGE_MARKER,
            // the informative images are part of the scope
            INFORMATIVE_IMAGE_MARKER);
        this.passedElementQuery = passedElementQuery;
        this.globalElementQuery = globalElementQuery;
        this.eeList = eeList;
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        extractMarkerListFromAuditParameter(sspHandler);

        new ImageElementSelector(passedElementQuery, true, true).
            selectElements(sspHandler, elementWithEmptyAlternativeWithoutMarkerHandler);
        new ImageElementSelector(globalElementQuery, true, true).
            selectElements(sspHandler, elementWithNotEmptyAlternativeWithoutMarkerHandler);

        removeElementsWithChildrenWithTextOrTextualAlternative(elementWithEmptyAlternativeWithoutMarkerHandler.get());

        elementWithNotEmptyAlternativeWithoutMarkerHandler.get().removeAll(elementWithEmptyAlternativeWithoutMarkerHandler.get());

        sortMarkerElements(elementWithEmptyAlternativeWithMarkerHandler, elementWithEmptyAlternativeWithoutMarkerHandler);
        sortMarkerElements(elementWithNotEmptyAlternativeWithMarkerHandler, elementWithNotEmptyAlternativeWithoutMarkerHandler);
        
    }

    @Override
    protected void check(
        SSPHandler sspHandler,
        TestSolutionHandler testSolutionHandler) {

        ElementChecker elementWithEmptyAlternativeWithMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(PASSED, ""),
            new ImmutablePair<>(NOT_APPLICABLE, ""),
            eeList);
        elementWithEmptyAlternativeWithMarkerChecker.check(
            sspHandler,
            elementWithEmptyAlternativeWithMarkerHandler,
            testSolutionHandler);

        ElementChecker elementWithNotEmptyAlternativeWithMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(FAILED, DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG),
            new ImmutablePair<>(NOT_APPLICABLE, ""),
            eeList);
        elementWithNotEmptyAlternativeWithMarkerChecker.check(
            sspHandler,
            elementWithNotEmptyAlternativeWithMarkerHandler,
            testSolutionHandler);

        ElementChecker elementWithNotEmptyAlternativeWithoutMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(NEED_MORE_INFO, CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG),
            new ImmutablePair<>(NOT_APPLICABLE, ""),
            eeList);
        elementWithNotEmptyAlternativeWithoutMarkerChecker.check(
            sspHandler,
            elementWithNotEmptyAlternativeWithoutMarkerHandler,
            testSolutionHandler);

        ElementChecker elementWithEmptyAlternativeWithoutMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(NEED_MORE_INFO, CHECK_NATURE_OF_IMAGE_WITHOUT_TEXTUAL_ALTERNATIVE_MSG),
            new ImmutablePair<>(NOT_APPLICABLE, ""),
            eeList);
        elementWithEmptyAlternativeWithoutMarkerChecker.check(
            sspHandler,
            elementWithEmptyAlternativeWithoutMarkerHandler,
            testSolutionHandler);
    }

    @Override
    public int getSelectionSize() {
        return this.elementWithNotEmptyAlternativeWithoutMarkerHandler.get().size()
            + this.elementWithNotEmptyAlternativeWithMarkerHandler.get().size()
            + this.elementWithEmptyAlternativeWithoutMarkerHandler.get().size()
            + this.elementWithEmptyAlternativeWithMarkerHandler.get().size();
    }

    private void removeElementsWithChildrenWithTextOrTextualAlternative(Collection<Element> elements) {
        elements.removeIf(e -> e.hasText() ||
            e.children().stream().anyMatch(ce ->
                ce.hasAttr(ARIA_LABEL_ATTR) ||
                ce.hasAttr(ARIA_LABELLEDBY_ATTR) ||
                ce.hasAttr(TITLE_ATTR) ||
                ((ce.tagName().equalsIgnoreCase(TITLE_ATTR) || ce.tagName().equalsIgnoreCase("desc")) && ce.hasText())));
    }
}
