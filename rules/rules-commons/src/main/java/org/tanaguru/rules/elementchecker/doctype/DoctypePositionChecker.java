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
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementCheckerImpl;
import static org.tanaguru.rules.keystore.RemarkMessageStore.BAD_DOCTYPE_LOCATION_MSG;

/**
 * 
 * This class checks whether the doctype is well-positioned on the page, i.e
 * before the html tag.
 */
public class DoctypePositionChecker extends ElementCheckerImpl {

    private static final String DOCTYPE_KEY = "<!doctype";
    private static final String HTML_ELEMENT_KEY = "<html";

    /**
     * Default constructor
     */
    public DoctypePositionChecker() {
        super();
    }

     @Override
    protected void doCheck(
             SSPHandler sspHandler, 
             Elements elements, 
             TestSolutionHandler testSolutionHandler) {
         SSP ssp = sspHandler.getSSP();
         
         // if the page doesn't have any doctype declaration, the test is 
         // not applicable
         if (StringUtils.isBlank(ssp.getDoctype())) {
             testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
             return;
         }
         String sourcePage = ssp.getAdaptedContent();
         int indexOfDoctype = StringUtils.indexOfIgnoreCase(sourcePage,DOCTYPE_KEY);
         
         int indexOfHtmlTag = StringUtils.indexOfIgnoreCase(sourcePage,HTML_ELEMENT_KEY);
         
         if (indexOfHtmlTag < indexOfDoctype || 
                 StringUtils.indexOfIgnoreCase(sourcePage,DOCTYPE_KEY, indexOfHtmlTag) != -1) {
             
             testSolutionHandler.addTestSolution(getFailureSolution());
             addProcessRemark(getFailureSolution(), BAD_DOCTYPE_LOCATION_MSG);
             
         } else {
             
             testSolutionHandler.addTestSolution(getSuccessSolution());
             
         }
    }
    
}