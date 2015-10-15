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
package org.tanaguru.rules.seo;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.lang.StringUtils;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.service.ProcessRemarkService;

/**
 * Are Rewrite Rules set up ?
 *
 * @author alingua
 */
public class SeoRule01071 extends AbstractPageRuleImplementation {

    public SeoRule01071() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        ProcessRemarkService processRemarkService =
                sspHandler.getProcessRemarkService();
        processRemarkService.resetService();
        TestSolution testSolution = TestSolution.PASSED;

        try {
            URL url = new URL(sspHandler.getSSP().getURI());
            if (StringUtils.isNotBlank(url.getQuery())) {
                testSolution = TestSolution.FAILED;
            }
        } catch (MalformedURLException ex) {
            testSolution = TestSolution.NOT_APPLICABLE;
        }

        if (testSolution.equals(TestSolution.FAILED)) {
            processRemarkService.addProcessRemark(TestSolution.FAILED,
                    RemarkMessageStore.URL_PARAMETERS_DETECTED);
        }

        return processResultDataService.getDefiniteResult(
                test,
                sspHandler.getPage(),
                testSolution,
                processRemarkService.getRemarkList());
    }
}
