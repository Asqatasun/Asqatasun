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

import java.util.List;
import java.util.Map;
import org.opens.tgol.command.ContractSortCommand;
import org.opens.tgol.form.FormField;
import org.opens.tgol.form.SelectElement;
import org.opens.tgol.form.SelectFormField;
import org.opens.tgol.form.TextualFormField;


/**
 *
 * @author jkowalczyk
 */
public final class ContractSortCommandFactory {

    private static ContractSortCommandFactory contractDisplayCommandFactory;

    /**
     * Factory has default constructor
     */
    private ContractSortCommandFactory(){}

    public static synchronized ContractSortCommandFactory getInstance() {
        if (contractDisplayCommandFactory == null) {
            contractDisplayCommandFactory = new ContractSortCommandFactory();
        }
        return contractDisplayCommandFactory;
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
                        if (se.getDefault() && se.getEnabled()) {
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