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
package org.opens.tanaguru.rules.cssvisitor;

import com.phloc.css.ECSSUnit;
import com.phloc.css.decl.CSSExpressionMemberTermSimple;
import com.phloc.css.utils.CSSNumberHelper;
import java.util.Collection;
import org.opens.tanaguru.entity.audit.TestSolution;

/**
 * CSS visitor that only parse selectors regarding a media list and detect the
 * one that define a property among a settable list of property
 *
 * @author jkowalczyk
 */
public class CssPropertyPresenceCSSVisitor extends SimpleCssVisitor {

    private Collection<String> cssPropertyList;
    public void setUnitList(Collection<String> cssPropertyList) {
        this.cssPropertyList = cssPropertyList;
    }

    /*
     * Constructor
     */
    public CssPropertyPresenceCSSVisitor(Collection<String> cssPropertyList) {
        super();
        this.cssPropertyList = cssPropertyList;
    }

    @Override
    protected void checkCSSExpressionMemberTermSimple(CSSExpressionMemberTermSimple exprMember) {
        String exprValue = exprMember.getOptimizedValue();
        ECSSUnit unit = extractUnitFromExpressionValue(exprValue);
        // if the selector has not unit, or the value is 0
        if (unit == null) {
            return;
        }
    }

    /**
     * 
     * @param exprValue
     * @return 
     */
    private ECSSUnit extractUnitFromExpressionValue(String exprValue) {
        return CSSNumberHelper.getMatchingUnitInclPercentage(exprValue);
    }

    @Override
    protected void checkCSSDeclarationProperty(String property) {
        if (cssPropertyList.contains(property)) {
            addCssCodeRemark(TestSolution.FAILED, "CssPropertyDetected", property);
        }
    }

}