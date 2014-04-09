package org.oceane.tanaguru.bean;

import java.util.List;

/**
 * Encapsule les resultats de l'audit
 * @author diamamp
 *
 */
public class AuditResult {

	//
	
	private long auditId;
	
	private String username;
	
	private int resultCode;
	
	private String resultText;
	
	private String resultUrl;
	
	private String score;
	
	private List<AuditInputError> errors;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultText() {
		return resultText;
	}

	public void setResultText(String resultText) {
		this.resultText = resultText;
	}

	public String getResultUrl() {
		return resultUrl;
	}

	public long getAuditId() {
		return auditId;
	}

	public void setAuditId(long auditId) {
		this.auditId = auditId;
	}

	public void setResultUrl(String resultUrl) {
		this.resultUrl = resultUrl;
	}

	public List<AuditInputError> getErrors() {
		return errors;
	}

	public void setErrors(List<AuditInputError> errors) {
		this.errors = errors;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	
	
}
