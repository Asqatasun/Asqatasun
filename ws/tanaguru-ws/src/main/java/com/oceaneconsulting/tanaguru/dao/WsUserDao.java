package com.oceaneconsulting.tanaguru.dao;

import org.tanaguru.sdk.entity.dao.GenericDAO;

import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.entity.WsUser;

/**
 * User DAO interface definition 
 * @author msobahi
 *
 */
public interface WsUserDao extends GenericDAO<WsUser, Long> {

	/**
	 * Get user's role
	 * @param user
	 * @return user's role
	 */
	WsRole getUserRole(WsUser user);
	
	/**
	 * Get user by login
	 * @param login : given user login
	 * @return WsUser object
	 */
	WsUser getUser(String login);

}