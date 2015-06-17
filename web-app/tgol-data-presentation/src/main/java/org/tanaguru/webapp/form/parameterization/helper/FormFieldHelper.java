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
import org.apache.commons.collections.CollectionUtils;
import org.tanaguru.webapp.form.*;
import org.tanaguru.webapp.form.builder.FormFieldBuilder;

/**
 * 
 * 
 * @author jkowalczyk
 */
public final class FormFieldHelper {

    private FormFieldHelper() {
    }

    /**
     * 
     * @param formFieldList
     * @param formValueMap 
     */
    public static void setValueToFormField(
            List<FormField> formFieldList,
            Map<String, Object> formValueMap) {
        for (FormField ff : formFieldList) {
            if (ff instanceof SelectFormField) {
                for (Map.Entry<String, List<SelectElement>> entry : ((SelectFormField)ff).getSelectElementMap().entrySet()) {
                    for (SelectElement se : entry.getValue()) {
                        se.setDefault(false);
                        if (formValueMap.get(entry.getKey()).equals(se.getValue())) {
                            se.setDefault(true);
                        }
                    }
                }
            } else if (ff instanceof CheckboxFormField) {
                // retrieve the user selection and select the UI elements
                CheckboxFormField cff = ((CheckboxFormField)ff);
                Collection<String> selectedValues = new HashSet<String>();
                if ((formValueMap.get(cff.getCode()) instanceof String[])) {
                    CollectionUtils.addAll(selectedValues, ((String[])formValueMap.get(cff.getCode())));
                } else if ((formValueMap.get(cff.getCode()) instanceof String)) {
                    selectedValues.add(((String)formValueMap.get(cff.getCode())));
                }
                for (CheckboxElement  ce : cff.getCheckboxElementList()) {
                    if(selectedValues.add(ce.getValue())) {
                        ce.setSelected(true);
                    } else {
                        ce.setSelected(false);
                    }
                }
            } else {
                ff.setValue(formValueMap.get(ff.getI18nKey()).toString());
            }
        }
    }
    
    /**
     *
     * @param formFieldBuilderList
     * @return
     */
    public static List<FormField> getFormFieldBuilderCopy(
        List<FormFieldBuilder> formFieldBuilderList) {
        List<FormField> initialisedFormFielList = new LinkedList<FormField>();
        for (FormFieldBuilder formFieldBuilder : formFieldBuilderList) {
            initialisedFormFielList.add(formFieldBuilder.build());
        }
        return initialisedFormFielList;
    }
    
    /**
     * 
     * @param formFieldBuilderList
     * @param formValueMap
     * @return
     */
    public static List<FormField> getFormFieldBuilderCopy(
        List<FormFieldBuilder> formFieldBuilderList,
        Map<String, Object> formValueMap) {
        List<FormField> initialisedFormFieldList = new LinkedList<FormField>();
        for (FormFieldBuilder formFieldBuilder : formFieldBuilderList) {
            initialisedFormFieldList.add(formFieldBuilder.build());
        }
        FormFieldHelper.setValueToFormField(initialisedFormFieldList, formValueMap);
        return initialisedFormFieldList;
    }
    
}