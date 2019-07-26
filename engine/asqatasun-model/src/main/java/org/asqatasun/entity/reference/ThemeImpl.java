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
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "THEME")
@XmlRootElement
public class ThemeImpl implements Theme, Serializable {

    @Column(name = "Cd_Theme")
    private String code;
    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
    @JsonIgnore
    private final Set<CriterionImpl> criterionList = new HashSet<>();
    @Column(name = "Description")
    @JsonIgnore
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Theme")
    private Long id;
    @Column(name = "Label", nullable = false)
    private String label;
    @Column(name = "Rank")
    @JsonIgnore
    private int rank;

    public ThemeImpl() {
        super();
    }

    public ThemeImpl(String code, String label, String description) {
        this();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.asqatasun.entity.reference.CriterionImpl.class)
    public Collection<Criterion> getCriterionList() {
        return (Collection)criterionList;
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
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setCriterionList(Collection<Criterion> criterionList) {
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
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + (this.code != null ? this.code.hashCode() : 0);
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ThemeImpl other = (ThemeImpl) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return Objects.equals(this.id, other.id) || (this.id != null && this.id.equals(other.id));
    }

	@Override
	public String toString() {
		return "ThemeImpl [id=" + id + ", label=" + label + "]";
	}
}
