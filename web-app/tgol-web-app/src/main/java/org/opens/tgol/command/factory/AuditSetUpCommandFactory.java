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
package org.opens.tgol.command.factory;

import java.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.service.parameterization.ParameterElementDataService;
import org.opens.tgol.command.AuditSetUpCommand;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ScopeEnum;
import org.opens.tgol.entity.decorator.tanaguru.parameterization.ParameterDataServiceDecorator;
import org.opens.tgol.entity.scenario.Scenario;
import org.opens.tgol.entity.service.contract.ContractDataService;
import org.opens.tgol.entity.service.scenario.ScenarioDataService;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.form.NumericalFormField;
import org.opens.tgol.form.SelectFormField;
import org.opens.tgol.form.parameterization.AuditSetUpFormField;
import org.opens.tgol.form.parameterization.helper.AuditSetUpFormFieldHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author jkowalczyk
 */
public final class AuditSetUpCommandFactory {

    /**
     * The code that identified the level Parameter
     */
    private String levelParameterCode = "LEVEL";

    public String getLevelParameterCode() {
        return levelParameterCode;
    }

    public void setLevelParameterCode(String levelParameterCode) {
        this.levelParameterCode = levelParameterCode;
    }

    /**
     *
     */
    public ParameterElement getLevelParameterElement() {
        return parameterElementDataService.getParameterElement(levelParameterCode);
    }
    /**
     *
     */
    private ParameterDataServiceDecorator parameterDataService;

    public ParameterDataServiceDecorator getParameterDataService() {
        return parameterDataService;
    }

    @Autowired
    public void setParameterDataService(ParameterDataServiceDecorator parameterDataService) {
        this.parameterDataService = parameterDataService;
    }
    /**
     *
     */
    private ParameterElementDataService parameterElementDataService;

    public ParameterElementDataService getParameterElementDataService() {
        return parameterElementDataService;
    }

    @Autowired
    public void setParameterElementDataService(ParameterElementDataService parameterElementDataService) {
        this.parameterElementDataService = parameterElementDataService;
    }
    /**
     *
     */
    private ContractDataService contractDataService;

    public ContractDataService getContractDataService() {
        return contractDataService;
    }

    @Autowired
    public void setContractDataService(ContractDataService contractDataService) {
        this.contractDataService = contractDataService;
    }
    /**
     *
     */
    private ScenarioDataService scenarioDataService;

    public ScenarioDataService getScenarioDataService() {
        return scenarioDataService;
    }

    @Autowired
    public void setScenarioDataService(ScenarioDataService scenarioDataService) {
        this.scenarioDataService = scenarioDataService;
    }

    /**
     * The holder that handles the unique instance of AuditSetUpCommandFactory
     */
    private static class AuditSetUpCommandFactoryHolder {

        private static final AuditSetUpCommandFactory INSTANCE =
                new AuditSetUpCommandFactory();
    }

    /**
     * Private constructor
     */
    private AuditSetUpCommandFactory() {
    }

    /**
     * Singleton pattern based on the "Initialization-on-demand holder idiom".
     * See
     *
     * @http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * @return the unique instance of AuditSetUpCommandFactory
     */
    public static AuditSetUpCommandFactory getInstance() {
        return AuditSetUpCommandFactoryHolder.INSTANCE;
    }

    /**
     *
     * @param contract
     * @param optionalFormFieldMap
     * @return
     */
    public AuditSetUpCommand getPageAuditSetUpCommand(
            Contract contract,
            List<SelectFormField> levelFormFieldList,
            Map<String, List<AuditSetUpFormField>> optionalFormFieldMap) {
        AuditSetUpCommand pageAuditSetUpCommand = new AuditSetUpCommand();
        pageAuditSetUpCommand.setScope(ScopeEnum.PAGE);
        pageAuditSetUpCommand.setUrlList(getGroupOfPagesUrl(contract, false));
        setUpAuditSetUpCommand(
                pageAuditSetUpCommand,
                contract,
                levelFormFieldList,
                optionalFormFieldMap,
                ScopeEnum.PAGE);
        return pageAuditSetUpCommand;
    }

    public AuditSetUpCommand getPageAuditSetUpCommand(
            Contract contract,
            String url,
            Set<Parameter> auditParamset) {
        AuditSetUpCommand pageAuditSetUpCommand = new AuditSetUpCommand();
        pageAuditSetUpCommand.setScope(ScopeEnum.PAGE);
        pageAuditSetUpCommand.setContractId(contract.getId());
        pageAuditSetUpCommand.setRelaunch(true);
        List<String> urlList = new ArrayList<String>();
        urlList.add(url);
        pageAuditSetUpCommand.setUrlList(urlList);
        for (Parameter param : auditParamset) {
            String paramCode = param.getParameterElement().getParameterElementCode();
            if (paramCode.equals(levelParameterCode)) {
                pageAuditSetUpCommand.setLevel(param.getValue());
            } else {
                pageAuditSetUpCommand.setAuditParameter(paramCode, param.getValue());
            }
        }
        return pageAuditSetUpCommand;
    }

    /**
     *
     * @param contract
     * @param levelFormFieldList
     * @param optionalFormFieldMap
     * @return
     */
    public AuditSetUpCommand getUploadAuditSetUpCommand(
            Contract contract,
            List<SelectFormField> levelFormFieldList,
            Map<String, List<AuditSetUpFormField>> optionalFormFieldMap) {
        AuditSetUpCommand uploadAuditSetUpCommand = new AuditSetUpCommand();
        uploadAuditSetUpCommand.setScope(ScopeEnum.FILE);
        uploadAuditSetUpCommand.setFileInputList(getGroupOfFileInput(contract));
        setUpAuditSetUpCommand(
                uploadAuditSetUpCommand,
                contract,
                levelFormFieldList,
                optionalFormFieldMap,
                ScopeEnum.FILE);
        return uploadAuditSetUpCommand;
    }

    /**
     *
     * @param contract
     * @param levelFormFieldList
     * @param optionalFormFieldMap
     * @return
     */
    public AuditSetUpCommand getSiteAuditSetUpCommand(
            Contract contract,
            List<SelectFormField> levelFormFieldList,
            Map<String, List<AuditSetUpFormField>> optionalFormFieldMap) {
        AuditSetUpCommand siteAuditSetUpCommand = new AuditSetUpCommand();
        siteAuditSetUpCommand.setUrlList(getGroupOfPagesUrl(contract, true));
        siteAuditSetUpCommand.setScope(ScopeEnum.DOMAIN);
        setUpAuditSetUpCommand(
                siteAuditSetUpCommand,
                contract,
                levelFormFieldList,
                optionalFormFieldMap,
                ScopeEnum.DOMAIN);
        return siteAuditSetUpCommand;
    }

    /**
     *
     * @param contract
     * @param levelFormFieldList
     * @param optionalFormFieldMap
     * @return
     */
    public AuditSetUpCommand getScenarioAuditSetUpCommand(
            Contract contract,
            String scenarioId,
            List<SelectFormField> levelFormFieldList,
            Map<String, List<AuditSetUpFormField>> optionalFormFieldMap) {
        AuditSetUpCommand scenarioAuditSetUpCommand = new AuditSetUpCommand();
        scenarioAuditSetUpCommand.setScope(ScopeEnum.SCENARIO);
        Scenario scenario = scenarioDataService.read(Long.valueOf(scenarioId));
        scenarioAuditSetUpCommand.setScenarioName(scenario.getLabel());
        scenarioAuditSetUpCommand.setScenarioId(scenario.getId());
        setUpAuditSetUpCommand(
                scenarioAuditSetUpCommand,
                contract,
                levelFormFieldList,
                optionalFormFieldMap,
                ScopeEnum.SCENARIO);
        return scenarioAuditSetUpCommand;
    }

    /**
     * Return a initialised auditCommand object for the given contract. This
     * object contains a map that associates a Parameter and a value (handled by
     * the AuditSetUpFormField). Each value is preset regarding the
     *
     * @param contract
     * @param auditSetUpFormFieldList
     * @param auditSite
     * @return
     */
    private synchronized void setUpAuditSetUpCommand(
            AuditSetUpCommand auditSetUpCommand,
            Contract contract,
            List<SelectFormField> levelFormFieldList,
            Map<String, List<AuditSetUpFormField>> optionalFormFieldMap,
            ScopeEnum scope) {

        // the auditSetCommand instance handles the id of the current contract
        auditSetUpCommand.setContractId(contract.getId());

        boolean isDefaultSet = true;
        if (scope == ScopeEnum.FILE || scope == ScopeEnum.PAGE) {
            // the default parameter corresponds by default to the site audit 
            // parameter set. If the audit is a page audit by definition, 
            // the parameter set is not set as default
            isDefaultSet = false;
        }

        // Set-up the audit level value to the auditSetUpCommand instance
        isDefaultSet = isDefaultSet
                && extractAndSetLevelValueFromAuditSetUpFormFieldList(contract, levelFormFieldList, auditSetUpCommand);

        // We set here the default value for each AuditSetUpFormField
        // If the parameter associated with the AuditSetUpFormField defines 
        // a different value as the default one, we override it 
        for (List<AuditSetUpFormField> apl : optionalFormFieldMap.values()) {
            for (AuditSetUpFormField ap : apl) {

                // We retrieve the default value of the Parameter associated 
                // with the AuditSetUpFormField
                String defaultValue = getDefaultParameterValue(ap);

                String paramValue = getValueOfParamOfAuditSetUpFormField(
                        contract,
                        defaultValue,
                        ap,
                        auditSetUpCommand);
                Logger.getLogger(this.getClass()).debug("paramValue  " + paramValue);
                // The auditSetUpCommand instance handles a map to bind each field
                // This map has to be set-up with the value of the Parameter and 
                // each parameter is identified throug the key with the 
                // ParameterElement code.
                auditSetUpCommand.addAuditParameterEntry(
                        ap.getParameterElement().getParameterElementCode(),
                        paramValue);

                // if from the set of options, one is different from the default
                // a flag is set. 
                isDefaultSet = isDefaultSet && StringUtils.equals(defaultValue, paramValue);
                Logger.getLogger(this.getClass()).debug("isDefaultSet  " + isDefaultSet);
            }
        }
        auditSetUpCommand.setDefaultParamSet(isDefaultSet);
    }

    /**
     *
     * @param contract
     * @return
     */
    private CommonsMultipartFile[] getGroupOfFileInput(Contract contract) {
        CommonsMultipartFile[] groupOfFileInput = new CommonsMultipartFile[AuditSetUpCommand.DEFAULT_LIST_SIZE];
        return (contract.getUser() == null) ? null : groupOfFileInput;
    }

    /**
     * This methods prepares the String table passed to the jsp that handles the
     * URL filled-in by the user. Depending the status of the user
     * (authenticated or guest), the table is pre-populated.
     *
     * @param contractId
     * @return
     */
    private List<String> getGroupOfPagesUrl(Contract contract, boolean isSiteAudit) {
        User user = contract.getUser();
        /* 
         * WARNING hard-coded exception for guest user 
         * @TODO : do it better
         */
        int nbOfPages = AuditSetUpCommand.DEFAULT_LIST_SIZE;
        List<String> groupOfPagesUrl = new LinkedList<String>();
        if (user == null) {
            return null;
        } else if (user.getEmail1().equalsIgnoreCase("guest") || isSiteAudit) {
            nbOfPages = 1;
        }
        for (int i = 0; i < nbOfPages; i++) {
            groupOfPagesUrl.add(new String());
        }
        // When the form is displayed for an authenticated user, it is
        // pre-populated with the Url recorded with the contract.
        String url = contractDataService.getUrlFromContractOption(contract);
        if (!StringUtils.isEmpty(url)) {
            groupOfPagesUrl.set(0, url);
        }
        return groupOfPagesUrl;
    }

    /**
     * The way to set and retrieve the referential and the level is always the
     * same, regardless the auditType.
     *
     * @return
     */
    private boolean extractAndSetLevelValueFromAuditSetUpFormFieldList(
            Contract contract,
            List<SelectFormField> levelFormFieldList,
            AuditSetUpCommand auditSetUpCommand) {
        ParameterElement levelParameterElement = getLevelParameterElement();
        // We retrieve the default value of the Parameter associated 
        // with the level ParameterElement
        String defaultValue = getParameterDataService().
                getDefaultParameter(levelParameterElement).getValue();
        Logger.getLogger(this.getClass()).debug("default level value " + defaultValue);
        ScopeEnum scope = auditSetUpCommand.getScope();
        boolean isDefaultValue = true;
        if (scope == ScopeEnum.DOMAIN || scope == ScopeEnum.SCENARIO) {
            String lastUserValue = retrieveParameterValueFromLastAudit(contract, levelParameterElement, auditSetUpCommand);
            Logger.getLogger(this.getClass()).debug("lastUserValue " + lastUserValue);
            if (lastUserValue != null && !StringUtils.equals(lastUserValue, defaultValue)) {
                // we override the auditParameter with the last user value
                defaultValue = lastUserValue;
                // If the level is overidden from the parameters of the last audit, 
                // we need to update the UI elements regarding this value (set this 
                // element as default)
                AuditSetUpFormFieldHelper.selectDefaultLevelFromLevelValue(levelFormFieldList, defaultValue);
                isDefaultValue = false;
            }
        }
        auditSetUpCommand.setLevel(defaultValue);
        return isDefaultValue;
    }

    /**
     * The default value of a Parameter associated with an AuditSetUpFormField
     * can be modified for two reasons : - the FormField is a NumericalFormField
     * - At least on site audit has been launched previously
     *
     * @param contract
     * @param defaultValue
     * @param ap
     * @param auditSetUpCommand
     * @return a boolean that indicates whether the added parameter value is the
     * default value
     */
    private String getValueOfParamOfAuditSetUpFormField(
            Contract contract,
            String defaultValue,
            AuditSetUpFormField ap,
            AuditSetUpCommand auditSetUpCommand) {

        // In case of site audit, we retrieve the last value filled-in 
        // by the user for the parameter. Let's consider this as a 
        // user preference management. 
        ScopeEnum scope = auditSetUpCommand.getScope();
        if (scope == ScopeEnum.DOMAIN || scope == ScopeEnum.SCENARIO) {
            String lastUserValue = retrieveParameterValueFromLastAudit(contract, ap.getParameterElement(), auditSetUpCommand);
            if (lastUserValue != null && !StringUtils.equals(lastUserValue, defaultValue)) {
                // we override the auditParameter with the last user value
                return lastUserValue;
            }
        }
        return defaultValue;
    }

    /**
     *
     * @param ap
     * @return
     */
    private String getDefaultParameterValue(AuditSetUpFormField ap) {

        String defaultValue = getParameterDataService().
                getDefaultParameter(ap.getParameterElement()).getValue();
        Logger.getLogger(this.getClass()).debug(
                "defaultValue  " + defaultValue + "for param " + ap.getParameterElement().getParameterElementCode());
        // override default value in case of NumericalFormField if the
        // its default max value is inferior to the default value of the parameter. 
        // The parameter value is reduced to the value of the field (to respect
        // its bounds)
        if (ap.getFormField() instanceof NumericalFormField) {
            String maxValue = ((NumericalFormField) ap.getFormField()).getMaxValue();
            if (Integer.valueOf(maxValue).compareTo(Integer.valueOf(defaultValue)) < 0) {
                defaultValue = maxValue;
            }
        }
        return defaultValue;
    }

    /**
     *
     * @param contract
     * @param parameterElement
     * @param scope
     * @return
     */
    private String retrieveParameterValueFromLastAudit(
            Contract contract,
            ParameterElement parameterElement,
            AuditSetUpCommand auditSetUpCommand) {
        ScopeEnum scope = auditSetUpCommand.getScope();
        if (scope == ScopeEnum.DOMAIN) {
            return parameterDataService.getLastParameterValueFromUser(contract.getId(), parameterElement, ScopeEnum.DOMAIN);
        } else if (scope == ScopeEnum.SCENARIO) {
            return parameterDataService.getLastParameterValueFromContractAndScenario(
                    contract.getId(),
                    parameterElement,
                    auditSetUpCommand.getScenarioName());
        }
        return null;
    }
}