package org.opens.tanaguru.entity.audit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.TestImpl;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.subject.WebResourceImpl;
import java.util.List;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "AUDIT")
@XmlRootElement
public class AuditImpl implements Audit, Serializable {

    @Column(name = "Comment")
    protected String comment;
    @OneToMany(mappedBy = "audit", cascade = CascadeType.ALL)
    protected List<ContentImpl> contentList = new ArrayList<ContentImpl>();
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "Dt_Creation")
    protected Date dateOfCreation;
    @OneToMany(mappedBy = "grossResultAudit", cascade = CascadeType.ALL)
    protected List<ProcessResultImpl> grossResultList = new ArrayList<ProcessResultImpl>();
    @Id
    @GeneratedValue
    @Column(name = "Id_Audit")
    protected Long id;
    @OneToMany(mappedBy = "netResultAudit", cascade = CascadeType.ALL)
    protected List<ProcessResultImpl> netResultList = new ArrayList<ProcessResultImpl>();
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    protected AuditStatus status = AuditStatus.INITIALISATION;
    @OneToOne(mappedBy = "audit", cascade = CascadeType.REFRESH)
    protected WebResourceImpl subject;
    @ManyToMany
    @JoinTable(name = "AUDIT_TEST", joinColumns =
    @JoinColumn(name = "Id_Audit"), inverseJoinColumns =
    @JoinColumn(name = "Id_Test"))
    protected List<TestImpl> testList = new ArrayList<TestImpl>();

    public AuditImpl() {
        super();
    }

    public AuditImpl(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public void addAllContent(List<? extends Content> contentList) {
        for (Content content : contentList) {
            addContent(content);
        }
    }

    @Override
    public void addAllGrossResult(List<? extends ProcessResult> pageResultList) {
        for (ProcessResult pageResult : pageResultList) {
            addGrossResult(pageResult);
        }
    }

    @Override
    public void addAllNetResult(List<? extends ProcessResult> testResultList) {
        for (ProcessResult testResult : testResultList) {
            addNetResult(testResult);
        }
    }

    @Override
    public void addAllTest(List<? extends Test> testList) {
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
    public List<ContentImpl> getContentList() {
        return contentList;
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
    public List<ProcessResultImpl> getGrossResultList() {
        return grossResultList;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tanaguru.entity.audit.DefiniteResultImpl.class)
    public List<ProcessResultImpl> getNetResultList() {
        return netResultList;
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
    public List<TestImpl> getTestList() {
        return testList;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void setContentList(List<? extends Content> contentList) {
        for (Content content : contentList) {
            content.setAudit(this);
        }
        this.contentList = (List<ContentImpl>) contentList;
    }

    @Override
    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public void setGrossResultList(List<? extends ProcessResult> pageResultList) {
        for (ProcessResult grossResult : pageResultList) {
            grossResult.setGrossResultAudit(this);
        }
        this.grossResultList = (List<ProcessResultImpl>) pageResultList;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setNetResultList(List<? extends ProcessResult> netResultList) {
        for (ProcessResult netResult : netResultList) {
            netResult.setNetResultAudit(this);
        }
        this.netResultList = (List<ProcessResultImpl>) netResultList;
    }

    @Override
    public void setStatus(AuditStatus status) {
        this.status = status;
    }

    @Override
    public void setTestList(List<? extends Test> testList) {
        this.testList = (List<TestImpl>) testList;
    }
}
