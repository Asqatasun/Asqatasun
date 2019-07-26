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
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.audit.Content;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.parameterization.ParameterImpl;
import org.asqatasun.entity.reference.Test;
import org.asqatasun.entity.reference.TestImpl;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.entity.subject.WebResourceImpl;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "AUDIT")
@XmlRootElement
public class AuditImpl implements Audit, Serializable {

    private static final long serialVersionUID = -9109080857144047795L;
    @Column(name = "Comment")
    private String comment;
    @OneToMany(mappedBy = "audit")
    @JsonIgnore
    private Set<ContentImpl> contentList;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Dt_Creation")
    private Date dateOfCreation;
    @Column(name = "Manual_Audit_Dt_Creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date manualAuditDateOfCreation;
    @OneToMany(mappedBy = "grossResultAudit")
    @JsonIgnore
    private Set<ProcessResultImpl> grossResultList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Audit")
    private Long id;
    @OneToMany(mappedBy = "netResultAudit")
    @JsonIgnore
    private Set<ProcessResultImpl> netResultList;
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private AuditStatus status = AuditStatus.INITIALISATION;
    @OneToOne(mappedBy = "audit")
    private WebResourceImpl subject;
    @ManyToMany
    @JoinTable(name = "AUDIT_TEST", joinColumns =
    @JoinColumn(name = "Id_Audit"), inverseJoinColumns =
    @JoinColumn(name = "Id_Test"))
    @JsonIgnore
    private Set<TestImpl> testList;
    @ManyToMany
    @JoinTable(name = "AUDIT_PARAMETER", joinColumns =
    @JoinColumn(name = "Id_Audit"), inverseJoinColumns =
    @JoinColumn(name = "Id_Parameter"))
    private Set<ParameterImpl> parameterSet;
    
    public AuditImpl() {
        super();
    }

    public AuditImpl(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public void addAllContent(Collection<Content> contentList) {
        for (Content content : contentList) {
            addContent(content);
        }
    }

    @Override
    public void addAllGrossResult(Collection<ProcessResult> pageResultList) {
        for (ProcessResult pageResult : pageResultList) {
            addGrossResult(pageResult);
        }
    }

    @Override
    public void addAllNetResult(Collection<ProcessResult> testResultList) {
        for (ProcessResult testResult : testResultList) {
            addNetResult(testResult);
        }
    }

    @Override
    public void addAllTest(Collection<Test> testList) {
        for (Test test : testList) {
            addTest(test);
        }
    }

    @Override
    public void addContent(Content content) {
        content.setAudit(this);
        if (contentList == null) {
            contentList = new HashSet<>();
        }
        this.contentList.add((ContentImpl) content);
    }

    @Override
    public void addGrossResult(ProcessResult pageResult) {
        pageResult.setGrossResultAudit(this);
        if (grossResultList == null) {
            grossResultList = new HashSet<>();
        }
        grossResultList.add((ProcessResultImpl) pageResult);
    }

    @Override
    public void addNetResult(ProcessResult testResult) {
        testResult.setNetResultAudit(this);
        if (netResultList == null) {
            netResultList = new HashSet<>();
        }
        netResultList.add((ProcessResultImpl) testResult);
    }

    @Override
    public void setSubject(WebResource subject) {
        subject.setAudit(this);
        this.subject = (WebResourceImpl) subject;
    }

    @Override
    public void addTest(Test test) {
        this.testList.add((TestImpl) test);
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.asqatasun.entity.audit.SSPImpl.class),
        @XmlElementRef(type = org.asqatasun.entity.audit.JavascriptContentImpl.class),
        @XmlElementRef(type = org.asqatasun.entity.audit.StylesheetContentImpl.class)})
    public Collection<Content> getContentList() {
        return (Collection)contentList;
    }

    @Override
    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    @Override
    public Date getManualAuditDateOfCreation() {
        return manualAuditDateOfCreation;
    }
    
    @Override
    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.asqatasun.entity.audit.IndefiniteResultImpl.class),
        @XmlElementRef(type = org.asqatasun.entity.audit.DefiniteResultImpl.class)})
    public Collection<ProcessResult> getGrossResultList() {
        return (Collection)grossResultList;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.asqatasun.entity.audit.DefiniteResultImpl.class)
    public Collection<ProcessResult> getNetResultList() {
        return (Collection)netResultList;
    }

    @Override
    public AuditStatus getStatus() {
        return status;
    }

    @Override
    @XmlElementRefs({
        @XmlElementRef(type = org.asqatasun.entity.subject.PageImpl.class),
        @XmlElementRef(type = org.asqatasun.entity.subject.SiteImpl.class)})
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=As.WRAPPER_OBJECT)
    @JsonSubTypes({
        @JsonSubTypes.Type(value=org.asqatasun.entity.subject.PageImpl.class, name="Page"),
        @JsonSubTypes.Type(value=org.asqatasun.entity.subject.SiteImpl.class, name="Site")})
    public WebResourceImpl getSubject() {
        return subject;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.asqatasun.entity.reference.TestImpl.class)
    public Collection<Test> getTestList() {
        return (Collection)testList;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void setContentList(Collection<Content> contentList) {
        if (this.contentList == null) {
            this.contentList = new HashSet<>();
        }
        for (Content content : contentList) {
            content.setAudit(this);
            this.contentList.add((ContentImpl)content);
        }
    }

    @Override
    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
    
    @Override
    public void setManualAuditDateOfCreation(Date manualAuditDateOfCreation) {
        this.manualAuditDateOfCreation = manualAuditDateOfCreation;
    }

    @Override
    public void setGrossResultList(Collection<ProcessResult> pageResultList) {
        if (this.grossResultList == null) {
            this.grossResultList = new HashSet<>();
        }
        for (ProcessResult grossResult : pageResultList) {
            grossResult.setGrossResultAudit(this);
            this.grossResultList.add((ProcessResultImpl)grossResult);
        }
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setNetResultList(Collection<ProcessResult> netResultList) {
        if (this.netResultList == null) {
            this.netResultList = new HashSet<>();
        }
        for (ProcessResult netResult : netResultList) {
            netResult.setNetResultAudit(this);
            this.netResultList.add((ProcessResultImpl)netResult);
        }
    }

    @Override
    public void setStatus(AuditStatus status) {
        this.status = status;
    }

    @Override
    public void setTestList(Collection<Test> testList) {
        if (this.testList == null) {
            this.testList = new HashSet<>();
        }
        for (Test test : testList) {
            this.testList.add((TestImpl)test);
        }
    }

    @Override
    public void setParameterSet(Collection<Parameter> parameterSet){
        if (this.parameterSet == null) {
            this.parameterSet = new HashSet<>();
        }
        for(Parameter param: parameterSet) {
            this.parameterSet.add((ParameterImpl)param);
        }
    }
    
    @Override
    public void addParameter(Parameter parameter){
        if (this.parameterSet == null) {
            this.parameterSet = new HashSet<>();
        }
        this.parameterSet.add((ParameterImpl)parameter);
    }

    @Override
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=As.WRAPPER_OBJECT)
    @JsonSubTypes({
        @JsonSubTypes.Type(value=org.asqatasun.entity.parameterization.ParameterImpl.class, name="Parameter")})
    public Collection<Parameter> getParameterSet() {
        if (this.parameterSet == null) {
            this.parameterSet = new HashSet<>();
        }
        return (Collection)parameterSet;
    }

}