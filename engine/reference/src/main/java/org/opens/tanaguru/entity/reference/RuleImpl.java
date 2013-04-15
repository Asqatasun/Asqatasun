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

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "RULE")
@XmlRootElement
public class RuleImpl implements Rule {

    @Column(name = "Class_Name", nullable = false)
    private String className;
    @Column(name = "Description")
    private String description;
    @Id
    @GeneratedValue
    @Column(name = "Id_Rule")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "Id_Rule_Package")
    private RulePackageImpl owningPackage;
    @OneToMany(mappedBy = "rule", cascade = CascadeType.ALL)
    private Collection<TestImpl> testList = new ArrayList<TestImpl>();

    public RuleImpl() {
        super();
    }

    public RuleImpl(String className, String description) {
        this();
        this.className = className;
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Rule)) {
            return false;
        }
        Rule rule = (Rule) obj;
        if (getOwningPackage() == null && rule.getOwningPackage() != null) {
            return false;
        }
        if (getClassName() == null && rule.getClassName() != null) {
            return false;
        }
        if (getDescription() == null && rule.getDescription() != null) {
            return false;
        }
        return getOwningPackage().equals(rule.getOwningPackage()) && getClassName().equals(rule.getClassName()) && getDescription().equals(rule.getDescription());
    }

    @Override
    public String getClassName() {
        return className;
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
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.RulePackageImpl.class)
    public RulePackage getOwningPackage() {
        return owningPackage;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.TestImpl.class)
    public Collection<Test> getTestList() {
        return (Collection<Test>)(ArrayList)testList;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        if (getOwningPackage() != null) {
            hashCode += getOwningPackage().hashCode();
        }
        if (getClassName() != null) {
            hashCode += getClassName().hashCode();
        }
        if (getDescription() != null) {
            hashCode += getDescription().hashCode();
        }
        return hashCode;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
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
    public void setOwningPackage(RulePackage owningPackage) {
        this.owningPackage = (RulePackageImpl) owningPackage;
    }

    @Override
    public void setTestList(Collection<Test> testList) {
        for (Test test : testList) {
            this.testList.add((TestImpl)test);
        }
    }
    
}