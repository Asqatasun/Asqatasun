/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.webapp.entity.contract;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.AuditImpl;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "TGSI_ACT")
@XmlRootElement
public class ActImpl implements Act, Serializable {

    private static final long serialVersionUID = -8484972854096344167L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Act")
    private Long id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "Begin_Date")
    private Date beginDate;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "End_Date")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private ActStatus status = ActStatus.INITIALISATION;

    @ManyToOne
    @JoinColumn(name = "CONTRACT_Id_Contract", nullable=false)
    private ContractImpl contract;

    @OneToOne
        @JoinTable(name = "TGSI_ACT_AUDIT", joinColumns =
        @JoinColumn(name = "ACT_Id_Act"), inverseJoinColumns =
        @JoinColumn(name = "AUDIT_Id_Audit"))
    private AuditImpl audit;

    @ManyToOne
    @JoinColumn(name = "SCOPE_Id_Scope", nullable=false)
    private TgsiScopeImpl scope;

    @Column(name = "Client_Ip")
    private String clientIp;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getBeginDate() {
        if (beginDate != null) {
            return new Date(beginDate.getTime());
        } else {
            return null;
        }
    }

    @Override
    public void setBeginDate(Date beginDate) {
        if (beginDate != null) {
            this.beginDate = new Date(beginDate.getTime());
        } else {
            this.beginDate = null;
        }
    }

    @Override
    public Date getEndDate() {
        if (endDate != null) {
            return new Date(endDate.getTime());
        } else {
            return null;
        }
    }

    @Override
    public void setEndDate(Date endDate) {
        if (endDate != null) {
            this.endDate = new Date(endDate.getTime());
        } else {
            this.endDate = null;
        }
    }

    @Override
    public ActStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(ActStatus status) {
        this.status = status;
    }

    @Override
    public Contract getContract() {
        return contract;
    }

    @Override
    public void setContract(Contract contract) {
        this.contract = (ContractImpl)contract;
    }

    @Override
    public Audit getAudit() {
        return audit;
    }

    @Override
    public void setAudit(Audit audit) {
        this.audit = (AuditImpl)audit;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void setScope(Scope scope) {
        this.scope = (TgsiScopeImpl)scope;
    }

    @Override
    public String getClientIp() {
        return this.clientIp;
    }

    @Override
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
   
}