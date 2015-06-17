/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.rulescreationdemo;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.rules.elementchecker.attribute.AttributePresenceChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;

/**
 * Implementation of the rule CheckWhetherEachLinkHaventTitleAttribute of the referential Rules creation demo.
 *
 * @see <a href="https://github.com/Tanaguru/Tanaguru/wiki/Create-a-rule-:-Getting-started"> 
 * Create a rule : Getting started</a>
 *
 * @author jkowalczyk
 */

public class CheckWhetherEachLinkHaventTitleAttribute extends 
                AbstractPageRuleWithSelectorAndCheckerImplementation {
    /**
     * Constructor
     */
    public CheckWhetherEachLinkHaventTitleAttribute() {
        super(new SimpleElementSelector("a"), // The ElementSelector implementation
              new AttributePresenceChecker( // The ElementChecker implementation
                  "title", // the attribute to search
                  TestSolution.FAILED, // solution when attribute is found
                  TestSolution.PASSED, // solution when attribute is not found
                  "LinkWithTitleDetected", // message associated with element when attribute is not found
                  null // no message created when attribute is not found because passed doesn't produce message
              )
        );
    }

}