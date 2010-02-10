package org.opens.tanaguru.i18n.entity.reference;

import org.opens.tanaguru.i18n.entity.reference.*;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.opens.tanaguru.entity.reference.NomenclatureElement;
import org.opens.tanaguru.entity.reference.NomenclatureElementImpl;
import org.opens.tanaguru.i18n.entity.AbstractInternationalizedEntity;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "NOMENCLATURE_ELEMENT_I18N")
public class NomenclatureElementI18nImpl extends
		AbstractInternationalizedEntity<NomenclatureElement> implements
		NomenclatureElementI18n, Serializable {

	@ManyToOne
	@JoinColumn(name = "Id_Nomenclature_Element")
	protected NomenclatureElementImpl target;
	@Column(name = "Value")
	protected String value;

	public NomenclatureElementI18nImpl() {
		super();
	}

	public NomenclatureElement getTarget() {
		return target;
	}

	public String getValue() {
		return value;
	}

	public void setTarget(NomenclatureElement target) {
		this.target = (NomenclatureElementImpl) target;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
