package org.opens.tanaguru.contentadapter.js;

import org.opens.tanaguru.contentadapter.RsrcLocator;
import org.opens.tanaguru.contentadapter.util.AbstractResource;

public class JSResourceImpl extends AbstractResource implements JSResource {

	public JSResourceImpl() {
		super();
	}

	public JSResourceImpl(String resource, int lineNumber, RsrcLocator location) {
		super(resource, lineNumber, location);
	}

	public String getRsrcName() {
		return "#JavaScript";
	}

	public short getRsrcType() {
		return 2;
	}
}
