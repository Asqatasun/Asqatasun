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
package org.tanaguru.rules.cssvisitor;

import com.phloc.css.decl.CSSExpressionMemberTermSimple;
import java.util.Collection;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.TestSolution;

/**
 * CSS visitor that only parse selectors regarding a media list and detect the
 * one that define a property among a settable list of property
 *
 * @author jkowalczyk
 */
public class CssPropertyPresenceCSSVisitor extends SimpleCssVisitor {

    private Collection<String> cssPropertyList;
    public void setPropertyList(Collection<String> cssPropertyList) {
        this.cssPropertyList = cssPropertyList;
    }
    
    private Collection<String> pseudoSelectorList;
    public void setUnitList(Collection<String> cssPropertyList) {
        this.cssPropertyList = cssPropertyList;
    }

    private String messageOnDetection;
    public String getMessageOnDetection() {
        return messageOnDetection;
    }

    public void setMessageOnDetection(String messageOnDetection) {
        this.messageOnDetection = messageOnDetection;
    }
    
    private TestSolution solutionOnDetection;
    public TestSolution getSolutionOnDetection() {
        return solutionOnDetection;
    }

    public void setSolutionOnDetection(TestSolution solutionOnDetection) {
        this.solutionOnDetection = solutionOnDetection;
    }
    
    /**
     * 
     * @param cssPropertyList
     * @param pseudoSelectorList
     * @param solutionOnDetection
     * @param messageOnDetection 
     */
    public CssPropertyPresenceCSSVisitor(
                Collection<String> cssPropertyList, 
                Collection<String> pseudoSelectorList, 
                TestSolution solutionOnDetection, 
                String messageOnDetection) {
        super(true);
        this.cssPropertyList = cssPropertyList;
        if (pseudoSelectorList != null) {
            this.pseudoSelectorList = pseudoSelectorList;
        }
        this.solutionOnDetection = solutionOnDetection;
        this.messageOnDetection = messageOnDetection;
    }

    @Override
    protected void checkCSSExpressionMemberTermSimple(CSSExpressionMemberTermSimple exprMember) {
        String contentValue = exprMember.getOptimizedValue();
        // Some attributes (like "content") declare their content within ""
        // These characters are removed to work on the appropriate content
        if (contentValue.startsWith("\"") && contentValue.endsWith("\"")) {
            contentValue = StringUtils.substring(contentValue, 1, contentValue.length()-1).trim();
        }
        if (StringUtils.isNotBlank(contentValue)) {
            addCssCodeRemark(
                        solutionOnDetection,
                        messageOnDetection,
                        contentValue);
        } else {
            getSolutionHandler().addTestSolution(TestSolution.PASSED);
        }
    }

    @Override
    protected void checkCSSDeclarationProperty(String property) {
        if (StringUtils.isBlank(property)) {
            return;
        }
        if (cssPropertyList.contains(property)) {
            if (CollectionUtils.isEmpty(pseudoSelectorList)) {
                excludeProperty = false;
                return;
            } else {
                for (String pseudoSelector : pseudoSelectorList) {
                    if (getCurrentSelector().contains(pseudoSelector)) {
                        excludeProperty = false;
                        return;
                    }
                }
            }
        }
        excludeProperty = true;
    }

}