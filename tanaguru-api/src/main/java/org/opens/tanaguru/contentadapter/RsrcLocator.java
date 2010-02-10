package org.opens.tanaguru.contentadapter;

public interface RsrcLocator {

	static final short EXTERNAL = 2;
	static final short INLINE = 3;
	static final short LOCAL = 1;
	static final short REFERENCED = 4;

	/**
	 * 
	 * @return a resource locator name
	 */
	String getRsrcLocatorName();

	/**
	 * 
	 * @return a resource locator type
	 */
	short getRsrcLocatorType();
}
