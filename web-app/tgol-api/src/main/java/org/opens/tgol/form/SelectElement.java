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
package org.opens.tgol.form;

/**
 *
 * @author jkowalczyk
 */
public interface SelectElement extends FormField {

    /**
     *
     * @param value
     */
    void setValue(String value);

    /**
     * 
     * @return
     *      the value of the select element
     */
    String getValue();

    /**
     *
     * @param isEnabled
     */
    void setEnabled(boolean isEnabled);

    /**
     * 
     * @return
     *      whether the element is enabled or not
     */
    boolean getEnabled();

    /**
     * 
     * @param isDefault
     */
    void setDefault(boolean isDefault);

    /**
     *
     * @return
     *      whether the element is the default value
     */
    boolean getDefault();
}