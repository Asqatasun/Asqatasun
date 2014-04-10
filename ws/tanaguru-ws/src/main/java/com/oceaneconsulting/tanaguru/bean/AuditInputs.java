package com.oceaneconsulting.tanaguru.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oceaneconsulting.tanaguru.enumerations.AuditType;

/**
 * Encapsule les inputs pour effectuer un audit
 * 
 * @author diamamp
 * 
 */
public class AuditInputs {

	// type d'audit
	AuditType auditType;

	// niveau audit

	private String level;

	// url
	private String pageUrl;
	// url
	private List<String> pageUrls;

	// scenario name

	private String scenarioName;
	// scenari 
	private String scenario;
	// utilisateur ?

	private String username;

	private String password;
	// liste des parametres

	// remote host
	private String ip;
	// remote port
	private int port;
	
	private Map<String, String> parameters = new HashMap<String, String>();
	/**
	 * key=value,key=value,...
	 */
	private String params;

	public AuditType getAuditType() {
		return auditType;
	}

	public void setAuditType(AuditType auditType) {
		this.auditType = auditType;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}



	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public List<String> getPageUrls() {
		return pageUrls;
	}

	public void setPageUrls(List<String> pageUrls) {
		this.pageUrls = pageUrls;
	}
	
	
}
