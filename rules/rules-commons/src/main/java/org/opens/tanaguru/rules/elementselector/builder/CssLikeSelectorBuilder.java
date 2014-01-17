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
package org.opens.tanaguru.rules.elementselector.builder;

import java.util.Collection;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.processor.SSPHandler;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ID_ATTR;

/**
 * css-selector like queries builder utilities.
 * 
 * @author jkowalczyk
 */
public final class CssLikeSelectorBuilder {

    private static final char ID_SELECTOR_PREFIX = '#';
    private static final char CLASS_SELECTOR_PREFIX = '.';
    private static final char OPEN_BRACKET = '[';
    private static final char OPEN_PARENTHESE = '(';
    private static final char CLOSE_BRACKET = ']';
    private static final char CLOSE_PARENTHESE = ')';
    private static final char SPACE = ' ';
    private static final char COMMA = ',';
    private static final char EQUAL = '=';
    private static final String NOT_PREFIX = ":not(";
    private static final String HAS_PREFIX = ":has(";
    private static final String NOT_EMPTY_REGEXP = "~=^\\s*$";
    
    /**
     * private constructor
     */
    private CssLikeSelectorBuilder(){}

    /**
     * Build a JQuery selector to retrieve elements that contain a given
     * attribute. The notEmptyAttribute attribute determines whether this attribute
     * can be empty or not
     * 
     * @param elementNameList
     * @param attributeName
     * @param notEmptyAttribute
     * @return 
     *      The jquery selector
     */
    public static String buildSelectorFromElementsAndAttribute(
            Collection<String> elementNameList, 
            @Nullable String attributeName, 
            boolean notEmptyAttribute) {
        StringBuilder selector = new StringBuilder();
        boolean isFirstElement = true;
        for (String elementName: elementNameList) {
            if (!isFirstElement) {
                selector.append(SPACE);
                selector.append(COMMA);
                selector.append(SPACE);
            }
            selector.append(elementName);
            if (StringUtils.isNotBlank(attributeName)) {
                selector.append(OPEN_BRACKET);
                selector.append(attributeName);
                selector.append(CLOSE_BRACKET);
                if (notEmptyAttribute) {
                    selector.append(NOT_PREFIX);
                    selector.append(OPEN_BRACKET);
                    selector.append(attributeName);
                    selector.append(NOT_EMPTY_REGEXP);
                    selector.append(CLOSE_BRACKET);
                    selector.append(CLOSE_PARENTHESE);
                }
            }
            isFirstElement = false;
        }
        return selector.toString();
    }
    
    /**
     * Build a JQuery selector to retrieve elements that contain a given
     * attribute. The notEmptyAttribute attribute determines whether this attribute
     * can be empty or not
     * 
     * @param elementName
     * @param attributeName
     * @param notEmptyAttribute
     * @return 
     *      The jquery selector
     */
    public static String buildSelectorFromElementAndAttribute(
            String elementName, 
            String attributeName, 
            boolean notEmptyAttribute) {
        StringBuilder selector = new StringBuilder();
        selector.append(elementName);
        if (attributeName != null && !attributeName.isEmpty()) {
            selector.append(OPEN_BRACKET);
            selector.append(attributeName);
            selector.append(CLOSE_BRACKET);
            if (notEmptyAttribute) {
                selector.append(NOT_PREFIX);
                selector.append(OPEN_BRACKET);
                selector.append(attributeName);
                selector.append(NOT_EMPTY_REGEXP);
                selector.append(CLOSE_BRACKET);
                selector.append(CLOSE_PARENTHESE);
            }
        }
        return selector.toString();
    }
    
    /**
     * Build a JQuery selector to retrieve elements different from the specified
     * type that contain a given attribute. 
     * 
     * @param elementName
     * @param attributeName
     * @param notEmptyAttribute
     * @return 
     *      The css selector
     */
    public static String buildSelectorFromElementDifferentFromAndAttribute(
            String elementName, 
            String attributeName) {
        StringBuilder selector = new StringBuilder();
        selector.append(NOT_PREFIX);
        selector.append(elementName);
        selector.append(CLOSE_PARENTHESE);
        if (attributeName != null && !attributeName.isEmpty()) {
            selector.append(OPEN_BRACKET);
            selector.append(attributeName);
            selector.append(CLOSE_BRACKET);
        }
        return selector.toString();
    }
    
    /**
     * Build a JQuery selector to retrieve elements that have or not children of a 
     * given type. Multiple children can be set.
     * 
     * @param elementName
     * @param childrenList
     * @param notEmptyAttribute
     * @return 
     *      The jquery selector
     */
    public static String buildSelectorFromElementAndChildren(
            String elementName, 
            Collection<String> childrenList, 
            boolean hasChild) {
        StringBuilder selector = new StringBuilder();
        boolean isFirstElement = true;
        for (String child : childrenList) {
            if (!isFirstElement) {
                selector.append(SPACE);
                selector.append(COMMA);
                selector.append(SPACE);
            }
            selector.append(elementName);
            if (!hasChild) {
                selector.append(NOT_PREFIX);
            }
            selector.append(HAS_PREFIX);
            selector.append(child);
            selector.append(CLOSE_PARENTHESE);
            if (!hasChild) {
                selector.append(CLOSE_PARENTHESE);
            }
            isFirstElement=false;
        }
        return selector.toString();
    }
    
    /**
     * 
     * @param elementName
     * @param childrenList
     * @param notEmptyAttribute
     * @return 
     *      The number of found elements for a given selector
     */
    public static int getNumberOfElements(
            SSPHandler sspHandler, 
            String selector) {
        return sspHandler.domCssLikeSelectNodeSet(selector).getSelectedElementNumber();
    }
    
    /**
     * Create a selector of the form 
     *          $elementName[$attributeName=$attributeValue]
     * 
     * @param elementName
     * @param attributeName
     * @param attributeValue
     * @return 
     */
    public static String buildSelectorFromElementsAndAttributeValue(
            String elementName, 
            String attributeName, 
            String attributeValue) {
        StringBuilder strb = new StringBuilder();
        strb.append(elementName);
        strb.append(OPEN_BRACKET);
        strb.append(attributeName);
        strb.append(EQUAL);
        strb.append(attributeValue);
        strb.append(CLOSE_BRACKET);
        return strb.toString();
    }
    
    /**
     * Create a selector of the form #$idValue
     * @param idValue
     * @return the css query
     */
    public static String buildSelectorFromId(String idValue) {
       if (StringUtils.contains(idValue, ':')) {
           return buildSelectorFromAttributeTypeAndValue(ID_ATTR, idValue);
       }
       StringBuilder strb = new StringBuilder();
       strb.append(ID_SELECTOR_PREFIX);
       strb.append(idValue);
       return strb.toString();
    }
    
    /**
     * Create a selector of the form .$classValue
     * @param classValue
     * @return the css query
     */
    public static String buildSelectorFromClass(String classValue) {
       StringBuilder strb = new StringBuilder();
       strb.append(CLASS_SELECTOR_PREFIX);
       strb.append(classValue);
       return strb.toString();
    }
    
    /**
     * Create a selector of the form [$attributeType]
     * @param attributeType
     * @return the css query
     */
    public static String buildSelectorFromAttributeType(String attributeType) {
       StringBuilder strb = new StringBuilder();
       strb.append(OPEN_BRACKET);
       strb.append(attributeType);
       strb.append(CLOSE_BRACKET);
       return strb.toString();
    }
    
    /**
     * Create a selector of the form [$attributeType=$attributeValue]
     * @param attributeType
     * @param attributeValue
     * @return the css query
     */
    public static String buildSelectorFromAttributeTypeAndValue(
                String attributeType,
                String attributeValue) {
       StringBuilder strb = new StringBuilder();
       strb.append(OPEN_BRACKET);
       strb.append(attributeType);
       strb.append(EQUAL);
       strb.append(attributeValue);
       strb.append(CLOSE_BRACKET);
       return strb.toString();
    }
    
    /**
     * Create a selector of the type :not($elementName [$attributeName]) that 
     * select all the elements with a given attribute and without a parent of type
     * of the given element
     * @param elementName
     * @param attributeName
     * @return the css query
     */
    public static String buildSelectorFromAttributeAndParentDifferentFrom(
            String elementName, 
            String attributeName) {
        StringBuilder selector = new StringBuilder();
        selector.append(NOT_PREFIX);
        selector.append(elementName);
        selector.append(SPACE);
        if (attributeName != null && !attributeName.isEmpty()) {
            selector.append(OPEN_BRACKET);
            selector.append(attributeName);
            selector.append(CLOSE_BRACKET);
        }
        selector.append(CLOSE_PARENTHESE);
        return selector.toString();
    }
}