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

import org.tanaguru.entity.audit.DefiniteResult;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * * This rule tests if all the iframe tags have a "title" attribute
 * @author jkowalczyk
 */
public class Aw21Rule02012 extends AbstractPageRuleImplementation {

    public static final String XPATH_EXPR = "//IFRAME";

    public Aw21Rule02012() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR);
        sspHandler.getProcessRemarkService().addEvidenceElement(EvidenceKeyStore.SRC_EE);
        TestSolution checkResult = sspHandler.checkAttributeExists(NodeAndAttributeKeyStore.TITLE_ATTR);

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                sspHandler.getRemarkList());

        result.setElementCounter(sspHandler.getSelectedElementList().size());
        return result;
    }

}