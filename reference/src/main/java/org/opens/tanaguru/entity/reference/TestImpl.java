package org.opens.tanaguru.entity.reference;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TEST")
@XmlRootElement
public class TestImpl implements Test, Serializable {

    @Column(name = "Cd_Test")
    protected String code;
    @ManyToOne
    @JoinColumn(name = "Id_Criterion")
    protected CriterionImpl criterion;
    @ManyToOne
    @JoinColumn(name = "Id_Decision_Level")
    protected DecisionLevelImpl decisionLevel;
    @Column(name = "Description")
    protected String description;
    @Id
    @GeneratedValue
    @Column(name = "Id_Test")
    protected Long id;
    @Column(name = "Label")
    protected String label;
    @ManyToOne
    @JoinColumn(name = "Id_Level")
    protected LevelImpl level;
    @Column(name = "Rank")
    protected int rank;
    @ManyToOne
    @JoinColumn(name = "Id_Rule")
    protected RuleImpl rule;
    @Column(name = "Rule_Archive_Name")
    protected String ruleArchiveName;
    @Column(name = "Rule_Class_Name")
    protected String ruleClassName;
    @ManyToOne
    @JoinColumn(name = "Id_Scope")
    protected ScopeImpl scope;
    @Column(name = "Rule_Design_Url")
    protected String ruleDesignUrl;


    public TestImpl() {
        super();
    }

    public TestImpl(String code, String label, String description) {
        super();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    @XmlElementRef(type = org.opens.tanaguru.entity.reference.CriterionImpl.class)
    public Criterion getCriterion() {
        return this.criterion;
    }

    @XmlElementRef(type = org.opens.tanaguru.entity.reference.DecisionLevelImpl.class)
    public DecisionLevel getDecisionLevel() {
        return this.decisionLevel;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     *
     * @return
     * @deprecated
     */
    @Deprecated
    public String getFullCode() {
        if (criterion == null) {
            return "";
        }

        Reference reference = this.criterion.getReference();
        Theme theme = this.criterion.getTheme();

        return reference.getCode() + theme.getCode() + criterion.getCode()
                + this.getCode();
    }

    public Long getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    @XmlElementRef(type = org.opens.tanaguru.entity.reference.LevelImpl.class)
    public Level getLevel() {
        return this.level;
    }

    public int getRank() {
        return rank;
    }

    @XmlElementRef(type = org.opens.tanaguru.entity.reference.RuleImpl.class)
    public Rule getRule() {
        return this.rule;
    }

    public String getRuleArchiveName() {
        return ruleArchiveName;
    }

    public String getRuleClassName() {
        return ruleClassName;
    }

    @XmlElementRef(type = org.opens.tanaguru.entity.reference.ScopeImpl.class)
    public Scope getScope() {
        return this.scope;
    }

    public String getRuleDesignUrl() {
        return this.ruleDesignUrl;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = (CriterionImpl) criterion;
    }

    public void setDecisionLevel(DecisionLevel decisionLevel) {
        this.decisionLevel = (DecisionLevelImpl) decisionLevel;
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

    public void setLevel(Level priority) {
        this.level = (LevelImpl) priority;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setRule(Rule rule) {
        this.rule = (RuleImpl) rule;
    }

    public void setRuleArchiveName(String ruleArchiveName) {
        this.ruleArchiveName = ruleArchiveName;
    }

    public void setRuleClassName(String ruleClassName) {
        this.ruleClassName = ruleClassName;
    }

    public void setScope(Scope scope) {
        this.scope = (ScopeImpl) scope;
    }

    public void setRuleDesignUrl(String ruleDesignUrl) {
        this.ruleDesignUrl = ruleDesignUrl;
    }
}
