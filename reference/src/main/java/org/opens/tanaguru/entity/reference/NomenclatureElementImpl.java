package org.opens.tanaguru.entity.reference;

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
@Table(name = "NOMENCLATURE_ELEMENT")
@XmlRootElement
public class NomenclatureElementImpl implements NomenclatureElement,
        Serializable {

    @Id
    @GeneratedValue
    @Column(name = "Id_Nomenclature_Element")
    protected Long id;
    @ManyToOne
    @JoinColumn(name = "Id_Nomenclature")
    protected NomenclatureImpl nomenclature;
    @Column(name = "Label", nullable = false)
    protected String label;

    public NomenclatureElementImpl() {
        super();
    }

    public NomenclatureElementImpl(String value) {
        super();
        this.label = value;
    }

    public Long getId() {
        return id;
    }

    @XmlTransient
    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public String getLabel() {
        return label;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = (NomenclatureImpl) nomenclature;
    }

    public void setLabel(String value) {
        this.label = value;
    }
}
