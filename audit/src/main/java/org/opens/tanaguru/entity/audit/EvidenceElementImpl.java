package org.opens.tanaguru.entity.audit;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "EVIDENCE_ELEMENT")
@XmlRootElement
public class EvidenceElementImpl implements EvidenceElement,
        Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id_Evidence_Element")
    protected Long id;
    @ManyToOne
    @JoinColumn(name = "EVIDENCE_Id_Evidence")
    protected EvidenceImpl evidence;
    @Column(name = "Label", nullable = false)
    protected String label;
    @ManyToOne
    @JoinColumn(name = "PROCESS_REMARK_Id_Process_Remark")
    protected SourceCodeRemarkImpl sourceCodeRemark;

    public EvidenceElementImpl() {
        super();
    }

    public EvidenceElementImpl(String value) {
        super();
        this.label = value;
    }

    @Override
    public Long getId() {
        return id;
    }

    @XmlTransient
    @Override
    public Evidence getEvidence() {
        return (Evidence) evidence;
    }

    @Override
    public String getLabel() {
        return label;
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
    public void setLabel(String value) {
        this.label = value;
    }

    @XmlTransient
    @Override
    public ProcessRemark getProcessRemark() {
        return sourceCodeRemark;
    }

    @Override
    public void setProcessRemark(ProcessRemark processRemark) {
        this.sourceCodeRemark = (SourceCodeRemarkImpl)processRemark;
    }

}
