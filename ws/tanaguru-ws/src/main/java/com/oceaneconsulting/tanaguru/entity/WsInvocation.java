package com.oceaneconsulting.tanaguru.entity;
import org.tanaguru.sdk.entity.Entity;

import com.oceaneconsulting.tanaguru.entity.impl.WsUserImpl;

import java.util.Date;

/**
 * Intefrace de l'entite des invocation
 * @author msobahi
 *
 */
public interface WsInvocation extends Entity {
	/**
	 * Getter du nom de l'host
	 * @return Nom du host
	 */
	String getHostName();
	
	/**
	 * Setter du nom du host
	 * @param hostName Nom du host
	 */
	void setHostName(String hostName);
	
	/**
	 * Getter de l'adresse ip du host
	 * @return Adresse ip du host
	 */
	String getHostIp();
	
	/**
	 * Setter de l'adresse ip du host
	 * @param hostIp L'adresse ip du host
	 */
	void setHostIp(String hostIp);
	
	/**
	 * Getter de la date d'invocation
	 * @return la date d'invocation
	 */
	Date getDateInvocation();
	
	/**
	 * Le setter de la date d'invocation
	 * @param dateInvocation d'invocation
	 */
	void setDateInvocation(Date dateInvocation);
	
	/**
	 * Getter du type de l'audit
	 * @return Type de l'audit
	 */
	Integer getAuditType();
	
	/**
	 * Setter du typed d'audit
	 * @param auditType Le type d'audit
	 */
	void setAuditType(Integer auditType);
	
	/**
	 * Getter de l'utilisateur qui a lance l'invocation
	 * @return Utilisateur 
	 */
	WsUserImpl getUser();
	
	/**
	 * Setter de l'utilisateur qui a lance l'invocation
	 * @param user L'utilsateur 
	 */
	void setUser(WsUserImpl user);
	
	Long getAuditId();

	void setAuditId(Long auditId);

	String getCategory();

	void setCategory(String category);

	String getCountry();

	void setCountry(String country);
}