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

import org.tanaguru.webapp.form.CheckboxElement;
import org.tanaguru.webapp.form.CheckboxElementImpl;

/**
 * 
 * @author jkowalczyk
 */
public class CheckboxElementBuilderImpl extends FormFieldBuilderImpl
        implements AbstractGenericFormFieldBuilder<CheckboxElement>, CheckboxElementBuilder {

    public CheckboxElementBuilderImpl() {
        super();
    }

    @Override
    public CheckboxElement build() {
        CheckboxElement formField = new CheckboxElementImpl();
        formField.setErrorI18nKey(getErrorI18nKey());
        formField.setI18nKey(getI18nKey());
        formField.setValue(getValue());
        formField.setEnabled(isEnabled);
        formField.setSelected(isSelected);
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

    private boolean isDefault = false;
    @Override
    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public boolean getDefaultElement() {
        return this.isDefault;
    }

    private boolean isSelected = false;
    @Override
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public boolean getSelected() {
        return this.isSelected;
    }

}