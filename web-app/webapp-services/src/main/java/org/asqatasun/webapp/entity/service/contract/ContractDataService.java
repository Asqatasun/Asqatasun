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
package org.asqatasun.webapp.entity.service.contract;

import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.asqatasun.entity.service.AbstractGenericDataService;
import org.asqatasun.webapp.entity.contract.Contract;
import org.asqatasun.webapp.entity.dao.contract.ContractDAO;
import org.asqatasun.webapp.entity.functionality.Functionality;
import org.asqatasun.webapp.entity.option.OptionElement;
import org.asqatasun.webapp.entity.user.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkowalczyk
 */
@Service("contractDataService")
public class ContractDataService extends AbstractGenericDataService<Contract, Long> {

    private static final String URL_OPTION_NAME = "DOMAIN";
    private static final String PRESET_CONTRACT_OPTION_NAME = "PRESET_CONTRACT";

    public Collection<Contract> getAllContractsByUser(User user) {
        return ((ContractDAO) entityDao).findAllContractsByUser(user);
    }

    public String getUrlFromContractOption(Contract contract) {
        return entityDao.read(contract.getId())
            .getOptionElementSet()
            .stream()
            .filter(optionElement -> StringUtils.equals(URL_OPTION_NAME, optionElement.getOption().getCode()))
            .findFirst().map(OptionElement::getValue).orElse("");
    }

    public String getPresetContractKeyContractOption(Contract contract) {
        return entityDao.read(contract.getId())
            .getOptionElementSet()
            .stream()
            .filter(optionElement -> StringUtils.equals(PRESET_CONTRACT_OPTION_NAME, optionElement.getOption().getCode()))
            .findFirst().map(OptionElement::getValue).orElse("");
    }
    public boolean doesContractHaveFunctionality(Contract contract, String functionnalityKey) {
        for (Functionality functionality : contract.getFunctionalitySet()) {
            if (StringUtils.equalsIgnoreCase(functionality.getCode(), functionnalityKey)) {
                return true;
            }
        }
        return false;
    }

}
