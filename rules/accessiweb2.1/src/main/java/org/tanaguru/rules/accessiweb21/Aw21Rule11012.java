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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.RuleHelper;
import org.tanaguru.rules.accessiweb21.handler.form.AbstractPageRuleFormThemeImplementation;
import org.tanaguru.rules.accessiweb21.handler.form.FormRulesHandler;
import org.w3c.dom.Node;

/**
 * Does each form field (input tag of type text, password, checkbox, radio,
 * file, or textarea and select tags), that is associated with a label
 * (label tag), pass the conditions below?
 * &lt;ul&gt;
 * &lt;li&gt; The form field has an id attribute &lt;/li&gt;
 * &lt;li&gt; The value of the id attribute is unique &lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @author jkowalczyk
 */

public class Aw21Rule11012 extends AbstractPageRuleFormThemeImplementation {

    public Aw21Rule11012() {
        super();
    }

    /**
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        super.processImpl(sspHandler);
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
        Set<Node> nodeSet = new HashSet<Node>();
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();

        nodeSet.addAll(formRulesHandler.getFormFieldImplicitlyAssociatedWithLabelList());
        resultSet.add(formRulesHandler.checkAttributeExist(
                nodeSet,
                NodeAndAttributeKeyStore.ID_ATTR,
                FormRulesHandler.ID_MISSING_MSG_CODE));
        processRemarkList.addAll(formRulesHandler.getSSPHandler().getProcessRemarkService().getRemarkList());

        nodeSet.addAll(formRulesHandler.getFormFieldExplicitlyAssociatedWithLabelList());
        resultSet.add(formRulesHandler.checkIdUnicity(nodeSet));
        processRemarkList.addAll(sspHandler.getProcessRemarkService().getRemarkList());

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                RuleHelper.synthesizeTestSolutionCollection(resultSet),
                processRemarkList);
        cleanUpRule();
        return processResult;
    }

}