/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
package org.tanaguru.processor;

import com.phloc.css.decl.CascadingStyleSheet;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.jsoup.select.Elements;
import org.tanaguru.contentadapter.util.URLIdentifier;
import org.tanaguru.entity.audit.*;
import org.tanaguru.entity.reference.Nomenclature;
import org.tanaguru.entity.service.audit.PreProcessResultDataService;
import org.tanaguru.entity.service.audit.ProcessRemarkDataService;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.ruleimplementation.RuleHelper;
import org.tanaguru.service.NomenclatureLoaderService;
import org.tanaguru.service.ProcessRemarkService;
import org.w3c.dom.Node;

/**
 * 
 * @author jkowalczyk
 */
public class SSPHandlerImpl implements SSPHandler {

    private static final String BASE64_IMAGE_PREFIX = "data:image";
    private static final char COMMA = ',';
    private CSSHandler cssHandler;
    private DOMHandler domHandler;
    private JSHandler jsHandler;
    private SSP ssp;
    private NomenclatureLoaderService nomenclatureLoaderService;
    private ProcessRemarkDataService processRemarkDataService;
    private String selectionExpression;
    private Map<String, BufferedImage> imageMap;
    private URLIdentifier urlIdentifier;
    private Set<ImageContent> imageOnErrorSet;
    private ProcessRemarkService processRemarkService;
    private final PreProcessResultDataService preProcessResultDataService;

    /**
     * 
     * @param nomenclatureLoaderService
     * @param urlIdentifier
     * @param create
     * @param create0
     * @param processRemarkService 
     * @param preProcessResultDataService
     */
    SSPHandlerImpl(
            NomenclatureLoaderService nomenclatureLoaderService, 
            URLIdentifier urlIdentifier, 
            CSSHandler create, 
            DOMHandler create0, 
            ProcessRemarkService processRemarkService, 
            PreProcessResultDataService preProcessResultDataService) {
        super();
        this.nomenclatureLoaderService = nomenclatureLoaderService;
        this.urlIdentifier = urlIdentifier;
        this.cssHandler = create;
        this.domHandler = create0;
        this.processRemarkService = processRemarkService;
        this.preProcessResultDataService = preProcessResultDataService;
    }

    @Override
    public SSPHandler beginSelection() {
        domHandler.beginXpathSelection();

        selectionExpression = null;

        try {
            urlIdentifier.setUrl(new URL(ssp.getURI()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(SSPHandlerImpl.class.getName()).error(ex);
        }
        return this;
    }
    
    @Override
    public SSPHandler beginCssLikeSelection() {
        domHandler.beginCssLikeSelection();
        
        try {
            urlIdentifier.setUrl(new URL(ssp.getURI()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(SSPHandlerImpl.class.getName()).error(ex);
        }
        return this;
    }
    
    @Override
    public SSPHandler beginCssSelection() {
        if (cssHandler != null) {
            cssHandler.beginSelection();
        }
        return this;
    }

    @Override
    public TestSolution checkAttributeExists(String attributeName) {
        return domHandler.checkAttributeExists(attributeName);
    }

    @Override
    public TestSolution checkChildNodeExists(String childNodeName) {
        return domHandler.checkChildNodeExists(childNodeName);
    }

    @Override
    public TestSolution checkNodeValue(Collection<String> blacklist,
            Collection<String> whitelist,
            TestSolution testSolution,
            String errorCodeMessage) {
        return domHandler.checkNodeValue(blacklist,
                whitelist,
                testSolution,
                errorCodeMessage);
    }

    @Override
    public TestSolution checkTextContentValue(Collection<String> blacklist,
            Collection<String> whitelist) {
        return domHandler.checkTextContentValue(blacklist, whitelist);
    }

    @Override
    public WebResource getPage() {
        return domHandler.getPage();
    }

    @Override
    public Collection<ProcessRemark> getRemarkList() {
        return processRemarkService.getRemarkList();
    }

    @Override
    public List<Node> getSelectedElementList() {
        return domHandler.getSelectedElementList();
    }
    
    @Override
    public Elements getSelectedElements() {
        return domHandler.getSelectedElements();
    }

    @Override
    public SSP getSSP() {
        return ssp;
    }

    @Override
    public boolean isSelectedElementsEmpty() {
        return domHandler.isSelectedElementsEmpty();
    }

    @Override
    public SSPHandler keepNodesWithAttribute(String attributeName) {
        domHandler.keepNodesWithAttribute(attributeName);
        return this;
    }

    @Override
    public SSPHandler keepNodesWithChildNode(String childNodeName) {
        domHandler.keepNodesWithChildNode(childNodeName);
        return this;
    }

    public SSPHandler selectAllJS() {
        if (jsHandler != null) {
            jsHandler.selectAllJS();
        }
        return this;
    }

    @Override
    public SSPHandler selectChildNodes(String childNodeName) {
        domHandler.selectChildNodes(childNodeName);
        return this;
    }

    @Override
    public SSPHandler selectChildNodesRecursively(String childNodeName) {
        domHandler.selectChildNodesRecursively(childNodeName);
        return this;
    }

    @Override
    public SSPHandler selectDocumentNodes(Collection<String> nodeNames) {
        domHandler.selectDocumentNodes(nodeNames);
        return this;
    }

    @Override
    public SSPHandler selectDocumentNodes(String nodeName) {
        domHandler.selectDocumentNodes(nodeName);
        return this;
    }

    public SSPHandler selectExternalJS() {
        if (jsHandler != null) {
            jsHandler.selectExternalJS();
        }
        return this;
    }

    public SSPHandler selectInlineJS() {
        if (jsHandler != null) {
            jsHandler.selectInlineJS();
        }
        return this;
    }

    public SSPHandler selectLocalJS() {
        if (jsHandler != null) {
            jsHandler.selectLocalJS();
        }
        return this;
    }

    @Override
    public void setCSSHandler(CSSHandler cssHandler) {
        this.cssHandler = cssHandler;
    }

    @Override
    public void setDOMHandler(DOMHandler domHandler) {
        this.domHandler = domHandler;
    }

    @Override
    public void setJSHandler(JSHandler jsHandler) {
        this.jsHandler = jsHandler;
    }

    @Override
    public void setSelectedElementList(List<Node> selectedElementList) {
        domHandler.setSelectedElementList(selectedElementList);
    }

    @Override
    public void setSSP(SSP ssp) {
        this.ssp = ssp;
        domHandler.setSSP(ssp);
        if (cssHandler != null) {
            cssHandler.setSSP(ssp);
        }
        if (jsHandler != null) {
            jsHandler.setSSP(ssp);
        }
        initializeImageMap();
    }

    @Override
    public void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureLoaderService) {
        this.nomenclatureLoaderService = nomenclatureLoaderService;
    }

    @Override
    public void setUrlIdentifier(URLIdentifier urlIdentifier) {
        this.urlIdentifier = urlIdentifier;
    }

    /**
     * http://www.ibm.com/developerworks/library/x-javaxpathapi.html
     *
     * @param expression
     * @return
     */
    @Override
    public SSPHandler domXPathSelectNodeSet(String expression) {
        domHandler.xPathSelectNodeSet(expression);
        selectionExpression = expression;
        return this;
    }
    
    /**
     * http://www.ibm.com/developerworks/library/x-javaxpathapi.html
     *
     * @param expression
     * @return
     */
    @Override
    public SSPHandler domCssLikeSelectNodeSet(String expression) {
        domHandler.cssLikeSelectNodeSet(expression);
        selectionExpression = expression;
        return this;
    }

    @Override
    public TestSolution domCheckNodeValueInNomenclature(String nomenclatureCode, String errorMessage) {
        Collection<TestSolution> resultSet = new ArrayList<>();

        Nomenclature nomenclature = nomenclatureLoaderService.loadByCode(nomenclatureCode);

        for (Node node : domHandler.getSelectedElementList()) {
            boolean isInNomenclature = false;
            String nodeValue = node.getNodeValue();
            for (String nomenclatureElementValue : nomenclature.getValueList()) {
                if (nodeValue.toLowerCase().equals(nomenclatureElementValue.toLowerCase())) {
                    isInNomenclature = true;
                    break;
                }
            }
            if (isInNomenclature) {
                resultSet.add(TestSolution.PASSED);
            } else {
                resultSet.add(TestSolution.FAILED);

                ProcessRemark remark = processRemarkDataService.create();
                remark.setIssue(TestSolution.FAILED);
                remark.setMessageCode(errorMessage);
                remark.setSelectionExpression(selectionExpression);
                remark.selectedElement(nodeValue);

//                remarkList.add(remark);
            }
        }

        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    /**
     * @return the selectionExpression
     */
    public String getSelectionExpression() {
        return selectionExpression;
    }

    /**
     * @param selectionExpression the selectionExpression to set
     */
    public void setSelectionExpression(String selectionExpression) {
        this.selectionExpression = selectionExpression;
    }

    @Override
    public BufferedImage getImageFromURL(String url) {
        // the src attribute of the img tag may embed a base64 image.
        // The format respects the following scheme: data:[<MIME-type>][;charset=<encoding>][;base64],<data>
        // We check first whether the url value starts with the "data:image" 
        // prefix and then we extract the raw data by getting the string value
        // after the comma.
        // Bug fix #428 
        if (url.startsWith(BASE64_IMAGE_PREFIX)) {
            Logger.getLogger(SSPHandlerImpl.class.getName()).info(url);
            InputStream in = new ByteArrayInputStream(Base64.decodeBase64(url.substring(url.indexOf(COMMA)+1)));
            try {
                return ImageIO.read(in);
            } catch(Exception e ) {
                Logger.getLogger(SSPHandlerImpl.class.getName()).warn(
                        "the embedded image " + url +"cannot be read");
                return null;
            }
        } else {
            try {
                String str = urlIdentifier.resolve(url).toExternalForm();
                return imageMap.get(str);
            } catch (Exception ex) {
                Logger.getLogger(SSPHandlerImpl.class.getName()).error(ex);
            }
            return null;
        }
    }

    @Override
    public TestSolution checkAttributeOnlyContainsNonAlphanumericCharacters(
            Node attribute,
            Node workingElement,
            TestSolution testSolution,
            String remarkMessage) {
        return domHandler.checkAttributeOnlyContainsNonAlphanumericCharacters(
                attribute,
                workingElement,
                testSolution,
                remarkMessage);
    }

    @Override
    public TestSolution checkAttributeOnlyContainsNonAlphanumericCharacters(
            String attributeContent,
            Node workingElement,
            TestSolution testSolution,
            String remarkMessage) {
        return domHandler.checkAttributeOnlyContainsNonAlphanumericCharacters(
                attributeContent,
                workingElement,
                testSolution,
                remarkMessage);
    }

    /**
     * This method is used to initialize the image map. Images are downloaded
     * by the crawler component, and the map we initialize here associates
     * the url of the image with the binary image content.
     */
    public void initializeImageMap() {
        if (imageMap == null) {
            imageMap = new HashMap<>();
        }
        if (imageOnErrorSet == null) {
            imageOnErrorSet = new HashSet<>();
        }
        imageMap.clear();
        for (RelatedContent relatedContent : ssp.getRelatedContentSet()) {
            if (relatedContent instanceof ImageContent) {

                BufferedImage image;
                if (((ImageContent) relatedContent).getHttpStatusCode() != 200
                        || ((ImageContent) relatedContent).getContent() == null) {
                    imageOnErrorSet.add((ImageContent) relatedContent);
                } else {
                    try {
                        image = ImageIO.read(new ByteArrayInputStream(
                                ((ImageContent) relatedContent).getContent()));
                        imageMap.put(((Content) relatedContent).getURI(), image);
                    } catch (IOException ex) {
                        Logger.getLogger(SSPHandlerImpl.class.getName()).error(ex);
                    }
                }
            }
        }
    }

    @Override
    public int getSelectedElementNumber() {
        return domHandler.getSelectedElementNumber();
    }
    
    @Override
    public int getTotalNumberOfElements() {
        return domHandler.getTotalNumberOfElements();
    }

    @Override
    public void setProcessRemarkService(ProcessRemarkService processRemarkService) {
        this.processRemarkService = processRemarkService;
        domHandler.setProcessRemarkService(processRemarkService);
        cssHandler.setProcessRemarkService(processRemarkService);
    }

    @Override
    public ProcessRemarkService getProcessRemarkService() {
        return processRemarkService;
    }

    @Override
    public void setMessageCode(String messageCode) {
        domHandler.setMessageCode(messageCode);
    }
    
    @Override 
    public String getPreProcessResult(String key, WebResource page) {
        return preProcessResultDataService.getPreProcessResultByKeyAndWebResource(key, page);
    }
    
    @Override
    public Map<String, CascadingStyleSheet> getStyleSheetMap() {
        return cssHandler.getStyleSheetMap();
    }

    @Override
    public Collection<StylesheetContent> getStyleSheetOnError() {
        return cssHandler.getStyleSheetOnError();
    }
    
    @Override
    @Deprecated
    public TestSolution checkChildNodeExistsRecursively(String childNodeName) {
        return domHandler.checkChildNodeExistsRecursively(childNodeName);
    }

    @Override
    @Deprecated
    public TestSolution checkContentNotEmpty() {
        return domHandler.checkContentNotEmpty();
    }

    @Override
    @Deprecated
    public TestSolution checkEachWithXpath(String expr) {
        return domHandler.checkEachWithXpath(expr);
    }
    
    @Override  
    @Deprecated
    public TestSolution checkTextContentAndAttributeValue(String attributeName,
            Collection<String> blacklist, Collection<String> whitelist) {
        return domHandler.checkTextContentAndAttributeValue(attributeName,
                blacklist, whitelist);
    }
    
    @Override
    @Deprecated
    public TestSolution checkTextContentValueLengthLower(int length,
            TestSolution defaultFailResult) {
        return domHandler.checkTextContentValueLengthLower(length,
                defaultFailResult);
    }

    @Override
    @Deprecated
    public TestSolution checkTextContentValueNotEmpty() {
        return domHandler.checkTextContentValueNotEmpty();
    }

    @Override
    @Deprecated
    public DOMHandler excludeNodesWithAttribute(String attributeName) {
        return domHandler.excludeNodesWithAttribute(attributeName);
    }

    @Override
    @Deprecated
    public SSPHandler excludeNodesWithChildNode(ArrayList<String> childNodeNames) {
        domHandler.excludeNodesWithChildNode(childNodeNames);
        return this;
    }

    @Override
    @Deprecated
    public SSPHandler excludeNodesWithChildNode(String childNodeName) {
        domHandler.excludeNodesWithChildNode(childNodeName);
        return this;
    }

    @Override
    @Deprecated
    public List<String> getAttributeValues(String attributeName) {
        return domHandler.getAttributeValues(attributeName);
    }
    
    @Override
    @Deprecated
    public List<String> getTextContentValues() {
        return domHandler.getTextContentValues();
    }
    
    @Override
    @Deprecated
    public SSPHandler keepNodesWithAttributeValueEquals(String attributeName,
            Collection<String> values) {
        domHandler.keepNodesWithAttributeValueEquals(attributeName, values);
        return this;
    }

    @Override
    @Deprecated
    public SSPHandler keepNodesWithAttributeValueNonEmpty(String attributeName) {
        domHandler.keepNodesWithAttributeValueNonEmpty(attributeName);
        return this;
    }

    @Override
    @Deprecated
    public SSPHandler keepNodesWithAttributeValueStartingWith(
            String attributeName, Collection<String> values) {
        domHandler.keepNodesWithAttributeValueStartingWith(attributeName,
                values);
        return this;
    }

    @Override
    @Deprecated
    public SSPHandler keepNodesWithAttributeValueStartingWith(
            String attributeName, String value) {
        domHandler.keepNodesWithAttributeValueStartingWith(attributeName, value);
        return this;
    }
    
    @Override
    @Deprecated
    public SSPHandler keepNodesWithoutChildNode(
            Collection<String> childNodeNames) {
        domHandler.keepNodesWithoutChildNode(childNodeNames);
        return this;
    }

    @Override
    @Deprecated
    public SSPHandler keepNodesWithoutChildNode(String childNodeName) {
        domHandler.keepNodesWithChildNode(childNodeName);
        return this;
    }
    
    @Override
    @Deprecated
    public SSPHandler selectAttributeByName(String attributeName) {
        domHandler.selectAttributeByName(attributeName);
        return this;
    }

    @Override
    @Deprecated
    public SSPHandler selectChildNodes(Collection<String> childNodeNames) {
        domHandler.selectChildNodes(childNodeNames);
        return this;
    }
    
    @Override
    @Deprecated
    public SSPHandler selectChildNodesRecursively(
            Collection<String> childNodeNames) {
        domHandler.selectChildNodesRecursively(childNodeNames);
        return this;
    }
    
    @Override
    @Deprecated
    public SSPHandler selectDocumentNodesWithAttribute(String attributeName) {
        domHandler.selectDocumentNodesWithAttribute(attributeName);
        return this;
    }
    
    @Override
    @Deprecated
    public TestSolution checkAttributeValueIsEmpty(String attributeName) {
        return domHandler.checkAttributeValueIsEmpty(attributeName);
    }

    @Override
    @Deprecated
    public String getMessageCode() {
        return domHandler.getMessageCode();
    }

    @Override
    @Deprecated
    public TestSolution checkNodeValue(Collection<String> blacklist,
            Collection<String> whitelist) {
        return domHandler.checkNodeValue(blacklist, whitelist);
    }
    
    @Override
    @Deprecated
    public TestSolution checkAttributeValueLengthLower(String attributeName,
            int length, TestSolution defaultFailResult) {
        return domHandler.checkAttributeValueLengthLower(attributeName, length,
                defaultFailResult);
    }

    @Override
    @Deprecated
    public TestSolution checkAttributeValueNotEmpty(String attributeName) {
        return domHandler.checkAttributeValueNotEmpty(attributeName);
    }

}