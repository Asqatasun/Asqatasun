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
@Table(name = "PARAMETER_ELEMENT")
@XmlRootElement
public class ParameterElementImpl implements ParameterElement, Serializable{

    @Id
    @GeneratedValue
    @Column(name = "Id_Parameter_Element")
    private Long id;

    @Column(name = "Cd_Parameter_Element")
    private String parameterElementCode;

    @ManyToOne
    @JoinColumn(name = "Id_Parameter_Family")
    private ParameterFamilyImpl parameterFamily;

    @Column(name = "Short_Label")
    private String shortLabel;

    @Column(name = "Long_Label")
    private String longLabel;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getParameterElementCode() {
        return parameterElementCode;
    }

    @Override
    public  void setParameterElementCode(String parameterElementCode) {
        this.parameterElementCode = parameterElementCode;
    }

    @Override
    public ParameterFamily getParameterFamily() {
        return parameterFamily;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tanaguru.entity.parameterization.ParameterFamilyImpl.class)
    public void setParameterFamily(ParameterFamily parameterFamily) {
        this.parameterFamily = (ParameterFamilyImpl)parameterFamily;
    }

    @Override
    public String getLongLabel() {
        return longLabel;
    }

    @Override
    public void setLongLabel(String longLabel) {
        this.longLabel = longLabel;
    }

    @Override
    public String getShortLabel() {
        return shortLabel;
    }

    @Override
    public void setShortLabel(String shortLabel) {
        this.shortLabel = shortLabel;
    }

}