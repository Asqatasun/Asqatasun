package org.opens.tanaguru.entity.reference;

import com.adex.sdk.entity.Entity;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface NomenclatureElement extends Entity {

	/**
	 * 
	 * @return the nomenclature
	 */
	Nomenclature getNomenclature();

	/**
	 * 
	 * @return the value
	 */
	String getLabel();

	/**
	 * 
	 * @param nomenclature
	 *            the nomenclature to set
	 */
	void setNomenclature(Nomenclature nomenclature);

	/**
	 * 
	 * @param value
	 *            the value to set
	 */
	void setLabel(String value);
}
