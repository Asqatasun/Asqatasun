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
package org.asqatasun.entity.audit;

import org.asqatasun.entity.audit.Evidence;
import org.asqatasun.entity.audit.EvidenceElement;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "EVIDENCE")
@XmlRootElement
public class EvidenceImpl implements Evidence, Serializable {

    private static final long serialVersionUID = -5891443605163793783L;
    @Column(name = "Cd_Evidence")
    private String code;
    @Column(name = "Description")
    private String description;
    @OneToMany(mappedBy = "evidence", cascade = CascadeType.ALL)
    @JsonIgnore
    private final Set<EvidenceElementImpl> elementList = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Evidence")
    private Long id;
    @Column(name = "Long_Label")
    private String longLabel;

    public EvidenceImpl() {
        super();
    }

    public EvidenceImpl(String code) {
        this();
        this.code = code;
    }

    @Override
    public void addElement(EvidenceElement element) {
        element.setEvidence(this);
        elementList.add((EvidenceElementImpl) element);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.asqatasun.entity.audit.EvidenceElementImpl.class)})
    public Collection<EvidenceElement> getElementList() {
        return (HashSet)elementList;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Collection<Integer> getIntegerValueList() {
//        Collection<Integer> values = new HashSet<Integer>();
//        for (EvidenceElement element : elementList) {
//            NomenclatureCssUnit cssUnitElement = (NomenclatureCssUnit) element;
//            values.add(element.getCssShortValue());
//        }
//        return values;
        return null;
    }

    @Override
    public String getLongLabel() {
        return longLabel;
    }

    @Override
    @JsonIgnore
    public Collection<String> getValueList() {
        Collection<String> values = new HashSet<>();
        for (EvidenceElement element : elementList) {
            values.add(element.getValue());
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
            Collection<EvidenceElement> elementList) {
        for (EvidenceElement evEl : elementList) {
            this.elementList.add((EvidenceElementImpl)evEl);
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
    
}