package com.oceaneconsulting.tanaguru.dao;

import org.tanaguru.sdk.entity.dao.GenericDAO;

import com.oceaneconsulting.tanaguru.entity.WsInvocation;
import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.entity.WsUser;

/**
 * Interface de la DAO des invocations
 * @author msobahi
 *
 */
public interface WsInvocationDao extends GenericDAO<WsInvocation, Long> {

	/**
	 * Recuperer le nombre d'invocations faites par l'utilisateur
	 * @param user L'utilsateur
	 * @return Le nombre d'invocations
	 */
	int getCountInvocByUser(WsUser user);
	
	/**
	 * Recuperer le nombre d'invocations faites par l'utilisateur via son identifiant
	 * @param userId Identifiant de l'utilisateur
	 * @return Le nombre d'invocations
	 */
	int getCountInvocByUserId(Long userId);

	/**
	 * Recuperer le nombre d'invocations faites par l'utilisateur
	 * @param user L'utilisateur
	 * @param hostName Le nom du host
	 * @param hostIp L'adresse ip du host
	 * @return Le nombre d'invocation
	 */
	int getCountInvocByUser(WsUser user, String hostName, String hostIp,WsRole role);

}