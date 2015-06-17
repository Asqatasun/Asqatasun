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
@Table(name = "USER")
@XmlRootElement
public class UserImpl implements User, Serializable {

    private static final long serialVersionUID = -8590295139213407939L;

    @Id
    @GeneratedValue
    @Column(name = "Id_User")
    protected Long id;
    @Column(name = "Password", nullable = false)
    protected String password;
    @ManyToMany
    @JoinTable(name = "USER_ROLE", joinColumns =
    @JoinColumn(name = "Id_User"), inverseJoinColumns =
    @JoinColumn(name = "Id_Role"))
    protected Collection<RoleImpl> roleList;
    @Column(name = "Username", nullable = false)
    protected String username;

    public UserImpl() {
        super();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @XmlElementWrapper
    @XmlAnyElement
    @Override
    public Collection<RoleImpl> getRoleList() {
        return roleList;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setRoleList(Collection<? extends Role> roleList) {
        this.roleList = (Collection<RoleImpl>) roleList;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

}