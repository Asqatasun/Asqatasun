package org.opens.tanaguru.i18n.entity.reference;

import org.opens.tanaguru.entity.reference.Nomenclature;
import com.adex.sdk.entity.i18n.InternationalizedEntity;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface NomenclatureI18n extends InternationalizedEntity<Nomenclature> {

	/**
	 * 
	 * @return the description
	 */
	String getDescription();

	/**
	 * 
	 * @return the long label
	 */
	String getLongLabel();

	/**
	 * 
	 * @return the short label
	 */
	String getShortLabel();

	/**
	 * 
	 * @param description
	 *            the description to set
	 */
	void setDescription(String description);

	/**
	 * 
	 * @param longLabel
	 *            the long label to set
	 */
	void setLongLabel(String longLabel);

	/**
	 * 
	 * @param shortLabel
	 *            the short label to set
	 */
	void setShortLabel(String shortLabel);
}
