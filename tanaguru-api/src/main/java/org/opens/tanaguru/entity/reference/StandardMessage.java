package org.opens.tanaguru.entity.reference;

import com.adex.sdk.entity.Entity;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface StandardMessage extends Entity {

	/**
	 * 
	 * @return the code
	 */
	String getCode();

	/**
	 * 
	 * @return the label
	 */
	String getLabel();

	/**
	 * 
	 * @return the text
	 */
	String getText();

	/**
	 * 
	 * @param code
	 *            the code to set
	 */
	void setCode(String code);

	/**
	 * 
	 * @param label
	 *            the label to set
	 */
	void setLabel(String label);

	/**
	 * 
	 * @param text
	 *            the text to set
	 */
	void setText(String text);
}
