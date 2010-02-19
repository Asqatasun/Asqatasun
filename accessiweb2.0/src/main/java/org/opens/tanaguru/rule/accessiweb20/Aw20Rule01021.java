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
public class Aw20Rule01021 extends AbstractPageRuleImplementation {

    public static final String IMG_TAG = "IMG";
    public static final String SRC_ATTRIBUTE = "src";
    public static final String LONGDESC_ATTRIBUTE = "longdesc";
    public static final String ALT_ATTRIBUTE = "alt";
    private URLIdentifier uRLIdentifier = new URLIdentifierImpl();

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        TestSolution checkResult = null;
        int nbOfImgTag = sspHandler.beginSelection().
                selectDocumentNodes(IMG_TAG).getSelectedElementList().size();

        int nbOfImgTagWithLongDesc = sspHandler.
                beginSelection().selectDocumentNodes(IMG_TAG).
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
                if (isDecorativeImage(sspHandler, imgSrc)) {
                    decorativeImgList.add(imgNode);
                }
            }

            checkResult = TestSolution.NEED_MORE_INFO;

            for (Node node : decorativeImgList) {
                if (node.getAttributes().getNamedItem(ALT_ATTRIBUTE) != null) {
                    if (!node.getAttributes().getNamedItem(ALT_ATTRIBUTE).getTextContent().equals("")) {
                        checkResult = TestSolution.FAILED;
                        break;
                    } else {
                        checkResult = TestSolution.PASSED;
                    }
                }
            }
            if (checkResult == TestSolution.PASSED &&
                    decorativeImgList.size() != nonInformativeImgNodeList.size()) {
                checkResult = TestSolution.NEED_MORE_INFO;
            }
        }

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                sspHandler.getRemarkList());

        return result;
    }

    private boolean isDecorativeImage(SSPHandler sspHandler, String imgUrl) {
        boolean isMonoColorImg = true;
        boolean isMonoDimension = false;
        boolean isDecorativeImg = false;
        try {
            URL src = new URL(sspHandler.getSSP().getURI());
            uRLIdentifier.setUrl(src);

            BufferedImage image = null;
            try {
                image = ImageIO.read(uRLIdentifier.resolve(imgUrl));
            } catch (IOException ex) {
                Logger.getLogger(Aw20Rule01021.class.getName()).
                        log(Level.WARNING, null, ex);
            }

            int rgbColor = -1;
            if (image != null) {
                if (image.getWidth() < 1 || image.getHeight() < 1) {
                    isMonoDimension = true;
                } else {
                    for (int i = 0; i < image.getWidth(); i++) {
                        for (int j = 0; j < image.getHeight(); j++) {
                            if (i == 0 && j == 0) {
                                rgbColor = image.getRGB(i, j);
                            }
                            if (rgbColor != image.getRGB(i, j)) {
                                i = image.getWidth();
                                j = image.getHeight();
                                isMonoColorImg = false;
                            }
                        }
                    }
                }
            }
            if (isMonoColorImg || isMonoDimension) {
                isDecorativeImg = true;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Aw20Rule01021.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return isDecorativeImg;
    }
}
