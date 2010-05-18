/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb20;


import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.w3c.dom.Node;


/**
 * This rule tests if the hierarchy between titles ("h" tags) is pertinent
 * @author jkowalczyk
 */
public class Aw20Rule09012 extends AbstractPageRuleImplementation {

    public static final String XPATH_EXPR = "//BODY/descendant::*[contains(name(),'H')]";
    public static final int HEADER_INDEX_IN_TAG = 1;

    public Aw20Rule09012() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR);

        TestSolution testSolution = TestSolution.NOT_APPLICABLE;

        int previousNodeIndex = -1;
        int indexOfReference = -1;
        int currentHeaderNodeIndex;
        boolean selectionHasOneElement = false;

        for (Node node : sspHandler.getSelectedElementList()) {
            currentHeaderNodeIndex = getHeaderIndex(node.getNodeName());

            if (currentHeaderNodeIndex != -1) {
                //if the current node is not the first encountered node and the currentheaderNodeIndex is allowed
                if (previousNodeIndex != -1 ) {
                    if (currentHeaderNodeIndex - previousNodeIndex >= 2) {
                        testSolution = testSolution.FAILED;
                        addSourceCodeRemark(sspHandler,testSolution,node);
                    }
                    if (currentHeaderNodeIndex < indexOfReference) {
                        testSolution = testSolution.FAILED;
                        addSourceCodeRemark(sspHandler,testSolution,node);
                    }
                // the first encountered header
                } else {
                    indexOfReference = currentHeaderNodeIndex;
                }
                selectionHasOneElement = true;
                previousNodeIndex = currentHeaderNodeIndex;
            }
        }

        if (selectionHasOneElement && testSolution != TestSolution.FAILED) {
            testSolution = TestSolution.PASSED;
        }

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                sspHandler.getRemarkList());

        return processResult;

    }

    private int getHeaderIndex(String header) {
        try {
            int index = Integer.parseInt(header.substring(HEADER_INDEX_IN_TAG));
            if ( index>0 && index<=6) {
                return index;
            } else {
                return -1;
            }
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private void addSourceCodeRemark(
            SSPHandler sspHandler,
            TestSolution testSolution,
            Node node) {

        sspHandler.addSourceCodeRemark(
                testSolution,
                node,
                "HeaderTagNotHierarchicallyWelldefined",
                node.getNodeName());
    }

}
