package com.adex.sdk.entity.i18n;

import com.adex.sdk.entity.Entity;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface Language extends Entity {

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
}
