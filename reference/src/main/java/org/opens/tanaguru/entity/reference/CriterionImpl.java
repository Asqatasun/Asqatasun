package org.opens.tanaguru.entity.reference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "CRITERION")
@XmlRootElement
public class CriterionImpl implements Criterion, Serializable {

    @Column(name = "Cd_Criterion")
    protected String code;
    @Column(name = "Description")
    protected String description;
    @Id
    @GeneratedValue
    @Column(name = "IdCriterion")
    protected Long id;
    @Column(name = "Label")
    protected String label;
    @Column(name = "Rank")
    protected int rank;
    @ManyToOne
    @JoinColumn(name = "Id_Reference")
    protected ReferenceImpl reference;
    @OneToMany(mappedBy = "criterion", cascade = CascadeType.ALL)
    protected List<TestImpl> testList = new ArrayList<TestImpl>();
    @ManyToOne
    @JoinColumn(name = "Id_Theme")
    protected ThemeImpl theme;

    public CriterionImpl() {
        super();
    }

    public CriterionImpl(String code, String label, String description) {
        super();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    public void addTest(Test test) {
        test.setCriterion(this);
        this.testList.add((TestImpl) test);
    }

    public String getCode() {
        return code;
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

    @XmlElementRef(type = org.opens.tanaguru.entity.reference.ReferenceImpl.class)
    public Reference getReference() {
        return reference;
    }

    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.TestImpl.class)
    public List<TestImpl> getTestList() {
        return testList;
    }

    @XmlElementRef(type = org.opens.tanaguru.entity.reference.ThemeImpl.class)
    public Theme getTheme() {
        return theme;
    }

    public void setCode(String code) {
        this.code = code;
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

    public void setReference(Reference reference) {
        this.reference = (ReferenceImpl) reference;
    }

    public void setTestList(List<? extends Test> testList) {
        this.testList = (List<TestImpl>) testList;
    }

    public void setTheme(Theme theme) {
        this.theme = (ThemeImpl) theme;
    }
}
