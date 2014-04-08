package com.oceaneconsulting.tanaguru.entity;
import org.opens.tanaguru.sdk.entity.Entity;

/**
 * Interface de l'entité des rôles 
 * @author msobahi
 *
 */
public interface WsRole extends Entity{
	
	/**
	 * Getter du rôle
	 * @return 
	 */
	String getRole();
	
	/**
	 * Setter du rôle  
	 * @param role Le rôle
	 */
	void setRole(String role);
	
	/**
	 * Getter du label du rôle
	 * @return La label du rôle
	 */
	String getLabel();
	
	/**
	 * Setter du label du rôle 
	 * @param label Le label du rôle
	 */
	void setLabel(String label);
	
}