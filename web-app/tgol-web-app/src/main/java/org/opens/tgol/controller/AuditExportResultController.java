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

import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.presentation.data.AuditStatistics;
import org.opens.tgol.presentation.data.TestResult;
import org.opens.tgol.presentation.factory.TestResultFactory;
import org.opens.tgol.util.TgolKeyStore;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tgol.report.service.ExportService;
import org.opens.tgol.report.service.exception.NotSupportedExportFormatException;
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
public class AuditExportResultController extends AuditDataHandlerController {

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
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value=TgolKeyStore.EXPORT_AUDIT_RESULT_CONTRACT_URL, method=RequestMethod.GET)
    @Secured(TgolKeyStore.ROLE_USER_KEY)
    public String exportAuditResultFromContract(
            @RequestParam(value=TgolKeyStore.WEBRESOURCE_ID_KEY, required=false) String webresourceId,
            @RequestParam(value=TgolKeyStore.EXPORT_FORMAT_KEY, required=false) String format,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        if (format== null || webresourceId == null) {
            return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        }
        User user = getCurrentUser();
        //We first check that the current user is allowed to display the result
        //of this audit
        Long wrId = Long.valueOf(webresourceId);
        WebResource webResource = getWebResourceDataService().ligthRead(wrId);
        if (isUserAllowedToDisplayResult(user,webResource) || (webResource!=null && webResource instanceof Site) ) {
            if (webResource != null) {
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
            } else {
                // If the Id given in argument does not correspond to any
                // webresource, a 404 page is returned
                try {
                    response.sendError(HttpStatus.SC_NOT_FOUND);
                } catch (IOException ex) {
                    LOGGER.error(ex);
                }
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
        boolean hasSourceCodeWithDoctype = false;
        if (webResource instanceof Page) {
            SSP ssp = null;
            try {
                ssp = getContentDataService().findSSP(webResource, webResource.getURL());
                if (ssp.getDoctype() != null && !ssp.getDoctype().trim().isEmpty()) {
                    hasSourceCodeWithDoctype = true;
                }
            } catch (NoResultException nre) {}
            AuditStatistics auditStatistics = getAuditStatistics(webResource, model);
            model.addAttribute(TgolKeyStore.STATISTICS_KEY, getAuditStatistics(webResource, model));

            List<TestResult> testResultList = TestResultFactory.getInstance().getTestResultList(
                        webResource,
                        getPageScope(),
                        hasSourceCodeWithDoctype,
                        false, // no detailed results
                        getLocaleResolver().resolveLocale(request));
            try {
                exportService.export(
                        response,
                        webResource.getId(),
                        auditStatistics,
                        testResultList,
                        locale,
                        exportFormat);
            } catch (ColumnBuilderException ex) {
                LOGGER.error(ex);
            } catch (ClassNotFoundException ex) {
                LOGGER.error(ex);
            } catch (JRException ex) {
                LOGGER.error(ex);
            }
        }
    }

}