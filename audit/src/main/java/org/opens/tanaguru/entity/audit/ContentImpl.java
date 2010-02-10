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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "CONTENT", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "Uri", "Id_Audit"})})
public abstract class ContentImpl implements Content, Serializable {

    @ManyToOne
    @JoinColumn(name = "Id_Audit", nullable = false)
    protected AuditImpl audit;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Dt_Loading", nullable = false)
    protected Date dateOfLoading;
    @Id
    @GeneratedValue
    @Column(name = "Id_Content")
    protected Long id;
    @Column(name = "Uri", nullable = false)
    protected String uri;

    public ContentImpl() {
        super();
    }

    public ContentImpl(Date dateOfLoading, String uri) {
        super();
        this.dateOfLoading = dateOfLoading;
        this.uri = uri;
    }

    @XmlTransient
    public Audit getAudit() {
        return audit;
    }

    public Date getDateOfLoading() {
        return dateOfLoading;
    }

    public Long getId() {
        return id;
    }

    public String getURI() {
        return uri;
    }

    public void setAudit(Audit audit) {
        this.audit = (AuditImpl) audit;
    }

    public void setDateOfLoading(Date dateOfLoading) {
        this.dateOfLoading = dateOfLoading;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setURI(String uri) {
        this.uri = uri;
    }
}
