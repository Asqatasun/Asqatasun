package org.opens.tanaguru.i18n.entity.reference;

import org.opens.tanaguru.i18n.entity.reference.*;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.entity.reference.NomenclatureImpl;
import org.opens.tanaguru.i18n.entity.AbstractInternationalizedEntity;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "NOMENCLATURE_I18N")
public class NomenclatureI18nImpl extends
		AbstractInternationalizedEntity<Nomenclature> implements
		NomenclatureI18n, Serializable {

	@Column(name = "Description")
	protected String description;
	@Column(name = "Long_Label")
	protected String longLabel;
	@Column(name = "Short_Label")
	protected String shortLabel;
	@ManyToOne
	@JoinColumn(name = "Id_Nomenclature")
	protected NomenclatureImpl target;

	public NomenclatureI18nImpl() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public String getLongLabel() {
		return longLabel;
	}

	public String getShortLabel() {
		return shortLabel;
	}

	public Nomenclature getTarget() {
		return target;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLongLabel(String longLabel) {
		this.longLabel = longLabel;
	}

	public void setShortLabel(String shortLabel) {
		this.shortLabel = shortLabel;
	}

	public void setTarget(Nomenclature target) {
		this.target = (NomenclatureImpl) target;
	}
}
