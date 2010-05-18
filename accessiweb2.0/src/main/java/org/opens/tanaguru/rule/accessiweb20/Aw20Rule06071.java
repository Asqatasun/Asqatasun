/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb20;

import java.util.ArrayList;
import java.util.List;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This rule tests if all <a> tags have a text content
 * @author jkowalczyk
 */
public class Aw20Rule06071 extends AbstractPageRuleImplementation {

    public static final String XPATH_EXPR = "//A";

    public static final String NAME_ATTRIBUTE = "name";

    private SSPHandler sspHandler;

    public Aw20Rule06071() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        this.sspHandler = sspHandler;
        List<Node> nodeList =
                sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR).
                getSelectedElementList();

        List<Node> suspectedNodeList = new ArrayList<Node>();

        TestSolution checkResult = TestSolution.NOT_APPLICABLE;

        for(Node node : nodeList) {
            checkResult = TestSolution.PASSED;
            if (doesNodeHaveATextChildNode(node)) {
                if (node.getTextContent() == null) {
                    suspectedNodeList.add(node);
                } else if (node.getTextContent().trim().isEmpty()) {
                    suspectedNodeList.add(node);
                }
            } else {
                checkResult = verifyChildNode(node);
            }
        }

        checkResult = verifySuspectedChildNode(suspectedNodeList, checkResult);

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                sspHandler.getRemarkList());

        return result;
    }

    private TestSolution verifyChildNode(Node node){
        return TestSolution.PASSED;
    }

    /**
     * We check here that the <a> node is an anchor.
     * If not the node is invalid
     * @param node
     * @return
     */
    private TestSolution verifySuspectedChildNode(List<Node> suspectedNodeList,
            TestSolution checkResult){
        TestSolution myCheckResult = checkResult;
        if (suspectedNodeList.size() != 0) {
            for (Node node : suspectedNodeList) {
                NamedNodeMap attributesMap = node.getAttributes();
                if (attributesMap.getLength() != 1) {
                    myCheckResult = TestSolution.FAILED;
                    sspHandler.addSourceCodeRemark(
                            myCheckResult,
                            node,
                            "LinkContentMissing",
                            node.getNodeName());
                } else {
                    if (!attributesMap.item(0).getNodeName().
                            equalsIgnoreCase(NAME_ATTRIBUTE)) {

                    myCheckResult = TestSolution.FAILED;
                    sspHandler.addSourceCodeRemark(
                            checkResult,
                            node,
                            "LinkContentMissing",
                            node.getNodeName());
                    }
                }
            }
        }
        return myCheckResult;
    }

    /**
     * A blank space or a return character is seen as a child node (names #text)
     * We check here if a node has child nodes that are not text-typed
     * 
     * @param node
     * @return
     */
    private boolean doesNodeHaveATextChildNode(Node node){
        if (node.hasChildNodes()) {
            NodeList attList = node.getChildNodes();
            if (attList.getLength() == 1 &&
                    attList.item(0).getNodeName().equalsIgnoreCase("#text")) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
