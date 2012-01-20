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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.opens.tgol.command.AuditResultSortCommand;
import org.opens.tgol.form.FormField;
import org.opens.tgol.form.SelectElement;
import org.opens.tgol.form.SelectFormField;
import org.opens.tgol.form.builder.FormFieldBuilder;

/**
 *
 * @author jkowalczyk
 */
public final class AuditResultSortCommandFactory {

    private String themeKey;
    public String getThemeKey() {
        return themeKey;
    }

    public void setThemeKey(String themeKey) {
        this.themeKey = themeKey;
    }

    private static AuditResultSortCommandFactory auditResultSortCommandFactory;

    /**
     * Factory has default constructor
     */
    private AuditResultSortCommandFactory(){}

    public static synchronized AuditResultSortCommandFactory getInstance() {
        if (auditResultSortCommandFactory == null) {
            auditResultSortCommandFactory = new AuditResultSortCommandFactory();
        }
        return auditResultSortCommandFactory;
    }

    
    /**
     * @param webResourceId
     * @param referenceCode
     * @return
     */
    public AuditResultSortCommand getInitialisedAuditResultSortCommand (
            Long webResourceId,
            List<FormField> formFieldList) {
        AuditResultSortCommand auditResultSortCommand = new AuditResultSortCommand();
        for (FormField ff :formFieldList) {
            if (ff instanceof SelectFormField) {
                for (Map.Entry<String, List<SelectElement>> entry : ((SelectFormField)ff).getSelectElementMap().entrySet()) {
                    for (SelectElement se : entry.getValue()) {
                        if (se.getDefault() && se.getEnabled()) {
                            auditResultSortCommand.getSortOptionMap().put(entry.getKey(), se.getValue());
                        }
                    }
                }
            }
        }
        auditResultSortCommand.setWebResourceId(webResourceId);
        return auditResultSortCommand;
    }

    public AuditResultSortCommand getInitialisedAuditResultSortCommand (
            Long webResourceId,
            String referenceCode,
            AuditResultSortCommand auditResultSortCommand) {
        return auditResultSortCommand;
    }

    /**
     *
     * @param referential
     * @param formFieldBuilderList
     * @return
     */
    public List<FormField> getFormFieldBuilderCopy(
        String referential,
        List<FormFieldBuilder> formFieldBuilderList) {
        // for each result page, only one referential and so only one theme list
        // is possible. We need to retrieve the appropriate theme list regarding
        // the referential
        // Copy the audit setup form field map from the builders
        List<FormField> initialisedFormFielList = new LinkedList<FormField>();
        boolean hasTheme = false;
        for (FormFieldBuilder formFieldBuilder : formFieldBuilderList) {
            if (!formFieldBuilder.getI18nKey().equals(themeKey)) {
                initialisedFormFielList.add(formFieldBuilder.build());
            } else if (!hasTheme && referential.equals(formFieldBuilder.getValue())){
                initialisedFormFielList.add(formFieldBuilder.build());
                hasTheme = true;
            }
        }
        return initialisedFormFielList;
    }

    /**
     * 
     * @param formFieldBuilderList
     * @param auditResultSortCommand
     * @return
     */
    public List<FormField> getFormFieldBuilderCopy(
        String referential,
        List<FormFieldBuilder> formFieldBuilderList,
        AuditResultSortCommand auditResultSortCommand) {
        // for each result page, only one referential and so only one theme list
        // is possible. We need to retrieve the appropriate theme list regarding
        // the referential
        // Copy the audit setup form field map from the builders
        List<FormField> initialisedFormFieldList = new LinkedList<FormField>();
        boolean hasTheme = false;
        for (FormFieldBuilder formFieldBuilder : formFieldBuilderList) {
            if (!formFieldBuilder.getI18nKey().equals(themeKey)) {
                initialisedFormFieldList.add(formFieldBuilder.build());
            } else if (!hasTheme && referential.equals(formFieldBuilder.getValue())){
                initialisedFormFieldList.add(formFieldBuilder.build());
                hasTheme = true;
            }
        }
        setValueToFormField(initialisedFormFieldList, auditResultSortCommand);
        return initialisedFormFieldList;
    }

    private void setValueToFormField(List<FormField> formFieldList,
            AuditResultSortCommand auditResultSortCommand) {
        for (FormField ff : formFieldList) {
            if (ff instanceof SelectFormField) {
                for (Map.Entry<String, List<SelectElement>> entry : ((SelectFormField)ff).getSelectElementMap().entrySet()) {
                    for (SelectElement se : entry.getValue()) {
                        se.setDefault(false);
                        if (auditResultSortCommand.getSortOptionMap().get(entry.getKey()).equals(se.getValue())) {
                            se.setDefault(true);
                        }
                    }
                }
            } else {
                ff.setValue(auditResultSortCommand.getSortOptionMap().get(ff.getI18nKey()));
            }
        }
    }

}