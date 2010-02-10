package org.opens.tanaguru.entity.factory.reference;

import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.reference.ThemeImpl;

/**
 * 
 * @author ADEX
 */
public class ThemeFactoryImpl implements ThemeFactory {

	public ThemeFactoryImpl() {
		super();
	}

	public Theme create() {
		return new ThemeImpl();
	}
}
