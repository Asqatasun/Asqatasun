package org.opens.tanaguru.i18n.entity.reference;

import org.opens.tanaguru.i18n.entity.reference.*;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.CriterionImpl;
import org.opens.tanaguru.i18n.entity.AbstractInternationalizedEntity;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "CRITERION_I18N")
public class CriterionI18nImpl extends
		AbstractInternationalizedEntity<Criterion> implements CriterionI18n,
		Serializable {

	@Column(name = "Description")
	protected String description;
	@Column(name = "Label")
	protected String label;
	@ManyToOne
	@JoinColumn(name = "Id_Criterion")
	protected CriterionImpl target;

	public CriterionI18nImpl() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public String getLabel() {
		return label;
	}

	public Criterion getTarget() {
		return target;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTarget(Criterion target) {
		this.target = (CriterionImpl) target;
	}
}
