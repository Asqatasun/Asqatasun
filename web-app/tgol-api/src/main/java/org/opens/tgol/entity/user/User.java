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
package org.opens.tgol.entity.user;

import java.util.Collection;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.option.OptionElement;

/**
 *
 * @author jkowalczyk
 */
public interface User extends Entity {

    /**
     *
     * @return the user email address
     */
    String getEmail1();

    /**
     * 
     * @param email
     */
    void setEmail1(String email);

    /**
     * @return the password (encrypted)
     */
    String getPassword();

    /**
     * 
     * @param password
     */
    void setPassword (String password);

    /**
     *
     * @return
     *          the user name
     */
    String getName();

    /**
     *
     * @param name
     */
    void setName(String name);

    /**
     *
     * @return
     *          the user first name
     */
    String getFirstName();

    /**
     *
     * @param firstName
     */
    void setFirstName(String firstName);

    /**
     * 
     * @return
     *          the user address
     */
    String getAddress();

    /**
     *
     * @param address
     */
    void setAddress(String address);
    
    /**
     *
     * @return
     *          the user phone number
     */
    String getPhoneNumber();

    /**
     *
     * @param phoneNumber
     */
    void setPhoneNumber(String phoneNumber);

    /**
     *
     * @param user
     *          the contract to add to a user
     */
    void addContract(Contract user);


    /**
     *
     * @param contractSet
     *          the contracts set to add to a user
     */
    void addAllContracts(Collection<Contract> contractSet);

    /**
     *
     * @return
     *           the list of the user's contracts
     */
    Collection<Contract> getContractSet();

    /**
     *
     * @return
     *      the url of the web page 1 associated with the user
     */
    String getWebUrl1();

    /**
     * 
     * @param webUrl1
     */
    void setWebUrl1(String webUrl1);

    /**
     *
     * @return
     *      the url of the web page 2 associated with the user
     */
    String getWebUrl2();

    /**
     * 
     * @param webUrl2
     */
    void setWebUrl2(String webUrl2);

    /**
     *
     * @return
     *          the identica Id of the user
     */
    String getIdenticaId();

    /**
     * 
     * @param identicaId
     */
    void setIdenticaId(String identicaId);

    /**
     *
     * @return
     *          the twitter Id of the user
     */
    String getTwitterId();

    /**
     * 
     * @param twitterId
     */
    void setTwitterId(String twitterId);

    /**
     * 
     * @return
     *      the role of the user
     */
    Role getRole();

    /**
     * 
     * @param role
     */
    void setRole(Role role);

    /**
     *
     * @return
     *      if the current is activated or not
     */
    boolean isAccountActivated();

    /**
     * 
     * @param value
     */
    void setAccountActivation(boolean value);

    /**
     * 
     * @return 
     */
    Collection<OptionElement> getOptionElementSet();

    /**
     * 
     * @param option 
     */
    void addOptionElement(OptionElement option);

    /**
     * 
     * @param optionElementSet 
     */
    void addAllOptionElement(Collection<OptionElement> optionElementSet);
    
}