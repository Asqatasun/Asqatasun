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

package org.opens.tanaguru.rules.elementchecker.attribute;

import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;

/**
 * Check whether an attribute set by constructor argument belongs to a blacklist.
 * The result of the detection is FAILED by default and can be overriden by 
 * constructor argument. 
 * The result is PASSED instead.
 */
public class AttributeBelongsToBlackListChecker 
                    extends TextElementBelongsToBlackListChecker  {

    /**
     * the attribute the test is about
     */
    private String attributeName;
    
    /**
     * Constructor
     * @param attributeName
     * @param textBelongsToBlackListMessageCode
     * @param blackListNomName
     */
    public AttributeBelongsToBlackListChecker(
            String attributeName,
            String blackListNomName,
            String textBelongsToBlackListMessageCode) {
        super(blackListNomName, textBelongsToBlackListMessageCode);
        this.attributeName = attributeName;
    }
    
    /**
     * Constructor
     * @param attributeName
     * @param textBelongsToBlackListMessageCode
     * @param blackListNomName
     * @param eeAttributeNameList 
     */
    public AttributeBelongsToBlackListChecker(
            String attributeName,
            String blackListNomName,
            String textBelongsToBlackListMessageCode,
            String... eeAttributeNameList) {
        super(blackListNomName, textBelongsToBlackListMessageCode, eeAttributeNameList);
        this.attributeName = attributeName;
    }
    
    /**
     * Constructor
     * @param attributeName
     * @param detectedSolution
     * @param textBelongsToBlackListMessageCode
     * @param blackListNomName
     */
    public AttributeBelongsToBlackListChecker(
            String attributeName,
            String blackListNomName,
            TestSolution detectedSolution,
            String textBelongsToBlackListMessageCode) {
        super(blackListNomName, detectedSolution, textBelongsToBlackListMessageCode);
        this.attributeName = attributeName;
    }
    
    /**
     * Constructor
     * @param attributeName
     * @param detectedSolution
     * @param textBelongsToBlackListMessageCode
     * @param blackListNomName
     * @param eeAttributeNameList 
     */
    public AttributeBelongsToBlackListChecker(
            String attributeName,
            String blackListNomName,
            TestSolution detectedSolution,
            String textBelongsToBlackListMessageCode,
            String... eeAttributeNameList) {
        super(blackListNomName, detectedSolution, textBelongsToBlackListMessageCode, eeAttributeNameList);
        this.attributeName = attributeName;
    }

    @Override
    public String buildTextFromElement(Element element) {
        return element.attr(attributeName).trim();
    }
    
}