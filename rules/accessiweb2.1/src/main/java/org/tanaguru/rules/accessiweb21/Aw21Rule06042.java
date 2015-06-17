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
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.RuleHelper;
import org.tanaguru.rules.accessiweb21.handler.link.AbstractPageRuleLinkThemeImplementation;
import org.tanaguru.rules.accessiweb21.handler.link.LinkRulesHandler;
import org.w3c.dom.Node;

/**
 * Does each identical link of type image have the same purpose and target?
 *
 * @author jkowalczyk
 */
public class Aw21Rule06042 extends AbstractPageRuleLinkThemeImplementation{

    /**
     * xpath expression to get "a" tags with "img" child node,
     * without context and without title attribute
     */
    private static final String XPATH_EXPR =
        "//A[" +
        "(not(@title) and "+
        LinkRulesHandler.IMAGE_LINK_IMG_NODE +") and " +
        LinkRulesHandler.LINK_WITHOUT_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    /**
     * xpath expression to get "a" tags with "object" child node,
     * without context and without title attribute
     */
    private static final String XPATH_EXPR2 =
        "//A[" +
        "(not(@title) and "+
        LinkRulesHandler.IMAGE_LINK_OBJECT_NODE +") and " +
        LinkRulesHandler.LINK_WITHOUT_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    /**
     * xpath expression to get "a" tags with "img" child nodes, without context
     * and with a title attribute identical to the text link
     */
    private static final String XPATH_EXPR3 =
        "//A[" +
        "(@title and "+
        LinkRulesHandler.IMAGE_LINK_IMG_NODE +") and " +
        LinkRulesHandler.LINK_WITHOUT_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    /**
     * xpath expression to get "a" tags with "object" child nodes, without context
     * and with a title attribute identical to the text link
     */
    private static final String XPATH_EXPR4 =
        "//A[" +
        "(@title and "+
        LinkRulesHandler.IMAGE_LINK_OBJECT_NODE +") and " +
        LinkRulesHandler.LINK_WITHOUT_CONTEXT +
        "]";

    /**
     * xpath expression to get "a" tags with "img" child nodes and with a context
     */
    private static final String XPATH_EXPR5 =
        "//A[" +
         "not(@title) and "+
        LinkRulesHandler.IMAGE_LINK_IMG_NODE + " and " +
        LinkRulesHandler.LINK_WITH_CONTEXT +
        "]";

    /**
     * xpath expression to get "a" tags with "object" child nodes and with a context
     */
    private static final String XPATH_EXPR6 =
        "//A[" +
         "not(@title) and "+
        LinkRulesHandler.IMAGE_LINK_OBJECT_NODE + " and " +
        LinkRulesHandler.LINK_WITH_CONTEXT +
        "]";

    /**
     * xpath expression to get "a" tags with "img" child nodes, a context and
     * a title attribute
     */
    private static final String XPATH_EXPR7 =
        "//A[" +
         "@title and "+
        LinkRulesHandler.IMAGE_LINK_IMG_NODE + " and " +
        LinkRulesHandler.LINK_WITH_CONTEXT +
        "]";

    /**
     * xpath expression to get "a" tags with "object" child nodes,a context and
     * a title attribute
     */
    private static final String XPATH_EXPR8 =
        "//A[" +
         "@title and "+
        LinkRulesHandler.IMAGE_LINK_OBJECT_NODE + " and " +
        LinkRulesHandler.LINK_WITH_CONTEXT +
        "]";

    public Aw21Rule06042() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        super.processImpl(sspHandler);
        
        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();
        List<Node> nodeList = new ArrayList<Node>();

        nodeList.addAll(sspHandler.beginSelection().
                domXPathSelectNodeSet(XPATH_EXPR).getSelectedElementList());

        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR2);
        nodeList.addAll(linkRulesHandler.removeTagsWithNotEmptyContent(
                sspHandler.getSelectedElementList()));
        sspHandler.setSelectedElementList(nodeList);
        resultSet.add(linkRulesHandler.searchIdenticalLinkWithDifferentTarget(
                null,
                TestSolution.FAILED,
                LinkRulesHandler.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                processRemarkList,
                false));

        nodeList.clear();
        nodeList.addAll(sspHandler.beginSelection().
                domXPathSelectNodeSet(XPATH_EXPR3).getSelectedElementList());

        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR4);
        nodeList.addAll(linkRulesHandler.removeTagsWithNotEmptyContent(
                sspHandler.getSelectedElementList()));
        sspHandler.setSelectedElementList(nodeList);

        resultSet.add(linkRulesHandler.searchIdenticalLinkWithDifferentTarget(
                null,
                TestSolution.FAILED,
                LinkRulesHandler.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                processRemarkList,
                true));

        nodeList.clear();
        nodeList.addAll(sspHandler.beginSelection().
                domXPathSelectNodeSet(XPATH_EXPR5).getSelectedElementList());

        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR6);
        nodeList.addAll(linkRulesHandler.removeTagsWithNotEmptyContent(
                sspHandler.getSelectedElementList()));
        sspHandler.setSelectedElementList(nodeList);
        resultSet.add(linkRulesHandler.searchIdenticalLinkWithDifferentTarget(
                null,
                TestSolution.NEED_MORE_INFO,
                LinkRulesHandler.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                processRemarkList,
                false));

        nodeList.clear();
        nodeList.addAll(sspHandler.beginSelection().
                domXPathSelectNodeSet(XPATH_EXPR7).getSelectedElementList());

        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR8);
        nodeList.addAll(linkRulesHandler.removeTagsWithNotEmptyContent(
                sspHandler.getSelectedElementList()));
        sspHandler.setSelectedElementList(nodeList);
        resultSet.add(linkRulesHandler.searchIdenticalLinkWithDifferentTarget(
                null,
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