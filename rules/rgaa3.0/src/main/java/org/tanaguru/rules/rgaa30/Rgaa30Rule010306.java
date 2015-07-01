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
package org.tanaguru.rules.rgaa30;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jsoup.nodes.Element;
import static org.tanaguru.entity.audit.TestSolution.FAILED;
import static org.tanaguru.entity.audit.TestSolution.NEED_MORE_INFO;
import static org.tanaguru.entity.audit.TestSolution.NOT_APPLICABLE;
import static org.tanaguru.entity.audit.TestSolution.PASSED;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementChecker;
import org.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.ARIA_LABEL_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.ROLE_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.SVG_NOT_IN_LINK_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.HtmlElementStore.DESC_ELEMENT;
import static org.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_SVG_AND_ALT_PERTINENCE_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_SVG_WITH_NOT_PERTINENT_ALT_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.INFORMATIVE_SVG_WITH_NOT_PERTINENT_ALT_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.SVG_WITHOUT_ROLE_IMAGE_MSG;

/**
 * Implementation of the rule 1.3.6 of the referential Rgaa 3.0.
 *
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-1-3-6">the rule 1.3.6 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-3-6"> 1.3.6 rule specification</a>
 */

public class Rgaa30Rule010306 extends AbstractMarkerPageRuleImplementation {

    /**
     * Contains all the informative SVG elements without the "img" role
     */
    private final ElementHandler<Element> ariaRoleMissingOnInformativeSvg 
            = new ElementHandlerImpl();
    
    /*
     * Contains all the  SVG elements without the "img" role.
     */
    private final ElementHandler<Element> ariaRoleMissingOnSvg 
            = new ElementHandlerImpl();
    
    /*
     * Contains all the SVG elements identifed as informative with an empty
     * aria-label
     */
    private final ElementHandler<Element> emptyAriaLabelOnInformativeSvg 
            = new ElementHandlerImpl();
    
    /*
     * Contains all the SVG elements not identifed as informative with an empty
     * aria-label
     */
    private final ElementHandler<Element> emptyAriaLabelOnSvg 
            = new ElementHandlerImpl();
    
    /*
     * Contains all the SVG elements identifed as informative with an empty
     * child desc tag
     */
    private final ElementHandler<Element> emptyChildDescOnInformativeSvg 
            = new ElementHandlerImpl();
    
    /*
     * Contains all the SVG elements not identifed as informative with an empty
     * child desc tag
     */
    private final ElementHandler<Element> emptyChildDescOnSvg 
            = new ElementHandlerImpl();
    
    /*
     * Contains all the SVG elements identifed as informative with an
     * aria-label different from the label attribute
     */
    private final ElementHandler<Element> ariaLabelDifferentFromTitleOnInformativeSvg 
            = new ElementHandlerImpl();
    
    /*
     * Contains all the SVG elements not identifed as informative with an 
     * aria-label different from the label attribute
     */
    private final ElementHandler<Element> ariaLabelDifferentFromTitleOnSvg
            = new ElementHandlerImpl();
    
    /*
     * Contains all the SVG elements identifed as informative with a
     * child desc tag different from the label attribute
     */
    private final ElementHandler<Element> childDescDifferentFromTitleOnInformativeSvg 
            = new ElementHandlerImpl();
    
    /*
     * Contains all the SVG elements not identifed as informative with a
     * child desc tag different from the label attribute
     */
    private final ElementHandler<Element> childDescDifferentFromTitleOnSvg 
            = new ElementHandlerImpl();
    
    /*
     * Contains all the SVG elements identifed as informative with a
     * a correct markup. Pertinence needs then a human check 
     */
    private final ElementHandler<Element> markupOkOnInformativeSvg 
            = new ElementHandlerImpl();
    
    /*
     * Contains all the SVG elements not identifed as informative with a
     * a correct markup. Pertinence needs then a human check 
     */
    private final ElementHandler<Element> markupOkOnSvg 
            = new ElementHandlerImpl();
    
    /**
     * Default constructor
     */
    public Rgaa30Rule010306 () {
        super(INFORMATIVE_IMAGE_MARKER, DECORATIVE_IMAGE_MARKER);
        setElementSelector(new SimpleElementSelector(SVG_NOT_IN_LINK_CSS_LIKE_QUERY));
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        super.select(sspHandler);
        
        extractBadConditionPatterns(
                getSelectionWithMarkerHandler(), // all svg identified as decorative by marker
                ariaRoleMissingOnInformativeSvg,
                emptyAriaLabelOnInformativeSvg,
                ariaLabelDifferentFromTitleOnInformativeSvg,
                emptyChildDescOnInformativeSvg,
                childDescDifferentFromTitleOnInformativeSvg,
                markupOkOnInformativeSvg);
        
        extractBadConditionPatterns( 
                getSelectionWithoutMarkerHandler(),    // all svg that are neither decorative, nor informative by marker
                ariaRoleMissingOnSvg,
                emptyAriaLabelOnSvg,
                ariaLabelDifferentFromTitleOnSvg,
                emptyChildDescOnSvg,
                childDescDifferentFromTitleOnSvg,
                markupOkOnSvg);
    }

    /**
     * 
     * @param svgElements
     * @param ariaRoleMissingOnSvg
     * @param emptyAriaLabelAttrOnSvg
     * @param ariaLabelDifferentFromTitleAttrOnSvg
     * @param emptyChildDescOnSvg
     * @param childDescDifferentFromTitleAttrOnSvg
     * @param wellFormedSvgElements 
     */
    private void extractBadConditionPatterns (
            ElementHandler<Element> svgElements,
            ElementHandler<Element> ariaRoleMissingOnSvg,
            ElementHandler<Element> emptyAriaLabelAttrOnSvg,
            ElementHandler<Element> ariaLabelDifferentFromTitleAttrOnSvg,
            ElementHandler<Element> emptyChildDescOnSvg,
            ElementHandler<Element> childDescDifferentFromTitleAttrOnSvg,
            ElementHandler<Element> wellFormedSvgElements) {
        for(Element element : svgElements.get()) {
            boolean patternDetected= false;
            if (!isRoleImagePresent(element)) {
                ariaRoleMissingOnSvg.add(element);
                patternDetected= true;
            }
            if (isAriaLabelExistsAndEmpty(element)) {
                emptyAriaLabelAttrOnSvg.add(element);
                patternDetected= true;
            }
            if (isAriaLabelExistsAndNotEmpty(element)
                    && isTitleExistsAndNotEmpty(element) 
                    && !StringUtils.equalsIgnoreCase(getAriaLabelAttrText(element), getTitleAttrText(element))) {
                ariaLabelDifferentFromTitleAttrOnSvg.add(element);
                patternDetected= true;
            }
            if (isChildDescExistsAndEmpty(element)) {
                emptyChildDescOnSvg.add(element);
                patternDetected= true;
            }
            if (isChildDescExistsAndNotEmpty(element)
                    && isTitleExistsAndNotEmpty(element) 
                    && !StringUtils.equalsIgnoreCase(getDescText(element), getTitleAttrText(element))) {
                childDescDifferentFromTitleAttrOnSvg.add(element);
                patternDetected= true;
            }
            if (!patternDetected) {
                wellFormedSvgElements.add(element);
            }
        }
    }
    
    @Override
    protected void check(SSPHandler sspHandler, TestSolutionHandler testSolutionHandler) {
        if (getSelectionWithMarkerHandler().isEmpty() && 
                getSelectionWithoutMarkerHandler().isEmpty()) {
            testSolutionHandler.addTestSolution(NOT_APPLICABLE);
            return;
        }
        
        ElementChecker ec = new ElementPresenceChecker(
                new ImmutablePair(FAILED, SVG_WITHOUT_ROLE_IMAGE_MSG), 
                new ImmutablePair(PASSED,""));
        
        if (!ariaRoleMissingOnInformativeSvg.isEmpty()) {
            // result is failed for sure
            ec.check(sspHandler, ariaRoleMissingOnInformativeSvg, testSolutionHandler);
        }
        
        if (!ariaRoleMissingOnSvg.isEmpty()) {
            // result is failed for sure
            ec.check(sspHandler, ariaRoleMissingOnSvg, testSolutionHandler);
        }
        
        ec = new ElementPresenceChecker(
                new ImmutablePair(NEED_MORE_INFO, INFORMATIVE_SVG_WITH_NOT_PERTINENT_ALT_MSG), 
                new ImmutablePair(NOT_APPLICABLE,""),
                ROLE_ATTR,
                ARIA_LABEL_ATTR,
                TITLE_ATTR);
        
        if (!emptyAriaLabelOnInformativeSvg.isEmpty()) {
            ec.check(sspHandler, emptyAriaLabelOnInformativeSvg, testSolutionHandler);
        }
        if (!ariaLabelDifferentFromTitleOnInformativeSvg.isEmpty()) {
            ec.check(sspHandler, ariaLabelDifferentFromTitleOnInformativeSvg, testSolutionHandler);
        }
        if (!emptyChildDescOnInformativeSvg.isEmpty()) {
            ec.check(sspHandler, emptyChildDescOnInformativeSvg, testSolutionHandler);
        }
        if (!childDescDifferentFromTitleOnInformativeSvg.isEmpty()) {
            ec.check(sspHandler, childDescDifferentFromTitleOnInformativeSvg, testSolutionHandler);
        }
        
        ec = new ElementPresenceChecker(
                new ImmutablePair(NEED_MORE_INFO, CHECK_NATURE_OF_SVG_WITH_NOT_PERTINENT_ALT_MSG), 
                new ImmutablePair(NOT_APPLICABLE,""),
                ROLE_ATTR,
                ARIA_LABEL_ATTR,
                TITLE_ATTR);
        
        if (!emptyAriaLabelOnSvg.isEmpty()) {
            ec.check(sspHandler, emptyAriaLabelOnSvg, testSolutionHandler);
        }
        if (!ariaLabelDifferentFromTitleOnSvg.isEmpty()) {
            ec.check(sspHandler, ariaLabelDifferentFromTitleOnSvg, testSolutionHandler);
        }
        if (!emptyChildDescOnSvg.isEmpty()) {
            ec.check(sspHandler, emptyChildDescOnSvg, testSolutionHandler);
        }
        if (!childDescDifferentFromTitleOnSvg.isEmpty()) {
            ec.check(sspHandler, childDescDifferentFromTitleOnSvg, testSolutionHandler);
        }
        
        ec = new ElementPresenceChecker(
                new ImmutablePair(NEED_MORE_INFO, CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG), 
                new ImmutablePair(NOT_APPLICABLE,""),
                ROLE_ATTR,
                ARIA_LABEL_ATTR,
                TITLE_ATTR);
        if (!markupOkOnInformativeSvg.isEmpty()) {
            ec.check(sspHandler, markupOkOnInformativeSvg, testSolutionHandler);
        }
        
        ec = new ElementPresenceChecker(
                new ImmutablePair(NEED_MORE_INFO, CHECK_NATURE_OF_SVG_AND_ALT_PERTINENCE_MSG), 
                new ImmutablePair(NOT_APPLICABLE,""),
                ROLE_ATTR,
                ARIA_LABEL_ATTR,
                TITLE_ATTR);
        if (!markupOkOnSvg.isEmpty()) {
            ec.check(sspHandler, markupOkOnSvg, testSolutionHandler);
        }
    }

    /**
     * 
     * @param element
     * @return whether the aria label exists and is empty
     */
    private boolean isRoleImagePresent(Element element) {
        return element.hasAttr(ROLE_ATTR) && StringUtils.equalsIgnoreCase("img", element.attr(ROLE_ATTR));
    }
    
    /**
     * 
     * @param element
     * @return whether the aria label exists and is empty
     */
    private boolean isAriaLabelExistsAndEmpty(Element element) {
        return element.hasAttr(ARIA_LABEL_ATTR) && StringUtils.isEmpty(getAriaLabelAttrText(element));
    }
    
    /**
     * 
     * @param element
     * @return whether the aria label exists and is not empty
     */
    private boolean isAriaLabelExistsAndNotEmpty(Element element) {
        return element.hasAttr(ARIA_LABEL_ATTR) && StringUtils.isNotEmpty(getAriaLabelAttrText(element));
    }
    
    /**
     * 
     * @param element
     * @return whether the aria label and the title are present, not empty 
     * and identical
     */
    private boolean isTitleExistsAndNotEmpty(Element element) {
        return element.hasAttr(TITLE_ATTR) && StringUtils.isNotEmpty(getTitleAttrText(element));
    }
    
    /**
     * 
     * @param element
     * @return whether the aria label and the title are present, not empty 
     * and identical
     */
    private boolean isChildDescExists(Element element) {
        return !element.select(DESC_ELEMENT).isEmpty();
    }
    
    /**
     * 
     * @param element
     * @return whether the child desc tag is present and empty
     */
    private boolean isChildDescExistsAndEmpty(Element element) {
        if (!isChildDescExists(element)) {
            return false;
        }
        return StringUtils.isEmpty(getDescText(element));
    }
    
    /**
     * 
     * @param element
     * @return whether the child desc tag is present and not empty
     */
    private boolean isChildDescExistsAndNotEmpty(Element element) {
        if (!isChildDescExists(element)) {
            return false;
        }
        return StringUtils.isNotEmpty(getDescText(element));
    }
    
    /**
     * 
     * @param element
     * @return the textual content of the desc tag
     */
    private String getDescText(Element element) {
        return element.select(DESC_ELEMENT).first().text(); 
    }
    
    /**
     * 
     * @param element
     * @return the textual content of the aria-label attribute
     */
    private String getAriaLabelAttrText(Element element) {
        return element.attr(ARIA_LABEL_ATTR); 
    }
    
    /**
     * 
     * @param element
     * @return the textual content of the title attribute
     */
    private String getTitleAttrText(Element element) {
        return element.attr(TITLE_ATTR); 
    }
}