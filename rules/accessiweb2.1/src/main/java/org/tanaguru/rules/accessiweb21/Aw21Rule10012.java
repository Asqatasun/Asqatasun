/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.accessiweb21;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.Nomenclature;
import org.tanaguru.entity.reference.NomenclatureElement;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.tanaguru.ruleimplementation.RuleHelper;
import org.tanaguru.service.ProcessRemarkService;
import org.w3c.dom.Node;

/**
 * On each Web page, attributes serving for information presentation must not
 * be available in the page source code. Does this rule have been followed?
 * 
 * @author jkowalczyk
 */
public class Aw21Rule10012 extends AbstractPageRuleImplementation {

    private static final String MESSAGE_CODE =
            "TagWithDeprecatedRepresentationAttributeFound";

    private static final String XPATH_EXPR =
            "//*[name()!='IMG' and name()!='OBJECT' and ancestor-or-self::BODY]";

    private static final String XPATH_EXPR1 =
            "//IMG[ancestor::BODY] | //OBJECT[ancestor::BODY]";

    private ProcessRemarkService processRemarkService;
    private int attrCounter = 0;

    public Aw21Rule10012() {
        super();
    }

    /**
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        processRemarkService = sspHandler.getProcessRemarkService();
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
        Set<Nomenclature> nomenclatureSet = new HashSet<Nomenclature>();
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();
        Nomenclature deprecatedHtmlAttributes = nomenclatureLoaderService.
                loadByCode("DeprecatedRepresentationAttributes");
        Nomenclature deprecatedHtmlAttributesWithException = nomenclatureLoaderService.
                loadByCode("DeprecatedRepresentationAttributesWithExceptions");

        nomenclatureSet.add(deprecatedHtmlAttributes);
        resultSet.add(searchDeprecatedAttributes(XPATH_EXPR1, sspHandler,nomenclatureSet));
        processRemarkList.addAll(sspHandler.getRemarkList());

        nomenclatureSet.add(deprecatedHtmlAttributesWithException);
        resultSet.add(searchDeprecatedAttributes(XPATH_EXPR, sspHandler,nomenclatureSet));
        processRemarkList.addAll(sspHandler.getRemarkList());

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                RuleHelper.synthesizeTestSolutionCollection(resultSet),
                processRemarkList);
        processResult.setElementCounter(attrCounter);
        return processResult;
    }

    /**
     * 
     * @param xpathExpr
     * @param sspHandler
     * @param nomenclatureSet
     * @return
     */
    private TestSolution searchDeprecatedAttributes(String xpathExpr, 
            SSPHandler sspHandler, Set<Nomenclature> nomenclatureSet) {

        TestSolution testSolution = TestSolution.PASSED;
        sspHandler.beginSelection().domXPathSelectNodeSet(xpathExpr);
        for (Node node : sspHandler.getSelectedElementList()) {
            attrCounter += node.getAttributes().getLength();
            for (int i=0 ; i<node.getAttributes().getLength() ;i++){
                for (Nomenclature nom : nomenclatureSet){
                    for (NomenclatureElement nomElement : nom.getElementList()){
                        if (node.getAttributes().item(i).getNodeName().
                                equalsIgnoreCase(nomElement.getLabel())){
                            testSolution = TestSolution.FAILED;
                            addSourceCodeRemark(
                                    testSolution,
                                    node,
                                    node.getAttributes().item(i).getNodeName(),
                                    processRemarkService.getEvidenceElement(
                                        EvidenceKeyStore.NODE_EE,
                                        node.getNodeName()));
                        }
                    }
                }
            }
        }
        return testSolution;
    }

    private void addSourceCodeRemark(
            TestSolution testSolution,
            Node node, 
            String defaultEvidenceValue,
            EvidenceElement otherEvidenceElement) {

        List<EvidenceElement> evidenceElementList =
                new ArrayList<EvidenceElement>();
        EvidenceElement defaultEvidenceElement =
                processRemarkService.getEvidenceElementFactory().create();
        defaultEvidenceElement.setValue(defaultEvidenceValue);
        defaultEvidenceElement.setEvidence(
                processRemarkService.getEvidenceDataService().
                findByCode(ProcessRemarkService.DEFAULT_EVIDENCE));
        evidenceElementList.add(defaultEvidenceElement);
        evidenceElementList.add(otherEvidenceElement);

        processRemarkService.addSourceCodeRemark(
                testSolution,
                node,
                MESSAGE_CODE,
                evidenceElementList);
    }

}