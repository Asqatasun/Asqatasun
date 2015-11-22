/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2015  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.asqatasun.rules.elementchecker.markup;

import org.jsoup.select.Elements;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementCheckerImpl;

/**
 * 
 * @author jkowalczyk
 */
public class MarkupChecker extends ElementCheckerImpl {

    /**
     * Default constructor
     */
    public MarkupChecker() {
        super();
    }

     @Override
    protected void doCheck(
             SSPHandler sspHandler, 
             Elements elements, 
             TestSolutionHandler testSolutionHandler) {
//         boolean isHtmlValid = MarkupValidator.getInstance().isHtmlValid(
//                        sspHandler.getPage().getId(), 
//                        sspHandler.getSSP().getDOM());
//         if (isHtmlValid) {
//             testSolutionHandler.addTestSolution(TestSolution.PASSED);
//         } else {
//             testSolutionHandler.addTestSolution(TestSolution.FAILED);
//         }
    }

}