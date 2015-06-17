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
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.Nomenclature;
import org.tanaguru.entity.reference.NomenclatureElement;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * 
 * @author jkowalczyk
 */
public class Aw21Rule08012 extends AbstractPageRuleImplementation {

    private static final String MESSAGE_CODE = "WrongDoctypeDeclaration";
    private static final String CASE_SENSITIVE_DOCTYPE_NOM = "RecommendedDoctypeDeclarations";
    private static final String CASE_INSENSITIVE_DOCTYPE_NOM = "RecommendedCaseInsensitiveDoctypeDeclarations";
    /**
     *
     */
    public Aw21Rule08012() {
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
        
        Nomenclature caseSensitiveDocytpeDeclarations =
                nomenclatureLoaderService.loadByCode(CASE_SENSITIVE_DOCTYPE_NOM);
        Nomenclature caseUnsensitiveDocytpeDeclarations =
                nomenclatureLoaderService.loadByCode(CASE_INSENSITIVE_DOCTYPE_NOM);
        List<ProcessRemark> processRemarkList =  new ArrayList<ProcessRemark>();
        
        TestSolution testSolution;
        String doctype = sspHandler.getSSP().getDoctype();
        if (StringUtils.isBlank(doctype)) {
            testSolution = TestSolution.NOT_APPLICABLE;
        } else {
            testSolution = doesDoctypeBelongToNom(doctype, caseUnsensitiveDocytpeDeclarations.getElementList(), false);
            if (testSolution != TestSolution.PASSED) {
                testSolution = doesDoctypeBelongToNom(doctype, caseSensitiveDocytpeDeclarations.getElementList(), true);
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

    /**
     * 
     * @param doctypeDeclarations
     * @param isCaseSensitive
     * @return 
     */
    private TestSolution doesDoctypeBelongToNom(String doctype, 
            Collection<? extends NomenclatureElement> doctypeDeclarations, 
            boolean isCaseSensitive) {
        TestSolution isPresent = TestSolution.FAILED;
        for (NomenclatureElement ne : doctypeDeclarations) {
            if (isCaseSensitive) {
                if (ne.getLabel().equals(doctype)) {
                    isPresent = TestSolution.PASSED;
                    break;
                }
            } else {
                if (ne.getLabel().equalsIgnoreCase(doctype)) {
                    isPresent = TestSolution.PASSED;
                    break;
                }
            }
        }
        return isPresent;
    }
    
}