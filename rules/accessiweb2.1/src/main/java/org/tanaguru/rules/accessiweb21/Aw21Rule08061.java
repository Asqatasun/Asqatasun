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

import java.util.ArrayList;
import java.util.List;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.Nomenclature;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.w3c.dom.Node;

/**
 *
 * @author jkowalczyk
 */
public class Aw21Rule08061 extends AbstractPageRuleImplementation {

    private static final String MESSAGE_CODE = "TitleTagNotRevelevant";
    private static final String UNEXPLICIT_PAGE_TITLE_NOM =
            "UnexplicitPageTitle";

    /**
     *
     */
    public Aw21Rule08061() {
        super();
    }

    /**
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();
        sspHandler.beginSelection().selectDocumentNodes(NodeAndAttributeKeyStore.HEAD_NODE).
                selectChildNodes(NodeAndAttributeKeyStore.TTTLE_NODE);

        Nomenclature unexplicitePageTitle =
                nomenclatureLoaderService.loadByCode(UNEXPLICIT_PAGE_TITLE_NOM);

        TestSolution testSolution =
                sspHandler.checkTextContentValue(unexplicitePageTitle.getValueList(), null);

        if (testSolution == TestSolution.FAILED) {
            for (Node node : sspHandler.getSelectedElementList()) {
                processRemarkList.add(
                        sspHandler.getProcessRemarkService().createSourceCodeRemark(
                            testSolution,
                            node,
                            MESSAGE_CODE,
                            node.getTextContent()));
            }
        }

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                processRemarkList);

        return processResult;
    }
}
