package org.opens.tanaguru.processing;

import org.opens.tanaguru.service.ProcessRemarkService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.opens.tanaguru.contentadapter.css.CSSOMDeclaration;
import org.opens.tanaguru.contentadapter.css.CSSOMRule;
import org.opens.tanaguru.entity.audit.Evidence;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.factory.audit.EvidenceElementFactory;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.entity.service.audit.EvidenceDataService;
import org.w3c.css.sac.ConditionalSelector;
import org.w3c.css.sac.DescendantSelector;
import org.w3c.css.sac.ElementSelector;
import org.w3c.css.sac.Selector;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.flute.parser.selectors.ClassConditionImpl;
import org.w3c.flute.parser.selectors.IdConditionImpl;

/**
 * 
 * @author jkowalczyk
 */
public class ProcessRemarkServiceImpl implements ProcessRemarkService {

    private static final String CSS_SELECTOR_EVIDENCE = "Css-Selector";
    private static final String CSS_FILENAME_EVIDENCE = "Css-Filename";
    private static final String START_COMMENT_OCCURENCE = "<!--";
    private static final String END_COMMENT_OCCURENCE = "-->";
    private XPath xpath = XPathFactory.newInstance().newXPath();
    Document document;

    public ProcessRemarkServiceImpl(ProcessRemarkFactory processRemarkFactory, SourceCodeRemarkFactory sourceCodeRemarkFactory, EvidenceElementFactory evidenceElementFactory, EvidenceDataService evidenceDataService) {
        super();
        this.processRemarkFactory = processRemarkFactory;
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
        this.evidenceElementFactory = evidenceElementFactory;
        this.evidenceDataService = evidenceDataService;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    protected Set<ProcessRemark> remarkSet;

    @Override
    public Collection<ProcessRemark> getRemarkList() {
        return this.remarkSet;
    }
    List<String> evidenceElementList = new ArrayList<String>();

    @Override
    public void addEvidenceElement(String element) {
        if (!evidenceElementList.contains(element)) {
            evidenceElementList.add(element);
        }
    }

    @Override
    public void setEvidenceElementList(Collection<String> element) {
        evidenceElementList.addAll(element);
    }
    protected ProcessRemarkFactory processRemarkFactory;

    public ProcessRemarkFactory getProcessRemarkFactory() {
        return processRemarkFactory;
    }

    public void setProcessRemarkFactory(ProcessRemarkFactory processRemarkFactory) {
        this.processRemarkFactory = processRemarkFactory;
    }
    protected SourceCodeRemarkFactory sourceCodeRemarkFactory;

    public SourceCodeRemarkFactory getSourceCodeRemarkFactory() {
        return sourceCodeRemarkFactory;
    }

    public void setSourceCodeRemarkFactory(SourceCodeRemarkFactory sourceCodeRemarkFactory) {
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
    }
    protected EvidenceElementFactory evidenceElementFactory;

    @Override
    public EvidenceElementFactory getEvidenceElementFactory() {
        return evidenceElementFactory;
    }

    public void setEvidenceElementFactory(EvidenceElementFactory evidenceElementFactory) {
        this.evidenceElementFactory = evidenceElementFactory;
    }
    protected EvidenceDataService evidenceDataService;

    @Override
    public EvidenceDataService getEvidenceDataService() {
        return evidenceDataService;
    }

    public void setEvidenceDataService(EvidenceDataService evidenceDataService) {
        this.evidenceDataService = evidenceDataService;
    }
    protected Map<Integer, String> sourceCodeWithLine =
            new TreeMap<Integer, String>();
    /**
     * Local map of evidence to avoid multiple access to database
     */
    private Map<String, Evidence> evidenceMap =
            new HashMap<String, Evidence>();

    @Override
    public void initializeService(Document document, String adaptedContent) {
        if (document != null) {
            this.document = document;
        }
        if (adaptedContent != null) {
            initializeSourceCodeMap(adaptedContent);
        }
        initialize();
    }

    @Override
    public void initializeService() {
        initialize();
    }

    private void initialize() {
        remarkSet = new LinkedHashSet<ProcessRemark>();
        evidenceElementList = new ArrayList<String>();
    }

    @Override
    public ProcessRemark createProcessRemark(
            TestSolution processResult,
            String messageCode) {
        return processRemarkFactory.create(processResult, messageCode);
    }

    @Override
    public void addProcessRemark(TestSolution processResult, String messageCode) {
        remarkSet.add(processRemarkFactory.create(processResult, messageCode));
    }

    @Override
    public void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, String attributeName) {
        remarkSet.add(createSourceCodeRemark(
                processResult,
                node,
                messageCode,
                attributeName));
    }

    @Override
    public void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, List<EvidenceElement> evidenceElementList) {
        SourceCodeRemark remark = sourceCodeRemarkFactory.create();
        remark.setIssue(processResult);
        remark.setMessageCode(messageCode);
        if (node != null) {
            remark.setLineNumber(searchNodeLineNumber(node));
        } else {
            remark.setLineNumber(-1);
        }
        for (EvidenceElement element : evidenceElementList) {
            remark.addElement(element);
            element.setProcessRemark(remark);
        }
        remarkSet.add(remark);
    }

    @Override
    public void addCssCodeRemark(TestSolution processResult,
            CSSOMRule rule, String messageCode, String attrName, String fileName) {// XXX
        SourceCodeRemark remark = sourceCodeRemarkFactory.create();
        remark.setIssue(processResult);
        remark.setMessageCode(messageCode);
        // This a fake sourceCode Remark, the line number cannot be found
        // we use a sourceCodeRemark here to add evidence elements
        remark.setLineNumber(-1);
        EvidenceElement evidenceElement = evidenceElementFactory.create();
        evidenceElement.setProcessRemark(remark);
        evidenceElement.setValue(attrName);
        evidenceElement.setEvidence(getEvidence(DEFAULT_EVIDENCE));
        remark.addElement(evidenceElement);
        try {
            String selectorValue = computeSelector(rule);
            if (selectorValue != null) {
                EvidenceElement cssSelectorEvidenceElement = evidenceElementFactory.create();
                cssSelectorEvidenceElement.setProcessRemark(remark);
                cssSelectorEvidenceElement.setValue(selectorValue);
                cssSelectorEvidenceElement.setEvidence(getEvidence(CSS_SELECTOR_EVIDENCE));
                remark.addElement(cssSelectorEvidenceElement);
            }
            if (fileName != null) {
                EvidenceElement fileNameEvidenceElement = evidenceElementFactory.create();
                fileNameEvidenceElement.setProcessRemark(remark);
                fileNameEvidenceElement.setValue(fileName);
                fileNameEvidenceElement.setEvidence(getEvidence(CSS_FILENAME_EVIDENCE));
                remark.addElement(fileNameEvidenceElement);
            }
        } catch (ClassCastException ex) {
            Logger.getLogger(ProcessRemarkServiceImpl.class.getName()).log(Level.WARNING, null, ex);
        }
        remarkSet.add(remark);
    }

    @Override
    public void addSourceCodeRemark(
            TestSolution processResult,
            Node node,
            String messageCode,
            String elementType,
            String elementName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @param node
     * @return
     */
    private int searchNodeLineNumber(Node node) {
        int nodeIndex = getNodeIndex(node);
        int lineNumber = 0;
        boolean found = false;
        boolean isWithinComment = false;
        Iterator<Integer> iter = sourceCodeWithLine.keySet().iterator();
        String codeLine;
        while (iter.hasNext() && !found) {
            int myLineNumber = iter.next();
            int index = 0;
            while (index != -1) {
                codeLine = sourceCodeWithLine.get(myLineNumber).toLowerCase();
                int characterPositionOri = index;
                index = codeLine.indexOf("<" + node.getNodeName().toLowerCase() + ">",
                        index);
                if (index == -1) {
                    index = codeLine.indexOf("<" + node.getNodeName().toLowerCase() + " ",
                            characterPositionOri);
                }
                int startCommentIndex = codeLine.indexOf(
                        START_COMMENT_OCCURENCE, characterPositionOri);
                int endCommentIndex = codeLine.indexOf(
                        END_COMMENT_OCCURENCE, characterPositionOri);
                if (index != -1) { // if an occurence of the tag is found
                    if (!isWithinComment
                            && !(startCommentIndex != -1 && index > startCommentIndex)
                            && !(endCommentIndex != -1 && index < endCommentIndex)) { // if a comment is not currently opened or a comment is found on the current line and the occurence is not within
                        if (nodeIndex == 0) {
                            found = true;
                            lineNumber = myLineNumber;
                            break;
                        }
                        nodeIndex--;
                    }
                    index += node.getNodeName().length();
                }
                // if a "start comment" occurence is found on the line,
                // the boolean isWithinComment is set to true. Thus, while a
                // "end comment" is not found, all the occurences of the
                // wanted node will be ignored
                if (!isWithinComment && startCommentIndex != -1 && endCommentIndex == -1) {
                    isWithinComment = true;
                } else if (isWithinComment && endCommentIndex != -1 && startCommentIndex < endCommentIndex) {
                    isWithinComment = false;
                }
            }
        }
        return lineNumber;
    }

    /**
     * This methods search the line where the current node is present in
     * the source code
     * @param node
     * @return
     */
    private int getNodeIndex(Node node) {
        try {
            XPathExpression xPathExpression = xpath.compile("//" + node.getNodeName().toUpperCase());
            Object result = xPathExpression.evaluate(document,
                    XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node current = nodeList.item(i);
                if (current.equals(node)) {
                    return i;
                }
            }
        } catch (XPathExpressionException ex) {
            Logger.getLogger(ProcessRemarkServiceImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
            throw new RuntimeException(ex);
        }
        return -1;
    }

    /**
     * 
     * @param adaptedContent
     */
    private void initializeSourceCodeMap(String adaptedContent) {
        sourceCodeWithLine.clear();
        int lineNumber = 1;
        StringReader sr = new StringReader(adaptedContent);
        BufferedReader br = new BufferedReader(sr);
        String line;
        try {
            while ((line = br.readLine()) != null) {
                sourceCodeWithLine.put(lineNumber, line);
                lineNumber++;
            }
        } catch (IOException ex) {
            Logger.getLogger(ProcessRemarkServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public EvidenceElement getEvidenceElement(String evidenceCode, String evidenceValue) {
        EvidenceElement evidenceElement = evidenceElementFactory.create();
        evidenceElement.setValue(evidenceValue);
        evidenceElement.setEvidence(getEvidence(evidenceCode));
        return evidenceElement;
    }

    @Override
    public SourceCodeRemark createSourceCodeRemark(
            TestSolution processResult,
            Node node,
            String messageCode,
            String elementName) {
        SourceCodeRemark remark = sourceCodeRemarkFactory.create();
        remark.setIssue(processResult);
        remark.setMessageCode(messageCode);

        remark.setLineNumber(searchNodeLineNumber(node));
        EvidenceElement evidenceElement = evidenceElementFactory.create();
        evidenceElement.setProcessRemark(remark);
        evidenceElement.setValue(elementName);
        evidenceElement.setEvidence(getEvidence(DEFAULT_EVIDENCE));
        for (String attr : evidenceElementList) {
            if (node.getAttributes().getNamedItem(attr) != null) {
                EvidenceElement evidenceElementSup = evidenceElementFactory.create();
                evidenceElementSup.setProcessRemark(remark);
                evidenceElementSup.setValue(node.getAttributes().getNamedItem(attr).getNodeValue());
                evidenceElementSup.setEvidence(getEvidence(attr));
                remark.addElement(evidenceElementSup);
            }
        }
        remark.addElement(evidenceElement);
        return remark;
    }

    @Override
    public void addConsolidationRemark(
            TestSolution processResult,
            String messageCode,
            String value,
            String url) {
        remarkSet.add(createConsolidationRemark(
                processResult,
                messageCode,
                value,
                url));
    }

    @Override
    public void addProcessRemark(
            TestSolution processResult,
            String messageCode,
            Collection<EvidenceElement> evidenceElementList) {
        remarkSet.add(createProcessRemark(
                processResult,
                messageCode,
                evidenceElementList));
    }

    @Override
    public ProcessRemark createProcessRemark(
            TestSolution processResult,
            String messageCode,
            Collection<EvidenceElement> evidenceElementList) {
        ProcessRemark remark = processRemarkFactory.create();
        remark.setIssue(processResult);
        remark.setMessageCode(messageCode);
        for (EvidenceElement element : evidenceElementList) {
            remark.addElement(element);
            element.setProcessRemark(remark);
        }
        return remark;
    }

    @Override
    public ProcessRemark createConsolidationRemark(
            TestSolution processResult,
            String messageCode,
            String value,
            String url) {
        ProcessRemark remark = processRemarkFactory.create();
        remark.setIssue(processResult);
        remark.setMessageCode(messageCode);

        if (value != null) {
            EvidenceElement evidenceElement = evidenceElementFactory.create();
            evidenceElement.setProcessRemark(remark);
            evidenceElement.setValue(value);
            evidenceElement.setEvidence(getEvidence(DEFAULT_EVIDENCE));
            remark.addElement(evidenceElement);
        }
        if (url != null) {
            EvidenceElement evidenceElement = evidenceElementFactory.create();
            evidenceElement.setProcessRemark(remark);
            evidenceElement.setValue(url);
            evidenceElement.setEvidence(getEvidence(URL_EVIDENCE));
            remark.addElement(evidenceElement);
        }
        return remark;
    }

    /**
     * Return an evidence instance for a given code. This method avoids multiple
     * access to mysql databases, by maintaining a map. 
     * @param code
     * @return
     */
    public Evidence getEvidence(String code) {
        if (evidenceMap.containsKey(code)) {
            return evidenceMap.get(code);
        } else {
            Evidence evidence = evidenceDataService.findByCode(code);
            evidenceMap.put(code, evidence);
            return evidence;
        }
    }

    private String computeSelector(CSSOMRule rule) {
        StringBuilder selectorValue = new StringBuilder();
        Selector selector = rule.getSelectors().get(0).getSelector();
        CSSOMDeclaration cssomDeclaration = null;
        if (rule.getSelectors().get(0).getOwnerDeclaration() != null) {
            cssomDeclaration = rule.getSelectors().get(0).getOwnerDeclaration().get(0);
        }
        if (selector instanceof DescendantSelector) {
            selectorValue.append(computeDescendantSelector((DescendantSelector)selector));
        } else if  (selector instanceof ConditionalSelector) {
            selectorValue.append(computeConditionalSelector((ConditionalSelector)selector));
        } else {
            selectorValue.append(computeElementSelector(selector, cssomDeclaration));
        }
        return selectorValue.toString();
    }

    private String computeDescendantSelector(DescendantSelector ds) {
        StringBuilder selectorValue = new StringBuilder();
        if (ds != null) {
            System.out.println(ds.getAncestorSelector());
            System.out.println(ds.getSimpleSelector());
            if (ds.getAncestorSelector() != null && ds.getAncestorSelector() instanceof DescendantSelector) {
                selectorValue.append(computeDescendantSelector((DescendantSelector)ds.getAncestorSelector()));
            } else if (ds.getAncestorSelector() != null && ds.getAncestorSelector() instanceof ConditionalSelector) {
                selectorValue.append(computeConditionalSelector((ConditionalSelector)ds.getAncestorSelector()));
            }
            if (ds.getSimpleSelector() != null && ds.getSimpleSelector() instanceof ConditionalSelector) {
                selectorValue.append(computeConditionalSelector((ConditionalSelector)ds.getSimpleSelector()));
            }
            else if(ds.getSimpleSelector() != null && ds.getSimpleSelector() instanceof ElementSelector) {
                selectorValue.append(computeElementSelector(ds.getSimpleSelector(), null));
            } 
        }
        return selectorValue.toString();
    }

    private String computeConditionalSelector(ConditionalSelector cs) {
         StringBuilder selectorValue = new StringBuilder();
        if (cs.getCondition() instanceof IdConditionImpl) {
            selectorValue.append("#");
            selectorValue.append(((IdConditionImpl)cs.getCondition()).getValue());
            selectorValue.append(' ');
        } else if (cs.getCondition() instanceof ClassConditionImpl) {
            selectorValue.append(".");
            selectorValue.append(((ClassConditionImpl)cs.getCondition()).getValue());
            selectorValue.append(' ');
        }
         return selectorValue.toString();
    }

    private String computeElementSelector(Selector es, CSSOMDeclaration cssomDeclaration) {
         StringBuilder selectorValue = new StringBuilder();
        if (es instanceof ElementSelector) {
            selectorValue.append(' ');
            selectorValue.append(((ElementSelector)es).getLocalName());
            selectorValue.append(' ');
        }
         return selectorValue.toString();
    }

}