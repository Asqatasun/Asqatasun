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

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.rules.elementselector.MultipleElementSelector;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG;

/**
 * Implementation of the rule 10.1.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-10-1-1">the rule 10.1.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-html5aria-liste-deployee.html#test-10-1-1"> 10.1.1 rule specification</a>
 *
 */
public class Rgaa30Rule100101 extends AbstractDetectionPageRuleImplementation {

    private static final String DEPREC_TAG_NOM = "DeprecatedRepresentationTagsV2";
    /* the total number of elements */
    int totalNumberOfElements = 0;
    
    /**
     * Default constructor
     */
    public Rgaa30Rule100101 () {
        super(
                new MultipleElementSelector(),
                // solution when at least one element is found
                TestSolution.FAILED,
                // solution when no element is found
                TestSolution.PASSED,
                // manual check message
                PRESENTATION_TAG_DETECTED_MSG,
                null
            );
    }

    @Override
    protected void select(SSPHandler sspHandler, ElementHandler selectionHandler) {
        // retrieve element from the nomenclature
        Nomenclature deprecatedHtmlTags = nomenclatureLoaderService.
                loadByCode(DEPREC_TAG_NOM);
        // add each element of the nomenclature to the selector
        MultipleElementSelector mes = (MultipleElementSelector)getElementSelector();
        for (String deprecatedTag  : deprecatedHtmlTags.getValueList()) {
            mes.addCssQuery(deprecatedTag.toLowerCase());
        }
        totalNumberOfElements = sspHandler.getTotalNumberOfElements();
        super.select(sspHandler, selectionHandler);
    }
    
     @Override
    public int getSelectionSize() {
        return totalNumberOfElements;
    }

}
