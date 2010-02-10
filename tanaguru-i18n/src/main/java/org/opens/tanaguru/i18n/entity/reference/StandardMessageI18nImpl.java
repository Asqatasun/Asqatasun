package org.opens.tanaguru.i18n.entity.reference;

import org.opens.tanaguru.i18n.entity.reference.*;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.opens.tanaguru.entity.reference.StandardMessage;
import org.opens.tanaguru.entity.reference.StandardMessageImpl;
import org.opens.tanaguru.i18n.entity.AbstractInternationalizedEntity;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "STANDARD_MESSAGE_I18N")
public class StandardMessageI18nImpl extends
		AbstractInternationalizedEntity<StandardMessage> implements
		StandardMessageI18n, Serializable {

	@Column(name = "Label")
	protected String label;
	@ManyToOne
	@JoinColumn(name = "Id_Standard_Message")
	protected StandardMessageImpl target;
	@Column(name = "Text")
	protected String text;

	public StandardMessageI18nImpl() {
		super();
	}

	public String getLabel() {
		return label;
	}

	public StandardMessage getTarget() {
		return target;
	}

	public String getText() {
		return text;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTarget(StandardMessage target) {
		this.target = (StandardMessageImpl) target;
	}

	public void setText(String text) {
		this.text = text;
	}
}
