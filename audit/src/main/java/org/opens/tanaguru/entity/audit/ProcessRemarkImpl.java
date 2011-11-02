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
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "PROCESS_REMARK")
@XmlRootElement
public class ProcessRemarkImpl implements ProcessRemark, Serializable {

    private static final long serialVersionUID = -8309768143794126739L;
    @Id
    @GeneratedValue
    @Column(name = "Id_Process_Remark")
    protected Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "Issue")
    protected TestSolution issue;
    @Column(name = "Message_Code")
    protected String messageCode;
    @ManyToOne
    @JoinColumn(name = "Id_Process_Result")
    protected ProcessResultImpl processResult;
    @Column(name = "Selected_Element")
    protected String selectedElement;
    @Column(name = "Selection_Expression")
    protected String selectionExpression;
    @OneToMany(mappedBy = "processRemark", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected Set<EvidenceElementImpl> elementList = new LinkedHashSet<EvidenceElementImpl>();

    public ProcessRemarkImpl() {
        super();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public TestSolution getIssue() {
        return issue;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    @XmlTransient
    public ProcessResult getProcessResult() {
        return processResult;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setIssue(TestSolution issue) {
        this.issue = issue;
    }

    @Override
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public void setProcessResult(ProcessResult processResult) {
        this.processResult = (ProcessResultImpl) processResult;
    }

    @Override
    public String getSelectedElement() {
        return selectedElement;
    }

    @Override
    public String getSelectionExpression() {
        return selectionExpression;
    }

    @Override
    public void selectedElement(String selectedElement) {
        this.selectedElement = selectedElement;
    }

    @Override
    public void setSelectionExpression(String selectionExpression) {
        this.selectionExpression = selectionExpression;
    }

    @Override
    public void addElement(EvidenceElement element) {
        element.setProcessRemark(this);
        elementList.add((EvidenceElementImpl) element);
    }

    @Override
    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.opens.tanaguru.entity.audit.EvidenceElementImpl.class)})
    public Collection<EvidenceElementImpl> getElementList() {
        return elementList;
    }

    @Override
    public void setElementList(
            Collection<? extends EvidenceElement> elementList) {
        this.elementList = (Set<EvidenceElementImpl>) elementList;
    }
}
