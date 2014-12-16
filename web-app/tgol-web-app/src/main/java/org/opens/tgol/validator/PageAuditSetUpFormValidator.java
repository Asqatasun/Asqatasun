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

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.opens.tgol.command.AuditSetUpCommand;
import org.opens.tgol.entity.service.contract.ContractDataService;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

/**
 *
 * @author jkowalczyk
 */
public class PageAuditSetUpFormValidator extends AuditSetUpFormValidator {

    private static final String URL_ON_DIFFERENT_DOMAIN_MSG_BUNDLE_KEY =
            "required.filledInUrlsWithDifferentDomain";
    private static final String NOT_ON_SAME_DOMAIN_MSG_BUNDLE_KEY =
            "required.notOnSameDomainUrl";
    private static final String NOT_ON_CONTRACT_MSG_BUNDLE_KEY =
            "required.notOnContractUrl";
    private static final String FILE_NOT_ALLOWED_HERE_MSG_BUNDLE_KEY =
            "required.fileAuditNotAllowed";
    private static final String ID_INPUT_PREFIX = "urlList";
    
    @Autowired
    public PageAuditSetUpFormValidator(ContractDataService contractDataService) {
        super(contractDataService);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuditSetUpCommand pageAuditSetUpCommand = (AuditSetUpCommand)target;
        validateUrl(pageAuditSetUpCommand, errors);
        super.validate(target, errors);
    }

    /**
     * Check whether the Urls filled-in by the user are correct.
     * 1- They have to be all on the same domain. 
     * 2- When the contract has on option of type DOMAIN, they have to match 
     * to the value of the option
     * 
     * @param pageAuditSetUpCommand
     * @param errors 
     */
    private void validateUrl(AuditSetUpCommand pageAuditSetUpCommand, Errors errors) {
        
        boolean onContractUrl = false;
        
        String contractUrl =  getContractDataService()
                                  .getUrlFromContractOption(
                                          getContractDataService()
                                                .read(pageAuditSetUpCommand.getContractId()));
        
        boolean emptyUrl = true;
        String domainOfFirstUrlEncountered = "";
        Set<Integer> urlWithProblemIndexSet = new HashSet();
        Set<Integer> urlWithFilePrefixSet = new HashSet();
        
        // We parse all the URL filled-in by the user and extract the ones that
        // don't match with the domain of the first encountered URL.
        int index=0;
        for (String url : pageAuditSetUpCommand.getUrlList()) {
            if (url != null && !url.isEmpty()){
                
                emptyUrl = false;
                
                // first we sanitize the url and extract the domain
                url = url.trim();
                
                if (hasUrlFilePrefix(url)) {
                    urlWithFilePrefixSet.add(index);
                } else {
                    url = extractDomainFromUrl(url);

                    // we store the url if it's the first one.
                    if (domainOfFirstUrlEncountered.isEmpty()) {
                        domainOfFirstUrlEncountered = url;

                    // if the domain of this url is different from the one of the 
                    // first encountered domain, we store its index.
                    } else if (!url.equalsIgnoreCase(domainOfFirstUrlEncountered)){
                        urlWithProblemIndexSet.add(index);
                    }
                    if (!onContractUrl &&
                            (StringUtils.containsIgnoreCase(contractUrl, url) || contractUrl.isEmpty())) {
                        onContractUrl = true;
                    }
                }
            }
            index++;
        }

        if(emptyUrl) { // if no URL is filled-in
            LOGGER.debug("emptyUrl");
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    MANDATORY_FIELD_MSG_BUNDLE_KEY);
        } else if (!urlWithFilePrefixSet.isEmpty()) { // if some URLs are not on the right domain
            LOGGER.debug("fileAuditNotAllowed");
            errors.rejectValue(GENERAL_ERROR_MSG_KEY,
                    FILE_NOT_ALLOWED_HERE_MSG_BUNDLE_KEY);
            for (Integer urlIndex : urlWithFilePrefixSet) {
                errors.rejectValue(
                        ID_INPUT_PREFIX+"["+urlIndex+"]",
                        FILE_NOT_ALLOWED_HERE_MSG_BUNDLE_KEY);
            }
        } else if(!onContractUrl) { // if the URL is not allowed (not on the contract for authenticated users)
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
    
    /**
     * @param url 
     * @return whether the current Url starts with a file prefix
     */
    public boolean hasUrlFilePrefix(String url) {
        return StringUtils.startsWith(url, TgolKeyStore.FILE_PREFIX);
    }

    @Override
    public boolean supports(Class clazz) {
        return AuditSetUpCommand.class.isAssignableFrom(clazz);
    }

}