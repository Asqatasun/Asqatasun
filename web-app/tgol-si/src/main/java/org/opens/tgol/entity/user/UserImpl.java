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
package org.opens.tgol.entity.user;

import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ContractImpl;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
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
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "TGSI_USER")
@XmlRootElement
public class UserImpl implements User, Serializable {

    private static final long serialVersionUID = -6192287245458300859L;
    
    @Id
    @GeneratedValue
    @Column(name = "Id_User")
    private Long id;

    @Column(name = "Email1")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Name")
    private String name;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Address")
    private String address;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Identica_Id")
    private String identicaId;

    @Column(name = "Twitter_Id")
    private String twitterId;

    @Column(name = "Web1")
    private String webUrl1;

    @Column(name = "Web2")
    private String webUrl2;

    @Column(name = "Activated")
    private boolean isAccountActivated;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "ROLE_Id_Role")
    private RoleImpl role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<ContractImpl> contractSet = new LinkedHashSet<ContractImpl>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getEmail1() {
        return email;
    }

    @Override
    public void setEmail1(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getWebUrl1() {
        return this.webUrl1;
    }

    @Override
    public void setWebUrl1(String webUrl1) {
        this.webUrl1 = webUrl1;
    }

    @Override
    public String getWebUrl2() {
        return this.webUrl2;
    }

    @Override
    public void setWebUrl2(String webUrl2) {
        this.webUrl2 = webUrl2;
    }

    @Override
    public String getIdenticaId() {
        return this.identicaId;
    }

    @Override
    public void setIdenticaId(String identicaId) {
        this.identicaId = identicaId;
    }

    @Override
    public String getTwitterId() {
        return this.twitterId;
    }

    @Override
    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    @Override
    public Role getRole() {
        return this.role;
    }

    @Override
    public void setRole(Role role) {
        this.role = (RoleImpl)role;
    }

    @Override
    public void addContract(Contract contract) {
        contract.setUser(this);
        this.contractSet.add((ContractImpl)contract);
    }

    @Override
    public void addAllContracts(Collection<? extends Contract> contractSet) {
        for (Contract contract : contractSet) {
            contract.setUser(this);
        }
        this.contractSet.addAll((Set<ContractImpl>)contractSet);
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tgol.entity.contract.ContractImpl.class)
    public Collection<ContractImpl> getContractSet() {
        return contractSet;
    }

    @Override
    public boolean isAccountActivated() {
        return isAccountActivated;
    }

    @Override
    public void setAccountActivation(boolean value) {
        this.isAccountActivated = value;
    }

}