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
package org.opens.tgol.entity.product;

import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ContractImpl;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "TGSI_PRODUCT")
@XmlRootElement
public class ProductImpl implements Product, Serializable {

    private static final long serialVersionUID = -6373251016557837684L;
    
    @Id
    @GeneratedValue
    @Column(name = "Id_Product")
    private Long id;

    @Column(name = "Code")
    private String code;

    @Column(name = "Label")
    private String label;

    @Column(name = "Description")
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ContractImpl> contractSet = new HashSet<ContractImpl>();

    @ManyToOne
    @JoinColumn(name = "SCOPE_Id_Scope", nullable = true)
    private TgsiScopeImpl scope;

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    protected Set<ActImpl> actSet = new HashSet<ActImpl>();

//    @ManyToOne
//    @JoinColumn(name = "FUNCTIONALITY_Id_Functionality", nullable = false)
//    protected FunctionalityImpl functionality;

//    @ManyToMany
//        @JoinTable(name = "PRODUCT_SCOPE", joinColumns =
//        @JoinColumn(name = "PRODUCT_Id_Product"), inverseJoinColumns =
//        @JoinColumn(name = "SCOPE_Id_Scope"))
//    Set<TgsiScopeImpl> scopeSet = new HashSet<TgsiScopeImpl>();

//    @ManyToMany
//        @JoinTable(name = "PRODUCT_RESTRICTION", joinColumns =
//        @JoinColumn(name = "PRODUCT_Id_Product"), inverseJoinColumns =
//        @JoinColumn(name = "RESTRICTION_Id_Restriction"))
//    Set<RestrictionImpl> restrictionSet = new HashSet<RestrictionImpl>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void addContract(Contract contract) {
        contract.setProduct(this);
        this.contractSet.add((ContractImpl)contract);
    }

    @Override
    public void addAllContracts(Set<? extends Contract> contractSet) {
        for (Contract contract : contractSet) {
            contract.setProduct(this);
        }
        this.contractSet.addAll((Set<ContractImpl>)contractSet);
    }

    @Override
    public Set<ContractImpl> getContractSet() {
        return contractSet;
    }

    @Override
    public TgsiScopeImpl getScope() {
        return scope;
    }

    @Override
    public void setScope(Scope scope) {
        this.scope = (TgsiScopeImpl)scope;
    }

//    @Override
//    public Functionality getFunctionality() {
//        return functionality;
//    }
//
//    @Override
//    public void setFunctionality(Functionality functionality) {
//        this.functionality = (FunctionalityImpl)functionality;
//    }
//
//    @Override
//    public Set<RestrictionImpl> getRestrictionSet() {
//        return restrictionSet;
//    }
//
//    @Override
//    public void addRestriction(Restriction restriction) {
//        restriction.addProduct(this);
//        restrictionSet.add((RestrictionImpl)restriction);
//    }
//
//    @Override
//    public void addAllRestriction(Set<? extends Restriction> restrictionSet) {
//        for (Restriction restriction : restrictionSet) {
//            restriction.addProduct(this);
//        }
//        this.restrictionSet.addAll((Set<RestrictionImpl>)restrictionSet);
//    }
//
//    @Override
//    public Set<TgsiScopeImpl> getScopeSet() {
//        return scopeSet;
//    }
//
//    @Override
//    public void addScope(Scope scope) {
//        scope.addProduct(this);
//        scopeSet.add((TgsiScopeImpl)scope);
//    }
//
//    @Override
//    public void addAllScope(Set<? extends Scope> scopeSet) {
//        for (Scope scope : scopeSet) {
//            scope.addProduct(this);
//        }
//        this.scopeSet.addAll((Set<TgsiScopeImpl>)scopeSet);;
//    }
//
//    @Override
//    public Set<ActImpl> getActSet() {
//        return actSet;
//    }
//
//    @Override
//    public void addAct(Act act) {
//        act.setProduct(this);
//        actSet.add((ActImpl)act);
//    }
//
//    @Override
//    public void addAllAct(Set<? extends Act> actSet) {
//        for (Act act : actSet) {
//            act.setProduct(this);
//        }
//        this.actSet.addAll((Set<ActImpl>)actSet);
//    }

}