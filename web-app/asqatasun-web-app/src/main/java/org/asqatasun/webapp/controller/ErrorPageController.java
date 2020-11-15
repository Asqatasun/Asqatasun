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

import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.asqatasun.webapp.util.TgolKeyStore.*;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class ErrorPageController implements ErrorController {

    private static final String CONTRACT_PATH = "home/contract";

    public ErrorPageController() {
        super();
    }

    @RequestMapping(value = ERROR_LOADING_URL, method=RequestMethod.GET)
    public String returnLoadingErrorPage(HttpServletRequest request, Model model){
        prepareModel(request, model);
        return LOADING_ERROR_VIEW_NAME;
    }

    @RequestMapping(value = CONTRACT_PATH+ERROR_LOADING_URL, method=RequestMethod.GET)
    public String returnLoadingErrorPageFromContract(HttpServletRequest request, Model model){
        prepareModel(request, model);
        return LOADING_ERROR_VIEW_NAME;
    }

    @RequestMapping(value = ERROR_ADAPTING_URL, method=RequestMethod.GET)
    public String returnAdaptingErrorPage(HttpServletRequest request,
            Model model){
        prepareModel(request, model);
        return ADAPTING_ERROR_VIEW_NAME;
    }

    @RequestMapping(value = CONTRACT_PATH+ERROR_ADAPTING_URL, method=RequestMethod.GET)
    public String returnAdaptingErrorPageFromContrat(HttpServletRequest request,
            Model model){
        prepareModel(request, model);
        return ADAPTING_ERROR_VIEW_NAME;
    }

    @RequestMapping(value = OUPS_URL, method=RequestMethod.GET)
    public String returnOupsErrorPage(HttpServletRequest request,
            Model model){
        prepareModel(request, model);
        return OUPS_VIEW_NAME;
    }

    @RequestMapping(value = CONTRACT_PATH+OUPS_URL, method=RequestMethod.GET)
    public String returnOupsErrorPageFromContrat(HttpServletRequest request,
            Model model){
        prepareModel(request, model);
        return OUPS_VIEW_NAME;
    }
    
    @RequestMapping(value = CONTRACT_PATH+QUOTA_EXCEEDED_URL, method=RequestMethod.GET)
    public String returnQuotaExceededPageFromContrat(HttpServletRequest request,
            Model model){
        prepareModel(request, model);
        return QUOTA_EXCEEDED_VIEW_NAME;
    }

    @RequestMapping(value = CONTRACT_PATH+QUOTA_BY_IP_EXCEEDED_URL, method=RequestMethod.GET)
    public String returnQuotaExceededByIpPageFromContrat(HttpServletRequest request,
            Model model){
        prepareModel(request, model);
        return QUOTA_BY_IP_EXCEEDED_VIEW_NAME;
    }
    
    @RequestMapping(value = CONTRACT_PATH+EXPORT_AUDIT_FORMAT_ERROR_URL, method=RequestMethod.GET)
    public String returnExportAuditFormatErrorPage(HttpServletRequest request,
            Model model){
        prepareModel(request, model);
        return EXPORT_AUDIT_FORMAT_ERROR_VIEW_NAME;
    }

    private void prepareModel(HttpServletRequest request, Model model) {
        model.addAttribute(DATE_KEY, GregorianCalendar.getInstance().getTime());
        if (request.getSession().getAttribute(AUDIT_URL_KEY) != null) {
            model.addAttribute(
                    URL_KEY,
                    request.getSession().getAttribute(AUDIT_URL_KEY));
            request.getSession().removeAttribute(AUDIT_URL_KEY);
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return ACCESS_DENIED_VIEW_NAME;
            }
            if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
                return ACCESS_DENIED_VIEW_NAME;
            }
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return ACCESS_DENIED_VIEW_NAME;
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return OUPS_VIEW_NAME;
            }
        }
        return "error";
    }
}
