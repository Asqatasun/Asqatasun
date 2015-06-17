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

import java.util.HashSet;
import java.util.Set;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.rules.accessiweb21.handler.form.AbstractPageRuleFormThemeImplementation;
import org.tanaguru.rules.accessiweb21.handler.form.FormRulesHandler;
import org.w3c.dom.Node;

/**
 * Does each form field (input tag of type text, password, checkbox, radio,
 * file, or textarea and select tags), pass one of the conditions below?
 * &lt;ul&gt;
 * &lt;li&gt; The form field has a title attribute&lt;/li&gt;
 * &lt;li&gt; A label (label tag) is associated with the form field&lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @author jkowalczyk
 */

public class Aw21Rule11011 extends AbstractPageRuleFormThemeImplementation {

    public Aw21Rule11011() {
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
        
        Set<Node> nodeSet = new HashSet<Node>();
        TestSolution testSolution = TestSolution.NOT_APPLICABLE;

        nodeSet.addAll(formRulesHandler.getFormFieldNotAssociatedWithLabelList());

        if (!nodeSet.isEmpty()) {
            testSolution = formRulesHandler.checkAttributeExist(
                    nodeSet,
                    NodeAndAttributeKeyStore.TITLE_ATTR,
                    FormRulesHandler.INVALID_FORM_FIELD_MSG_CODE);
        }

        // if no form field have been found before and the collection of
        // form fields implicitly associated with label is not empty, the result
        // is passed
        if (testSolution.equals(TestSolution.NOT_APPLICABLE) && 
                (!formRulesHandler.getFormFieldExplicitlyAssociatedWithLabelList().isEmpty() ||
                !formRulesHandler.getFormFieldImplicitlyAssociatedWithLabelList().isEmpty())) {
            testSolution = testSolution.PASSED;
        }

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                sspHandler.getRemarkList());
        cleanUpRule();
        return processResult;
    }

}