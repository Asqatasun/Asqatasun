/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.entity.audit;

import org.asqatasun.entity.audit.Evidence;
import org.asqatasun.entity.audit.EvidenceElement;
import org.asqatasun.entity.audit.ProcessRemark;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "EVIDENCE_ELEMENT")
@XmlRootElement
public class EvidenceElementImpl implements EvidenceElement,
        Serializable {
    
    private static final long serialVersionUID = 5494394934902604527L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Evidence_Element")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "EVIDENCE_Id_Evidence")
    private EvidenceImpl evidence;
    @Column(name = "Element_Value", nullable = false, length = 16777215)
    private String value;
    @ManyToOne
    @JoinColumn(name = "PROCESS_REMARK_Id_Process_Remark")
    @JsonIgnore
    private ProcessRemarkImpl processRemark;

    public EvidenceElementImpl() {
        super();
    }

    public EvidenceElementImpl(String value) {
        super();
        this.value = value;
    }

    @Override
    public Long getId() {
        return id;
    }

    @XmlTransient
    @Override
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonSubTypes({
        @JsonSubTypes.Type(value=org.asqatasun.entity.audit.EvidenceImpl.class, name="Evidence")})
    public Evidence getEvidence() {
        return (Evidence) evidence;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setEvidence(Evidence Evidence) {
        this.evidence = (EvidenceImpl) Evidence;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @XmlTransient
    @Override
    public ProcessRemark getProcessRemark() {
        return processRemark;
    }

    @Override
    public void setProcessRemark(ProcessRemark processRemark) {
        if (processRemark instanceof ProcessRemarkImpl) {
            this.processRemark = (ProcessRemarkImpl)processRemark;
        }
    }

}
