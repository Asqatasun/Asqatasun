package org.opens.tanaguru.contentadapter.css;

import java.util.List;

import java.io.Serializable;

/**
 * 
 * @author ADEX
 */
public class CSSOMDeclarationImpl implements CSSOMDeclaration, Serializable {

	private String property;
	private List<CSSOMRule> rule;
	private short unit;
	private String value;

	public CSSOMDeclarationImpl() {
		super();
	}

	public CSSOMDeclarationImpl(String property, String value, short unit) {
		super();
		this.property = property;
		this.value = value;
		this.unit = unit;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CSSOMDeclarationImpl other = (CSSOMDeclarationImpl) obj;
		if (property == null) {
			if (other.property != null) {
				return false;
			}
		} else if (!property.equals(other.property)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	public String getProperty() {
		return property;
	}

	public List<CSSOMRule> getRule() {
		return rule;
	}

	public short getUnit() {
		return unit;
	}

	public String getValue() {
		return value;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setRule(List<CSSOMRule> rule) {
		this.rule = rule;
	}

	public void setUnit(short unit) {
		this.unit = unit;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
