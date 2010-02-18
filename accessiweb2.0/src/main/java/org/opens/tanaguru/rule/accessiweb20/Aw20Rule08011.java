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
 * This rule tests if the doctype "balise" is present in the document
 * @author jkowalczyk
 */
public class Aw20Rule08011 extends AbstractPageRuleImplementation {

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {



        TestSolution checkResult = sspHandler.checkChildNodeExists("doctype");
        DefiniteResult definiteResult = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                sspHandler.getRemarkList());
        return definiteResult;
    }
}
