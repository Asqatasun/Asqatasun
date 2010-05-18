package org.opens.tanaguru.contentadapter.css;

import java.util.List;

import org.w3c.css.sac.Condition;
import org.w3c.css.sac.Selector;

public interface CSSOMSelector {

	/**
	 * 
	 * @param declaration
	 *            the declaration to add
	 * @return true if the declaration was successfully added, false otherwise
	 */
	boolean addCSSOMDeclaration(CSSOMDeclaration declaration);

	/**
	 * 
	 * @param condition
	 *            a w3c condition interface
	 * @return the string representation of the w3c condition
	 */
	String conditionToString(Condition condition);

	/**
	 * 
	 * @return a list of owner declaration
	 */
	List<CSSOMDeclaration> getOwnerDeclaration();

	/**
	 * 
	 * @return the selector object
	 */
	Selector getSelector();

	/**
	 * 
	 * @return the selector text
	 */
	String getSelectorTxt();

	/**
	 * 
	 * @param selector
	 *            a w3c selector interface
	 * @return the string representation of the w3c selector
	 */
	String selectorToString(Selector selector);

	/**
	 * 
	 * @param selector
	 *            the selector object to set
	 */
	void setSelector(Selector selector);

	/**
	 * 
	 * @param selectorDeclaration
	 *            the list of the owner declarations to set
	 */
	void setSelectorDeclaration(List<CSSOMDeclaration> selectorDeclaration);

	/**
	 * 
	 * @param selectorTxt
	 *            the selector text to set
	 */
	void setSelectorTxt(String selectorTxt);
}
