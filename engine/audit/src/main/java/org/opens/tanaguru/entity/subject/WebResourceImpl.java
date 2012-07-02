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
package org.opens.tanaguru.entity.subject;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditImpl;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.ProcessResultImpl;

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
    private AuditImpl audit;
    @Id
    @GeneratedValue
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
    private Set<ProcessResultImpl> processResultList = new LinkedHashSet<ProcessResultImpl>();
    @Column(name = "Mark")
    private float mark;
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
    public float getMark() {
        return mark;
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
    public void setMark(float mark) {
        this.mark = mark;
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
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.IndefiniteResultImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.DefiniteResultImpl.class)})
    public Collection<ProcessResultImpl> getProcessResultList() {
        return processResultList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setProcessResultList(Collection<? extends ProcessResult> processResultList) {
        this.processResultList = (HashSet<ProcessResultImpl>) processResultList;
    }

    @Override
    public void addProcessResult(ProcessResult processResult) {
        processResult.setSubject(this);
        this.processResultList.add((ProcessResultImpl) processResult);
    }

    @Override
    public void addAllProcessResult(Collection<? extends ProcessResult> processResultList) {
        for (ProcessResult processResult : processResultList) {
            addProcessResult(processResult);
        }
    }

}