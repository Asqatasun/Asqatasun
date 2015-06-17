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

package org.tanaguru.rules.elementchecker.pertinence;

import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.elementchecker.text.TextEndsWithChecker;
import org.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;
import org.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * This class checks whether the content of an attribute is pertinent by verifying :
 * <ul>
 * <li>it is not empty</li>
 * <li>it is not only composed of non alphanumerical characters</li>
 * <li>it is not blacklisted</li>
 * <li>it is not identical to another text (the content of another attribute
 * for instance)</li>
  *</ul>
 */
public class AttributePertinenceChecker extends TextPertinenceChecker {

    /* The attr element builder needed to retrieve the attribute to compare with*/
    private TextAttributeOfElementBuilder attrElementBuilder;
    @Override
    public TextElementBuilder getTextElementBuilder() {
        if (attrElementBuilder == null) {
            attrElementBuilder = new TextAttributeOfElementBuilder();
        }
        return attrElementBuilder;
    }

    
    /**
     * Constructor. 
     * Enables to override the not pertinent solution. 
     * 
     * @param attributeName
     * @param checkEmptiness
     * @param textElementBuilderToCompareWith
     * @param extensionsListNameToCompareWith
     * @param attrNotPertinentSolution
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     * @param eeAttributeNameList 
     */
    public AttributePertinenceChecker(
            String attributeName,
            boolean checkEmptiness,
            @Nullable TextElementBuilder textElementBuilderToCompareWith,
            @Nullable String extensionsListNameToCompareWith,
            TestSolution attrNotPertinentSolution,
            String notPertinentMessageCode,
            String manualCheckMessage,
            String... eeAttributeNameList) {
        super(checkEmptiness, 
              textElementBuilderToCompareWith, 
              null, // this attribute is about the text blacklist nomenclature 
              attrNotPertinentSolution, 
              notPertinentMessageCode, 
              manualCheckMessage, 
              eeAttributeNameList);
        this.attrElementBuilder.setAttributeName(attributeName);
        
        addExtensionChecker(extensionsListNameToCompareWith);
    }

    
    /**
     * Constructor. 
     * Enables to override the not pertinent solution. 
     * 
     * @param attributeName
     * @param checkEmptiness
     * @param textElementBuilderToCompareWith
     * @param extensionsListNameToCompareWith
     * @param attrNotPertinentSolution
     * @param notPertinentMessageCode
     * @param manualCheckMessage 
     */
    public AttributePertinenceChecker(
            String attributeName,
            boolean checkEmptiness,
            @Nullable TextElementBuilder textElementBuilderToCompareWith,
            @Nullable String extensionsListNameToCompareWith,
            TestSolution attrNotPertinentSolution,
            String notPertinentMessageCode,
            String manualCheckMessage) {
        super(checkEmptiness, 
              textElementBuilderToCompareWith, 
              null, // this attribute is about the text blacklist nomenclature 
              attrNotPertinentSolution, 
              notPertinentMessageCode, 
              manualCheckMessage);
        this.attrElementBuilder.setAttributeName(attributeName);
        
        addExtensionChecker(extensionsListNameToCompareWith);
    }
    
    /**
     * Constructor. 
     * Returns FAILED when the attribute is not pertinent.
     * 
     * @param attributeName
     * @param checkEmptiness
     * @param textElementBuilderToCompareWith
     * @param extensionsListNameToCompareWith
     * @param notPertinentMessageCode
     * @param manualCheckMessage 
     */
    public AttributePertinenceChecker(
            String attributeName,
            boolean checkEmptiness,
            @Nullable TextElementBuilder textElementBuilderToCompareWith,
            @Nullable String extensionsListNameToCompareWith,
            String notPertinentMessageCode,
            String manualCheckMessage) {
        this(attributeName,
             checkEmptiness, 
             textElementBuilderToCompareWith, 
             extensionsListNameToCompareWith,
             TestSolution.FAILED,
             notPertinentMessageCode, 
             manualCheckMessage);
    }
    
    /**
     * Constructor.
     * Returns FAILED when the attribute is not pertinent.
     * 
     * @param attributeName
     * @param checkEmptiness
     * @param textElementBuilderToCompareWith
     * @param extensionsListNameToCompareWith
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     * @param eeAttributeNameList 
     */
    public AttributePertinenceChecker(
            String attributeName,
            boolean checkEmptiness,
            @Nullable TextElementBuilder textElementBuilderToCompareWith,
            @Nullable String extensionsListNameToCompareWith,
            String notPertinentMessageCode,
            String manualCheckMessage,
            String... eeAttributeNameList) {
        this(attributeName,
             checkEmptiness, 
             textElementBuilderToCompareWith, 
             extensionsListNameToCompareWith,
             TestSolution.FAILED,
             notPertinentMessageCode, 
             manualCheckMessage, 
             eeAttributeNameList);
    }
    
    /**
     * Add a {@link TextEndsWithChecker} to the checker collection
     */
    private void addExtensionChecker(String extensionsListNameToCompareWith) {
        // The second check consists in verifying the element content does not only
        // contains non alphanumerical characters
        if (StringUtils.isNotBlank(extensionsListNameToCompareWith)) {
            addChecker(new TextEndsWithChecker(
                            getTextElementBuilder(),
                            extensionsListNameToCompareWith,
                            getFailureSolution(), 
                            notPertinentMessageCode, 
                            getEeAttributeNames()));
        }
    }

}