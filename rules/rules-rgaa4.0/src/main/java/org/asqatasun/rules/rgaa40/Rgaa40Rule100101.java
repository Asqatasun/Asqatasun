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

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.reference.Nomenclature;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.asqatasun.rules.elementselector.MultipleElementSelector;
import static org.asqatasun.rules.keystore.RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG;

/**
 * Implementation of rule 10.1.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://doc.asqatasun.org/v5/en/Business-rules/RGAA-v4/10.Presentation_of_information/Rule-10-1-1/">rule 10.1.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-10-1-1">10.1.1 rule specification</a>
 */
public class Rgaa40Rule100101 extends AbstractDetectionPageRuleImplementation {

    private static final String DEPREC_TAG_NOM = "DeprecatedRepresentationTagsV3";
    /* the total number of elements */
    int totalNumberOfElements = 0;
    
    /**
     * Default constructor
     */
    public Rgaa40Rule100101() {
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
    protected void select(SSPHandler sspHandler) {
        // retrieve element from the nomenclature
        Nomenclature deprecatedHtmlTags = nomenclatureLoaderService.
                loadByCode(DEPREC_TAG_NOM);
        // add each element of the nomenclature to the selector
        MultipleElementSelector mes = (MultipleElementSelector)getElementSelector();
        for (String deprecatedTag  : deprecatedHtmlTags.getValueList()) {
            mes.addCssQuery(deprecatedTag.toLowerCase());
        }
        totalNumberOfElements = sspHandler.getTotalNumberOfElements();
        super.select(sspHandler);
    }
    
     @Override
    public int getSelectionSize() {
        return totalNumberOfElements;
    }

}
