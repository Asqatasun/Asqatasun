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
import static org.opens.tanaguru.rules.keystore.AttributeStore.CONTENT_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.META_DESC_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.META_DESC_IDENTICAL_TO_ANOTHER_PAGE_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.META_DESC_NOT_UNIQUE_MSG;
import org.opens.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * For each page of a site or a group of pages, is the meta description tag
 * unique ?
 *
 * @author jkowalczyk
 */
public class SeoRule01021 extends AbstractUniqueElementSiteRuleImplementation {


    /**
     * Constructor
     */
    public SeoRule01021() {
        super(
                new SimpleElementSelector(META_DESC_CSS_LIKE_QUERY),
                new TextAttributeOfElementBuilder(CONTENT_ATTR), 
                META_DESC_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                META_DESC_NOT_UNIQUE_MSG, 
                false);
    }

}