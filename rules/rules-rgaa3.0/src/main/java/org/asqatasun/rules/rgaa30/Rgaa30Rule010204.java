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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jsoup.nodes.Element;
import static org.asqatasun.entity.audit.TestSolution.*;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.*;
import static org.asqatasun.rules.keystore.HtmlElementStore.*;
import static org.asqatasun.rules.keystore.MarkerStore.*;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

/**
 * Implementation of the rule 1.2.4 of the referential Rgaa 3.0.
 *
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-1-2-4.html">the rule 1.2.4 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-2-4"> 1.2.4 rule specification</a>
 */

public class Rgaa30Rule010204 extends AbstractMarkerPageRuleImplementation {

    /**
     * Contains all the decorative SVG elements without the "img" role
     */
    private final ElementHandler<Element> decorativeSvgElementsWithoutRoleImage 
            = new ElementHandlerImpl();
    /*
    * Contains all the  SVG elements without the "img" role.
    */
    private final ElementHandler<Element> svgElementsWithoutRoleImage 
            = new ElementHandlerImpl();
    /*
    *Contains all the SVG element with title attribute or child with title attribute
    */
    private final ElementHandler<Element> titleAttrOnSvgOrChild 
            = new ElementHandlerImpl();
     /*
    *Contains all the decorative SVG element with title attribute or child with title attribute
    */
    private final ElementHandler<Element> titleAttrOnDecorativeSvgOrChild 
            = new ElementHandlerImpl();
    /*
    *Contains suspected decorative SVG elements
    */
    private final ElementHandler<Element> decorativeSvgElements
            = new ElementHandlerImpl();
    
    /*
    *Suspected decorative SVG elements
    */
    private final ElementHandler<Element> suspectedDecorativeSvgElements
            = new ElementHandlerImpl();
      /*
    *Contains SVG elements with desc or title child
    */
    private final ElementHandler<Element> svgElementsWithDescOrTitleChild 
            = new ElementHandlerImpl();
     /*
    *Contains decorative SVG elements with desc or title child
    */
    private final ElementHandler<Element> decorativeSvgElementsWithDescOrTitleChild 
            = new ElementHandlerImpl();
    /*
    *Contains  SVG elements with arria attribute or on child
    */
    private final ElementHandler<Element> ariaAttrOnSvgOrChild 
            = new ElementHandlerImpl();
    /*
    *Contains decorative SVG elements with arria attribute or on child
    */
    private final ElementHandler<Element> ariaAttrOnDecorativeSvgOrChild 
            = new ElementHandlerImpl();
    
    /**
     * Default constructor
     */
    public Rgaa30Rule010204 () {
        super(DECORATIVE_IMAGE_MARKER, INFORMATIVE_IMAGE_MARKER);
        setElementSelector(new SimpleElementSelector(SVG_NOT_IN_LINK_CSS_LIKE_QUERY));
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        super.select(sspHandler);
        
        extractMalformedPatternDetectedElements(
                getSelectionWithMarkerHandler(), // all svg identified as decorative by marker
                decorativeSvgElementsWithoutRoleImage, 
                ariaAttrOnDecorativeSvgOrChild, 
                decorativeSvgElementsWithDescOrTitleChild, 
                titleAttrOnDecorativeSvgOrChild,
                decorativeSvgElements);
        
        extractMalformedPatternDetectedElements( // all svg that are neither decorative, nor informative by marker
                getSelectionWithoutMarkerHandler(), 
                svgElementsWithoutRoleImage, 
                ariaAttrOnSvgOrChild, 
                svgElementsWithDescOrTitleChild, 
                titleAttrOnSvgOrChild,
                suspectedDecorativeSvgElements);
        
    }

    /**
     * 
     * @param svgElements
     * @param svgElementsWithoutRoleImage
     * @param ariaAttrOnSvgOrChild
     * @param svgElementsWithDescOrTitleChild
     * @param titleAttrOnSvgOrChild 
     */
    private void extractMalformedPatternDetectedElements (
            ElementHandler<Element> svgElements,
            ElementHandler<Element> svgElementsWithoutRoleImage,
            ElementHandler<Element> ariaAttrOnSvgOrChild,
            ElementHandler<Element> svgElementsWithDescOrTitleChild,
            ElementHandler<Element> titleAttrOnSvgOrChild,
            ElementHandler<Element> wellFormedSvgElements) {
        for(Element element : svgElements.get()) {
            boolean patternDetected= false;
            if (!StringUtils.equalsIgnoreCase(element.attr(ROLE_ATTR), "img")) {
                svgElementsWithoutRoleImage.add(element);
                patternDetected= true;
            }
            if (element.hasAttr(ARIA_LABEL_ATTR) || 
                    element.hasAttr(ARIA_LABELLEDBY_ATTR) ||
                    element.hasAttr(ARIA_DESCRIBEDBY_ATTR) || 
                    !element.select(ARIA_DESCRIBEDBY_CSS_LIKE_QUERY+","+ ARIA_LABEL_CSS_LIKE_QUERY+","+ARIA_LABELLEDBY_CSS_LIKE_QUERY).isEmpty()) {
                ariaAttrOnSvgOrChild.add(element);
                patternDetected= true;
            }
            if (!element.select(NOT_EMPTY_ARIA_TITLE_CSS_LIKE_QUERY+","+NOT_EMPTY_ARIA_DESC_CSS_LIKE_QUERY).isEmpty()) {
                svgElementsWithDescOrTitleChild.add(element);
                patternDetected= true;
            } 
            if (element.hasAttr(TITLE_ELEMENT) || 
                    !element.select("[title]").isEmpty()) {
                titleAttrOnSvgOrChild.add(element);
                patternDetected= true;
            }
            if (wellFormedSvgElements != null && !patternDetected) {
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
                new ImmutablePair(FAILED, DECORATIVE_SVG_WITHOUT_ROLE_IMG_ATTRIBUTE), 
                new ImmutablePair(PASSED,""));
        
        if (!decorativeSvgElementsWithoutRoleImage.isEmpty()) {
            // result is failed for sure
            ec.check(sspHandler, decorativeSvgElementsWithoutRoleImage, testSolutionHandler);
        }
        
        ec = new ElementPresenceChecker(
                new ImmutablePair(FAILED, DECORATIVE_SVG_OR_CHILDREN_WITH_ARWIA_ATTRIBUTE), 
                new ImmutablePair(PASSED,""));
        
        if (!ariaAttrOnDecorativeSvgOrChild.isEmpty()) {
            ec.check(sspHandler, ariaAttrOnDecorativeSvgOrChild, testSolutionHandler);
        }
        
        ec = new ElementPresenceChecker(
                new ImmutablePair(FAILED, DECORATIVE_SVG_WITH_NOT_EMPTY_TITLE_OR_DESC_TAGS), 
                new ImmutablePair(PASSED,""));
        
        if (!decorativeSvgElementsWithDescOrTitleChild.isEmpty()) {
            ec.check(sspHandler, decorativeSvgElementsWithDescOrTitleChild, testSolutionHandler);
        }
        
        ec = new ElementPresenceChecker(
                new ImmutablePair(FAILED, DECORATIVE_SVG_OR_CHILD_WITH_TITLE_ATTRIBUTE), 
                new ImmutablePair(PASSED,""));
        
        if (!titleAttrOnDecorativeSvgOrChild.isEmpty()) {
          //  ec.check(sspHandler, titleAttrOnSvgOrChild, testSolutionHandler);
            ec.check(sspHandler, titleAttrOnDecorativeSvgOrChild, testSolutionHandler);
        }
        if (!decorativeSvgElements.isEmpty()) {
            testSolutionHandler.addTestSolution(PASSED);
        }
      
        // This control is inhibated to avoid throwing same error as 1.3.5
        // Thus, each vectorial image not determined with marker without role="img"
        // will raise a Failed, only once, in 1.3.5
//       ec = new ElementPresenceChecker(
//                new ImmutablePair(TestSolution.NEED_MORE_INFO, SUSPECTED_INFORMATIVE_SVG_ROLE_IMAGE_MISSING_ON_SVG), 
//                new ImmutablePair(TestSolution.PASSED,""));
//        
//        if (!svgElementsWithoutRoleImage.isEmpty()) {
//            // result is failed for sure
//            ec.check(sspHandler, svgElementsWithoutRoleImage, testSolutionHandler);
//        }
        
        ec = new ElementPresenceChecker(
                new ImmutablePair(NEED_MORE_INFO, SUSPECTED_INFORMATIVE_SVG_WITH_ARIA_ATTRIBUTE_DETECTED_ON_ELEMENT_OR_CHILD), 
                new ImmutablePair(PASSED,""));
        
        if (!ariaAttrOnSvgOrChild.isEmpty()) {
            ec.check(sspHandler, ariaAttrOnSvgOrChild, testSolutionHandler);
        }
        
        ec = new ElementPresenceChecker(
                new ImmutablePair(NEED_MORE_INFO, SUSPECTED_INFORMATIVE_SVG_WITH_DESC_OR_TITLE_CHILD_TAG), 
                new ImmutablePair(PASSED,""));
        
        if (!svgElementsWithDescOrTitleChild.isEmpty()) {
            ec.check(sspHandler, svgElementsWithDescOrTitleChild, testSolutionHandler);
        }
        
        ec = new ElementPresenceChecker(
                new ImmutablePair(NEED_MORE_INFO, SUSPECTED_INFORMATIVE_SVG_WITH_TITLE_ATTRIBUTE_ON_ELEMENT_OR_CHILD), 
                new ImmutablePair(PASSED,""));
        
        if (!titleAttrOnSvgOrChild.isEmpty()) {
            ec.check(sspHandler, titleAttrOnSvgOrChild, testSolutionHandler);
        }
        
        ec = new ElementPresenceChecker(
                new ImmutablePair(NEED_MORE_INFO, SUSPECTED_WELL_FORMATED_DECORATIVE_SVG), 
                new ImmutablePair(PASSED,""));
        
        if (!suspectedDecorativeSvgElements.isEmpty()) {
            ec.check(sspHandler, suspectedDecorativeSvgElements, testSolutionHandler);
        }
       
    }

}