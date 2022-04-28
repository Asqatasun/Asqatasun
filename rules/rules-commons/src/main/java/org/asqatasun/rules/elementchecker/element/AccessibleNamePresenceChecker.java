/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2020  Asqatasun.org
 *
 *  This file is part of Asqatasun.
 *
 *  Asqatasun is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.rules.elementchecker.element;

import org.apache.commons.lang3.tuple.Pair;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementCheckerImpl;
import org.asqatasun.rules.elementselector.ElementWithAccessibleNameSelector;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.HtmlElementStore.*;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITHOUT_ROLE_IMG_ATTRIBUTE;

/**
 * This implementation of the {@link ElementChecker} interface checks whether an
 * accessible name is present regarding the following definition respecting the order, for all the elements:
 * - text associated via the `aria-labelledby` WAI-ARIA attribute
 * - presence of an `aria-label` WAI-ARIA attribute
 * <p>
 * If these both elements are missing, the presence of the accessible name is then checked regarding the following logical
 * (depending on the type of the tag):
 * - for the "svg", "canvas" tags and tags with "role=img", presence of text for these elements and their children
 * - for the "img" and the "input tag=img" tags, presence of the alt attribute,
 * then the presence of the title attribute if the alt attribute is missing
 * - for the "area" tags, presence of the alt attribute
 * - for the "object" and "embed" tags, presence of the title attribute, then the presence of an adjacent link or button
 * if the title attribute is missing.
 * <p>
 * Note :
 * For the svg element, we check that the "role=img" attribute is present before checking anything else.
 */
public class AccessibleNamePresenceChecker extends ElementCheckerImpl {

    private String roleImgAttributeMissingMsgCode = CHECK_NATURE_OF_IMAGE_WITHOUT_ROLE_IMG_ATTRIBUTE;

    /**
     * Constructor.
     *
     * @param detectedSolutionPair    Represents the result when the checker responds true (this element we're looking for is found)
     * @param notDetectedSolutionPair Represents the result when the checker responds false (this element we're looking for is not found)
     * @param eeAttributeNameList
     */
    public AccessibleNamePresenceChecker(
        Pair<TestSolution, String> detectedSolutionPair,
        Pair<TestSolution, String> notDetectedSolutionPair,
        String... eeAttributeNameList) {
        super(detectedSolutionPair, notDetectedSolutionPair, eeAttributeNameList);
    }

    /**
     * Constructor.
     *
     * @param detectedSolutionPair
     * @param notDetectedSolutionPair
     * @param eeAttributeNameList
     */
    public AccessibleNamePresenceChecker(
        Pair<TestSolution, String> detectedSolutionPair,
        Pair<TestSolution, String> notDetectedSolutionPair,
        String roleImgAttributeMissingMsgCode,
        String... eeAttributeNameList) {
        super(detectedSolutionPair, notDetectedSolutionPair, eeAttributeNameList);
        this.roleImgAttributeMissingMsgCode = roleImgAttributeMissingMsgCode;
    }

    @Override
    public void doCheck(SSPHandler sspHandler, Elements elements, TestSolutionHandler testSolutionHandler) {
        for (Element el : elements) {
            testSolutionHandler.addTestSolution(checkAccessibleNamePresence(sspHandler, el));
        }
    }

    /**
     * Most of the logic of the 1.1 criterion is handled by this method.
     * The multiple "if"s are a deliberate choice to fit the definition of how an accessible name is defined in the
     * glossary of the RGAAv4 referential.
     * (cf https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image)
     *
     * @param sspHandler
     * @param element
     */
    private TestSolution checkAccessibleNamePresence(SSPHandler sspHandler, Element element) {

        if (isElementExpectsAriaRoleImg(element) && !element.attr(ROLE_ATTR).contains("img")) {
            addSourceCodeRemark(getFailureSolution(), element, roleImgAttributeMissingMsgCode);
            return getFailureSolution();
        }

        if (ElementWithAccessibleNameSelector.isAccessibleNamePresent(sspHandler, element)) {
            addSourceCodeRemark(getSuccessSolution(), element, getSuccessMsgCode());
            return getSuccessSolution();
        } else {
            addSourceCodeRemark(getFailureSolution(), element, getFailureMsgCode());
            return getFailureSolution();
        }

    }

    private static boolean isElementExpectsAriaRoleImg(Element element) {
        return element.tagName().equals(SVG_ELEMENT);
    }

}
