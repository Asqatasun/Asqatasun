package org.opens.tanaguru.entity.parameterization;


import com.adex.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 */
public interface Parameter extends Entity {

    /**
     * 
     * @return
     */
    ParameterElement getParameterElement();

    /**
     *
     * @param parameterElement
     */
    void setParameterElement(ParameterElement parameterElement);

    /**
     *
     * @return
     */
    String getValue();

    /**
     *
     * @param value
     */
    void setValue(String value);

    /**
     *
     * @return
     */
    Boolean isDefaultParameterValue();

    /**
     *
     * @param isDefaultParameterValue
     */
    void setDefaultParameterValue(Boolean isDefaultParameterValue);

//    /**
//     *
//     * @param auditCollection
//     */
//    void setAuditCollection(Collection<? extends Audit> auditCollection);
//
//    /**
//     *
//     * @param audit
//     */
//    void addAudit(Audit audit);
//
//    /**
//     *
//     * @return
//     */
//    Collection<? extends Audit> getAuditCollection();
}