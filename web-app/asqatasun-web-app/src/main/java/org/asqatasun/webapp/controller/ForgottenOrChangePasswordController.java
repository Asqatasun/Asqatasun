 /*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.webapp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.asqatasun.emailsender.EmailSender;
import org.asqatasun.webapp.security.tokenmanagement.TokenManager;
import org.asqatasun.util.MD5Encoder;
import org.asqatasun.webapp.command.ChangePasswordCommand;
import org.asqatasun.webapp.command.ForgottenPasswordCommand;
import org.asqatasun.entity.user.User;
import org.asqatasun.webapp.exception.ForbiddenPageException;
import org.asqatasun.webapp.exception.ForbiddenUserException;
import org.asqatasun.webapp.ui.form.menu.SecondaryLevelMenuDisplayer;
import org.asqatasun.webapp.util.TgolKeyStore;
import org.asqatasun.webapp.validator.ChangePasswordFormValidator;
import org.asqatasun.webapp.validator.ForgottenPasswordFormValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.access.annotation.Secured;
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

    private static final String USER_ACCOUNT_TO_REPLACE =  "#userAccount";
    private static final String CHANGE_PASSWORD_URL_TO_REPLACE =  "#changePasswordUrl";
    private static final String CHANGE_PASSWORD_URL = "change-password.html";
    @Value("${app.emailSender.smtp.from}")
    private String appEmailFrom;
    @Value("${app.webapp.ui.config.forgottenPassword.excludeUserList}")
    private List<String> forbiddenUserList;
    @Value("${app.webapp.ui.config.webAppUrl}")
    private String webAppUrl;
    private final ChangePasswordFormValidator changePasswordFormValidator;
    private final ForgottenPasswordFormValidator forgottenPasswordFormValidator;
    private final EmailSender emailSender;
    private final LocaleResolver localeResolver;
    private final SecondaryLevelMenuDisplayer secondaryLevelMenuDisplayer;
    private final ReloadableResourceBundleMessageSource messageSource;
    private final TokenManager tokenManager;



    @Autowired
    public ForgottenOrChangePasswordController(
        ChangePasswordFormValidator changePasswordFormValidator,
        ForgottenPasswordFormValidator forgottenPasswordFormValidator,
        EmailSender emailSender, LocaleResolver localeResolver,
        SecondaryLevelMenuDisplayer secondaryLevelMenuDisplayer,
        ReloadableResourceBundleMessageSource messageSource,
        TokenManager tokenManager) {
        super();
        this.changePasswordFormValidator = changePasswordFormValidator;
        this.forgottenPasswordFormValidator = forgottenPasswordFormValidator;
        this.emailSender = emailSender;
        this.localeResolver = localeResolver;
        this.secondaryLevelMenuDisplayer = secondaryLevelMenuDisplayer;
        this.messageSource = messageSource;
        this.tokenManager = tokenManager;
    }

    /**
     * This method displays the change password page from an authenticated user
     * @param userId
     * @param token
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.CHANGE_PASSWORD_URL, method = RequestMethod.GET)
    public String displayChangePasswordFromUserPage(
            @RequestParam(TgolKeyStore.USER_ID_KEY) String userId,
            @RequestParam(TgolKeyStore.TOKEN_KEY) String token,
            HttpServletRequest request,
            Model model) {
        model.addAttribute(TgolKeyStore.CHANGE_PASSWORD_FROM_ADMIN_KEY, false);
        secondaryLevelMenuDisplayer.setModifiableReferentialsForUserToModel(
                        getCurrentUser(), 
                        model); 
        return displayChangePasswordView(userId, token, model, request);
    }

    /**
     * 
     * @param id
     * @param request
     * @param model
     * @return 
     */
    @RequestMapping(value = TgolKeyStore.CHANGE_PASSWORD_FROM_ADMIN_URL, method = RequestMethod.GET)
    @Secured(TgolKeyStore.ROLE_ADMIN_KEY)
    public String displayChangePasswordFromAdminPage(
            @RequestParam(TgolKeyStore.USER_ID_KEY) String id,
            HttpServletRequest request,
            Model model) {
        model.addAttribute(TgolKeyStore.CHANGE_PASSWORD_FROM_ADMIN_KEY, true);
        return displayChangePasswordView(id, TgolKeyStore.AUTHENTICATED_KEY, model, request);
    }
    
    /**
     * 
     * @param id
     * @param token
     * @param model
     * @param request
     * @return 
     */
    private String displayChangePasswordView(
            String id,
            String token, 
            Model model,
            HttpServletRequest request) {
        Long userId;
        try {
            userId = Long.valueOf(id);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenUserException();
        }
        if (StringUtils.isBlank(token)) {
            return TgolKeyStore.ACCESS_DENIED_VIEW_REDIRECT_NAME;
        }
        User currentUser = getCurrentUser();
        User user;
        // if the change password request comes from an authentied user or from
        // an admin
        if (token.equalsIgnoreCase(TgolKeyStore.AUTHENTICATED_KEY)) {
            if (currentUser == null || 
                    (!currentUser.getId().equals(userId) &&
                    !currentUser.getRole().getRoleName().equals(TgolKeyStore.ROLE_ADMIN_NAME_KEY)) ||
                    forbiddenUserList.contains(currentUser.getEmail1())) {
                return TgolKeyStore.ACCESS_DENIED_VIEW_REDIRECT_NAME;
            } else {
                if (!currentUser.getId().equals(userId)){
                    user = userDataService.read(userId);
                } else {
                    user = currentUser;
                }
            }
        // the request is submitted through an unauthentified user and the token
        // has to be checked.
        } else {
            user = userDataService.read(userId);
            try {
                // if the token is invalid
                if (!tokenManager.checkUserToken(user.getEmail1(), token)) {
                    model.addAttribute(TgolKeyStore.INVALID_CHANGE_PASSWORD_URL_KEY, true);
                    return TgolKeyStore.CHANGE_PASSWORD_VIEW_NAME;
                } else {
                    // if the token is valid but the request comes from the 
                    // form submission with success
                    Object passwordModified = model.asMap().get(TgolKeyStore.PASSWORD_MODIFIED_KEY);
                    if (passwordModified instanceof Boolean &&
                            (Boolean)passwordModified) {
                        tokenManager.setTokenUsed(token);
                        return TgolKeyStore.CHANGE_PASSWORD_VIEW_NAME;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                model.addAttribute(TgolKeyStore.INVALID_CHANGE_PASSWORD_URL_KEY, true);
                return TgolKeyStore.CHANGE_PASSWORD_VIEW_NAME;
            }
        }
        if (user == null) {
            return TgolKeyStore.ACCESS_DENIED_VIEW_REDIRECT_NAME;
        }
        ChangePasswordCommand cpc = new ChangePasswordCommand();
        model.addAttribute(TgolKeyStore.CHANGE_PASSWORD_COMMAND_KEY, cpc);
        model.addAttribute(TgolKeyStore.USER_NAME_KEY, user.getEmail1());
        request.getSession().setAttribute(TgolKeyStore.USER_ID_KEY,user.getId());
        return TgolKeyStore.CHANGE_PASSWORD_VIEW_NAME;
    }
    
    /**
     * This method displays the change password page from an authenticated user
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.FORGOTTEN_PASSWORD_URL, method = RequestMethod.GET)
    public String displayForgottenPasswordPage(
            Model model) {
        if (getCurrentUser() != null) {
            return TgolKeyStore.ACCESS_DENIED_VIEW_REDIRECT_NAME;
        }
        model.addAttribute(TgolKeyStore.FORGOTTEN_PASSWORD_COMMAND_KEY,
                new ForgottenPasswordCommand());
        return TgolKeyStore.FORGOTTEN_PASSWORD_VIEW_NAME;
    }

    /**
     * This methods controls the validity of the form and modify the password 
     * of the wished user
     * 
     * @param forgottenPasswordCommand
     * @param result
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = TgolKeyStore.FORGOTTEN_PASSWORD_URL, method = RequestMethod.POST)
    protected String submitForgottenPasswordForm(
            @ModelAttribute(TgolKeyStore.FORGOTTEN_PASSWORD_COMMAND_KEY) ForgottenPasswordCommand forgottenPasswordCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request) throws Exception {
        // We check whether the form is valid
        forgottenPasswordFormValidator.validate(forgottenPasswordCommand, result);
        // If the form has some errors, we display it again with errors' details
        if (result.hasErrors()) {
            return displayFormWithErrors(
                    model,
                    forgottenPasswordCommand);
        }
        Locale locale = localeResolver.resolveLocale(request);
        sendResetEmail(userDataService.getUserFromEmail(forgottenPasswordCommand.getEmail()), locale);
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
    protected String submitChangePasswordFromUser(
            @ModelAttribute(TgolKeyStore.CHANGE_PASSWORD_COMMAND_KEY) ChangePasswordCommand changePasswordCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request)
            throws Exception {
        return changePassword(
                changePasswordCommand, 
                result, 
                model, 
                request,
                false);
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
    @RequestMapping(value = TgolKeyStore.CHANGE_PASSWORD_FROM_ADMIN_URL, method = RequestMethod.POST)
    @Secured(TgolKeyStore.ROLE_ADMIN_KEY)
    protected String submitChangePasswordFromAdmin(
            @ModelAttribute(TgolKeyStore.CHANGE_PASSWORD_COMMAND_KEY) ChangePasswordCommand changePasswordCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request)
            throws Exception {
        return changePassword(
                changePasswordCommand, 
                result, 
                model, 
                request,
                true);
    }
    
    /**
     * 
     * @param changePasswordCommand
     * @param result
     * @param model
     * @param request
     * @param isrequestFromAdmin
     * @return
     * @throws Exception
     */
    protected String changePassword(
            ChangePasswordCommand changePasswordCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request,
            boolean isrequestFromAdmin)
            throws Exception {
        User user = userDataService.read((Long)request.getSession().getAttribute(TgolKeyStore.USER_ID_KEY));
        if (forbiddenUserList.contains(user.getEmail1())) {
            throw new ForbiddenPageException();
        }
        // We check whether the form is valid
        changePasswordFormValidator.validate(changePasswordCommand,result,user);
        // If the form has some errors, we display it again with errors' details
        if (result.hasErrors()) {
            model.addAttribute(TgolKeyStore.USER_NAME_KEY, user.getEmail1());
            return displayChangePasswordFormWithErrors(
                    model,
                    changePasswordCommand,
                    isrequestFromAdmin);
        }
        request.getSession().removeAttribute(TgolKeyStore.USER_ID_KEY);
        model.addAttribute(TgolKeyStore.PASSWORD_MODIFIED_KEY, true);
        updateUserPassword(user,changePasswordCommand);
        if (isrequestFromAdmin) {
            return displayChangePasswordFromAdminPage(user.getId().toString(), request, model);
        } else {
            return displayChangePasswordFromUserPage(user.getId().toString(), request.getParameter("token"), request, model);
        }
    }

    /**
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = TgolKeyStore.FORGOTTEN_PASSWORD_CONFIRMATION_URL, method = RequestMethod.GET)
    public String displayUpSignUpConfirmationPage(
            Model model,
            HttpServletRequest request) {
        model.addAttribute(TgolKeyStore.URL_KEY, request.getSession().getAttribute(TgolKeyStore.URL_KEY));
        request.getSession().removeAttribute(TgolKeyStore.URL_KEY);
        return TgolKeyStore.FORGOTTEN_PASSWORD_CONFIRMATION_VIEW_NAME;
    }

    /**
     * 
     * @param model
     * @param forgottenPasswordCommand
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
     * @param changePasswordCommand
     * @param isrequestFromAdmin
     * @return
     */
    private String displayChangePasswordFormWithErrors(
            Model model,
            ChangePasswordCommand changePasswordCommand,
            boolean isrequestFromAdmin) {
        model.addAttribute(TgolKeyStore.CHANGE_PASSWORD_COMMAND_KEY,
                changePasswordCommand);
        if (isrequestFromAdmin) {
            model.addAttribute(TgolKeyStore.CHANGE_PASSWORD_FROM_ADMIN_KEY, true);
        } else {
            model.addAttribute(TgolKeyStore.CHANGE_PASSWORD_FROM_ADMIN_KEY, false);
        }
        return TgolKeyStore.CHANGE_PASSWORD_VIEW_NAME;
    }

    /**
     * This method gets data from a property file to fill-in the inscription
     * e-mail
     * @param user
     */
    private void sendResetEmail(User user, Locale locale) {
        Set<String> emailToSet = new HashSet();
        emailToSet.add(user.getEmail1());
        String emailSubject = messageSource.getMessage(TgolKeyStore.FORGOTTEN_PASSWD_EMAIL_SUBJECT_KEY, null, locale);
        String emailContent = messageSource.getMessage(TgolKeyStore.FORGOTTEN_PASSWD_EMAIL_CONTENT_KEY, null, locale).
            replaceAll(USER_ACCOUNT_TO_REPLACE, user.getEmail1()).
            replaceAll(CHANGE_PASSWORD_URL_TO_REPLACE, computeReturnedUrl(user));
        emailSender.sendEmail(
            appEmailFrom,
            emailToSet,
            Collections.<String>emptySet(),
            StringUtils.EMPTY,
            emailSubject,
            emailContent
        );
    }

    /**
     * 
     * @param user
     * @return
     */
    private String computeReturnedUrl(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append(webAppUrl+ CHANGE_PASSWORD_URL);
        sb.append("?user=");
        sb.append(user.getId());
        sb.append("&token=");
        try {
            sb.append(URLEncoder.encode(tokenManager.getTokenUser(user.getEmail1()), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            LoggerFactory.getLogger(this.getClass()).warn(ex.getMessage());
        }
        return sb.toString();
    }

    /**
     *
     * @param user
     * @param changePasswordCommand
     * @return an User
     * @throws Exception
     */
    private User updateUserPassword(
            User user,
            ChangePasswordCommand changePasswordCommand) throws Exception {
        user.setPassword(MD5Encoder.MD5(changePasswordCommand.getNewPassword()));
        userDataService.saveOrUpdate(user);
        return user;
    }

}
