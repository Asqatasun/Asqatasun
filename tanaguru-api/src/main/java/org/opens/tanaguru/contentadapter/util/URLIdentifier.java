package org.opens.tanaguru.contentadapter.util;

import java.net.URL;

public interface URLIdentifier {

	/**
	 * 
	 * @return an absolute path
	 */
	URL getAbsolutePath();

	/**
	 * 
	 * @return a url
	 */
	URL getUrl();

	/**
	 * 
	 * @param path
	 *            the pathe to resolve
	 * @return the resolved URL
	 */
	URL resolve(String path);

	/**
	 * 
	 * @param url
	 *            the url to set
	 */
	void setUrl(URL url);
}
