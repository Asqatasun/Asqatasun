/*
 * @(#)TextContentExtractor.java
 *
 * Copyright  2010 SAS OPEN-S. All rights reserved.
 * OPEN-S PROPRIETARY/CONFIDENTIAL.  Use is subject to license terms.
 *
 * This file is  protected by the  intellectual  property rights
 * in  France  and  other  countries, any  applicable  copyrights  or
 * patent rights, and international treaty provisions. No part may be
 * reproduced  in  any  form  by  any  mean  without   prior  written
 * authorization of OPEN-S.
 */

package org.opens.tanaguru.rules.seo.util;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;

/**
 *
 * @author jkowalczyk
 */
public final class TextContentExtractor {

    private static final char SPACE_CHAR = ' ';
    private static final String TEXT_NODE = "#text";
    private static final String IMG_NODE = "img";
    private static final String SPAN_NODE = "span";
    private static final String STRONG_NODE = "strong";
    private static final String OBJECT_NODE = "object";
    private static final String AREA_NODE = "area";
    private static final String LINK_NODE = "a";
    private static final String ALT_ATTR = "alt";

    /**
     * Default constructor 
     */
    private TextContentExtractor(){};

    /**
     * This method builds the text of a node depending its nature. If the node is
     * a textual node, the extracted text corresponds to the text of the node.
     * But if the node is an image, an object, an area, the extracted text
     * corresponds to the text of the alt attribute.
     * If the node is a link, the extracted text corresponds to the link title.
     *
     * @param node
     * @return
     */
    public static String buildTextContentFromNodeElements(Element element) {
        StringBuilder strBuffer = new StringBuilder();
        if (element.children().isEmpty()) {
            return element.ownText();
        }
        for (int i=0;i<element.children().size();i++){
            String nodeName = element.child(i).nodeName();
            if (StringUtils.equalsIgnoreCase(nodeName, TEXT_NODE) &&
                    !element.child(i).ownText().trim().isEmpty()) {
                strBuffer.append(element.child(i).ownText().trim());
                strBuffer.append(SPACE_CHAR);
            } else if (StringUtils.equalsIgnoreCase(nodeName, IMG_NODE) &&
                    element.child(i).hasAttr(ALT_ATTR) &&
                    !element.child(i).attr(ALT_ATTR).trim().isEmpty()) {
                strBuffer.append(element.child(i).attr(ALT_ATTR).trim());
                strBuffer.append(SPACE_CHAR);
            } if (StringUtils.equalsIgnoreCase(nodeName, OBJECT_NODE) &&
                    !element.child(i).ownText().trim().isEmpty()) {
                strBuffer.append(element.child(i).ownText().trim());
                strBuffer.append(SPACE_CHAR);
            } else if (StringUtils.equalsIgnoreCase(nodeName, AREA_NODE) &&
                    element.child(i).hasAttr(ALT_ATTR) &&
                    !element.child(i).attr(ALT_ATTR).trim().isEmpty()) {
                strBuffer.append(element.child(i).attr(ALT_ATTR).trim());
                strBuffer.append(SPACE_CHAR);
            }  else if (StringUtils.equalsIgnoreCase(nodeName, STRONG_NODE) &&
                    !element.child(i).ownText().trim().isEmpty()) {
                strBuffer.append(element.child(i).ownText().trim());
                strBuffer.append(SPACE_CHAR);
            }  else if (StringUtils.equalsIgnoreCase(nodeName, SPAN_NODE) &&
                    !element.child(i).ownText().trim().isEmpty()) {
                strBuffer.append(element.child(i).ownText().trim());
                strBuffer.append(SPACE_CHAR);
            } else if (StringUtils.equalsIgnoreCase(nodeName, LINK_NODE)){
                // recursive call of the method in case of A tag.
                strBuffer.append(buildTextContentFromNodeElements(element.child(i)));
            } else {
                strBuffer.append(element.child(i).ownText());
            }
        }
        return strBuffer.toString().trim().toLowerCase();
    }

}
