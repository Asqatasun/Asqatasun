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

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jsoup.nodes.Element;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.reference.Nomenclature;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.element.ElementWithAttributePresenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import org.asqatasun.rules.elementselector.builder.CssLikeSelectorBuilder;
import static org.asqatasun.rules.keystore.AttributeStore.HEIGHT_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.WIDTH_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.ELEMENT_WITH_HEIGHT_ATTR_NOT_IMG_V2;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.ELEMENT_WITH_WITDH_ATTR_NOT_IMG_V2;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.ELEMENT_WITH_HEIGHT_ATTR_NOT_IMG;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.ELEMENT_WITH_WITDH_ATTR_NOT_IMG;
import org.asqatasun.rules.keystore.HtmlElementStore;
import static org.asqatasun.rules.keystore.RemarkMessageStore.PRESENTATION_ATTR_DETECTED_MSG;

/**
 * Implementation of rule 10.1.2 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://doc.asqatasun.org/v5/en/Business-rules/RGAA-v4/10.Presentation_of_information/Rule-10-1-2/">rule 10.1.2 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-10-1-2">10.1.2 rule specification</a>
 */
public class Rgaa40Rule100102 extends AbstractPageRuleMarkupImplementation {

    private static final String PRESENTATION_ATTR_NOM = 
                "DeprecatedRepresentationAttributesV3";
    
    private final Map<String, ElementHandler> attrElementHandlerMap = new HashMap<>();
    /* the total number of elements */
    int totalNumberOfElements = 0;

    /**
     * Default constructor
     */
    public Rgaa40Rule100102() {
        super();
    }
    
    @Override
    protected void select(SSPHandler sspHandler) {
        totalNumberOfElements = sspHandler.getTotalNumberOfElements();
        // retrieve element from the nomenclature
        Nomenclature deprecatedHtmlAttr = nomenclatureLoaderService.
                loadByCode(PRESENTATION_ATTR_NOM);
        for (String deprecatedAttr : deprecatedHtmlAttr.getValueList()) {
            SimpleElementSelector sec = 
                        new SimpleElementSelector(buildQuery(deprecatedAttr));
            ElementHandler<Element> eh = new ElementHandlerImpl();
            sec.selectElements(sspHandler, eh);
            
            attrElementHandlerMap.put(deprecatedAttr, eh);
        }   
        
        // elements with width attribute that are not img
        SimpleElementSelector secWidthAttrNotImg = 
                new SimpleElementSelector(ELEMENT_WITH_WITDH_ATTR_NOT_IMG_V2);
        ElementHandler<Element> ehWithAttrNotImg = new ElementHandlerImpl();
        secWidthAttrNotImg.selectElements(sspHandler, ehWithAttrNotImg);
            
        attrElementHandlerMap.put(WIDTH_ATTR, ehWithAttrNotImg);
        
        // elements with width attribute that are not img
        SimpleElementSelector secHeightAttrNotImg = 
                new SimpleElementSelector(ELEMENT_WITH_HEIGHT_ATTR_NOT_IMG_V2);
        ElementHandler<Element> ehHeightAttrNotImg = new ElementHandlerImpl();
        secHeightAttrNotImg.selectElements(sspHandler, ehHeightAttrNotImg);
            
        attrElementHandlerMap.put(HEIGHT_ATTR, ehHeightAttrNotImg);
    }

    @Override
    protected void check(SSPHandler sspHandler, TestSolutionHandler testSolutionHandler) {

        // Attributes checks
        for (Map.Entry<String, ElementHandler> attrElementHandlerMapEntry : 
                attrElementHandlerMap.entrySet()) {
            
            ElementChecker attrEc = new ElementWithAttributePresenceChecker(
                new ImmutablePair(TestSolution.FAILED, PRESENTATION_ATTR_DETECTED_MSG),
                new ImmutablePair(TestSolution.PASSED,""),
                attrElementHandlerMapEntry.getKey()
            );
            
            attrEc.check(
                    sspHandler, 
                    attrElementHandlerMapEntry.getValue(), 
                    testSolutionHandler);
        }
    }
    
    @Override
    public int getSelectionSize() {
        return totalNumberOfElements;
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
