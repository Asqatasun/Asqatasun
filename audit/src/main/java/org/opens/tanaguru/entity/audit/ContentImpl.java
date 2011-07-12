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
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "CONTENT")
@org.hibernate.annotations.Entity(
		selectBeforeUpdate = false,
		dynamicInsert = true,
		dynamicUpdate = true)
public abstract class ContentImpl implements Content, Serializable {

    private static final long serialVersionUID = -8672816298160346526L;
    @ManyToOne
    @JoinColumn(name = "Id_Audit")
    protected AuditImpl audit;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Dt_Loading")
    protected Date dateOfLoading;
    @Id
    @GeneratedValue
    @Column(name = "Id_Content")
    protected Long id;
    @Column(name = "Uri", length = 2048, nullable = false)
    protected String uri;
    @Column(name = "Http_Status_Code", nullable = false)
    protected int httpStatusCode = -1;

    public ContentImpl() {
        super();
    }

    public ContentImpl(String uri) {
        super();
        this.uri = uri;
    }

    public ContentImpl(Date dateOfLoading, String uri) {
        super();
        this.dateOfLoading = dateOfLoading;
        this.uri = uri;
    }

    public ContentImpl(Date dateOfLoading, String uri, int httpStatusCode) {
        super();
        this.dateOfLoading = dateOfLoading;
        this.uri = uri;
        this.httpStatusCode = httpStatusCode;
    }

    @XmlTransient
    @Override
    public Audit getAudit() {
        return audit;
    }

    @Override
    public Date getDateOfLoading() {
        return dateOfLoading;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getURI() {
        return uri;
    }

    @Override
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    @Override
    public void setAudit(Audit audit) {
        this.audit = (AuditImpl) audit;
    }

    @Override
    public void setDateOfLoading(Date dateOfLoading) {
        this.dateOfLoading = dateOfLoading;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setURI(String uri) {
        this.uri = uri;
    }

    @Override
    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

}
