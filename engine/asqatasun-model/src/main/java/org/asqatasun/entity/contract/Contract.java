/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.entity.contract;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.asqatasun.entity.functionality.Functionality;
import org.asqatasun.entity.option.OptionElementImpl;
import org.asqatasun.entity.referential.Referential;
import org.asqatasun.entity.scenario.Scenario;
import org.asqatasun.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "CONTRACT")
@XmlRootElement
public class Contract implements org.asqatasun.entity.Entity, Serializable {

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
    private User user;

    @OneToMany(mappedBy = "contract")
    private Set<Act> actSet = new LinkedHashSet<>();
    
    @OneToMany(mappedBy = "contract")
    private Set<Scenario> scenarioSet = new LinkedHashSet<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "CONTRACT_FUNCTIONALITY", joinColumns =
        @JoinColumn(name = "CONTRACT_Id_Contract"), inverseJoinColumns =
        @JoinColumn(name = "FUNCTIONALITY_Id_Functionality"))
    private Set<Functionality> functionalitySet = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "CONTRACT_OPTION_ELEMENT", joinColumns =
        @JoinColumn(name = "CONTRACT_Id_Contract"), inverseJoinColumns =
        @JoinColumn(name = "OPTION_ELEMENT_Id_Option_Element"))
    Set<OptionElementImpl> optionElementSet = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "CONTRACT_REFERENTIAL", joinColumns =
        @JoinColumn(name = "CONTRACT_Id_Contract"), inverseJoinColumns =
        @JoinColumn(name = "REFERENTIAL_Id_Referential"))
    Set<Referential> referentialSet = new HashSet<>();

    public Long getId() {
        return id;
    }
    public void setId(Long l) {
        this.id = l;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public Date getBeginDate() {
        if (beginDate != null) {
            return new Date(beginDate.getTime());
        } else {
            return null;
        }
    }
    public void setBeginDate(Date beginDate) {
        if (beginDate != null) {
            this.beginDate = new Date(beginDate.getTime());
        } else {
            this.beginDate = null;
        }
    }
    public Date getEndDate() {
        if (endDate != null) {
            return new Date(endDate.getTime());
        } else {
            return null;
        }
    }
    public void setEndDate(Date endDate) {
        if (endDate != null) {
            this.endDate = new Date(endDate.getTime());
        } else {
            this.endDate = null;
        }
    }
    public Date getRenewalDate() {
        if (renewalDate != null) {
            return new Date(renewalDate.getTime());
        } else {
            return null;
        }
    }
    public void setRenewalDate(Date renewalDate) {
        if (renewalDate != null) {
            this.renewalDate = new Date(renewalDate.getTime());
        } else {
            this.renewalDate = null;
        }
    }
    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = (User)user;
    }
    public Collection<Act> getActSet() {
        return (Collection)this.actSet;
    }
    public void addAct(Act act) {
        actSet.add((Act)act);
    }
    public void addAllAct(Collection<Act> actSet) {
        for (Act act : actSet) {
            if (act instanceof Act) {
                this.actSet.add((Act)act);
            }
        }
    }
    public Collection<OptionElementImpl> getOptionElementSet() {
        return (Collection)this.optionElementSet;
    }
    public void addOptionElement(OptionElementImpl option) {
        optionElementSet.add((OptionElementImpl)option);
    }
    public void addAllOptionElement(Collection<OptionElementImpl> optionElementSet) {
        this.optionElementSet = new HashSet<>();
        for (OptionElementImpl optionElement : optionElementSet) {
            if (optionElement instanceof OptionElementImpl) {
                this.optionElementSet.add((OptionElementImpl)optionElement);
            }
        }
    }
    public Collection<Functionality> getFunctionalitySet() {
        return (Collection)this.functionalitySet;
    }
    public void addFunctionality(Functionality functionality) {
        this.functionalitySet.add((Functionality)functionality);
    }
    public void addAllFunctionality(Collection<Functionality> functionalitySet) {
        this.functionalitySet = new HashSet<>();
        for (Functionality funct : functionalitySet) {
            if (funct instanceof Functionality) {
                this.functionalitySet.add((Functionality)funct);
            }
        }
    }
    public Set<Referential> getReferentialSet() {
        Set<Referential> lReferentialSet = new HashSet<>();
        lReferentialSet.addAll(this.referentialSet);
        return lReferentialSet;
    }
    public void addReferential(Referential referential) {
        this.referentialSet.add((Referential)referential);
    }
    public void addAllReferential(Collection<Referential> referentialSet) {
        this.referentialSet = new HashSet<>();
        for (Referential ref : referentialSet) {
            if (ref instanceof Referential) {
                this.referentialSet.add((Referential)ref);
            }
        }
    }
    public Collection<Scenario> getScenarioSet() {
        return (Collection)this.scenarioSet;
    }
    public void addScenario(Scenario scenario) {
        this.scenarioSet.add((Scenario)scenario);
    }
    public void addAllScenario(Collection<Scenario> scenarioSet) {
        for (Scenario scenario : scenarioSet) {
            if (scenario instanceof Scenario) {
                this.scenarioSet.add((Scenario)scenario);
            }
        }
    }

}
