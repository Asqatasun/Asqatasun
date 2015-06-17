package com.oceaneconsulting.tanaguru.service;

//import org.tanaguru.sdk.entity.service.GenericDataService;

import com.oceaneconsulting.tanaguru.entity.WsInvocation;
import com.oceaneconsulting.tanaguru.entity.WsRole;
import com.oceaneconsulting.tanaguru.entity.WsUser;

/**
 * Interface du service d'invocation
 * @author msobahi
 *
 */
public interface WsInvocationService /*extends GenericDataService<WsInvocation, Long>*/ {
	
	
	public void create(WsInvocation wsInvocation);

	/**
	 * Recuperer le nombre d'invocation par utilisteur
	 * @param user L'utilisateur
	 * @return Le nombre d'invocation par utilisateur
	 */
	int getCountInvocByUser(WsUser user);

	/**
	 * Recuperer le nombre d'invocation par utilisateur, nom du host, adresse ip du host et role
	 * @param user L'utilisateur
	 * @param hostName Nom du host
	 * @param hostIp Adresse ip du host
	 * @param role Role
	 * @return Le nombre d'invocation 
	 */
	int getCountInvocByUser(WsUser user, String hostName, String hostIp, WsRole role);

	/**
	 * Recuperer le nombre d'invocation par identifiant d'utilisateur
	 * @param userId Identifiant de l'utilisateur
	 * @return Nombre d'invocation 
	 */
	int getCountInvocByUserId(Long userId);

	/**
	 * Verifier le depassement de la limitation des invocations
	 * @param user L'utilisateur
	 * @param hostName Le nom du host
	 * @param hostIp L'adresse ip du hist
	 * @param role Le role
	 * @return limitation depasse true/false
	 */
	boolean checkLimitationOverflow(WsUser user, String hostName, String hostIp, WsRole role);
	
	

}
