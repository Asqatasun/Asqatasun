/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb20;

import java.awt.image.BufferedImage;
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
    public static final String ALT_ATTRIBUTE = "alt";
    public static final String EMPTY_STRING = "";
    public static final String SUSPECTED_IMG_MSG =
            "SuspectedDecorativeImageWithNotEmptyAltAttribute";
    public static final String XPATH_EXPR1 =
        "//IMG[@alt and @longdesc and not(ancestor::A) and @src]";
    public static final String XPATH_EXPR2 =
            "//IMG[@alt and not(ancestor::A) and @src]";
    public static final String XPATH_EXPR3 =
            "//IMG[@alt and not(ancestor::A) and not(@longdesc) and @src]";

    public Aw20Rule01021() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        TestSolution checkResult = TestSolution.NEED_MORE_INFO;
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();

        // number of informative images (images with "alt"
        // and "longdesc" attributes)
        int imgWithAltAndLongdescAttributeCounter =
                sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR1).
                getSelectedElementList().size();

        // number of images with "alt" attribute
        int imgWithAltAttributeCounter = 
                sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR2).
                getSelectedElementList().size();

        // if there's no img in the page with "alt" attribute or all the images
        // with "alt" attributes are informative (with longdesc "attribute")
        // the test is not applicable
        if (imgWithAltAttributeCounter == 0
                || imgWithAltAttributeCounter == imgWithAltAndLongdescAttributeCounter) {
            checkResult = TestSolution.NOT_APPLICABLE;
            DefiniteResult result = definiteResultFactory.create(
                    test,
                    sspHandler.getSSP().getPage(),
                    checkResult,
                    processRemarkList);
            return result;
        }

        // selection of images with "alt" attribute but without "longdesc" attribute
        List<Node> imgWithAltAttributeNodeList = 
                sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR3).
                getSelectedElementList();

        List<Node> decorativeImgWithAltAttributeNodeList = new ArrayList<Node>();
        BufferedImage image = null;
        for (Node imgNode : imgWithAltAttributeNodeList) {
            String imgSrc = imgNode.getAttributes().
                getNamedItem(SRC_ATTRIBUTE).getTextContent();

            // for each element of the collection, we test if this image is decorative
            image = sspHandler.getImageFromURL(imgSrc);
            if (image != null ) {
                if (ImageChecker.getInstance().
                        isDecorativeImage(image)) {
                    decorativeImgWithAltAttributeNodeList.add(imgNode);
                }
            }
        }

        for(Node workingElement : decorativeImgWithAltAttributeNodeList){
            Node altAttribute = workingElement.getAttributes().getNamedItem(
                    ALT_ATTRIBUTE);
            Node srcAttribute = workingElement.getAttributes().getNamedItem(
                    SRC_ATTRIBUTE);
            if (altAttribute != null && srcAttribute != null) {
                if (altAttribute.getNodeValue().length() != 0) {
                    sspHandler.addSourceCodeRemark(
                            checkResult,
                            workingElement,
                            SUSPECTED_IMG_MSG,
                            srcAttribute.getNodeValue());
                }
            }
        }

//        if (!decorativeImgWithAltAttributeNodeList.isEmpty()) {
//            sspHandler.setSelectedElementList(decorativeImgWithAltAttributeNodeList);
//            sspHandler.checkAttributeValueIsEmpty(ALT_ATTRIBUTE);

//        }

//        // if all the images with an "alt" attribute but without "longdesc"
//        // attribute are seen as decorative and if none of them has an "alt"
//        // attribute equals to an empty string, the test is passed
//        if (checkResult == TestSolution.PASSED
//                && decorativeImgWithAltAttributeNodeList.size() <
//                imgWithAltAttributeNodeList.size()) {
//            checkResult = TestSolution.NEED_MORE_INFO;
//        }


        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                sspHandler.getRemarkList());

        return result;
    }
}
