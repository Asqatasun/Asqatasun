/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.parameterization;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "PARAMETER")
@XmlRootElement
public class ParameterImpl implements Parameter, Serializable {

    private static final long serialVersionUID = -4716887460453311773L;

    @Id
    @GeneratedValue
    @Column(name = "Id_Parameter")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Id_Parameter_Element")
    private ParameterElementImpl parameterElement;

    @Column(name="Parameter_Value")
    private String parameterValue;

    @Column(name = "Is_Default")
    private Boolean isDefaultParameterValue = null;

    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public ParameterElement getParameterElement() {
        return parameterElement;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tanaguru.entity.parameterization.ParameterElementImpl.class)
    public void setParameterElement(ParameterElement parameterElement) {
        this.parameterElement = (ParameterElementImpl)parameterElement;
    }

    @Override
    public String getValue() {
        return parameterValue;
    }

    @Override
    public void setValue(String value) {
        this.parameterValue = value;
    }

    @Override
    public Boolean isDefaultParameterValue() {
        if (isDefaultParameterValue == null) {
            return isDefaultParameterValue;
        }
        return isDefaultParameterValue;
    }

    @Override
    public void setDefaultParameterValue(Boolean isDefaultParameterValue) {
        this.isDefaultParameterValue = isDefaultParameterValue;
    }

}