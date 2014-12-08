/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.rgaa30;

import java.util.HashMap;
import java.util.Map;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.element.ElementWithAttributePresenceChecker;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import org.opens.tanaguru.rules.elementselector.builder.CssLikeSelectorBuilder;
import static org.opens.tanaguru.rules.keystore.AttributeStore.HEIGHT_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.WIDTH_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.ELEMENT_WITH_HEIGHT_ATTR_NOT_IMG;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.ELEMENT_WITH_WITDH_ATTR_NOT_IMG;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.PRESENTATION_ATTR_DETECTED_MSG;

/**
 * Implementation of the rule 10.1.2 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="https://github.com/Tanaguru/Tanaguru-rules-RGAA-3-doc/wiki/Rule-10-1-1">the rule 10.1.2 design page.</a>
 * @see <a href="https://references.modernisation.gouv.fr/sites/default/files/RGAA3/referentiel_technique.htm#test-10-1-1"> 10.1.2 rule specification</a>
 *
 */
public class Rgaa30Rule100102 extends AbstractPageRuleMarkupImplementation {

    private static final String PRESENTATION_ATTR_NOM = 
                "DeprecatedRepresentationAttributes";
    
    private final Map<String, ElementHandler> attrElementHandlerMap = new HashMap();
    /* the total number of elements */
    int totalNumberOfElements = 0;
    
    /**
     * Default constructor
     */
    public Rgaa30Rule100102 () {
        super();
    }
    
    @Override
    protected void select(SSPHandler sspHandler, ElementHandler elementHandler) {
        totalNumberOfElements = sspHandler.getTotalNumberOfElements();
        // retrieve element from the nomenclature
        Nomenclature deprecatedHtmlAttr = nomenclatureLoaderService.
                loadByCode(PRESENTATION_ATTR_NOM);
        for (String deprecatedAttr : deprecatedHtmlAttr.getValueList()) {
            SimpleElementSelector sec = 
                        new SimpleElementSelector(buildQuery(deprecatedAttr));
            ElementHandler eh = new ElementHandlerImpl();
            sec.selectElements(sspHandler, eh);
            
            attrElementHandlerMap.put(deprecatedAttr, eh);
        }   
        
        // elements with width attribute that are not img
        SimpleElementSelector secWidthAttrNotImg = 
                new SimpleElementSelector(ELEMENT_WITH_WITDH_ATTR_NOT_IMG);
        ElementHandler ehWithAttrNotImg = new ElementHandlerImpl();
        secWidthAttrNotImg.selectElements(sspHandler, ehWithAttrNotImg);
            
        attrElementHandlerMap.put(WIDTH_ATTR, ehWithAttrNotImg);
        
        // elements with width attribute that are not img
        SimpleElementSelector secHeightAttrNotImg = 
                new SimpleElementSelector(ELEMENT_WITH_HEIGHT_ATTR_NOT_IMG);
        ElementHandler ehHeightAttrNotImg = new ElementHandlerImpl();
        secHeightAttrNotImg.selectElements(sspHandler, ehHeightAttrNotImg);
            
        attrElementHandlerMap.put(HEIGHT_ATTR, ehHeightAttrNotImg);
    }

    @Override
    protected void check(SSPHandler sspHandler, ElementHandler selectionHandler, TestSolutionHandler testSolutionHandler) {

        // Attributes checks
        for (Map.Entry<String, ElementHandler> attrElementHandlerMapEntry : 
                attrElementHandlerMap.entrySet()) {
            
            ElementChecker attrEc = new ElementWithAttributePresenceChecker(
                TestSolution.FAILED, 
                TestSolution.PASSED,
                PRESENTATION_ATTR_DETECTED_MSG, 
                null,
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
