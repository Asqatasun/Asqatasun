/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */

package org.tanaguru.rules.elementchecker.doctype;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.NomenclatureElement;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import static org.tanaguru.rules.keystore.RemarkMessageStore.INVALID_DOCTYPE_MSG;

/**
 * 
 * This class checks whether the doctype is valid regarding two nomenclatures
 * of doctypes, the case-sensitive ones and the case-insensitive ones.
 */
public class DoctypeValidityChecker extends NomenclatureBasedElementChecker {

    private static final String CASE_SENSITIVE_DOCTYPE_NOM = 
            "RecommendedDoctypeDeclarations";
    private static final String CASE_INSENSITIVE_DOCTYPE_NOM = 
            "RecommendedCaseInsensitiveDoctypeDeclarations";
    
    /**
     * Default constructor
     */
    public DoctypeValidityChecker() {
        super();
    }

     @Override
    protected void doCheck(
             SSPHandler sspHandler, 
             Elements elements, 
             TestSolutionHandler testSolutionHandler) {
         String doctype = sspHandler.getSSP().getDoctype();
         // if the page doesn't have any doctype declaration, the test is 
         // not applicable
         if (StringUtils.isBlank(doctype)) {
             testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
             return;
         }
         
         if (!doesDoctypeBelongToNom(doctype, CASE_INSENSITIVE_DOCTYPE_NOM, false) && 
                ! doesDoctypeBelongToNom(doctype, CASE_SENSITIVE_DOCTYPE_NOM, true)){

            testSolutionHandler.addTestSolution(getFailureSolution());
            addProcessRemark(getFailureSolution(),INVALID_DOCTYPE_MSG);
            
        } else {
            testSolutionHandler.addTestSolution(getSuccessSolution());
        }
    }

    /**
     * 
     * @param doctype
     * @param nomenclatureName
     * @param isCaseSensitive
     * @return whether the given doctype belongs to a nomenclature of doctype
     */
    private boolean doesDoctypeBelongToNom(
            String doctype, 
            String nomenclatureName, 
            boolean isCaseSensitive) {

        for (NomenclatureElement ne : getNomenclatureLoaderService().loadByCode(nomenclatureName).getElementList()) {
            if (isCaseSensitive) {
                if (ne.getLabel().equals(doctype)) {
                    return true;
                }
            } else {
                if (ne.getLabel().equalsIgnoreCase(doctype)) {
                    return true;
                }
            }
        }
        return false;
    }

    
}