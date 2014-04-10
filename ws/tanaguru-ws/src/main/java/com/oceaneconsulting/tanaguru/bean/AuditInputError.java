package com.oceaneconsulting.tanaguru.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oceaneconsulting.tanaguru.enumerations.AuditType;

/**
 * Encapsule une erreur concernat les parametre d'audit
 * 
 * @author diamamp
 * 
 */
public class AuditInputError {

	String code;
	String text;
	String description;
	
	public AuditInputError() {
		// TODO Auto-generated constructor stub
	}
	
	
	public AuditInputError(String code, String text, String description) {
		this.code = code;
		this.text  = text;
		this.description = description;
	}
	
	
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
