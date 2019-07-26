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

import org.asqatasun.entity.service.reference.NomenclatureCssUnit;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "NOMENCLATURE")
@XmlRootElement
public class NomenclatureImpl implements Nomenclature, Serializable {

    @Column(name = "Cd_Nomenclature")
    private String code;
    @Column(name = "Description")
    private String description;
    @OneToMany(mappedBy = "nomenclature", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Collection<NomenclatureElementImpl> elementList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Nomenclature")
    private Long id;
    @Column(name = "Long_Label")
    private String longLabel;
    @ManyToOne
    @JoinColumn(name = "Id_Nomenclature_Parent")
    private NomenclatureImpl parent;
    @Column(name = "Short_Label")
    private String shortLabel;

    public NomenclatureImpl() {
        super();
    }

    public NomenclatureImpl(String code) {
        this();
        this.code = code;
    }

    @Override
    public void addElement(NomenclatureElement element) {
        element.setNomenclature(this);
        elementList.add((NomenclatureElementImpl) element);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = NomenclatureElementImpl.class),
        @XmlElementRef(type = org.asqatasun.entity.reference.NomenclatureCssUnitImpl.class)})
    @Override
    public Collection<NomenclatureElement> getElementList() {
        return (Collection)elementList;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Collection<Integer> getIntegerValueList() {
        Collection<Integer> values = new HashSet<>();
        for (NomenclatureElement element : elementList) {
            if (element instanceof NomenclatureCssUnit) {
                NomenclatureCssUnit cssUnitElement = (NomenclatureCssUnit) element;
                values.add(cssUnitElement.getCssShortValue());
            }
        }
        return values;
    }

    @Override
    public String getLongLabel() {
        return longLabel;
    }

    @Override
    public Nomenclature getParent() {
        return parent;
    }

    @Override
    public String getShortLabel() {
        return shortLabel;
    }

    @Override
    public Collection<String> getValueList() {
        Collection<String> values = new HashSet<>();
        for (NomenclatureElement element : elementList) {
            values.add(element.getLabel());
        }
        return values;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setElementList(
            Collection<NomenclatureElement> elementList) {
        if (this.elementList == null) {
            this.elementList = new HashSet<>();
        }
        for (NomenclatureElement nomEl : elementList) {
            this.elementList.add((NomenclatureElementImpl)nomEl);
        }
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setLongLabel(String longLabel) {
        this.longLabel = longLabel;
    }

    @Override
    public void setParent(Nomenclature parent) {
        this.parent = (NomenclatureImpl) parent;
    }

    @Override
    public void setShortLabel(String label) {
        this.shortLabel = label;
    }

}