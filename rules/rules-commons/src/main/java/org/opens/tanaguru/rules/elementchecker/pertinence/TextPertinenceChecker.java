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
import org.opens.tanaguru.rules.elementchecker.text.TextBelongsToBlackListChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextEmptinessChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextNotIdenticalToAttributeChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextOnlyContainsNonAlphanumericalCharactersChecker;
import org.opens.tanaguru.rules.textbuilder.SimpleTextElementBuilder;
import org.opens.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * Text pertinence of a text by invoking successively unitary checkers
 */
public class TextPertinenceChecker extends PertinenceChecker {

    /* The element builder needed to retrieve the text to check */
    private TextElementBuilder textElementBuilder;
    public TextElementBuilder getTextElementBuilder() {
        if (textElementBuilder == null) {
            textElementBuilder = new SimpleTextElementBuilder();
        }
        return textElementBuilder;
    }

    /* check emptiness */
    private boolean checkEmptiness;
    /* attribute name to compare with*/
    private String attributeNameToCompare = null;
    /* blacklist name to compare with*/
    private String blacklistNameToCompareWith = null;
    /* not pertinent message code */
    String notPertinentMessageCode = null;
    
    /**
     * constructor.
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

        this.checkEmptiness = checkEmptiness;
        this.attributeNameToCompare = attributeNameToCompare;
        this.blacklistNameToCompareWith = blacklistNameToCompareWith;
        this.notPertinentMessageCode = notPertinentMessageCode;

        addCheckers();
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
        this(checkEmptiness, 
             attributeNameToCompare, 
             blacklistNameToCompareWith, 
             TestSolution.FAILED,
             notPertinentMessageCode,
             manualCheckMessage, 
             eeAttributeNameList);
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
     */
    public TextPertinenceChecker(
            boolean checkEmptiness,
            @Nullable String attributeNameToCompare,
            @Nullable String blacklistNameToCompareWith,
            String notPertinentMessageCode,
            String manualCheckMessage) {
        this(checkEmptiness, 
             attributeNameToCompare, 
             blacklistNameToCompareWith, 
             TestSolution.FAILED,
             notPertinentMessageCode,
             manualCheckMessage);
    }
    
    /**
     * Add all the checkers to the checker collection
     */
    private void addCheckers() {
        addTextEmptinessChecker();
        addBlackListChecker();
        addTextNonAlphanumChecker();
        addAttributeComparisonChecker();
    }
    
    /**
     * Add a {@link TextEmptinessChecker} to the checker collection
     */
    protected final void addTextEmptinessChecker() {
        // The first check, when requested, is the consists in verifying
        // the element content emptiness
        if (checkEmptiness) {
            addChecker(new TextEmptinessChecker(
                            getTextElementBuilder(),
                            getNotPertinentSolution(), 
                            TestSolution.PASSED, 
                            notPertinentMessageCode, 
                            null, 
                            getEeAttributeNames()));
        }
    }

    /**
     * Add a {@link TextBelongsToBlackListChecker} to the checker collection
     */
    protected final void addBlackListChecker() {
        // The second check consists in verifying the element content does not only
        // contains non alphanumerical characters
        if (StringUtils.isNotBlank(blacklistNameToCompareWith)) {
            addChecker(new TextBelongsToBlackListChecker(
                            getTextElementBuilder(),
                            blacklistNameToCompareWith,
                            getNotPertinentSolution(),
                            notPertinentMessageCode, 
                            getEeAttributeNames()));
        }
    }
    
    /**
     * Add a {@link TextOnlyContainsNonAlphanumericalCharactersChecker}
     * to the checker collection
     */
    protected final void addTextNonAlphanumChecker() {
        // The third check consists in verifying the element content does not only
        // contains non alphanumerical characters
        addChecker(new TextOnlyContainsNonAlphanumericalCharactersChecker(
                        getTextElementBuilder(),
                        getNotPertinentSolution(),
                        notPertinentMessageCode, 
                        getEeAttributeNames()));
    }

    /**
     * Add a {@link TextNotIdenticalToAttributeChecker} to the checker collection 
     */
    protected final void addAttributeComparisonChecker() {
        // The last check, when requested, consists in verifying the element
        // content is not identical to another attribute.
        if (StringUtils.isNotBlank(attributeNameToCompare)) {
            addChecker(new TextNotIdenticalToAttributeChecker(
                            getTextElementBuilder(),
                            attributeNameToCompare, 
                            getNotPertinentSolution(),
                            notPertinentMessageCode, 
                            getEeAttributeNames()));
        }
    }

}