/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
package org.asqatasun.ruleimplementation.rgaa4.media;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.asqatasun.rules.elementselector.SimpleElementSelector;

import static org.asqatasun.rules.keystore.CssLikeQueryStore.MEDIA_VIDEO_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.LINK_MEDIA_VIDEO_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG;

/**
 * This class deals with the tests related with
 */
public abstract class AbstractMediaVideoDetectionRuleImplementation extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public AbstractMediaVideoDetectionRuleImplementation() {
        super(
            new SimpleElementSelector(MEDIA_VIDEO_CSS_LIKE_QUERY + ", " + LINK_MEDIA_VIDEO_CSS_LIKE_QUERY),
            TestSolution.NEED_MORE_INFO,  // solution when at least one element is found
            TestSolution.NOT_APPLICABLE,  // solution when no element is found
            MANUAL_CHECK_ON_ELEMENTS_MSG, // manual check message
            null
        );
        // System.out.println(MEDIA_VIDEO_CSS_LIKE_QUERY + ", " + LINK_MEDIA_VIDEO_CSS_LIKE_QUERY);
    }

}
