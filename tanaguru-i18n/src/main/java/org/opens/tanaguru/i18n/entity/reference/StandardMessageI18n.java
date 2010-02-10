package org.opens.tanaguru.i18n.entity.reference;

import org.opens.tanaguru.entity.reference.StandardMessage;
import com.adex.sdk.entity.i18n.InternationalizedEntity;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface StandardMessageI18n extends
		InternationalizedEntity<StandardMessage> {

	/**
	 * 
	 * @return the label
	 */
	String getLabel();

	/**
	 * 
	 * @return the text
	 */
	String getText();

	/**
	 * 
	 * @param label
	 *            the label to set
	 */
	void setLabel(String label);

	/**
	 * 
	 * @param text
	 *            the text to set
	 */
	void setText(String text);
}
