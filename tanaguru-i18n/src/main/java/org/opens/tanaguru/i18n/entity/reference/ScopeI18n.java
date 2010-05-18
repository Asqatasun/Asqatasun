package org.opens.tanaguru.i18n.entity.reference;

import org.opens.tanaguru.entity.reference.Scope;
import com.adex.sdk.entity.i18n.InternationalizedEntity;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface ScopeI18n extends InternationalizedEntity<Scope> {

	/**
	 * 
	 * @return the description
	 */
	String getDescription();

	/**
	 * 
	 * @return the label
	 */
	String getLabel();

	/**
	 * 
	 * @param description
	 *            the description to set
	 */
	void setDescription(String description);

	/**
	 * 
	 * @param label
	 *            the label to set
	 */
	void setLabel(String label);
}
