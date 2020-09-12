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
package org.asqatasun.entity.factory.contract;

import java.util.Date;
import java.util.Set;

import org.asqatasun.entity.GenericFactory;
import org.asqatasun.entity.contract.Contract;
import org.asqatasun.entity.functionality.Functionality;
import org.asqatasun.entity.option.OptionElementImpl;
import org.asqatasun.entity.referential.Referential;
import org.asqatasun.entity.scenario.Scenario;
import org.asqatasun.entity.user.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author jkowalczyk
 */
@Component("contractFactory")
public class ContractFactory implements GenericFactory <Contract>{

    public Contract createContract(
            String label,
            Date beginDate,
            Date endDate,
            Date renewalDate,
            Float price,
            Set<Functionality> functionalitySet, 
            Set<OptionElementImpl> optionElementSet,
            Set<Referential> referentialSet, 
            Set<Scenario> ScenarioSet, 
            User user) {
        Contract contract = create();
        contract.setLabel(label);
        contract.setBeginDate(beginDate);
        contract.setEndDate(endDate);
        contract.setRenewalDate(renewalDate);
        contract.addAllFunctionality(functionalitySet);
        contract.addAllOptionElement(optionElementSet);
        contract.addAllReferential(referentialSet);
        contract.addAllScenario(ScenarioSet);
        contract.setPrice(price);
        return contract;
    }

    @Override
    public Contract create() {
        return new Contract();
    }

}
