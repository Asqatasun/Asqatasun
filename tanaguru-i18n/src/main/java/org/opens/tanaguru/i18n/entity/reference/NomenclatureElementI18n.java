package org.opens.tanaguru.i18n.entity.reference;

import org.opens.tanaguru.entity.reference.NomenclatureElement;
import com.adex.sdk.entity.i18n.InternationalizedEntity;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface NomenclatureElementI18n extends
		InternationalizedEntity<NomenclatureElement> {

	/**
	 * 
	 * @return the value
	 */
	String getValue();

	/**
	 * 
	 * @param value
	 *            the value to set
	 */
	void setValue(String value);
}
