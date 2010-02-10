package org.opens.tanaguru.entity.membership;

import com.adex.sdk.entity.Entity;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface Office extends Entity {

	/**
	 * 
	 * @return the group
	 */
	OfficeGroup getGroup();

	/**
	 * 
	 * @return the label
	 */
	String getLabel();

	/**
	 * 
	 * @param group
	 *            the group to set
	 */
	void setGroup(OfficeGroup group);

	/**
	 * 
	 * @param label
	 *            the label to set
	 */
	void setLabel(String label);
}
