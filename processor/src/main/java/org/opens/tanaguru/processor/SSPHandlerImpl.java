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
    Set<ImageContent> imageOnErrorSet;

    public SSPHandlerImpl() {
        super();
    }

    public SSPHandler beginSelection() {
        domHandler.beginSelection();
        if (cssHandler != null) 
            cssHandler.beginSelection();
        if (jsHandler!=null)
            jsHandler.beginSelection();

        selectionExpression = null;
        remarkList = new ArrayList<ProcessRemark>();

        URL src;
        try {
            src = new URL(ssp.getURI());
            urlIdentifier.setUrl(src);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SSPHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

//        XXX R2-implémenter avec la mise en commun des listes de remarques
//        domHandler.setRemarkList(remarkList);
//        cssHandler.setRemarkList(remarkList);
//        jsHandler.setRemarkList(remarkList);

        return this;
    }

    public TestSolution checkAttributeExists(String attributeName) {
        return domHandler.checkAttributeExists(attributeName);
    }

    public TestSolution checkAttributeValueExpression(String attributeName,
            String regex) {
        return domHandler.checkAttributeValueExpression(attributeName, regex);
    }

    public TestSolution checkAttributeValueLengthLower(String attributeName,
            int length, TestSolution defaultFailResult) {
        return domHandler.checkAttributeValueLengthLower(attributeName, length,
                defaultFailResult);
    }

    public TestSolution checkAttributeValueNotEmpty(String attributeName) {
        return domHandler.checkAttributeValueNotEmpty(attributeName);
    }

    public TestSolution checkAttributeValueIsEmpty(String attributeName) {
        return domHandler.checkAttributeValueIsEmpty(attributeName);
    }

    public TestSolution checkChildNodeExists(String childNodeName) {
        return domHandler.checkChildNodeExists(childNodeName);
    }

    public TestSolution checkChildNodeExistsRecursively(String childNodeName) {
        return domHandler.checkChildNodeExistsRecursively(childNodeName);
    }

    public TestSolution checkContentNotEmpty() {
        return domHandler.checkContentNotEmpty();
    }

    public TestSolution checkEachWithXpath(String expr) {
        return domHandler.checkEachWithXpath(expr);
    }

    public TestSolution checkNodeValue(Collection<String> blacklist,
            Collection<String> whitelist) {
        return domHandler.checkNodeValue(blacklist, whitelist);
    }

    public TestSolution checkRelativeUnitExists(Collection<Integer> blackList) {
        if (cssHandler != null)
            return cssHandler.checkRelativeUnitExists(blackList);
        else return TestSolution.NOT_APPLICABLE;
    }

    public TestSolution checkTextContentAndAttributeValue(String attributeName,
            Collection<String> blacklist, Collection<String> whitelist) {
        return domHandler.checkTextContentAndAttributeValue(attributeName,
                blacklist, whitelist);
    }

    public TestSolution checkTextContentValue(Collection<String> blacklist,
            Collection<String> whitelist) {
        return domHandler.checkTextContentValue(blacklist, whitelist);
    }

    public TestSolution checkTextContentValueLengthLower(int length,
            TestSolution defaultFailResult) {
        return domHandler.checkTextContentValueLengthLower(length,
                defaultFailResult);
    }

    public TestSolution checkTextContentValueNotEmpty() {
        return domHandler.checkTextContentValueNotEmpty();
    }

    public DOMHandler excludeNodesWithAttribute(String attributeName) {
        return domHandler.excludeNodesWithAttribute(attributeName);
    }

    public SSPHandler excludeNodesWithChildNode(ArrayList<String> childNodeNames) {
        domHandler.excludeNodesWithChildNode(childNodeNames);
        return this;
    }

    public SSPHandler excludeNodesWithChildNode(String childNodeName) {
        domHandler.excludeNodesWithChildNode(childNodeName);
        return this;
    }

    public List<String> getAttributeValues(String attributeName) {
        return domHandler.getAttributeValues(attributeName);
    }

    public WebResource getPage() {
        return domHandler.getPage();
    }

    public List<ProcessRemark> getRemarkList() {// XXX Ré-implémenter avec la mise en commun des listes de remarques
        List<ProcessRemark> allRemarkList = new ArrayList<ProcessRemark>();

        allRemarkList.addAll(remarkList);
        if (domHandler != null)
            allRemarkList.addAll(domHandler.getRemarkList());
        if (cssHandler != null)
            allRemarkList.addAll(cssHandler.getRemarkList());
        if (jsHandler!=null)
            allRemarkList.addAll(jsHandler.getRemarkList());

        return allRemarkList;
    }

    public List<Node> getSelectedElementList() {
        return domHandler.getSelectedElementList();
    }

    public SSP getSSP() {
        return ssp;
    }

    public List<String> getTextContentValues() {
        return domHandler.getTextContentValues();
    }

    public boolean isSelectedElementsEmpty() {
        return domHandler.isSelectedElementsEmpty();
    }

    public SSPHandler keepNodesWithAttribute(String attributeName) {
        domHandler.keepNodesWithAttribute(attributeName);
        return this;
    }

    public SSPHandler keepNodesWithAttributeValueEquals(String attributeName,
            Collection<String> values) {
        domHandler.keepNodesWithAttributeValueEquals(attributeName, values);
        return this;
    }

    public SSPHandler keepNodesWithAttributeValueNonEmpty(String attributeName) {
        domHandler.keepNodesWithAttributeValueNonEmpty(attributeName);
        return this;
    }

    public SSPHandler keepNodesWithAttributeValueStartingWith(
            String attributeName, Collection<String> values) {
        domHandler.keepNodesWithAttributeValueStartingWith(attributeName,
                values);
        return this;
    }

    public SSPHandler keepNodesWithAttributeValueStartingWith(
            String attributeName, String value) {
        domHandler.keepNodesWithAttributeValueStartingWith(attributeName, value);
        return this;
    }

    public SSPHandler keepNodesWithChildNode(String childNodeName) {
        domHandler.keepNodesWithChildNode(childNodeName);
        return this;
    }

    public SSPHandler keepNodesWithoutChildNode(
            Collection<String> childNodeNames) {
        domHandler.keepNodesWithoutChildNode(childNodeNames);
        return this;
    }

    public SSPHandler keepNodesWithoutChildNode(String childNodeName) {
        domHandler.keepNodesWithChildNode(childNodeName);
        return this;
    }

    public SSPHandler selectAllJS() {
        if (jsHandler != null)
            jsHandler.selectAllJS();
        return this;
    }

    public SSPHandler selectAllRules() {
        if (cssHandler != null)
            cssHandler.selectAllRules();
        return this;
    }

    public SSPHandler selectAttributeByName(String attributeName) {
        domHandler.selectAttributeByName(attributeName);
        return this;
    }

    public SSPHandler selectChildNodes(Collection<String> childNodeNames) {
        domHandler.selectChildNodes(childNodeNames);
        return this;
    }

    public SSPHandler selectChildNodes(String childNodeName) {
        domHandler.selectChildNodes(childNodeName);
        return this;
    }

    public SSPHandler selectChildNodesRecursively(
            Collection<String> childNodeNames) {
        domHandler.selectChildNodesRecursively(childNodeNames);
        return this;
    }

    public SSPHandler selectChildNodesRecursively(String childNodeName) {
        domHandler.selectChildNodesRecursively(childNodeName);
        return this;
    }

    public SSPHandler selectDocumentNodes(Collection<String> nodeNames) {
        domHandler.selectDocumentNodes(nodeNames);
        return this;
    }

    public SSPHandler selectDocumentNodes(String nodeName) {
        domHandler.selectDocumentNodes(nodeName);
        return this;
    }

    public SSPHandler selectDocumentNodesWithAttribute(String attributeName) {
        domHandler.selectDocumentNodesWithAttribute(attributeName);
        return this;
    }

    public SSPHandler selectExternalJS() {
        if (jsHandler!=null)
            jsHandler.selectExternalJS();
        return this;
    }

    public SSPHandler selectInlineJS() {
        if (jsHandler!=null)
            jsHandler.selectInlineJS();
        return this;
    }

    public SSPHandler selectLocalJS() {
        if (jsHandler!=null)
            jsHandler.selectLocalJS();
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

    public void setSelectedElementList(List<Node> selectedElementList) {// XXX Cette méthode doit-elle être rester visible de l'extérieur?
        domHandler.setSelectedElementList(selectedElementList);
    }

    public void setSSP(SSP ssp) {
        this.ssp = ssp;
        domHandler.setSSP(ssp);
        if (cssHandler!=null)
            cssHandler.setSSP(ssp);
        if (jsHandler!=null)
            jsHandler.setSSP(ssp);
        initializeImageMap();
    }

    public void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureLoaderService) {
        this.nomenclatureLoaderService = nomenclatureLoaderService;
    }

    public void setProcessRemarkFactory(ProcessRemarkFactory processRemarkFactory) {
        this.processRemarkFactory = processRemarkFactory;
    }

    public void setSourceCodeRemarkFactory(SourceCodeRemarkFactory sourceCodeRemarkFactory) {
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
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
    public SSPHandler domXPathSelectNodeSet(String expression) {
        domHandler.xPathSelectNodeSet(expression);
        selectionExpression = expression;
        return this;
    }

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

    /**
     * 
     * @param processResult
     * @param node
     * @param messageCode
     * @param attributeName
     */
    public void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, String attributeName) {
        domHandler.addSourceCodeRemark(processResult, node, messageCode, attributeName);
    }

    @Override
    public SSPHandler keepRulesWithMedia(Collection<String> mediaNames) {
        if (cssHandler != null)
            cssHandler.keepRulesWithMedia(mediaNames);
        return this;
    }

    private void initializeImageMap(){
        if (imageMap==null){
            imageMap = new HashMap<String, BufferedImage>();
        }
        if (imageOnErrorSet==null){
            imageOnErrorSet = new HashSet<ImageContent>();
        }
        imageMap.clear();
        for(RelatedContent relatedContent : ssp.getRelatedContentSet()){
            if (relatedContent instanceof ImageContent){

                BufferedImage image;
                if (((ImageContent) relatedContent).getHttpStatusCode()!= 200 ||
                        ((ImageContent) relatedContent).getContent()==null){
                    imageOnErrorSet.add((ImageContent)relatedContent);
                } else {
                    try {
                        image = ImageIO.read(new ByteArrayInputStream(
                                ((ImageContent) relatedContent).getContent()));
                        imageMap.put(((Content)relatedContent).getURI(), image);
                    } catch (IOException ex) {
                        Logger.getLogger(SSPHandlerImpl.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    @Override
    public BufferedImage getImageFromURL(String url) {
        try {
            return imageMap.get(UURIFactory.getInstance(urlIdentifier.resolve(url)
                    .toExternalForm()).toString());
        } catch (URIException ex) {
            Logger.getLogger(SSPHandlerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public TestSolution checkAttributePertinence(
            String attributeName,
            Collection<String> blackList,
            Collection<String> attributeToCompareWithList,
            String sourceCodeRemark) {
        return domHandler.checkAttributePertinence(
                attributeName,
                blackList,
                attributeToCompareWithList,
                sourceCodeRemark);
    }

}
