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

package org.opens.tanaguru.rules.elementchecker.text;

import java.util.Collection;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import org.opens.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * 
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
    
    /* Detected solution. Default is FAILED */
    private TestSolution detectedSolution = TestSolution.FAILED;
    
    /* The text element builder. By default, it is a simple Text builder */
    private TextElementBuilder textElementBuilder;
    
    /**
     * Constructor
     * @param textElementBuilder
     * @param extensionListNomName
     * @param textEndsWithMessageCode
     */
    public TextEndsWithChecker(
            TextElementBuilder textElementBuilder,
            String extensionListNomName,
            String textEndsWithMessageCode) {
        super();
        this.textElementBuilder = textElementBuilder;
        this.extensionListNomName = extensionListNomName;
        this.textEndsWithMessageCode = textEndsWithMessageCode;
    }
    
    /**
     * Constructor
     * @param textElementBuilder
     * @param extensionListNomName
     * @param textEndsWithMessageCode
     * @param eeAttributeNameList 
     */
    public TextEndsWithChecker(
            TextElementBuilder textElementBuilder,
            String extensionListNomName,
            String textEndsWithMessageCode,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.textElementBuilder = textElementBuilder;
        this.extensionListNomName = extensionListNomName;
        this.textEndsWithMessageCode = textEndsWithMessageCode;
    }
    
    /**
     * Constructor
     * @param textElementBuilder
     * @param extensionListNomName
     * @param detectedSolution
     * @param textEndsWithMessageCode
     */
    public TextEndsWithChecker(
            TextElementBuilder textElementBuilder,
            String extensionListNomName,
            TestSolution detectedSolution, 
            String textEndsWithMessageCode) {
        this(textElementBuilder, extensionListNomName, textEndsWithMessageCode);
        this.detectedSolution = detectedSolution;
    }
    
    /**
     * Constructor
     * @param textElementBuilder
     * @param extensionListNomName
     * @param detectedSolution
     * @param textEndsWithMessageCode
     * @param eeAttributeNameList 
     */
    public TextEndsWithChecker(
            TextElementBuilder textElementBuilder,
            String extensionListNomName,
            TestSolution detectedSolution, 
            String textEndsWithMessageCode,
            String... eeAttributeNameList) {
        this(textElementBuilder,
             extensionListNomName, 
             textEndsWithMessageCode,
             eeAttributeNameList);
        this.detectedSolution = detectedSolution;
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
                        this.textElementBuilder.buildTextFromElement(element)));
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
                
                addSourceCodeRemark(
                    detectedSolution,
                    element,
                    textEndsWithMessageCode);

                return detectedSolution;
            }
        }
        return TestSolution.PASSED;
    }

}