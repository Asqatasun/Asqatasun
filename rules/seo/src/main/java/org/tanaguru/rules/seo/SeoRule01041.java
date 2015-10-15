/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.seo;

import org.tanaguru.entity.audit.IndefiniteResult;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractUniqueElementSiteRuleImplementation;
import static org.tanaguru.rules.keystore.RemarkMessageStore.SOURCE_CODE_IDENTICAL_TO_ANOTHER_PAGE_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.SOURCE_CODE_NOT_UNIQUE_MSG;

/**
 * For each page of a website or a group of pages, is the source code unique ?
 * 
 * @author jkowalczyk
 */
public class SeoRule01041 extends AbstractUniqueElementSiteRuleImplementation {

    /**
     * Constructor
     */
    public SeoRule01041() {
        super(
                null, 
                null,
                SOURCE_CODE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                SOURCE_CODE_NOT_UNIQUE_MSG, 
                true);
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.getProcessRemarkService().resetService();
        // The source code is encoding amoung MD5 algorithm after the adaptation
        // phase. This conversion is useless.
        IndefiniteResult result = 
                processResultDataService.getIndefiniteResult(
                        test,
                        sspHandler.getPage(), 
                        sspHandler.getSSP().getSource());
        return result;
    }

}
