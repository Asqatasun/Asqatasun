package org.opens.tanaguru.entity.reference;

import java.util.Collection;

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

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "RULE")
@XmlRootElement
public class RuleImpl implements Rule {

    @Column(name = "Class_Name", nullable = false)
    protected String className;
    @Column(name = "Description")
    protected String description;
    @Id
    @GeneratedValue
    @Column(name = "Id_Rule")
    protected Long id;
    @ManyToOne
    @JoinColumn(name = "Id_Rule_Package")
    protected RulePackageImpl owningPackage;
    @OneToMany(mappedBy = "rule", cascade = CascadeType.ALL)
    protected Collection<TestImpl> testList;

    public RuleImpl() {
        super();
    }

    public RuleImpl(String className, String description) {
        this();
        this.className = className;
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Rule)) {
            return false;
        }
        Rule rule = (Rule) obj;
        if (getOwningPackage() == null && rule.getOwningPackage() != null) {
            return false;
        }
        if (getClassName() == null && rule.getClassName() != null) {
            return false;
        }
        if (getDescription() == null && rule.getDescription() != null) {
            return false;
        }
        return getOwningPackage().equals(rule.getOwningPackage()) && getClassName().equals(rule.getClassName()) && getDescription().equals(rule.getDescription());
    }

    public String getClassName() {
        return className;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    @XmlElementRef(type = org.opens.tanaguru.entity.reference.RulePackageImpl.class)
    public RulePackage getOwningPackage() {
        return owningPackage;
    }

    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.TestImpl.class)
    public Collection<TestImpl> getTestList() {
        return testList;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        if (getOwningPackage() != null) {
            hashCode += getOwningPackage().hashCode();
        }
        if (getClassName() != null) {
            hashCode += getClassName().hashCode();
        }
        if (getDescription() != null) {
            hashCode += getDescription().hashCode();
        }
        return hashCode;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOwningPackage(RulePackage owningPackage) {
        this.owningPackage = (RulePackageImpl) owningPackage;
    }

    public void setTestList(Collection<? extends Test> testList) {
        this.testList = (Collection<TestImpl>) testList;
    }
}
