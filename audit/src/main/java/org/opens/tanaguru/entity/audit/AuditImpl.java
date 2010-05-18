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
    @Column(name = "Mark")
    protected float mark;
    @OneToMany(mappedBy = "netResultAudit", cascade = CascadeType.ALL)
    protected List<ProcessResultImpl> netResultList = new ArrayList<ProcessResultImpl>();
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    protected AuditStatus status = AuditStatus.INITIALISATION;
    @OneToOne(mappedBy = "audit", cascade = CascadeType.ALL)
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

    public void addAllContent(List<? extends Content> contentList) {
        for (Content content : contentList) {
            addContent(content);
        }
    }

    public void addAllGrossResult(List<? extends ProcessResult> pageResultList) {
        for (ProcessResult pageResult : pageResultList) {
            addGrossResult(pageResult);
        }
    }

    public void addAllNetResult(List<? extends ProcessResult> testResultList) {
        for (ProcessResult testResult : testResultList) {
            addNetResult(testResult);
        }
    }

    public void addAllTest(List<? extends Test> testList) {
        for (Test test : testList) {
            addTest(test);
        }
    }

    public void addContent(Content content) {
        content.setAudit(this);
        this.contentList.add((ContentImpl) content);
    }

    public void addGrossResult(ProcessResult pageResult) {
        pageResult.setGrossResultAudit(this);
        grossResultList.add((ProcessResultImpl) pageResult);
    }

    public void addNetResult(ProcessResult testResult) {
        testResult.setNetResultAudit(this);
        netResultList.add((ProcessResultImpl) testResult);
    }

    public void setSubject(WebResource subject) {
        subject.setAudit(this);
        this.subject = (WebResourceImpl) subject;
    }

    public void addTest(Test test) {
        this.testList.add((TestImpl) test);
    }

    public String getComment() {
        return comment;
    }

    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.SSPImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.JavascriptContentImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.StylesheetContentImpl.class)})
    public List<ContentImpl> getContentList() {
        return contentList;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.IndefiniteResultImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.DefiniteResultImpl.class)})
    public List<ProcessResultImpl> getGrossResultList() {
        return grossResultList;
    }

    public Long getId() {
        return id;
    }

    public float getMark() {
        return mark;
    }

    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tanaguru.entity.audit.DefiniteResultImpl.class)
    public List<ProcessResultImpl> getNetResultList() {
        return netResultList;
    }

    public AuditStatus getStatus() {
        return status;
    }

    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.subject.PageImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.subject.SiteImpl.class)})
    public WebResourceImpl getSubject() {
        return subject;
    }

    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.TestImpl.class)
    public List<TestImpl> getTestList() {
        return testList;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setContentList(List<? extends Content> contentList) {
        for (Content content : contentList) {
            content.setAudit(this);
        }
        this.contentList = (List<ContentImpl>) contentList;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public void setGrossResultList(List<? extends ProcessResult> pageResultList) {
        for (ProcessResult grossResult : pageResultList) {
            grossResult.setGrossResultAudit(this);
        }
        this.grossResultList = (List<ProcessResultImpl>) pageResultList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public void setNetResultList(List<? extends ProcessResult> netResultList) {
        for (ProcessResult netResult : netResultList) {
            netResult.setNetResultAudit(this);
        }
        this.netResultList = (List<ProcessResultImpl>) netResultList;
    }

    public void setStatus(AuditStatus status) {
        this.status = status;
    }

    public void setTestList(List<? extends Test> testList) {
        this.testList = (List<TestImpl>) testList;
    }
}
