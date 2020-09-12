/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.entity.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.asqatasun.entity.contract.Contract;
import org.asqatasun.entity.option.OptionElementImpl;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "TGSI_USER")
@XmlRootElement
public class User implements org.asqatasun.entity.Entity, Serializable {

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
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Contract> contractSet = new LinkedHashSet<>();

    @ManyToMany
        @JoinTable(name = "TGSI_USER_OPTION_ELEMENT", joinColumns =
        @JoinColumn(name = "USER_Id_User"), inverseJoinColumns =
        @JoinColumn(name = "OPTION_ELEMENT_Id_Option_Element"))
    Set<OptionElementImpl> optionElementSet = new HashSet<>();
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail1() {
        return email;
    }
    public void setEmail1(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getWebUrl1() {
        return this.webUrl1;
    }
    public void setWebUrl1(String webUrl1) {
        this.webUrl1 = webUrl1;
    }
    public String getWebUrl2() {
        return this.webUrl2;
    }
    public void setWebUrl2(String webUrl2) {
        this.webUrl2 = webUrl2;
    }
    public String getIdenticaId() {
        return this.identicaId;
    }
    public void setIdenticaId(String identicaId) {
        this.identicaId = identicaId;
    }
    public String getTwitterId() {
        return this.twitterId;
    }
    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }
    public Role getRole() {
        return this.role;
    }
    public void setRole(Role role) {
        this.role = (Role)role;
    }
    public void addContract(Contract contract) {
        contract.setUser(this);
        this.contractSet.add((Contract)contract);
    }
    public void addAllContracts(Collection<Contract> contractSet) {
        for (Contract contract : contractSet) {
            contract.setUser(this);
            if (contract instanceof Contract) {
                this.contractSet.add((Contract)contract);
            }
        }
    }

    @XmlElementWrapper
    @XmlElementRef(type = Contract.class)
    public Collection<Contract> getContractSet() {
        return (Collection)contractSet;
    }
    public boolean isAccountActivated() {
        return isAccountActivated;
    }
    public void setAccountActivation(boolean value) {
        this.isAccountActivated = value;
    }
    public Collection<OptionElementImpl> getOptionElementSet() {
        return (Collection)this.optionElementSet;
    }
    public void addOptionElement(OptionElementImpl option) {
        optionElementSet.add((OptionElementImpl)option);
    }
    public void addAllOptionElement(Collection<OptionElementImpl> optionElementSet) {
        this.optionElementSet = new HashSet<>();
        for (OptionElementImpl optionElement : optionElementSet) {
            if (optionElement instanceof OptionElementImpl) {
                this.optionElementSet.add((OptionElementImpl)optionElement);
            }
        }
    }
    public boolean isRoleAdmin() {
        if (this.role.getId() != null) {
            if (this.role.getId() == 3) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }  
}
