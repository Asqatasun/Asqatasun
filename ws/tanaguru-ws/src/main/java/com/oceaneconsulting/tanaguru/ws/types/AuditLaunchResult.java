package com.oceaneconsulting.tanaguru.ws.types;

import java.io.Serializable;

/**
 * Audit launching result.
 * 
 * @author shamdi at oceaneconsulting dot com
 *
 */
public class AuditLaunchResult implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -790338109931724106L;

	private Long idAudit;
	
	private String message = "";

	public Long getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(Long idAudit) {
		this.idAudit = idAudit;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
