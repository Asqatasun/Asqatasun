package org.opens.tanaguru.i18n.entity.reference;

import org.opens.tanaguru.i18n.entity.reference.*;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.ScopeImpl;
import org.opens.tanaguru.i18n.entity.AbstractInternationalizedEntity;
import javax.persistence.Table;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "SCOPE_I18N")
public class ScopeI18nImpl extends AbstractInternationalizedEntity<Scope>
		implements ScopeI18n, Serializable {

	@Column(name = "Description")
	protected String description;
	@Column(name = "Label")
	protected String label;
	@ManyToOne
	@JoinColumn(name = "Id_Scope")
	protected ScopeImpl target;

	public ScopeI18nImpl() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public String getLabel() {
		return label;
	}

	public Scope getTarget() {
		return target;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTarget(Scope target) {
		this.target = (ScopeImpl) target;
	}
}
