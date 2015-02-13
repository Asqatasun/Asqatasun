package org.tanaguru.jms.util;

import java.io.Serializable;

public class CommonOrder implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8611490740805106935L;
	
	
	protected String level;
	protected String tblMarker;
	protected String prTblMarker;
	protected String dcrImgMarker;
	protected String infImgMarker;
	protected String maxDocuments;
	protected String maxDuration;
	protected String depth;
	protected String exclusionRegExp;
	
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getTblMarker() {
		return tblMarker;
	}
	public void setTblMarker(String tblMarker) {
		this.tblMarker = tblMarker;
	}
	public String getPrTblMarker() {
		return prTblMarker;
	}
	public void setPrTblMarker(String prTblMarker) {
		this.prTblMarker = prTblMarker;
	}
	public String getDcrImgMarker() {
		return dcrImgMarker;
	}
	public void setDcrImgMarker(String dcrImgMarker) {
		this.dcrImgMarker = dcrImgMarker;
	}
	public String getInfImgMarker() {
		return infImgMarker;
	}
	public void setInfImgMarker(String infImgMarker) {
		this.infImgMarker = infImgMarker;
	}
	public String getMaxDocuments() {
		return maxDocuments;
	}
	public void setMaxDocuments(String maxDocuments) {
		this.maxDocuments = maxDocuments;
	}
	public String getMaxDuration() {
		return maxDuration;
	}
	public void setMaxDuration(String maxDuration) {
		this.maxDuration = maxDuration;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getExclusionRegExp() {
		return exclusionRegExp;
	}
	public void setExclusionRegExp(String exclusionRegExp) {
		this.exclusionRegExp = exclusionRegExp;
	}
	
}
