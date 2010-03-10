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
import org.opens.tanaguru.entity.reference.Nomenclature;

/**
 * This rule tests if an informative image has a pertinent alt attribute content
 * @author jkowalczyk
 */
public class Aw20Rule01031 extends AbstractPageRuleImplementation {

    public static final String IMG_TAG = "IMG";
    public static final String SRC_ATTRIBUTE = "src";
    public static final String ALT_ATTRIBUTE = "alt";
    public static final String TITLE_ATTRIBUTE = "title";
    public static final String XPATH_EXPR1 =
            "//IMG[@alt and not(ancestor::A)]";
    public static final String XPATH_EXPR2 =
            "//IMG[@alt and @longdesc and not(ancestor::A)]";

    public Aw20Rule01031() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        
        TestSolution checkResult = TestSolution.NEED_MORE_INFO;
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();

        List<Node> imgNodes = sspHandler.beginSelection().
                domXPathSelectNodeSet(XPATH_EXPR1).getSelectedElementList();

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
            List<Node> imgWithLongdescAndAltNodeList =
                    sspHandler.beginSelection().
                    domXPathSelectNodeSet(XPATH_EXPR2).getSelectedElementList();
            String altAttributeContent = null;
            String fileName = null;
            for (Node node : imgWithLongdescAndAltNodeList) {
                altAttributeContent = node.getAttributes().
                        getNamedItem(ALT_ATTRIBUTE).getTextContent();
                fileName = getFileNameFromUrl(node.getAttributes().
                        getNamedItem(SRC_ATTRIBUTE).getTextContent());
                if (fileName!=null &&
                        altAttributeContent.equalsIgnoreCase(fileName))   {
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

    /**
     *
     * @param url
     * @return
     */
    private String getFileNameFromUrl(String url) {
        int beginIndex = url.lastIndexOf('/');
        String fileName = url.substring(beginIndex+1, url.length());
        if (hasAnImageExtension(fileName)) {
            return fileName;
        } else {
            return null;
        }
    }

    /**
     *
     * @param fileName
     * @return
     */
    private boolean hasAnImageExtension(String fileName) {
        Nomenclature imageFileExtensionNomenclature = nomenclatureLoaderService.
                loadByCode("ImageFileExtensions");
        int beginIndex = fileName.lastIndexOf('.');
        String fileExt = fileName.substring(beginIndex+1, fileName.length());
        for (String object : imageFileExtensionNomenclature.getValueList()) {
            if (fileExt.equalsIgnoreCase(object)) {
                return true;
            }
        }
        return false;
    }
}
