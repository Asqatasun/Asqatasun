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
package org.asqatasun.webapp.entity.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.asqatasun.webapp.entity.contract.Contract;
import org.asqatasun.webapp.entity.contract.ContractImpl;
import org.asqatasun.webapp.entity.option.OptionElement;
import org.asqatasun.webapp.entity.option.OptionElementImpl;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Collection<ContractImpl> contractSet = new LinkedHashSet<>();

    @ManyToMany
        @JoinTable(name = "TGSI_USER_OPTION_ELEMENT", joinColumns =
        @JoinColumn(name = "USER_Id_User"), inverseJoinColumns =
        @JoinColumn(name = "OPTION_ELEMENT_Id_Option_Element"))
    Set<OptionElementImpl> optionElementSet = new HashSet<>();
    
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
    public void addAllContracts(Collection<Contract> contractSet) {
        for (Contract contract : contractSet) {
            contract.setUser(this);
            if (contract instanceof ContractImpl) {
                this.contractSet.add((ContractImpl)contract);
            }
        }
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.asqatasun.webapp.entity.contract.ContractImpl.class)
    public Collection<Contract> getContractSet() {
        return (Collection)contractSet;
    }

    @Override
    public boolean isAccountActivated() {
        return isAccountActivated;
    }
    
    @Override
    public void setAccountActivation(boolean value) {
        this.isAccountActivated = value;
    }

    @Override
    public Collection<OptionElement> getOptionElementSet() {
        return (Collection)this.optionElementSet;
    }

    @Override
    public void addOptionElement(OptionElement option) {
        optionElementSet.add((OptionElementImpl)option);
    }

    @Override
    public void addAllOptionElement(Collection<OptionElement> optionElementSet) {
        this.optionElementSet = new HashSet<>();
        for (OptionElement optionElement : optionElementSet) {
            if (optionElement instanceof OptionElementImpl) {
                this.optionElementSet.add((OptionElementImpl)optionElement);
            }
        }
    }
    
}