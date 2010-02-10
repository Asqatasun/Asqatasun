package org.opens.tanaguru.contentadapter.util;

import org.opens.tanaguru.contentadapter.RsrcLocator;

public class ExternalRsrc implements RsrcLocator {

	public ExternalRsrc() {
		super();
	}

	public String getRsrcLocatorName() {
		return "#External";
	}

	public short getRsrcLocatorType() {
		return 2;
	}
}
