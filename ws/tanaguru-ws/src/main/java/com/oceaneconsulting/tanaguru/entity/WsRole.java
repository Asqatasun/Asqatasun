package com.oceaneconsulting.tanaguru.entity;
import org.opens.tanaguru.sdk.entity.Entity;
public interface WsRole extends Entity{
	
	String getRole();
	void setRole(String role);
	String getLabel();
	void setLabel(String label);
	
}