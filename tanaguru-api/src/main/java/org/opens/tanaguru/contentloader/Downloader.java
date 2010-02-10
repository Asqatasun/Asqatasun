package org.opens.tanaguru.contentloader;

public interface Downloader {

	/**
	 * 
	 * @return the content downloaded
	 */
	String getResult();

	String getURL();

	void run();

	void setURL(String url);
}
