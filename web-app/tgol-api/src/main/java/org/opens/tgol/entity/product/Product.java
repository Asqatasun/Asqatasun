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
package org.opens.tgol.entity.product;

import java.util.Set;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.option.Option;

/**
 *
 * @author jkowalczyk
 */
public interface Product extends Entity {

    /**
     *
     * @return
     *      the label describing the offer
     */
    String getCode();

    /**
     *
     * @param label
     */
    void setCode (String code);

    /**
     *
     * @return
     *      the label describing the offer
     */
    String getLabel();

    /**
     *
     * @param label
     */
    void setLabel (String label);

    /**
     *
     * @return
     *      the description of the offer
     */
    String getDescription();

    /**
     * 
     * @param description
     */
    void setDescription(String description);

    /**
     *
     * @return
     *      the scope of the product
     */
    Scope getScope();

    /**
     * 
     * @param scope
     */
    void setScope(Scope scope);

    /**
     *
     * @param user
     *          the contract to add to the product
     */
    void addContract(Contract user);


    /**
     *
     * @param
     *          the products set to add to a user
     */
    void addAllContracts(Set<? extends Contract> contractSet);

    /**
     *
     * @return
     *           the list of the product's contracts
     */
    Set<? extends Contract> getContractSet();

    /**
     *
     * @return
     *      the set of the options associated with the offer
     */
    Set<? extends Option> getOptionSet();

    /**
     *
     * @param restriction
     */
    void addOption (Option option);

    /**
     *
     * @param optionSet
     */
    void addAllOption (Set<? extends Option> optionSet);

}