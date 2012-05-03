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
package org.opens.tgol.entity.factory.contract;

import java.util.Date;
import java.util.Set;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.functionality.Functionality;
import org.opens.tgol.entity.option.OptionElement;
import org.opens.tgol.entity.referential.Referential;
import org.opens.tgol.entity.scenario.Scenario;
import org.opens.tgol.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public interface ContractFactory extends GenericFactory<Contract> {

    /**
     * 
     * @param label
     * @param beginDate
     * @param endDate
     * @param renewalDate
     * @param price
     * @param functionalitySet
     * @param optionSet
     * @param referentialSet
     * @param scenarioSet
     * @param user
     * @return 
     *      an initialised instance of Contract
     */
    Contract createContract(
            String label,
            Date beginDate,
            Date endDate,
            Date renewalDate,
            Float price,
            Set<? extends Functionality> functionalitySet,
            Set<? extends OptionElement> optionSet,
            Set<? extends Referential> referentialSet,
            Set<? extends Scenario> scenarioSet,
            User user);

}
