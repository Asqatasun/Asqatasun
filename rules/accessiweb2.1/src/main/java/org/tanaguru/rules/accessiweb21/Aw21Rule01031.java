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
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.Nomenclature;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.tanaguru.rules.accessiweb21.image.ImageChecker;
import org.tanaguru.service.ProcessRemarkService;
import org.w3c.dom.Node;

/**
 * This rule tests if an informative image has a pertinent alt attribute content
 * @author jkowalczyk
 */
public class Aw21Rule01031 extends AbstractPageRuleImplementation {

    private static final String NOT_PERTINENT_ALT_ATTRIBUTE =
            "NotPertinentAltAttribute";
    private static final String NOT_PERTINENT_ALT_BUT_SUSPECTED_DECORATED = 
            "NotPertinentAltAttributeButSuspectedDecorativeImage";
    private static final String NOT_PERTINENT_ALT_WITH_SUSPECTED_INFORMATIVE =
            "NotPertinentAltAttributeWithSuspectedInformativeImage";
    private static final String XPATH_EXPR1 =
            "//IMG[@alt and not(ancestor::A) and @src]";
    private static final String XPATH_EXPR2 =
            "//IMG[@alt and @longdesc and not(ancestor::A) and @src]";
    private static final String XPATH_EXPR3 =
            "//IMG[@alt and not(@longdesc) and not(ancestor::A) and @src]";
    private ProcessRemarkService processRemarkService;

    public Aw21Rule01031() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        this.processRemarkService = sspHandler.getProcessRemarkService();
        int elementCounter = 0;
        TestSolution checkResult = TestSolution.NEED_MORE_INFO;

        // Get the list of <img> tags with an "alt" attribute
        //but not within a <a> tag
        List<Node> imgNodes = sspHandler.beginSelection().
                domXPathSelectNodeSet(XPATH_EXPR1).getSelectedElementList();

        // If the page has no <img> tag with an alt attribute, the test result
        // is NA
        if (imgNodes.isEmpty()) {
            checkResult = TestSolution.NOT_APPLICABLE;
            DefiniteResult result = definiteResultFactory.create(
                    test,
                    sspHandler.getSSP().getPage(),
                    checkResult,
                    sspHandler.getRemarkList());
            return result;
        }

        // Fill-in 2 node lists : one with the suspected decorated images,
        // the other with all the other nodes.
        List<Node> suspectedDecoratedNodes = new ArrayList<Node>();
        List<Node> notSuspectedDecoratedNodes = new ArrayList<Node>();

        for (Node imgNode : sspHandler.beginSelection().
                domXPathSelectNodeSet(XPATH_EXPR3).getSelectedElementList()) {
            String imgSrc = imgNode.getAttributes().
                    getNamedItem(NodeAndAttributeKeyStore.SRC_ATTR).getTextContent();
            BufferedImage image = sspHandler.getImageFromURL(imgSrc);
            if (ImageChecker.getInstance().isDecorativeImage(image)) {
                suspectedDecoratedNodes.add(imgNode);
            } else {
                notSuspectedDecoratedNodes.add(imgNode);
            }
        }

        //Images with "longdesc" attribute are considered as informative images
        List<Node> informativeImageNodes = sspHandler.beginSelection().
                domXPathSelectNodeSet(XPATH_EXPR2).getSelectedElementList();

        //check the pertinence of the  alt attribute for images that are
        // surely informative, and not suspected as decorative
        if (!notSuspectedDecoratedNodes.isEmpty()) {
            checkResult = checkAltAttributePertinence(
                notSuspectedDecoratedNodes,
                NOT_PERTINENT_ALT_WITH_SUSPECTED_INFORMATIVE,
                TestSolution.NEED_MORE_INFO);
            elementCounter += notSuspectedDecoratedNodes.size();
        }

        //check the pertinence of the alt attribute for images that are
        // suspected as decorative
        if (!suspectedDecoratedNodes.isEmpty())  {
            checkResult = checkAltAttributePertinence(
                suspectedDecoratedNodes,
                NOT_PERTINENT_ALT_BUT_SUSPECTED_DECORATED,
                TestSolution.NEED_MORE_INFO);
            elementCounter += notSuspectedDecoratedNodes.size();
        }

        // check the pertinence of the alt attribute for images that are
        // surely informative. In this case, the test result is Failed is at
        // least one element has a not pertinent alt attribute
        if (!informativeImageNodes.isEmpty()) {
            checkResult = checkAltAttributePertinence(
                informativeImageNodes,
                NOT_PERTINENT_ALT_ATTRIBUTE,
                TestSolution.FAILED);
            elementCounter += notSuspectedDecoratedNodes.size();
        }

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                sspHandler.getRemarkList());
        result.setElementCounter(elementCounter);
        return result;
    }

    /**
     * This method checks if a file has an image extension.
     * We use the "ImageFileExtensions" nomenclature to do so
     * @param fileName
     * @return
     */
    private boolean hasAnImageExtension(String fileName) {
        Nomenclature imageFileExtensionNomenclature =
                nomenclatureLoaderService.loadByCode("ImageFileExtensions");
        int beginIndex = fileName.lastIndexOf('.');
        String fileExt = fileName.substring(beginIndex + 1, fileName.length());
        for (String object : imageFileExtensionNomenclature.getValueList()) {
            if (fileExt.equalsIgnoreCase(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This methods check the pertinence of an alt attribute.
     * To do so, we check that the source attribute and the alt attribute
     * not are equal.
     * We also verify if the the alt attribute has not a file extension.
     * @param nodeList
     * @param processRemarkMessage
     * @param processRemarkList
     */
    private TestSolution checkAltAttributePertinence(
            List<Node> nodeList, 
            String processRemarkMessage,
            TestSolution testSolution) {
        String altAttributeContent;
        String srcAttributeContent;

        TestSolution defaultSolution = TestSolution.NEED_MORE_INFO;
        for (Node node : nodeList) {
            List<EvidenceElement> evidenceElementList =
                new ArrayList<EvidenceElement>();
            // extract the content of the alt attribute of the node
            altAttributeContent = node.getAttributes().
                    getNamedItem(NodeAndAttributeKeyStore.ALT_ATTR).getTextContent();
            // extract the content of the src attribute of the node
            srcAttributeContent = node.getAttributes().
                    getNamedItem(NodeAndAttributeKeyStore.SRC_ATTR).getTextContent();


            if ((srcAttributeContent != null
                    && altAttributeContent.equalsIgnoreCase(srcAttributeContent))
                    || hasAnImageExtension(altAttributeContent)) {
                
                evidenceElementList.add(processRemarkService.getEvidenceElement(
                        processRemarkService.getEvidenceDataService().
                        findByCode(ProcessRemarkService.DEFAULT_EVIDENCE).getCode(),
                        altAttributeContent));

                evidenceElementList.add(processRemarkService.getEvidenceElement(
                        processRemarkService.getEvidenceDataService().
                        findByCode(EvidenceKeyStore.SRC_EE).getCode(),
                        srcAttributeContent));

                processRemarkService.addSourceCodeRemark(
                        testSolution,
                        node,
                        processRemarkMessage,
                        evidenceElementList);
                defaultSolution = testSolution;
            }
        }
        return defaultSolution;
    }
}
