/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.webapp.ui.form;

/**
 *
 * @author jkowalczyk
 */
public class NumericalFormField extends FormField {

    private int minValue;
    public String getMinValue() {
        return String.valueOf(this.minValue);
    }
    public void setMinValue(String minValue) {
        this.minValue=Integer.parseInt(minValue);
    }

    private int maxValue;
    public String getMaxValue() {
        return String.valueOf(this.maxValue);
    }
    public void setMaxValue(String maxValue) {
        this.maxValue=Integer.parseInt(maxValue);
    }

    @Override
    public boolean checkParameters(String value) {
        int intValue = Integer.parseInt(value);
        return (maxValue==0 || (intValue>=minValue && intValue<=maxValue));
    }

}
