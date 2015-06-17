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
package org.tanaguru.webapp.validator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.tanaguru.util.MD5Encoder;
import org.tanaguru.webapp.command.ChangePasswordCommand;
import org.tanaguru.webapp.entity.user.User;
import org.tanaguru.webapp.util.TgolPasswordChecker;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author jkowalczyk
 */
public class ChangePasswordFormValidator implements Validator {

    private static final String MISSING_PASSWORD_KEY =
            "change-password.missingPassword";
    private static final String PASSWORD_NOT_IDENTICAL_KEY =
            "change-password.passwordNotIdentical";
    private static final String INVALID_PASSWORD_KEY =
            "change-password.invalidPassword";
    private static final String INCORRECT_PASSWORD_KEY =
            "change-password.incorrectPassword";
    private static final String CURRENT_PASSWORD_KEY = "currentPassword";
    private static final String NEW_PASSWORD_KEY = "newPassword";

    @Override
    public void validate(Object target, Errors errors) {
        ChangePasswordCommand changePasswordCommand = (ChangePasswordCommand) target;
        checkPassword(changePasswordCommand, null, errors);
    }

    public void validate(Object target, Errors errors, User currentUser) {
        ChangePasswordCommand changePasswordCommand = (ChangePasswordCommand) target;
        checkPassword(changePasswordCommand, currentUser, errors);
    }

    /**
     *
     * @param userSubscriptionCommand
     * @param errors
     * @return
     */
    private boolean checkPassword(
            ChangePasswordCommand changePasswordCommand,
            User user,
            Errors errors) {
        String currentPassword = changePasswordCommand.getCurrentPassword();
        String newPassword = changePasswordCommand.getNewPassword();
        if (currentPassword != null) {
            if (StringUtils.isBlank(currentPassword)) {
                errors.rejectValue(CURRENT_PASSWORD_KEY, MISSING_PASSWORD_KEY);
                return false;
            } else {
                try {
                    if (!MD5Encoder.MD5(currentPassword).equalsIgnoreCase(user.getPassword())) {
                        errors.rejectValue(CURRENT_PASSWORD_KEY, INCORRECT_PASSWORD_KEY);
                        return false;
                    }
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                    Logger.getLogger(this.getClass()).warn(ex);
                }
            }
        }
        if (!newPassword.equals(changePasswordCommand.getConfirmNewPassword())) {
            errors.rejectValue(NEW_PASSWORD_KEY, PASSWORD_NOT_IDENTICAL_KEY);
            return false;
        } else if (!TgolPasswordChecker.getInstance().checkPasswordValidity(newPassword)) {
            errors.rejectValue(NEW_PASSWORD_KEY, INVALID_PASSWORD_KEY);
            return false;
        }
        return true;
    }

    @Override
    public boolean supports(Class clazz) {
        return ChangePasswordFormValidator.class.isAssignableFrom(clazz);
    }
}