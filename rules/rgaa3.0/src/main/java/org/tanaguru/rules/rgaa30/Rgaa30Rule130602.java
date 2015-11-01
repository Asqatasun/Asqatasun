/*
 * Tanaguru - Automated webpage assessment
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

package org.tanaguru.rules.rgaa30;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.link.AbstractDownloadableLinkRuleImplementation;
import org.tanaguru.rules.elementchecker.text.TextEndsWithChecker;
import static org.tanaguru.rules.keystore.AttributeStore.HREF_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.tanaguru.rules.keystore.RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_WEIGHT_MSG;
import org.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 13.6.2 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-13-6-2">the rule 13.6.2 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-13-6-2"> 13.6.2 rule specification</a>
 *
 * @author jkowalczyk
 */

public class Rgaa30Rule130602 extends AbstractDownloadableLinkRuleImplementation {

    /* the browser readable extensions nomenclature */
    private static final String OFFICE_DOC_EXT_NOM = "DownloadableDocumentExtensions";

    /**
     * Default constructor
     */
    public Rgaa30Rule130602 () {
        super(
                new TextEndsWithChecker(
                    // the href attribute is tested
                    new TextAttributeOfElementBuilder(HREF_ATTR), 
                    // the nomenclature listing the extensions to test
                    OFFICE_DOC_EXT_NOM, 
                    // the result when detected
                    TestSolution.NEED_MORE_INFO, 
                    // the message when detected
                    DOWNLOADABLE_FILE_DETECTED_CHECK_WEIGHT_MSG, 
                    // the evidence elements
                    HREF_ATTR, 
                    TITLE_ATTR)
            );
    }

}
