package org.opens.tanaguru.entity.reference;

import org.opens.tanaguru.entity.service.reference.NomenclatureCssUnit;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author ADEX
 */
@Entity
@XmlRootElement
public class NomenclatureCssUnitImpl extends NomenclatureElementImpl implements
		NomenclatureCssUnit, Serializable {

	@Column(name = "shortValue")
	protected int cssShortValue;

	public NomenclatureCssUnitImpl() {
		super();
	}

	public NomenclatureCssUnitImpl(int cssShortValue, String cssStringValue) {
		super(cssStringValue);
		this.cssShortValue = cssShortValue;
	}

	public int getCssShortValue() {
		return cssShortValue;
	}

	public void setCssShortValue(int cssShortValue) {
		this.cssShortValue = cssShortValue;
	}
}
