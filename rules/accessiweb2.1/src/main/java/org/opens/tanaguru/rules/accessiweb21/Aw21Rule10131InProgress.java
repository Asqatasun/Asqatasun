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

package org.opens.tanaguru.rules.accessiweb21;

import java.util.Collection;
import java.util.List;
import org.opens.tanaguru.contentadapter.css.CSSOMDeclaration;
import org.opens.tanaguru.contentadapter.css.CSSOMRule;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * Implementation of the rule 10.13.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-10-13-1">the rule 10.13.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-10-13-1"> 10.13.1 rule specification</a>
 *
 * @author jkowalczyk
 */

public class Aw21Rule10131InProgress extends AbstractPageRuleImplementation {

    private static String DISPLAY_PROPERTY = "display";
    private static String VISIBILITY_PROPERTY = "visibility";
    private static String NONE_PROPERTY_VALUE = "none";
    private static String HIDDEN_PROPERTY_VALUE = "hidden";
    
    public Aw21Rule10131InProgress() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        
        Collection<CSSOMRule> selectedCSSRuleCollection = 
                 sspHandler.beginSelection().selectAllRules().getSelectedCSSOMRuleList();
        
        TestSolution testResult = TestSolution.NOT_APPLICABLE;
        
        for (CSSOMRule workingRule : selectedCSSRuleCollection) {
            List<CSSOMDeclaration> declarations = workingRule.getDeclarations();
            for (CSSOMDeclaration declaration : declarations) {
                if ((declaration.getProperty().equalsIgnoreCase(DISPLAY_PROPERTY) && 
                        declaration.getValue().equalsIgnoreCase(NONE_PROPERTY_VALUE)) || 
                        (declaration.getProperty().equalsIgnoreCase(VISIBILITY_PROPERTY) && 
                        declaration.getValue().equalsIgnoreCase(HIDDEN_PROPERTY_VALUE))) {
                    testResult = TestSolution.NEED_MORE_INFO;
//                    sspHandler.getProcessRemarkService().addCssCodeRemark(
//                            testResult, 
//                            workingRule, 
//                            attrName, 
//                            getFileNameFromCssomRule(workingRule));
                } 
           }
        }

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testResult,
                sspHandler.getRemarkList());

        result.setElementCounter(sspHandler.getCssSelectorNumber());
        return result;
    }

}