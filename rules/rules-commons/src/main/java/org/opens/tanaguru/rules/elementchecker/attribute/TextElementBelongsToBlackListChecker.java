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

package org.opens.tanaguru.rules.elementchecker.attribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;
import org.apache.commons.collections.CollectionUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import org.opens.tanaguru.rules.elementchecker.TextualElementBuilder;

/**
 * 
 * @author jkowalczyk
 */
public class TextElementBelongsToBlackListChecker extends NomenclatureBasedElementChecker 
            implements TextualElementBuilder {

    private static final String NON_ALPHANUMERIC_PATTERN_STR ="[^\\p{L}]*";
    private static final String TEXT_CASE_INSENSITIVE_PATTERN_STR ="(?i)";

    /**
     * The black list pattern collection build from case insensitive 
     * nomenclature elements prefixed and suffixed by non alphanumeric pattern
     */
    private Collection<Pattern> blackListPatternCollection;
    public Collection<Pattern> getBlackListPatternCollection() {
        if (blackListPatternCollection == null) {
            blackListPatternCollection = new ArrayList<Pattern>();
            StringBuilder patternBuilder = new StringBuilder();
            for (String blacklistedText : getNomenclatureLoaderService().loadByCode(blackListNomName).getValueList()) {
                patternBuilder.setLength(0);
                patternBuilder.append(TEXT_CASE_INSENSITIVE_PATTERN_STR);
                patternBuilder.append(NON_ALPHANUMERIC_PATTERN_STR);
                patternBuilder.append(blacklistedText);
                patternBuilder.append(NON_ALPHANUMERIC_PATTERN_STR);
                blackListPatternCollection.add(Pattern.compile(patternBuilder.toString()));
            }
        }
        return blackListPatternCollection;
    }
    
    /**
     * The name of the nomenclature that handles the blacklist.
     */
    private String blackListNomName;

    /**
     * The message thrown when an element belongs to the black list.
     */
    private String textBelongsToBlackListMessageCode;
    
    /**
     * Detected solution. Default is FAILED.
     */
    private TestSolution detectedSolution = TestSolution.FAILED;
    
    /**
     * Constructor
     * @param blackListNomName
     * @param textBelongsToBlackListMessageCode
     */
    public TextElementBelongsToBlackListChecker(
            String blackListNomName,
            String textBelongsToBlackListMessageCode) {
        super();
        this.blackListNomName = blackListNomName;
        this.textBelongsToBlackListMessageCode = textBelongsToBlackListMessageCode;
    }
    
    /**
     * Constructor
     * @param blackListNomName
     * @param textBelongsToBlackListMessageCode
     * @param eeAttributeNameList 
     */
    public TextElementBelongsToBlackListChecker(
            String blackListNomName,
            String textBelongsToBlackListMessageCode,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.blackListNomName = blackListNomName;
        this.textBelongsToBlackListMessageCode = textBelongsToBlackListMessageCode;
    }
    
    /**
     * Constructor
     * @param blackListNomName
     * @param detectedSolution
     * @param textBelongsToBlackListMessageCode
     */
    public TextElementBelongsToBlackListChecker(
            String blackListNomName,
            TestSolution detectedSolution,
            String textBelongsToBlackListMessageCode) {
        super();
        this.blackListNomName = blackListNomName;
        this.detectedSolution = detectedSolution;
        this.textBelongsToBlackListMessageCode = textBelongsToBlackListMessageCode;
    }
    
    /**
     * Constructor
     * @param blackListNomName
     * @param detectedSolution
     * @param textBelongsToBlackListMessageCode
     * @param eeAttributeNameList 
     */
    public TextElementBelongsToBlackListChecker(
            String blackListNomName,
            TestSolution detectedSolution,
            String textBelongsToBlackListMessageCode,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.blackListNomName = blackListNomName;
        this.detectedSolution = detectedSolution;
        this.textBelongsToBlackListMessageCode = textBelongsToBlackListMessageCode;
    }

    @Override
    protected void doCheck(
             SSPHandler sspHandler, 
             Elements elements, 
             TestSolutionHandler testSolutionHandler) {
         for (Element element : elements) {
             testSolutionHandler.addTestSolution(
                     checkTextElementBelongsToBlacklist(
                        element, 
                        buildTextFromElement(element)));
         }
    }
    
    /**
     * 
     * @param element
     * @param elementText
     * @return failed whether the text belongs to a blacklist, need_more_info
     * instead
     */
    private TestSolution checkTextElementBelongsToBlacklist(
            Element element,
            String elementText) {
        // the test is made through the getter to force the initialisation
        if (element == null || 
                CollectionUtils.isEmpty(getBlackListPatternCollection())) {
            return TestSolution.NOT_APPLICABLE;
        }
        for (Pattern pattern : blackListPatternCollection) {
            if (pattern.matcher(elementText).matches()) {
                getProcessRemarkService().addSourceCodeRemarkOnElement(
                    detectedSolution,
                    element,
                    textBelongsToBlackListMessageCode, 
                    getEvidenceElementCollection(element, getEeAttributeNameList()));
                return detectedSolution;
            }
        }
        return TestSolution.PASSED;
    }

    @Override
    public String buildTextFromElement(Element element) {
        return element.text().trim();
    }
    
}