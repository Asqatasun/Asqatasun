package org.opens.tanaguru.contentadapter;

/**
 *
 * @author ADEX
 */
public interface HTMLCleaner {

    /**
     *
     * @return the original DOM
     */
    String getDirtyHTML();

    /**
     *
     * @return cleaned DOM
     */
    String getResult();

    /**
     * Starts the processing
     */
    void run();

    /**
     * Sets the original DOM to clean
     * @param dirtyHTML the original DOM
     */
    void setDirtyHTML(String dirtyHTML);

    /**
     *
     * @return the name of the corrector
     */
    String getCorrectorName();
}
