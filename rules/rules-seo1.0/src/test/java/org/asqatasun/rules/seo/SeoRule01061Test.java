/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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

import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.subject.Page;
import org.asqatasun.entity.subject.Site;
import org.asqatasun.rules.seo.test.SeoRuleImplementationTestCase;

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
        setRuleImplementationClassName("org.asqatasun.rules.seo.SeoRule01061");
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
        Site site = getWebResourceFactory().createSite("http://site-robot.asqatasun.ovh");
        getWebResourceMap().put("Seo.Test.1.6.1-2Failed-01", site);
        Page page = getWebResourceFactory().createPage("http://site-robot.asqatasun.ovh");
        site.addChild(page);
        
        site = getWebResourceFactory().createSite("http://site.asqatasun.ovh");
        getWebResourceMap().put("Seo.Test.1.6.1-2Failed-02", site);
        page = getWebResourceFactory().createPage("http://site.asqatasun.ovh");
        site.addChild(page);
        
        site = getWebResourceFactory().createSite("http://site-sitemap.asqatasun.ovh");
        getWebResourceMap().put("Seo.Test.1.6.1-1Passed-01", site);
        page = getWebResourceFactory().createPage("http://site-sitemap.asqatasun.ovh");
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
