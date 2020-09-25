/**
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
package org.asqatasun.rules.rgaa40;

import org.asqatasun.entity.audit.IndefiniteResult;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.subject.Page;
import org.asqatasun.entity.subject.Site;
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;

/**
 * Unit test class for implementation of rule 12.5.2 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/documentation/en/90_Rules/rgaa4.0/12.Navigation/Rule-12-5-2.md">rule 12.5.2 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-12-5-2">12.5.2 rule specification</a>
 */
public class Rgaa40Rule120502Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule120502Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa40.Rgaa40Rule120502");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rgaa40.Test.12.05.02-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa40/Rgaa40Rule120502/Rgaa40.Test.12.05.02-3NMI-01.html"));

        Site site = getWebResourceFactory().createSite("file:Site-NotTested");
        getWebResourceMap().put("Rgaa40.Test.12.05.02-5NT-01", site);

        Page page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "rgaa40/Rgaa40Rule120502/Rgaa40.Test.12.05.02-3NMI-01.html");
        site.addChild(page);
        getWebResourceMap().put("Rgaa40.Test.12.05.02-5NT-01-page1",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "rgaa40/Rgaa40Rule120502/Rgaa40.Test.12.05.02-3NMI-01.html");
        site.addChild(page);
        getWebResourceMap().put("Rgaa40.Test.12.05.02-5NT-01-page1",page);
    }

    @Override
    protected void setProcess() {
        ProcessResult pr = processPageTest("Rgaa40.Test.12.05.02-4NA-01");
        assertTrue(pr instanceof IndefiniteResult);
        assertEquals(getWebResourceMap().get("Rgaa40.Test.12.05.02-4NA-01"),pr.getSubject());
        assertEquals("mock-result", pr.getValue());
        
        process("Rgaa40.Test.12.05.02-5NT-01");

    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa40.Test.12.05.02-4NA-01").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa40.Test.12.05.02-5NT-01").getValue());
    }

}
