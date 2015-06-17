/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.webapp.presentation.highlighter;

import com.uwyn.jhighlight.highlighter.ExplicitStateHighlighter;
import com.uwyn.jhighlight.renderer.XmlXhtmlRenderer;
import com.uwyn.jhighlight.tools.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Implementation of HtmlHighlighter based on the JHighlight XmlXhtmlRenderer.
 *
 * @author jkowalczyk
 */
public class HtmlHighlighterImpl implements HtmlHighlighter {

    @Override
    public String highlightSourceCode(String doctype, String htmlContent) {
        StringBuilder source = new StringBuilder();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(doctype) && 
                !org.apache.commons.lang3.StringUtils.startsWithIgnoreCase(htmlContent, doctype)) {
            source.append(doctype.trim().
                    replace(CARRIAGE_RETURN_CHAR, EMPTY_CHAR).
                    replace('\n', EMPTY_CHAR));
            source.append(CARRIAGE_RETURN_CHAR);
        }
        source.append(htmlContent);
        try {
            return new MyXhtmlRenderer().highlight(source.toString());
        } catch (IOException ioe){
            return "";
        }
    }

    @Override
    public String highlightSourceCode(String htmlContent) {
        try {
            return new MyXhtmlRenderer().highlight(htmlContent);
        } catch (IOException ioe){
            return "";
        }
    }

    /**
     * Inner class that extends XmlXhtmlRenderer and override the highlight 
     * method and thus create an highlighted source code based on an
     * ol/li structure.
     */
    private class MyXhtmlRenderer extends XmlXhtmlRenderer {

        public String highlight(String source) throws IOException {
            ExplicitStateHighlighter highlighter = getHighlighter();

            StringReader sr = new StringReader(source);
            BufferedReader br = new BufferedReader(sr);

            StringBuilder highlightedSourceCode = new StringBuilder();
            String line;
            String token;
            int length;
            int style;
            int lineNumber = 2;
            String css_class;
            int previous_style = 0;
            boolean newline = false;
            highlightedSourceCode.append("<ol>");
            highlightedSourceCode.append("<li id=\"line1\">");
            while ((line = br.readLine()) != null) {
                if (line.trim().length() > 0) {
                    line += "\n";
                    line = StringUtils.convertTabsToSpaces(line, 4);

                    // should be optimized by reusing a custom LineReader class
                    Reader lineReader = new StringReader(line);
                    highlighter.setReader(lineReader);
                    int index = 0;
                    while (index < line.length()) {

                        style = highlighter.getNextToken();
                        length = highlighter.getTokenLength();
                        token = line.substring(index, index + length);

                        if (style != previous_style
                                || newline) {
                            css_class = getCssClass(style);

                            if (css_class != null) {
                                if (previous_style != 0 && !newline) {
                                    highlightedSourceCode.append("</span>");
                                }
                                highlightedSourceCode.append("<span class=\"");
                                highlightedSourceCode.append(css_class);
                                highlightedSourceCode.append("\">");

                                previous_style = style;
                            }
                        }

                        newline = false;
                        highlightedSourceCode.append(
                                StringUtils.replace(
                                    StringUtils.encodeHtml(
                                        StringUtils.replace(token, "\n", "")),
                                " ", "&nbsp;"));

                        index += length;
                    }

                    highlightedSourceCode.append("</span></li>\n<li id=\"line");
                    highlightedSourceCode.append(lineNumber);
                    highlightedSourceCode.append("\">\n");

                    newline = true;
                    lineNumber++;
                }
            }

            highlightedSourceCode.append("</ol>");
            return highlightedSourceCode.toString();
        }
    }
    
}