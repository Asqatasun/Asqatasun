package org.opens.tanaguru.entity.reference;

import com.adex.sdk.entity.Entity;
import com.adex.sdk.entity.Reorderable;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface Test extends Entity, Reorderable {

	/**
	 * 
	 * @return the code
	 */
	String getCode();

	/**
	 * 
	 * @return the criterion
	 */
	Criterion getCriterion();

	/**
	 * 
	 * @return the decision level
	 */
	DecisionLevel getDecisionLevel();

	/**
	 * 
	 * @return the description
	 */
	String getDescription();

	/**
	 * 
	 * @return the full code
	 */
	String getFullCode();

	/**
	 * 
	 * @return the label
	 */
	String getLabel();

	/**
	 * 
	 * @return the level
	 */
	Level getLevel();

        /**
	 *
	 * @return the rule design url
	 */
	String getRuleDesignUrl();

	/**
	 * 
	 * @return the rule
	 * @deprecated
	 */
	Rule getRule();

	String getRuleArchiveName();

	String getRuleClassName();

	/**
	 * 
	 * @return the scope
	 */
	Scope getScope();

	/**
	 * 
	 * @param code
	 *            the code to set
	 */
	void setCode(String code);

	/**
	 * 
	 * @param criterion
	 *            the criterion to set
	 */
	void setCriterion(Criterion criterion);

	/**
	 * 
	 * @param decisionLevel
	 *            the decision level to set
	 */
	void setDecisionLevel(DecisionLevel decisionLevel);

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

	/**
	 * 
	 * @param level
	 *            the level to set
	 */
	void setLevel(Level level);

	/**
	 * 
	 * @param rule
	 *            the rule to set
	 * @deprecated
	 */
	void setRule(Rule rule);

	void setRuleArchiveName(String ruleArchiveName);

	void setRuleClassName(String ruleClassName);

	/**
	 * 
	 * @param scope
	 *            the scrope to set
	 */
	void setScope(Scope scope);

	void setRuleDesignUrl(String ruleDesignUrl);
}
