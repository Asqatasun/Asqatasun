package org.opens.tanaguru.entity.audit;

import com.adex.sdk.entity.Entity;
import java.util.Collection;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface ProcessRemark extends Entity {

    /**
     *
     * @return the issue
     */
    TestSolution getIssue();

    /**
     * 
     * @return the message code
     */
    String getMessageCode();

    /**
     *
     * @return the process result
     */
    ProcessResult getProcessResult();

    /**
     *
     * @return the selected element
     */
    String getSelectedElement();

    /**
     *
     * @return the selection expression
     */
    String getSelectionExpression();

    /**
     *
     * @param issue
     *            the issue to set
     */
    void setIssue(TestSolution issue);

    /**
     *
     * @param messageCode
     *              the message code to set
     */
    void setMessageCode(String messageCode);

    /**
     *
     * @param processResult
     *            the process result to set
     */
    void setProcessResult(ProcessResult processResult);

    /**
     *
     * @param element the selected element to set
     */
    void selectedElement(String element);

    /**
     * 
     * @param selectionExpression the selection expression to set
     */
    void setSelectionExpression(String selectionExpression);

    /**
     *
     * @param element
     *            the element to add
     */
    void addElement(EvidenceElement element);

    /**
     *
     * @return the elements
     */
    Collection<? extends EvidenceElement> getElementList();

    /**
     *
     * @param elements
     *            the elements to set
     */
    void setElementList(Collection<? extends EvidenceElement> elements);
}
