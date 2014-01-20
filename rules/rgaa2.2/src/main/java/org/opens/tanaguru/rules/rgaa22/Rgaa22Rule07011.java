/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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

package org.opens.tanaguru.rules.rgaa22;

import java.util.Arrays;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleCssImplementation;
import org.opens.tanaguru.rules.csschecker.CssPropertyPresenceChecker;
import org.opens.tanaguru.rules.keystore.AttributeStore;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CONTENT_CSS_PROPERTY_DETECTED_MSG;

/**
 * Implementation of the rule 7.1 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-7-1">the rule 7.1 design page.</a>
 * @see <a href="http://rgaa.net/Absence-de-generation-de-contenus.html"> 7.1 rule specification
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule07011 extends AbstractPageRuleCssImplementation {

    private static final String[] pseudoSelectors = {":after", ":before"};
    
    public Rgaa22Rule07011() {
        super(new CssPropertyPresenceChecker(
                        AttributeStore.CONTENT_ATTR, 
                        Arrays.asList(pseudoSelectors),
                        TestSolution.NEED_MORE_INFO,
                        CONTENT_CSS_PROPERTY_DETECTED_MSG));
    }

}