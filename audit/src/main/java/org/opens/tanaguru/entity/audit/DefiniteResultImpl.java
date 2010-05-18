package org.opens.tanaguru.entity.audit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author ADEX
 */
@Entity
@XmlRootElement
public class DefiniteResultImpl extends ProcessResultImpl implements
        DefiniteResult, Serializable {

    @Enumerated(EnumType.STRING)
    @Column(name = "Definite_Value")
    protected TestSolution definiteValue;

    public DefiniteResultImpl() {
        super();
    }

    public TestSolution getDefiniteValue() {
        return definiteValue;
    }

    public Object getValue() {
        return getDefiniteValue();
    }

    public void setDefiniteValue(TestSolution definiteValue) {
        this.definiteValue = definiteValue;
    }

    public void setValue(Object value) {
        setDefiniteValue((TestSolution) value);
    }
}
