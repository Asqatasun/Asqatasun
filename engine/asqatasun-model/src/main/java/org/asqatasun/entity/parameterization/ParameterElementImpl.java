/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.entity.parameterization;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "PARAMETER_ELEMENT")
@XmlRootElement
public class ParameterElementImpl implements ParameterElement, Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @XmlElementRef(type = org.asqatasun.entity.parameterization.ParameterFamilyImpl.class)
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonSubTypes({
        @JsonSubTypes.Type(value=org.asqatasun.entity.parameterization.ParameterFamilyImpl.class, name="ParameterFamily")})
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