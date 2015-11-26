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

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.DATA_TABLE_MARKUP_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.HtmlElementStore.TABLE_ELEMENT;
import static org.asqatasun.rules.keystore.MarkerStore.COMPLEX_TABLE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.DATA_TABLE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.PRESENTATION_TABLE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

/**
 * Implementation of the rule 5.8.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-5-8-1">the rule 5.8.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-5-8-1"> 5.8.1 rule specification</a>
 *
 */
public class Rgaa30Rule050801 extends AbstractMarkerPageRuleImplementation {

    /** 
     * Tables not identified as presentation table that does not contain
     * data table markup
     */
    private final ElementHandler<Element> notIdentifiedTableWithoutDataTableMarkup = 
            new ElementHandlerImpl();
    /** 
     * Tables identified as presentation table that does not contain
     * data table markup
     */
    private final ElementHandler<Element> presentationTableWithoutDataTableMarkup = 
            new ElementHandlerImpl();
    
    /** The local element counter */
    private int tableCounter = 0;
    
    /**
     * Default constructor
     */
    public Rgaa30Rule050801() {
        super(
                new SimpleElementSelector(TABLE_ELEMENT),

                // the presentation tables are not part of the scope
                new String[]{PRESENTATION_TABLE_MARKER},
                
                // the data and complex tables are part of the scope
                new String[]{DATA_TABLE_MARKER,COMPLEX_TABLE_MARKER},

                // checker for elements identified by marker
                new ElementPresenceChecker(
                    // failed when element is found
                    new ImmutablePair(TestSolution.FAILED, PRESENTATION_TABLE_WITH_FORBIDDEN_MARKUP_MSG),
                    // passed when element is not found
                    new ImmutablePair(TestSolution.NOT_APPLICABLE, "")
                ),
                
                // checker for elements not identified by marker
                new ElementPresenceChecker(
                    // nmi when element is found
                    new ImmutablePair(TestSolution.NEED_MORE_INFO, CHECK_TABLE_IS_DATA_TABLE_MSG),
                    // nmi when element is not found
                    new ImmutablePair(TestSolution.NOT_APPLICABLE, "")
                )
            );
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        super.select(sspHandler);
        
        if (getSelectionWithoutMarkerHandler().isEmpty() && 
                getSelectionWithMarkerHandler().isEmpty()) {
            return;
        }
        
        tableCounter = getSelectionWithoutMarkerHandler().get().size() + 
                       getSelectionWithMarkerHandler().get().size();
        
        // extract not identified tables with data table markup
        extractTableWithDataTableMarkup(
                    getSelectionWithoutMarkerHandler(), 
                    notIdentifiedTableWithoutDataTableMarkup);
        
        // extract presentation tables with data table markup
        extractTableWithDataTableMarkup(
                    getSelectionWithMarkerHandler(), 
                    presentationTableWithoutDataTableMarkup);
    }
    
    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {
        super.check(sspHandler, testSolutionHandler);
        ElementChecker ec;
        if (!notIdentifiedTableWithoutDataTableMarkup.isEmpty()) {
            ec = new ElementPresenceChecker(
                        // nmi when element is found
                        new ImmutablePair(TestSolution.NEED_MORE_INFO, CHECK_TABLE_IS_PRESENTATION_TABLE_MSG),
                        // na when element is not found
                        new ImmutablePair(TestSolution.NOT_APPLICABLE, "")
            );
            ec.check(
                    sspHandler, 
                    notIdentifiedTableWithoutDataTableMarkup, 
                    testSolutionHandler);
        }
        if (!presentationTableWithoutDataTableMarkup.isEmpty()) {
            ec = new ElementPresenceChecker(
                        // passed when element is foundexit
                        new ImmutablePair(TestSolution.PASSED, ""),
                        // na when element is not found
                        new ImmutablePair(TestSolution.NOT_APPLICABLE, "")
            );
            ec.check(
                    sspHandler, 
                    presentationTableWithoutDataTableMarkup, 
                    testSolutionHandler);
        }
    }
 
    /**
     * 
     * @param sspHandler
     * @param elementHandler 
     * @param elementHandlerWithoutDataTableMarkup
     */
    private void extractTableWithDataTableMarkup(
                ElementHandler<Element> elementHandler, 
                ElementHandler<Element> elementHandlerWithoutDataTableMarkup) {
        
        Elements elementsWithMarkup = new Elements();
        
        for (Element el : elementHandler.get()) {
            if (el.select(DATA_TABLE_MARKUP_CSS_LIKE_QUERY).size() > 0) {
                elementsWithMarkup.add(el);
            } else if (elementHandlerWithoutDataTableMarkup != null) {
                elementHandlerWithoutDataTableMarkup.add(el);
            }
        }
        elementHandler.clean().addAll(elementsWithMarkup);
    }
    
    @Override
    public int getSelectionSize() {
        return tableCounter;
    }
    
}
