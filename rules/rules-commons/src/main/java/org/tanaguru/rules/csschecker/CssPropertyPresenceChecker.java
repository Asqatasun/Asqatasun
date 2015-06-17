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

package org.tanaguru.rules.csschecker;

import com.phloc.css.decl.CascadingStyleSheet;
import java.util.Arrays;
import java.util.Collection;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.cssvisitor.CssPropertyPresenceCSSVisitor;

/**
 * This checker enables to control that some css properties are present among 
 * all the selectors of the page.
 * 
 * @author jkowalczyk
 */
public class CssPropertyPresenceChecker extends SimpleCssChecker {

    /**
     * 
     * @param propertyName
     * @param pseudoSelectorList
     * @param solutionOnDetection
     * @param messageOnDetection 
     */
    public CssPropertyPresenceChecker (
            String propertyName, 
            Collection<String> pseudoSelectorList,
            TestSolution solutionOnDetection, 
            String messageOnDetection) {
        setCssVisitor(new CssPropertyPresenceCSSVisitor(
                                Arrays.asList(propertyName), 
                                pseudoSelectorList,
                                solutionOnDetection, 
                                messageOnDetection));
    }
    
    /**
     * 
     * @param propertyNameList
     * @param pseudoSelectorList
     * @param solutionOnDetection
     * @param messageOnDetection 
     */
    public CssPropertyPresenceChecker (
            Collection<String> propertyNameList,
            Collection<String> pseudoSelectorList,
            TestSolution solutionOnDetection, 
            String messageOnDetection) {
        setCssVisitor(new CssPropertyPresenceCSSVisitor(
                                propertyNameList, 
                                pseudoSelectorList,
                                solutionOnDetection,
                                messageOnDetection));
    }
    
    @Override
    public void check(
            SSPHandler sspHandler, 
            String styleSheetName, 
            CascadingStyleSheet cascadingStyleSheet, 
            TestSolutionHandler testSolutionHandler) {
        super.check(sspHandler, styleSheetName, cascadingStyleSheet, testSolutionHandler);
    }

}