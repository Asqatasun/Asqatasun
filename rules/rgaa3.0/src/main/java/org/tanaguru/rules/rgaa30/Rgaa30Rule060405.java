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
package org.tanaguru.rules.rgaa30;

import org.tanaguru.ruleimplementation.link.AbstractLinkRuleImplementation;
import org.tanaguru.rules.elementchecker.link.IdenticalLinkWithDifferentTargetChecker;
import org.tanaguru.rules.elementselector.SvgLinkElementSelector;

/**
 * Implementation of the rule 6.4.5 of the referential Rgaa 3.0.
 *
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-6-4-5">the rule 6.4.5 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-6-4-5"> 6.4.5 rule specification</a>
 */

public class Rgaa30Rule060405 extends AbstractLinkRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule060405 () {
        // context is taken into consideration 
        super(new SvgLinkElementSelector(false, true), 
              new IdenticalLinkWithDifferentTargetChecker(false),
              new IdenticalLinkWithDifferentTargetChecker(true));
    }
    
}