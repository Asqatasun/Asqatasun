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
 * This rule tests if an area tag defined in a map tag, associated with the usemap
 * attribute of an img tag, has an alt attribute
 * @author jkowalczyk
 */
public class Aw20Rule01012 extends AbstractPageRuleImplementation {

    public static final String XPATH_EXPR = 
            "//AREA[concat('#', ancestor::MAP/@name) = //IMG/@usemap]";
    public static final String XPATH_EXPR2 =
            "//AREA[ancestor::MAP/@name = //IMG/@usemap]";

    public static final String ALT_ATTRIBUTE = "ALT";

    public Aw20Rule01012(){
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        
        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR);
        TestSolution checkResult = sspHandler.checkAttributeExists(ALT_ATTRIBUTE);

        if (checkResult == TestSolution.NOT_APPLICABLE) {
            sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR2);
            checkResult = sspHandler.checkAttributeExists(ALT_ATTRIBUTE);
        }
        
        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                sspHandler.getRemarkList());
        return result;
    }
}
