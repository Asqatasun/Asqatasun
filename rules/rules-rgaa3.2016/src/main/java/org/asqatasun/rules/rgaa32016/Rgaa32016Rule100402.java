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

import org.asqatasun.ruleimplementation.AbstractPageRuleCssImplementation;
import org.asqatasun.rules.csschecker.ForbiddenUnitChecker;

/**
 * Implementation of the rule 10.4.2 of the referential RGAA 3.2016
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.2016/10.Presentation_of_information/Rule-10-4-2.html">the rule 10.4.2 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-10-4-2">10.4.2 rule specification</a>
 *
 * @author jkowalczyk
 */

public class Rgaa32016Rule100402 extends AbstractPageRuleCssImplementation {

    /* the font-size css property key */
    private static final String FONT_SIZE_CSS_PROPERTY = "font-size";
    
    /**
     * 
     */
    public Rgaa32016Rule100402 () {
        super(new ForbiddenUnitChecker(FONT_SIZE_CSS_PROPERTY), "MediaListNotAcceptingRelativeUnits");
    }

}
