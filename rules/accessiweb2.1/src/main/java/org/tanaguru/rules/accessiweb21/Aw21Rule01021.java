/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.accessiweb21;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import org.tanaguru.entity.audit.DefiniteResult;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.tanaguru.rules.accessiweb21.image.ImageChecker;
import org.w3c.dom.Node;

/**
 * This rule tests if all the decorative images have an empty "alt" attribute
 * @author jkowalczyk
 */
public class Aw21Rule01021 extends AbstractPageRuleImplementation {

    public static final String SUSPECTED_IMG_MSG =
            "SuspectedDecorativeImageWithNotEmptyAltAttribute";
    public static final String XPATH_EXPR1 =
        "//IMG[@alt and @longdesc and not(ancestor::A) and @src]";
    public static final String XPATH_EXPR2 =
            "//IMG[@alt and not(ancestor::A) and @src]";
    public static final String XPATH_EXPR3 =
            "//IMG[@alt and not(ancestor::A) and not(@longdesc) and @src]";
    
    public Aw21Rule01021() {
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
                getNamedItem(NodeAndAttributeKeyStore.SRC_ATTR).getTextContent();

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
                    NodeAndAttributeKeyStore.ALT_ATTR);
            Node srcAttribute = workingElement.getAttributes().getNamedItem(
                    NodeAndAttributeKeyStore.SRC_ATTR);
            if (altAttribute != null && srcAttribute != null) {
                if (altAttribute.getNodeValue().length() != 0) {
                    sspHandler.getProcessRemarkService().addSourceCodeRemark(
                            checkResult,
                            workingElement,
                            SUSPECTED_IMG_MSG,
                            srcAttribute.getNodeValue());
                }
            }
        }

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                sspHandler.getRemarkList());

        return result;
    }
}
