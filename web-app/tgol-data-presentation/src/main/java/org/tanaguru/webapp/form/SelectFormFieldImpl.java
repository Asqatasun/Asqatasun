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
package org.tanaguru.webapp.form;

import java.util.List;
import java.util.Map;

/**
 *
 * @author jkowalczyk
 */
public class SelectFormFieldImpl extends FormFieldImpl implements SelectFormField {

    public SelectFormFieldImpl() {
        super();
    }

    @Override
    public boolean checkParameters(String value) {
        return true;
    }

    private Map<String, List<SelectElement>> selectElementMap;
    @Override
    public void setSelectElementMap(Map<String,List<SelectElement>> selectElementList) {
        this.selectElementMap = selectElementList;
    }

    @Override
    public Map<String, List<SelectElement>> getSelectElementMap() {
        return this.selectElementMap;
    }

    private String restrictionCode;
    @Override
    public String getRestrictionCode() {
        return restrictionCode;
    }

    @Override
    public void setRestrictionCode(String restrictionCode) {
        this.restrictionCode = restrictionCode;
    }

    private String activationCode;
    @Override
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    @Override
    public String getActivationCode() {
        return activationCode;
    }

}