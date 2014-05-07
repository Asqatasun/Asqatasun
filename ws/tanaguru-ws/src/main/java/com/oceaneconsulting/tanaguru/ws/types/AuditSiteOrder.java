package com.oceaneconsulting.tanaguru.ws.types;


/**
 * Audit site request.
 * 
 * @author shamdi at oceaneconsulting dot com
 *
 */
public class AuditSiteOrder extends CommonOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8574438742995163333L;
	
	//parameters options in tanaguru
	private String siteURL;
//	private String level;
//	private String tblMarker;
//	private String prTblMarker;
//	private String dcrImgMarker;
//	private String infImgMarker;
//	private String maxDocuments;
//	private String maxDuration;
//	private String depth;
//	private String exclusionRegExp;
//	
	//webservice parameters
	private String category;
	private String country;
	
	public String getSiteURL() {
		return siteURL;
	}
	public void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
	}
//	public String getLevel() {
//		return level;
//	}
//	public void setLevel(String level) {
//		this.level = level;
//	}
//	public String getTblMarker() {
//		return tblMarker;
//	}
//	public void setTblMarker(String tblMarker) {
//		this.tblMarker = tblMarker;
//	}
//	public String getPrTblMarker() {
//		return prTblMarker;
//	}
//	public void setPrTblMarker(String prTblMarker) {
//		this.prTblMarker = prTblMarker;
//	}
//	public String getDcrImgMarker() {
//		return dcrImgMarker;
//	}
//	public void setDcrImgMarker(String dcrImgMarker) {
//		this.dcrImgMarker = dcrImgMarker;
//	}
//	public String getInfImgMarker() {
//		return infImgMarker;
//	}
//	public void setInfImgMarker(String infImgMarker) {
//		this.infImgMarker = infImgMarker;
//	}
//	public String getMaxDocuments() {
//		return maxDocuments;
//	}
//	public void setMaxDocuments(String maxDocuments) {
//		this.maxDocuments = maxDocuments;
//	}
//	public String getMaxDuration() {
//		return maxDuration;
//	}
//	public void setMaxDuration(String maxDuration) {
//		this.maxDuration = maxDuration;
//	}
//	public String getDepth() {
//		return depth;
//	}
//	public void setDepth(String depth) {
//		this.depth = depth;
//	}
//	public String getExclusionRegExp() {
//		return exclusionRegExp;
//	}
//	public void setExclusionRegExp(String exclusionRegExp) {
//		this.exclusionRegExp = exclusionRegExp;
//	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "AuditSiteOrder [siteURL=" + siteURL + ", level=" + level
				+ ", tblMarker=" + tblMarker + ", prTblMarker=" + prTblMarker
				+ ", dcrImgMarker=" + dcrImgMarker + ", infImgMarker="
				+ infImgMarker + ", maxDocuments=" + maxDocuments
				+ ", maxDuration=" + maxDuration + ", depth=" + depth
				+ ", exclusionRegExp=" + exclusionRegExp + ", category="
				+ category + ", country=" + country + "]";
	}
}
