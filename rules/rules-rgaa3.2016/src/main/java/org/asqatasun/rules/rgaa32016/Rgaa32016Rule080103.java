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
import org.asqatasun.rules.elementchecker.doctype.DoctypePositionChecker;

/**
 * Implementation of the rule 8.1.3 of the referential RGAA 3.2016
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.2016/08.Mandatory_elements/Rule-8-1-3.html">the rule 8.1.3 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-8-1-3">8.1.3 rule specification</a>
 *
 */
public class Rgaa32016Rule080103 extends AbstractPageRuleWithCheckerImplementation {

    
    public Rgaa32016Rule080103 () {
        super(new DoctypePositionChecker());
    }

}
