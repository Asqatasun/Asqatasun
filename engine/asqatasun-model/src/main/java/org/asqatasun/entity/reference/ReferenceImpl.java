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

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "REFERENCE")
@XmlRootElement
public class ReferenceImpl implements Reference, Serializable {

    @Column(name = "Cd_Reference")
    private String code;
    @OneToMany(mappedBy = "reference", cascade = CascadeType.ALL)
    @JsonIgnore
    private final List<CriterionImpl> criterionList = new ArrayList<>();
    @Column(name = "Description")
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Reference")
    private Long id;
    @Column(name = "Label", nullable = false)
    private String label;
    @Column(name = "Rank")
    private int rank;
    @Column(name = "Url", nullable = true)
    private String url;
    @ManyToOne
    @JoinColumn(name = "Id_Default_Level")
    private LevelImpl defaultLevel;
    
    public ReferenceImpl() {
        super();
    }

    public ReferenceImpl(String code, String label, String description) {
        this();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    @Override
    public void addCriterion(Criterion criterion) {
        criterion.setReference(this);
        this.criterionList.add((CriterionImpl) criterion);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.asqatasun.entity.reference.CriterionImpl.class)
    public List<Criterion> getCriterionList() {
        return (ArrayList<Criterion>)(ArrayList)criterionList;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public int getRank() {
        return rank;
    }
    
    @Override
    @XmlElementRef(type = org.asqatasun.entity.reference.LevelImpl.class)
    public Level getDefaultLevel() {
        return defaultLevel;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setCriterionList(List<Criterion> criterionList) {
        for (Criterion cr : criterionList) {
            this.criterionList.add((CriterionImpl)cr);
        }
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
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

    @Override
    public void setDefaultLevel(Level defaultLevel) {
        this.defaultLevel = (LevelImpl)defaultLevel;
    }

}
