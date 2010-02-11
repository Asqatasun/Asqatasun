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
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.w3c.dom.Node;

/**
 *
 * @author jkowalczyk
 */
public class Rule10011 extends AbstractPageRuleImplementation {

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        Nomenclature deprecatedHtmlTags =
                nomenclatureLoaderService.loadByCode("DeprecatedHtmlTags");

        sspHandler.beginSelection().selectDocumentNodes(deprecatedHtmlTags.getValueList());

        TestSolution testSolution = TestSolution.PASSED;

        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();
        for (Node node : sspHandler.getSelectedElementList()) {
            testSolution = TestSolution.FAILED;
            processRemarkList.add(processRemarkFactory.create(testSolution, "DeprecatedTagFound"));

        }

        ProcessResult processResult =
                definiteResultFactory.create(test, sspHandler.getPage(), testSolution, processRemarkList);

        return processResult;
    }
}
