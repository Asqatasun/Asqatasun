package com.oceaneconsulting.tanaguru.entity.impl;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import com.oceaneconsulting.tanaguru.entity.WsRole;

/**
 * Classe des rôles 
 * @author msobahi
 *
 */
@Entity
@Table(name = "WS_ROLE")
@XmlRootElement
public class WsRoleImpl implements WsRole, Serializable {
	
	/**
	 * Identifiant
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ROLE")
	private Long id;
	
	/**
	 * Le rôle
	 */
	@Column(name = "ROLE")
	private String role;
	
	/**
	 * Le libellé du rôle
	 */
	@Column(name = "LABEL")
	private String label;
	
	
	@Override
	public Long getId(){
		return this.id;
	}
	
	@Override
	public void setId(Long id){
		this.id = id;
	}
	
	@Override
	public String getRole(){
		return this.role;
	}
	
	@Override
	public void setRole(String role){
		this.role = role;
	}
	
	@Override
	public String getLabel(){
		return label;
	}
	
	@Override
	public void setLabel(String label){
		this.label = label;
	}
	
}