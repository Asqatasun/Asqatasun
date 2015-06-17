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

import java.util.List;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.rules.accessiweb21.handler.lang.AbstractPageRuleLangThemeImplementation;
import org.tanaguru.rules.accessiweb21.handler.lang.LangRulesHandler;
import org.w3c.dom.Node;

/**
 * 
 * @author jkowalczyk
 */
public class Aw21Rule08031 extends AbstractPageRuleLangThemeImplementation {

    /**
     *
     */
    public Aw21Rule08031() {
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
        TestSolution testSolution = TestSolution.PASSED;
        if(!langRulesHandler.isLanguageProvidedByHtmlTag()) {
            if (langRulesHandler.isLanguageAbsentOnThePage()) {
                testSolution = TestSolution.FAILED;
                sspHandler.getProcessRemarkService().addProcessRemark(
                        TestSolution.FAILED,
                        LangRulesHandler.LANG_ATTRIBUTE_MISSING_ON_WHOLE_PAGE_MSG_CODE);
            } else {
                List<Node> nodeList = langRulesHandler.getAllNodeWithoutLangAttribute();
                if (!nodeList.isEmpty()) {
                    testSolution = TestSolution.FAILED;
                        sspHandler.getProcessRemarkService().addProcessRemark(
                            TestSolution.FAILED,
                            LangRulesHandler.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG_CODE);
                }
            }
        }
        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                sspHandler.getProcessRemarkService().getRemarkList());
        return processResult;
    }

}