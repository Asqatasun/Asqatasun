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
    @Column(name = "Element_Value", length = 5000, nullable = false)
    protected String value;
    @ManyToOne
    @JoinColumn(name = "PROCESS_REMARK_Id_Process_Remark")
    protected SourceCodeRemarkImpl sourceCodeRemark;

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
        return sourceCodeRemark;
    }

    @Override
    public void setProcessRemark(ProcessRemark processRemark) {
        if (processRemark instanceof SourceCodeRemarkImpl) {
            this.sourceCodeRemark = (SourceCodeRemarkImpl)processRemark;
        }
    }

}
