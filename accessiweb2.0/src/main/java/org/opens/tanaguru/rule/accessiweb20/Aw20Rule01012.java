/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb20;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Node;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.contentadapter.util.URLIdentifierImpl;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * This rule tests if all the decorative images have an empty "alt" attribute
 * @author jkowalczyk
 */
public class Aw20Rule01012 extends AbstractPageRuleImplementation {

    public static final String XPATH_EXPR = 
            "//AREA[concat('#', ancestor::MAP@name) = //IMG@usemap]";
    public static final String XPATH_EXPR2 =
            "//AREA[ancestor::MAP@name = //IMG@usemap]";
    public static final String XPATH_EXPR3 =
            "//AREA[concat('#', ancestor::MAP@id) = //IMG@usemap]";
    public static final String XPATH_EXPR4 =
            "//AREA[ancestor::MAP@id = //IMG@usemap]";

    public static final String ALT_ATTRIBUTE = "ALT";

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR);
        TestSolution checkResult = sspHandler.checkAttributeExists(ALT_ATTRIBUTE);

        if (checkResult == TestSolution.NOT_APPLICABLE) {
            sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR2);
            checkResult = sspHandler.checkAttributeExists(ALT_ATTRIBUTE);
        }
        
        if (checkResult == TestSolution.NOT_APPLICABLE) {
            sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR3);
            checkResult = sspHandler.checkAttributeExists(ALT_ATTRIBUTE);
        }
        if (checkResult == TestSolution.NOT_APPLICABLE) {
            sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR4);
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
