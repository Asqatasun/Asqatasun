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
 * This rule tests if all the decorative images have an empty "alt" attribute
 * @author jkowalczyk
 */
public class Aw20Rule01011 extends AbstractPageRuleImplementation {

    public static final String IMG_TAG = "IMG";
    public static final String ALT_ATTRIBUTE = "alt";

    public Aw20Rule01011() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        sspHandler.beginSelection().
                selectDocumentNodes(IMG_TAG);

        TestSolution checkResult = sspHandler.checkAttributeExists(ALT_ATTRIBUTE);

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                sspHandler.getRemarkList());

        return result;
    }
}
