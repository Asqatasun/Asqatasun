package com.oceaneconsulting.tanaguru.entity.impl;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import com.oceaneconsulting.tanaguru.entity.WsInvocation;
import java.util.Date;

/**
 * Classe des invocations
 * @author msobahi
 *
 */
@Entity
@Table(name = "WS_INVOCATION")
@XmlRootElement
public class WsInvocationImpl implements WsInvocation, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7482159329304767809L;

    /**
     * Identifiant
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INVOCATION")
    private Long id;
    /**
     * Nom du host
     */
    @Column(name = "HOST_NAME")
    private String hostName;

    /**
     * L'adresse IP du host
     */
    @Column(name = "HOST_IP")
    private String hostIp;

    /**
     * Date d'invocation
     */
    @Column(name = "DT_INVOCATION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateInvocation;

    /**
     * Le type d'audit
     */
    @Column(name = "AUDIT_TYPE")
    private Integer auditType;

    /**
     * L'utilsateur qui a lanc l'audit
     */
    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private WsUserImpl user;

    /**
     * Audit identifier
     */
    @Column(name = "ID_AUDIT")
    private Long auditId;

    /**
     * Audit category
     */
    @Column(name = "CATEGORY")
    private String category;

    /**
     * Audit country
     */
    @Column(name = "COUNTRY")
    private String country;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getHostName() {
        return this.hostName;
    }

    @Override
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
    public String getHostIp() {
        return this.hostIp;
    }

    @Override
    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    @Override
    public Date getDateInvocation() {
        return dateInvocation;
    }

    @Override
    public void setDateInvocation(Date dateInvocation) {
        this.dateInvocation = dateInvocation;
    }

    @Override
    public Integer getAuditType() {
        return this.auditType;
    }

    @Override
    public void setAuditType(Integer auditType) {
        this.auditType = auditType;
    }

    @Override
    public WsUserImpl getUser() {
        return user;
    }

    @Override
    public void setUser(WsUserImpl user) {
        this.user = user;
    }

    @Override
    public Long getAuditId() {
        return auditId;
    }

    @Override
    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }
}
