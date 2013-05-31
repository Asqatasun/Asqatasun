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
package org.opens.tgol.validator;

import java.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.opens.tgol.command.CreateContractCommand;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.form.parameterization.ContractOptionFormField;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author jkowalczyk
 */
public class CreateContractFormValidator implements Validator {
    
    private static final String GENERAL_ERROR_MSG_KEY = "generalErrorMsg";
    private static final String CONTRACT_URL_KEY = "contractUrl";
    private static final String CONTRACT_LABEL_KEY = "label";
    private static final String USER_LIST_KEY = "userList";
    private static final String BEGIN_DATE_KEY = "beginDate";
    private static final String END_DATE_KEY = "endDate";

    private static final String MANDATORY_FIELD_MSG_BUNDLE_KEY =
            "sign-up.mandatoryField";
    private static final String EMPTY_LABEL_KEY =
            "edit-contract.emptyLabel";
    private static final String EMPTY_BEGIN_DATE_KEY =
            "edit-contract.emptyBeginDate";
    private static final String EMPTY_END_DATE_KEY =
            "edit-contract.emptyEndDate";
    private static final String EMPTY_USER_LIST_KEY =
            "edit-contract.emptyUserList";
    private static final String EMPTY_REF_KEY =
            "edit-contract.emptyRefChoice";
    private static final String END_DATE_ANTERIOR_TO_BEGIN_KEY =
            "edit-contract.endDateAnteriorToBeginDate";
    private static final String INVALID_URL_KEY =
            "sign-up.invalidUrl";

//    private static final String URL_CHECKER_REGEXP =
//            "(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
//    private final Pattern urlCheckerPattern = Pattern.compile(URL_CHECKER_REGEXP);
    
    private Map<String, ContractOptionFormField> contractOptionFormFieldMap =
            new HashMap<String,ContractOptionFormField>();
    public void setContractOptionFormFieldMap(Map<String, List<ContractOptionFormField>> contractOptionFormFieldMap) {
        for (Map.Entry<String, List<ContractOptionFormField>> entry : contractOptionFormFieldMap.entrySet()) {
            for (ContractOptionFormField coff : entry.getValue()){
                this.contractOptionFormFieldMap.put(coff.getOption().getCode(), coff);
            }
        }
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        CreateContractCommand ccc = (CreateContractCommand)target;
        checkMandatoryElements(ccc, errors, false);
        checkElements(ccc, errors);
    }

    public void validateMultipleUsers(Object target, Errors errors) {
        CreateContractCommand ccc = (CreateContractCommand)target;
        checkMandatoryElements(ccc, errors, true);
        checkElements(ccc, errors);
    }
    
    /**
     * Check whether mandatory elements are valid : label (not empty), dates
     * and users when needed
     * @param ccc
     * @param errors
     * @param checkUsers 
     */
    private void checkMandatoryElements(
            CreateContractCommand ccc, 
            Errors errors, 
            boolean checkUsers) {
        boolean hasMandatoryElementWrong = false;

        if (!checkContractLabel(ccc, errors)) {
            hasMandatoryElementWrong = true;
        }
        
        if (checkUsers) {
            if (!checkUsers(ccc, errors)) {
                errors.rejectValue(USER_LIST_KEY, EMPTY_USER_LIST_KEY);
                hasMandatoryElementWrong = true;
            }
        }
        
        if (!checkDates(ccc, errors)) {
            hasMandatoryElementWrong = true;
        }
        
        if(hasMandatoryElementWrong) { // if no URL is filled-in
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    MANDATORY_FIELD_MSG_BUNDLE_KEY);
        }
    }
    
    /**
     * Check whether contract Url and other options are valid
     * @param ccc
     * @param errors 
     */
    private void checkElements(
            CreateContractCommand ccc, 
            Errors errors) {
        
        checkContractUrl(ccc, errors);
        
        ContractOptionFormField coff;
        for (Map.Entry<String, String> entry : ccc.getOptionMap().entrySet()) {
            coff = contractOptionFormFieldMap.get(entry.getKey());
            try {
                if (!StringUtils.isEmpty(entry.getValue()) && 
                    !coff.getFormField().checkParameters(entry.getValue())){
                    // the auditParameter[] key is due to object mapping of the form
                    // management of Spring mvc. Each field is mapped with a method
                    // of the mapped object. In this case, the returned object of the method
                    // is a map.
                    errors.rejectValue("optionMap["+entry.getKey()+"]", coff.getFormField().getErrorI18nKey());
                }
            }catch (NumberFormatException nfe) {
                errors.rejectValue("optionMap["+entry.getKey()+"]", coff.getFormField().getErrorI18nKey());
            }
        }
    }
    
    /**
     *
     * @param userSubscriptionCommand
     * @param errors
     * @return
     */
    private boolean checkContractLabel(CreateContractCommand createContractCommand, Errors errors) {
        if (StringUtils.isEmpty(createContractCommand.getLabel())) {
            errors.rejectValue(CONTRACT_LABEL_KEY, EMPTY_LABEL_KEY);
            return false;
        }
        return true;
    }
    
    /**
     *
     * @param userSubscriptionCommand
     * @param errors
     * @return
     */
    private boolean checkContractUrl(CreateContractCommand createContractCommand, Errors errors) {
        String url = createContractCommand.getContractUrl().trim();
        if (StringUtils.isBlank(url)) {
            return true;
        }
        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator (schemes, UrlValidator.ALLOW_2_SLASHES);
        if (!urlValidator.isValid(url)) {
            errors.rejectValue(CONTRACT_URL_KEY, INVALID_URL_KEY);
            return false;
        }
        return true;
    }
    
    /**
     *
     * @param userSubscriptionCommand
     * @param errors
     * @return
     */
    private boolean checkDates(CreateContractCommand createContractCommand, Errors errors) {
        Date beginDate = createContractCommand.getBeginDate();
        Date endDate = createContractCommand.getEndDate();
        if (beginDate == null) {
            errors.rejectValue(BEGIN_DATE_KEY, EMPTY_BEGIN_DATE_KEY);
            return false;
        }
        if (endDate == null) {
            errors.rejectValue(END_DATE_KEY, EMPTY_END_DATE_KEY);
            return false;
        }
        if (endDate.before(beginDate) || endDate.equals(beginDate)) {
            errors.rejectValue(BEGIN_DATE_KEY, END_DATE_ANTERIOR_TO_BEGIN_KEY);
            return false;
        }
        return true;
    }

    @Override
    public boolean supports(Class clazz) {
        return CreateContractCommand.class.isAssignableFrom(clazz);
    }

    /**
     *
     * @param userSubscriptionCommand
     * @param errors
     * @return
     */
    private boolean checkUsers(CreateContractCommand createContractCommand, Errors errors) {
        Collection<User> userList = createContractCommand.getUserList();
        if (userList == null || userList.isEmpty()) {
            return false;
        }
        boolean hasAtLeastOneRealUser = false;
        for (User user : userList) {
            if (user != null) {
                hasAtLeastOneRealUser = true;
            }
        }
        return hasAtLeastOneRealUser;
    }

}