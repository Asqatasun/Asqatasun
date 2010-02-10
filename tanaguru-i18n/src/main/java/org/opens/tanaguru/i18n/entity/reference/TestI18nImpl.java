package org.opens.tanaguru.i18n.entity.reference;

import org.opens.tanaguru.i18n.entity.reference.*;
import com.adex.sdk.entity.i18n.Language;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.TestImpl;
import org.opens.tanaguru.i18n.entity.AbstractInternationalizedEntity;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "TEST_I18N")
public class TestI18nImpl extends AbstractInternationalizedEntity<Test>
		implements TestI18n, Serializable {

	@Column(name = "Description")
	protected String description;
	@Column(name = "Label")
	protected String label;
	@ManyToOne
	@JoinColumn(name = "Id_Test")
	protected TestImpl target;

	public TestI18nImpl() {
		super();
	}

	public TestI18nImpl(Language language, Test target, String label,
			String description) {
		super(language, target);
		this.label = label;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getLabel() {
		return label;
	}

	public Test getTarget() {
		return target;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTarget(Test target) {
		this.target = (TestImpl) target;
	}
}
