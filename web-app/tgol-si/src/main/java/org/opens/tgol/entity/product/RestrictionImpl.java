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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "TGSI_RESTRICTION")
@XmlRootElement
public class RestrictionImpl implements Restriction, Serializable {

    private static final long serialVersionUID = 866337625495716065L;
    
    @Id
    @GeneratedValue
    @Column(name = "Id_Restriction")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Id_Restriction_Element")
    private RestrictionElementImpl restrictionElement;

    @Column(name = "Restriction_Value")
    private String value;

    @ManyToMany
        @JoinTable(name = "TGSI_CONTRACT_RESTRICTION", joinColumns =
        @JoinColumn(name = "RESTRICTION_Id_Restriction"), inverseJoinColumns =
        @JoinColumn(name = "CONTRACT_Id_Contract"))
    private Set<ContractImpl> contractSet = new HashSet<ContractImpl>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public RestrictionElement getRestrictionElement() {
        return restrictionElement;
    }

    @Override
    public void setRestrictionElement(RestrictionElement restrictionElement) {
        this.restrictionElement = (RestrictionElementImpl)restrictionElement;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public Set<ContractImpl> getContractSet() {
        return contractSet;
    }

    @Override
    public void addContract(Contract contract) {
        this.contractSet.add((ContractImpl)contract);
    }

    @Override
    public void addAllContract(Set<? extends Contract> contractSet) {
        this.contractSet.addAll((Set<ContractImpl>)contractSet);
    }

}