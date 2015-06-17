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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.RuleHelper;
import org.tanaguru.rules.accessiweb21.handler.link.AbstractPageRuleLinkThemeImplementation;
import org.tanaguru.rules.accessiweb21.handler.link.LinkRulesHandler;

/**
 * For each link such as clickable area (content of the alt attribute of an
 * area tag), does the link context allow to understand the link purpose and
 * target if necessary (except in special cases)?
 * 
 * @author jkowalczyk
 */
public class Aw21Rule06013 extends AbstractPageRuleLinkThemeImplementation{

    /**
     * xpath expression to get area tags without context and without title
     */
    private static final String XPATH_EXPR =
        LinkRulesHandler.AREA_SELECTOR +
        "(not(@title) and "+
        LinkRulesHandler.CLICKABLE_AREA +") and " +
        LinkRulesHandler.LINK_WITHOUT_CONTEXT +
        "]";

    /**
     * xpath expression to get area tags without context but with a title
     * attribute identical to the alt attribute
     */
    private static final String XPATH_EXPR2 =
        LinkRulesHandler.AREA_SELECTOR +
        "(normalize-space(@title)=normalize-space(@alt) and "+
        LinkRulesHandler.CLICKABLE_AREA +") and " +
        LinkRulesHandler.LINK_WITHOUT_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    /**
     * xpath expression to get area tags without context and with a title
     * attribute different from the alt attribute
     */
    private static final String XPATH_EXPR3 =
        LinkRulesHandler.AREA_SELECTOR +
        "(@title and normalize-space(@title)!=normalize-space(@alt) and "+
        LinkRulesHandler.CLICKABLE_AREA +") and " +
        LinkRulesHandler.LINK_WITHOUT_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    /**
     * xpath expression to get area tags with a context
     */
    private static final String XPATH_EXPR4 =
        LinkRulesHandler.AREA_SELECTOR +
        "("+LinkRulesHandler.CLICKABLE_AREA + ") and " +
        LinkRulesHandler.LINK_WITH_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    public Aw21Rule06013() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        super.processImpl(sspHandler);
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();

        resultSet.add(linkRulesHandler.checkContextPertinence(
                XPATH_EXPR,
                TestSolution.FAILED,
                LinkRulesHandler.UNEXPLICIT_CONTEXT,
                processRemarkList));

        resultSet.add(linkRulesHandler.checkContextPertinence(
                XPATH_EXPR2,
                TestSolution.FAILED,
                LinkRulesHandler.UNEXPLICIT_CONTEXT,
                processRemarkList));

        resultSet.add(linkRulesHandler.checkContextPertinence(
                XPATH_EXPR3,
                TestSolution.NEED_MORE_INFO,
                LinkRulesHandler.SUSPECTED_UNEXPLICIT_CONTEXT,
                processRemarkList));

        resultSet.add(linkRulesHandler.checkContextPertinence(
                XPATH_EXPR4,
                TestSolution.NEED_MORE_INFO,
                LinkRulesHandler.SUSPECTED_UNEXPLICIT_CONTEXT,
                processRemarkList));

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                RuleHelper.synthesizeTestSolutionCollection(resultSet),
                processRemarkList);
        processResult.setElementCounter(linkRulesHandler.getElementCounter());

        cleanUpRule();
        return processResult;
    }

}