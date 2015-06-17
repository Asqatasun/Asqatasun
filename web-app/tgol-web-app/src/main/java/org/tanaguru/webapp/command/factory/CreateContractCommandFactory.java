/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.tanaguru.webapp.command.factory;

import java.io.Serializable;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.webapp.command.CreateContractCommand;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.functionality.Functionality;
import org.tanaguru.webapp.entity.option.Option;
import org.tanaguru.webapp.entity.option.OptionElement;
import org.tanaguru.webapp.entity.referential.Referential;
import org.tanaguru.webapp.entity.service.functionality.FunctionalityDataService;
import org.tanaguru.webapp.entity.service.option.OptionDataService;
import org.tanaguru.webapp.entity.service.option.OptionElementDataService;
import org.tanaguru.webapp.entity.service.referential.ReferentialDataService;

/**
 *
 * @author jkowalczyk
 */
public class CreateContractCommandFactory implements Serializable {

    private static final String DOMAIN_OPTION_CODE = "DOMAIN";
    
    private Collection<Referential> referentialList;
    private Collection<Functionality> functionalityList;
    private Collection<Option> optionList = new HashSet<Option>();
    
    public void setReferentialDataService (ReferentialDataService referentialDataService) {
        referentialList = referentialDataService.findAll();
    }
    
    public void setFunctionalityDataService (FunctionalityDataService funcitonalityDataService) {
        functionalityList = funcitonalityDataService.findAll();
    }

    private Option contractUrlOption;
    
    public void setOptionDataService (OptionDataService optionDataService) {
        for (Option option : optionDataService.findAll()) {
            if (option.getCode().equals(DOMAIN_OPTION_CODE)){
                contractUrlOption = option;
            } else {
                optionList.add(option);    
            }
        }
    }

    private OptionElementDataService optionElementDataService;
    public void setOptionElementDataService (OptionElementDataService optionElementDataService) {
        this.optionElementDataService = optionElementDataService;
    }

    /**
     * The holder that handles the unique instance of CreateContractCommandFactory
     */
    private static class CreateContractCommandFactoryHolder {
        private static final CreateContractCommandFactory INSTANCE = 
                new CreateContractCommandFactory();
    }
    
    /**
     * Private constructor
     */
    private CreateContractCommandFactory() {}
    
    /**
     * Singleton pattern based on the "Initialization-on-demand 
     * holder idiom". See @http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * @return the unique instance of CreateContractCommandFactory
     */
    public static CreateContractCommandFactory getInstance() {
        return CreateContractCommandFactoryHolder.INSTANCE;
    }
    
    /**
     * 
     * @param contract
     * @return 
     */
    public CreateContractCommand getInitialisedCreateContractCommand(Contract contract) {
        CreateContractCommand createContractCommand = new CreateContractCommand();
        
        createContractCommand.setLabel(contract.getLabel());
        createContractCommand.setBeginDate(contract.getBeginDate());
        createContractCommand.setEndDate(contract.getEndDate());
        
        addFunctionalityToCommand(createContractCommand, contract);
        addReferentialToCommand(createContractCommand, contract);
        addOptionToCommand(createContractCommand,contract);
        addContractUrlToCommand(createContractCommand,contract);

        return createContractCommand;
    }
    
    /**
     * 
     * @param createContractCommand
     * @return 
     */
    public CreateContractCommand getInitialisedCreateContractCommand(
            CreateContractCommand createContractCommand) {
        addFunctionalityToExistingCommand(createContractCommand);
        addReferentialToExistingCommand(createContractCommand);
        return createContractCommand;
    }
    
    /**
     * 
     * @return 
     */
    public CreateContractCommand getNewCreateContractCommand() {
        CreateContractCommand createContractCommand = getCreateContractCommand();
        Calendar cal = Calendar.getInstance();
        createContractCommand.setBeginDate(cal.getTime());
        cal.add(Calendar.YEAR,1);
        createContractCommand.setEndDate(cal.getTime());
        return createContractCommand;
    }
    
    /**
     * 
     * @return 
     */
    private CreateContractCommand getCreateContractCommand() {
        CreateContractCommand createContractCommand = new CreateContractCommand();

        addNewFunctionalityToCommand(createContractCommand);
        addNewReferentialToCommand(createContractCommand);
        addNewOptionsToCommand(createContractCommand);

        return createContractCommand;
    }

    /**
     * 
     */
    private void addFunctionalityToCommand(CreateContractCommand ccc, Contract contract) {
        Map<String,Boolean> functMap = new LinkedHashMap<String,Boolean>();
        
        for (Functionality funct : this.functionalityList){
            if (contract.getFunctionalitySet().contains(funct)) {
                functMap.put(funct.getCode(),Boolean.TRUE);
            } else {
                functMap.put(funct.getCode(),Boolean.FALSE);
            }
        }
        ccc.setFunctionalityMap(functMap);
    }
    
    /**
     * 
     */
    private void addFunctionalityToExistingCommand(CreateContractCommand ccc) {
        Map<String,Boolean> functMap = new LinkedHashMap<String,Boolean>();
        
        for (Map.Entry<String,Boolean> entry : ccc.getFunctionalityMap().entrySet()){
            if (entry.getValue() == null) {
                functMap.put(entry.getKey(),Boolean.FALSE);
            } else {
                functMap.put(entry.getKey(),Boolean.TRUE);
            }
        }
        ccc.setFunctionalityMap(functMap);
    }
    
    /**
     * 
     * @param ccc 
     */
    private void addNewFunctionalityToCommand(CreateContractCommand ccc) {
        Map<String,Boolean> functMap = new LinkedHashMap<String,Boolean>();
        
        for (Functionality funct : this.functionalityList){
            functMap.put(funct.getCode(),Boolean.FALSE);
        }
        ccc.setFunctionalityMap(functMap);
    }
    
    /**
     * 
     * @param ccc
     * @param contract 
     */
    private void addReferentialToCommand(CreateContractCommand ccc, Contract contract) {
        Map<String,Boolean> refMap = new LinkedHashMap<String,Boolean>();
        
        for (Referential ref : referentialList){
            if (contract.getReferentialSet().contains(ref)) {
                refMap.put(ref.getCode(),Boolean.TRUE);
            } else {
                refMap.put(ref.getCode(),Boolean.FALSE);
            }
        }
        ccc.setReferentialMap(refMap);
    }
    
    /**
     * 
     * @param ccc
     * @param contract 
     */
    private void addReferentialToExistingCommand(CreateContractCommand ccc) {
        Map<String,Boolean> refMap = new LinkedHashMap<String,Boolean>();
        
        for (Map.Entry<String,Boolean> entry : ccc.getReferentialMap().entrySet()){
            if (entry.getValue() == null) {
                refMap.put(entry.getKey(),Boolean.FALSE);
            } else {
                refMap.put(entry.getKey(),Boolean.TRUE);
            }
        }
        ccc.setReferentialMap(refMap);
    }
    
    /**
     * 
     * @param ccc 
     */
    private void addNewReferentialToCommand(CreateContractCommand ccc) {
        Map<String,Boolean> refMap = new LinkedHashMap<String,Boolean>();
        
        for (Referential ref : referentialList){
            refMap.put(ref.getCode(),Boolean.FALSE);
        }
        ccc.setReferentialMap(refMap);
    }
    
    /**
     * 
     * @param ccc
     * @param contract 
     */
    private void addOptionToCommand(CreateContractCommand ccc, Contract contract) {
        Map<String,String> optionMap = new LinkedHashMap<String, String>();
        
        for (Option option : optionList){
            optionMap.put(
                option.getCode(),
                getValueFromOptionElementCollection(contract.getOptionElementSet(),option));
        }
        ccc.setOptionMap(optionMap);
    }
    
    /**
     * 
     * @param ccc
     * @param contract 
     */
    private void addContractUrlToCommand(CreateContractCommand ccc, Contract contract) {
        for (OptionElement optionElement : contract.getOptionElementSet()){
            if (optionElement.getOption().equals(contractUrlOption)) {
                ccc.setContractUrl(optionElement.getValue());
            }
        }
    }
    
    /**
     * 
     * @param ccc
     * @param contract 
     */
    private void addNewOptionsToCommand(CreateContractCommand ccc) {
        Map<String,String> optionMap = new LinkedHashMap<String,String>();
        for (Option option : optionList) {
            if (!option.getCode().equals(DOMAIN_OPTION_CODE)) {
                optionMap.put(option.getCode(),"");
            }
        }
        ccc.setOptionMap(optionMap);
    }

    /**
     * Retrieve the option value from a collection of option elements.
     * 
     * @param optionElementCollection
     * @param option
     * @return 
     */
    private String getValueFromOptionElementCollection(
            Collection<OptionElement> optionElementCollection, 
            Option option) {
        for (OptionElement optionElement : optionElementCollection) {
            if (optionElement.getOption().getCode().equals(option.getCode())) {
                return optionElement.getValue();
            }
        }
        return "";
    }

    /**
     * 
     * @param ccc the CreateContractCommand
     * @param contract
     * @return 
     */
    public Contract updateContractFromCommand(
            CreateContractCommand ccc, 
            Contract contract) {
        
        Set<Functionality> functSet = new HashSet<Functionality>();
        Set<Referential> refSet = new HashSet<Referential>();
        Set<OptionElement> optionElementSet = new HashSet<OptionElement>();
        
        for (Map.Entry<String,Boolean> entry : ccc.getFunctionalityMap().entrySet()) {
            if (entry.getValue() != null && entry.getValue()) {
                functSet.add(getFunctionalityFromCode(entry.getKey()));
            }
        }
        for (Map.Entry<String,Boolean> entry : ccc.getReferentialMap().entrySet()) {
            if (entry.getValue() != null && entry.getValue()) {
                refSet.add(getReferentialFromCode(entry.getKey()));
            }
        }
        for (Map.Entry<String,String> entry : ccc.getOptionMap().entrySet()) {
            if (!StringUtils.isEmpty(entry.getValue())) {
                optionElementSet.add(getOptionElementFromOptionAndValue(entry.getKey(), entry.getValue()));
            }
        }
        if (!StringUtils.isEmpty(ccc.getContractUrl()) && !ccc.getContractUrl().equalsIgnoreCase("http://")) {
            optionElementSet.add(addUrlToContract(ccc.getContractUrl()));
        }
        
        contract.addAllFunctionality(functSet);
        contract.addAllReferential(refSet);
        contract.addAllOptionElement(optionElementSet);
        
        contract.setBeginDate(ccc.getBeginDate());
        Calendar cal = Calendar.getInstance();
        cal.setTime(ccc.getEndDate());
        // the end contract date is added without the hour/minute/second info
        // we force the end of the filled-in day before we persist.
        cal.add(Calendar.MINUTE, 59);
        cal.add(Calendar.HOUR_OF_DAY, 23);
        cal.add(Calendar.SECOND, 59);
        contract.setEndDate(cal.getTime());
        contract.setLabel(ccc.getLabel());

        return contract;
    }

    
    /**
    * 
    * @param url
    * @return 
    */
    private OptionElement addUrlToContract(String url) {
        return createOptionElement(contractUrlOption, url);
    }
    
    /**
     * @param value
     * @param optionCode
     * @return 
     */
    private OptionElement getOptionElementFromOptionAndValue(String optionCode, String value) {
        Option option = getOptionFromCode(optionCode);
        return createOptionElement(option, value);
    }
    
    /**
     * 
     * @param value
     * @param option
     * @return 
     */
    private OptionElement createOptionElement(Option option, String value) {
        OptionElement optionElement = optionElementDataService.getOptionElementFromValueAndOption(value, option);
        if (optionElement != null) {
            return optionElement;
        }
        optionElement = optionElementDataService.create();
        optionElement.setValue(value);
        optionElement.setOption(option);
        optionElementDataService.saveOrUpdate(optionElement);
        return optionElement;
    }

    /**
     * Retrieve a referential from its code regarding the local referential list.
     * 
     * @param refCode
     * @return 
     */
    private Referential getReferentialFromCode(String refCode) {
        for (Referential ref : referentialList) {
            if (ref.getCode().equalsIgnoreCase(refCode)) {
                return ref;
            }
        }
        return null;
    }
    
    /**
     * Retrieve a functionality from its code regarding the local functionality
     * list.
     * 
     * @param functCode
     * @return 
     */
    private Functionality getFunctionalityFromCode(String functCode) {
        for (Functionality funct : functionalityList) {
            if (funct.getCode().equalsIgnoreCase(functCode)) {
                return funct;
            }
        }
        return null;
    }
    
    /**
     * Retrieve an option from its code regarding the local option list
     * 
     * @param optionCode
     * @return 
     */
    private Option getOptionFromCode(String optionCode) {
        for (Option option : optionList) {
            if (option.getCode().equalsIgnoreCase(optionCode)) {
                return option;
            }
        }
        return null;
    }
    
}