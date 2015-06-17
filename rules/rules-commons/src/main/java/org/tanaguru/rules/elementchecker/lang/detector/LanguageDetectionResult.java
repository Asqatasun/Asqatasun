/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
package org.tanaguru.rules.elementchecker.lang.detector;

import com.cybozu.labs.langdetect.Language;
import org.apache.commons.lang3.StringUtils;

/**
 * This class handles the detected language, its probability and the 
 * reliability of the detection.
 *
 */
public class LanguageDetectionResult {

    private static final int MIN_NUMBER_OF_WORDS_TO_BE_RELIABLE = 20;
    private static final double MIN_PROBABILITY_TO_BE_RELIABLE = 0.99;
    
    /**
     * The detected language
     */
    private String detectedLanguage;

    public String getDetectedLanguage() {
        return detectedLanguage;
    }

    public void setDetectedLanguage(String detectedLanguage) {
        this.detectedLanguage = detectedLanguage;
    }
    
    /**
     * The detected language
     */
    private boolean isMultipleLanguage;

    public boolean isMultipleLanguage() {
        return isMultipleLanguage;
    }

    public void setMultipleLanguage(boolean isMultipleLanguage) {
        this.isMultipleLanguage = isMultipleLanguage;
    }
    
    /**
     * The probability of the result
     */
    private final double probability;
    /**
     * The number of words of the tested text
     */
    private int numberOfWords = 0;

    private void computeNumberOfWords(String testedText) {
        String trimmedText = StringUtils.trim(testedText);
        if (StringUtils.isBlank(trimmedText)) {
            return;
        }
        numberOfWords = trimmedText.split("\\s+").length;
    }

    /**
     *
     * @param language
     * @param testedText
     * @param isMultipleLanguage
     */
    public LanguageDetectionResult(Language language, String testedText, boolean isMultipleLanguage) {
        this.detectedLanguage = language.lang;
        this.probability = language.prob;
        this.isMultipleLanguage = isMultipleLanguage;
        computeNumberOfWords(testedText);
    }

    /**
     *
     * @return whether the detection is reliable regarding its probability and
     * the number of tested words
     */
    public boolean isReliable() {
        return probability > MIN_PROBABILITY_TO_BE_RELIABLE
                && numberOfWords >= MIN_NUMBER_OF_WORDS_TO_BE_RELIABLE;
    }
 
}