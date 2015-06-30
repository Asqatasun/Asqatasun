/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
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
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */

package org.tanaguru.rules.elementchecker.text;

import java.util.Collection;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import org.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * This class checks whether a given textual element ends with an extension
 * that belongs to a nomenclature
 */
public class TextEndsWithChecker extends NomenclatureBasedElementChecker {

    /* the extension collection */
    private Collection<String> extensions;
    public Collection<String> getExtensions() {
        if (extensions == null) {
            extensions = getNomenclatureLoaderService().
                    loadByCode(extensionListNomName).getValueList();
        }
        return extensions;
    }
    
    /* The name of the nomenclature that handles the extension list */
    private String extensionListNomName;

    /*
     * The message thrown when an element ends with an extension that belongs to
     * to the extension list.
     */
    private String textEndsWithMessageCode;
    
    /*
     * The message thrown when an element doesn't end with an extension that belongs to
     * to the extension list.
     */
    private String textNotEndsWithMessageCode;
    
    /* The text element builder. By default, it is a simple Text builder */
    private TextElementBuilder testableTextBuilder;
    
    /**
     * Constructor
     * @param testableTextBuilder
     * @param extensionListNomName
     * @param textEndsWithMessageCode
     */
    public TextEndsWithChecker(
            TextElementBuilder testableTextBuilder,
            String extensionListNomName,
            String textEndsWithMessageCode) {
        super();
        this.testableTextBuilder = testableTextBuilder;
        this.extensionListNomName = extensionListNomName;
        this.textEndsWithMessageCode = textEndsWithMessageCode;
    }
    
    /**
     * Constructor
     * @param testableTextBuilder
     * @param extensionListNomName
     * @param textEndsWithMessageCode
     * @param eeAttributeNameList 
     */
    public TextEndsWithChecker(
            TextElementBuilder testableTextBuilder,
            String extensionListNomName,
            String textEndsWithMessageCode,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.testableTextBuilder = testableTextBuilder;
        this.extensionListNomName = extensionListNomName;
        this.textEndsWithMessageCode = textEndsWithMessageCode;
    }
    
    /**
     * Constructor
     * @param testableTextBuilder
     * @param extensionListNomName
     * @param detectedSolution
     * @param notDetectedSolution
     * @param textEndsWithMessageCode
     */
    public TextEndsWithChecker(
            TextElementBuilder testableTextBuilder,
            String extensionListNomName,
            TestSolution detectedSolution, 
            TestSolution notDetectedSolution, 
            String textEndsWithMessageCode) {
        this(testableTextBuilder, extensionListNomName, textEndsWithMessageCode);
        setFailureSolution(detectedSolution);
    }
    
    /**
     * Constructor
     * @param testableTextBuilder
     * @param extensionListNomName
     * @param detectedSolution
     * @param textEndsWithMessageCode
     * @param eeAttributeNameList 
     */
    public TextEndsWithChecker(
            TextElementBuilder testableTextBuilder,
            String extensionListNomName,
            TestSolution detectedSolution, 
            String textEndsWithMessageCode,
            String... eeAttributeNameList) {
        this(testableTextBuilder,
             extensionListNomName, 
             textEndsWithMessageCode,
             eeAttributeNameList);
        setFailureSolution(detectedSolution);
    }
    
    /**
     * Constructor
     * @param testableTextBuilder
     * @param extensionListNomName
     * @param detectedSolution
     * @param notDetectedSolution
     * @param textEndsWithMessageCode
     * @param textNotEndsWithMessageCode
     * @param eeAttributeNameList 
     */
    public TextEndsWithChecker(
            TextElementBuilder testableTextBuilder,
            String extensionListNomName,
            TestSolution detectedSolution, 
            TestSolution notDetectedSolution, 
            String textEndsWithMessageCode,
            String textNotEndsWithMessageCode,
            String... eeAttributeNameList) {
        this(testableTextBuilder,
             extensionListNomName, 
             textEndsWithMessageCode,
             eeAttributeNameList);
        setFailureSolution(detectedSolution);
        setSuccessSolution(notDetectedSolution);
        this.textNotEndsWithMessageCode = textNotEndsWithMessageCode;
    }

    @Override
    protected void doCheck(
             SSPHandler sspHandler, 
             Elements elements, 
             TestSolutionHandler testSolutionHandler) {
         for (Element element : elements) {
             testSolutionHandler.addTestSolution(
                     checkTextElementEndsWithExtension(
                        element, 
                        this.testableTextBuilder.buildTextFromElement(element)));
         }
    }
    
    /**
     * 
     * @param element
     * @param elementText
     * @return failed whether the text belongs to a blacklist, need_more_info
     * instead
     */
    private TestSolution checkTextElementEndsWithExtension(
            Element element,
            String elementText) {
        // the test is made through the getter to force the initialisation
        if (element == null ||
                elementText == null ||
                CollectionUtils.isEmpty(getExtensions())) {
            return TestSolution.NOT_APPLICABLE;
        }
        for (String extension : extensions) {
            if (StringUtils.endsWithIgnoreCase(elementText, extension)) {
                addSourceCodeRemark(getFailureSolution(),element,textEndsWithMessageCode);
                return getFailureSolution();
            }
        }
        if (!getSuccessSolution().equals(TestSolution.PASSED) && 
            !getSuccessSolution().equals(TestSolution.NOT_APPLICABLE)) {
            addSourceCodeRemark(getSuccessSolution(),element,textNotEndsWithMessageCode);
        }
        return getSuccessSolution();
    }

}