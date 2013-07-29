/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
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
 *  Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.rules.elementchecker.doctype;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.NomenclatureElement;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.INVALID_DOCTYPE_MSG;

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

            testSolutionHandler.addTestSolution(TestSolution.FAILED);
            addProcessRemark(TestSolution.FAILED,INVALID_DOCTYPE_MSG);
            
        } else {
            testSolutionHandler.addTestSolution(TestSolution.PASSED);
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