package org.opens.tanaguru.contentadapter.util;

import org.opens.tanaguru.contentadapter.RsrcLocator;

public class ReferencedRsrc implements RsrcLocator {

	public ReferencedRsrc() {
		super();
	}

	public String getRsrcLocatorName() {
		return "#Referenced";
	}

	public short getRsrcLocatorType() {
		return 4;
	}
}
