package org.opens.tanaguru.entity.audit;

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
public class IndefiniteResultImpl extends ProcessResultImpl implements
        IndefiniteResult, Serializable {

    private static final long serialVersionUID = -9026725317465914229L;
    
    @Column(name = "Indefinite_Value")
    protected String indefiniteValue;

    public IndefiniteResultImpl() {
        super();
    }

    /**
     * @return the indefiniteValue
     */
    public String getIndefiniteValue() {
        return indefiniteValue;
    }

    public Object getValue() {
        return getIndefiniteValue();
    }

    /**
     * @param indefiniteValue
     *            the indefiniteValue to set
     */
    public void setIndefiniteValue(String indefiniteValue) {
        this.indefiniteValue = indefiniteValue;
    }

    public void setValue(Object value) {
        setIndefiniteValue((String) value);
    }
}
