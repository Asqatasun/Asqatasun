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
 * This rule tests if all the decorative images have an empty "alt" attribute
 * @author jkowalczyk
 */
public class Aw20Rule01021 extends AbstractPageRuleImplementation {

    public static final String IMG_TAG = "IMG";
    public static final String SRC_ATTRIBUTE = "src";
    public static final String LONGDESC_ATTRIBUTE = "longdesc";
    public static final String ALT_ATTRIBUTE = "alt";

    public Aw20Rule01021() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        
        TestSolution checkResult = null;
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();

        int nbOfImgTag = sspHandler.beginSelection().
                selectDocumentNodes(IMG_TAG).getSelectedElementList().size();

        int nbOfImgTagWithLongDesc = sspHandler.beginSelection().selectDocumentNodes(IMG_TAG).
                keepNodesWithAttribute(LONGDESC_ATTRIBUTE).
                getSelectedElementList().size();

        if (nbOfImgTag == nbOfImgTagWithLongDesc) { //all the images are informative
            checkResult = TestSolution.NOT_APPLICABLE; // the test is non applicable
        } else {

            List<Node> nonInformativeImgNodeList = sspHandler.beginSelection().
                    selectDocumentNodes(IMG_TAG).excludeNodesWithAttribute(LONGDESC_ATTRIBUTE).
                    getSelectedElementList();

            List<Node> decorativeImgList = new ArrayList<Node>();

            for (Node imgNode : nonInformativeImgNodeList) {
                String imgSrc = imgNode.getAttributes().
                        getNamedItem(SRC_ATTRIBUTE).getTextContent();
                if (ImageChecker.getInstance().isDecorativeImage(sspHandler, imgSrc)) {
                    decorativeImgList.add(imgNode);
                }
            }

            checkResult = TestSolution.NEED_MORE_INFO;
            boolean hasAtLeastOneImageFailed = false;
            boolean hasAtLeastOneImageWithAltAttribute = false;
            for (Node node : decorativeImgList) {
                if (node.getAttributes().getNamedItem(ALT_ATTRIBUTE) != null) {
                    if (!node.getAttributes().getNamedItem(ALT_ATTRIBUTE).getTextContent().equals("")) {
                        hasAtLeastOneImageFailed = true;
                        processRemarkList.add(processRemarkFactory.
                            create(TestSolution.FAILED, "DecorativeImageWithNotEmptyAltAttribute"));
                        
                    }
                    hasAtLeastOneImageWithAltAttribute = true;
                }
            }
            
            if (hasAtLeastOneImageWithAltAttribute) {
                if (hasAtLeastOneImageFailed){
                    checkResult = TestSolution.FAILED;
                } else {
                    checkResult = TestSolution.PASSED;
                }
            } else if (decorativeImgList.size() == nonInformativeImgNodeList.size()) {
                checkResult = TestSolution.NOT_APPLICABLE;
            }

            if (checkResult == TestSolution.PASSED
                    && decorativeImgList.size() != nonInformativeImgNodeList.size()) {
                checkResult = TestSolution.NEED_MORE_INFO;
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
