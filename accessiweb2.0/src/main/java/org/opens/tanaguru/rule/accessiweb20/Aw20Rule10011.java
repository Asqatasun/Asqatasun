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
import org.w3c.dom.Node;

/**
 * This rule tests if forbidden representation tags are used in the source
 * code.
 * We use the specific nomenclature "DeprecatedRepresentationTags" to determine
 * the presence of these
 * @author jkowalczyk
 */
public class Aw20Rule10011 extends AbstractPageRuleImplementation {

//    Object selectionNomenclatureList;
//    private boolean initialized = false;

    public Aw20Rule10011() {
        super();
//        setUp();
    }

//    void initialize() {
//        if (initialized) {
//            return;
//        }
//
//        initialized = true;
//    }

//    Object process() {
//        initialize();
//        processImpl();
//    }

//    Object getSelectionNomenclatureList() {
//        return null;
//    }

//    void setUp() {
//        selectionNomenclatureList.add("DeprecatedRepresentationTags");
//    }

    /**
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
//        nomenclatureList.get("DeprecatedRepresentationTags");

        Nomenclature deprecatedHtmlTags = nomenclatureLoaderService.
                loadByCode("DeprecatedRepresentationTags");

        sspHandler.beginSelection().
                selectDocumentNodes(deprecatedHtmlTags.getValueList());

        TestSolution testSolution = TestSolution.PASSED;

        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();

        for (Node node : sspHandler.getSelectedElementList()) {
            testSolution = TestSolution.FAILED;
            processRemarkList.add(processRemarkFactory.
                    create(testSolution, "DeprecatedRepresentationTagFound"));
        }

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                processRemarkList);

        return processResult;
    }
}
