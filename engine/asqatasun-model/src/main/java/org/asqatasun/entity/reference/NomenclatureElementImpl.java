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
package org.asqatasun.entity.reference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "NOMENCLATURE_ELEMENT")
@XmlRootElement
public class NomenclatureElementImpl implements NomenclatureElement,
        Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Nomenclature_Element")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "Id_Nomenclature")
    private NomenclatureImpl nomenclature;
    @Column(name = "Label", nullable = false)
    private String label;

    public NomenclatureElementImpl() {
        super();
    }

    public NomenclatureElementImpl(String value) {
        super();
        this.label = value;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    @XmlTransient
    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = (NomenclatureImpl) nomenclature;
    }

    @Override
    public void setLabel(String value) {
        this.label = value;
    }
}