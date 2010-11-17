package org.opens.tanaguru.entity.audit;

import java.util.Collection;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface ConsolidationRemark extends ProcessRemark {

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
