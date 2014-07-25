package com.oceaneconsulting.tanaguru.ws.types;

import java.io.Serializable;
import java.util.List;

public class GlobalStatsOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1766176949082589222L;
	
	private List<Long> idAudits;
	private String country;
	private String category;
	private Integer auditType;
	private String status;
	
	public List<Long> getIdAudits() {
		return idAudits;
	}
	public void setIdAudits(List<Long> idAudits) {
		this.idAudits = idAudits;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getAuditType() {
		return auditType;
	}
	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Global Stats Order [idAudits=" + idAudits + ", country="
				+ country + ", category=" + category + ", auditType="
				+ auditType + ", status=" + status + "]";
	}

	
}
