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

/**
 *
 * @author jkowalczyk
 */
public class NumericalFormFieldImpl extends FormFieldImpl implements NumericalFormField {

    private int minValue;
    @Override
    public String getMinValue() {
        return String.valueOf(this.minValue);
    }

    @Override
    public void setMinValue(String minValue) {
        this.minValue=Integer.valueOf(minValue);
    }

    private int maxValue;
    @Override
    public String getMaxValue() {
        return String.valueOf(this.maxValue);
    }

    @Override
    public void setMaxValue(String maxValue) {
        this.maxValue=Integer.valueOf(maxValue);
    }

    @Override
    public boolean checkParameters(String value) {
        int intValue = Integer.valueOf(value);
        return (maxValue==0 || (intValue>=minValue && intValue<=maxValue));
    }

}