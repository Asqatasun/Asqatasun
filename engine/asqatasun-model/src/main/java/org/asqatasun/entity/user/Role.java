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
import javax.persistence.*;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "ROLE")
@Inheritance(strategy=InheritanceType.JOINED)
public class Role implements org.asqatasun.entity.Entity, Serializable {

    private static final long serialVersionUID = -8229758185132700393L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Role")
    private Long id;

    @Column(name = "Role_Name")
    private String roleName;

    @ManyToOne(targetEntity= Role.class)
    @JoinColumn(name = "ROLE_Id_Role")
    private Role parentRole;

    @OneToMany(mappedBy = "parentRole", cascade = CascadeType.ALL)
    private Collection<Role> childRoleSet = new HashSet<Role>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public void addChildRole(Role role) {
        role.setParentRole(this);
        childRoleSet.add((Role)role);
    }
    public void addAllChildRole(Collection<Role> childRoleSet) {
        for (Role role : childRoleSet) {
            role.setParentRole(this);
            if (role instanceof Role) {
                this.childRoleSet.add((Role)role);
            }
        }
    }
    public Collection<Role> getChildRoleSet() {
        return (Collection)childRoleSet;
    }
    public Role getParentRole() {
        return parentRole;
    }
    public void setParentRole(Role parentRole) {
        this.parentRole = (Role)parentRole;
    }

}
