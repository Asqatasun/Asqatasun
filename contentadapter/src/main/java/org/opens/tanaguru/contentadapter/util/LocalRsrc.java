package org.opens.tanaguru.contentadapter.util;

import org.opens.tanaguru.contentadapter.RsrcLocator;

public class LocalRsrc implements RsrcLocator {

	public LocalRsrc() {
		super();
	}

	public String getRsrcLocatorName() {
		return "#Local";
	}

	public short getRsrcLocatorType() {
		return 1;
	}
}
