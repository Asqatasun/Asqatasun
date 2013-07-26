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

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.IndefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.rules.seo.unicity.AbstractTagUnicitySiteRuleImplementation;

/**
 * For each page of a site or a group of pages, is the meta description tag
 * unique ?
 *
 * @author jkowalczyk
 */
public class SeoRule01021 extends AbstractTagUnicitySiteRuleImplementation {

    public static final String SITE_LEVEL_MESSAGE_CODE = "MetaDescriptionNotUnique";
    public static final String PAGE_LEVEL_MESSAGE_CODE = "MetaDescriptionIdenticalTo";
    private static final String TAG_DETECTION_XPATH_EXPR =
            "head meta[name=description][content]";
    private static final String CONTENT_ATTRIBUTE_NAME = "content";

    public SeoRule01021() {
        super();
        setPageLevelMessageCode(PAGE_LEVEL_MESSAGE_CODE);
        setSiteLevelMessageCode(SITE_LEVEL_MESSAGE_CODE);
        setSelectionExpression(TAG_DETECTION_XPATH_EXPR);
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(getSelectionExpression());
        Elements elements = sspHandler.getSelectedElements();
        String elementValue=  null;
        if (!elements.isEmpty()) {
            Element element = elements.iterator().next();
            elementValue = element.attr(CONTENT_ATTRIBUTE_NAME).trim();
        }
        IndefiniteResult result = indefiniteResultFactory.create(test,
                sspHandler.getPage(), elementValue);
        return result;
    }

}
