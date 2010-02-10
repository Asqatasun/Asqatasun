package org.opens.tanaguru.entity.reference;

import java.util.Collection;

import com.adex.sdk.entity.Entity;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface Rule extends Entity {

	/**
	 * 
	 * @return the name of the class
	 */
	String getClassName();

	/**
	 * 
	 * @return the description
	 */
	String getDescription();

	/**
	 * 
	 * @return the owning package
	 */
	RulePackage getOwningPackage();

	Collection<? extends Test> getTestList();

	/**
	 * 
	 * @param className
	 *            the name of the class to set
	 */
	void setClassName(String className);

	/**
	 * 
	 * @param description
	 *            the description to set
	 */
	void setDescription(String description);

	/**
	 * 
	 * @param owningPackage
	 *            the owning package to set
	 */
	void setOwningPackage(RulePackage owningPackage);

	void setTestList(Collection<? extends Test> testList);
}
