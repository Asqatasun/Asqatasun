/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb20;

import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 *
 * @author jkowalczyk
 */
public class Aw2Rule08051 extends AbstractPageRuleImplementation {

    private static final String HEAD_TAG = "HEAD";
    private static final String TTTLE_TAG = "TITLE";

    /**
     *
     */
    public Aw2Rule08051() {
        super();
    }

    /**
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        sspHandler.beginSelection().selectDocumentNodes(HEAD_TAG).
                selectChildNodes(TTTLE_TAG);

        TestSolution testSolution = null;

        if (sspHandler.getSelectedElementList() != null
                && sspHandler.getSelectedElementList().size() == 1) {
            testSolution = TestSolution.PASSED;
        } else {
            sspHandler.beginSelection().selectDocumentNodes(HEAD_TAG.toLowerCase()).
                    selectChildNodes(TTTLE_TAG.toLowerCase());
            if (sspHandler.getSelectedElementList() != null
                && sspHandler.getSelectedElementList().size() == 1) {
                testSolution = TestSolution.PASSED;
            } else {
                testSolution = TestSolution.FAILED;
            }
        }

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                null);

        return processResult;
    }
}
