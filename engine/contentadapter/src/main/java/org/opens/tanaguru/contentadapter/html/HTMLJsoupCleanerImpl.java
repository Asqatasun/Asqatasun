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
package org.opens.tanaguru.contentadapter.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.nodes.Node;
import org.opens.tanaguru.contentadapter.HTMLCleaner;

/**
 * 
 * @author jkowalczyk
 */
public class HTMLJsoupCleanerImpl extends AbstractHTMLCleaner implements HTMLCleaner {
    
    static final String CORRECTOR_NAME = "JsoupCleaner";

    public HTMLJsoupCleanerImpl() {
        super();
    }

    @Override
    public void run() {
        Document doc = Jsoup.parse(dirtyHTML);
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        doc.outputSettings().outline(true);
        doc.outputSettings().indentAmount(2);
        removeComments(doc);
        removeMalformedAttributes(doc);
        result = doc.outerHtml();
   }

    /**
     * Remove the comments of the page 
     * 
     * @param node 
     */
    private void removeComments(Node node) {
        // as we are removing child nodes while iterating, we cannot use a normal foreach over children,
        // or will get a concurrent list modification error.
        int i = 0;
        while (i < node.childNodes().size()) {
            Node child = node.childNode(i);
            if (child.nodeName().equals("#comment"))
                child.remove();
            else {
                removeComments(child);
                i++;
            }
        }
    }
    
    /**
     * Remove the comments of the page 
     * 
     * @param node 
     */
    private void removeMalformedAttributes(Node node) {
        // as we are removing child nodes while iterating, we cannot use a normal foreach over children,
        // or will get a concurrent list modification error.
        int i = 0;
        while (i < node.childNodes().size()) {
            Node child = node.childNode(i);
            for (Attribute attr : child.attributes()) {
                if (attr.getKey().startsWith("\"") && attr.getKey().endsWith("\"")) {
                    child.removeAttr(attr.getKey());
                }
            }
            removeMalformedAttributes(child);
            i++;
        }
    }
    
    @Override
    public String getCorrectorName() {
        return CORRECTOR_NAME;
    }

}