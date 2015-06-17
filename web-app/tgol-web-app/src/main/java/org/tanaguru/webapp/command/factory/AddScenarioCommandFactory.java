/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.tanaguru.webapp.command.factory;

import org.tanaguru.webapp.command.AddScenarioCommand;
import org.tanaguru.webapp.entity.contract.Contract;


/**
 *
 * @author jkowalczyk
 */
public final class AddScenarioCommandFactory {

    /**
     * The holder that handles the unique instance of AddScenarioCommandFactory
     */
    private static class AddScenarioCommandFactoryHolder {
        private static final AddScenarioCommandFactory INSTANCE = new AddScenarioCommandFactory();
    }
    
    /**
     * Private constructor
     */
    private AddScenarioCommandFactory() {}
    
    /**
     * Singleton pattern based on the "Initialization-on-demand 
     * holder idiom". See @http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * @return the unique instance of AddScenarioCommandFactory
     */
    public static AddScenarioCommandFactory getInstance() {
        return AddScenarioCommandFactoryHolder.INSTANCE;
    }
    
    /**
     * Return a initialised auditCommand object for the given contract. This object
     * handles the last values selected by the user
     * 
     * @param contract
     * @return
     */
    public static AddScenarioCommand getAddScenarioCommand (Contract contract) {
        AddScenarioCommand addScenarioCommand = new AddScenarioCommand();
        addScenarioCommand.setContractId(contract.getId());
        return addScenarioCommand;
    }

}