package org.opens.tanaguru.entity.reference;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "DECISION_LEVEL")
@XmlRootElement
public class DecisionLevelImpl implements DecisionLevel, Serializable {

	@Column(name = "Cd_Decision_Level")
	protected String code;
	@Column(name = "Description")
	protected String description;
	@Id
	@GeneratedValue
	@Column(name = "Id_Decision_Level")
	protected Long id;
	@Column(name = "Label", nullable = false)
	protected String label;

	public DecisionLevelImpl() {
		super();
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
