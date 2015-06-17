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

package org.tanaguru.rules.elementchecker.lang;

import javax.annotation.Nonnull;
import org.jsoup.nodes.Element;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import static org.tanaguru.rules.keystore.RemarkMessageStore.*;

/**
 * This class checks whether the lang declaration is valid and relevant
 */
public class LangDeclarationValidityChecker extends LangChecker {

    /** check declaration validity */
    private final boolean checkDeclarationValidity;
    
    /** check declaration relevancy */
    private final boolean checkDeclarationRelevancy;
    
    /**
     * Default constructor
     * @param checkDeclarationValidity
     * @param checkDeclarationRelevancy
     */
    public LangDeclarationValidityChecker(
            @Nonnull boolean checkDeclarationValidity,
            @Nonnull boolean checkDeclarationRelevancy) {
        super(null,
              IRRELEVANT_LANG_DECL_MSG, 
              SUSPECTED_IRRELEVANT_LANG_DECL_MSG, 
              SUSPECTED_RELEVANT_LANG_DECL_MSG);
        this.checkDeclarationValidity = checkDeclarationValidity;
        this.checkDeclarationRelevancy = checkDeclarationRelevancy;
    }

    @Override
    protected TestSolution doCheckLanguage(Element element, SSPHandler sspHandler) {
          return checkLanguageDeclarationValidity(element, sspHandler);
    }

    /**
     * 
     * @param element
     * @param sspHandler
     * @return 
     */
    public TestSolution checkLanguageDeclarationValidity(Element element, SSPHandler sspHandler) {
        String langDefinition = extractLangDefinitionFromElement(element, sspHandler);
        String effectiveLang = extractEffectiveLang(langDefinition);
        TestSolution declarationValidity = 
                checkLanguageDeclarationValidity(
                    element, 
                    langDefinition, 
                    effectiveLang,
                    checkDeclarationValidity);
        if (checkDeclarationValidity && declarationValidity.equals(TestSolution.FAILED)) {
            return TestSolution.FAILED;
        }
        if (checkDeclarationRelevancy) {
            if (declarationValidity.equals(TestSolution.FAILED)) {
                return TestSolution.NOT_APPLICABLE;
            }
            String extractedText = extractTextFromElement(element, true);
            if (isTextTestable(extractedText)) {
                return checkLanguageRelevancy(
                        element, 
                        effectiveLang, 
                        null,
                        extractedText, 
                        TestSolution.PASSED,
                        TestSolution.FAILED);
            }
        }
        return TestSolution.PASSED;
    }
    
}