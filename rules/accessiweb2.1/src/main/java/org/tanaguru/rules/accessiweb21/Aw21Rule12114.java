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

package org.asqatasun.rules.accessiweb21;

import java.util.ArrayList;
import java.util.Collection;
import org.asqatasun.entity.audit.DefiniteResult;
import org.asqatasun.entity.audit.ProcessRemark;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleImplementation;

/**
 * Implementation of the rule 12.11.4 of the referential Accessiweb 2.1.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw21-rule-12-11-4">the rule 12.11.4 design page.</a>
 * @see <a href="http://www.braillenet.org/accessibilite/referentiel-aw21-en/index.php#test-12-11-4"> 12.11.4 rule specification</a>
 *
 * @author jkowalczyk
 */

public class Aw21Rule12114 extends AbstractPageRuleImplementation {

    /**
     * Default constructor
     */
    public Aw21Rule12114 () {
        super();
    }

    /**
     * Concrete implementation of the 12.11.4 rule.
     * @param sspHandler
     * @return
     *      the processResult of the test
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        TestSolution testSolution = TestSolution.NOT_TESTED;
        Collection<ProcessRemark> remarkList = new ArrayList<ProcessRemark>();
        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                testSolution,
                remarkList);
        
        return result;
    }

}