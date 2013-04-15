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
package org.opens.tgol.form.parameterization.helper;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.opens.tgol.form.FormField;
import org.opens.tgol.form.SelectElement;
import org.opens.tgol.form.SelectFormField;
import org.opens.tgol.form.builder.FormFieldBuilder;

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
            Map<String, String> formValueMap) {
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
            } else {
                ff.setValue(formValueMap.get(ff.getI18nKey()));
            }
        }
    }
    
        /**
     *
     * @param referential
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
        Map<String, String> formValueMap) {
        List<FormField> initialisedFormFieldList = new LinkedList<FormField>();
        for (FormFieldBuilder formFieldBuilder : formFieldBuilderList) {
            initialisedFormFieldList.add(formFieldBuilder.build());
        }
        FormFieldHelper.setValueToFormField(initialisedFormFieldList, formValueMap);
        return initialisedFormFieldList;
    }
    
}