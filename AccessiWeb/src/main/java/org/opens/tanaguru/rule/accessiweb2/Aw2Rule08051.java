/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb2;

import java.util.ArrayList;
import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 *
 * @author jkowalczyk
 */
public class Aw2Rule08051 extends AbstractPageRuleImplementation {

    private static final String HEAD_TAG = "head";
    private static final String TTTLE_TAG = "title";

    /**
     *
     */
    public Aw2Rule08051(){
        super();
    }

    /**
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        sspHandler.beginSelection().selectDocumentNodes(HEAD_TAG);

        TestSolution testSolution = sspHandler.checkChildNodeExists(TTTLE_TAG);

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                null);

        return processResult;
    }
}
