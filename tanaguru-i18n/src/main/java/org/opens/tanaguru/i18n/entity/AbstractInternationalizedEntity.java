package org.opens.tanaguru.i18n.entity;

import com.adex.sdk.entity.i18n.Language;
import com.adex.sdk.entity.i18n.InternationalizedEntity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author ADEX
 */
@MappedSuperclass
public abstract class AbstractInternationalizedEntity<E> implements
		InternationalizedEntity<E>, Serializable {

	@Id
	@GeneratedValue
	@Column(name = "Id_I18n")
	protected Long id;
	@ManyToOne
	@JoinColumn(name = "Id_Language")
	protected LanguageImpl language;

	public AbstractInternationalizedEntity() {
		super();
	}

	public AbstractInternationalizedEntity(Language language) {
		super();
		this.language = (LanguageImpl) language;
	}

	public AbstractInternationalizedEntity(Language language, E target) {
		super();
		this.language = (LanguageImpl) language;
		this.setTarget(target);
	}

	public Long getId() {
		return id;
	}

	public Language getLanguage() {
		return language;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLanguage(Language language) {
		this.language = (LanguageImpl) language;
	}
}
