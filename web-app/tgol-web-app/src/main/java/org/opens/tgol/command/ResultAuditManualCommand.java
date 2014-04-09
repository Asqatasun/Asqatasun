package org.opens.tgol.command;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tgol.presentation.data.TestResult;

public class ResultAuditManualCommand implements Serializable {
	
	private Map<Theme, List<TestResult>> testResultMap;

	
	/**
	 * 
	 */
	public ResultAuditManualCommand() {
		
	}


	public Map<Theme, List<TestResult>> getTestResultMap() {
		return testResultMap;
	}


	public void setTestResultMap(Map<Theme, List<TestResult>> testResultMap) {
		this.testResultMap = testResultMap;
	}


	
	
	
	

}
