/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2012  Open-S Company
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
package org.opens.tanaguru.rules.accessiweb21;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

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
