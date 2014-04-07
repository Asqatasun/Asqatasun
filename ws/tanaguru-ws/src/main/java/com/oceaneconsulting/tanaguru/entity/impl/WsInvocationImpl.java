package com.oceaneconsulting.tanaguru.entity.impl;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import com.oceaneconsulting.tanaguru.entity.WsInvocation;
import java.util.Date;

@Entity
@Table(name = "WS_INVOCATION")
@XmlRootElement
public class WsInvocationImpl implements WsInvocation, Serializable {
	  
	  
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INVOCATION")
	private Long id;
	 
    @Column(name = "HOST_NAME") 
	private String hostName;
	    
    @Column(name = "HOST_IP")
	private String hostIp;
	  
    @Column(name = "DT_INVOCATION")
	private Date dateInvocation;
	  
    @Column(name = "AUDIT_TYPE")
	private Integer auditType;
	
    @ManyToOne
    @JoinColumn(name = "ID_USER", nullable = false)
	private WsUserImpl user;
    
    @Override
    public Long getId(){
    	return this.id;
    }
    
    @Override
	public void setId(Long id){
    	this.id = id;
    }
    
    @Override
	public String getHostName(){
    	return this.hostName;
    }
    
    @Override
	public void setHostName(String hostName){
    	this.hostName = hostName;
    }
    
    @Override
	public String getHostIp(){
    	return this.hostIp;
    }
    
    @Override
	public void setHostIp(String hostIp){
    	this.hostIp = hostIp;
    }
    
    @Override
	public Date getDateInvocation(){
    	return dateInvocation;
    }
    
    @Override
	public void setDateInvocation(){
    	this.dateInvocation = dateInvocation;
    }
    
    @Override
	public Integer getAuditType(){
    	return this.auditType;
    }
    
    @Override
	public void setAuditType(Integer auditType){
    	this.auditType = auditType;
    }
    
    @Override
	public WsUserImpl getUser(){
    	return user;
    }
    
    @Override
	public void setUser(WsUserImpl user){
    	this.user = user;
    }
}