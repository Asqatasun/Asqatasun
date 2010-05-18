package org.opens.tanaguru.entity.factory.reference;

import org.opens.tanaguru.entity.reference.Level;
import org.opens.tanaguru.entity.reference.LevelImpl;

/**
 * 
 * @author ADEX
 */
public class LevelFactoryImpl implements LevelFactory {

	public LevelFactoryImpl() {
		super();
	}

	public Level create() {
		return new LevelImpl();
	}
}
