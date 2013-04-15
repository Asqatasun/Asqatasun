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
package org.opens.tgol.command.factory;

import org.opens.tgol.command.AddScenarioCommand;
import org.opens.tgol.entity.contract.Contract;


/**
 *
 * @author jkowalczyk
 */
public final class AddScenarioCommandFactory {

    private static AddScenarioCommandFactory addScenarioCommandFactory;

    /**
     * Factory has default constructor
     */
    private AddScenarioCommandFactory(){}

    public static synchronized AddScenarioCommandFactory getInstance() {
        if (addScenarioCommandFactory == null) {
            addScenarioCommandFactory = new AddScenarioCommandFactory();
        }
        return addScenarioCommandFactory;
    }
    
    /**
     * Return a initialised auditCommand object for the given contract. This object
     * handles the last values selected by the user
     * 
     * @param contract
     * @param auditSetUpFormFieldList
     * @param auditSite
     * @return
     */
    public static AddScenarioCommand getAddScenarioCommand (
            Contract contract) {
        AddScenarioCommand addScenarioCommand = new AddScenarioCommand();
        addScenarioCommand.setContractId(contract.getId());
        return addScenarioCommand;
    }

}