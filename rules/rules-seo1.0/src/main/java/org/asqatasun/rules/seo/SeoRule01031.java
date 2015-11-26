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
package org.asqatasun.rules.seo;

import org.jsoup.nodes.Element;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import static org.asqatasun.rules.keystore.RemarkMessageStore.URL_EXCEEDS_LIMIT_MSG;

/**
 * Test whether the Url of the page exceeds 255 characters
 * 
 * @author jkowalczyk
 */
public class SeoRule01031 extends AbstractPageRuleMarkupImplementation {

    private static final int URL_MAX_LENGTH = 255;

    public SeoRule01031() {
        super();
    }

    @Override
    protected void select(final SSPHandler sspHandler) {
        // do nothing
    }
    
    @Override
    protected void check(
            final SSPHandler sspHandler, 
            final TestSolutionHandler testSolutionHandler) {

        if (sspHandler.getSSP().getURI().length() > URL_MAX_LENGTH) {
            testSolutionHandler.addTestSolution(TestSolution.FAILED);
            sspHandler.getProcessRemarkService().addProcessRemark(
                    TestSolution.FAILED, 
                    URL_EXCEEDS_LIMIT_MSG);
            return;
        }
        testSolutionHandler.addTestSolution(TestSolution.PASSED);
    }

    @Override
    public int getSelectionSize() {
        return 1;
    }
}
