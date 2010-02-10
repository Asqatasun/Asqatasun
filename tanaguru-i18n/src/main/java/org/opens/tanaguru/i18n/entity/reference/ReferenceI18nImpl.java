package org.opens.tanaguru.i18n.entity.reference;

import org.opens.tanaguru.i18n.entity.reference.*;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.ReferenceImpl;
import org.opens.tanaguru.i18n.entity.AbstractInternationalizedEntity;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "REFERENCE_I18N")
public class ReferenceI18nImpl extends
		AbstractInternationalizedEntity<Reference> implements ReferenceI18n,
		Serializable {

	@Column(name = "Description")
	private String description;
	@Column(name = "Label")
	protected String label;
	@ManyToOne
	@JoinColumn(name = "Id_Reference")
	protected ReferenceImpl target;

	public ReferenceI18nImpl() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public String getLabel() {
		return label;
	}

	public Reference getTarget() {
		return target;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTarget(Reference target) {
		this.target = (ReferenceImpl) target;
	}
}
