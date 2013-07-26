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

package org.opens.tanaguru.rules.elementchecker.pertinence;

import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.elementchecker.attribute.AttributeEmptinessChecker;
import org.opens.tanaguru.rules.elementchecker.attribute.AttributeEndsWithChecker;
import org.opens.tanaguru.rules.elementchecker.attribute.AttributeNotIdenticalToOtherAttributeChecker;
import org.opens.tanaguru.rules.elementchecker.attribute.AttributeOnlyContainsNonAlphanumericalCharactersChecker;

/**
 * 
 */
public class AttributePertinenceChecker extends PertinenceChecker {

    /**
     * Constructor. 
     * Returns FAILED when the attribute is not pertinent.
     * 
     * @param attributeName
     * @param checkEmptiness
     * @param attributeNameToCompare
     * @param extensionsListNameToCompareWith
     * @param notPertinentMessageCode
     * @param manualCheckMessage 
     */
    public AttributePertinenceChecker(
            String attributeName,
            boolean checkEmptiness,
            String attributeNameToCompare,
            String extensionsListNameToCompareWith,
            String notPertinentMessageCode,
            String manualCheckMessage) {
        super(manualCheckMessage);

         // The first check, when requested, is the attribute emptiness
        if (checkEmptiness) {
            addChecker(new AttributeEmptinessChecker(
                        attributeName, 
                        TestSolution.FAILED, 
                        TestSolution.PASSED, 
                        notPertinentMessageCode, 
                        null));
        }

        if (StringUtils.isNotBlank(extensionsListNameToCompareWith)) {
            addChecker(new AttributeEndsWithChecker(
                            attributeName, 
                            extensionsListNameToCompareWith,
                            notPertinentMessageCode));
        }
        
        // The second check consists in verifying the attribute does not only
        // contains non alphanumerical characters
        addChecker(new AttributeOnlyContainsNonAlphanumericalCharactersChecker(
                        attributeName,   
                        notPertinentMessageCode));
        
        // The last check, when requested, consists in verifying the attribute
        // content is not identical to another attribute.
        if (StringUtils.isNotBlank(attributeNameToCompare)) {
            addChecker(new AttributeNotIdenticalToOtherAttributeChecker(
                            attributeName, 
                            attributeNameToCompare, 
                            notPertinentMessageCode));
        }
    }
    
    /**
     * Constructor.
     * Returns FAILED when the attribute is not pertinent.
     * 
     * @param attributeName
     * @param checkEmptiness
     * @param attributeNameToCompare
     * @param extensionsListNameToCompareWith
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     * @param eeAttributeNameList 
     */
    public AttributePertinenceChecker(
            String attributeName,
            boolean checkEmptiness,
            String attributeNameToCompare,
            String extensionsListNameToCompareWith,
            String notPertinentMessageCode,
            String manualCheckMessage,
            String... eeAttributeNameList) {
        super(manualCheckMessage, eeAttributeNameList);

         // The first check, when requested, is the attribute emptiness
        if (checkEmptiness) {
            addChecker(new AttributeEmptinessChecker(
                        attributeName, 
                        TestSolution.FAILED, 
                        TestSolution.PASSED, 
                        notPertinentMessageCode, 
                        null, 
                        eeAttributeNameList));
        }

        if (StringUtils.isNotBlank(extensionsListNameToCompareWith)) {
            addChecker(new AttributeEndsWithChecker(
                            attributeName, 
                            extensionsListNameToCompareWith,
                            notPertinentMessageCode, 
                            eeAttributeNameList));
        }
        
        // The second check consists in verifying the attribute does not only
        // contains non alphanumerical characters
        addChecker(new AttributeOnlyContainsNonAlphanumericalCharactersChecker(
                        attributeName,   
                        notPertinentMessageCode, 
                        eeAttributeNameList));
        
        // The last check, when requested, consists in verifying the attribute
        // content is not identical to another attribute.
        if (StringUtils.isNotBlank(attributeNameToCompare)) {
            addChecker(new AttributeNotIdenticalToOtherAttributeChecker(
                            attributeName, 
                            attributeNameToCompare, 
                            notPertinentMessageCode, 
                            eeAttributeNameList));
        }
    }
    
    /**
     * Constructor. 
     * Enables to override the not pertinent solution.
     * 
     * @param attributeName
     * @param checkEmptiness
     * @param attributeNameToCompare
     * @param extensionsListNameToCompareWith
     * @param attrNotPertinentSolution
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     */
    public AttributePertinenceChecker(
            String attributeName,
            boolean checkEmptiness,
            String attributeNameToCompare,
            String extensionsListNameToCompareWith,
            TestSolution attrNotPertinentSolution,
            String notPertinentMessageCode,
            String manualCheckMessage) {
        super(manualCheckMessage, attrNotPertinentSolution);

         // The first check, when requested, is the attribute emptiness
        if (checkEmptiness) {
            addChecker(new AttributeEmptinessChecker(
                        attributeName, 
                        attrNotPertinentSolution, 
                        TestSolution.PASSED, 
                        notPertinentMessageCode, 
                        null));
        }

        if (StringUtils.isNotBlank(extensionsListNameToCompareWith)) {
            addChecker(new AttributeEndsWithChecker(
                            attributeName,
                            extensionsListNameToCompareWith,
                            attrNotPertinentSolution,
                            notPertinentMessageCode));
        }
        
        // The second check consists in verifying the attribute does not only
        // contains non alphanumerical characters
        addChecker(new AttributeOnlyContainsNonAlphanumericalCharactersChecker(
                        attributeName,   
                        attrNotPertinentSolution,
                        notPertinentMessageCode));
        
        // The last check, when requested, consists in verifying the attribute
        // content is not identical to another attribute.
        if (StringUtils.isNotBlank(attributeNameToCompare)) {
            addChecker(new AttributeNotIdenticalToOtherAttributeChecker(
                            attributeName, 
                            attributeNameToCompare, 
                            attrNotPertinentSolution,
                            notPertinentMessageCode));
        }
    }
    
    /**
     * Constructor. 
     * Enables to override the not pertinent solution.
     * 
     * @param attributeName
     * @param checkEmptiness
     * @param attributeNameToCompare
     * @param extensionsListNameToCompareWith
     * @param attrNotPertinentSolution
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     * @param eeAttributeNameList 
     */
    public AttributePertinenceChecker(
            String attributeName,
            boolean checkEmptiness,
            String attributeNameToCompare,
            String extensionsListNameToCompareWith,
            TestSolution attrNotPertinentSolution,
            String notPertinentMessageCode,
            String manualCheckMessage,
            String... eeAttributeNameList) {
        super(manualCheckMessage, attrNotPertinentSolution, eeAttributeNameList);

         // The first check, when requested, is the attribute emptiness
        if (checkEmptiness) {
            addChecker(new AttributeEmptinessChecker(
                        attributeName, 
                        attrNotPertinentSolution, 
                        TestSolution.PASSED, 
                        notPertinentMessageCode, 
                        null, 
                        eeAttributeNameList));
        }

        if (StringUtils.isNotBlank(extensionsListNameToCompareWith)) {
            addChecker(new AttributeEndsWithChecker(
                            attributeName,
                            extensionsListNameToCompareWith,
                            attrNotPertinentSolution,
                            notPertinentMessageCode,
                            eeAttributeNameList));
        }
        
        // The second check consists in verifying the attribute does not only
        // contains non alphanumerical characters
        addChecker(new AttributeOnlyContainsNonAlphanumericalCharactersChecker(
                        attributeName,   
                        attrNotPertinentSolution,
                        notPertinentMessageCode, 
                        eeAttributeNameList));
        
        // The last check, when requested, consists in verifying the attribute
        // content is not identical to another attribute.
        if (StringUtils.isNotBlank(attributeNameToCompare)) {
            addChecker(new AttributeNotIdenticalToOtherAttributeChecker(
                            attributeName, 
                            attributeNameToCompare, 
                            attrNotPertinentSolution,
                            notPertinentMessageCode, 
                            eeAttributeNameList));
        }
    }

}