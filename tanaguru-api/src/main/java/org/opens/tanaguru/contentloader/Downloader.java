package org.opens.tanaguru.contentloader;

public interface Downloader {// TODO Write Javadoc

    /**
     *
     * @return the content downloaded
     */
    String getResult();

    /**
     *
     * @return the URL set
     */
    String getURL();

    void run();

    /**
     *
     * @param url the URL of the content to download
     */
    void setURL(String url);
}
