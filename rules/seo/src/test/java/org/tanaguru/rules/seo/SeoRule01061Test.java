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

import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.subject.Page;
import org.tanaguru.entity.subject.Site;
import org.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule01061Test extends SeoRuleImplementationTestCase {
    
    public SeoRule01061Test(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.seo.SeoRule01061");
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected void setUpWebResourceMap() {
        Site site = getWebResourceFactory().createSite("http://site-robot.tgqa.org");
        getWebResourceMap().put("Seo.Test.1.6.1-2Failed-01", site);
        Page page = getWebResourceFactory().createPage("http://site-robot.tgqa.org");
        site.addChild(page);
        
        site = getWebResourceFactory().createSite("http://site.tgqa.org");
        getWebResourceMap().put("Seo.Test.1.6.1-2Failed-02", site);
        page = getWebResourceFactory().createPage("http://site.tgqa.org");
        site.addChild(page);
        
        site = getWebResourceFactory().createSite("http://site-sitemap.tgqa.org");
        getWebResourceMap().put("Seo.Test.1.6.1-1Passed-01", site);
        page = getWebResourceFactory().createPage("http://site-sitemap.tgqa.org");
        site.addChild(page);
    }
    
    @Override
    protected void setProcess() {
        process("Seo.Test.1.6.1-1Passed-01");
        process("Seo.Test.1.6.1-2Failed-01");
        process("Seo.Test.1.6.1-2Failed-02");
    }

    @Override
    protected void setConsolidate() {
        ProcessResult processResult =
                consolidate("Seo.Test.1.6.1-1Passed-01");
        assertEquals(TestSolution.PASSED, processResult.getValue());

        processResult = consolidate("Seo.Test.1.6.1-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        
        processResult = consolidate("Seo.Test.1.6.1-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());
    }
    
}
