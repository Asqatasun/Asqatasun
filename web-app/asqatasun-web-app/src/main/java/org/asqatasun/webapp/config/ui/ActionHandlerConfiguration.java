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
package org.asqatasun.webapp.config.ui;

import org.asqatasun.webapp.action.builder.ActionBuilder;
import org.asqatasun.webapp.action.voter.*;
import org.asqatasun.webapp.restriction.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

@Configuration
public class ActionHandlerConfiguration {

    @Bean
    public ActionHandler contractActionHandler() {
        ContractActionHandlerImpl contractActionHandlerImpl = new ContractActionHandlerImpl();
        contractActionHandlerImpl.setActionBuilderList(
            Arrays.asList(
                auditPageActionBuilder(),
                auditSiteActionBuilder(),
                auditScenarioActionBuilder(),
                auditUploadActionBuilder()));
        return contractActionHandlerImpl;
    }
    @Bean
    public ActionBuilder auditPageActionBuilder(){
        return build("PAGES",
            "contract.launchPages",
            "/home/contract/audit-page-set-up.html",
            "images/Audit_Page_Desactivated.png",
            "images/Audit_Page.png",
            null);
    }
    @Bean
    public ActionBuilder auditSiteActionBuilder(){
        return build("DOMAIN",
            "contract.launchFullSite",
            "/home/contract/audit-site-set-up.html",
            "images/Audit_Site_Desactivated.png",
            "images/Audit_Site.png",
            null);
    }
    @Bean
    public ActionBuilder auditUploadActionBuilder(){
        return build("UPLOAD",
            "contract.launchUpload",
            "/home/contract/audit-upload-set-up.html",
            "images/Audit_HorsLigne_Desactivated.png",
            "images/Audit_HorsLigne.png",
            null);
    }
    @Bean
    public ActionBuilder auditScenarioActionBuilder(){
        return build("SCENARIO",
            "contract.launchScenario",
            "/home/contract/scenario-management.html",
            "images/Audit_Scenario_Desactivated.png",
            "images/Audit_Scenario.png",
            null);
    }

    @Bean
    public ActionBuilder exportOdsActionBuilder(){
        return build("EXPORT_ODS",
            "resultPage.odsExport",
            "#",
            "images/ods_export.png",
            "images/ods_export.png",
            "resultPage.odsExportAlt");
    }
    @Bean
    public ActionBuilder exportCsvActionBuilder(){
        return build("EXPORT_CSV",
            "resultPage.csvExport",
            "/home/contract/export-audit-result.html?format=csv",
            "images/csv_export.png",
            "images/csv_export.png",
            "resultPage.csvExportAlt");
    }
    @Bean
    public ActionBuilder exportPdfActionBuilder(){
        return build("EXPORT_PDF",
            "resultPage.pdfExport",
            "/home/contract/export-audit-result.html?format=pdf",
            "images/pdf_export.png",
            "images/pdf_export.png",
            "resultPage.pdfExportAlt");
    }
    @Bean
    public ActionVoter exportVoter(){
        ActionVoterImpl voter = new ActionVoterImpl();
        voter.setActionEnableMap(new HashMap <String, Boolean>() {
            {
                put("EXPORT_ODS", true);
                put("EXPORT_PDF", true);
                put("EXPORT_CSV", true);
            }});
        voter.setCode("EXPORT");
        return voter;
    }

    @Bean
    public ActionHandler resultActionHandler() {
        ResultActionHandlerImpl resultActionHandler = new ResultActionHandlerImpl();
        resultActionHandler.setActionBuilderList(
            Arrays.asList(exportPdfActionBuilder(), exportCsvActionBuilder(), exportOdsActionBuilder()));
        resultActionHandler.setActionAccessibilityVoterMap(Collections.singletonMap("EXPORT",exportVoter()));
        return resultActionHandler;
    }

    @Bean
    public RestrictionVoter actByIpLimitationVoter() {
        return new ActByIpLimitationVoter();
    }
    @Bean
    public RestrictionVoter actLimitationVoter() {
        return new ActLimitationVoter();
    }
    @Bean
    public RestrictionVoter domainActLimitationVoter() {
        ActByScopeLimitationVoter voter = new ActByScopeLimitationVoter();
        voter.setScopes(Arrays.asList("DOMAIN"));
        return voter;
    }
    @Bean
    public RestrictionVoter pagesActLimitationVoter() {
        ActByScopeLimitationVoter voter = new ActByScopeLimitationVoter();
        voter.setScopes(Arrays.asList("PAGE","GROUPOFFILES"));
        return voter;
    }
    @Bean
    public RestrictionVoter uploadActLimitationVoter() {
        ActByScopeLimitationVoter voter = new ActByScopeLimitationVoter();
        voter.setScopes(Arrays.asList("FILE","GROUPOFFILES"));
        return voter;
    }
    @Bean
    public RestrictionVoter scenarioActLimitationVoter() {
        ActByScopeLimitationVoter voter = new ActByScopeLimitationVoter();
        voter.setScopes(Arrays.asList("SCENARIO"));
        return voter;
    }
    @Bean
    public RestrictionHandler restrictionHandler() {
        RestrictionHandler restrictionHandler = new RestrictionHandler();
        restrictionHandler.setRestrictionVoterMap(new HashMap <String, RestrictionVoter>() {
            {
                put("ACT_LIMITATION", actLimitationVoter());
                put("ACT_BY_IP_LIMITATION", actByIpLimitationVoter());
                put("DOMAIN_ACT_LIMITATION", domainActLimitationVoter());
                put("PAGES_ACT_LIMITATION", pagesActLimitationVoter());
                put("UPLOAD_ACT_LIMITATION", uploadActLimitationVoter());
                put("SCENARIO_ACT_LIMITATION", scenarioActLimitationVoter());
            }});
        return restrictionHandler;
    }

    private ActionBuilder build(String actionCode, String actionI81NCode, String actionUrl,
                                String disabledActionImageUrl, String enabledActionImageUrl,
                                String actionAltI81NCode) {
        ActionBuilder action = new ActionBuilder();
        action.setActionCode(actionCode);
        action.setActionI81NCode(actionI81NCode);
        if (actionAltI81NCode!=null) {
            action.setActionAltI81NCode(actionAltI81NCode);
        }
        action.setActionUrl(actionUrl);
        action.setDisabledActionImageUrl(disabledActionImageUrl);
        action.setEnabledActionImageUrl(enabledActionImageUrl);
        return action;
    }
}
