package org.opens.tanaguru.entity.membership;

import java.util.Collection;

import com.adex.sdk.entity.Entity;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface Role extends Entity {

	/**
	 * 
	 * @return the label
	 */
	String getLabel();

	/**
	 * 
	 * @return the groups
	 */
	Collection<? extends OfficeGroup> getOfficeGroupList();

	/**
	 * 
	 * @return the user list
	 */
	Collection<? extends User> getUserList();

	/**
	 * 
	 * @param label
	 *            the label to set
	 */
	void setLabel(String label);

	/**
	 * 
	 * @param groups
	 *            the groups to set
	 */
	void setOfficeGroupList(Collection<? extends OfficeGroup> groups);

	/**
	 * 
	 * @param userList
	 *            the user list to set
	 */
	void setUserList(Collection<? extends User> userList);
}
