package org.opens.tanaguru.service;


import java.util.List;
import org.opens.tanaguru.contentadapter.css.CSSOMRule;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.factory.audit.EvidenceElementFactory;
import org.opens.tanaguru.entity.service.audit.EvidenceDataService;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * 
 * @author jkowalczyk
 */
public interface ProcessRemarkService {

    public static final String DEFAULT_EVIDENCE = "Element-Name";

    /**
     *
     * @param processResult
     * @param node
     * @param messageCode
     * @param elementName
     */
    public void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, String elementName);

    /**
     *
     * @param processResult
     * @param node
     * @param messageCode
     * @param elementType
     * @param elementName
     */
    public void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, String elementType, String elementName);

    /**
     *
     * @param processResult
     * @param node
     * @param messageCode
     * @param evidenceElementList
     */
    public void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, List<EvidenceElement> evidenceElementList);

    /**
     *
     * @param processResult
     * @param rule
     * @param messageCode
     * @param attrName
     */
    public void addCssCodeRemark(TestSolution processResult,
            CSSOMRule rule, String messageCode, String attrName);

    /**
     *
     * @return
     */
    public List<ProcessRemark> getRemarkList();

    /**
     *
     * @param element
     */
    public void addEvidenceElement (String element);

    /**
     *
     * @param element
     */
    public void setEvidenceElementList (List<String> element);

    /**
     *
     * @param document
     * @param adaptedContent
     */
    public void initializeService (Document document, String adaptedContent);

    /**
     *
     * @param processResult
     * @param messageCode
     */
    public void addProcessRemark(TestSolution processResult, String messageCode);

    /**
     *
     * @param processResult
     * @param messageCode
     * @return
     */
    public ProcessRemark createProcessRemark(TestSolution processResult, String messageCode);


    /**
     * 
     * @param processResult
     * @param node
     * @param messageCode
     * @param elementName
     * @return
     */
    public SourceCodeRemark createSourceCodeRemark(TestSolution processResult,
            Node node, String messageCode, String elementName);

    /**
     *
     * @return
     */
    public EvidenceElementFactory getEvidenceElementFactory();

    /**
     *
     * @return
     */
    public EvidenceDataService getEvidenceDataService();

    /**
     *
     * @param evidenceCode
     * @param evidenceValue
     * @return
     */
    public EvidenceElement getEvidenceElement(String evidenceCode, String evidenceValue);

}