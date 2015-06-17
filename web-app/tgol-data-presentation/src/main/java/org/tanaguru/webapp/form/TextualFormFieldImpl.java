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

import java.util.regex.Pattern;

/**
 *
 * @author jkowalczyk
 */
public class TextualFormFieldImpl extends FormFieldImpl implements TextualFormField {

    public TextualFormFieldImpl() {
        super();
    }

    /**
     * checker pattern
     */
    private Pattern checkerPattern = null;

    private String validationRegexp;
    @Override
    public void setValidationRegexp(String validationRegexp) {
        checkerPattern = Pattern.compile(validationRegexp);
        this.validationRegexp = validationRegexp;
    }

    @Override
    public String getValidationRegexp() {
        return validationRegexp;
    }

    @Override
    public boolean checkParameters(String value) {
        return (checkerPattern == null || checkerPattern.matcher(value).matches());
    }

}