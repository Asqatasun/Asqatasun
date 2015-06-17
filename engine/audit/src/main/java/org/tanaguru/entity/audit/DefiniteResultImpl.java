/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.tanaguru.entity.audit;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import org.hibernate.envers.Audited;

/**
 *
 * @author jkowalczyk
 */
@Entity
@XmlRootElement
@Audited
public class DefiniteResultImpl extends ProcessResultImpl implements
        DefiniteResult, Serializable {

    private static final long serialVersionUID = -6932621013333884500L;

    @Enumerated(EnumType.STRING)
    @Column(name = "Definite_Value")
    @Audited
    private TestSolution definiteValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "Manual_Definite_Value")
    private TestSolution manualDefiniteValue;

    @Column(name = "Manual_Audit_Comment")
    private String manualAuditComment;

    @Transient
    private List<DefiniteResultImpl> history;

    public List<DefiniteResultImpl> getHistory() {
        return history;
    }

    public void setHistory(List<DefiniteResultImpl> history) {
        this.history = history;
    }

    public DefiniteResultImpl() {
        super();
    }

    @Override
    public TestSolution getDefiniteValue() {
        return definiteValue;
    }

    @Override
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonSubTypes({
        @JsonSubTypes.Type(value=org.tanaguru.entity.audit.TestSolution.class, name="TestSolution")})
    public Object getValue() {
        return getDefiniteValue();
    }

    @Override
    public void setDefiniteValue(TestSolution definiteValue) {
        this.definiteValue = definiteValue;
    }

    @Override
    public void setValue(Object value) {
        setDefiniteValue((TestSolution) value);
    }

    @Override
    public TestSolution getManualDefiniteValue() {
        return manualDefiniteValue;
    }

    @Override
    public void setManualDefiniteValue(TestSolution manualDefiniteValue) {
        this.manualDefiniteValue = manualDefiniteValue;
    }

    @Override
    public String getManualAuditComment() {
        return manualAuditComment;
    }

    @Override
    public void setManualAuditComment(String manualAuditcomment) {
        this.manualAuditComment = manualAuditcomment;
    }

    @Override
    public Object getManualValue() {
        return getManualDefiniteValue();
    }

    @Override
    public void setManualValue(Object manualValue) {
        setManualDefiniteValue((TestSolution) manualValue);
    }
}
