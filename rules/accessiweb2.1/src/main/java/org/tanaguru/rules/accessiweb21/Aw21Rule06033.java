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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.asqatasun.entity.audit.ProcessRemark;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.RuleHelper;
import org.asqatasun.rules.accessiweb21.handler.link.AbstractPageRuleLinkThemeImplementation;
import org.asqatasun.rules.accessiweb21.handler.link.LinkRulesHandler;

/**
 * Is each link text such as a clickable area (content of the alt attribute
 * of an area tag) explicit out of context (except in special cases)?
 * 
 * @author jkowalczyk
 */
public class Aw21Rule06033 extends AbstractPageRuleLinkThemeImplementation{

    /**
     * xpath expression to get area tags without context and without title
     */
    private static final String XPATH_EXPR =
        LinkRulesHandler.AREA_SELECTOR +
        LinkRulesHandler.CLICKABLE_AREA +
        LinkRulesHandler.END_BRACKET;


    public Aw21Rule06033() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        super.processImpl(sspHandler);
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();

        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR);
        resultSet.add(linkRulesHandler.checkContextPertinence(
                XPATH_EXPR,
                TestSolution.FAILED,
                LinkRulesHandler.UNEXPLICIT_LINK_OUT_OF_CONTEXT,
                processRemarkList));

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                RuleHelper.synthesizeTestSolutionCollection(resultSet),
                processRemarkList);

        cleanUpRule();
        return processResult;
    }

}