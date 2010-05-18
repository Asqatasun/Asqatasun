package org.opens.tanaguru.entity.subject;

import java.util.Collection;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface Site extends WebResource {

	/**
	 * 
	 * @param webResourceList
	 *            the Web resource list to add
	 */
	void addAllChild(Collection<WebResource> webResourceList);

	/**
	 * 
	 * @param webResource
	 *            the Web resource to add
	 */
	void addChild(WebResource webResource);

	/**
	 * 
	 * @param url
	 *            the URL of the component to find
	 * @return true if a component is found
	 */
	boolean contains(String url);

	/**
	 * 
	 * @return the components
	 */
	Collection<? extends WebResource> getComponentList();

	/**
	 * 
	 * @param components
	 *            the components to set
	 */
	void setComponentList(Collection<? extends WebResource> components);
}
