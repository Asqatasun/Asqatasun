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
package org.tanaguru.rules.accessiweb21;

import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * Is each form field grouping (fieldset tag) followed by a legend (legend tag)
 * in the source code?
 *
 * @author jkowalczyk
 */

public class Aw21Rule11061 extends AbstractPageRuleImplementation {

    /**
     * The error message code
     */
    public static final String ERROR_MESSAGE_CODE = "FieldsetWithoutLegend";

    /**
     * Default constructor
     */
    public Aw21Rule11061() {
        super();
    }

    /**
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        sspHandler.beginSelection().selectDocumentNodes(NodeAndAttributeKeyStore.FIELDSET_NODE);
        sspHandler.setMessageCode(ERROR_MESSAGE_CODE);
        TestSolution testSolution = sspHandler.checkChildNodeExists(NodeAndAttributeKeyStore.LEGEND_NODE);

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                sspHandler.getRemarkList());

        return processResult;
    }
    
}