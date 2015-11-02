/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.rules.seo;

import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementChecker;
import org.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.tanaguru.rules.elementselector.ElementSelector;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.FLASH_CONTENT_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.HtmlElementStore.SCRIPT_ELEMENT;
import static org.tanaguru.rules.keystore.RemarkMessageStore.FLASH_CONTENT_DETECTED_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.SUSPECTED_FLASH_CONTENT_DETECTED_MSG;

/**
 * Detect the presence of flash content in the page
 * 
 * @author jkowalczyk
 */
public class SeoRule08011 extends AbstractPageRuleMarkupImplementation {

    private static final String SWF_EXT = "swf";
    private ElementHandler decidableElements = new ElementHandlerImpl();
    private ElementHandler notDecidableElements = new ElementHandlerImpl();
    

    public SeoRule08011() {
        super();
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        ElementSelector es = new SimpleElementSelector(FLASH_CONTENT_CSS_LIKE_QUERY);
	es.selectElements(sspHandler, decidableElements);
        es = new SimpleElementSelector(SCRIPT_ELEMENT);
	es.selectElements(sspHandler, notDecidableElements);
	Iterator<Element> iter = notDecidableElements.get().iterator();
        while (iter.hasNext()) {
            Element script = iter.next();
            if (!StringUtils.contains(script.html(), SWF_EXT)) {
		iter.remove();
	    }
        }
    }

    @Override
    protected void check(SSPHandler sspHandler, 
                         TestSolutionHandler testSolutionHandler) {
        ElementChecker ec = new ElementPresenceChecker(
				TestSolution.FAILED, 
				TestSolution.PASSED, 
				FLASH_CONTENT_DETECTED_MSG, 
				null);
	ec.check(sspHandler, decidableElements, testSolutionHandler);
	ec = new ElementPresenceChecker(
				TestSolution.NEED_MORE_INFO,
				TestSolution.PASSED, 
				SUSPECTED_FLASH_CONTENT_DETECTED_MSG, 
				null);
	ec.check(sspHandler, notDecidableElements, testSolutionHandler);
	if (testSolutionHandler.getTestSolution().equals(TestSolution.PASSED)) {
		testSolutionHandler.addTestSolution(checkSourceCode(sspHandler));
	}
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
        if (sspHandler.getSSP().getAdaptedContent().toLowerCase().contains(SWF_EXT)){
            testSolution = TestSolution.NEED_MORE_INFO;
            sspHandler.getProcessRemarkService().addProcessRemark(
                    TestSolution.NEED_MORE_INFO,
                    SUSPECTED_FLASH_CONTENT_DETECTED_MSG);
        }
        return testSolution;
    }

  @Override
  public int getSelectionSize() {
      return decidableElements.size() + notDecidableElements.size();
  }

}