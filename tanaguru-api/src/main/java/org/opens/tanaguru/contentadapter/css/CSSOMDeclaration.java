package org.opens.tanaguru.contentadapter.css;

import java.util.List;

public interface CSSOMDeclaration {

	/**
	 * 
	 * @return the property
	 */
	String getProperty();

	/**
	 * 
	 * @return a collection of rules
	 */
	List<CSSOMRule> getRule();

	/**
	 * 
	 * @return the unit
	 */
	short getUnit();

	/**
	 * 
	 * @return the preperty value
	 */
	String getValue();

	/**
	 * 
	 * @param property
	 *            the property to set
	 */
	void setProperty(String property);

	/**
	 * 
	 * 
	 * @param rule
	 *            the list of rules to set
	 */
	void setRule(List<CSSOMRule> rule);

	/**
	 * 
	 * @param unit
	 *            the unit to set
	 */
	void setUnit(short unit);

	/**
	 * 
	 * @param value
	 *            the value to set
	 */
	void setValue(String value);
}
