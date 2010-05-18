package org.opens.tanaguru.entity.audit;

/**
 *
 * @author jkowalczyk
 */
public interface TextContent extends Content {

    /**
     *
     * @return the source code of the resource
     */
    String getSource();

    /**
     * Set the source code of the resource
     * @param source
     */
    void setSource(String source);

    /**
     *
     * @return the adapted content of the resource
     */
    String getAdaptedContent();

    /**
     * Set the adapted content of the resource
     * @param source
     */
    void setAdaptedContent(String source);
}
