package org.opens.tanaguru.entity.subject;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;

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

@Entity
@Table(name = "WEB_RESOURCE")
public abstract class WebResourceImpl implements WebResource, Serializable {

    @OneToOne
    @JoinColumn(name = "Id_Audit", nullable = true)
    protected AuditImpl audit;
    @Id
    @GeneratedValue
    @Column(name = "Id_Web_Resource")
    protected Long id;
    @Column(name = "Label")
    protected String label;
    @ManyToOne
    @JoinColumn(name = "Id_Web_Resource_Parent")
    protected SiteImpl parent;
    @Column(name = "Url", nullable = false)
    protected String url;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    protected List<ProcessResultImpl> processResultList = new ArrayList<ProcessResultImpl>();

    public WebResourceImpl() {
        super();
    }

    public WebResourceImpl(String url) {
        super();
        this.url = url;
    }

    @XmlTransient
    public Audit getAudit() {
        return audit;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    @XmlTransient
    public Site getParent() {
        return parent;
    }

    public String getURL() {
        return url;
    }

    public void setAudit(Audit audit) {
        this.audit = (AuditImpl) audit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setParent(Site parent) {
        this.parent = (SiteImpl) parent;
    }

    public void setURL(String url) {
        this.url = url;
    }

    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.IndefiniteResultImpl.class),
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.DefiniteResultImpl.class)})
    public List<ProcessResultImpl> getProcessResultList() {
        return processResultList;
    }

    public void setProcessResultList(List<? extends ProcessResult> processResultList) {
        this.processResultList = (List<ProcessResultImpl>) processResultList;
    }

    public void addProcessResult(ProcessResult processResult) {
        processResult.setSubject(this);
        this.processResultList.add((ProcessResultImpl) processResult);
    }

    public void addAllProcessResult(List<? extends ProcessResult> processResultList) {
        for (ProcessResult processResult : processResultList) {
            addProcessResult(processResult);
        }
    }
}
