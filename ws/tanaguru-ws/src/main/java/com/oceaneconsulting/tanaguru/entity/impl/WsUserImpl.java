package com.oceaneconsulting.tanaguru.entity.impl;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import com.oceaneconsulting.tanaguru.entity.WsUser;

@Entity
@Table(name = "WS_USER")
@XmlRootElement
public class WsUserImpl implements WsUser, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column (name = "FIRST_NAME")
	private String firstName;
	
	@Column (name ="ACTIVE")
	private Boolean active;
	
	@ManyToOne 
	@JoinColumn(name="ID_ROLE", nullable = false)
	private WsRoleImpl role;
	
	@Override
	public Long getId(){
		return this.id;
	}
	
	@Override
	public void setId(Long id){
		this.id = id;
	}
	
	@Override
	public String getName(){
		return this.name;
	}
	
	@Override
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public String getFirstName(){
		return this.firstName;
	}
	
	@Override
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	@Override
	public Boolean getActive(){
		return this.active;
	}
	
	@Override
	public void setActive(Boolean active){
		this.active = active;
	}
	
	@Override
	public WsRoleImpl getRole(){
		return this.role;
	}
	
	@Override
	public void setRole(WsRoleImpl role){
		this.role = role;
	}

}