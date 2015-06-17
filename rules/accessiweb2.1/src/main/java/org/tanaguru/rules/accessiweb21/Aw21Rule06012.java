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
import org.w3c.dom.Node;

/**
 * For each image link (content of the alt attribute or text between
 * &lt;object&gt; and &lt;/object&gt;), does the link context allow to
 * understand the link purpose and target if necessary (except in special cases)?
 *
 * @author jkowalczyk
 */
public class Aw21Rule06012 extends AbstractPageRuleLinkThemeImplementation{

    /**
     * xpath expression to get "a" tags with "img" child node,
     * without context and without title attribute
     */
    private static final String XPATH_EXPR =
        LinkRulesHandler.A_SELECTOR +
        "(not(@title) and "+
        LinkRulesHandler.IMAGE_LINK_IMG_NODE +") and " +
        LinkRulesHandler.LINK_WITHOUT_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    /**
     * xpath expression to get "a" tags with "object" child node,
     * without context and without title attribute
     */
    private static final String XPATH_EXPR2 =
        LinkRulesHandler.A_SELECTOR +
        "(not(@title) and "+
        LinkRulesHandler.IMAGE_LINK_OBJECT_NODE +") and " +
        LinkRulesHandler.LINK_WITHOUT_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    /**
     * xpath expression to get "a" tags with "img" child nodes, without context
     * and with a title attribute identical to the text link
     */
    private static final String XPATH_EXPR3 =
        LinkRulesHandler.A_SELECTOR +
        "(@title and "+
        LinkRulesHandler.IMAGE_LINK_IMG_NODE +") and " +
        LinkRulesHandler.LINK_WITHOUT_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    /**
     * xpath expression to get "a" tags with "object" child nodes, without context
     * and with a title attribute identical to the text link
     */
    private static final String XPATH_EXPR4 =
        LinkRulesHandler.A_SELECTOR +
        "(@title and "+
        LinkRulesHandler.IMAGE_LINK_OBJECT_NODE +") and " +
        LinkRulesHandler.LINK_WITHOUT_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    /**
     * xpath expression to get "a" tags with "img" child nodes and with a context
     */
    private static final String XPATH_EXPR5 =
        LinkRulesHandler.A_SELECTOR +
        LinkRulesHandler.IMAGE_LINK_IMG_NODE + " and " +
        LinkRulesHandler.LINK_WITH_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    /**
     * xpath expression to get "a" tags with "object" child nodes and with a context
     */
    public static final String XPATH_EXPR6 =
        LinkRulesHandler.A_SELECTOR +
        LinkRulesHandler.IMAGE_LINK_OBJECT_NODE + " and " +
        LinkRulesHandler.LINK_WITH_CONTEXT +
        LinkRulesHandler.END_BRACKET;

    public Aw21Rule06012() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        super.processImpl(sspHandler);
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();

        List<Node> imgNodeWithContext = new ArrayList<Node>();
        List<Node> objectNodeWithContext = new ArrayList<Node>();
        List<Node> imgNodeWithoutContextAndWithTitle = new ArrayList<Node>();
        List<Node> objectNodeWithoutContextAndWithTitle = new ArrayList<Node>();

        resultSet.add(linkRulesHandler.checkContextPertinence(
                XPATH_EXPR,
                TestSolution.FAILED,
                LinkRulesHandler.UNEXPLICIT_CONTEXT,
                processRemarkList));

        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR2);
        sspHandler.setSelectedElementList(linkRulesHandler.removeTagsWithNotEmptyContent(
                sspHandler.getSelectedElementList()));
        resultSet.add(linkRulesHandler.checkContextPertinence(
                null,
                TestSolution.FAILED,
                LinkRulesHandler.UNEXPLICIT_CONTEXT,
                processRemarkList));

        dispatchNodeWithContentEqualsToTitleAttribute(
                sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR3).getSelectedElementList(),
                imgNodeWithoutContextAndWithTitle,
                imgNodeWithContext);
        sspHandler.setSelectedElementList(imgNodeWithoutContextAndWithTitle);
        resultSet.add(linkRulesHandler.checkContextPertinence(
                null,
                TestSolution.FAILED,
                LinkRulesHandler.UNEXPLICIT_CONTEXT,
                processRemarkList));

        dispatchNodeWithContentEqualsToTitleAttribute(
                linkRulesHandler.removeTagsWithNotEmptyContent(sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR4).getSelectedElementList()),
                objectNodeWithoutContextAndWithTitle,
                objectNodeWithContext);
        sspHandler.setSelectedElementList(objectNodeWithoutContextAndWithTitle);
        resultSet.add(linkRulesHandler.checkContextPertinence(
                null,
                TestSolution.FAILED,
                LinkRulesHandler.UNEXPLICIT_CONTEXT,
                processRemarkList));

        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR5);
        sspHandler.getSelectedElementList().addAll(imgNodeWithContext);
        resultSet.add(linkRulesHandler.checkContextPertinence(
                null,
                TestSolution.NEED_MORE_INFO,
                LinkRulesHandler.SUSPECTED_UNEXPLICIT_CONTEXT,
                processRemarkList));

        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR6);
        sspHandler.setSelectedElementList(linkRulesHandler.removeTagsWithNotEmptyContent(
                sspHandler.getSelectedElementList()));
        sspHandler.getSelectedElementList().addAll(objectNodeWithContext);
        resultSet.add(linkRulesHandler.checkContextPertinence(
                null,
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

    /**
     * This method dispatches the encountered tags with a title attribute.
     * If the link content is equal to the title attribute, the tag is regarded
     * as a link without context and we check whether the link content is
     * blacklisted or only contains non alphanumeric character.
     * If not, the tag is regarded as a link with context.
     * @param selectedNodeList
     * @param equalList
     * @param notEqualList
     */
    private void dispatchNodeWithContentEqualsToTitleAttribute(
            List<Node> selectedNodeList,
            List<Node> equalList,
            List<Node> notEqualList){
        for (Node node : selectedNodeList){
            if (node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.TITLE_ATTR).getTextContent().
                    equalsIgnoreCase(linkRulesHandler.buildTextContentFromNodeElements(node))){
                equalList.add(node);
            } else {
                notEqualList.add(node);
            }
        }
    }

}