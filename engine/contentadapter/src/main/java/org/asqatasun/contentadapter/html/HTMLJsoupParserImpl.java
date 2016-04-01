/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.contentadapter.html;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.asqatasun.contentadapter.ContentAdapter;
import org.asqatasun.contentadapter.HTMLParser;
import org.asqatasun.contentadapter.css.CSSJsoupPhlocContentAdapterImpl;
import org.asqatasun.entity.audit.SSP;

/**
 * 
 * @author jkowalczyk
 */
public class HTMLJsoupParserImpl implements HTMLParser {

    protected Set<ContentAdapter> contentAdapterSet = Collections.EMPTY_SET;
    protected boolean initialized = false;
    protected SSP ssp;

    public HTMLJsoupParserImpl(Set<ContentAdapter> contentAdapterSet) {
        super();
        this.contentAdapterSet = contentAdapterSet;
    }

    @Override
    public Set<ContentAdapter> getContentAdapterSet() {
        return contentAdapterSet;
    }

    @Override
    public SSP getSSP() {
        return ssp;
    }

    @Override
    public void run() {
        if (contentAdapterSet.isEmpty()) {
            return;
        }
        Document doc = Jsoup.parse(ssp.getDOM(), ssp.getURI());

        Collection<Element> inlineCssElementList = new ArrayList<>();
        Collection<Element> localeCssElementList = new ArrayList<>();
        Collection<Element> externalCssElementList = new ArrayList<>();
        
        for (Element el : doc.select("[style]")) {
            inlineCssElementList.add(el);            
        }

        for (Element el : doc.select("style")) {
            localeCssElementList.add(el);
        }

        for (Element el : doc.select("link[type=text/css][rel*=stylesheet][href]")) {
            externalCssElementList.add(el);
        }
        
        for (ContentAdapter ca : contentAdapterSet) {
            if (ca instanceof CSSJsoupPhlocContentAdapterImpl) {
                ((CSSJsoupPhlocContentAdapterImpl)ca).setExternalCssElements(externalCssElementList);
                ((CSSJsoupPhlocContentAdapterImpl)ca).setInlineCssElements(inlineCssElementList);
                ((CSSJsoupPhlocContentAdapterImpl)ca).setLocaleCssElements(localeCssElementList);
                ca.setSSP(ssp);
                ((CSSJsoupPhlocContentAdapterImpl)ca).initContext();
                ((CSSJsoupPhlocContentAdapterImpl)ca).adaptContent();
            }
        }
    }

    @Override
    public void setContentAdapterSet(Set<ContentAdapter> contentAdapterSet) {
        this.contentAdapterSet = contentAdapterSet;
    }

    @Override
    public void setSSP(SSP ssp) {
        this.ssp = ssp;
    }

}