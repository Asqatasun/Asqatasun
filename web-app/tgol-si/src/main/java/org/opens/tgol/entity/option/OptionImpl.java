/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tgol.entity.option;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "TGSI_OPTION")
@XmlRootElement
public class OptionImpl implements Option, Serializable {

    private static final long serialVersionUID = 866337625495716065L;
    
    @Id
    @GeneratedValue
    @Column(name = "Id_Option")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "OPTION_ELEMENT_Id_Option_Element")
    private OptionElementImpl optionElement;

    @Column(name = "Option_Value")
    private String value;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public OptionElement getOptionElement() {
        return optionElement;
    }

    @Override
    public void setOptionElement(OptionElement optionElement) {
        this.optionElement = (OptionElementImpl)optionElement;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

}