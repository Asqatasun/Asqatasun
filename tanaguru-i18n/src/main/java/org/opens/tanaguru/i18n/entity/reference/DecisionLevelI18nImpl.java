package org.opens.tanaguru.i18n.entity.reference;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.opens.tanaguru.entity.reference.DecisionLevel;
import org.opens.tanaguru.entity.reference.DecisionLevelImpl;
import org.opens.tanaguru.i18n.entity.AbstractInternationalizedEntity;
import javax.persistence.Table;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "DECISION_LEVEL_I18N")
public class DecisionLevelI18nImpl extends
		AbstractInternationalizedEntity<DecisionLevel> implements
		DecisionLevelI18n, Serializable {

	@Column(name = "Description")
	protected String description;
	@Column(name = "Label")
	protected String label;
	@ManyToOne
	@JoinColumn(name = "Id_Decision_Level")
	protected DecisionLevelImpl target;

	public DecisionLevelI18nImpl() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public String getLabel() {
		return label;
	}

	public DecisionLevel getTarget() {
		return target;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTarget(DecisionLevel target) {
		this.target = (DecisionLevelImpl) target;
	}
}
