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

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.cybozu.labs.langdetect.Language;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Utility class, implementing the singleton pattern, that enables to 
 * determine the language of some text.
 */
public class LanguageDetector {

    private static final Logger LOGGER = Logger.getLogger(LanguageDetector.class);

    /**
     * The path from where the language profiles can be loaded. By default, 
     * these profiles are loaded from the profile directory of the jar of the 
     * lang-detect library
     */
    private String profilePath = "classpath*:profiles/*";
    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
    
    /**
     * The holder that handles the unique instance of LanguageDetector
     */
    private static class LanguageDetectorHolder {
        private static final LanguageDetector INSTANCE = new LanguageDetector();
    }
    
    /**
     * Private constructor
     */
    private LanguageDetector() {
        initProfiles();
    }
    
    /**
     * Singleton pattern based on the "Initialization-on-demand 
     * holder idiom". See @http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * @return the unique instance of LanguageDetector
     */
    public static LanguageDetector getInstance() {
        return LanguageDetectorHolder.INSTANCE;
    }

    /**
     * Perform the detection 
     * 
     * @param text to test
     * @return the detected language
     */
    public LanguageDetectionResult detectLanguage(String text) {
        try {
            Detector detector = DetectorFactory.create(0.15);
            // issue#47 correction
            detector.append(text.toLowerCase());
            ArrayList<Language> languages = detector.getProbabilities();
            Language detectedLanguage =  
                    extractLangWithHighestProbability(languages);
            return new LanguageDetectionResult(detectedLanguage, text, languages.size()>1);
        } catch (LangDetectException ex) {
            LOGGER.warn(ex);
        }
        return null;
    }

    /**
     * Multiple results are returned in a list. This method parses the different
     * results and keeps the best regarding the relevancy value.
     *
     * @param languages
     * @return the language with the highest probability
     */
    private Language extractLangWithHighestProbability(ArrayList<Language> languages) {
        double bestRelevancy = -1;
        Language langWinner = null;
        for (Language lang : languages) {
            if (lang.prob > bestRelevancy) {
                bestRelevancy = lang.prob;
                langWinner = lang;
            }
        }
        return langWinner;
    }

        /**
     * Initialise the language profiles needed by the detector. This
     * initialisation has to be performed only once.
     */
    private void initProfiles() {
        PathMatchingResourcePatternResolver resolver = 
                new PathMatchingResourcePatternResolver();
        List<String> profiles = new ArrayList<>();
        DetectorFactory.setSeed(0L);
        try {
            for (Resource rs : resolver.getResources(profilePath)) {
                StringWriter writer = new StringWriter();
                IOUtils.copy(rs.getInputStream(), writer);
                profiles.add(writer.toString());
            }
            DetectorFactory.loadProfile(profiles);
        } catch (IOException | LangDetectException ex) {
            LOGGER.warn(ex);
        }
    }
    
}