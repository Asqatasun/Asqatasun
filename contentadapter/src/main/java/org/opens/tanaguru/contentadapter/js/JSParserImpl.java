package org.opens.tanaguru.contentadapter.js;

import org.opens.tanaguru.contentadapter.js.*;
import org.opens.tanaguru.contentadapter.util.AbstractContentParser;

/**
 * 
 * @author ADEX
 */
public class JSParserImpl extends AbstractContentParser implements JSParser {

	private JSResource result;

	public JSParserImpl() {
		super();
	}

	public JSResource getResult() {
		return result;
	}

	public void run() {
		result = (JSResource) resource;
	}
}
