package com.oceaneconsulting.tanaguru.service;

import org.tanaguru.sdk.entity.service.GenericDataService;

import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.entity.WsUser;

/**
 * User service interface definition
 * @author msobahi 
 *
 */
public interface WsUserService extends GenericDataService<WsUser, Long> {

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
