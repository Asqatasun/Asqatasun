/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.asqatasun.rules.rgaa22;

import org.asqatasun.ruleimplementation.AbstractPageRuleWithCheckerImplementation;
import org.asqatasun.rules.elementchecker.contrast.ContrastChecker;

/**
 * Implementation of the rule 2.7 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-2-7">the rule 2.7 design page.</a>
 * @see <a href="http://rgaa.net/Valeur-du-rapport-de-contraste-du,9.html"> 2.7 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule02071 extends AbstractPageRuleWithCheckerImplementation {

    /** The constract checker with a value of ratio set to 4.5*/
    private static final ContrastChecker CONSTRAST_CHECKER = 
            new ContrastChecker(4.5f, true, true, true);

    /**
     * Default constructor
     */
    public Rgaa22Rule02071 () {
        super(CONSTRAST_CHECKER);
    }

    @Override
    public int getSelectionSize() {
        return CONSTRAST_CHECKER.getElementCounter();
    }

}