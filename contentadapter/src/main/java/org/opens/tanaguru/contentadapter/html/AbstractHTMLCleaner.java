package org.opens.tanaguru.contentadapter.html;

import org.opens.tanaguru.contentadapter.HTMLCleaner;

/**
 * 
 * @author ADEX
 */
public abstract class AbstractHTMLCleaner implements HTMLCleaner {

	protected String dirtyHTML;
	protected String result;

	public AbstractHTMLCleaner() {
		super();
	}

	public String getDirtyHTML() {
		return dirtyHTML;
	}

	public String getResult() {
		return result;
	}

	public void setDirtyHTML(String dirtyHTML) {
		this.dirtyHTML = dirtyHTML;
	}
}
