package org.opens.tanaguru.entity.audit;

import com.adex.sdk.entity.Entity;

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
}
