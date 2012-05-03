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
package org.opens.tgol.entity.contract;

import java.util.Date;
import java.util.Set;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tgol.entity.functionality.Functionality;
import org.opens.tgol.entity.option.OptionElement;
import org.opens.tgol.entity.referential.Referential;
import org.opens.tgol.entity.scenario.Scenario;
import org.opens.tgol.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public interface Contract extends Entity {

    /**
     * 
     * @return
     *      the label associated with the contract
     */
    String getLabel();

    /**
     *
     * @param label
     */
    void setLabel(String label);

    /**
     *
     * @return
     *      the begin date of the contract
     */
    Date getBeginDate();

    /**
     *
     * @param beginDate
     */
    void setBeginDate(Date beginDate);

    /**
     *
     * @return
     *      the end date of the contract
     */
    Date getEndDate();

    /**
     *
     * @param endDate
     */
    void setEndDate(Date endDate);

    /**
     *
     * @return
     *      the renewal date of the contract
     */
    Date getRenewalDate();

    /**
     *
     * @param renewalDate
     */
    void setRenewalDate(Date renewalDate);

    /**
     *
     * @return
     *      the price of the contract
     */
    Float getPrice();

    /**
     *
     * @param price
     */
    void setPrice(Float price);

    /**
     *
     * @return
     *      the user owning the contract
     */
    User getUser();

    /**
     *
     * @param user
     */
    void setUser(User user);

    /**
     *
     * @return
     *      the set of the acts associated with the contract
     */
    Set<? extends Act> getActSet();

    /**
     *
     * @param act
     */
    void addAct (Act act);

    /**
     *
     * @param actSet
     */
    void addAllAct (Set<? extends Act> actSet);

        /**
     *
     * @return
     *      the set of the option elements associated with the offer
     */
    Set<? extends OptionElement> getOptionElementSet();

    /**
     *
     * @param optionElement
     */
    void addOptionElement (OptionElement optionElement);

    /**
     *
     * @param optionElementSet
     */
    void addAllOptionElement (Set<? extends OptionElement> optionElementSet);
    
    /**
     *
     * @return
     *      the set of functionality associated with the contract
     */
    Set<? extends Functionality> getFunctionalitySet();

    /**
     * 
     * @param functionality
     */
    void addFunctionality(Functionality functionality);
    
    /**
     * 
     * @param functionality
     */
    void addAllFunctionality(Set<? extends Functionality> functionalitySet);
    
    /**
     *
     * @return
     *      the set of references associated with the contract
     */
    Set<? extends Referential> getReferentialSet();

    /**
     * 
     * @param reference
     */
    void addReferential(Referential referential);
    
    /**
     * 
     * @param reference
     */
    void addAllReferential(Set<? extends Referential> referentialSet);
    
    /**
     *
     * @return
     *      the set of scenarios associated with the contract
     */
    Set<? extends Scenario> getScenarioSet();

    /**
     * 
     * @param reference
     */
    void addScenario(Scenario Scenario);
    
    /**
     * 
     * @param reference
     */
    void addAllScenario(Set<? extends Scenario> scenarioSet);
}
