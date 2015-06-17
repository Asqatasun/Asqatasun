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
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.rules.accessiweb21.handler.form.AbstractPageRuleFormThemeImplementation;

/**
 * Does each label (label tag) that is associated with a form field
 * (input tag of type text, password, checkbox, radio, file, or textarea and
 * select tag) pass the conditions below?
 * &lt;ul&gt;
 * &lt;li&gt; The label tag has a for attribute &lt;/li&gt;
 * &lt;li&gt; The value of the for attribute is equal to the value of the id
 * attribute of the associated form field &lt;/li&gt;
 * &lt;/ul&gt;
 *
 * @author jkowalczyk
 */

public class Aw21Rule11013 extends AbstractPageRuleFormThemeImplementation {

    public Aw21Rule11013() {
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
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();

        TestSolution testSolution = formRulesHandler.checkLabelForAttribute();
        processRemarkList.addAll(formRulesHandler.getSSPHandler().getProcessRemarkService().getRemarkList());

        if (!testSolution.equals(TestSolution.FAILED) &&
                !formRulesHandler.getFormFieldExplicitlyAssociatedWithLabelList().isEmpty()) {
            testSolution = TestSolution.PASSED;
        }

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                processRemarkList);
        cleanUpRule();
        return processResult;
    }

}