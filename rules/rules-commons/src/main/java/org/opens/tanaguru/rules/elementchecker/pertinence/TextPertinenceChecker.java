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

import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.elementchecker.attribute.TextElementBelongsToBlackListChecker;
import org.opens.tanaguru.rules.elementchecker.attribute.TextElementEmptinessChecker;
import org.opens.tanaguru.rules.elementchecker.attribute.TextElementNotIdenticalToAttributeChecker;
import org.opens.tanaguru.rules.elementchecker.attribute.TextElementOnlyContainsNonAlphanumericalCharactersChecker;

/**
 * 
 */
public class TextPertinenceChecker extends PertinenceChecker {

    /**
     * Constructor.
     * Returns FAILED when the attribute is not pertinent.
     * 
     * @param checkEmptiness
     * @param attributeNameToCompare
     * @param blacklistNameToCompareWith
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     */
    public TextPertinenceChecker(
            boolean checkEmptiness,
            @Nullable String attributeNameToCompare,
            @Nullable String blacklistNameToCompareWith,
            String notPertinentMessageCode,
            String manualCheckMessage) {
        super(manualCheckMessage);

        // The first check, when requested, is the consists in verifying
        // the element content emptiness
        if (checkEmptiness) {
            addChecker(new TextElementEmptinessChecker(
                        TestSolution.FAILED, 
                        TestSolution.PASSED, 
                        notPertinentMessageCode, 
                        null));
        }

        // The second check consists in verifying the element content 
        // does not belong to a blacklist 
        if (StringUtils.isNotBlank(blacklistNameToCompareWith)) {
            addChecker(new TextElementBelongsToBlackListChecker(
                        blacklistNameToCompareWith, 
                        notPertinentMessageCode));
        }
        
        // The third check consists in verifying the element content does not only
        // contains non alphanumerical characters
        addChecker(new TextElementOnlyContainsNonAlphanumericalCharactersChecker(
                        notPertinentMessageCode));
        
        // The last check, when requested, consists in verifying the element
        // content is not identical to another attribute.
        if (StringUtils.isNotBlank(attributeNameToCompare)) {
            addChecker(new TextElementNotIdenticalToAttributeChecker(
                            attributeNameToCompare, 
                            notPertinentMessageCode));
        }
    }
    
    /**
     * Constructor.
     * Returns FAILED when the attribute is not pertinent.
     * 
     * @param checkEmptiness
     * @param attributeNameToCompare
     * @param blacklistNameToCompareWith
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     * @param eeAttributeNameList
     */
    public TextPertinenceChecker(
            boolean checkEmptiness,
            @Nullable String attributeNameToCompare,
            @Nullable String blacklistNameToCompareWith,
            String notPertinentMessageCode,
            String manualCheckMessage,
            String... eeAttributeNameList) {
        super(manualCheckMessage, eeAttributeNameList);

        // The first check, when requested, is the consists in verifying
        // the element content emptiness
        if (checkEmptiness) {
            addChecker(new TextElementEmptinessChecker(
                        TestSolution.FAILED, 
                        TestSolution.PASSED, 
                        notPertinentMessageCode, 
                        null, 
                        eeAttributeNameList));
        }

        // The second check consists in verifying the element content does not only
        // contains non alphanumerical characters
        if (StringUtils.isNotBlank(blacklistNameToCompareWith)) {
            addChecker(new TextElementBelongsToBlackListChecker(
                        blacklistNameToCompareWith, 
                        notPertinentMessageCode, 
                        eeAttributeNameList));
        }
        
        // The third check consists in verifying the element content does not only
        // contains non alphanumerical characters
        addChecker(new TextElementOnlyContainsNonAlphanumericalCharactersChecker(
                        notPertinentMessageCode, 
                        eeAttributeNameList));
        
        // The last check, when requested, consists in verifying the element
        // content is not identical to another attribute.
        if (StringUtils.isNotBlank(attributeNameToCompare)) {
            addChecker(new TextElementNotIdenticalToAttributeChecker(
                            attributeNameToCompare, 
                            notPertinentMessageCode,    
                            eeAttributeNameList));
        }
    }
    
    /**
     * Constructor.
     * Enables to override the not pertinent solution.
     * 
     * @param checkEmptiness
     * @param attributeNameToCompare
     * @param blacklistNameToCompareWith
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     * @param eeAttributeNameList 
     */
    public TextPertinenceChecker(
            boolean checkEmptiness,
            String attributeNameToCompare,
            String blacklistNameToCompareWith,
            TestSolution notPertinentSolution,
            String notPertinentMessageCode,
            String manualCheckMessage,
            String... eeAttributeNameList) {
        super(manualCheckMessage, notPertinentSolution, eeAttributeNameList);

        // The first check, when requested, is the consists in verifying
        // the element content emptiness
        if (checkEmptiness) {
            addChecker(new TextElementEmptinessChecker(
                        notPertinentSolution, 
                        TestSolution.PASSED, 
                        notPertinentMessageCode, 
                        null, 
                        eeAttributeNameList));
        }

        // The second check consists in verifying the element content does not only
        // contains non alphanumerical characters
        if (StringUtils.isNotBlank(blacklistNameToCompareWith)) {
            addChecker(new TextElementBelongsToBlackListChecker(
                        blacklistNameToCompareWith,
                        notPertinentSolution,
                        notPertinentMessageCode, 
                        eeAttributeNameList));
        }
        
        // The third check consists in verifying the element content does not only
        // contains non alphanumerical characters
        addChecker(new TextElementOnlyContainsNonAlphanumericalCharactersChecker(
                        notPertinentSolution,
                        notPertinentMessageCode, 
                        eeAttributeNameList));
        
        // The last check, when requested, consists in verifying the element
        // content is not identical to another attribute.
        if (StringUtils.isNotBlank(attributeNameToCompare)) {
            addChecker(new TextElementNotIdenticalToAttributeChecker(
                            attributeNameToCompare, 
                            notPertinentSolution,
                            notPertinentMessageCode, 
                            eeAttributeNameList));
        }
    }

}