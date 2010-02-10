package org.opens.tanaguru.i18n.entity.reference;

import org.opens.tanaguru.i18n.entity.reference.*;
import com.adex.sdk.entity.i18n.Language;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.opens.tanaguru.entity.reference.Level;
import org.opens.tanaguru.entity.reference.LevelImpl;
import org.opens.tanaguru.i18n.entity.AbstractInternationalizedEntity;
import javax.persistence.Table;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "LEVEL_I18N")
public class LevelI18nImpl extends AbstractInternationalizedEntity<Level>
		implements LevelI18n, Serializable {

	@Column(name = "Description")
	protected String description;
	@Column(name = "Label")
	protected String label;
	@ManyToOne
	@JoinColumn(name = "Id_Level")
	protected LevelImpl target;

	public LevelI18nImpl() {
		super();
	}

	public LevelI18nImpl(Language language, Level target, String label,
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

	public Level getTarget() {
		return target;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTarget(Level target) {
		this.target = (LevelImpl) target;
	}
}
