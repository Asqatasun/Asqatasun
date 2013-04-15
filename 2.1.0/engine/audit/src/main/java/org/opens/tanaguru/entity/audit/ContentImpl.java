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
package org.opens.tanaguru.entity.audit;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "CONTENT")
@org.hibernate.annotations.Entity(
		selectBeforeUpdate = false,
		dynamicInsert = true,
		dynamicUpdate = true)
public abstract class ContentImpl implements Content, Serializable {

    private static final long serialVersionUID = -8672816298160346526L;
    @ManyToOne
    @JoinColumn(name = "Id_Audit")
    private AuditImpl audit;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Dt_Loading")
    private Date dateOfLoading;
    @Id
    @GeneratedValue
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
