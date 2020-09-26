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
package org.asqatasun.entity.audit;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Set;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "TAG")
@XmlRootElement
public class TagImpl implements Tag, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Tag")
    private Long id;
    @Column(name = "Value")
    private String value;
    @ManyToMany
    @JoinTable(name = "AUDIT_TAG", joinColumns =
    @JoinColumn(name = "Id_Tag"), inverseJoinColumns =
    @JoinColumn(name = "Id_Audit"))
    private Set<AuditImpl> auditSet;

    public TagImpl() {
        super();
    }

    public TagImpl(String value) {
        this();
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String setValue() {
        return value;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
