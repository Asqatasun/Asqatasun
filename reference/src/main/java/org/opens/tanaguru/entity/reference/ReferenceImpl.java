package org.opens.tanaguru.entity.reference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "REFERENCE")
@XmlRootElement
public class ReferenceImpl implements Reference, Serializable {

    @Column(name = "Cd_Reference")
    protected String code;
    @OneToMany(mappedBy = "reference", cascade = CascadeType.ALL)
    protected List<CriterionImpl> criterionList = new ArrayList<CriterionImpl>();
    @Column(name = "Description")
    protected String description;
    @Id
    @GeneratedValue
    @Column(name = "Id_Reference")
    protected Long id;
    @Column(name = "Label", nullable = false)
    protected String label;
    @Column(name = "Rank")
    protected int rank;

    public ReferenceImpl() {
        super();
    }

    public ReferenceImpl(String code, String label, String description) {
        this();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    public void addCriterion(Criterion criterion) {
        criterion.setReference(this);
        this.criterionList.add((CriterionImpl) criterion);
    }

    public String getCode() {
        return code;
    }

    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.CriterionImpl.class)
    public List<CriterionImpl> getCriterionList() {
        return criterionList;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getRank() {
        return rank;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCriterionList(List<? extends Criterion> criterionList) {
        this.criterionList = (List<CriterionImpl>) criterionList;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
