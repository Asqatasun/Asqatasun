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
package org.tanaguru.webapp.controller;

import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import org.tanaguru.webapp.util.TgolKeyStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class ErrorPageController {

    private static final String CONTRACT_PATH = "home/contract";

    public ErrorPageController() {
        super();
    }

    @RequestMapping(value = TgolKeyStore.ERROR_LOADING_URL, method=RequestMethod.GET)
    public String returnLoadingErrorPage(HttpServletRequest request, Model model){
        prepareModel(request, model);
        return TgolKeyStore.LOADING_ERROR_VIEW_NAME;
    }

    @RequestMapping(value = CONTRACT_PATH+TgolKeyStore.ERROR_LOADING_URL, method=RequestMethod.GET)
    public String returnLoadingErrorPageFromContract(HttpServletRequest request, Model model){
        prepareModel(request, model);
        return TgolKeyStore.LOADING_ERROR_VIEW_NAME;
    }

    @RequestMapping(value = TgolKeyStore.ERROR_ADAPTING_URL, method=RequestMethod.GET)
    public String returnAdaptingErrorPage(HttpServletRequest request,
            Model model){
        prepareModel(request, model);
        return TgolKeyStore.ADAPTING_ERROR_VIEW_NAME;
    }

    @RequestMapping(value = CONTRACT_PATH+TgolKeyStore.ERROR_ADAPTING_URL, method=RequestMethod.GET)
    public String returnAdaptingErrorPageFromContrat(HttpServletRequest request,
            Model model){
        prepareModel(request, model);
        return TgolKeyStore.ADAPTING_ERROR_VIEW_NAME;
    }

    @RequestMapping(value = TgolKeyStore.OUPS_URL, method=RequestMethod.GET)
    public String returnOupsErrorPage(HttpServletRequest request,
            Model model){
        prepareModel(request, model);
        return TgolKeyStore.OUPS_VIEW_NAME;
    }

    @RequestMapping(value = CONTRACT_PATH+TgolKeyStore.OUPS_URL, method=RequestMethod.GET)
    public String returnOupsErrorPageFromContrat(HttpServletRequest request,
            Model model){
        prepareModel(request, model);
        return TgolKeyStore.OUPS_VIEW_NAME;
    }
    
    @RequestMapping(value = CONTRACT_PATH+TgolKeyStore.QUOTA_EXCEEDED_URL, method=RequestMethod.GET)
    public String returnQuotaExceededPageFromContrat(HttpServletRequest request,
            Model model){
        prepareModel(request, model);
        return TgolKeyStore.QUOTA_EXCEEDED_VIEW_NAME;
    }

    @RequestMapping(value = CONTRACT_PATH+TgolKeyStore.QUOTA_BY_IP_EXCEEDED_URL, method=RequestMethod.GET)
    public String returnQuotaExceededByIpPageFromContrat(HttpServletRequest request,
            Model model){
        prepareModel(request, model);
        return TgolKeyStore.QUOTA_BY_IP_EXCEEDED_VIEW_NAME;
    }
    
    @RequestMapping(value = CONTRACT_PATH+TgolKeyStore.EXPORT_AUDIT_FORMAT_ERROR_URL, method=RequestMethod.GET)
    public String returnExportAuditFormatErrorPage(HttpServletRequest request,
            Model model){
        prepareModel(request, model);
        return TgolKeyStore.EXPORT_AUDIT_FORMAT_ERROR_VIEW_NAME;
    }

    private void prepareModel(HttpServletRequest request, Model model) {
        model.addAttribute(TgolKeyStore.DATE_KEY, GregorianCalendar.getInstance().getTime());
        if (request.getSession().getAttribute(TgolKeyStore.AUDIT_URL_KEY) != null) {
            model.addAttribute(
                    TgolKeyStore.URL_KEY,
                    request.getSession().getAttribute(TgolKeyStore.AUDIT_URL_KEY));
            request.getSession().removeAttribute(TgolKeyStore.AUDIT_URL_KEY);
        }
    }

}