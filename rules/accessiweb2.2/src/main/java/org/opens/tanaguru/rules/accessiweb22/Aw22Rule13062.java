/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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

package org.opens.tanaguru.rules.accessiweb22;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.ruleimplementation.link.AbstractDownloadableLinkRuleImplementation;
import org.opens.tanaguru.rules.elementchecker.text.TextEndsWithChecker;
import static org.opens.tanaguru.rules.keystore.AttributeStore.HREF_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_WEIGHT_MSG;
import org.opens.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 13.6.2 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-13-6-2">the rule 13.6.2 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-13-6-2"> 13.6.2 rule specification</a>
 *
 * @author jkowalczyk
 */

public class Aw22Rule13062 extends AbstractDownloadableLinkRuleImplementation {

    /* the browser readable extensions nomenclature */
    private static final String OFFICE_DOC_EXT_NOM = "DownloadableDocumentExtensions";

    /**
     * Default constructor
     */
    public Aw22Rule13062 () {
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