/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.entity.audit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@XmlRootElement
public class DefiniteResultImpl extends ProcessResultImpl implements
        DefiniteResult, Serializable {

    private static final long serialVersionUID = -6932621013333884500L;

    @Enumerated(EnumType.STRING)
    @Column(name = "Definite_Value")
    private TestSolution definiteValue;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Manual_Definite_Value")
    private TestSolution manualDefiniteValue;
    
    @Column(name = "Manual_Audit_Comment")
    private String manualAuditComment;

    public DefiniteResultImpl() {
        super();
    }

    @Override
    public TestSolution getDefiniteValue() {
        return definiteValue;
    }

    @Override
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
	public String getManualAuditcomment() {
		return manualAuditComment;
	}

	@Override
	public void setManualAuditComment(String manualAuditcomment) {
		this.manualAuditComment=manualAuditcomment;
	}
   
}
