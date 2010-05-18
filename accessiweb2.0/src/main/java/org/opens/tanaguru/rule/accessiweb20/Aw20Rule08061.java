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
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 *
 * @author jkowalczyk
 */
public class Aw20Rule08061 extends AbstractPageRuleImplementation {

    private static final String HEAD_TAG = "HEAD";
    private static final String TTTLE_TAG = "TITLE";

    /**
     *
     */
    public Aw20Rule08061() {
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

        Nomenclature unexplicitePageTitle =
                nomenclatureLoaderService.loadByCode("UnexplicitPageTitle");

        TestSolution testSolution =
                sspHandler.checkTextContentValue(unexplicitePageTitle.getValueList(), null);

        if (testSolution == TestSolution.FAILED) {
            processRemarkList.add(processRemarkFactory.create(TestSolution.FAILED, "TitleTagNotRevelevant"));
        }

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                processRemarkList);

        return processResult;
    }
}
