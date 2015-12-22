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

package org.asqatasun.rules.rgaa30;

import org.asqatasun.ruleimplementation.AbstractPageRuleWithCheckerImplementation;
import org.asqatasun.rules.elementchecker.contrast.ContrastChecker;

/**
 * Implementation of the rule 3.4.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-4-1.html">the rule 3.4.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-3-4-1"> 3.4.1 rule specification</a>
 *
 * @author jkowalczyk
 */

public class Rgaa30Rule030401 extends AbstractPageRuleWithCheckerImplementation {
    
    /** The contrast checker with a value of ratio set to 7*/
    private final ContrastChecker contrastChecker = 
            new ContrastChecker(7f, true, false, true);

    /**
     * Default constructor
     */
    public Rgaa30Rule030401 () {
        super();
        setElementChecker(contrastChecker);
    }

    @Override
    public int getSelectionSize() {
        return contrastChecker.getElementCounter();
    }

}
