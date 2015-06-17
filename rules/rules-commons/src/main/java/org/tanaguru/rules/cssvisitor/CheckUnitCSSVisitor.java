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

import com.phloc.css.ECSSUnit;
import com.phloc.css.decl.CSSExpressionMemberTermSimple;
import com.phloc.css.utils.CSSNumberHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.tanaguru.entity.audit.TestSolution;
import static org.tanaguru.rules.keystore.RemarkMessageStore.BAD_UNIT_TYPE_MSG;

/**
 * CSS visitor that only parse selectors regarding a media list, extract values
 * and units, and checks whether the unit doesn't belong to a forbidden unit
 * list.
 *
 * @author jkowalczyk
 */
public class CheckUnitCSSVisitor extends SimpleCssVisitor {

    private static final Pattern NON_ZERO_VALUE_PATTERN =
            Pattern.compile("0+\\.0+[a-zA-Z]*|0+[a-zA-Z]*");

    private List<ECSSUnit> checkUnitList;
    public void setUnitList(Collection<String> unitList) {
        this.checkUnitList = getECSSUnitListFromLexicalUnitBlackList(unitList);
    }

    /* the property name to visit. If null, all properties are visited.*/
    private String propertyName;
    
    /**
     * Constructor
     */
    public CheckUnitCSSVisitor() {
        super();
    }
    
    /**
     * Constructor
     * @param propertyName 
     */
    public CheckUnitCSSVisitor(String propertyName) {
        super(true); /*for counter purpose*/
        this.propertyName = propertyName;
    }

    @Override
    protected void checkCSSExpressionMemberTermSimple(CSSExpressionMemberTermSimple exprMember) {
        String exprValue = exprMember.getOptimizedValue();
        ECSSUnit unit = extractUnitFromExpressionValue(exprValue);
        // if the selector has not unit, or the value is 0
        if (unit == null) {
            return;
        }
        String value = extractValueFromExpressionValueAndUnit(exprValue, unit);
        checkUnitAndValue(unit, value);
    }

    /**
     * 
     * @param exprValue
     * @return 
     */
    private ECSSUnit extractUnitFromExpressionValue(String exprValue) {
        return CSSNumberHelper.getMatchingUnitInclPercentage(exprValue);
    }

    /**
     * 
     * @param exprValue
     * @param unit
     * @return 
     */
    private String extractValueFromExpressionValueAndUnit(String exprValue, ECSSUnit unit) {
        String value = exprValue.substring(0, exprValue.length() - unit.getName().length());
        try {
            Float.valueOf(value);
        } catch (NumberFormatException nfe) {
            Logger.getLogger(this.getClass()).debug("not numerical value " + exprValue + " " + unit + " " +value);
            return null;
        }
        return value;
    }

    private void checkUnitAndValue(ECSSUnit unit, String value) {
        if (value != null && !NON_ZERO_VALUE_PATTERN.matcher(value).matches() && unit != null) {
            if (checkUnitList.contains(unit)) {
                Logger.getLogger(this.getClass()).debug("wrong unit encountered " + getCurrentSelector() + " " + unit.getName());
                addCssCodeRemark(
                        TestSolution.FAILED,
                        BAD_UNIT_TYPE_MSG,
                        unit.getName());
            }
        }
    }

    /**
     * Return a list of ECCSUnit from a unit blacklist. The units in the blacklist 
     * are defined 
     * 
     * @param blacklist
     * @return 
     */
    private List<ECSSUnit> getECSSUnitListFromLexicalUnitBlackList(Collection<String> blacklist) {
        List<ECSSUnit> unitList = new ArrayList<>();
        for (String unit : blacklist) {
            unitList.add(ECSSUnit.getFromNameOrNull(unit));
        }
        return unitList;
    }

    @Override
    protected void checkCSSDeclarationProperty(String property) {
        if (StringUtils.isBlank(propertyName)) {
            return;
        }
        excludeProperty = !StringUtils.equalsIgnoreCase(propertyName, property);
    }

}