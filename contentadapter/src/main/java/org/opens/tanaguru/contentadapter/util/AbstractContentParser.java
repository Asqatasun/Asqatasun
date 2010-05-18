package org.opens.tanaguru.contentadapter.util;

import org.opens.tanaguru.contentadapter.ContentParser;
import org.opens.tanaguru.contentadapter.Resource;

/**
 * 
 * @author ADEX
 */
public abstract class AbstractContentParser implements ContentParser {

	protected Resource resource;

	public AbstractContentParser() {
		super();
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
