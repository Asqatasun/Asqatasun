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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.collection.PersistentBag;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterImpl;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.TestImpl;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.subject.WebResourceImpl;

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
    protected String comment;
    @OneToMany(mappedBy = "audit")
    protected List<ContentImpl> contentList = new ArrayList<ContentImpl>();
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "Dt_Creation")
    protected Date dateOfCreation;
    @OneToMany(mappedBy = "grossResultAudit")
    protected List<ProcessResultImpl> grossResultList = new ArrayList<ProcessResultImpl>();
    @Id
    @GeneratedValue
    @Column(name = "Id_Audit")
    protected Long id;
    @OneToMany(mappedBy = "netResultAudit")
    protected List<ProcessResultImpl> netResultList = new ArrayList<ProcessResultImpl>();
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    protected AuditStatus status = AuditStatus.INITIALISATION;
    @OneToOne(mappedBy = "audit")
    protected WebResourceImpl subject;
    @ManyToMany
    @JoinTable(name = "AUDIT_TEST", joinColumns =
    @JoinColumn(name = "Id_Audit"), inverseJoinColumns =
    @JoinColumn(name = "Id_Test"))
    protected List<TestImpl> testList = new ArrayList<TestImpl>();
    @ManyToMany
    @JoinTable(name = "AUDIT_PARAMETER", joinColumns =
    @JoinColumn(name = "Id_Audit"), inverseJoinColumns =
    @JoinColumn(name = "Id_Parameter"))
    protected List<ParameterImpl> parameterSet = new ArrayList<ParameterImpl>();
    
    public AuditImpl() {
        super();
    }

    public AuditImpl(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public void addAllContent(List<Content> contentList) {
        for (Content content : contentList) {
            addContent(content);
        }
    }

    @Override
    public void addAllGrossResult(List<ProcessResult> pageResultList) {
        for (ProcessResult pageResult : pageResultList) {
            addGrossResult(pageResult);
        }
    }

    @Override
    public void addAllNetResult(List<ProcessResult> testResultList) {
        for (ProcessResult testResult : testResultList) {
            addNetResult(testResult);
        }
    }

    @Override
    public void addAllTest(List<Test> testList) {
        for (Test test : testList) {
            addTest(test);
        }
    }

    @Override
    public void addContent(Content content) {
        content.setAudit(this);
        this.contentList.add((ContentImpl) content);
    }

    @Override
    public void addGrossResult(ProcessResult pageResult) {
        pageResult.setGrossResultAudit(this);
        grossResultList.add((ProcessResultImpl) pageResult);
    }

    @Override
    public void addNetResult(ProcessResult testResult) {
        testResult.setNetResultAudit(this);
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
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.SSPImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.JavascriptContentImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.StylesheetContentImpl.class)})
    public List<Content> getContentList() {
        return (List<Content>)(ArrayList)contentList;
    }

    @Override
    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.IndefiniteResultImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.DefiniteResultImpl.class)})
    public List<ProcessResult> getGrossResultList() {
        return (List<ProcessResult>)(ArrayList)grossResultList;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tanaguru.entity.audit.DefiniteResultImpl.class)
    public List<ProcessResult> getNetResultList() {
        return (List<ProcessResult>)(ArrayList)netResultList;
    }

    @Override
    public AuditStatus getStatus() {
        return status;
    }

    @Override
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.subject.PageImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.subject.SiteImpl.class)})
    public WebResourceImpl getSubject() {
        return subject;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.TestImpl.class)
    public List<Test> getTestList() {
        if (testList instanceof PersistentBag) {
            return (PersistentBag)testList;
        }
        return (List<Test>)(ArrayList)testList;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void setContentList(List<Content> contentList) {
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
    public void setGrossResultList(List<ProcessResult> pageResultList) {
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
    public void setNetResultList(List<ProcessResult> netResultList) {
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
    public void setTestList(List<Test> testList) {
        for (Test test : testList) {
            this.testList.add((TestImpl)test);
        }
    }

    @Override
    public void setParameterSet(Collection<Parameter> parameterSet){
        for(Parameter param: parameterSet) {
            this.parameterSet.add((ParameterImpl)param);
        }
    }
    
    @Override
    public void addParameter(Parameter parameter){
        this.parameterSet.add((ParameterImpl)parameter);
    }

    @Override
    public Collection<Parameter> getParameterSet() {
        if (parameterSet instanceof PersistentBag) {
            return (PersistentBag)parameterSet;
        }
        return (Collection<Parameter>)(ArrayList)parameterSet;
    }

}