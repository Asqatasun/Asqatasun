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

import org.asqatasun.entity.audit.EvidenceElement;
import org.asqatasun.entity.audit.ProcessRemark;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Process_Remark")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "Issue")
    private TestSolution issue;
    @Column(name = "Message_Code")
    private String messageCode;
    @ManyToOne
    @JoinColumn(name = "Id_Process_Result")
    @JsonIgnore
    private ProcessResultImpl processResult;
    @Column(name = "Selected_Element")
    @JsonIgnore
    private String selectedElement;
    @Column(name = "Selection_Expression")
    @JsonIgnore
    private String selectionExpression;
    @OneToMany(mappedBy = "processRemark", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<EvidenceElementImpl> elementSet;

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
        if (elementSet == null) {
            elementSet = new LinkedHashSet<>();
        }
        element.setProcessRemark(this);
        elementSet.add((EvidenceElementImpl) element);
    }

    @Override
    @XmlElementWrapper
    @XmlElementRefs({
        @XmlElementRef(type = org.asqatasun.entity.audit.EvidenceElementImpl.class)})
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonSubTypes({
        @JsonSubTypes.Type(value=org.asqatasun.entity.audit.EvidenceElementImpl.class, name="EvidenceElement")})
    public Collection<EvidenceElement> getElementList() {
        return (Collection)elementSet;
    }

    @Override
    public void setElementList(Collection<EvidenceElement> elementList) {
        if (this.elementSet == null) {
            this.elementSet = new LinkedHashSet<>();
        }
        for (EvidenceElement evEl : elementList) {
            this.elementSet.add((EvidenceElementImpl) evEl);
        }
    }
    
}