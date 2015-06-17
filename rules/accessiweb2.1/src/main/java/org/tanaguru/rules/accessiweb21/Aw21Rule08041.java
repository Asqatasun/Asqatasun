/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.tanaguru.rules.accessiweb21;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.RuleHelper;
import org.tanaguru.rules.accessiweb21.handler.lang.AbstractPageRuleLangThemeImplementation;
import org.w3c.dom.Node;

/**
 * 
 * @author jkowalczyk
 */
public class Aw21Rule08041 extends AbstractPageRuleLangThemeImplementation {

    /**
     *
     */
    public Aw21Rule08041() {
        super();
    }

    /**
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        super.processImpl(sspHandler);
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
        Set<ProcessRemark> prSet = new HashSet<ProcessRemark>();
        String lang;
        String extractedText;
        // if the lang attribute is provided by the html tag, we check its
        // validity and its relevancy
        Matcher m = null;
        if (langRulesHandler.isLanguageProvidedByHtmlTag()) {
            Node node = langRulesHandler.getHtmlTagWithLangAttribute();
            lang = langRulesHandler.extractLanguageFromNode(node);
            extractedText = langRulesHandler.extractTextContentOfThePage();
            m = langRulesHandler.getNonAlphanumericPattern().matcher(extractedText);
            if (!extractedText.isEmpty() && !m.matches() ) {
                resultSet.add(langRulesHandler.checkLanguageDeclarationValidity(node, lang));
                resultSet.add(langRulesHandler.checkLanguageRelevancy(node, lang, extractedText));
            }
        } else {
            resultSet.add(TestSolution.NOT_APPLICABLE);
        }
        prSet.addAll(sspHandler.getProcessRemarkService().getRemarkList());
        // if the lang attribute is provided by some attribute, we check the
        // validity and its relevancy on the specified text
        List<Node> nodeWithLangAttrList = langRulesHandler.getNodeWithLangAttribute();
        if (!nodeWithLangAttrList.isEmpty()) {
            for (Node node : nodeWithLangAttrList) {
                lang = langRulesHandler.extractLanguageFromNode(node);
                extractedText = langRulesHandler.extractTextContentFromNodeAndChildNode(node);
                m = langRulesHandler.getNonAlphanumericPattern().matcher(extractedText);
                if (!extractedText.isEmpty() && !m.matches()) {
                    resultSet.add(langRulesHandler.checkLanguageDeclarationValidity(node, lang));
                    resultSet.add(langRulesHandler.checkLanguageRelevancy(node, lang, extractedText));
                    prSet.addAll(sspHandler.getProcessRemarkService().getRemarkList());
                }
            }
        }
        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                RuleHelper.synthesizeTestSolutionCollection(resultSet),
                prSet);
        return processResult;
    }

}