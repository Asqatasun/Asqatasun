package org.opens.tanaguru.entity.audit;

import java.util.Collection;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface SourceCodeRemark extends ConsolidationRemark {

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

}
