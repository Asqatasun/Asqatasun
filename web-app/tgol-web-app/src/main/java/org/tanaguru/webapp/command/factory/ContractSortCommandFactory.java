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

import java.util.List;
import java.util.Map;
import org.tanaguru.webapp.command.ContractSortCommand;
import org.tanaguru.webapp.form.FormField;
import org.tanaguru.webapp.form.SelectElement;
import org.tanaguru.webapp.form.SelectFormField;
import org.tanaguru.webapp.form.TextualFormField;


/**
 *
 * @author jkowalczyk
 */
public final class ContractSortCommandFactory {

    /**
     * The holder that handles the unique instance of ContractSortCommandFactory
     */
    private static class ContractSortCommandFactoryHolder {
        private static final ContractSortCommandFactory INSTANCE = 
                new ContractSortCommandFactory();
    }
    
    /**
     * Private constructor
     */
    private ContractSortCommandFactory() {}
    
    /**
     * Singleton pattern based on the "Initialization-on-demand 
     * holder idiom". See @http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * @return the unique instance of ContractSortCommandFactory
     */
    public static ContractSortCommandFactory getInstance() {
        return ContractSortCommandFactoryHolder.INSTANCE;
    }
    
    /**
     * 
     * @param userId
     * @param formFieldList
     * @return
     * An initialised auditCommand object for the given contract. This object
     * handles the last values selected by the user
     */
    public ContractSortCommand getInitialisedContractDisplayCommand (
            Long userId,
            List<FormField> formFieldList) {
        ContractSortCommand contractDisplayCommand = new ContractSortCommand();
        for (FormField ff : formFieldList) {
            if (ff instanceof SelectFormField) {
                for (Map.Entry<String, List<SelectElement>> entry : ((SelectFormField)ff).getSelectElementMap().entrySet()) {
                    for (SelectElement se : entry.getValue()) {
                        if (se.getDefaultElement() && se.getEnabled()) {
                            contractDisplayCommand.getSortOptionMap().put(entry.getKey(), se.getValue());
                        }
                    }
                }
            } else if (ff instanceof TextualFormField) {
                contractDisplayCommand.getSortOptionMap().put(
                        ((TextualFormField)ff).getI18nKey(),
                        ((TextualFormField)ff).getValue());
            }
        }
        contractDisplayCommand.setUserId(userId);
        return contractDisplayCommand;
    }
    
}