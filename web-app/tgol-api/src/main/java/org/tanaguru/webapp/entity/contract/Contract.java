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

import java.util.Collection;
import java.util.Date;
import org.tanaguru.sdk.entity.Entity;
import org.tanaguru.webapp.entity.functionality.Functionality;
import org.tanaguru.webapp.entity.option.OptionElement;
import org.tanaguru.webapp.entity.referential.Referential;
import org.tanaguru.webapp.entity.scenario.Scenario;
import org.tanaguru.webapp.entity.user.User;

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
    Collection<Act> getActSet();

    /**
     *
     * @param act
     */
    void addAct (Act act);

    /**
     *
     * @param actSet
     */
    void addAllAct (Collection<Act> actSet);

        /**
     *
     * @return
     *      the set of the option elements associated with the offer
     */
    Collection<OptionElement> getOptionElementSet();

    /**
     *
     * @param optionElement
     */
    void addOptionElement (OptionElement optionElement);

    /**
     *
     * @param optionElementSet
     */
    void addAllOptionElement (Collection<OptionElement> optionElementSet);
    
    /**
     *
     * @return
     *      the set of functionality associated with the contract
     */
    Collection<Functionality> getFunctionalitySet();

    /**
     * 
     * @param functionality
     */
    void addFunctionality(Functionality functionality);
    
    /**
     * 
     * @param functionalitySet
     */
    void addAllFunctionality(Collection<Functionality> functionalitySet);
    
    /**
     *
     * @return
     *      the set of references associated with the contract
     */
    Collection<Referential> getReferentialSet();

    /**
     * 
     * @param referential
     */
    void addReferential(Referential referential);
    
    /**
     * 
     * @param referentialSet
     */
    void addAllReferential(Collection<Referential> referentialSet);
    
    /**
     *
     * @return
     *      the set of scenarios associated with the contract
     */
    Collection<Scenario> getScenarioSet();

    /**
     * 
     * @param scenario
     */
    void addScenario(Scenario scenario);
    
    /**
     * 
     * @param scenarioSet
     */
    void addAllScenario(Collection<Scenario> scenarioSet);
}
