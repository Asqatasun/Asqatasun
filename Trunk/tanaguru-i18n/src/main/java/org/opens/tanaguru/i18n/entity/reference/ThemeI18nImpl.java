package org.opens.tanaguru.i18n.entity.reference;

import org.opens.tanaguru.i18n.entity.reference.*;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.reference.ThemeImpl;
import org.opens.tanaguru.i18n.entity.AbstractInternationalizedEntity;
import com.adex.sdk.entity.i18n.Language;
import javax.persistence.Table;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "THEME_I18N")
public class ThemeI18nImpl extends AbstractInternationalizedEntity<Theme>
		implements ThemeI18n, Serializable {

	@Column(name = "Description")
	protected String description;
	@Column(name = "Label")
	protected String label;
	@ManyToOne
	@JoinColumn(name = "Id_Theme")
	protected ThemeImpl target;

	public ThemeI18nImpl() {
		super();
	}

	public ThemeI18nImpl(Language language, Theme target, String label,
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

	public Theme getTarget() {
		return target;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTarget(Theme target) {
		this.target = (ThemeImpl) target;
	}
}
