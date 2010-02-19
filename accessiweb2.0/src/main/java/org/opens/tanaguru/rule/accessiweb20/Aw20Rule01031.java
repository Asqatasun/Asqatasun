/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb20;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.opens.tanaguru.ruleimplementation.ImageChecker;
import org.opens.tanaguru.entity.audit.ProcessRemark;

/**
 * This rule tests if an informative image has a pertinent alt attribute content
 * @author jkowalczyk
 */
public class Aw20Rule01031 extends AbstractPageRuleImplementation {

    public static final String IMG_TAG = "IMG";
    public static final String SRC_ATTRIBUTE = "src";
    public static final String ALT_ATTRIBUTE = "alt";
    public static final String TITLE_ATTRIBUTE = "title";
    public static final String XPATH_EXPR =
            "//IMG[@title and @alt and @longdesc]";

    public Aw20Rule01031() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        
        TestSolution checkResult = TestSolution.NEED_MORE_INFO;
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();

        List<Node> imgNodes = sspHandler.beginSelection().
                selectDocumentNodes(IMG_TAG).getSelectedElementList();

        int decorativeImgCount = 0;

        for (Node imgNode : imgNodes) {
            String imgSrc = imgNode.getAttributes().
                        getNamedItem(SRC_ATTRIBUTE).getTextContent();
            if (ImageChecker.getInstance().isDecorativeImage(sspHandler, imgSrc)) {
                decorativeImgCount++;
            }
        }

        if (decorativeImgCount== imgNodes.size()) {
            checkResult = TestSolution.NOT_APPLICABLE;
        } else {
            List<Node> informativeWithTitleAndAltNodes = 
                    sspHandler.beginSelection().
                    domXPathSelectNodeSet(XPATH_EXPR).getSelectedElementList();
            String altAttributeContent = null;
            String titleAttributeContent = null;
            for (Node node : informativeWithTitleAndAltNodes) {
                altAttributeContent = node.getAttributes().
                        getNamedItem(ALT_ATTRIBUTE).getTextContent();
                titleAttributeContent = node.getAttributes().
                        getNamedItem(TITLE_ATTRIBUTE).getTextContent();
                if (altAttributeContent.equalsIgnoreCase(titleAttributeContent))   {
                    checkResult = TestSolution.FAILED;
                    processRemarkList.add(processRemarkFactory.
                            create(TestSolution.FAILED, "NotPertinentAltAttribute"));
                }
            }
        }

        processRemarkList.addAll(sspHandler.getRemarkList());
        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                processRemarkList);

        return result;
    }
}
