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
package org.opens.tgol.controller;

import org.opens.tgol.command.ChangePasswordCommand;
import org.opens.tgol.command.ForgottenPasswordCommand;
import org.opens.tgol.emailsender.EmailSender;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.util.webapp.ExposablePropertyPlaceholderConfigurer;
import org.opens.tgol.util.TgolKeyStore;
import org.opens.tgol.util.TgolTokenHelper;
import org.opens.tgol.validator.ChangePasswordFormValidator;
import org.opens.tgol.validator.ForgottenPasswordFormValidator;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.opens.tanaguru.util.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class ForgottenOrChangePasswordController extends AbstractController {

    private static final String EMAIL_FROM_KEY = "forgotten-password.emailFrom";
    private static final String EMAIL_SUBJECT_KEY = "forgotten-password.emailSubject";
    private static final String EMAIL_CONTENT_KEY = "forgotten-password.emailContent";
    private static final String BUNDLE_NAME = "forgotten-password-page-I18N";
    private static final String CHANGE_PASSWORD_URL_KEY = "changePasswordUrl";
    private static final String AUTHENTICATED_KEY = "authenticated";

    private ChangePasswordFormValidator changePasswordFormValidator;
    @Autowired
    public final void setChangePasswordFormValidator(ChangePasswordFormValidator changePasswordFormValidator) {
        this.changePasswordFormValidator = changePasswordFormValidator;
    }

    private ForgottenPasswordFormValidator forgottenPasswordFormValidator;
    @Autowired
    public final void setForgottenPasswordFormValidator(ForgottenPasswordFormValidator forgottenPasswordFormValidator) {
        this.forgottenPasswordFormValidator = forgottenPasswordFormValidator;
    }

    private ExposablePropertyPlaceholderConfigurer exposablePropertyPlaceholderConfigurer;
    @Autowired
    public final void setExposablePropertyPlaceholderConfigurer(ExposablePropertyPlaceholderConfigurer exposablePropertyPlaceholderConfigurer) {
        this.exposablePropertyPlaceholderConfigurer = exposablePropertyPlaceholderConfigurer;
    }

    private EmailSender emailSender;
    @Autowired
    public final void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    private LocaleResolver localeResolver;
    @Autowired
    public final void setLocaleResolver(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    public ForgottenOrChangePasswordController() {
        super();
    }

    /**
     * This method displays the change password page from an authenticated user
     * @param contractId
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.CHANGE_PASSWORD_URL, method = RequestMethod.GET)
    public String setChangePasswordPage(
            @RequestParam(TgolKeyStore.EMAIL_KEY) String email,
            @RequestParam(TgolKeyStore.TOKEN_KEY) String token,
            HttpServletRequest request,
            Model model) {
        if (email == null ||email.isEmpty() || token == null || token.isEmpty()) {
            return TgolKeyStore.ACCESS_DENIED_VIEW_REDIRECT_NAME;
        }
        if (token.equalsIgnoreCase(AUTHENTICATED_KEY)) {
            if (getCurrentUser()==null || !getCurrentUser().getEmail1().equalsIgnoreCase(email)) {
                return TgolKeyStore.ACCESS_DENIED_VIEW_REDIRECT_NAME;
            } else {
                model.addAttribute(TgolKeyStore.AUTHENTICATED_USER_KEY, getCurrentUser());
            }
        } else {
            User user = getUserDataService().getUserFromEmail(email);
            try {
                if (!TgolTokenHelper.getInstance().checkUserToken(user, token)) {
                    model.addAttribute(TgolKeyStore.INVALID_CHANGE_PASSWORD_URL_KEY, true);
                }
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                model.addAttribute(TgolKeyStore.INVALID_CHANGE_PASSWORD_URL_KEY, true);
            }
        }
        ChangePasswordCommand cpc = new ChangePasswordCommand();
        cpc.setUserEmail(email);
        model.addAttribute(TgolKeyStore.CHANGE_PASSWORD_COMMAND_KEY, cpc);
        return TgolKeyStore.CHANGE_PASSWORD_VIEW_NAME;
    }

    /**
     * This method displays the change password page from an authenticated user
     * @param contractId
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.FORGOTTEN_PASSWORD_URL, method = RequestMethod.GET)
    public String setForgottenPasswordPage(
            Model model) {
        if (getCurrentUser() != null) {
            return TgolKeyStore.ACCESS_DENIED_VIEW_REDIRECT_NAME;
        }
        model.addAttribute(TgolKeyStore.FORGOTTEN_PASSWORD_COMMAND_KEY,
                new ForgottenPasswordCommand());
        return TgolKeyStore.FORGOTTEN_PASSWORD_VIEW_NAME;
    }

    /**
     * This methods controls the validity of the form and launch an audit with
     * values populated by the user. In case of audit failure, an appropriate
     * message is displayed
     * @param launchAuditFromContractCommand
     * @param result
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = TgolKeyStore.FORGOTTEN_PASSWORD_URL, method = RequestMethod.POST)
    protected String submitForm(
            @ModelAttribute(TgolKeyStore.FORGOTTEN_PASSWORD_COMMAND_KEY) ForgottenPasswordCommand forgottenPasswordCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request)
            throws Exception {
        // We check whether the form is valid
        forgottenPasswordFormValidator.validate(forgottenPasswordCommand, result);
        // If the form has some errors, we display it again with errors' details
        if (result.hasErrors()) {
            return displayFormWithErrors(
                    model,
                    forgottenPasswordCommand);
        }
        Locale locale = localeResolver.resolveLocale(request);
        sendResetEmail(getUserDataService().getUserFromEmail(forgottenPasswordCommand.getEmail()), locale);
        request.getSession().setAttribute(TgolKeyStore.URL_KEY, forgottenPasswordCommand.getEmail());
        return TgolKeyStore.FORGOTTEN_PASSWORD_CONFIRMATION_VIEW_REDIRECT_NAME;
    }

    /**
     * 
     * @param changePasswordCommand
     * @param result
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = TgolKeyStore.CHANGE_PASSWORD_URL, method = RequestMethod.POST)
    protected String changePassword(
            @ModelAttribute(TgolKeyStore.CHANGE_PASSWORD_COMMAND_KEY) ChangePasswordCommand changePasswordCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request)
            throws Exception {
        // We check whether the form is valid
        changePasswordFormValidator.validate(changePasswordCommand, result);
        // If the form has some errors, we display it again with errors' details
        if (result.hasErrors()) {
            return displayChangePasswordFormWithErrors(
                    model,
                    changePasswordCommand);
        }
        model.addAttribute(TgolKeyStore.PASSWORD_MODIFIED_KEY, true);
        updateUserPassword(changePasswordCommand);
        if (getCurrentUser() != null) {
            model.addAttribute(TgolKeyStore.AUTHENTICATED_USER_KEY, getCurrentUser());
        }
        return TgolKeyStore.CHANGE_PASSWORD_VIEW_NAME;
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.FORGOTTEN_PASSWORD_CONFIRMATION_URL, method = RequestMethod.GET)
    public String setUpSignUpConfirmationPage(
            Model model,
            HttpServletRequest request) {
        model.addAttribute(TgolKeyStore.URL_KEY, request.getSession().getAttribute(TgolKeyStore.URL_KEY));
        request.getSession().removeAttribute(TgolKeyStore.URL_KEY);
        return TgolKeyStore.FORGOTTEN_PASSWORD_CONFIRMATION_VIEW_NAME;
    }

    /**
     * 
     * @param model
     * @param contract
     * @param launchAuditFromContractCommand
     * @return
     */
    private String displayFormWithErrors(
            Model model,
            ForgottenPasswordCommand forgottenPasswordCommand) {
        model.addAttribute(TgolKeyStore.FORGOTTEN_PASSWORD_COMMAND_KEY,
                forgottenPasswordCommand);
        return TgolKeyStore.FORGOTTEN_PASSWORD_VIEW_NAME;
    }
    
    /**
     *
     * @param model
     * @param contract
     * @param launchAuditFromContractCommand
     * @return
     */
    private String displayChangePasswordFormWithErrors(
            Model model,
            ChangePasswordCommand changePasswordCommand) {
        if (changePasswordCommand.getNewPassword() != null &&
            getCurrentUser() != null) {
            model.addAttribute(TgolKeyStore.AUTHENTICATED_USER_KEY, getCurrentUser());
        }
        model.addAttribute(TgolKeyStore.CHANGE_PASSWORD_COMMAND_KEY,
                changePasswordCommand);
        return TgolKeyStore.CHANGE_PASSWORD_VIEW_NAME;
    }

    /**
     * This method gets data from a property file to fill-in the inscription
     * e-mail
     * @param user
     */
    private void sendResetEmail(User user, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        String emailFrom = resourceBundle.getString(EMAIL_FROM_KEY);
        Set<String> emailToSet = new HashSet<String>();
        emailToSet.add(user.getEmail1());
        String emailSubject = resourceBundle.getString(EMAIL_SUBJECT_KEY);
        String emailContentPattern = resourceBundle.getString(EMAIL_CONTENT_KEY);
        String emailContent = MessageFormat.format(emailContentPattern,
                user.getEmail1(),
                computeReturnedUrl(user));
        emailSender.sendEmail(emailFrom, emailToSet, emailSubject, emailContent);
    }

    /**
     * 
     * @param user
     * @return
     */
    private String computeReturnedUrl(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append(exposablePropertyPlaceholderConfigurer.getResolvedProps().get(CHANGE_PASSWORD_URL_KEY));
        sb.append("?email=");
        sb.append(user.getEmail1());
        sb.append("&token=");
        try {
            sb.append(URLEncoder.encode(TgolTokenHelper.getInstance().getTokenUser(user), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
        }
        return sb.toString();
    }

    /**
     *
     * @param userSignUpCommand
     * @return
     * @throws Exception
     */
    private User updateUserPassword(ChangePasswordCommand changePasswordCommand) throws Exception {
        User user = getUserDataService().getUserFromEmail(changePasswordCommand.getUserEmail());
        Logger.getLogger(this.getClass()).info(changePasswordCommand.getUserEmail());
        Logger.getLogger(this.getClass()).info(user);
        Logger.getLogger(this.getClass()).info(changePasswordCommand.getNewPassword());
        user.setPassword(MD5Encoder.MD5(changePasswordCommand.getNewPassword()));
        getUserDataService().saveOrUpdate(user);
        return user;
    }

}