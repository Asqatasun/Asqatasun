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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.webapp.form.parameterization.helper;

import java.util.*;
import org.tanaguru.webapp.form.parameterization.ContractOptionFormField;
import org.tanaguru.webapp.form.parameterization.builder.ContractOptionFormFieldBuilder;

/**
 * That class handles utility methods to deal with ContractOptionFormFields. 
 * 
 * @author jkowalczyk
 */
public final class ContractOptionFormFieldHelper {

    private ContractOptionFormFieldHelper(){}
    
    /**
     * Create a fresh map of contractOptionFormField from an contractOptionFormFieldBuilder
     * map
     * 
     * @param contractOptionFormFieldBuilderMap
     * @return 
     */
    public static Map<String, List<ContractOptionFormField>> getFreshContractOptionFormFieldMap (
            Map<String, List<ContractOptionFormFieldBuilder>> contractOptionFormFieldBuilderMap) {

        // Copy the audit setup form field map from the builders
        Map<String, List<ContractOptionFormField>> initialisedContractOptionFormFielMap = 
                new LinkedHashMap<String, List<ContractOptionFormField>>();
        for (Map.Entry<String, List<ContractOptionFormFieldBuilder>> entry : contractOptionFormFieldBuilderMap.entrySet()) {
            initialisedContractOptionFormFielMap.put(
                    entry.getKey(), 
                    getFreshContractOptionFormFieldList(entry.getValue()));
        }
        return initialisedContractOptionFormFielMap;
    }
    
    /**
     * Create a fresh list of auditSetUpFormField from a auditSetUpFormFieldBuilder
     * list
     * 
     * @param contractOptionFormFieldBuilderList
     * @return 
     */
    public static List<ContractOptionFormField> getFreshContractOptionFormFieldList(
            List<ContractOptionFormFieldBuilder> contractOptionFormFieldBuilderList) {
        List<ContractOptionFormField> setUpFormFieldList = new LinkedList<ContractOptionFormField>();
        for (Iterator<ContractOptionFormFieldBuilder> it = contractOptionFormFieldBuilderList.iterator(); it.hasNext();) {
            ContractOptionFormFieldBuilder seb = it.next();
            setUpFormFieldList.add(seb.build());
        }
        return setUpFormFieldList;
    }
    
}