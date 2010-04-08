/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb20;


import java.util.ArrayList;
import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;


/**
 * This rule tests if each page has at least one <h1> tag
 * @author jkowalczyk
 */
public class Aw20Rule09011 extends AbstractPageRuleImplementation {

    public static final String H1_TAG = "H1";
    public static final String BODY_TAG = "BODY";

    public Aw20Rule09011() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.beginSelection().selectDocumentNodes(BODY_TAG).selectChildNodesRecursively(H1_TAG);
//                selectChildNodes(H1_TAG);
        
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();
        TestSolution testSolution = TestSolution.PASSED;

        if (sspHandler.getSelectedElementList().isEmpty()) {
            testSolution = TestSolution.FAILED;
            processRemarkList.add(processRemarkFactory.create(TestSolution.FAILED, "H1TagMissing"));
        }

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                processRemarkList);

        return processResult;

    }


}
