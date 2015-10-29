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

import java.util.regex.Pattern;
import org.apache.commons.validator.routines.UrlValidator;
import org.tanaguru.webapp.command.CreateUserCommand;
import org.tanaguru.webapp.command.UserSignUpCommand;
import org.tanaguru.webapp.entity.service.user.UserDataService;
import org.tanaguru.webapp.util.TgolPasswordChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author jkowalczyk
 */
public class SignUpFormValidator implements Validator {
    
    private static final String GENERAL_ERROR_MSG_KEY = "generalErrorMsg";
    private static final String SITE_URL_KEY = "siteUrl";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";

    private static final String MANDATORY_FIELD_MSG_BUNDLE_KEY =
            "sign-up.mandatoryField";
    private static final String EXISTING_ACCOUNT_WITH_EMAIL_KEY =
            "sign-up.existingAccountWithEmail";
    private static final String PASSWORD_NOT_IDENTICAL_KEY =
            "sign-up.passwordNotIdentical";
    private static final String MISSING_URL_KEY =
            "sign-up.missingUrl";
    private static final String MISSING_EMAIL_KEY =
            "sign-up.missingEmail";
    private static final String MISSING_PASSWORD_KEY =
            "sign-up.missingPassword";
    private static final String INVALID_EMAIL_KEY =
            "sign-up.invalidEmail";
    private static final String INVALID_URL_KEY =
            "sign-up.invalidUrl";
    private static final String INVALID_PASSWORD_KEY =
            "sign-up.invalidPassword";
    
    // from http://www.regular-expressions.info/email.html
    private static final String EMAIL_CHECKER_REGEXP =
            "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    private final Pattern emailCheckerPattern = Pattern.compile(EMAIL_CHECKER_REGEXP);

    private UserDataService userDataService;
    public UserDataService getContractDataService() {
        return userDataService;
    }

    private boolean checkSiteUrl = true;
    public void setCheckSiteUrl(boolean checkSiteUrl) {
        this.checkSiteUrl = checkSiteUrl;
    }

    @Autowired
    public void setUserDataService(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Override
    public void validate(Object target, Errors errors) {
        boolean hasMandatoryElementWrong = false;
        CreateUserCommand userSubscriptionCommand = (CreateUserCommand)target;

        if (!checkSiteUrl(userSubscriptionCommand, errors)) {
            hasMandatoryElementWrong = true;
        }

        if (!checkEmail(userSubscriptionCommand, errors)) {
            hasMandatoryElementWrong = true;
        }

        if (!checkPassword(userSubscriptionCommand, errors)) {
            hasMandatoryElementWrong = true;
        }

//        if (userSubscriptionCommand.getPhoneNumber() != null &&
//                !phoneCheckerPattern.matcher(userSubscriptionCommand.getPhoneNumber()).matches()) {
//            hasMandatoryElementWrong = true;
//            errors.rejectValue(PHONE_NUMBER_KEY, INVALID_PHONE_KEY);
//        }
        if(hasMandatoryElementWrong) { // if no URL is filled-in
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    MANDATORY_FIELD_MSG_BUNDLE_KEY);
        }
    }

    public void validateUpdate(Object target, Errors errors, String currentUserEmail) {
        CreateUserCommand userSubscriptionCommand = (CreateUserCommand)target;
        if (!currentUserEmail.equalsIgnoreCase(userSubscriptionCommand.getEmail())) {
            checkEmail(userSubscriptionCommand, errors);
        }
    }

    /**
     *
     * @param userSubscriptionCommand
     * @param errors
     * @return
     */
    private boolean checkSiteUrl(CreateUserCommand userSubscriptionCommand, Errors errors) {
        if (!checkSiteUrl) {
            return true;
        }
        if (userSubscriptionCommand.getSiteUrl() == null ||
                userSubscriptionCommand.getSiteUrl().trim().isEmpty()) {
            errors.rejectValue(SITE_URL_KEY, MISSING_URL_KEY);
            return false;
        } else {
            String url = userSubscriptionCommand.getSiteUrl().trim();
            String[] schemes = {"http","https"};
            UrlValidator urlValidator = new UrlValidator (schemes, UrlValidator.ALLOW_2_SLASHES);
            if (!urlValidator.isValid(url)) {
                errors.rejectValue(SITE_URL_KEY, INVALID_URL_KEY);
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @param userSubscriptionCommand
     * @param errors
     * @return
     */
    private boolean checkEmail(
            CreateUserCommand userSubscriptionCommand,
            Errors errors) {
        if (userSubscriptionCommand.getEmail() == null ||
                userSubscriptionCommand.getEmail().trim().isEmpty()) {
            errors.rejectValue(EMAIL_KEY, MISSING_EMAIL_KEY);
            return false;
        } else {
            String email = userSubscriptionCommand.getEmail();
            if (userDataService.getUserFromEmail(userSubscriptionCommand.getEmail()) != null) {
                errors.rejectValue(EMAIL_KEY, EXISTING_ACCOUNT_WITH_EMAIL_KEY);
                return false;
            } else if (!emailCheckerPattern.matcher(email).matches()) {
                errors.rejectValue(EMAIL_KEY, INVALID_EMAIL_KEY);
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @param userSubscriptionCommand
     * @param errors
     * @return
     */
    private boolean checkPassword(
            CreateUserCommand userSubscriptionCommand,
            Errors errors) {
        String password = userSubscriptionCommand.getPassword();
        if (password == null ||
                password.trim().isEmpty()) {
            errors.rejectValue(PASSWORD_KEY, MISSING_PASSWORD_KEY);
            return false;
        } else if (!password.equals(userSubscriptionCommand.getConfirmPassword())) {
            errors.rejectValue(PASSWORD_KEY, PASSWORD_NOT_IDENTICAL_KEY);
            return false;
        } else if (!TgolPasswordChecker.getInstance().checkPasswordValidity(password)) {
            errors.rejectValue(PASSWORD_KEY, INVALID_PASSWORD_KEY);
            return false;
        }
        return true;
    }

    @Override
    public boolean supports(Class clazz) {
        return UserSignUpCommand.class.isAssignableFrom(clazz);
    }

}