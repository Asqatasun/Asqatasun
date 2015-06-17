package com.oceaneconsulting.tanaguru.entity;
import org.tanaguru.sdk.entity.Entity;

/**
 * Interface de l'entite des reles 
 * @author msobahi
 *
 */
public interface WsRole extends Entity{
	
	/**
	 * Getter du rele
	 * @return 
	 */
	String getRole();
	
	/**
	 * Setter du role  
	 * @param role Le rele
	 */
	void setRole(String role);
	
	/**
	 * Getter du label du role
	 * @return La label du role
	 */
	String getLabel();
	
	/**
	 * Setter du label du rele 
	 * @param label Le label du rele
	 */
	void setLabel(String label);
	
}