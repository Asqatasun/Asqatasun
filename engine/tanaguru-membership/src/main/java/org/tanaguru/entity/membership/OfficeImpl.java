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
package org.tanaguru.entity.membership;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "OFFICE")
@XmlRootElement
public class OfficeImpl implements Office, Serializable {

    private static final long serialVersionUID = -8436079517430211391L;

    @ManyToOne
    @JoinColumn(name = "Id_Office_Group")
    protected OfficeGroupImpl group;
    @Id
    @GeneratedValue
    @Column(name = "Id_Office")
    protected Long id;
    @Column(name = "Label", nullable = false)
    protected String label;

    public OfficeImpl() {
        super();
    }

    @XmlElementRef(type = org.tanaguru.entity.membership.OfficeGroupImpl.class)
    @Override
    public OfficeGroup getGroup() {
        return group;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setGroup(OfficeGroup officeGroup) {
        this.group = (OfficeGroupImpl) officeGroup;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

}