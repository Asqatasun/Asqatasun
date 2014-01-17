/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
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
 *  Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.rules.elementchecker.helper;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.TestSolution;
import static org.opens.tanaguru.rules.keystore.AttributeStore.*;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 *
 * @author jkowalczyk
 */
public final class RuleCheckHelper {

    private static final String CAPTCHA_KEYWORD = "captcha";
    private static final String TEST_RESULT_EVIDENCE_ELEMENT = "Precomputed-Test-Result";
    private static final String ABSENT_ATTRIBUTE_VALUE = "attribute-absent";
    private static final String MSG_SPACER = "_";

    /**
     * The ProcessRemarkService
     */
    private static ProcessRemarkService prs;
    public static void setProcessRemarkService(ProcessRemarkService processRemarkService) {
        prs = processRemarkService;
    }
    
    /**
     * Default private constructor
     */
    private RuleCheckHelper(){}

    /**
     * This methods parses all the elements retrieved from the scope, extracts
     * the ones where the occurrence "captcha" is found among the attribute values
     * and removes these elements from the initial set of elements.
     * 
     * @param elements
     * @return 
     */
    public static Elements extractCaptchaElements (Elements elements) {
        Elements captchaElements = new Elements();
        for (Element el : elements) {
            for (Attribute attr : el.attributes()) {
                if (StringUtils.containsIgnoreCase(attr.getValue(), CAPTCHA_KEYWORD)) {
                    captchaElements.add(el);
                    break;
                }
            }
            for (Element pel : el.parents()) {
                for (Attribute attr : pel.attributes()) {
                    if (StringUtils.containsIgnoreCase(attr.getValue(), CAPTCHA_KEYWORD)) {
                        captchaElements.add(el);
                        break;
                    }
                }
            }
        }
        elements.removeAll(captchaElements);
        return captchaElements;
    }
    
    /**
     * This methods checks whether a given attribute is present for a set of 
     * elements. These elements may be out of scope, and a pre-result is 
     * computed.
     * 
     * @param elements
     * @param attributeName
     * @param messageCode
     * @param eeAttributeName
     * @return 
     */
    public static TestSolution checkAttributePresenceWithPreComputedResult (
            Elements elements, 
            String attributeName, 
            String messageCode, 
            String eeAttributeName) {
        for (Element el : elements) {
            Collection<EvidenceElement> eeList = new ArrayList<EvidenceElement>();
            if (!el.hasAttr(attributeName)) {
                eeList.add(prs.getEvidenceElement(TEST_RESULT_EVIDENCE_ELEMENT, TestSolution.FAILED.name()));
                addEvidenceElementToCollection(eeList, el, eeAttributeName);
                prs.addSourceCodeRemarkOnElement(
                        TestSolution.NEED_MORE_INFO, 
                        el, 
                        messageCode, 
                        eeList);
            } else {
                eeList.add(prs.getEvidenceElement(TEST_RESULT_EVIDENCE_ELEMENT, TestSolution.PASSED.name()));
                addEvidenceElementToCollection(eeList, el, eeAttributeName);
                prs.addSourceCodeRemarkOnElement(
                        TestSolution.NEED_MORE_INFO, 
                        el, 
                        messageCode, 
                        eeList);
            }
        }
        return TestSolution.NEED_MORE_INFO;
    }
    
    /**
     * Extract the text of an image link
     * 
     * @param element
     * @return 
     */
    public static String extractImageLinkText (Element element) {
        StringBuilder strb = new StringBuilder();
        for (Node node : element.childNodes()) {
            if (node instanceof TextNode) {
                strb.append(((TextNode)node).text());
            } else if (node instanceof Element && 
                    StringUtils.equalsIgnoreCase(node.nodeName(), HtmlElementStore.IMG_ELEMENT) && 
                    node.hasAttr(ALT_ATTR)) {
                strb.append(node.attr(ALT_ATTR).trim());
            } else if (node instanceof Element) {
                strb.append(extractImageLinkText((Element)node));
            }
        }
        return StringUtil.normaliseWhitespace(strb.toString().trim()); 
    } 

    /**
     * Add an evidenceElement to a given evidenceElement collection. 
     * @param eeList
     * @param element
     * @param attr
     * @return 
     */
    private static void addEvidenceElementToCollection(
            Collection<EvidenceElement> eeList,
            Element element, 
            String attr) {
        EvidenceElement extraEe;
        if (isElementTextRequested(attr)) {
            extraEe = prs.getEvidenceElement(attr, element.text());
        } else if (isAttributeExternalResource(attr)) {
            extraEe = prs.getEvidenceElement(attr, buildAttributeValue(element, attr, true));
        } else {
            extraEe = prs.getEvidenceElement(attr, buildAttributeValue(element, attr, false));
        }
        eeList.add(extraEe);
    }
    
    /**
     * 
     * @param attributeName
     * @return 
     */
    private static boolean isAttributeExternalResource(String attributeName) {
        return (StringUtils.equalsIgnoreCase(attributeName, SRC_ATTR) || 
                StringUtils.equalsIgnoreCase(attributeName, HREF_ATTR)) ? true : false; 
    }  
    
    /**
     * 
     * @param attributeName
     * @return 
     */
    private static boolean isElementTextRequested(String attributeName) {
        return StringUtils.equalsIgnoreCase(attributeName, HtmlElementStore.TEXT_ELEMENT2) ? true : false; 
    }  
    
    /**
     * 
     * @param element
     * @param attributeName
     * @param isExternalLink
     * @return 
     */
    private static String buildAttributeValue(
            Element element, 
            String attributeName, 
            boolean isExternalResource) {
        if (!element.hasAttr(attributeName)) {
            return ABSENT_ATTRIBUTE_VALUE;
        } else if (isExternalResource && !element.attr("abs:"+attributeName).isEmpty()) {
            return element.absUrl(attributeName);
        } else {
            return element.attr(attributeName);
        }
    }

    public static String specifyMessageToRule(String msg, String testCode) {
        StringBuilder strb = new StringBuilder(msg);
        strb.append(MSG_SPACER);
        strb.append(testCode);
        return strb.toString();
    }

}