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


import com.sebuilder.interpreter.IO;
import com.sebuilder.interpreter.factory.ScriptFactory.SuiteException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MimeTypes;
import org.json.JSONException;
import org.tanaguru.webapp.command.AddScenarioCommand;
import org.tanaguru.webapp.command.AuditSetUpCommand;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.scenario.Scenario;
import org.tanaguru.webapp.entity.service.contract.ContractDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author jkowalczyk
 */
public class AddScenarioFormValidator implements Validator {

    protected static final Logger LOGGER = Logger.getLogger(AddScenarioFormValidator.class);
    protected static final String GENERAL_ERROR_MSG_KEY = "generalErrorMsg";
    protected static final String MANDATORY_FIELD_MSG_BUNDLE_KEY =
            "scenarioManagement.mandatoryFieldOnError";
    private static final String SCENARIO_FILE_KEY = "scenarioFile";
    private static final String SCENARIO_LABEL_KEY = "scenarioLabel";
    private static final String FILE_SIZE_EXCEEDED_MSG_BUNDLE_KEY =
            "scenarioManagement.fileSizeExceeded";
    private static final String NOT_SCENARIO_MSG_BUNDLE_KEY =
            "scenarioManagement.notScenarioFileFound";
    private static final String NO_SCENARIO_UPLOADED_MSG_BUNDLE_KEY = 
            "scenarioManagement.noScenarioUploaded";
    private static final String NO_SCENARIO_LABEL_MSG_BUNDLE_KEY = 
            "scenarioManagement.noScenarioLabel";
    private static final String SCENARIO_LABEL_EXISTS_MSG_BUNDLE_KEY = 
            "scenarioManagement.scenarioLabelExists";
    private static final String INVALID_SCENARIO_MSG_BUNDLE_KEY = 
            "scenarioManagement.invalidScenario";
            
    public List<String> authorizedMimeType = new ArrayList();
    public List<String> getAuthorizedMimeType() {
        return authorizedMimeType;
    }

    public void setAuthorizedMimeType(List<String> authorizedMimeType) {
        this.authorizedMimeType = authorizedMimeType;
    }
    
    // Default = 2MB
    private long maxFileSize=2097152;
    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }
    
    private final ContractDataService contractDataService;
    public ContractDataService getContractDataService() {
        return contractDataService;
    }

    @Autowired
    public AddScenarioFormValidator(ContractDataService contractDataService) {
        this.contractDataService = contractDataService;
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
        AddScenarioCommand addScenarioCommand = (AddScenarioCommand)target;
        if (checkScenarioLabel(addScenarioCommand, errors)) {
            if (checkScenarioFileTypeAndSize(addScenarioCommand, errors)) {
                checkScenarioFileValidity(addScenarioCommand, errors);
            }
        }
    }

    /**
     * 
     * @param addScenarioCommand
     * @param errors 
     * @return  whether the scenario handled by the current AddScenarioCommand
     * has a well-formed label
     */
    public boolean checkScenarioLabel(
            AddScenarioCommand addScenarioCommand, 
            Errors errors) {
        if(StringUtils.isEmpty(addScenarioCommand.getScenarioLabel())) { // if no label set
            LOGGER.debug("empty Scenario Label");
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    MANDATORY_FIELD_MSG_BUNDLE_KEY);
            errors.rejectValue(SCENARIO_LABEL_KEY,
                    NO_SCENARIO_LABEL_MSG_BUNDLE_KEY);
            return false;
        }
        Contract contract = contractDataService.read(addScenarioCommand.getContractId());
        Set<String> scenarioLabelSet = new HashSet();
        for (Scenario scenario :contract.getScenarioSet()) {
            scenarioLabelSet.add(scenario.getLabel());
        }
        if (scenarioLabelSet.contains(addScenarioCommand.getScenarioLabel())) {
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    MANDATORY_FIELD_MSG_BUNDLE_KEY);
            errors.rejectValue(SCENARIO_LABEL_KEY,
                    SCENARIO_LABEL_EXISTS_MSG_BUNDLE_KEY);
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param addScenarioCommand
     * @param errors 
     */
    public void checkScenarioFileValidity(
            AddScenarioCommand addScenarioCommand, 
            Errors errors) {
        try {
            IO.read(addScenarioCommand.getScenarioContent());
        } catch (JSONException | IOException | SuiteException je) {
            errors.rejectValue(SCENARIO_FILE_KEY, INVALID_SCENARIO_MSG_BUNDLE_KEY);
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    MANDATORY_FIELD_MSG_BUNDLE_KEY);
        }
    }
    
    /**
     * 
     * @param addScenarioCommand
     * @param errors 
     * @return  whether the scenario handled by the current AddScenarioCommand
     * has a correct type and size
     */
    public boolean checkScenarioFileTypeAndSize(
            AddScenarioCommand addScenarioCommand, 
            Errors errors) {
        if (addScenarioCommand.getScenarioFile() == null) { // if no file uploaded
            LOGGER.debug("empty Scenario File");
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    MANDATORY_FIELD_MSG_BUNDLE_KEY);
            errors.rejectValue(SCENARIO_FILE_KEY,
                    NO_SCENARIO_UPLOADED_MSG_BUNDLE_KEY);
            return false;
        }
        Metadata metadata = new Metadata();
        MimeTypes mimeTypes = TikaConfig.getDefaultConfig().getMimeRepository();
        String mime;
        try {
            CommonsMultipartFile cmf = addScenarioCommand.getScenarioFile();
            if (cmf.getSize() > maxFileSize) {
                Long maxFileSizeInMega = maxFileSize / 1000000;
                String[] arg = {maxFileSizeInMega.toString()};
                errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                        MANDATORY_FIELD_MSG_BUNDLE_KEY);
                errors.rejectValue(SCENARIO_FILE_KEY, FILE_SIZE_EXCEEDED_MSG_BUNDLE_KEY, arg, "{0}");
                return false;
            } else if (cmf.getSize() > 0) {
                mime = mimeTypes.detect(new BufferedInputStream(cmf.getInputStream()), metadata).toString();
                LOGGER.debug("mime  " + mime + "  " + cmf.getOriginalFilename());
                if (!authorizedMimeType.contains(mime)) {
                    errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                        MANDATORY_FIELD_MSG_BUNDLE_KEY);
                    errors.rejectValue(SCENARIO_FILE_KEY, NOT_SCENARIO_MSG_BUNDLE_KEY);
                    return false;
                }
            } else {
                LOGGER.debug("File with size null");
                errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    MANDATORY_FIELD_MSG_BUNDLE_KEY);
                errors.rejectValue(SCENARIO_FILE_KEY,
                    NO_SCENARIO_UPLOADED_MSG_BUNDLE_KEY);
                return false;
            }
        } catch (IOException ex) {
            LOGGER.warn(ex);
            errors.rejectValue(SCENARIO_FILE_KEY, NOT_SCENARIO_MSG_BUNDLE_KEY);
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    MANDATORY_FIELD_MSG_BUNDLE_KEY);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean supports(Class clazz) {
        return AuditSetUpCommand.class.isAssignableFrom(clazz);
    }

}