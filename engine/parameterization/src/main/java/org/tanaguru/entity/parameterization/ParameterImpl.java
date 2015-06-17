/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.entity.parameterization;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @XmlElementRef(type = org.tanaguru.entity.parameterization.ParameterElementImpl.class)
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonSubTypes({
        @JsonSubTypes.Type(value=org.tanaguru.entity.parameterization.ParameterElementImpl.class, name="ParameterElement")})
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