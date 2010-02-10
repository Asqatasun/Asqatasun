package org.opens.tanaguru.contentadapter.util;

import org.opens.tanaguru.contentadapter.RsrcLocator;

public class InlineRsrc implements RsrcLocator {

	public InlineRsrc() {
		super();
	}

	public String getRsrcLocatorName() {
		return "#Inline";
	}

	public short getRsrcLocatorType() {
		return 3;
	}
}
