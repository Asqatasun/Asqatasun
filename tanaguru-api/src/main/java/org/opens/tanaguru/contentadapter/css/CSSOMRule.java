package org.opens.tanaguru.contentadapter.css;

import java.util.List;
import org.w3c.css.sac.SACMediaList;

public interface CSSOMRule {

	/**
	 * 
	 * @param declaration
	 *            the declaration to add
	 * @return true if the declaration was added, false otherwise
	 */
	boolean addCSSOMDeclaration(CSSOMDeclaration declaration);

	/**
	 * 
	 * @param selector
	 *            the selector to add
	 * @return true if the selector was added, false otherwise
	 */
	boolean addCSSOMSelector(CSSOMSelector selector);

	/**
	 * 
	 * @return a list of declarations
	 */
	List<CSSOMDeclaration> getDeclarations();

	/**
	 * 
	 * @return the owner style sheet
	 */
	CSSOMStyleSheet getOwnerStyle();

	/**
	 * 
	 * @return a list of selectors
	 */
	List<CSSOMSelector> getSelectors();

        /**
	 *
	 * @return a list of selectors
	 */
	SACMediaList getMediaList();

	/**
	 * 
	 * @param declarations
	 *            the list of declarations to set
	 */
	void setDeclarations(List<CSSOMDeclaration> declarations);

	/**
	 * 
	 * @param ownerStyle
	 *            the StyleSheet to set
	 */
	void setOwnerStyle(CSSOMStyleSheet ownerStyle);

	/**
	 * 
	 * @param selectors
	 *            the list of selectors to set
	 */
	void setSelectors(List<CSSOMSelector> selectors);

        /**
         * 
         * @param mediaList
         *              the list of media to set
         */
	void setMediaList(SACMediaList mediaList);

}
