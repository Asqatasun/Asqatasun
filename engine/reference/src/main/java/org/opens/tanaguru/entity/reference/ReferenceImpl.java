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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "REFERENCE")
@XmlRootElement
public class ReferenceImpl implements Reference, Serializable {

    @Column(name = "Cd_Reference")
    protected String code;
    @OneToMany(mappedBy = "reference", cascade = CascadeType.ALL)
    protected List<CriterionImpl> criterionList = new ArrayList<CriterionImpl>();
    @Column(name = "Description")
    protected String description;
    @Id
    @GeneratedValue
    @Column(name = "Id_Reference")
    protected Long id;
    @Column(name = "Label", nullable = false)
    protected String label;
    @Column(name = "Rank")
    protected int rank;
    @Column(name = "Url", nullable = true)
    protected String url;

    public ReferenceImpl() {
        super();
    }

    public ReferenceImpl(String code, String label, String description) {
        this();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    public void addCriterion(Criterion criterion) {
        criterion.setReference(this);
        this.criterionList.add((CriterionImpl) criterion);
    }

    public String getCode() {
        return code;
    }

    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.CriterionImpl.class)
    public List<CriterionImpl> getCriterionList() {
        return criterionList;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getRank() {
        return rank;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCriterionList(List<? extends Criterion> criterionList) {
        this.criterionList = (List<CriterionImpl>) criterionList;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }
}
