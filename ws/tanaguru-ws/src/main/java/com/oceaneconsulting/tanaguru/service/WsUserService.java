package com.oceaneconsulting.tanaguru.service;

import org.opens.tanaguru.sdk.entity.service.GenericDataService;

import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.entity.WsUser;

/**
 * Interface du service des utilisateurs 
 * @author msobahi 
 *
 */
public interface WsUserService extends GenericDataService<WsUser, Long> {

	/**
	 * Retourner le role de l'utilisateur
	 * @param user L'utilisateur
	 * @return Le role de l'utilisateur.
	 */
	WsRole getUserRole(WsUser user);

}
