package org.opens.tanaguru.entity.reference;

import java.util.Collection;

import com.adex.sdk.entity.Entity;

/**
 * 
 * @author ADEX
 */
public interface Nomenclature extends Entity {

	/**
	 * 
	 * @param element
	 *            the element to add
	 */
	void addElement(NomenclatureElement element);

	/**
	 * 
	 * @return the code
	 */
	String getCode();

	/**
	 * 
	 * @return the description
	 */
	String getDescription();

	/**
	 * 
	 * @return the elements
	 */
	Collection<? extends NomenclatureElement> getElementList();

	/**
	 * 
	 * @return the integer values of the elements
	 */
	Collection<Integer> getIntegerValueList();

	/**
	 * 
	 * @return the long label
	 */
	String getLongLabel();

	/**
	 * 
	 * @return the parent
	 */
	Nomenclature getParent();

	/**
	 * 
	 * @return the short label
	 */
	String getShortLabel();

	/**
	 * 
	 * @return the values from the elements
	 */
	Collection<String> getValueList();

	/**
	 * 
	 * @param code
	 *            the code to set
	 */
	void setCode(String code);

	/**
	 * 
	 * @param description
	 *            the description to set
	 */
	void setDescription(String description);

	/**
	 * 
	 * @param elements
	 *            the elements to set
	 */
	void setElementList(Collection<? extends NomenclatureElement> elements);

	/**
	 * 
	 * @param longLabel
	 *            the long label to set
	 */
	void setLongLabel(String longLabel);

	/**
	 * 
	 * @param parent
	 *            the parent to set
	 */
	void setParent(Nomenclature parent);

	/**
	 * 
	 * @param shortLabel
	 *            the short label to set
	 */
	void setShortLabel(String shortLabel);
}
