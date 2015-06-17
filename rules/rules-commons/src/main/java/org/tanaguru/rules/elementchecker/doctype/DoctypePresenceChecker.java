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
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementCheckerImpl;
import static org.tanaguru.rules.keystore.RemarkMessageStore.DOCTYPE_MISSING_MSG;

/**
 * 
 * This class checks whether the doctype is present on the page.
 */
public class DoctypePresenceChecker extends ElementCheckerImpl {

    /**
     * Default constructor
     */
    public DoctypePresenceChecker() {
        super();
    }

     @Override
    protected void doCheck(
             SSPHandler sspHandler, 
             Elements elements, 
             TestSolutionHandler testSolutionHandler) {
         if (StringUtils.isBlank(sspHandler.getSSP().getDoctype())) {
             
            addProcessRemark(TestSolution.FAILED,DOCTYPE_MISSING_MSG);
            testSolutionHandler.addTestSolution(getFailureSolution());
            
        } else {
            testSolutionHandler.addTestSolution(getSuccessSolution());
        }
    }

    
}