/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.tanaguru.webapp.form.builder;

/**
 *
 * @author jkowalczyk
 */
public interface SelectElementBuilder extends FormFieldBuilder {

    /**
     * Sets whether each instance SelectElement is enabled
     * @param isEnabled
     */
    void setEnabled(boolean isEnabled);

    /**
     * 
     * @return
     */
    boolean getEnabled();
    
    /**
     * Sets the option code that enables to set the element as default
     * @param defaultCode
     */
    void setDefaultCode(String defaultCode);

    /**
     * 
     * @return
     */
    String getDefaultCode();
    
    /**
     * Sets whether each instance of SelectElement is the default among all the
     * selectElement of the SelectFormField
     * @param isDefault
     */
    void setDefault(boolean isDefault);

    /**
     * 
     * @return
     */
    boolean getDefaultElement();

}