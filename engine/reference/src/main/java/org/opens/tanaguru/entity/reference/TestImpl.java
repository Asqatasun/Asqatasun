/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
 * Contact us by mail: open-s AT open-s DOT com
 */
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

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "TEST")
@XmlRootElement
public class TestImpl implements Test, Serializable {

    @Column(name = "Cd_Test")
    private String code;
    @ManyToOne
    @JoinColumn(name = "Id_Criterion")
    private CriterionImpl criterion;
    @ManyToOne
    @JoinColumn(name = "Id_Decision_Level")
    private DecisionLevelImpl decisionLevel;
    @Column(name = "Description")
    private String description;
    @Id
    @GeneratedValue
    @Column(name = "Id_Test")
    private Long id;
    @Column(name = "Label")
    private String label;
    @ManyToOne
    @JoinColumn(name = "Id_Level")
    private LevelImpl level;
    @Column(name = "Rank")
    private int rank;
    @ManyToOne
    @JoinColumn(name = "Id_Rule")
    private RuleImpl rule;
    @Column(name = "Rule_Archive_Name")
    private String ruleArchiveName;
    @Column(name = "Rule_Class_Name")
    private String ruleClassName;
    @ManyToOne
    @JoinColumn(name = "Id_Scope")
    private ScopeImpl scope;
    @Column(name = "Rule_Design_Url")
    private String ruleDesignUrl;

    public TestImpl() {
        super();
    }

    public TestImpl(String code, String label, String description) {
        super();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.CriterionImpl.class)
    public Criterion getCriterion() {
        return this.criterion;
    }
    
    @Override
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.DecisionLevelImpl.class)
    public DecisionLevel getDecisionLevel() {
        return this.decisionLevel;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     *
     * @return @deprecated
     */
    @Deprecated
    @Override
    public String getFullCode() {
        if (criterion == null) {
            return "";
        }

        Reference reference = this.criterion.getReference();
        Theme theme = this.criterion.getTheme();

        return reference.getCode() + theme.getCode() + criterion.getCode()
                + this.getCode();
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.LevelImpl.class)
    public Level getLevel() {
        return this.level;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.RuleImpl.class)
    public Rule getRule() {
        return this.rule;
    }

    @Override
    public String getRuleArchiveName() {
        return ruleArchiveName;
    }

    @Override
    public String getRuleClassName() {
        return ruleClassName;
    }

    @Override
    @XmlElementRef(type = org.opens.tanaguru.entity.reference.ScopeImpl.class)
    public Scope getScope() {
        return this.scope;
    }

    @Override
    public String getRuleDesignUrl() {
        return this.ruleDesignUrl;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setCriterion(Criterion criterion) {
        this.criterion = (CriterionImpl) criterion;
    }

    @Override
    public void setDecisionLevel(DecisionLevel decisionLevel) {
        this.decisionLevel = (DecisionLevelImpl) decisionLevel;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void setLevel(Level priority) {
        this.level = (LevelImpl) priority;
    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public void setRule(Rule rule) {
        this.rule = (RuleImpl) rule;
    }

    @Override
    public void setRuleArchiveName(String ruleArchiveName) {
        this.ruleArchiveName = ruleArchiveName;
    }

    @Override
    public void setRuleClassName(String ruleClassName) {
        this.ruleClassName = ruleClassName;
    }

    @Override
    public void setScope(Scope scope) {
        this.scope = (ScopeImpl) scope;
    }

    @Override
    public void setRuleDesignUrl(String ruleDesignUrl) {
        this.ruleDesignUrl = ruleDesignUrl;
    }
}
