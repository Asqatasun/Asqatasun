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

import org.tanaguru.webapp.form.SelectElement;
import org.tanaguru.webapp.form.SelectElementImpl;

/**
 * 
 * @author jkowalczyk
 */
public class SelectElementBuilderImpl extends FormFieldBuilderImpl
        implements AbstractGenericFormFieldBuilder<SelectElement>, SelectElementBuilder {

    public SelectElementBuilderImpl() {
        super();
    }

    @Override
    public SelectElement build() {
        SelectElement formField = new SelectElementImpl();
        formField.setErrorI18nKey(getErrorI18nKey());
        formField.setI18nKey(getI18nKey());
        formField.setValue(getValue());
        formField.setDefault(isDefaultElement);
        formField.setDefaultCode(defaultCode);
        formField.setEnabled(isEnabled);
        return formField;
    }

    private boolean isEnabled = false;
    @Override
    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public boolean getEnabled() {
        return this.isEnabled;
    }

    private boolean isDefaultElement = false;
    @Override
    public void setDefault(boolean isDefaultElement) {
        this.isDefaultElement = isDefaultElement;
    }

    @Override
    public boolean getDefaultElement() {
        return this.isDefaultElement;
    }

    String defaultCode = null;
    @Override
    public void setDefaultCode(String defaultCode) {
        this.defaultCode = defaultCode;
    }

    @Override
    public String getDefaultCode() {
        return defaultCode;
    }

}