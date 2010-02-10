package org.opens.tanaguru.processor;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import java.util.Collection;

/**
 * 
 * @author ADEX
 */
public interface JSHandler {

	/**
	 * 
	 * @return the current JSHandler
	 */
	public JSHandler beginSelection();

	/**
	 * 
	 * @return a collection of ProcessRemark
	 */
	public Collection<ProcessRemark> getRemarkList();

	/**
	 * 
	 * @return a SSP
	 */
	public SSP getSSP();

	/**
	 * 
	 * @return the current JSHandler with all the JavaScript content
	 */
	public JSHandler selectAllJS();

	/**
	 * 
	 * @return the current JSHandler with the external JavaScript
	 */
	public JSHandler selectExternalJS();

	/**
	 * 
	 * @return the current JSHandler with the Inline JavaScript
	 */
	public JSHandler selectInlineJS();

	/**
	 * 
	 * @return the current JavaScript with the local JavaScript
	 */
	public JSHandler selectLocalJS();

	/**
	 * 
	 * @param processRemarkFactory
	 *            the ProcessRemarkFactory to set
	 */
	public void setProcessRemarkFactory(
			ProcessRemarkFactory processRemarkFactory);

	/**
	 * 
	 * @param remarkList
	 *            the remarkList to set
	 */
	public void setRemarkList(Collection<ProcessRemark> remarkList);

	/**
	 * 
	 * @param sourceCodeRemarkFactory
	 *            the SourceCodeRemarkFactory to set
	 */
	public void setSourceCodeRemarkFactory(
			SourceCodeRemarkFactory sourceCodeRemarkFactory);

	/**
	 * 
	 * @param ssp
	 *            the SSP to set
	 */
	public void setSSP(SSP ssp);
}
