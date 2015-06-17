/*
 * @(#)AbstractTagDetectionPageRuleImplementation.java
 *
 * Copyright  2010 SAS OPEN-S. All rights reserved.
 * OPEN-S PROPRIETARY/CONFIDENTIAL.  Use is subject to license terms.
 *
 * This file is  protected by the  intellectual  property rights
 * in  France  and  other  countries, any  applicable  copyrights  or
 * patent rights, and international treaty provisions. No part may be
 * reproduced  in  any  form  by  any  mean  without   prior  written
 * authorization of OPEN-S.
 */
package org.tanaguru.rules.accessiweb21.detection;

import java.util.Collection;
import java.util.LinkedList;
import org.tanaguru.entity.audit.DefiniteResult;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.w3c.dom.Node;

/**
 * This abstract class checks nodes from a xpath selection expression
 * and creates a sourceCodeRemark for each selected element.
 * If the selection set is empty, this rule returns true, false instead. 
 * 
 * @author jkowalczyk
 */
public class AbstractTagDetectionPageRuleImplementation
        extends AbstractPageRuleImplementation {

    private String messageCode;
    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    private String selectionExpression;
    public String getSelectionExpression() {
        return selectionExpression;
    }

    public void setSelectionExpression(String xPathExpr) {
        this.selectionExpression = xPathExpr;
    }

    private TestSolution notDetectedSolution = TestSolution.PASSED;
    public TestSolution getNotDetectedSolution() {
        return notDetectedSolution;
    }

    public void setNotDetectedSolution(TestSolution notDetectedSolution) {
        this.notDetectedSolution = notDetectedSolution;
    }

    private TestSolution detectedSolution = TestSolution.FAILED;
    public TestSolution getDetectedSolution() {
        return detectedSolution;
    }

    public void setDetectedSolution(TestSolution detectedSolution) {
        this.detectedSolution = detectedSolution;
    }

    private boolean isRemarkCreatedOnDetection = true;
    public boolean isIsRemarkCreatedOnDetection() {
        return isRemarkCreatedOnDetection;
    }

    public void setIsRemarkCreatedOnDetection(boolean isRemarkCreatedOnDetection) {
        this.isRemarkCreatedOnDetection = isRemarkCreatedOnDetection;
    }


    public AbstractTagDetectionPageRuleImplementation() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        Collection<Node> elements = sspHandler.beginSelection().
                domXPathSelectNodeSet(getSelectionExpression()).
                getSelectedElementList();
        Collection<ProcessRemark> processRemarkSet = new LinkedList<ProcessRemark>();
        TestSolution checkResult = notDetectedSolution;
        if (!elements.isEmpty()) {
            checkResult = detectedSolution;
            if (isRemarkCreatedOnDetection) {
                for (Node node  : elements) {
                    processRemarkSet.add(
                        sspHandler.getProcessRemarkService().createSourceCodeRemark(
                            checkResult,
                            node,
                            getMessageCode(),
                            node.getNodeName()));
                }
            }
        } else if (!isRemarkCreatedOnDetection) {
            processRemarkSet.add(
                sspHandler.getProcessRemarkService().createProcessRemark(
                        checkResult,
                        getMessageCode()));
        }

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                processRemarkSet);
        result.setElementCounter(sspHandler.getSelectedElementList().size());
        return result;
    }

}