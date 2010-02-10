package org.opens.tanaguru.contentadapter.css;

import java.util.List;

public interface CSSOMStyleSheet {

	/**
	 * 
	 * @param rule
	 *            the rule to add
	 * @return true if the rule was successfully added, false otherwise
	 */
	boolean addCSSOMRule(CSSOMRule rule);

	/**
	 * 
	 * @return the line number
	 */
	int getLineNumber();

	/**
	 * 
	 * @return a list of rules
	 */
	List<CSSOMRule> getRules();

	/**
	 * 
	 * @return a style sheet type
	 */
	short getType();

	/**
	 * 
	 * @param lineNumber
	 *            the line number to set
	 */
	void setLineNumber(int lineNumber);

	/**
	 * 
	 * @param rules
	 *            the list of rules to set
	 */
	void setRules(List<CSSOMRule> rules);

	/**
	 * 
	 * @param type
	 *            the type to set
	 */
	void setType(short type);
}
