package org.opens.tanaguru.entity.audit;


import com.adex.sdk.entity.Entity;
import java.util.Collection;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface ProcessResult extends Entity {

    /**
     *
     * @param remarkList
     *            the remark list to add
     */
    void addAllRemark(Collection<? extends ProcessRemark> remarkSet);

    /**
     *
     * @param childResult
     *            the child process result to add
     */
    void addChildResult(ProcessResult childResult);

    /**
     *
     * @param remark
     *            the remark to add
     */
    void addRemark(ProcessRemark remark);

    /**
     *
     * @return the sub result list
     */
    Collection<? extends ProcessResult> getChildResultList();

    /**
     *
     * @return the audit a the gross result
     */
    Audit getGrossResultAudit();

    /**
     *
     * @return the audit of a net result
     */
    Audit getNetResultAudit();

    /**
     *
     * @return the parent result
     */
    ProcessResult getParentResult();

    /**
     *
     * @return the remark list
     */
    Collection<? extends ProcessRemark> getRemarkSet();

    /**
     *
     * @return the subject
     */
    WebResource getSubject();

    /**
     *
     * @return the test
     */
    Test getTest();

    /**
     *
     * @return the value
     */
    Object getValue();

    /**
     *
     * @return the number of tested elements
     */
    int getElementCounter();

    /**
     *
     * @param subResultList
     *            the sub result list to set
     */
    void setChildResultList(Collection<? extends ProcessResult> subResultList);

    /**
     *
     * @param grossResultAudit
     *            the audit of a gross result to set
     */
    void setGrossResultAudit(Audit grossResultAudit);

    /**
     *
     * @param netResultAudit
     *            the audit of a net result to set
     */
    void setNetResultAudit(Audit netResultAudit);

    /**
     *
     * @param parentResult
     *            the parent result to set
     */
    void setParentResult(ProcessResult parentResult);

    /**
     *
     * @param remarkList
     *            the remark list to set
     */
    void setRemarkSet(Collection<? extends ProcessRemark> remarkList);

    /**
     *
     * @param subject
     *            the subject to set
     */
    void setSubject(WebResource subject);

    /**
     *
     * @param test
     *            the test to set
     */
    void setTest(Test test);

    /**
     *
     * @param value
     *            the value to set
     */
    void setValue(Object value);
    
    /**
     * 
     * @param elementCounter
     *              the number of tested elements
     */
    void setElementCounter(int elementCounter);

}
