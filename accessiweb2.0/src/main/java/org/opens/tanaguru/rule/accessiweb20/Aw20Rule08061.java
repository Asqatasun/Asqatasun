/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb20;

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

    private static final String HEAD_TAG = "head";
    private static final String TTTLE_TAG = "title";

    /**
     *
     */
    public Aw20Rule08061(){
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

        System.out.println("sspHandler.getSelectedElementList().isEmpty()" + sspHandler.getSelectedElementList().isEmpty());

        Nomenclature unexplicitePageTitle = nomenclatureLoaderService.
                loadByCode("UnexplicitPageTitle");
        
        TestSolution testSolution = sspHandler.
                checkTextContentValue(unexplicitePageTitle.getValueList(), null);

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                null);

        return processResult;
    }

}
