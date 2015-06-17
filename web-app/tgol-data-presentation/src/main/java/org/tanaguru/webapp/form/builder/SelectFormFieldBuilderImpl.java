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
package org.tanaguru.webapp.form.builder;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.tanaguru.webapp.form.SelectElement;
import org.tanaguru.webapp.form.SelectFormField;
import org.tanaguru.webapp.form.SelectFormFieldImpl;

/**
 *
 * @author jkowalczyk
 */
public class SelectFormFieldBuilderImpl extends FormFieldBuilderImpl
        implements AbstractGenericFormFieldBuilder<SelectFormField>, SelectFormFieldBuilder {

    public SelectFormFieldBuilderImpl() {
        super();
    }

    @Override
    public SelectFormField build() {
        SelectFormField formField = new SelectFormFieldImpl();
        formField.setErrorI18nKey(getErrorI18nKey());
        formField.setI18nKey(getI18nKey());
        formField.setValue(getValue());
        formField.setRestrictionCode(restrictionCode);
        formField.setActivationCode(activationCode);
        formField.setSelectElementMap(getSelectElementMap());
        return formField;
    }
    
    private Map<String, List<SelectElementBuilder>> selectElementBuilderMap;
    @Override
    public void setSelectElementBuilderMap(Map<String, List<SelectElementBuilder>> selectElementBuilderMap) {
        this.selectElementBuilderMap = selectElementBuilderMap;
    }
    
    @Override
    public void addSelectElementBuilder(String refKey, List<SelectElementBuilder> selectElementBuilderList) {
        if (selectElementBuilderMap != null) {
            this.selectElementBuilderMap.put(refKey, selectElementBuilderList);
        }
    }
    
    @Override
    public Map<String, List<SelectElement>> getSelectElementMap() {
        Map<String, List<SelectElement>> selectElementMap = new TreeMap<>();
        for (Map.Entry<String, List<SelectElementBuilder>> entry : selectElementBuilderMap.entrySet()) {
            List<SelectElement> selectElementList = new LinkedList<>();
            for (SelectElementBuilder seb : entry.getValue()) {
                selectElementList.add((SelectElement)seb.build());
            }
            selectElementMap.put(entry.getKey(), selectElementList);
        }
        return selectElementMap;
    }

    private String restrictionCode = null;
    @Override
    public String getRestrictionCode() {
        return restrictionCode;
    }

    @Override
    public void setRestrictionCode(String restrictionCode) {
        this.restrictionCode = restrictionCode;
    }

    private String activationCode = null;
    @Override
    public String getActivationCode() {
        return activationCode;
    }

    @Override
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

}