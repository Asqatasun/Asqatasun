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
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * For each Web page, is the &lt;a href="http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mDTD"&gt;
 * document type&lt;/a&gt; (doctype tag) available?
 * @author jkowalczyk
 */
public class Aw21Rule08011 extends AbstractPageRuleImplementation {

    private static final String MESSAGE_CODE = "DoctypeMissing";
    /**
     *
     */
    public Aw21Rule08011() {
        super();
    }
 
    /**
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        // explicit reset call to the ProcessRemark service, cause the rule
        // doesn't make any selection an DOM.
        sspHandler.getProcessRemarkService().resetService();
        
        List<ProcessRemark> processRemarkList =  new ArrayList<ProcessRemark>();
        TestSolution testSolution = TestSolution.PASSED;
        String doctype = sspHandler.getSSP().getDoctype();
        if (StringUtils.isBlank(doctype)) {
            testSolution = TestSolution.FAILED;
            processRemarkList.add(
                    sspHandler.getProcessRemarkService().createProcessRemark(
                    TestSolution.FAILED,
                    MESSAGE_CODE));
        } 

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                processRemarkList);

        return processResult;
    }
}
