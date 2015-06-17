/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.entity.membership;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "ROLE")
@XmlRootElement
public class RoleImpl implements Role, Serializable {

    private static final long serialVersionUID = -6684798009105901720L;

    @Id
    @GeneratedValue
    @Column(name = "Id_Role")
    protected Long id;
    @Column(name = "Label", nullable = false)
    protected String label;
    @ManyToMany
    @JoinTable(name = "ROLE_GROUP", joinColumns =
    @JoinColumn(name = "Id_Role"), inverseJoinColumns =
    @JoinColumn(name = "Id_Office_Group"))
    protected Collection<OfficeGroupImpl> officeGroupList;
    @ManyToMany(mappedBy = "roleList")
    protected Collection<UserImpl> userList;

    public RoleImpl() {
        super();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @XmlElementWrapper
    @XmlAnyElement
    @Override
    public Collection<OfficeGroupImpl> getOfficeGroupList() {
        return officeGroupList;
    }

    @XmlElementWrapper
    @XmlAnyElement
    @Override
    public Collection<UserImpl> getUserList() {
        return userList;
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
    public void setOfficeGroupList(
            Collection<? extends OfficeGroup> officeGroupList) {
        this.officeGroupList = (Collection<OfficeGroupImpl>) officeGroupList;
    }

    @Override
    public void setUserList(Collection<? extends User> userList) {
        this.userList = (Collection<UserImpl>) userList;
    }
}
