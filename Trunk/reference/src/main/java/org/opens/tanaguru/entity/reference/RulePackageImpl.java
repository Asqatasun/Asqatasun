package org.opens.tanaguru.entity.reference;

import java.util.ArrayList;
import java.util.Collection;

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

/**
 * 
 * @author ADEX
 */
@Entity
@Table(name = "RULE_PACKAGE")
@XmlRootElement
public class RulePackageImpl implements RulePackage {

    @Column(name = "Description")
    protected String description;
    @Id
    @GeneratedValue
    @Column(name = "Id_Rule_Package")
    protected Long id;
    @Column(name = "Package_Name")
    protected String packageName;
    @OneToMany(mappedBy = "owningPackage", cascade = CascadeType.ALL)
    protected Collection<RuleImpl> ruleList;

    public RulePackageImpl() {
        super();
        ruleList = new ArrayList<RuleImpl>();
    }

    public RulePackageImpl(String packageName, String description) {
        this();
        this.packageName = packageName;
        this.description = description;
    }

    public void addRule(Rule rule) {
        rule.setOwningPackage(this);
        this.ruleList.add((RuleImpl) rule);
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getPackageName() {
        return packageName;
    }

    @XmlElementWrapper
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.RuleImpl.class)
    public Collection<RuleImpl> getRuleList() {
        return ruleList;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setRuleList(Collection<? extends Rule> ruleList) {
        this.ruleList = (Collection<RuleImpl>) ruleList;
    }
}
