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

@Entity
@Table(name = "WEB_RESOURCE")
public abstract class WebResourceImpl implements WebResource, Serializable {

    private static final long serialVersionUID = 5534153976635867531L;
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
    @Column(name = "Url", length=2048, nullable = false)
    protected String url;
    @OneToMany(mappedBy = "subject")
    protected Set<ProcessResultImpl> processResultList = new LinkedHashSet<ProcessResultImpl>();
    @Column(name = "Mark")
    protected float mark;

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