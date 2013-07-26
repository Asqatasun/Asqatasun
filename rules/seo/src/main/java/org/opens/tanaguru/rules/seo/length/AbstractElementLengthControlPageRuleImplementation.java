/*
 * @(#)AbstractElementLengthControlPageRuleImplementation.java
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
package org.opens.tanaguru.rules.seo.length;

import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * This abstract class checks whether a page contains a textuel element that
 * exceed a given size.
 * If the size of the element exceeds the limit, this rule returns true,
 * false instead.
 * 
 * @author jkowalczyk
 */
public class AbstractElementLengthControlPageRuleImplementation extends AbstractPageRuleImplementation {

    private int length;
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    private String messageCode;
    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    private String element;
    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public AbstractElementLengthControlPageRuleImplementation() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.beginSelection();
        TestSolution testSolution = TestSolution.PASSED;
        if (element.length() > length) {
            testSolution = TestSolution.FAILED;
            sspHandler.getProcessRemarkService().addProcessRemark(
                            testSolution,
                            getMessageCode());
        }
        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                testSolution,
                sspHandler.getRemarkList());

        return result ;
    }

}