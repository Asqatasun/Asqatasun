package org.opens.tanaguru.entity.factory.subject;

import org.opens.tanaguru.entity.subject.Page;
import com.adex.sdk.entity.factory.GenericFactory;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface PageFactory extends GenericFactory<Page> {

	/**
	 * 
	 * @param url
	 *            the url to set
	 * @return a new instance of Page
	 */
	Page create(String url);
}
