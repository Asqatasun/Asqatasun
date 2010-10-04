package org.opens.tanaguru.entity.audit;

import java.util.Collection;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface SourceCodeRemark extends ProcessRemark {

    /**
     *
     * @param element
     *            the element to add
     */
    void addElement(EvidenceElement element);

    /**
     *
     * @return the position of the first character
     */
    int getCharacterPosition();

    /**
     *
     * @return the line number
     */
    int getLineNumber();

    /**
     *
     * @return the target
     */
    String getTarget();

    /**
     *
     * @return the elements
     */
    Collection<? extends EvidenceElement> getElementList();

    /**
     *
     * @param characterPosition
     *            the position of the character to set
     */
    void setCharacterPosition(int characterPosition);

    /**
     *
     * @param lineNumber
     *            the line number to set
     */
    void setLineNumber(int lineNumber);

    /**
     *
     * @param target
     *            the target to set
     */
    void setTarget(String target);

    /**
     *
     * @param elements
     *            the elements to set
     */
    void setElementList(Collection<? extends EvidenceElement> elements);
}
