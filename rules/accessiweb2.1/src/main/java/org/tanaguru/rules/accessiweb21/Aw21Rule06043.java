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
package org.tanaguru.rules.accessiweb21;

import java.util.*;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.RuleHelper;
import org.tanaguru.rules.accessiweb21.handler.link.AbstractPageRuleLinkThemeImplementation;
import org.tanaguru.rules.accessiweb21.handler.link.LinkRulesHandler;

/**
 * Does each identical link of type clickable area have the same purpose
 * and target?
 * 
 * @author jkowalczyk
 */
public class Aw21Rule06043 extends AbstractPageRuleLinkThemeImplementation{

    /**
     * xpath expression to get area tags without context and without title
     */
    private static final String XPATH_EXPR =
        LinkRulesHandler.AREA_SELECTOR +
        "(not(@title) and "+
        LinkRulesHandler.CLICKABLE_AREA +") and " +
        LinkRulesHandler.LINK_WITHOUT_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    /**
     * xpath expression to get area tags without context but with a title
     * attribute identical to the alt attribute
     */
    private static final String XPATH_EXPR2 =
        LinkRulesHandler.AREA_SELECTOR +
        "(@title and "+
        LinkRulesHandler.CLICKABLE_AREA +") and " +
        LinkRulesHandler.LINK_WITHOUT_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    /**
      * xpath expression to get area tags with a context and without title
     * attribute
     */
    private static final String XPATH_EXPR3 =
        LinkRulesHandler.AREA_SELECTOR +
        "(not(@title) and "+
        LinkRulesHandler.CLICKABLE_AREA + ") and " +
        LinkRulesHandler.LINK_WITH_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    /**
     * xpath expression to get area tags with a context and with a title
     * attribute
     */
    private static final String XPATH_EXPR4 =
        LinkRulesHandler.AREA_SELECTOR +
        "(@title and "+
        LinkRulesHandler.CLICKABLE_AREA + ") and " +
        LinkRulesHandler.LINK_WITH_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    public Aw21Rule06043() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        super.processImpl(sspHandler);
        
        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();

        resultSet.add(linkRulesHandler.searchIdenticalLinkWithDifferentTarget(
                XPATH_EXPR,
                TestSolution.FAILED,
                LinkRulesHandler.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                processRemarkList,
                false));

        resultSet.add(linkRulesHandler.searchIdenticalLinkWithDifferentTarget(
                XPATH_EXPR2,
                TestSolution.FAILED,
                LinkRulesHandler.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                processRemarkList,
                true));

        resultSet.add(linkRulesHandler.searchIdenticalLinkWithDifferentTarget(
                XPATH_EXPR3,
                TestSolution.NEED_MORE_INFO,
                LinkRulesHandler.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                processRemarkList,
                false));

        resultSet.add(linkRulesHandler.searchIdenticalLinkWithDifferentTarget(
                XPATH_EXPR4,
                TestSolution.NEED_MORE_INFO,
                LinkRulesHandler.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                processRemarkList,
                true));

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                RuleHelper.synthesizeTestSolutionCollection(resultSet),
                processRemarkList);

        cleanUpRule();
        return processResult;
    }

}