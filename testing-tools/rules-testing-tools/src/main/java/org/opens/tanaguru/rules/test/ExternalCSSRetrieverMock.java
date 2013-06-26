/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
package org.opens.tanaguru.rules.test;

import java.util.Collection;
import java.util.HashSet;
import org.opens.tanaguru.contentadapter.css.ExternalCSSRetriever;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;

/**
 *
 * @author jkowalczyk
 */
public class ExternalCSSRetrieverMock implements ExternalCSSRetriever {

    private static final String INLINE_CSS_SUFFIX = "#tanaguru-css-";
    Collection<StylesheetContent> externalCss = new HashSet<StylesheetContent>();
    
    @Override
    public Collection<StylesheetContent> getExternalCSS(SSP ssp) {
        for (RelatedContent rc : ssp.getRelatedContentSet()) {
            if (rc instanceof StylesheetContent && !(((Content)rc).getURI().contains(INLINE_CSS_SUFFIX))) {
                externalCss.add((StylesheetContent)rc);
            }
        }
        return externalCss;
    }

    @Override
    public void addNewStylesheetContent(SSP ssp, StylesheetContent stylesheetContent) {
        externalCss.add(stylesheetContent);
    }

}