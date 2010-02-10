package org.opens.tanaguru.contentadapter;

/**
 * 
 * @author ADEX
 */
public interface ContentParser {

	Resource getResource();

	void run();

	void setResource(Resource resource);
}
