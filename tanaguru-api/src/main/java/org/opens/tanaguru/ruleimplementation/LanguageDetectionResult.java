/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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