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
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.H1_IDENTICAL_TO_ANOTHER_PAGE_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.H1_NOT_UNIQUE_MSG;
import org.opens.tanaguru.rules.textbuilder.DeepTextElementBuilder;

/**
 * For each page of a site or a group of pages, is the H1 tag unique ?
 * 
 * @author jkowalczyk
 */
public class SeoRule07061 extends AbstractUniqueElementSiteRuleImplementation {

    /**
     * Constructor
     */
    public SeoRule07061() {
        super(
                new SimpleElementSelector(HtmlElementStore.H1_ELEMENT),
                new DeepTextElementBuilder(), 
                H1_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                H1_NOT_UNIQUE_MSG,
                true);
    }

}