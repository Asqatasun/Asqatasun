/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb20;

import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * This rule tests if all the frame tags have a "title" attribute
 * @author jkowalczyk
 */
public class Aw20Rule02011 extends AbstractPageRuleImplementation {

    public static final String XPATH_EXPR =
            "//FRAME";
    public static final String TITLE_ATTRIBUTE = "title";

    public Aw20Rule02011() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR);

        TestSolution checkResult = sspHandler.checkAttributeExists(TITLE_ATTRIBUTE);

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                sspHandler.getRemarkList());

        return result;
    }
}
