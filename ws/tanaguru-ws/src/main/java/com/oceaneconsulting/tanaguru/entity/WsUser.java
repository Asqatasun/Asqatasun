package com.oceaneconsulting.tanaguru.entity;

import org.tanaguru.sdk.entity.Entity;

import com.oceaneconsulting.tanaguru.entity.impl.WsRoleImpl;

/**
 * Interface de l'utilisateur web service 
 * @author msobahi
 *
 */
public interface WsUser extends Entity {
	
	/**
	 * Getter du nom de l'utilisateur
	 * 
	 * @return Nom de l'utilisateur
	 */
	String getName();
	
	/**
	 * Setter du nom de l'utilisateur 
	 * 
	 * @param name Nom de l'utilisateur
	 */
	void setName(String name);
	
	/**
	 * Getter du nom de l'utilisateur
	 * 
	 * @return Nom de l'utilisateur
	 */
	String getFirstName();
	
	/**
	 * Setter du nom de l'utilisateur 
	 * 
	 * @param firstName Nom de l'utilisateur
	 */
	void setFirstName(String firstName);
	
	/**
	 * Getter du flag d'activite de l'utilisateur
	 * 
	 * @return Flag d'activite de l'utilisateur
	 */
	Boolean getActive();
	
	/**
	 * Setter du flag d'activite de l'utilisateur
	 * 
	 * @param active Flag d'activite de l'utilisateur
	 */
	void setActive(Boolean active);
	
	/**
	 * Getter du role de l'utilsateur
	 * 
	 * @return Role de l'utilisateur
	 */
	WsRoleImpl getRole();
	
	/**
	 * Setter du role de l'utilisateur
	 * 
	 * @param role Role de l'utilisateur
	 */
	void setRole(WsRoleImpl role);

	/**
	 * Getter de l'email de l'utilisateur
	 * @return l'email de l'utilisateur
	 */
	String getEmail();

	/**
	 * Setter de l'email de l'utilisateur
	 * @param email L'email de l'utilisateur
	 */
	void setEmail(String email);

	/**
	 * Getter du mot de passe de l'utilisateur
	 * @return le mot de passe de l'utilisateur
	 */
	String getPassword();

	/**
	 * Setteir du mot de passe de l'utilisateur
	 * @param password Le mot de passe 
	 */
	void setPassword(String password);
	
}