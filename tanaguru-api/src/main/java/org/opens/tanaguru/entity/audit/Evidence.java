package org.opens.tanaguru.entity.audit;

import java.util.Collection;

import com.adex.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 */
public interface Evidence extends Entity {

    /**
     *
     * @param element
     *            the element to add
     */
    void addElement(EvidenceElement element);

    /**
     *
     * @return the code
     */
    String getCode();

    /**
     *
     * @return the description
     */
    String getDescription();

    /**
     *
     * @return the elements
     */
    Collection<? extends EvidenceElement> getElementList();

    /**
     *
     * @return the integer values of the elements
     */
    Collection<Integer> getIntegerValueList();

    /**
     *
     * @return the long label
     */
    String getLongLabel();

    /**
     *
     * @return the values from the elements
     */
    Collection<String> getValueList();

    /**
     *
     * @param code
     *            the code to set
     */
    void setCode(String code);

    /**
     *
     * @param description
     *            the description to set
     */
    void setDescription(String description);

    /**
     *
     * @param elements
     *            the elements to set
     */
    void setElementList(Collection<? extends EvidenceElement> elements);

    /**
     *
     * @param longLabel
     *            the long label to set
     */
    void setLongLabel(String longLabel);
}
