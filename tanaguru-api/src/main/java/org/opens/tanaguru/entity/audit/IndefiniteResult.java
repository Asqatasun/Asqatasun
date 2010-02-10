package org.opens.tanaguru.entity.audit;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface IndefiniteResult extends ProcessResult {

	/**
	 * 
	 * @return the indefinite value
	 */
	String getIndefiniteValue();

	/**
	 * 
	 * @param value
	 *            the indefinite value to set
	 */
	void setIndefiniteValue(String value);
}
