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
package org.asqatasun.webapp.command.factory;

import java.util.List;
import java.util.Map;
import org.asqatasun.webapp.command.ContractSortCommand;
import org.asqatasun.webapp.ui.form.FormField;
import org.asqatasun.webapp.ui.form.SelectElement;
import org.asqatasun.webapp.ui.form.SelectFormField;
import org.asqatasun.webapp.ui.form.TextualFormField;
import org.springframework.stereotype.Component;


/**
 *
 * @author jkowalczyk
 */
@Component
public final class ContractSortCommandFactory {

    /**
     * Private constructor
     */
    private ContractSortCommandFactory() {}

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
                contractDisplayCommand.getSortOptionMap().put(ff.getI18nKey(), ff.getValue());
            }
        }
        contractDisplayCommand.setUserId(userId);
        return contractDisplayCommand;
    }
    
}
