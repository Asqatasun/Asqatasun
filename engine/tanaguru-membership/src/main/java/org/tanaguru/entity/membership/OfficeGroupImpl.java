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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "OFFICE_GROUP")
@XmlRootElement
public class OfficeGroupImpl implements OfficeGroup, Serializable {

    private static final long serialVersionUID = -4925048176836988336L;

    @Id
    @GeneratedValue
    @Column(name = "Id_Office_Group")
    protected Long id;
    @Column(name = "Label")
    protected String label;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    protected Collection<OfficeImpl> officeList;
    @ManyToMany(mappedBy = "officeGroupList")
    protected Collection<RoleImpl> roleList;

    public OfficeGroupImpl() {
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
    public Collection<OfficeImpl> getOfficeList() {
        return officeList;
    }

    @XmlElementWrapper
    @XmlAnyElement
    @Override
    public Collection<RoleImpl> getRoleList() {
        return roleList;
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
    public void setOfficeList(Collection<? extends Office> offices) {
        this.officeList = (Collection<OfficeImpl>) offices;
    }

    @Override
    public void setRoleList(
            Collection<? extends org.tanaguru.entity.membership.Role> roleList) {
        this.roleList = (Collection<RoleImpl>) roleList;
    }

}