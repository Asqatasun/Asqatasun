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
package org.asqatasun.entity.audit;

import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.PreProcessResult;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.entity.subject.WebResourceImpl;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "PRE_PROCESS_RESULT", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"Id_Audit", "Id_Web_Resource",
        "Pre_Process_Key"})})
public class PreProcessResultImpl implements PreProcessResult, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Pre_Process_Result")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "Id_Audit", nullable = true)
    private AuditImpl audit;
    @ManyToOne
    @JoinColumn(name = "Id_Web_Resource", nullable = true)
    private WebResourceImpl subject;
    @Column(name = "Pre_Process_Key")
    private String preProcessKey;
    @Column(name = "Pre_Process_Value" , length = 16777215)
    private String preProcessValue;
    
    public PreProcessResultImpl() {
        super();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    @XmlTransient
    public Audit getAudit() {
        return audit;
    }

    @Override
    @XmlElementRefs({
        @XmlElementRef(type = org.asqatasun.entity.subject.PageImpl.class)})
    public WebResource getSubject() {
        return subject;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setAudit(Audit audit) {
        this.audit = (AuditImpl) audit;
    }

    @Override
    public void setSubject(WebResource subject) {
        this.subject = (WebResourceImpl) subject;
    }

    @Override
    public String getValue() {
        return preProcessValue;
    }

    @Override
    public String getKey() {
        return preProcessKey;
    }

    @Override
    public void setValue(String value) {
        this.preProcessValue = value;
    }

    @Override
    public void setKey(String key) {
        this.preProcessKey = key;
    }

}