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

import org.apache.commons.lang3.StringUtils;
import org.tanaguru.webapp.command.ForgottenPasswordCommand;
import org.tanaguru.webapp.entity.service.user.UserDataService;
import org.tanaguru.webapp.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author jkowalczyk
 */
public class ForgottenPasswordFormValidator implements Validator {
    
    private static final String GENERAL_ERROR_MSG_KEY = "generalErrorMsg";
    private static final String EXISTING_ACCOUNT_WITH_EMAIL_KEY =
            "forgotten-password.notExistingAccountWithEmail";
    private static final String MISSING_EMAIL_KEY =
            "sign-up.missingEmail";
    private static final String NOT_ACTIVATED_ACCOUNT_KEY =
            "forgotten-password.notActivatedAccount";

    private UserDataService userDataService;
    public UserDataService getContractDataService() {
        return userDataService;
    }

    @Autowired
    public void setUserDataService(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ForgottenPasswordCommand forgottenPasswordCommand = (ForgottenPasswordCommand)target;
        checkEmailExist(forgottenPasswordCommand, errors);
    }

    /**
     * 
     * @param userSubscriptionCommand
     * @param errors
     * @return
     */
    private boolean checkEmailExist(
            ForgottenPasswordCommand forgottenPasswordCommand,
            Errors errors) {
        if (StringUtils.isBlank(forgottenPasswordCommand.getEmail())) {
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    MISSING_EMAIL_KEY);
            return false;
        } else {
            User user = userDataService.getUserFromEmail(forgottenPasswordCommand.getEmail());
            if (user == null) {
                errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    EXISTING_ACCOUNT_WITH_EMAIL_KEY);
                return false;
            } else if (!user.isAccountActivated()) {
                errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    NOT_ACTIVATED_ACCOUNT_KEY);
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean supports(Class clazz) {
        return ForgottenPasswordFormValidator.class.isAssignableFrom(clazz);
    }

}