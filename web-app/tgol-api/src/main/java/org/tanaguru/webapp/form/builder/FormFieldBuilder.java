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

import org.tanaguru.webapp.form.FormField;

/**
 *
 * @author jkowalczyk
 */
public interface FormFieldBuilder {

    /**
     * 
     * @return
     *      a set instance of FormField
     */
    FormField build();

    /**
     *
     * @return
     *      the I18n key that has to be set to each instance of FormField
     */
    String getI18nKey();

    /**
     * Sets the I18n key that has to be set to each instance of FormField
     * @param i18nKey
     */
    void setI18nKey(String i18nKey);

    /**
     *
     * @return
     *      the value that has to be set to each instance of FormField
     */
    String getValue();

    /**
     * Sets the value that has to be set to each instance of FormField
     * @param value
     */
    void setValue(String value);

    /**
     * 
     * @return
     *      the I18n error message key that has to be set to each instance of FormField
     */
    String getErrorI18nKey();

    /**
     * Sets the I18n error message key that has to be set to each instance of FormField
     * @param errorI18nKey
     */
    void setErrorI18nKey(String errorI18nKey);

}