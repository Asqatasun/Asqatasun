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

package org.asqatasun.rules.rgaa32016;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.link.AbstractDownloadableLinkRuleImplementation;
import org.asqatasun.rules.elementchecker.text.TextEndsWithChecker;
import static org.asqatasun.rules.keystore.AttributeStore.HREF_ATTR;
import static org.asqatasun.rules.keystore.RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG;
import org.asqatasun.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 13.7.1 of the referential RGAA 3.2016
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.2016/13.Consultation/Rule-13-7-1.html">the rule 13.7.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-13-7-1">13.7.1 rule specification</a>
 *
 * @author jkowalczyk
 */

public class Rgaa32016Rule130701 extends AbstractDownloadableLinkRuleImplementation {
    
    /* the office document extensions nomenclature */
    private static final String OFFICE_DOC_EXT_NOM = "OfficeDocumentExtensions";

    /**
     * Default constructor
     */
    public Rgaa32016Rule130701  () {
        super(
                new TextEndsWithChecker(
                    // the href attribute is tested
                    new TextAttributeOfElementBuilder(HREF_ATTR), 
                    // the nomenclature listing the extensions to test
                    OFFICE_DOC_EXT_NOM, 
                    // the result when detected
                    TestSolution.NEED_MORE_INFO, 
                    // the message when detected
                    OFFICE_DOCUMENT_DETECTED_MSG, 
                    // the evidence elements
                    HREF_ATTR)
            );
    }
    
}
