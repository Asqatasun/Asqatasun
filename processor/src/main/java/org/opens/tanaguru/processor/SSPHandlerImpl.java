package org.opens.tanaguru.processor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.httpclient.URIException;
import org.archive.net.UURIFactory;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ImageContent;
import org.opens.tanaguru.service.ProcessRemarkService;

import org.w3c.dom.Node;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.ruleimplementation.RuleHelper;
import org.opens.tanaguru.service.NomenclatureLoaderService;

public class SSPHandlerImpl implements SSPHandler {

    protected CSSHandler cssHandler;
    protected DOMHandler domHandler;
    protected JSHandler jsHandler;
    protected SSP ssp;
    protected NomenclatureLoaderService nomenclatureLoaderService;
    protected SourceCodeRemarkFactory sourceCodeRemarkFactory;
    protected ProcessRemarkFactory processRemarkFactory;
    protected String selectionExpression;
    protected List<ProcessRemark> remarkList;
    protected Map<String, BufferedImage> imageMap;
    private URLIdentifier urlIdentifier;
    private Set<ImageContent> imageOnErrorSet;
    private ProcessRemarkService processRemarkService;

    SSPHandlerImpl(NomenclatureLoaderService nomenclatureLoaderService, URLIdentifier urlIdentifier, CSSHandler create, DOMHandler create0, ProcessRemarkService processRemarkService) {
        super();
        this.nomenclatureLoaderService = nomenclatureLoaderService;
        this.urlIdentifier = urlIdentifier;
        this.cssHandler = create;
        this.domHandler = create0;
        this.processRemarkService = processRemarkService;
    }

    @Override
    public SSPHandler beginSelection() {
        domHandler.beginSelection();
        if (cssHandler != null) {
            cssHandler.beginSelection();
        }
        if (jsHandler != null) {
            jsHandler.beginSelection();
        }

        selectionExpression = null;
        remarkList = new ArrayList<ProcessRemark>();

        URL src;
        try {
            src = new URL(ssp.getURI());
            urlIdentifier.setUrl(src);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SSPHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }

    @Override
    public TestSolution checkAttributeExists(String attributeName) {
        return domHandler.checkAttributeExists(attributeName);
    }

    @Override
    public TestSolution checkAttributeValueExpression(String attributeName,
            String regex) {
        return domHandler.checkAttributeValueExpression(attributeName, regex);
    }

    @Override
    public TestSolution checkAttributeValueLengthLower(String attributeName,
            int length, TestSolution defaultFailResult) {
        return domHandler.checkAttributeValueLengthLower(attributeName, length,
                defaultFailResult);
    }

    @Override
    public TestSolution checkAttributeValueNotEmpty(String attributeName) {
        return domHandler.checkAttributeValueNotEmpty(attributeName);
    }

    @Override
    public TestSolution checkAttributeValueIsEmpty(String attributeName) {
        return domHandler.checkAttributeValueIsEmpty(attributeName);
    }

    @Override
    public TestSolution checkChildNodeExists(String childNodeName) {
        return domHandler.checkChildNodeExists(childNodeName);
    }

    @Override
    public TestSolution checkChildNodeExistsRecursively(String childNodeName) {
        return domHandler.checkChildNodeExistsRecursively(childNodeName);
    }

    @Override
    public TestSolution checkContentNotEmpty() {
        return domHandler.checkContentNotEmpty();
    }

    @Override
    public TestSolution checkEachWithXpath(String expr) {
        return domHandler.checkEachWithXpath(expr);
    }

    @Override
    public TestSolution checkNodeValue(Collection<String> blacklist,
            Collection<String> whitelist) {
        return domHandler.checkNodeValue(blacklist, whitelist);
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
    public TestSolution checkRelativeUnitExists(Collection<Integer> blackList) {
        if (cssHandler != null) {
            return cssHandler.checkRelativeUnitExists(blackList);
        } else {
            return TestSolution.NOT_APPLICABLE;
        }
    }

    @Override
    public TestSolution checkTextContentAndAttributeValue(String attributeName,
            Collection<String> blacklist, Collection<String> whitelist) {
        return domHandler.checkTextContentAndAttributeValue(attributeName,
                blacklist, whitelist);
    }

    @Override
    public TestSolution checkTextContentValue(Collection<String> blacklist,
            Collection<String> whitelist) {
        return domHandler.checkTextContentValue(blacklist, whitelist);
    }

    @Override
    public TestSolution checkTextContentValueLengthLower(int length,
            TestSolution defaultFailResult) {
        return domHandler.checkTextContentValueLengthLower(length,
                defaultFailResult);
    }

    @Override
    public TestSolution checkTextContentValueNotEmpty() {
        return domHandler.checkTextContentValueNotEmpty();
    }

    @Override
    public DOMHandler excludeNodesWithAttribute(String attributeName) {
        return domHandler.excludeNodesWithAttribute(attributeName);
    }

    @Override
    public SSPHandler excludeNodesWithChildNode(ArrayList<String> childNodeNames) {
        domHandler.excludeNodesWithChildNode(childNodeNames);
        return this;
    }

    @Override
    public SSPHandler excludeNodesWithChildNode(String childNodeName) {
        domHandler.excludeNodesWithChildNode(childNodeName);
        return this;
    }

    @Override
    public List<String> getAttributeValues(String attributeName) {
        return domHandler.getAttributeValues(attributeName);
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
    public SSP getSSP() {
        return ssp;
    }

    @Override
    public List<String> getTextContentValues() {
        return domHandler.getTextContentValues();
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
    public SSPHandler keepNodesWithAttributeValueEquals(String attributeName,
            Collection<String> values) {
        domHandler.keepNodesWithAttributeValueEquals(attributeName, values);
        return this;
    }

    @Override
    public SSPHandler keepNodesWithAttributeValueNonEmpty(String attributeName) {
        domHandler.keepNodesWithAttributeValueNonEmpty(attributeName);
        return this;
    }

    @Override
    public SSPHandler keepNodesWithAttributeValueStartingWith(
            String attributeName, Collection<String> values) {
        domHandler.keepNodesWithAttributeValueStartingWith(attributeName,
                values);
        return this;
    }

    @Override
    public SSPHandler keepNodesWithAttributeValueStartingWith(
            String attributeName, String value) {
        domHandler.keepNodesWithAttributeValueStartingWith(attributeName, value);
        return this;
    }

    @Override
    public SSPHandler keepNodesWithChildNode(String childNodeName) {
        domHandler.keepNodesWithChildNode(childNodeName);
        return this;
    }

    @Override
    public SSPHandler keepNodesWithoutChildNode(
            Collection<String> childNodeNames) {
        domHandler.keepNodesWithoutChildNode(childNodeNames);
        return this;
    }

    @Override
    public SSPHandler keepNodesWithoutChildNode(String childNodeName) {
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
    public SSPHandler selectAllRules() {
        if (cssHandler != null) {
            cssHandler.selectAllRules();
        }
        return this;
    }

    @Override
    public SSPHandler selectAttributeByName(String attributeName) {
        domHandler.selectAttributeByName(attributeName);
        return this;
    }

    @Override
    public SSPHandler selectChildNodes(Collection<String> childNodeNames) {
        domHandler.selectChildNodes(childNodeNames);
        return this;
    }

    @Override
    public SSPHandler selectChildNodes(String childNodeName) {
        domHandler.selectChildNodes(childNodeName);
        return this;
    }

    @Override
    public SSPHandler selectChildNodesRecursively(
            Collection<String> childNodeNames) {
        domHandler.selectChildNodesRecursively(childNodeNames);
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

    @Override
    public SSPHandler selectDocumentNodesWithAttribute(String attributeName) {
        domHandler.selectDocumentNodesWithAttribute(attributeName);
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

    public void setCSSHandler(CSSHandler cssHandler) {
        this.cssHandler = cssHandler;
    }

    public void setDOMHandler(DOMHandler domHandler) {
        this.domHandler = domHandler;
    }

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

    public void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureLoaderService) {
        this.nomenclatureLoaderService = nomenclatureLoaderService;
    }

    public void setUrlIdentifier(URLIdentifier urlIdentifier) {
        this.urlIdentifier = urlIdentifier;
    }

    /**
     * http://www.ibm.com/developerworks/library/x-javaxpathapi.html
     *
     * @param expr
     * @return
     */
    @Override
    public SSPHandler domXPathSelectNodeSet(String expression) {
        domHandler.xPathSelectNodeSet(expression);
        selectionExpression = expression;
        return this;
    }

    @Override
    public TestSolution domCheckNodeValueInNomenclature(String nomenclatureCode, String errorMessage) {
        Set<TestSolution> resultSet = new HashSet<TestSolution>();

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

                ProcessRemark remark = processRemarkFactory.create();
                remark.setIssue(TestSolution.FAILED);
                remark.setMessageCode(errorMessage);
                remark.setSelectionExpression(selectionExpression);
                remark.selectedElement(nodeValue);

                remarkList.add(remark);
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
    public SSPHandler keepRulesWithMedia(Collection<String> mediaNames) {
        if (cssHandler != null) {
            cssHandler.keepRulesWithMedia(mediaNames);
        }
        return this;
    }

    @Override
    public BufferedImage getImageFromURL(String url) {
        try {
            return imageMap.get(UURIFactory.getInstance(urlIdentifier.resolve(url).toExternalForm()).toString());
        } catch (URIException ex) {
            Logger.getLogger(SSPHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    private void initializeImageMap() {
        if (imageMap == null) {
            imageMap = new HashMap<String, BufferedImage>();
        }
        if (imageOnErrorSet == null) {
            imageOnErrorSet = new HashSet<ImageContent>();
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
                        Logger.getLogger(SSPHandlerImpl.class.getName()).
                                log(Level.SEVERE, null, ex);
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
    public int getCssSelectorNumber() {
        return cssHandler.getCssSelectorNumber();
    }

    @Override
    public String getMessageCode() {
        return domHandler.getMessageCode();
    }

    @Override
    public void setMessageCode(String messageCode) {
        domHandler.setMessageCode(messageCode);
    }
}
