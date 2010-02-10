package org.opens.tanaguru.entity.reference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
@Entity
@Table(name = "STANDARD_MESSAGE")
@XmlRootElement
public class StandardMessageImpl implements StandardMessage {

	@Column(name = "Cd_Standard_Message")
	protected String code;
	@Id
	@GeneratedValue
	@Column(name = "Id_Standard_Message")
	protected Long id;
	@Column(name = "Label")
	protected String label;
	@Column(name = "Text")
	protected String text;

	public StandardMessageImpl() {
		super();
	}

	public StandardMessageImpl(String code, String text) {
		this();
		this.code = code;
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public Long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public String getText() {
		return text;
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

	public void setText(String text) {
		this.text = text;
	}
}
