package org.opens.tanaguru.entity.membership;

import java.util.Collection;

import com.adex.sdk.entity.Entity;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface User extends Entity {

	/**
	 * 
	 * @return the password
	 */
	String getPassword();

	/**
	 * 
	 * @return the roles
	 */
	Collection<? extends Role> getRoleList();

	/**
	 * 
	 * @return the username
	 */
	String getUsername();

	/**
	 * 
	 * @param password
	 *            the password to set
	 */
	void setPassword(String password);

	/**
	 * 
	 * @param roles
	 *            the roles to set
	 */
	void setRoleList(Collection<? extends Role> roles);

	/**
	 * 
	 * @param username
	 *            the username to set
	 */
	void setUsername(String username);
}
