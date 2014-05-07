package com.oceaneconsulting.tanaguru.ws.types;

public class AuditScenarioOrder extends CommonOrder {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5583964419147793630L;
	
	private String scenarioLabel;
	private String scenarioText;
	
	
	public String getScenarioLabel() {
		return scenarioLabel;
	}
	public void setScenarioLabel(String scenarioLabel) {
		this.scenarioLabel = scenarioLabel;
	}
	public String getScenarioText() {
		return scenarioText;
	}
	public void setScenarioText(String scenarioText) {
		this.scenarioText = scenarioText;
	}
	
	@Override
	public String toString() {
		return "Audit Scenario Order [scenarioLabel=" + scenarioLabel
				+ ", scenarioText=" + scenarioText + ", level=" + level
				+ ", tblMarker=" + tblMarker + ", prTblMarker=" + prTblMarker
				+ ", dcrImgMarker=" + dcrImgMarker + ", infImgMarker="
				+ infImgMarker + ", maxDocuments=" + maxDocuments
				+ ", maxDuration=" + maxDuration + ", depth=" + depth
				+ ", exclusionRegExp=" + exclusionRegExp + "]";
	}
	
	
	
	
}
