/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.webapp.entity.contract;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.tanaguru.webapp.entity.functionality.Functionality;
import org.tanaguru.webapp.entity.functionality.FunctionalityImpl;
import org.tanaguru.webapp.entity.option.OptionElement;
import org.tanaguru.webapp.entity.option.OptionElementImpl;
import org.tanaguru.webapp.entity.referential.Referential;
import org.tanaguru.webapp.entity.referential.ReferentialImpl;
import org.tanaguru.webapp.entity.scenario.Scenario;
import org.tanaguru.webapp.entity.scenario.ScenarioImpl;
import org.tanaguru.webapp.entity.user.User;
import org.tanaguru.webapp.entity.user.UserImpl;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "TGSI_CONTRACT")
@XmlRootElement
public class ContractImpl implements Contract, Serializable {

    private static final long serialVersionUID = -8963515112270144367L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Contract")
    private Long id;

    @Column(name = "Label", nullable=false)
    private String label;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "Begin_Date")
    private Date beginDate;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "End_Date")
    private Date endDate;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "Renewal_Date")
    private Date renewalDate;

    @Column(name = "Price")
    private Float price;

    @ManyToOne
    @JoinColumn(name = "USER_Id_User")
    private UserImpl user;

    @OneToMany(mappedBy = "contract")
    private Set<ActImpl> actSet = new LinkedHashSet<>();
    
    @OneToMany(mappedBy = "contract")
    private Set<ScenarioImpl> scenarioSet = new LinkedHashSet<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "TGSI_CONTRACT_FUNCTIONALITY", joinColumns =
        @JoinColumn(name = "CONTRACT_Id_Contract"), inverseJoinColumns =
        @JoinColumn(name = "FUNCTIONALITY_Id_Functionality"))
    private Set<FunctionalityImpl> functionalitySet = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "TGSI_CONTRACT_OPTION_ELEMENT", joinColumns =
        @JoinColumn(name = "CONTRACT_Id_Contract"), inverseJoinColumns =
        @JoinColumn(name = "OPTION_ELEMENT_Id_Option_Element"))
    Set<OptionElementImpl> optionElementSet = new HashSet<OptionElementImpl>();
    
    @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "TGSI_CONTRACT_REFERENTIAL", joinColumns =
        @JoinColumn(name = "CONTRACT_Id_Contract"), inverseJoinColumns =
        @JoinColumn(name = "REFERENTIAL_Id_Referential"))
    Set<ReferentialImpl> referentialSet = new HashSet<ReferentialImpl>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long l) {
        this.id = l;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public Date getBeginDate() {
        if (beginDate != null) {
            return new Date(beginDate.getTime());
        } else {
            return null;
        }
    }

    @Override
    public void setBeginDate(Date beginDate) {
        if (beginDate != null) {
            this.beginDate = new Date(beginDate.getTime());
        } else {
            this.beginDate = null;
        }
    }

    @Override
    public Date getEndDate() {
        if (endDate != null) {
            return new Date(endDate.getTime());
        } else {
            return null;
        }
    }

    @Override
    public void setEndDate(Date endDate) {
        if (endDate != null) {
            this.endDate = new Date(endDate.getTime());
        } else {
            this.endDate = null;
        }
    }

    @Override
    public Date getRenewalDate() {
        if (renewalDate != null) {
            return new Date(renewalDate.getTime());
        } else {
            return null;
        }
    }

    @Override
    public void setRenewalDate(Date renewalDate) {
        if (renewalDate != null) {
            this.renewalDate = new Date(renewalDate.getTime());
        } else {
            this.renewalDate = null;
        }
    }

    @Override
    public Float getPrice() {
        return price;
    }

    @Override
    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = (UserImpl)user;
    }

    @Override
    public Collection<Act> getActSet() {
        return (Collection)this.actSet;
    }

    @Override
    public void addAct(Act act) {
        actSet.add((ActImpl)act);
    }

    @Override
    public void addAllAct(Collection<Act> actSet) {
        for (Act act : actSet) {
            if (act instanceof ActImpl) {
                this.actSet.add((ActImpl)act);
            }
        }
    }

    @Override
    public Collection<OptionElement> getOptionElementSet() {
        return (Collection)this.optionElementSet;
    }

    @Override
    public void addOptionElement(OptionElement option) {
        optionElementSet.add((OptionElementImpl)option);
    }

    @Override
    public void addAllOptionElement(Collection<OptionElement> optionElementSet) {
        this.optionElementSet = new HashSet<>();
        for (OptionElement optionElement : optionElementSet) {
            if (optionElement instanceof OptionElementImpl) {
                this.optionElementSet.add((OptionElementImpl)optionElement);
            }
        }
    }

    @Override
    public Collection<Functionality> getFunctionalitySet() {
        return (Collection)this.functionalitySet;
    }

    @Override
    public void addFunctionality(Functionality functionality) {
        this.functionalitySet.add((FunctionalityImpl)functionality);
    }

    @Override
    public void addAllFunctionality(Collection<Functionality> functionalitySet) {
        this.functionalitySet = new HashSet<>();
        for (Functionality funct : functionalitySet) {
            if (funct instanceof FunctionalityImpl) {
                this.functionalitySet.add((FunctionalityImpl)funct);
            }
        }
    }
    
    @Override
    public Set<Referential> getReferentialSet() {
        Set<Referential> lReferentialSet = new HashSet<>();
        lReferentialSet.addAll(this.referentialSet);
        return lReferentialSet;
    }

    @Override
    public void addReferential(Referential referential) {
        this.referentialSet.add((ReferentialImpl)referential);
    }

    @Override
    public void addAllReferential(Collection<Referential> referentialSet) {
        this.referentialSet = new HashSet<>();
        for (Referential ref : referentialSet) {
            if (ref instanceof ReferentialImpl) {
                this.referentialSet.add((ReferentialImpl)ref);
            }
        }
    }
    
    @Override
    public Collection<Scenario> getScenarioSet() {
        return (Collection)this.scenarioSet;
    }

    @Override
    public void addScenario(Scenario scenario) {
        this.scenarioSet.add((ScenarioImpl)scenario);
    }

    @Override
    public void addAllScenario(Collection<Scenario> scenarioSet) {
        for (Scenario scenario : scenarioSet) {
            if (scenario instanceof ScenarioImpl) {
                this.scenarioSet.add((ScenarioImpl)scenario);
            }
        }
    }

}