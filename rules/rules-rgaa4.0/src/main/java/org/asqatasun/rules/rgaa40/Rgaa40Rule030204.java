/**
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.rules.rgaa40;

import org.asqatasun.ruleimplementation.AbstractPageRuleWithCheckerImplementation;
import org.asqatasun.rules.elementchecker.contrast.ContrastChecker;

/**
 * Implementation of rule 3.2.4 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://doc.asqatasun.org/v5/en/Business-rules/RGAA-v4/03.Colours/Rule-3-2-4/">rule 3.2.4 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-3-2-4">3.2.4 rule specification</a>
 */
public class Rgaa40Rule030204 extends AbstractPageRuleWithCheckerImplementation {

    /**
     * The contrast checker with:
     * - a value of ratio set to 4.5
     * - a value of normal font size threshold in pixel to 24
     * - a value of bold font size threshold in pixel to 18.5
     */
    private final ContrastChecker contrastChecker =
        new ContrastChecker(
            3f,
            false,
            true,
            false,
            24.0f,
            18.5f
        );


    /**
     * Default constructor
     */
    public Rgaa40Rule030204() {
        super();
        setElementChecker(contrastChecker);
    }

    @Override
    public int getSelectionSize() {
        return contrastChecker.getElementCounter();
    }

}
