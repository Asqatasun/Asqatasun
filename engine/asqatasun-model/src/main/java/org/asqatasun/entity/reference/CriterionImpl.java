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
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "CRITERION")
@XmlRootElement
public class CriterionImpl implements Criterion, Serializable {

    @Column(name = "Cd_Criterion")
    private String code;
    @Column(name = "Description")
    @JsonIgnore
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Criterion")
    private Long id;
    @Column(name = "Label")
    private String label;
    @Column(name = "Url")
    private String url;
    @Column(name = "Rank")
    @JsonIgnore
    private int rank;
    @ManyToOne
    @JoinColumn(name = "Reference_Id_Reference")
    @JsonIgnore
    private ReferenceImpl reference;
    @OneToMany(mappedBy = "criterion", cascade = CascadeType.ALL)
    @JsonIgnore
    private final Collection<TestImpl> testList = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "Theme_Id_Theme")
    private ThemeImpl theme;

    public CriterionImpl() {
        super();
    }

    public CriterionImpl(String code, String label, String description) {
        super();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    @Override
    public void addTest(Test test) {
        test.setCriterion(this);
        this.testList.add((TestImpl) test);
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
    @XmlElementRef(type = ReferenceImpl.class)
    public Reference getReference() {
        return reference;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = TestImpl.class)
    public Collection<Test> getTestList() {
        return (Collection)testList;
    }

    @Override
    @XmlElementRef(type = ThemeImpl.class)
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonSubTypes({
        @JsonSubTypes.Type(value=ThemeImpl.class, name="Theme")})
    public Theme getTheme() {
        return theme;
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
    public void setReference(Reference reference) {
        this.reference = (ReferenceImpl) reference;
    }

    @Override
    public void setTestList(Collection<Test> testList) {
        for (Test test : testList) {
            this.testList.add((TestImpl)test);
        }
    }

    @Override
    public void setTheme(Theme theme) {
        this.theme = (ThemeImpl) theme;
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
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.code != null ? this.code.hashCode() : 0);
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final CriterionImpl other = (CriterionImpl) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return !(!Objects.equals(this.id, other.id) && (this.id == null || !this.id.equals(other.id)));
    }

}