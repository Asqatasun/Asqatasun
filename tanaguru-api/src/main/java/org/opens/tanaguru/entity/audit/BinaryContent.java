package org.opens.tanaguru.entity.audit;

/**
 * 
 * @author jkowalczyk
 */
public interface BinaryContent extends Content {

    /**
     *
     * @return the binary content
     */
    byte[] getContent();

    /**
     * Set the raw content
     * @param source
     */
    void setContent(byte[] source);
}
