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
import org.tanaguru.entity.reference.Nomenclature;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.w3c.dom.Node;

/**
 * This rule tests if forbidden representation tags are used in the source
 * code.
 * We use the specific nomenclature "DeprecatedRepresentationTags" to determine
 * the presence of these
 * @author jkowalczyk
 */
public class Aw21Rule10011 extends AbstractPageRuleImplementation {

    private static final String XPATH_EXPR =
            "//*";
    private static final String MESSAGE_CODE =
            "DeprecatedRepresentationTagFound";
    private static final String DEPREC_TAG_NOM = "DeprecatedRepresentationTags";

    public Aw21Rule10011() {
        super();
    }

    /**
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        Nomenclature deprecatedHtmlTags = nomenclatureLoaderService.
                loadByCode(DEPREC_TAG_NOM);

        sspHandler.beginSelection().
                selectDocumentNodes(deprecatedHtmlTags.getValueList());

        TestSolution testSolution = TestSolution.PASSED;

        for (Node node : sspHandler.getSelectedElementList()) {
            testSolution = TestSolution.FAILED;
            sspHandler.getProcessRemarkService().addSourceCodeRemark(
                    testSolution,
                    node,
                    MESSAGE_CODE,
                    node.getNodeName());
        }

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                sspHandler.getRemarkList());
        processResult.setElementCounter(
                sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR).
                getSelectedElementList().size());
        return processResult;
    }
    
}