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
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.cssvisitor.CheckUnitCSSVisitor;

/**
 * This checker enables to control that not relatives units are not used based 
 * on a nomenclature.
 * 
 * @author jkowalczyk
 */
public class ForbiddenUnitChecker extends SimpleCssChecker {

    private static final String RELATIVE_CSS_UNIT_NOM = "RelativeCssUnits";

    /**
     * 
     * Constructor
     */
    public ForbiddenUnitChecker () {
        setCssVisitor(new CheckUnitCSSVisitor());
    }
    
    /**
     * Constructor
     * @param propertyName
     */
    public ForbiddenUnitChecker (String propertyName) {
        setCssVisitor(new CheckUnitCSSVisitor(propertyName));
    }
    
    @Override
    public void check(
            SSPHandler sspHandler, 
            String styleSheetName, 
            CascadingStyleSheet cascadingStyleSheet, 
            TestSolutionHandler testSolutionHandler) {
        ((CheckUnitCSSVisitor)getCssVisitor()).setUnitList(
                getNomenclatureLoaderService().loadByCode(RELATIVE_CSS_UNIT_NOM).getValueList());
        super.check(sspHandler, styleSheetName, cascadingStyleSheet, testSolutionHandler);
    }

}