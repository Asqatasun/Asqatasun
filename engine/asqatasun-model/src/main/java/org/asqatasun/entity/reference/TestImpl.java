/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.entity.reference;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

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
    @JsonIgnore
    private DecisionLevelImpl decisionLevel;
    @Column(name = "Description")
    @JsonIgnore
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Test")
    private Long id;
    @Column(name = "Label")
    private String label;
    @ManyToOne
    @JoinColumn(name = "Id_Level")
    private LevelImpl level;
    @Column(name = "Rank")
    @JsonIgnore
    private int rank;
    @Column(name = "Rule_Archive_Name")
    @JsonIgnore
    private String ruleArchiveName;
    @Column(name = "Rule_Class_Name")
    @JsonIgnore
    private String ruleClassName;
    @ManyToOne
    @JoinColumn(name = "Id_Scope")
    private ScopeImpl scope;
    @Column(name = "Rule_Design_Url")
    private String ruleDesignUrl;
    @Column(name = "Weight", precision=2, scale=1)
    private BigDecimal weight;
    @Column(name = "No_Process")
    @JsonIgnore
    private boolean noProcess;

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
    @XmlElementRef(type = org.asqatasun.entity.reference.CriterionImpl.class)
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonSubTypes({
        @JsonSubTypes.Type(value=org.asqatasun.entity.reference.CriterionImpl.class, name="Criterion")})
    public Criterion getCriterion() {
        return this.criterion;
    }
    
    @Override
    @XmlElementRef(type = org.asqatasun.entity.reference.DecisionLevelImpl.class)
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
    @JsonIgnore
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
    @XmlElementRef(type = org.asqatasun.entity.reference.LevelImpl.class)
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonSubTypes({
        @JsonSubTypes.Type(value=org.asqatasun.entity.reference.LevelImpl.class, name="Level")})
    public Level getLevel() {
        return this.level;
    }

    @Override
    public int getRank() {
        return rank;
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
    @XmlElementRef(type = ScopeImpl.class)
    @JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonSubTypes({
        @JsonSubTypes.Type(value=ScopeImpl.class, name="Scope")})
    public Scope getScope() {
        return this.scope;
    }

    @Override
    public String getRuleDesignUrl() {
        return this.ruleDesignUrl;
    }
    
    @Override
    public BigDecimal getWeight() {
        return this.weight;
    }
    
    @Override
    public boolean getNoProcess() {
        return this.noProcess;
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

    @Override
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
    
    @Override
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    
    @Override
    public void setNoProcess(boolean noProcess) {
        this.noProcess = noProcess;
    }
 
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + (this.code != null ? this.code.hashCode() : 0);
        hash = 11 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TestImpl other = (TestImpl) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return Objects.equals(this.id, other.id) || (this.id != null && this.id.equals(other.id));
    }

}