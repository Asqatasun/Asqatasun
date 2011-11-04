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

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "TGSI_ROLE")
@Inheritance(strategy=InheritanceType.JOINED)
public class RoleImpl implements Role, Serializable {

    private static final long serialVersionUID = -8229758185132700393L;
    
    @Id
    @GeneratedValue
    @Column(name = "Id_Role")
    private Long id;

    @Column(name = "Role_Name")
    private String roleName;

    @ManyToOne(targetEntity=RoleImpl.class)
    @JoinColumn(name = "ROLE_Id_Role")
    private RoleImpl parentRole;

    @OneToMany(mappedBy = "parentRole", cascade = CascadeType.ALL)
    private Collection<RoleImpl> childRoleSet = new HashSet<RoleImpl>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getRoleName() {
        return roleName;
    }

    @Override
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public void addChildRole(Role role) {
        role.setParentRole(this);
        childRoleSet.add((RoleImpl)role);
    }

    @Override
    public void addAllChildRole(Collection<? extends Role> childRoleSet) {
        for (Role role : childRoleSet) {
            role.setParentRole(this);
        }
        this.childRoleSet.addAll((Set<RoleImpl>)childRoleSet);
    }

    @Override
    public Collection<? extends Role> getChildRoleSet() {
        return childRoleSet;
    }

    @Override
    public Role getParentRole() {
        return parentRole;
    }

    @Override
    public void setParentRole(Role parentRole) {
        this.parentRole = (RoleImpl)parentRole;
    }

}