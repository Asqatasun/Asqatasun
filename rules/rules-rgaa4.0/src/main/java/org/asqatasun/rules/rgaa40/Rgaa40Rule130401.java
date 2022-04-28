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
import org.asqatasun.ruleimplementation.link.AbstractDownloadableLinkRuleImplementation;
import org.asqatasun.rules.elementchecker.text.TextEndsWithChecker;
import static org.asqatasun.rules.keystore.AttributeStore.HREF_ATTR;
import static org.asqatasun.rules.keystore.RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG2;
import org.asqatasun.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of rule 13.4.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://doc.asqatasun.org/v5/en/Business-rules/RGAA-v4/13.Consultation/Rule-13-4-1/">rule 13.4.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-13-4-1">13.4.1 rule specification</a>
 */
public class Rgaa40Rule130401 extends AbstractDownloadableLinkRuleImplementation {

    /* the office document extensions nomenclature */
    private static final String OFFICE_DOC_EXT_NOM = "OfficeDocumentExtensions";

    /**
     * Default constructor
     */
    public Rgaa40Rule130401()  {
        super(
            new TextEndsWithChecker(
                // the href attribute is tested
                new TextAttributeOfElementBuilder(HREF_ATTR),
                // the nomenclature listing the extensions to test
                OFFICE_DOC_EXT_NOM,
                // the result when detected
                TestSolution.NEED_MORE_INFO,
                // the message when detected
                OFFICE_DOCUMENT_DETECTED_MSG2,
                // the evidence elements
                HREF_ATTR)
        );
    }

}
