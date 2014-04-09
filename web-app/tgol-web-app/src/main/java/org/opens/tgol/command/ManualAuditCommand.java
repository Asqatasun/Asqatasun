package org.opens.tgol.command;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opens.tgol.presentation.data.TestResultImpl;


public class ManualAuditCommand implements Serializable{
	private static final long serialVersionUID = -5390311731974450559L;
	
	private String generalErrorMsg;
    public String getGeneralErrorMsg() {
        return generalErrorMsg;
    }
    
	private Map<String, TestResultImpl> modifiedTestResultMap=new HashMap<String, TestResultImpl>();
	public Map<String, TestResultImpl> getModifiedTestResultMap() {
		return modifiedTestResultMap;
	}
	public void setModifedTestResultMap(Map<String, TestResultImpl> modifiedTestResultMap) {
		this.modifiedTestResultMap = modifiedTestResultMap;
	}
	
	private List<String> statusList= Arrays.asList("passed", "failed", "na");
	public List<String> getStatusList() {
		return statusList;
	}
	
	/** Default constructor */
	public ManualAuditCommand() {
	}
}
