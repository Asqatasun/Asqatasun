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

package org.asqatasun.rules.rgaa22;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jsoup.nodes.Element;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.reference.Nomenclature;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementchecker.element.ElementWithAttributePresenceChecker;
import org.asqatasun.rules.elementselector.MultipleElementSelector;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import org.asqatasun.rules.elementselector.builder.CssLikeSelectorBuilder;
import org.asqatasun.rules.keystore.HtmlElementStore;
import static org.asqatasun.rules.keystore.RemarkMessageStore.PRESENTATION_ATTR_DETECTED_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG;

/**
 * Implementation of the rule 7.8 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.old-dot-org.org/en/content/rgaa22-rule-7-8">the rule 7.8 design page.</a>
 * @see <a href="http://rgaa.net/Absence-d-attributs-ou-d-elements.html"> 7.8 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule07081 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    private static final String PRESENTATION_TAG_NOM = "DeprecatedRepresentationTagsV2";
    private static final String PRESENTATION_ATTR_NOM = "DeprecatedRepresentationAttributesV2";
    
    private final Map<String, ElementHandler> attrElementHandlerMap = 
            new HashMap<>();
    
    
    private final ElementHandler<Element> deprecatedElementHandler = 
            new ElementHandlerImpl();

    /**
     * Default constructor
     */
    public Rgaa22Rule07081 () {
        super();
        // Retrieve all elements of the page and store to main elementHandler
        // for couting purpose.
        setElementSelector(new SimpleElementSelector("*"));
    }
    
    @Override
    protected void select(SSPHandler sspHandler) {
        
        super.select(sspHandler);

        // retrieve element from the nomenclature
        Nomenclature deprecatedHtmlTags = nomenclatureLoaderService.
                loadByCode(PRESENTATION_TAG_NOM);
        // add each element of the nomenclature to the selector
        MultipleElementSelector mes = new MultipleElementSelector();
        for (String deprecatedTag  : deprecatedHtmlTags.getValueList()) {
            mes.addCssQuery(deprecatedTag.toLowerCase());
        }   
        
        mes.selectElements(sspHandler, deprecatedElementHandler);
        
        // retrieve element from the nomenclature
        Nomenclature deprecatedHtmlAttr = nomenclatureLoaderService.
                loadByCode(PRESENTATION_ATTR_NOM);
        for (String deprecatedAttr : deprecatedHtmlAttr.getValueList()) {
            SimpleElementSelector sec = new SimpleElementSelector(
                    buildQuery(deprecatedAttr));
            ElementHandler eh = new ElementHandlerImpl();
            sec.selectElements(sspHandler, eh);
            
            attrElementHandlerMap.put(deprecatedAttr, eh);
        }   
    }

    @Override
    protected void check(SSPHandler sspHandler, TestSolutionHandler testSolutionHandler) {
 
        // Tags check
        ElementChecker ec = new ElementPresenceChecker(
                TestSolution.FAILED, 
                TestSolution.PASSED,
                PRESENTATION_TAG_DETECTED_MSG, 
                null);
        
        ec.check(sspHandler, deprecatedElementHandler, testSolutionHandler);
        
        // Attributes checks
        for (Map.Entry<String, ElementHandler> attrElementHandlerMapEntry : attrElementHandlerMap.entrySet()) {
            
            ElementChecker attrEc = new ElementWithAttributePresenceChecker(
                new ImmutablePair(TestSolution.FAILED, PRESENTATION_ATTR_DETECTED_MSG),
                new ImmutablePair(TestSolution.PASSED,""),
                attrElementHandlerMapEntry.getKey()
            );
            
            attrEc.check(sspHandler, attrElementHandlerMapEntry.getValue(), testSolutionHandler);
        }
    }
    
        /**
     * 
     * @param attributeName
     * @return return the query regarding the attributeName excluding the svg 
     * elements 
     */
    private String buildQuery(String attributeName) {
        StringBuilder strb = new StringBuilder();
        strb.append(CssLikeSelectorBuilder.
                        buildSelectorFromElementDifferentFromAndAttribute(
                            HtmlElementStore.SVG_ELEMENT, 
                            attributeName));
        strb.append(CssLikeSelectorBuilder.
                        buildSelectorFromAttributeAndParentDifferentFrom(
                            HtmlElementStore.SVG_ELEMENT, 
                            attributeName));
        return strb.toString();
    }
}