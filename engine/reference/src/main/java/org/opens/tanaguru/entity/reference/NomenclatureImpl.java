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
package org.opens.tanaguru.entity.reference;

import org.opens.tanaguru.entity.service.reference.NomenclatureCssUnit;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "NOMENCLATURE")
@XmlRootElement
public class NomenclatureImpl implements Nomenclature, Serializable {

    @Column(name = "Cd_Nomenclature")
    protected String code;
    @Column(name = "Description")
    protected String description;
    @OneToMany(mappedBy = "nomenclature", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    protected Collection<NomenclatureElementImpl> elementList = new HashSet<NomenclatureElementImpl>();
    @Id
    @GeneratedValue
    @Column(name = "Id_Nomenclature")
    protected Long id;
    @Column(name = "Long_Label")
    protected String longLabel;
    @ManyToOne
    @JoinColumn(name = "Id_Nomenclature_Parent")
    protected NomenclatureImpl parent;
    @Column(name = "Short_Label")
    protected String shortLabel;

    public NomenclatureImpl() {
        super();
    }

    public NomenclatureImpl(String code) {
        this();
        this.code = code;
    }

    public void addElement(NomenclatureElement element) {
        element.setNomenclature(this);
        elementList.add((NomenclatureElementImpl) element);
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.reference.NomenclatureElementImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.reference.NomenclatureCssUnitImpl.class)})
    public Collection<NomenclatureElementImpl> getElementList() {
        return elementList;
    }

    public Long getId() {
        return id;
    }

    public Collection<Integer> getIntegerValueList() {
        Collection<Integer> values = new HashSet<Integer>();
        for (NomenclatureElement element : elementList) {
            if (element instanceof NomenclatureCssUnit) {
                NomenclatureCssUnit cssUnitElement = (NomenclatureCssUnit) element;
                values.add(cssUnitElement.getCssShortValue());
            }
        }
        return values;
    }

    public String getLongLabel() {
        return longLabel;
    }

    public Nomenclature getParent() {
        return parent;
    }

    public String getShortLabel() {
        return shortLabel;
    }

    public Collection<String> getValueList() {
        Collection<String> values = new HashSet<String>();
        for (NomenclatureElement element : elementList) {
            values.add(element.getLabel());
        }
        return values;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setElementList(
            Collection<? extends NomenclatureElement> elementList) {
        this.elementList = (Collection<NomenclatureElementImpl>) elementList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLongLabel(String longLabel) {
        this.longLabel = longLabel;
    }

    public void setParent(Nomenclature parent) {
        this.parent = (NomenclatureImpl) parent;
    }

    public void setShortLabel(String label) {
        this.shortLabel = label;
    }
}
