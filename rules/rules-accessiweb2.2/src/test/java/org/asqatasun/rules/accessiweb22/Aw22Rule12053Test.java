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
package org.asqatasun.rules.accessiweb22;

import org.asqatasun.entity.audit.IndefiniteResult;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.subject.Page;
import org.asqatasun.entity.subject.Site;
import org.asqatasun.rules.accessiweb22.test.Aw22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 12.5.3 of the referential Accessiweb 2.2.
 *
 * @author jkowalczyk
 */
public class Aw22Rule12053Test extends Aw22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Aw22Rule12053Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.asqatasun.rules.accessiweb22.Aw22Rule12053");
    }

   @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.12.5.3-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule12053/AW22.Test.12.5.3-3NMI-01.html"));
        
        Site site = getWebResourceFactory().createSite("file:Site-NotTested");
        getWebResourceMap().put("AW22.Test.12.5.3-5NT-01", site);

        Page page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "accessiweb22/Aw22Rule12053/AW22.Test.12.5.3-3NMI-01.html");
        site.addChild(page);
        getWebResourceMap().put("AW22.Test.12.5.3-5NT-01-page1",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "accessiweb22/Aw22Rule12053/AW22.Test.12.5.3-3NMI-01.html");
        site.addChild(page);
        getWebResourceMap().put("AW22.Test.12.5.3-5NT-01-page1",page);
    }

    @Override
    protected void setProcess() {
        ProcessResult pr = processPageTest("AW22.Test.12.5.3-4NA-01");
        assertTrue(pr instanceof IndefiniteResult);
        assertEquals(getWebResourceMap().get("AW22.Test.12.5.3-4NA-01"),pr.getSubject());
        assertEquals("mock-result", pr.getValue());
        
        process("AW22.Test.12.5.3-5NT-01");
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.12.5.3-4NA-01").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW22.Test.12.5.3-5NT-01").getValue());
    }

}
