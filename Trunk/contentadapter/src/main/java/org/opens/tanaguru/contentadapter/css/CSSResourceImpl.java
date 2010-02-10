package org.opens.tanaguru.contentadapter.css;

import org.opens.tanaguru.contentadapter.util.AbstractResource;
import org.opens.tanaguru.contentadapter.RsrcLocator;

public class CSSResourceImpl extends AbstractResource implements CSSResource {

	public CSSResourceImpl() {
		super();
	}

	public CSSResourceImpl(String resource, int lineNumber, RsrcLocator location) {
		super(resource, lineNumber, location);
	}

	public String getRsrcName() {
		return "#StyleSheet";
	}

	public short getRsrcType() {
		return 1;
	}
}
