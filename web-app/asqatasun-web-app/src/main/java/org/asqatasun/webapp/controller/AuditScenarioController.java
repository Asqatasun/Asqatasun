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

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.util.JRStyledTextParser;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.asqatasun.webapp.command.AddScenarioCommand;
import org.asqatasun.webapp.command.AuditSetUpCommand;
import org.asqatasun.webapp.command.factory.AddScenarioCommandFactory;
import org.asqatasun.entity.contract.Act;
import org.asqatasun.entity.contract.ActStatus;
import org.asqatasun.entity.contract.Contract;
import org.asqatasun.entity.contract.ScopeEnum;
import org.asqatasun.entity.scenario.Scenario;
import org.asqatasun.entity.service.scenario.ScenarioDataService;
import org.asqatasun.webapp.exception.ForbiddenPageException;
import org.asqatasun.webapp.ui.form.parameterization.AuditSetUpFormField;
import org.asqatasun.webapp.ui.form.parameterization.builder.AuditSetUpFormFieldBuilder;
import org.asqatasun.webapp.util.TgolKeyStore;
import org.asqatasun.webapp.validator.AddScenarioFormValidator;
import org.asqatasun.webapp.validator.AuditSetUpFormValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jkowalczyk
 */
@Controller
public class AuditScenarioController extends AbstractAuditSetUpController {

    private JRStyledTextParser j;
    private final AddScenarioFormValidator addScenarioFormValidator;
    private final AuditSetUpFormValidator auditSiteSetUpFormValidator;
    private final ScenarioDataService scenarioDataService;
    private final Map<String, List<AuditSetUpFormFieldBuilder>> scenarioOptionFormFieldBuilderMap;
    private final AddScenarioCommandFactory addScenarioCommandFactory;

    public AuditScenarioController(AddScenarioFormValidator addScenarioFormValidator,
                                   ScenarioDataService scenarioDataService,
                                   @Qualifier(value = "auditSetUpFormValidator")
                                       AuditSetUpFormValidator auditSiteSetUpFormValidator,
                                   @Qualifier(value="scenarioOptionFormFieldBuilderMap")
                                       Map <String, List <AuditSetUpFormFieldBuilder>> scenarioOptionFormFieldBuilderMap,
                                   AddScenarioCommandFactory addScenarioCommandFactory) {
        super();
        this.addScenarioFormValidator = addScenarioFormValidator;
        this.scenarioDataService = scenarioDataService;
        this.auditSiteSetUpFormValidator = auditSiteSetUpFormValidator;
        this.scenarioOptionFormFieldBuilderMap = scenarioOptionFormFieldBuilderMap;
        this.addScenarioCommandFactory = addScenarioCommandFactory;
    }

    @PostConstruct
    protected void init() {
        super.init();
        viewFunctionalityBindingMap = Collections.singletonMap("audit-scenario-set-up", "SCENARIO");
    }


    /**
     * @param contractId
     * @param request
     * @param response
     * @param model
     * @return The pages audit set-up form page
     */
    @RequestMapping(value = TgolKeyStore.AUDIT_SCENARIO_MANAGEMENT_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayScenarioManagement(
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        prepareScenarioManagementData(model, contractId);
        return TgolKeyStore.SCENARIO_MANAGEMENT_VIEW_NAME;
    }

    @RequestMapping(value = TgolKeyStore.AUDIT_SCENARIO_MANAGEMENT_CONTRACT_URL, method = RequestMethod.POST)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    protected String addScenario(
            @ModelAttribute(TgolKeyStore.ADD_SCENARIO_COMMAND_KEY) AddScenarioCommand addScenarioCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request) {

        Contract contract = contractDataService.read(addScenarioCommand.getContractId());

        addScenarioFormValidator.validate(addScenarioCommand, result);
        
        // If the "add scenario" form has no error, a new scenario is added, 
        // and the same page with updated data is displayed again
        if (!result.hasErrors()) {
            saveScenario(addScenarioCommand, contract);
            model.addAttribute(TgolKeyStore.NEW_SCENARIO_NAME_KEY, addScenarioCommand.getScenarioLabel());
            prepareScenarioManagementData(model, addScenarioCommand.getContractId().toString());
            return TgolKeyStore.SCENARIO_MANAGEMENT_VIEW_NAME;
        }
        
        addScenarioListToModel(contract, model);
        model.addAttribute(TgolKeyStore.ADD_SCENARIO_COMMAND_KEY, addScenarioCommand);
        model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());

        return TgolKeyStore.SCENARIO_MANAGEMENT_VIEW_NAME;
    }

    @RequestMapping(value = TgolKeyStore.DOWNLOAD_SCENARIO_URL_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public void getScenarioFile(
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            @RequestParam(TgolKeyStore.SCENARIO_ID_KEY) String scenarioId,
            HttpServletResponse response) {
        Contract contract = contractDataService.read(Long.valueOf(contractId));
        if (contract.getUser().getId().equals(getCurrentUser().getId())) {
            try {
                for (Scenario scenario : contract.getScenarioSet()) {
                    if (scenario.getId().equals(Long.valueOf(scenarioId))) {
                        InputStream is = IOUtils.toInputStream(scenario.getContent());
                        IOUtils.copy(is, response.getOutputStream());
                        response.setContentType(TgolKeyStore.CONTENT_TYPE); 
                        StringBuilder strb = new StringBuilder(TgolKeyStore.ATTACHMENT);
                        strb.append(scenario.getLabel());
                        strb.append(TgolKeyStore.JSON_EXTENSION);
                        response.setHeader(TgolKeyStore.CONTENT_DISPOSITION,strb.toString()); 
                        response.flushBuffer();
                        return;
                    }
                }
                throw new ForbiddenPageException(getCurrentUser());
            } catch (IOException ex) {
                throw new RuntimeException("IOError writing file to output stream");
            }
        } else {
            throw new ForbiddenPageException(getCurrentUser());
        }
    }
    
    @RequestMapping(value = TgolKeyStore.DELETE_SCENARIO_URL_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String deleteScenarioFile(
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            @RequestParam(TgolKeyStore.SCENARIO_ID_KEY) String scenarioId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Contract contract = contractDataService.read(Long.valueOf(contractId));
        if (contract.getUser().getId().equals(getCurrentUser().getId())) {
            for (Scenario scenario : contract.getScenarioSet()) {
                if (scenario.getId().equals(Long.valueOf(scenarioId))) {
                    deleteScenario(scenario, contract);
                    model.addAttribute(TgolKeyStore.DELETED_SCENARIO_NAME_KEY, scenario.getLabel());
                    prepareScenarioManagementData(model, contractId);
                    return TgolKeyStore.SCENARIO_MANAGEMENT_VIEW_NAME;
                }
            }
        }
        throw new ForbiddenPageException(getCurrentUser());
    }
    
    @RequestMapping(value = TgolKeyStore.DELETE_SCENARIO_URL_CONTRACT_URL, method = RequestMethod.POST)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String addScenarioFromDeleteScenarioPage(
            @ModelAttribute(TgolKeyStore.ADD_SCENARIO_COMMAND_KEY) AddScenarioCommand addScenarioCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request) {
        return this.addScenario(addScenarioCommand, result, model, request);
    }

    @RequestMapping(value = TgolKeyStore.AUDIT_SCENARIO_SET_UP_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayScenarioSetUp(
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            @RequestParam(TgolKeyStore.SCENARIO_ID_KEY) String scenarioId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Scenario scenario = scenarioDataService.read(Long.valueOf(scenarioId));
        model.addAttribute(TgolKeyStore.SCENARIO_KEY, scenario);
        return displayAuditSetUpView(
                TgolKeyStore.AUDIT_SCENARIO_SET_UP_VIEW_NAME,
                contractId,
                scenarioId,
                scenarioOptionFormFieldBuilderMap,
                ScopeEnum.SCENARIO,
                model);
    }

    @RequestMapping(value = TgolKeyStore.AUDIT_SCENARIO_SET_UP_CONTRACT_URL, method = RequestMethod.POST)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    protected String submitForm(
            @ModelAttribute(TgolKeyStore.ADD_SCENARIO_COMMAND_KEY) AuditSetUpCommand auditSetUpCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request) {
        Contract contract = contractDataService.read(auditSetUpCommand.getContractId());   
        Map<String, List<AuditSetUpFormField>> formFielMap = getFreshAuditSetUpFormFieldMap(
                    contract, 
                    scenarioOptionFormFieldBuilderMap);
        return submitForm(
                contract, 
                auditSetUpCommand, 
                formFielMap, 
                auditSiteSetUpFormValidator,
                model, 
                result, 
                request);
    }

    /**
     * Prepare data to be displayed on the scenario management page
     * 
     * @param model
     * @param contractId 
     */
    private void prepareScenarioManagementData(Model model, String contractId) {
        Long contractIdValue;
        try {
            contractIdValue = Long.valueOf(contractId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenPageException(getCurrentUser());
        }
        Contract contract = contractDataService.read(contractIdValue);
        if (isUserAllowedToDisplaySetUpPage(contract, TgolKeyStore.AUDIT_SCENARIO_SET_UP_VIEW_NAME)) {
            // add the AddScenarioCommand instance to the model
            model.addAttribute(TgolKeyStore.ADD_SCENARIO_COMMAND_KEY,
                    addScenarioCommandFactory.getAddScenarioCommand(contract));
            // add the contract label to the model
            model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
            // add the list of scenario to the model
            addScenarioListToModel(contract, model);
        }
    }
    
    /**
     * Persist a scenario
     * 
     * @param addScenarioCommand
     * @param contract 
     */
    private void saveScenario(AddScenarioCommand addScenarioCommand, Contract contract) {
        Scenario scenario = scenarioDataService.create();
        scenario.setLabel(addScenarioCommand.getScenarioLabel());
        scenario.setContent(addScenarioCommand.getScenarioContent());
        scenario.setContract(contract);
        scenario.setDateOfCreation(Calendar.getInstance().getTime());
        scenarioDataService.saveOrUpdate(scenario);
    }

    /**
     * Delete a scenario and the associated audits.
     * @param scenario
     * @param contract
     */
    private void deleteScenario(Scenario scenario, Contract contract) {
        scenarioDataService.delete(scenario.getId());
        Collection<Act> actCollection = retrieveActCollection(scenario, contract);
        for (Act act : actCollection) {
            auditDataService.delete(act.getAudit().getId());
            act.setStatus(ActStatus.DELETED);
            act.setAudit(null);
            actDataService.saveOrUpdate(act);
        }
    }
    
    /**
     * 
     * @param contract
     * @param model 
     */
    private void addScenarioListToModel(Contract contract, Model model) {
        List<Scenario> scenarios = new ArrayList();
        scenarios.addAll(contract.getScenarioSet());
        Collections.sort(scenarios, new ScenarioDateSorter());
        model.addAttribute(TgolKeyStore.SCENARIO_LIST_KEY, scenarios);
    }

    /**
     * Sort a collection of scenarios by date of creation
     */
    private class ScenarioDateSorter implements Comparator<Scenario> {
        @Override
        public int compare(Scenario t, Scenario t1) {
            return t1.getDateOfCreation().compareTo(t.getDateOfCreation());
        }
    }
    
    /**
     * Retrieve a collection of acts for a given scenario and contract. 
     * To do so, we retrieve all the acts associated with a contract and only 
     * keep the one with an webResource URL that matches the scenario label.
     * 
     * @param scenario
     * @param contract
     * @return 
     */
    private Collection<Act> retrieveActCollection(Scenario scenario, Contract contract) {
        Collection<Act> actCollectionFromScenarioAndContract = new HashSet();
        for (Act act : actDataService.getActsByContract(contract, -1, 2, ScopeEnum.SCENARIO, true)) {
            if (StringUtils.equals(scenario.getLabel(), act.getAudit().getSubject().getURL())) {
                actCollectionFromScenarioAndContract.add(act);
            }
        }
        return actCollectionFromScenarioAndContract;
    }
    
}
