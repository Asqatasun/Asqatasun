package org.opens.tanaguru.i18n.entity;

import com.adex.sdk.entity.i18n.Language;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "LANGUAGE")
public class LanguageImpl implements Language, Serializable {

	@Column(name = "Code", nullable = false)
	protected String code;
	@Id
	@GeneratedValue
	@Column(name = "Id_Language")
	protected Long id;
	@Column(name = "Label", nullable = false)
	protected String label;

	public String getCode() {
		return code;
	}

	public Long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
