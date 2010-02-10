package org.opens.tanaguru.entity.audit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "PROCESS_REMARK")
@XmlRootElement
public class ProcessRemarkImpl implements ProcessRemark, Serializable {

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

    public ProcessRemarkImpl() {
        super();
    }

    public Long getId() {
        return id;
    }

    public TestSolution getIssue() {
        return issue;
    }

    public String getMessageCode() {
        return messageCode;
    }

    @XmlTransient
    public ProcessResult getProcessResult() {
        return processResult;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIssue(TestSolution issue) {
        this.issue = issue;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public void setProcessResult(ProcessResult processResult) {
        this.processResult = (ProcessResultImpl) processResult;
    }

    public String getSelectedElement() {
        return selectedElement;
    }

    public String getSelectionExpression() {
        return selectionExpression;
    }

    public void selectedElement(String selectedElement) {
        this.selectedElement = selectedElement;
    }

    public void setSelectionExpression(String selectionExpression) {
        this.selectionExpression = selectionExpression;
    }
}
