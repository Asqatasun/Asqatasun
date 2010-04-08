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
import org.w3c.dom.Node;

/**
 *
 * @author jkowalczyk
 */
public class Aw20Rule08051 extends AbstractPageRuleImplementation {

    private static final String HEAD_TAG = "HEAD";
    private static final String TTTLE_TAG = "TITLE";

    /**
     *
     */
    public Aw20Rule08051() {
        super();
    }

    /**
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();
        sspHandler.beginSelection().selectDocumentNodes(HEAD_TAG).
                selectChildNodes(TTTLE_TAG);

        TestSolution testSolution = null;

        if (sspHandler.getSelectedElementList() != null
                && sspHandler.getSelectedElementList().size() == 1) {
            testSolution = TestSolution.PASSED;
        } else {
            testSolution = TestSolution.FAILED;
            processRemarkList.add(processRemarkFactory.create(TestSolution.FAILED, "TitleTagMissing"));
        }

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                processRemarkList);

        return processResult;
    }
}
