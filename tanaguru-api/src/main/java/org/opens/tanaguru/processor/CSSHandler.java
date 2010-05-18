package org.opens.tanaguru.processor;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import java.util.Collection;

public interface CSSHandler {

	/**
	 * 
	 * @return the current CSSHandler instance
	 */
	CSSHandler beginSelection();

	/**
	 * 
	 * @param blacklist
	 *            the list of prevented values
	 * @return the result of the check processing
	 */
	TestSolution checkRelativeUnitExists(Collection<Integer> blacklist);

	/**
	 * 
	 * @return a collection of ProcessRemark
	 */
	Collection<ProcessRemark> getRemarkList();

	/**
	 * 
	 * @return the current CSSHandler instance
	 */
	CSSHandler selectAllRules();

        /**
	 *
	 * @return the current CSSHandler instance
	 */
	CSSHandler keepRulesWithMedia(Collection<String> mediaNames);

	/**
	 * 
	 * @param processRemarkFactory
	 *            the ProcessRemarkFactory to set
	 */
	void setProcessRemarkFactory(ProcessRemarkFactory processRemarkFactory);

	/**
	 * 
	 * @param remarkList
	 *            the remakList to Set
	 */
	void setRemarkList(Collection<ProcessRemark> remarkList);

	/**
	 * 
	 * @param sourceCodeRemarkFactory
	 */
	void setSourceCodeRemarkFactory(
			SourceCodeRemarkFactory sourceCodeRemarkFactory);

	/**
	 * 
	 * @param ssp
	 *            the SSP so set
	 */
	void setSSP(SSP ssp);
}
