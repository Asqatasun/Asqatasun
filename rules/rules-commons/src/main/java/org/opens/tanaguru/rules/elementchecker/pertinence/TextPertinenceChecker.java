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

import java.util.Collections;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.elementchecker.CompositeChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextBelongsToBlackListChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextEmptinessChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextNotIdenticalToAttributeChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextOnlyContainsNonAlphanumericalCharactersChecker;
import org.opens.tanaguru.rules.textbuilder.SimpleTextElementBuilder;
import org.opens.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * This class checks whether a text is pertinent by verifying :
 * <ul>
 * <li>it is not empty</li>
 * <li>it is not only composed of non alphanumerical characters</li>
 * <li>it is not blacklisted</li>
 * <li>it is not identical to another text (the content of another attribue
 * for instance)</li>
  *</ul>
 */
public class TextPertinenceChecker extends CompositeChecker {

    /* The element builder needed to retrieve the text to check */
    private TextElementBuilder textElementBuilder;
    @Override
    public TextElementBuilder getTextElementBuilder() {
        if (textElementBuilder == null) {
            textElementBuilder = new SimpleTextElementBuilder();
        }
        return textElementBuilder;
    }

    /* check emptiness */
    private boolean checkEmptiness;
    /* attribute name to compare with*/
    private TextElementBuilder textElementBuilderToCompareWith;
    /* blacklist name to compare with*/
    private String blacklistNameToCompareWith = null;
    /* not pertinent message code */
    String notPertinentMessageCode = null;
    
    /**
     * constructor.
     * Enables to override the not pertinent solution.
     * 
     * @param checkEmptiness
     * @param textElementBuilderToCompareWith
     * @param blacklistNameToCompareWith
     * @param notPertinentSolution
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     * @param eeAttributeNameList 
     */
    public TextPertinenceChecker(
            boolean checkEmptiness,
            TextElementBuilder textElementBuilderToCompareWith, 
            String blacklistNameToCompareWith,
            TestSolution notPertinentSolution,
            String notPertinentMessageCode,
            String manualCheckMessage,
            String... eeAttributeNameList) {
        super(notPertinentSolution, eeAttributeNameList);

        addCheckMessageFromSolution(
                TestSolution.PASSED, 
                Collections.singletonMap(TestSolution.NEED_MORE_INFO, manualCheckMessage));
        
        this.checkEmptiness = checkEmptiness;
        this.textElementBuilderToCompareWith = textElementBuilderToCompareWith;
        this.blacklistNameToCompareWith = blacklistNameToCompareWith;
        this.notPertinentMessageCode = notPertinentMessageCode;

        addCheckers();
    }
    
    /**
     * Constructor.
     * Returns FAILED when the attribute is not pertinent.
     * 
     * @param checkEmptiness
     * @param textElementBuilderToCompareWith
     * @param blacklistNameToCompareWith
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     * @param eeAttributeNameList
     */
    public TextPertinenceChecker(
            boolean checkEmptiness,
            @Nullable TextElementBuilder textElementBuilderToCompareWith, 
            @Nullable String blacklistNameToCompareWith,
            String notPertinentMessageCode,
            String manualCheckMessage,
            String... eeAttributeNameList) {
        this(checkEmptiness, 
             textElementBuilderToCompareWith, 
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
     * @param textElementBuilder
     * @param checkEmptiness
     * @param textElementBuilderToCompareWith
     * @param blacklistNameToCompareWith
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     * @param eeAttributeNameList
     */
    public TextPertinenceChecker(
            TextElementBuilder textElementBuilder,
            boolean checkEmptiness,
            @Nullable TextElementBuilder textElementBuilderToCompareWith, 
            @Nullable String blacklistNameToCompareWith,
            String notPertinentMessageCode,
            String manualCheckMessage,
            String... eeAttributeNameList) {
        super(TestSolution.FAILED, eeAttributeNameList);

        addCheckMessageFromSolution(
                TestSolution.PASSED, 
                Collections.singletonMap(TestSolution.NEED_MORE_INFO, manualCheckMessage));
        
        this.checkEmptiness = checkEmptiness;
        this.textElementBuilderToCompareWith = textElementBuilderToCompareWith;
        this.blacklistNameToCompareWith = blacklistNameToCompareWith;
        this.notPertinentMessageCode = notPertinentMessageCode;
        this.textElementBuilder = textElementBuilder;
        
        addCheckers();
    }

    /**
     * Constructor.
     * Returns FAILED when the attribute is not pertinent.
     * 
     * @param checkEmptiness
     * @param textElementBuilderToCompareWith
     * @param blacklistNameToCompareWith
     * @param notPertinentMessageCode
     * @param manualCheckMessage
     */
    public TextPertinenceChecker(
            boolean checkEmptiness,
            @Nullable TextElementBuilder textElementBuilderToCompareWith, 
            @Nullable String blacklistNameToCompareWith,
            String notPertinentMessageCode,
            String manualCheckMessage) {
        this(checkEmptiness, 
             textElementBuilderToCompareWith, 
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
                            getFailureSolution(), 
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
                            getFailureSolution(),
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
                        getFailureSolution(),
                        notPertinentMessageCode, 
                        getEeAttributeNames()));
    }

    /**
     * Add a {@link TextNotIdenticalToAttributeChecker} to the checker collection 
     */
    protected final void addAttributeComparisonChecker() {
        // The last check, when requested, consists in verifying the element
        // content is not identical to another attribute.
        if (textElementBuilderToCompareWith != null) {
            addChecker(new TextNotIdenticalToAttributeChecker(
                            getTextElementBuilder(),
                            textElementBuilderToCompareWith, 
                            getFailureSolution(),
                            TestSolution.PASSED,
                            notPertinentMessageCode, 
                            null,
                            getEeAttributeNames()));
        }
    }

}