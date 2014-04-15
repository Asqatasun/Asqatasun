package com.oceaneconsulting.tanaguru.dao;

import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.entity.WsUser;

/**
 * Interface de la DAO des utilisateurs
 * @author msobahi
 *
 */
public interface WsUserDao extends GenericDAO<WsUser, Long> {

	/**
	 * Recuperer le role de l'utilisateur
	 * @param user L'utilisateur
	 * @return Le role de l'utilisateur
	 */
	WsRole getUserRole(WsUser user);

}