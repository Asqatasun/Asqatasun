/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.seo;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * Detect the presence of flash content in the page
 * 
 * @author jkowalczyk
 */
public class SeoRule08011 extends AbstractPageRuleImplementation {

    private static final String CSS_EXPR = "object";
    private static final String CSS_EXPR2 = "script";

    private static final String SWF_EXT = "swf";
    private static final String EMBED_NODE_NAME = "embed";
    private static final String SRC_ATTRIBUTE = "src";
    private static final String TYPE_ATTRIBUTE = "type";
    private static final String SWF_TYPE_DEFINITION = "application/x-shockwave-flash";

    public static final String FLASH_CONTENT_DETECTED_MESSAGE_CODE =
            "FlashContentDetected";
    public static final String SUSPECTED_FLASH_CONTENT_DETECTED_MESSAGE_CODE =
            "SuspectedFlashContentDetected";

    public SeoRule08011() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();
        TestSolution testSolution = checkObjectNodes(sspHandler.beginCssLikeSelection().
                domCssLikeSelectNodeSet(CSS_EXPR).
                getSelectedElements(),
                sspHandler);

        processRemarkList.addAll(sspHandler.getRemarkList());
        
        TestSolution scriptNodetestSolution =
                checkScriptNodes(sspHandler.domCssLikeSelectNodeSet(
                    CSS_EXPR2).getSelectedElements(),
                    sspHandler);
        
        processRemarkList.addAll(sspHandler.getRemarkList());

        if (testSolution.equals(TestSolution.PASSED)){
            testSolution = scriptNodetestSolution;
        }
        
        if (testSolution.equals(TestSolution.PASSED)){
            testSolution = checkSourceCode(sspHandler);
        }
        processRemarkList.addAll(sspHandler.getRemarkList());
        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                testSolution,
                processRemarkList);

        return result ;
    }

    /**
     * This methods checks for each &lt;object&gt; node whether its &lt;embed&gt;
     * child node contains a "src" attribute with a "swf" extension or a
     * "type" attribute equals to "application/x-shockwave-flash"
     * @param elements
     * @param sspHandler
     * @return
     */
    private TestSolution checkObjectNodes (Elements elements,
            SSPHandler sspHandler) {
        TestSolution testSolution = TestSolution.PASSED;
        if (!elements.isEmpty()) {
            for (Element element : elements) {
                Element embedElement = null;
                for (int i=0;i<element.children().size();i++){
                    if (element.child(i).nodeName().trim().
                            equalsIgnoreCase(EMBED_NODE_NAME)) {
                        embedElement = element.child(i);
                        break;
                    }
                }
                if (embedElement != null && (embedElement.hasAttr(SRC_ATTRIBUTE) &&
                            embedElement.attr(SRC_ATTRIBUTE).endsWith(SWF_EXT)) ||
                         (embedElement.hasAttr(TYPE_ATTRIBUTE) &&
                            embedElement.attr(TYPE_ATTRIBUTE).trim().equalsIgnoreCase(SWF_TYPE_DEFINITION))) {
                    testSolution = TestSolution.FAILED;
                    sspHandler.getProcessRemarkService().addSourceCodeRemarkOnElement(
                        testSolution,
                        element,
                        FLASH_CONTENT_DETECTED_MESSAGE_CODE);
                    
                }
            }
        }
        return testSolution;
    }

    /**
     * This methods checks the presence of the "swf" occurrence within
     * &lt;script&gt; nodes.
     * If the occurrence is found, a source process remark is created and the
     * testSolution is set to NEED_MORE_INFORMATION
     * @param elements
     * @param sspHandler
     * @return
     */
    private TestSolution checkScriptNodes (Elements elements,
            SSPHandler sspHandler) {
        TestSolution testSolution = TestSolution.PASSED;
        if (!elements.isEmpty()) {
            for (Element element : elements) {
                if (element.outerHtml().contains(SWF_EXT)) {
                    testSolution = TestSolution.NEED_MORE_INFO;
                    sspHandler.getProcessRemarkService().addSourceCodeRemarkOnElement(
                        testSolution,
                        element,
                        SUSPECTED_FLASH_CONTENT_DETECTED_MESSAGE_CODE);
                }
            }
        }
        return testSolution;
    }

    /**
     * This methods checks the presence of the "swf" occurrence in the source
     * code.
     * If the occurrence is found, a process remark is created and the
     * testSolution is set to NEED_MORE_INFORMATION
     * @param sspHandler
     * @return
     */
    private TestSolution checkSourceCode (SSPHandler sspHandler) {
        TestSolution testSolution = TestSolution.PASSED;
        if (sspHandler.getSSP().getSource().toLowerCase().contains(SWF_EXT)){
            testSolution = TestSolution.NEED_MORE_INFO;
            sspHandler.getProcessRemarkService().addProcessRemark(
                    TestSolution.NEED_MORE_INFO,
                    SUSPECTED_FLASH_CONTENT_DETECTED_MESSAGE_CODE);
        }
        return testSolution;
    }

}
