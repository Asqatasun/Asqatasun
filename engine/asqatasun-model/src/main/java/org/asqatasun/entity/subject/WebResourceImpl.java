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

package org.asqatasun.entity.subject;

import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.AuditImpl;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.ProcessResultImpl;
import org.asqatasun.entity.subject.Site;
import org.asqatasun.entity.subject.WebResource;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "WEB_RESOURCE")
public abstract class WebResourceImpl implements WebResource, Serializable {

    private static final long serialVersionUID = 5534153976635867531L;
    @OneToOne
    @JoinColumn(name = "Id_Audit", nullable = true)
    @JsonIgnore
    private AuditImpl audit;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Web_Resource")
    private Long id;
    @Column(name = "Label")
    private String label;
    @ManyToOne
    @JoinColumn(name = "Id_Web_Resource_Parent")
    private SiteImpl parent;
    @Column(name = "Url", length=2048, nullable = false)
    private String url;
    @OneToMany(mappedBy = "subject")
    private Set<ProcessResultImpl> processResultSet;
    @Column(name = "Rank", nullable = false)
    private int rank = 0;

    public WebResourceImpl() {
        super();
    }

    public WebResourceImpl(String url) {
        super();
        this.url = url;
    }

    @Override
    @XmlTransient
    public Audit getAudit() {
        return audit;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Site getParent() {
        return parent;
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public void setAudit(Audit audit) {
        this.audit = (AuditImpl) audit;
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
    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public void setParent(Site parent) {
        this.parent = (SiteImpl) parent;
    }

    @Override
    public void setURL(String url) {
        this.url = url;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.asqatasun.entity.audit.IndefiniteResultImpl.class),
        @XmlElementRef(type = org.asqatasun.entity.audit.DefiniteResultImpl.class)})
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonSubTypes({
        @JsonSubTypes.Type(value=org.asqatasun.entity.audit.IndefiniteResultImpl.class, name="Indefinite"),
        @JsonSubTypes.Type(value=org.asqatasun.entity.audit.DefiniteResultImpl.class, name="Definite")})
    public Collection<ProcessResult> getProcessResultList() {
        return (Collection)processResultSet;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setProcessResultList(Collection<ProcessResult> processResultList) {
        addAllProcessResult(processResultList);
    }

    @Override
    public void addProcessResult(ProcessResult processResult) {
        if (this.processResultSet == null) {
            this.processResultSet = new LinkedHashSet<>();
        }
        processResult.setSubject(this);
        this.processResultSet.add((ProcessResultImpl) processResult);
    }

    @Override
    public void addAllProcessResult(Collection<ProcessResult> processResultList) {
        if (this.processResultSet == null) {
            this.processResultSet = new LinkedHashSet<>();
        }
        for (ProcessResult processResult : processResultList) {
            addProcessResult(processResult);
        }
    }

}