package org.opens.tanaguru.contentadapter.html.util;

/**
 * 
 * @author ADEX
 */
public interface TempHTMLFileManager {

	void create();

	void delete();

	String getSourceCode();

	String getTempFolder();

	String getURL();

	void setSourceCode(String sourceCode);

	void setTempFolder(String tempFolder);
}
