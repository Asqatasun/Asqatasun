/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.ruleimplementation;

import org.tanaguru.rules.elementchecker.ElementChecker;
import org.tanaguru.rules.elementchecker.doctype.DoctypeHtml5Checker;

/**
 * <p>
 * This class should be overridden by concrete {@link RuleImplementation} 
 * classes which implement tests with page scope.
 * </p>
 * <p>It embeds a {@link ElementChecker} set by constructor argument to perform 
 * the check. No selection is expected here.
 * </p>
 */
public abstract class AbstractPageRuleWithDoctypeHtml5CheckerImplementation 
        extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /**
     * The default constructor
     * 
     */
   
    public AbstractPageRuleWithDoctypeHtml5CheckerImplementation() {
        super();
        setElementChecker(new DoctypeHtml5Checker());
    }
    
}