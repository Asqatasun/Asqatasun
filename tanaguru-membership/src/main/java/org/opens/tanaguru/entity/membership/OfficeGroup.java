package org.opens.tanaguru.entity.membership;

import java.util.Collection;

import com.adex.sdk.entity.Entity;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface OfficeGroup extends Entity {

	/**
	 * 
	 * @return the label
	 */
	String getLabel();

	/**
	 * 
	 * @return the offices
	 */
	Collection<? extends Office> getOfficeList();

	/**
	 * 
	 * @return the role list
	 */
	Collection<? extends Role> getRoleList();

	/**
	 * 
	 * @param label
	 *            the label to set
	 */
	void setLabel(String label);

	/**
	 * 
	 * @param offices
	 *            the offices to set
	 */
	void setOfficeList(Collection<? extends Office> offices);

	/**
	 * 
	 * @param roleList
	 *            the role list to set
	 */
	void setRoleList(Collection<? extends Role> roleList);
}
