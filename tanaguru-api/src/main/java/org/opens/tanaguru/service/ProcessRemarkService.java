package org.opens.tanaguru.service;

import java.util.Collection;
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
    public static final String URL_EVIDENCE = "Url";

    /**
     *
     * @param document
     * @param adaptedContent
     */
    void initializeService(Document document, String adaptedContent);

    /**
     *
     */
    void initializeService();

    /**
     *
     * @param processResult
     * @param node
     * @param messageCode
     * @param elementName
     */
    void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, String elementName);

    /**
     *
     * @param processResult
     * @param node
     * @param messageCode
     * @param elementType
     * @param elementName
     */
    void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, String elementType, String elementName);

    /**
     *
     * @param processResult
     * @param node
     * @param messageCode
     * @param evidenceElementList
     */
    void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, List<EvidenceElement> evidenceElementList);

    /**
     *
     * @param processResult
     * @param node
     * @param messageCode
     * @param elementName
     * @return
     */
    SourceCodeRemark createSourceCodeRemark(TestSolution processResult,
            Node node, String messageCode, String elementName);

    /**
     *
     * @param processResult
     * @param rule
     * @param messageCode
     * @param attrName
     */
    void addCssCodeRemark(TestSolution processResult,
            CSSOMRule rule, String messageCode, String attrName);

    /**
     *
     * @param processResult
     * @param messageCode
     */
    void addProcessRemark(TestSolution processResult, String messageCode);

    /**
     *
     * @param processResult
     * @param messageCode
     * @param evidenceElementList
     */
    void addProcessRemark(TestSolution processResult,
            String messageCode, Collection<EvidenceElement> evidenceElementList);

    /**
     *
     * @param processResult
     * @param messageCode
     * @return
     */
    ProcessRemark createProcessRemark(TestSolution processResult, String messageCode);

    /**
     *
     * @param processResult
     * @param messageCode
     * @param evidenceElementList
     * @return
     */
    ProcessRemark createProcessRemark(TestSolution processResult,
            String messageCode, Collection<EvidenceElement> evidenceElementList);

    /**
     * 
     * @param processResult
     * @param messageCode
     * @param value
     * @param url
     */
    void addConsolidationRemark(TestSolution processResult,
            String messageCode, String value, String url);

    /**
     *
     * @param processResult
     * @param messageCode
     * @param value
     * @param url
     * @return
     */
    ProcessRemark createConsolidationRemark(TestSolution processResult,
            String messageCode, String value, String url);

    /**
     *
     * @return
     *          the list of remarks created by the service
     */
    Collection<ProcessRemark> getRemarkList();

    /**
     *
     * @param evidenceCode
     * @param evidenceValue
     * @return
     */
    EvidenceElement getEvidenceElement(String evidenceCode, String evidenceValue);

    /**
     *
     * @param element
     */
    void addEvidenceElement(String element);

    /**
     *
     * @param element
     */
    void setEvidenceElementList(Collection<String> element);

    /**
     *
     * @return
     */
    EvidenceElementFactory getEvidenceElementFactory();

    /**
     *
     * @return
     */
    EvidenceDataService getEvidenceDataService();
}
