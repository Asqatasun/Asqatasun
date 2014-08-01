/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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

import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tgol.exception.ForbiddenPageException;
import org.opens.tgol.presentation.data.AuditStatistics;
import org.opens.tgol.presentation.data.TestResult;
import org.opens.tgol.presentation.factory.TestResultFactory;
import org.opens.tgol.report.service.ExportService;
import org.opens.tgol.report.service.exception.NotSupportedExportFormatException;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/** 
 *
 * @author jkowalczyk
 */
@Controller
public class AuditExportResultController extends AbstractAuditDataHandlerController {

    private static final Logger LOGGER = Logger.getLogger(AuditExportResultController.class);

    private ExportService exportService;
    public ExportService getExportService() {
        return exportService;
    }
    @Autowired
    public void setExportService(ExportService exportService) {
        this.exportService = exportService;
    }

    public AuditExportResultController() {
        super();
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

        WebResource webResource = getWebResourceDataService().ligthRead(webResourceIdValue);
        // if the id of the webresource corresponds to a Site webResource
        if (isUserAllowedToDisplayResult(getAuditFromWebResource(webResource))) {
            // If the Id given in argument correspond to a webResource,
            // data are retrieved to be prepared and displayed
            try {
                prepareSuccessfullAuditDataToExport(
                        webResource,
                        model,
                        getLocaleResolver().resolveLocale(request),
                        format,
                        request,
                        response);
                return null;
            } catch (NotSupportedExportFormatException exc) {
                model.addAttribute(TgolKeyStore.WEBRESOURCE_ID_KEY, webresourceId);
                model.addAttribute(TgolKeyStore.EXPORT_FORMAT_KEY, format);
                LOGGER.warn(exc);
                return TgolKeyStore.EXPORT_AUDIT_FORMAT_ERROR_VIEW_REDIRECT_NAME;
            }
        }
        return TgolKeyStore.EXPORT_AUDIT_FORMAT_ERROR_VIEW_REDIRECT_NAME;
    }

    /**
     * 
     * @param page
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
        Scope scope = getSiteScope();
        if (webResource instanceof Page) {
            scope = getPageScope();
        }
        List<TestResult> testResultList = TestResultFactory.getInstance().getTestResultList(
                    webResource,
                    scope,
                    getLocaleResolver().resolveLocale(request));
        
        AuditStatistics auditStatistics = getAuditStatistics(
                    webResource, 
                    model, 
                    TgolKeyStore.TEST_DISPLAY_SCOPE_VALUE, false);//TODO a revoir dans le cas manuel 
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
            LOGGER.error(ex);
        }
    }

}