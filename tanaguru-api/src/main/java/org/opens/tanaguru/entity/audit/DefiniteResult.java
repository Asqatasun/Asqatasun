package org.opens.tanaguru.entity.audit;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface DefiniteResult extends ProcessResult {

	/**
	 * 
	 * @return the definite value
	 */
	TestSolution getDefiniteValue();

	/**
	 * 
	 * @param testSolution
	 *            the definite value to set
	 */
	void setDefiniteValue(TestSolution testSolution);
}
