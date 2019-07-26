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
import org.asqatasun.entity.audit.Content;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "CONTENT")
@org.hibernate.annotations.DynamicInsert(true)
@org.hibernate.annotations.DynamicUpdate(true)
@org.hibernate.annotations.SelectBeforeUpdate(false)
public abstract class ContentImpl implements Content, Serializable {

    private static final long serialVersionUID = -8672816298160346526L;
    @ManyToOne
    @JoinColumn(name = "Id_Audit")
    private AuditImpl audit;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Dt_Loading")
    private Date dateOfLoading;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Content")
    private Long id;
    @Column(name = "Uri", length = 2048, nullable = false)
    private String uri;
    @Column(name = "Http_Status_Code", nullable = false)
    private int httpStatusCode = -1;

    public ContentImpl() {
        super();
    }

    public ContentImpl(String uri) {
        super();
        this.uri = uri;
    }

    public ContentImpl(Date dateOfLoading, String uri) {
        super();
        this.dateOfLoading = dateOfLoading;
        this.uri = uri;
    }

    public ContentImpl(Date dateOfLoading, String uri, int httpStatusCode) {
        super();
        this.dateOfLoading = dateOfLoading;
        this.uri = uri;
        this.httpStatusCode = httpStatusCode;
    }

    @XmlTransient
    @Override
    public Audit getAudit() {
        return audit;
    }

    @Override
    public Date getDateOfLoading() {
        return dateOfLoading;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getURI() {
        return uri;
    }

    @Override
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    @Override
    public void setAudit(Audit audit) {
        this.audit = (AuditImpl) audit;
    }

    @Override
    public void setDateOfLoading(Date dateOfLoading) {
        this.dateOfLoading = dateOfLoading;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setURI(String uri) {
        this.uri = uri;
    }

    @Override
    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

}
