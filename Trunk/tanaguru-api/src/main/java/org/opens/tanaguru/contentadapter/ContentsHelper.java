package org.opens.tanaguru.contentadapter;

import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public abstract class ContentsHelper {

	/**
	 * 
	 * @param contentList
	 *            the content list to extract SSP list from
	 * @return the list of SSP from the content list
	 */
	public static Collection<SSP> filterSSP(Collection<Content> contentList) {
		Collection sspList = new ArrayList<SSP>();
		for (Content content : contentList) {
			if (content instanceof SSP) {
				sspList.add(content);
			}
		}
		return sspList;
	}
}
