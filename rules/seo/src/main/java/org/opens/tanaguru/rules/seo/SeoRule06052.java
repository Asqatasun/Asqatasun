/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
package org.opens.tanaguru.rules.seo;

import java.util.ArrayList;
import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.entity.reference.NomenclatureElement;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * 
 * @author jkowalczyk
 */
public class SeoRule06052 extends AbstractPageRuleImplementation {

    private static final String MESSAGE_CODE = "WrongDoctypeDeclaration";
    private static final String RECOM_DOCTYPE_NOM = "RecommendedDoctypeDeclarations";
    /**
     *
     */
    public SeoRule06052() {
        super();
    }

    /**
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        Nomenclature recommendedDocytpeDeclarations =
                nomenclatureLoaderService.loadByCode(RECOM_DOCTYPE_NOM);
        List<ProcessRemark> processRemarkList =  new ArrayList<ProcessRemark>();
        
        TestSolution testSolution = TestSolution.FAILED;
        String doctype = sspHandler.getSSP().getDoctype();
        if (doctype == null || doctype.trim().isEmpty()) {
            testSolution = TestSolution.NOT_APPLICABLE;
        } else {
            for (NomenclatureElement nomElement : recommendedDocytpeDeclarations.getElementList()) {
                if (nomElement.getLabel().equals(doctype)) {
                    testSolution = TestSolution.PASSED;
                    break;
                }
            }
        }

        if (testSolution == TestSolution.FAILED) {
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