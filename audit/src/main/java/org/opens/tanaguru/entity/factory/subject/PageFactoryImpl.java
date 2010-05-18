package org.opens.tanaguru.entity.factory.subject;

import org.opens.tanaguru.entity.factory.subject.*;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.PageImpl;

/**
 * 
 * @author ADEX
 */
public class PageFactoryImpl implements PageFactory {

	public PageFactoryImpl() {
		super();
	}

	public Page create() {
		return new PageImpl();
	}

	public Page create(String url) {
		Page page = new PageImpl();
		page.setURL(url);
		return page;
	}
}
