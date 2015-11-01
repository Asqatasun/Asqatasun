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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.webapp.entity.service.contract;

import java.util.Collection;
import org.tanaguru.sdk.entity.service.GenericDataService;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public interface ContractDataService extends GenericDataService<Contract, Long> {

    /**
     *
     * @param user
     * @return
     *      the collection of contracts for a given user
     */
    Collection<Contract> getAllContractsByUser(User user);
    
    /**
     * The Url associated with the contract is an option. We need to iterate 
     * through the options of the contract to retrieve the one that handles 
     * the Url. If this option is missing, an empty String is returned.
     * 
     * @param contract
     * @return 
     */
    String getUrlFromContractOption(Contract contract);
    
    /**
     * The contract may have been created with preset functionalities. In this
     * case, the key of the preset contract is kept as a contract option and this 
     * method extract the value of this option from the option set
     * 
     * @param contract
     * @return the presetContractKey
     */
    String getPresetContractKeyContractOption(Contract contract);
    
    /**
     * 
     * @param contract
     * @param functionnalityKey
     * @return whether the contract has the given functionnality
     */
    boolean doesContractHaveFunctionality(Contract contract, String functionnalityKey);

}