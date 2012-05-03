/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2012  Open-S Company
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
package org.opens.tgol.mock;

import org.apache.commons.lang.StringUtils;
import org.opens.tgol.command.UserSignUpCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author jkowalczyk
 */
public class MockFormValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void validate(Object object, Errors errors) {
        // mock the UserSignUpCommand validation
        if (object instanceof UserSignUpCommand) {
            UserSignUpCommand userSignUpCommand = (UserSignUpCommand)object;
            // if the userSignUpCommand is instanciated with an email attribute
            // set as "valid", the validation is seen as valid
            if (StringUtils.isEmpty(userSignUpCommand.getEmail())) {
                errors.reject("invalid form");
            }
        }
    }

}