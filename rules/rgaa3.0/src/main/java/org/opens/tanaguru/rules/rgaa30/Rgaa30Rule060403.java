/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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
package org.opens.tanaguru.rules.rgaa30;

import org.opens.tanaguru.ruleimplementation.link.AbstractLinkRuleImplementation;
import org.opens.tanaguru.rules.elementchecker.link.IdenticalLinkWithDifferentTargetChecker;
import org.opens.tanaguru.rules.elementselector.AreaLinkElementSelector;

/**
 * Implementation of the rule 6.4.3 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="https://github.com/Tanaguru/Tanaguru-rules-RGAA-3-doc/wiki/Rule-6-4-3">the rule 6.4.3 design page.</a>
 * @see <a href="https://references.modernisation.gouv.fr/sites/default/files/RGAA3/referentiel_technique.htm#test-6-4-3"> 6.4.3 rule specification</a>
 *
 */
public class Rgaa30Rule060403 extends AbstractLinkRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule060403 () {
        // context is taken into consideration 
        super(new AreaLinkElementSelector(false, true), 
              new IdenticalLinkWithDifferentTargetChecker(false),
              new IdenticalLinkWithDifferentTargetChecker(true));
    }
}
