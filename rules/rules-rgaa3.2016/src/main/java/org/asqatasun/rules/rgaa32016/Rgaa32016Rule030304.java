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

package org.asqatasun.rules.rgaa32016;

import org.asqatasun.ruleimplementation.AbstractPageRuleWithCheckerImplementation;
import org.asqatasun.rules.elementchecker.contrast.ContrastChecker;

/**
 * Implementation of the rule 3.3.4 of the referential RGAA 3.2016
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.2016/03.Colours/Rule-3-3-4.html">the rule 3.3.4 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-3-3-4">3.3.4 rule specification</a>
 *
 * @author jkowalczyk
 */

public class Rgaa32016Rule030304 extends AbstractPageRuleWithCheckerImplementation {
    
    /** The contrast checker with a value of ratio set to 3*/
    private final ContrastChecker contrastChecker = 
            new ContrastChecker(3f, false, true, false);

    /**
     * Default constructor
     */
    public Rgaa32016Rule030304  () {
        super();
        setElementChecker(contrastChecker);
    }

    @Override
    public int getSelectionSize () {
        return contrastChecker.getElementCounter();
    }

}
