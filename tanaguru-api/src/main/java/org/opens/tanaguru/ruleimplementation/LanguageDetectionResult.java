/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.ruleimplementation;

/**
 * This class handles the detected language and the reliability of the
 * detection.
 * 
 * @author jkowalczyk
 */
public class LanguageDetectionResult {

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
     * The reliability of the result
     */
    private boolean isReliable;
    public boolean isReliable() {
        return isReliable;
    }

    public void setIsReliable(boolean isReliable) {
        this.isReliable = isReliable;
    }

    public LanguageDetectionResult(String detectedLanguage, boolean isReliable) {
        this.detectedLanguage = detectedLanguage;
        this.isReliable = isReliable;
    }
    
}