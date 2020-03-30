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

import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.asqatasun.entity.reference.Scope;
import org.asqatasun.entity.service.subject.WebResourceDataService;
import org.asqatasun.entity.subject.Page;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.webapp.dto.AuditStatistics;
import org.asqatasun.webapp.dto.TestResult;
import org.asqatasun.webapp.exception.ForbiddenPageException;
import org.asqatasun.webapp.dto.factory.TestResultFactory;
import org.asqatasun.webapp.report.service.ExportService;
import org.asqatasun.webapp.report.service.exception.NotSupportedExportFormatException;
import org.asqatasun.webapp.util.TgolKeyStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;


/** 
 *
 * @author jkowalczyk
 */
@Controller
public class AuditExportResultController extends AbstractAuditDataHandlerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditExportResultController.class);

    private final ExportService exportService;
    private final TestResultFactory testResultFactory;
    private final LocaleResolver localeResolver;
    private final WebResourceDataService webResourceDataService;

    @Autowired
    public AuditExportResultController(LocaleResolver localeResolver,
                                       WebResourceDataService webResourceDataService,
                                       ExportService exportService,
                                       TestResultFactory testResultFactory) {
        super();
        this.localeResolver = localeResolver;
        this.webResourceDataService = webResourceDataService;
        this.exportService = exportService;
        this.testResultFactory = testResultFactory;
    }

    @Override
    public Map<String,String> getParametersToDisplay() {
        return Collections.singletonMap("LEVEL", "level");
    }

    /**
     * The export view is only available for page result
     * 
     * @param webresourceId 
     * @param format 
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value=TgolKeyStore.EXPORT_AUDIT_RESULT_CONTRACT_URL, method=RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String exportAuditResultFromContract (
            @RequestParam(value=TgolKeyStore.WEBRESOURCE_ID_KEY, required=false) String webresourceId,
            @RequestParam(value=TgolKeyStore.EXPORT_FORMAT_KEY, required=false) String format,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {

        if (format == null || webresourceId == null) {
            throw new ForbiddenPageException();
        }
        //We first check that the current user is allowed to display the result
        //of this audit
        Long webResourceIdValue;
        try {
            webResourceIdValue = Long.valueOf(webresourceId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenPageException();
        }

        WebResource webResource = webResourceDataService.ligthRead(webResourceIdValue);
        // if the id of the webresource corresponds to a Site webResource
        if (isUserAllowedToDisplayResult(getAuditFromWebResource(webResource))) {
            // If the Id given in argument correspond to a webResource,
            // data are retrieved to be prepared and displayed
            try {
                prepareSuccessfullAuditDataToExport(
                        webResource,
                        model,
                        localeResolver.resolveLocale(request),
                        format,
                        request,
                        response);
                return null;
            } catch (NotSupportedExportFormatException exc) {
                model.addAttribute(TgolKeyStore.WEBRESOURCE_ID_KEY, webresourceId);
                model.addAttribute(TgolKeyStore.EXPORT_FORMAT_KEY, format);
                LOGGER.warn(exc.getMessage());
                return TgolKeyStore.EXPORT_AUDIT_FORMAT_ERROR_VIEW_REDIRECT_NAME;
            }
        }
        return TgolKeyStore.EXPORT_AUDIT_FORMAT_ERROR_VIEW_REDIRECT_NAME;
    }

    /**
     * 
     * @param webResource
     * @param model
     * @param locale
     * @param exportFormat
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private void prepareSuccessfullAuditDataToExport(
            WebResource webResource,
            Model model,
            Locale locale,
            String exportFormat,
            HttpServletRequest request,
            HttpServletResponse response) throws NotSupportedExportFormatException {

        model.addAttribute(TgolKeyStore.LOCALE_KEY,locale);
        Scope scope = siteScope;
        if (webResource instanceof Page) {
            scope = pageScope;
        }
        List<TestResult> testResultList = testResultFactory.getTestResultList(
                    webResource,
                    scope,
                    localeResolver.resolveLocale(request));
        
        AuditStatistics auditStatistics = getAuditStatistics(
                    webResource,
                    TgolKeyStore.TEST_DISPLAY_SCOPE_VALUE,
            false);//TODO a revoir dans le cas manuel
        model.addAttribute(TgolKeyStore.STATISTICS_KEY, auditStatistics);

        try {
            exportService.export(
                    response,
                    webResource.getId(),
                    auditStatistics,
                    testResultList,
                    locale,
                    exportFormat);
        } catch (ColumnBuilderException | ClassNotFoundException | JRException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

}
