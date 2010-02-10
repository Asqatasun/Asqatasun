package org.opens.tanaguru.entity.reference;

import java.util.Collection;

import com.adex.sdk.entity.Entity;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface RulePackage extends Entity {

	/**
	 * 
	 * @param rule
	 *            the rule to add
	 */
	public void addRule(Rule rule);

	/**
	 * 
	 * @return the description
	 */
	String getDescription();

	/**
	 * 
	 * @return the name of the package
	 */
	String getPackageName();

	/**
	 * 
	 * @return the rule list
	 */
	Collection<? extends Rule> getRuleList();

	/**
	 * 
	 * @param description
	 *            the description to set
	 */
	void setDescription(String description);

	/**
	 * 
	 * @param packageName
	 *            the name of the package to set
	 */
	void setPackageName(String packageName);

	/**
	 * 
	 * @param ruleList
	 *            the rule list to set
	 */
	void setRuleList(Collection<? extends Rule> ruleList);
}
