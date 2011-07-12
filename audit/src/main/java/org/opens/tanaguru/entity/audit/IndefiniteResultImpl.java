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
    
    @Column(name = "Indefinite_Value", length = 4096)
    protected String indefiniteValue;

    public IndefiniteResultImpl() {
        super();
    }

    /**
     * @return the indefiniteValue
     */
    @Override
    public String getIndefiniteValue() {
        return indefiniteValue;
    }

    @Override
    public Object getValue() {
        return getIndefiniteValue();
    }

    /**
     * @param indefiniteValue
     *            the indefiniteValue to set
     */
    @Override
    public void setIndefiniteValue(String indefiniteValue) {
        this.indefiniteValue = indefiniteValue;
    }

    @Override
    public void setValue(Object value) {
        setIndefiniteValue((String) value);
    }
}
