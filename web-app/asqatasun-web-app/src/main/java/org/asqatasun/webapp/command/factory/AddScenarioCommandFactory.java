/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.webapp.command.factory;

import org.asqatasun.webapp.command.AddScenarioCommand;
import org.asqatasun.webapp.entity.contract.Contract;
import org.springframework.stereotype.Component;


/**
 *
 * @author jkowalczyk
 */
@Component
public class AddScenarioCommandFactory {

    /**
     * Private constructor
     */
    private AddScenarioCommandFactory() {}
    
    /**
     * Return a initialised auditCommand object for the given contract. This object
     * handles the last values selected by the user
     * 
     * @param contract
     * @return
     */
    public AddScenarioCommand getAddScenarioCommand (Contract contract) {
        AddScenarioCommand addScenarioCommand = new AddScenarioCommand();
        addScenarioCommand.setContractId(contract.getId());
        return addScenarioCommand;
    }

}
