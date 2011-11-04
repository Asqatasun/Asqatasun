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
@Table(name = "RULE_PACKAGE")
@XmlRootElement
public class RulePackageImpl implements RulePackage {

    @Column(name = "Description")
    protected String description;
    @Id
    @GeneratedValue
    @Column(name = "Id_Rule_Package")
    protected Long id;
    @Column(name = "Package_Name")
    protected String packageName;
    @OneToMany(mappedBy = "owningPackage", cascade = CascadeType.ALL)
    protected Collection<RuleImpl> ruleList;

    public RulePackageImpl() {
        super();
        ruleList = new ArrayList<RuleImpl>();
    }

    public RulePackageImpl(String packageName, String description) {
        this();
        this.packageName = packageName;
        this.description = description;
    }

    public void addRule(Rule rule) {
        rule.setOwningPackage(this);
        this.ruleList.add((RuleImpl) rule);
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getPackageName() {
        return packageName;
    }

    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.RuleImpl.class)
    public Collection<RuleImpl> getRuleList() {
        return ruleList;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setRuleList(Collection<? extends Rule> ruleList) {
        this.ruleList = (Collection<RuleImpl>) ruleList;
    }
}
