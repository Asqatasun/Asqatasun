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
package org.opens.tanaguru.rules.seo;

import org.opens.tanaguru.ruleimplementation.AbstractUniqueElementSiteRuleImplementation;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.TITLE_WITHIN_HEAD_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.TITLE_IDENTICAL_TO_ANOTHER_PAGE_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.TITLE_NOT_UNIQUE_MSG;
import org.opens.tanaguru.rules.textbuilder.SimpleTextElementBuilder;

/**
 * For each page of a site or a group of pages, is the title unique ?
 * 
 * @author jkowalczyk
 */
public class SeoRule06041 extends AbstractUniqueElementSiteRuleImplementation {

    /**
     * Constructor
     */
    public SeoRule06041() {
        super(
                new SimpleElementSelector(TITLE_WITHIN_HEAD_CSS_LIKE_QUERY),
                new SimpleTextElementBuilder(), 
                TITLE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                TITLE_NOT_UNIQUE_MSG,
                true);
    }

}