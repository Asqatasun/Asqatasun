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

import org.opens.tgol.command.AuditSetUpCommand;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.service.contract.ContractDataService;
import org.opens.tgol.form.parameterization.AuditSetUpFormField;
import org.opens.tgol.util.TgolKeyStore;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MimeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author jkowalczyk
 */
public class AuditSetUpFormValidator implements Validator {

    private static final Logger LOGGER = Logger.getLogger(AuditSetUpFormValidator.class);
    private static final String GENERAL_ERROR_MSG_KEY = "generalErrorMsg";
    private static final String MANDATORY_FIELD_MSG_BUNDLE_KEY =
            "required.mandatoryField";
    private static final String NO_FILE_UPLOADED_MSG_BUNDLE_KEY =
            "required.noFileUploaded";
    private static final String URL_ON_DIFFERENT_DOMAIN_MSG_BUNDLE_KEY =
            "required.filledInUrlsWithDifferentDomain";
    private static final String NOT_ON_SAME_DOMAIN_MSG_BUNDLE_KEY =
            "required.notOnSameDomainUrl";
    private static final String FILE_SIZE_EXCEEDED_MSG_BUNDLE_KEY =
            "required.fileSizeExceeded";
    private static final String NOT_HTML_MSG_BUNDLE_KEY =
            "required.notHtmlFileFound";
    private static final String NOT_ON_CONTRACT_MSG_BUNDLE_KEY =
            "required.notOnContractUrl";
    public static final String ID_INPUT_PREFIX = "urlList";
    public static final String ID_INPUT_FILE_PREFIX = "fileInputList";
    public static final String URL_GENERAL_ERROR = "urlGeneralError";
    public static final String PARAMETER_GENERAL_ERROR = "parameterGeneralError";

    // Default = 2MB
    private long maxFileSize=2097152;
    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    private ContractDataService contractDataService;
    public ContractDataService getContractDataService() {
        return contractDataService;
    }

    @Autowired
    public AuditSetUpFormValidator(ContractDataService contractDataService) {
        this.contractDataService = contractDataService;
    }

    private Map<String, AuditSetUpFormField> auditSetUpFormFieldMap =
            new HashMap<String,AuditSetUpFormField>();
    public void setAuditSetUpFormFieldMap(Map<String, List<AuditSetUpFormField>> auditSetUpFormFieldMapSortedByParamType) {
        for (Map.Entry<String, List<AuditSetUpFormField>> entry : auditSetUpFormFieldMapSortedByParamType.entrySet()) {
            for (AuditSetUpFormField asuff : entry.getValue()){
                auditSetUpFormFieldMap.put(asuff.getParameterElement().getParameterElementCode(), asuff);
            }
        }
    }

    public List<String> authorizedMimeType = new ArrayList<String>();
    public List<String> getAuthorizedMimeType() {
        return authorizedMimeType;
    }

    public void setAuthorizedMimeType(List<String> authorizedMimeType) {
        this.authorizedMimeType = authorizedMimeType;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuditSetUpCommand auditSetUpCommand = (AuditSetUpCommand)target;
        if (!auditSetUpCommand.isAuditSite()) {
            if (!auditSetUpCommand.getUrlList().isEmpty()) {
                validateUrl(auditSetUpCommand, errors);
            } else if (auditSetUpCommand.getFileInputList().length > 0) {
                validateFiles(auditSetUpCommand, errors);
            }
        }
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

    /**
     * Control whether the uploaded files are of HTML type and whether their
     * size is under the maxFileSize limit.
     *
     * @param auditSetUpCommand
     * @param errors
     */
    public void validateFiles(AuditSetUpCommand auditSetUpCommand, Errors errors) {
        boolean emptyFile = true;
        Metadata metadata = new Metadata();
        MimeTypes mimeTypes = TikaConfig.getDefaultConfig().getMimeRepository();
        String mime = null;

        for (int i=0;i<auditSetUpCommand.getFileInputList().length;i++ ) {
            try {
                CommonsMultipartFile cmf = auditSetUpCommand.getFileInputList()[i];
                if (cmf.getSize() > maxFileSize) {
                    Long maxFileSizeInMega = maxFileSize / 1000000;
                    String[] arg = {maxFileSizeInMega.toString()};
                    errors.rejectValue(ID_INPUT_FILE_PREFIX + "[" + i + "]", FILE_SIZE_EXCEEDED_MSG_BUNDLE_KEY, arg, "{0}");
                }
                if (cmf.getSize() > 0) {
                    emptyFile = false;
                    mime = mimeTypes.detect(new BufferedInputStream(cmf.getInputStream()), metadata).toString();
                    LOGGER.debug("mime  " + mime + "  " +cmf.getOriginalFilename());
                    if (!authorizedMimeType.contains(mime)) {
                        errors.rejectValue(ID_INPUT_FILE_PREFIX + "[" + i + "]", NOT_HTML_MSG_BUNDLE_KEY);
                    }
                }
            } catch (IOException ex) {
                LOGGER.warn(ex);
                errors.rejectValue(ID_INPUT_FILE_PREFIX + "[" + i + "]", NOT_HTML_MSG_BUNDLE_KEY);
            }
        }
        if(emptyFile) { // if no file is uploaded
            LOGGER.debug("emptyFiles");
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    NO_FILE_UPLOADED_MSG_BUNDLE_KEY);
            return;
        }
    }
    
    public void validateUrl(AuditSetUpCommand auditSetUpCommand, Errors errors) {
        boolean onContractUrl = false;
        String contractUrl = "";
        Contract contract = contractDataService.read(auditSetUpCommand.getContractId());
        // if the user is a guest user, no restriction is applied on the URL he
        // can fills-in
        if (StringUtils.equalsIgnoreCase(TgolKeyStore.ROLE_GUEST_KEY, contract.getUser().getRole().getRoleName())) {
            onContractUrl = true;
        } else {
            contractUrl = extractDomainFromUrl(contractDataService.read(
                auditSetUpCommand.getContractId()).getUrl());
        }
        boolean emptyUrl = true;
        String domainOfFirstUrlEncountered = "";
        Set<Integer> urlWithProblemIndexSet = new HashSet<Integer>();
        // We parse all the URL filled-in by the user and extract the ones that
        // don't match with the domain of the first encountered URL.
        int index=0;
        for (String url : auditSetUpCommand.getUrlList()) {
            if (url != null && !url.isEmpty()){
                url = url.trim();
                url = extractDomainFromUrl(url);
                emptyUrl = false;
                if (domainOfFirstUrlEncountered.isEmpty()) {
                    domainOfFirstUrlEncountered = url;
                } else if (!url.equalsIgnoreCase(domainOfFirstUrlEncountered)){
                    urlWithProblemIndexSet.add(index);
                }
                if (!onContractUrl &&
                        (StringUtils.containsIgnoreCase(url, contractUrl) || contractUrl.isEmpty())) {
                    onContractUrl = true;
                }
            }
            index++;
        }

        if(emptyUrl) { // if no URL is filled-in
            LOGGER.debug("emptyUrl");
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    MANDATORY_FIELD_MSG_BUNDLE_KEY);
        } else if(!onContractUrl) { // if the URL is not allowed (not on the contract for authenticated users
            LOGGER.debug("notOnContract");
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    NOT_ON_CONTRACT_MSG_BUNDLE_KEY);
        } else if (!urlWithProblemIndexSet.isEmpty()) { // if some URLs are not on the right domain
            LOGGER.debug("notOnSameDomain");
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    URL_ON_DIFFERENT_DOMAIN_MSG_BUNDLE_KEY);
            for (Integer urlIndex : urlWithProblemIndexSet) {
                String[] arg = {domainOfFirstUrlEncountered};
                errors.rejectValue(
                        ID_INPUT_PREFIX+"["+urlIndex+"]",
                        NOT_ON_SAME_DOMAIN_MSG_BUNDLE_KEY,
                        arg,
                        "{0}");
            }
        }
    }

    /**
     * This methods extracts the name of a group of pages from an url
     * @param url
     * @return
     */
    public String extractDomainFromUrl(String url) {
        int fromIndex = 0;
        if (url.startsWith(TgolKeyStore.HTTP_PREFIX)) {
            fromIndex = TgolKeyStore.HTTP_PREFIX.length();
        } else if (url.startsWith(TgolKeyStore.HTTPS_PREFIX)) {
            fromIndex = TgolKeyStore.HTTPS_PREFIX.length();
        }
        if (url.indexOf(TgolKeyStore.SLASH_CHAR, fromIndex) != -1) {
            return url.substring(fromIndex, url.indexOf(TgolKeyStore.SLASH_CHAR, fromIndex));
        } else {
            return url.substring(fromIndex);
        }
    }

    @Override
    public boolean supports(Class clazz) {
        return AuditSetUpCommand.class.isAssignableFrom(clazz);
    }

}