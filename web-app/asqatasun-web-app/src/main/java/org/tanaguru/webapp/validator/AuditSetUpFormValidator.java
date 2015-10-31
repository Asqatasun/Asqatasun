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
package org.tanaguru.webapp.validator;

import java.util.*;
import org.apache.log4j.Logger;
import org.tanaguru.webapp.command.AuditSetUpCommand;
import org.tanaguru.webapp.entity.service.contract.ContractDataService;
import org.tanaguru.webapp.form.parameterization.AuditSetUpFormField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author jkowalczyk
 */
public class AuditSetUpFormValidator implements Validator {

    protected static final Logger LOGGER = Logger.getLogger(AuditSetUpFormValidator.class);
    protected static final String GENERAL_ERROR_MSG_KEY = "generalErrorMsg";
    protected static final String MANDATORY_FIELD_MSG_BUNDLE_KEY =
            "required.mandatoryField";

    private final ContractDataService contractDataService;
    public ContractDataService getContractDataService() {
        return contractDataService;
    }

    @Autowired
    public AuditSetUpFormValidator(ContractDataService contractDataService) {
        this.contractDataService = contractDataService;
    }

    private final Map<String, AuditSetUpFormField> auditSetUpFormFieldMap = new HashMap();
    public void setAuditSetUpFormFieldMap(Map<String, List<AuditSetUpFormField>> auditSetUpFormFieldMapSortedByParamType) {
        for (Map.Entry<String, List<AuditSetUpFormField>> entry : auditSetUpFormFieldMapSortedByParamType.entrySet()) {
            for (AuditSetUpFormField asuff : entry.getValue()){
                auditSetUpFormFieldMap.put(asuff.getParameterElement().getParameterElementCode(), asuff);
            }
        }
    }

    /**
     * The AuditSetUpFormValidator checks whether the options values are 
     * acceptable regarding the FormField internal checker
     * 
     * @param target
     * @param errors 
     */
    @Override
    public void validate(Object target, Errors errors) {
        AuditSetUpCommand auditSetUpCommand = (AuditSetUpCommand)target;
        AuditSetUpFormField asuff;
        for (Map.Entry<String, String> entry : auditSetUpCommand.getAuditParameter().entrySet()) {
            asuff = auditSetUpFormFieldMap.get(entry.getKey());
            try {
                if (!asuff.getFormField().checkParameters(entry.getValue())){
                    // the auditParameter[] key is due to object mapping of the form
                    // management of Spring mvc. Each field is mapped with a method
                    // of the mapped object. In this case, the returned object of the method
                    // is a map.
                    errors.rejectValue("auditParameter["+entry.getKey()+"]", asuff.getFormField().getErrorI18nKey());
                }
            }catch (NumberFormatException nfe) {
                errors.rejectValue("auditParameter["+entry.getKey()+"]", asuff.getFormField().getErrorI18nKey());
            }
        }
    }

    @Override
    public boolean supports(Class clazz) {
        return AuditSetUpCommand.class.isAssignableFrom(clazz);
    }

    /**
     * This methods checks whether the selected level is allowed
     * 
     * @param auditSetUpCommand
     * @param errors 
     * @param authorisedReferentialList
     */
    public void validateLevel(
            AuditSetUpCommand auditSetUpCommand, 
            Errors errors, 
            Collection<String> authorisedReferentialList) {
        String referential = auditSetUpCommand.getLevel().split(";")[0];
        if (!authorisedReferentialList.contains(referential)) {
            errors.rejectValue("level", "level-error");
        }
    }

}