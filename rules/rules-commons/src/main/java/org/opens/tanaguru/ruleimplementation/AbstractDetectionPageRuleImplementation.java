/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.opens.tanaguru.ruleimplementation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;

/**
 * This class should be overridden by concrete {@link RuleImplementation} 
 * classes which implement tests with page scope.
 * <p> It deals with the detection of elements.</p>
 * Elements are retrieved through the {@link ElementSelector} set by constructor
 * argument. 
 * Once retrieved, the {@link ElementPresenceChecker} is called and returns 
 * by default PASSED when no element has been detected and FAILED when at least
 * one element has been detected. The solution of the detection and the no detection
 * can be overridden by constructor argument.
 */
public abstract class AbstractDetectionPageRuleImplementation
            extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    private final ElementPresenceChecker epc;
    public ElementPresenceChecker getElementPresenceChecker() {
        return epc;
    }

//    /**
//     * Constructor
//     * 
//     * @param elementSelector
//     * @param messageCodeOnElementDetected
//     * @param messageCodeOnElementNotDetected
//     * @param eeAttributeNameList 
//     */
//    public AbstractDetectionPageRuleImplementation(
//            @Nonnull ElementSelector elementSelector, 
//            @Nullable String messageCodeOnElementDetected, 
//            @Nullable String messageCodeOnElementNotDetected,
//            String... eeAttributeNameList) {
//        this(elementSelector, 
//             ElementPresenceChecker.DEFAULT_DETECTED_SOLUTION,
//             ElementPresenceChecker.DEFAULT_NOT_DETECTED_SOLUTION,
//             messageCodeOnElementDetected, 
//             messageCodeOnElementNotDetected, 
//             eeAttributeNameList);
//    }
    
    /**
     * Constructor
     * 
     * @param elementSelector
     * @param detectedSolutionPair
     * @param notDetectedSolutionPair
     * @param eeAttributeNameList 
     */
    public AbstractDetectionPageRuleImplementation(
            @Nonnull ElementSelector elementSelector, 
            @Nonnull Pair<TestSolution, String> detectedSolutionPair, 
            @Nonnull Pair<TestSolution, String> notDetectedSolutionPair, 
            String... eeAttributeNameList) {
        super(elementSelector, 
              new ElementPresenceChecker(
                            detectedSolutionPair, 
                            notDetectedSolutionPair, 
                            eeAttributeNameList)
        );

        this.epc = (ElementPresenceChecker)getElementChecker();
    }
    
    /**
     * @Deprecated 
     * Use constructor with Pair instead
     * 
     * @param elementSelector
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param eeAttributeNameList 
     */
    public AbstractDetectionPageRuleImplementation(
            @Nonnull ElementSelector elementSelector, 
            @Nonnull TestSolution detectedSolution, 
            @Nonnull TestSolution notDetectedSolution, 
            @Nullable String messageCodeOnElementDetected, 
            @Nullable String messageCodeOnElementNotDetected,
            String... eeAttributeNameList) {
        super(elementSelector, 
              new ElementPresenceChecker(
                            new ImmutablePair(detectedSolution, messageCodeOnElementDetected),
                            new ImmutablePair(notDetectedSolution, messageCodeOnElementNotDetected),
                            eeAttributeNameList)
        );

        this.epc = (ElementPresenceChecker)getElementChecker();
    }

}