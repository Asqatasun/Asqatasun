package org.opens.tanaguru.entity.audit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.TestImpl;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.subject.WebResourceImpl;

@Entity
@Table(name = "PROCESS_RESULT", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"Id_Test", "Id_Web_Resource",
        "Id_Audit_Gross_Result"}),
    @UniqueConstraint(columnNames = {"Id_Test", "Id_Web_Resource",
        "Id_Audit_Net_Result"})})
public abstract class ProcessResultImpl implements ProcessResult, Serializable {

    @OneToMany(mappedBy = "parentResult", cascade = CascadeType.ALL)
    protected List<ProcessResultImpl> childResultList = new ArrayList<ProcessResultImpl>();
    @ManyToOne
    @JoinColumn(name = "Id_Audit_Gross_Result")
    protected AuditImpl grossResultAudit;
    @Id
    @GeneratedValue
    @Column(name = "Id_Process_Result")
    protected Long id;
    @ManyToOne
    @JoinColumn(name = "Id_Audit_Net_Result")
    protected AuditImpl netResultAudit;
    @ManyToOne
    @JoinColumn(name = "Id_Process_Result_Parent")
    protected ProcessResultImpl parentResult;
    @OneToMany(mappedBy = "processResult", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected List<ProcessRemarkImpl> remarkList = new Vector<ProcessRemarkImpl>();
    @ManyToOne
    @JoinColumn(name = "Id_Web_Resource", nullable = false)
    protected WebResourceImpl subject;
    @ManyToOne
    @JoinColumn(name = "Id_Test")
    protected TestImpl test;

    public ProcessResultImpl() {
        super();
    }

    public void addAllRemark(List<? extends ProcessRemark> remarkList) {
        if (remarkList == null) {
            return;
        }

        for (ProcessRemark remark : remarkList) {
            addRemark(remark);
        }
    }

    public void addChildResult(ProcessResult childResult) {
        childResult.setParentResult(this);
        this.childResultList.add((ProcessResultImpl) childResult);
    }

    public void addRemark(ProcessRemark remark) {
        remark.setProcessResult(this);
        this.remarkList.add((ProcessRemarkImpl) remark);
    }

    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.DefiniteResultImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.IndefiniteResultImpl.class)})
    public List<ProcessResultImpl> getChildResultList() {
        return childResultList;
    }

    @XmlTransient
    public Audit getGrossResultAudit() {
        return grossResultAudit;
    }

    public Long getId() {
        return id;
    }

    @XmlTransient
    public Audit getNetResultAudit() {
        return netResultAudit;
    }

    @XmlTransient
    public ProcessResult getParentResult() {
        return parentResult;
    }

    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.ProcessRemarkImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.SourceCodeRemarkImpl.class)})
    public List<ProcessRemarkImpl> getRemarkList() {
        return remarkList;
    }

    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.subject.PageImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.subject.SiteImpl.class)})
    public WebResource getSubject() {
        return subject;
    }

    @XmlElementRef(type = org.opens.tanaguru.entity.reference.TestImpl.class)
    public Test getTest() {
        return test;
    }

    public void setChildResultList(
            List<? extends ProcessResult> childResultList) {
        for (ProcessResult processResult : childResultList) {
            processResult.setParentResult(this);
        }
        this.childResultList = (List<ProcessResultImpl>) childResultList;
    }

    public void setGrossResultAudit(Audit grossResultAudit) {
        this.grossResultAudit = (AuditImpl) grossResultAudit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNetResultAudit(Audit netResultAudit) {
        this.netResultAudit = (AuditImpl) netResultAudit;
    }

    public void setParentResult(ProcessResult parentResult) {
        this.parentResult = (ProcessResultImpl) parentResult;
    }

    public void setRemarkList(List<? extends ProcessRemark> remarkList) {
        for (ProcessRemark processRemark : remarkList) {
            processRemark.setProcessResult(this);
        }
        this.remarkList = (List<ProcessRemarkImpl>) remarkList;
    }

    public void setSubject(WebResource subject) {
        this.subject = (WebResourceImpl) subject;
    }

    public void setTest(Test test) {
        this.test = (TestImpl) test;
    }
}
