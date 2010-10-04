package org.opens.tanaguru.entity.audit;

import com.adex.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 */
public interface EvidenceElement extends Entity {

    /**
     *
     * @return the evidence
     */
    Evidence getEvidence();

    /**
     *
     * @return the value
     */
    String getLabel();

    /**
     * 
     * @return the process remark
     */
    ProcessRemark getProcessRemark();

    /**
     *
     * @param evidence
     *            the evidence to set
     */
    void setEvidence(Evidence evidence);

    /**
     *
     * @param value
     *            the value to set
     */
    void setLabel(String value);

    /**
     *
     * @param processRemark
     *          the process remark to set
     */
    void setProcessRemark(ProcessRemark processRemark);
}
