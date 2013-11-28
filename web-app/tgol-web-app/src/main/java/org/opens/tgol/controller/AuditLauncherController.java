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

import java.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.service.parameterization.ParameterElementDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tgol.command.AuditSetUpCommand;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ScopeEnum;
import org.opens.tgol.entity.option.OptionElement;
import org.opens.tgol.entity.service.option.OptionElementDataService;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.exception.KrashAuditException;
import org.opens.tgol.exception.LostInSpaceException;
import org.opens.tgol.orchestrator.TanaguruOrchestrator;
import org.opens.tgol.util.HttpStatusCodeFamily;
import org.opens.tgol.util.TgolKeyStore;
import org.opens.tgol.util.webapp.ExposablePropertyPlaceholderConfigurer;
import org.opens.tgol.voter.restriction.RestrictionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class AuditLauncherController extends AuditDataHandlerController {

    private static final Logger LOGGER = Logger.getLogger(AuditLauncherController.class);
    private static final String LEVEL_PARAM_KEY="LEVEL";
    private static final String PROXY_HOST_PARAM_KEY="PROXY_HOST";
    private static final String PROXY_PORT_PARAM_KEY="PROXY_PORT";
    private static final String DEPTH_PARAM_KEY="DEPTH";
    private static final String MAX_DOCUMENT_PARAM_KEY="MAX_DOCUMENTS";
    private static final String MAX_DURATION_PARAM_KEY="MAX_DURATION";
    private static final String EXCLUSION_URL_LIST_PARAM_KEY="EXCLUSION_REGEXP";
    private static final String DEPTH_PAGE_PARAM_VALUE="0";
    private static final String PROXY_HOST_CONF_KEY="proxyHost";
    private static final String PROXY_PORT_CONF_KEY="proxyPort";
    private static final String PROXY_EXCLUSION_URL_CONF_KEY="proxyExclusionUrl";
    private static final String EMAIL_SENT_TO_USER_EXCLUSION_CONF_KEY="emailSentToUserExclusionList";
            
    private String groupePagesName = "";
    /**
     * The TanaguruOrchestrator instance needed to launch the audit process
     */
    private TanaguruOrchestrator tanaguruExecutor;

    @Autowired
    public final void setTanaguruExecutor(TanaguruOrchestrator tanaguruExecutor) {
        this.tanaguruExecutor = tanaguruExecutor;
    }
    /**
     * The RestrictionHandler instance needed to decide 
     */
    private RestrictionHandler restrictionHandler;

    @Autowired
    public final void setRestrictionHandler(RestrictionHandler restrictionHandler) {
        this.restrictionHandler = restrictionHandler;
    }
    /**
     * The ParameterElementDataService instance
     */
    private ParameterElementDataService parameterElementDataService;

    @Autowired
    public void setParameterElementDataService(ParameterElementDataService parameterElementDataService) {
        this.parameterElementDataService = parameterElementDataService;
        setParameterElementMap(parameterElementDataService);
    }
    private Map<String, ParameterElement> parameterElementMap = new HashMap<String, ParameterElement>();

    private void setParameterElementMap(ParameterElementDataService peds) {
        for (ParameterElement pe : peds.findAll()) {
            parameterElementMap.put(pe.getParameterElementCode(), pe);
        }
    }
    /**
     * default audit page parameter set.
     */
    Set<Parameter> auditPageParamSet = null;

    private ExposablePropertyPlaceholderConfigurer exposablePropertyPlaceholderConfigurer;
    @Autowired
    public final void setExposablePropertyPlaceholderConfigurer(ExposablePropertyPlaceholderConfigurer exposablePropertyPlaceholderConfigurer) {
        this.exposablePropertyPlaceholderConfigurer = exposablePropertyPlaceholderConfigurer;
    }
    
    private String httpProxyHost;
    /**
     * Direct call to the property place holder configurer due to exposition
     * context
     * 
     * @return 
     */
    public String getHttpProxyHost() {
        if (httpProxyHost == null) {
            httpProxyHost = exposablePropertyPlaceholderConfigurer.getResolvedProps().get(PROXY_HOST_CONF_KEY);
        }
        return httpProxyHost;
    }

    private String httpProxyPort;
    /**
     * Direct call to the property place holder configurer due to exposition
     * context
     * 
     * @return 
     */
    public String getHttpProxyPort() {
        if (httpProxyPort == null) {
            httpProxyPort = exposablePropertyPlaceholderConfigurer.getResolvedProps().get(PROXY_PORT_CONF_KEY);
        }
        return httpProxyPort;
    }

    /**
     * Multiple Url can be set through a unique String separated by ;
     */
    private List<String> proxyExclusionUrlList;
    /**
     * Direct call to the property place holder configurer due to exposition
     * context
     * 
     * @return 
     */
    public List<String> getProxyExclusionUrlList() {
        if (proxyExclusionUrlList == null) {
            String rawList = exposablePropertyPlaceholderConfigurer.getResolvedProps().get(PROXY_EXCLUSION_URL_CONF_KEY);
            proxyExclusionUrlList = new ArrayList<String>();
            proxyExclusionUrlList.addAll(Arrays.asList(rawList.split(";")));
        }
        return proxyExclusionUrlList;
    }

    /**
     * 
     */
    private List<String> emailSentToUserExclusionList;
    /**
     * Direct call to the property place holder configurer due to exposition
     * context
     * 
     * @return 
     */
    public List<String> getEmailSentToUserExclusionList() {
        if (emailSentToUserExclusionList == null) {
            String rawList = exposablePropertyPlaceholderConfigurer.getResolvedProps().get(EMAIL_SENT_TO_USER_EXCLUSION_CONF_KEY);
            emailSentToUserExclusionList = new ArrayList<String>();
            emailSentToUserExclusionList.addAll(Arrays.asList(rawList.split(";")));
        }
        return emailSentToUserExclusionList;
    }
    
    /**
     * The user options that have to be converted as audit parameters
     */
    private List<String> userOption;
    public List<String> getUserOption() {
        return userOption;
    }

    public void setUserOption(List<String> userOption) {
        this.userOption = userOption;
    }

    /**
     * The user options that have to be converted as audit parameters and 
     * that depend on the selected referential
     */
    private List<String> userOptionDependingOnReferential;
    public List<String> getUserOptionDependingOnReferential() {
        return userOptionDependingOnReferential;
    }

    public void setUserOptionDependingOnReferential(List<String> userOptionDependingOnReferential) {
        this.userOptionDependingOnReferential = userOptionDependingOnReferential;
    }
    
    private OptionElementDataService optionElementDataService;
    public OptionElementDataService getOptionElementDataService() {
        return optionElementDataService;
    }
    @Autowired
    public void setOptionElementDataService(OptionElementDataService optionElementDataService) {
        this.optionElementDataService = optionElementDataService;
    }
    
    public AuditLauncherController() {
        super();
    }

    /**
     * This methods enables an authenticated user to launch an audit.
     * @param contractId
     * @param locale
     * @param model
     * @return
     */
    public String launchAudit(
            final AuditSetUpCommand auditSetUpCommand,
            final Locale locale,
            Model model) {
        Contract contract = getContractDataService().read(auditSetUpCommand.getContractId());
        if (isContractExpired(contract)) {
            return displayContractView(contract, model);
        } else {
            // before launching the audit, we check whether any restriction on the
            //contract forbids it.
            String checkResult = restrictionHandler.checkRestriction(contract, getClientIpAddress());
            if (!checkResult.equalsIgnoreCase(TgolKeyStore.ACT_ALLOWED)) {
                return checkResult;
            }
            ScopeEnum auditScope = auditSetUpCommand.getScope();
            if (auditScope.equals(ScopeEnum.PAGE) ||
                    auditScope.equals(ScopeEnum.FILE)) {
                return preparePageAudit(auditSetUpCommand, contract, locale, auditScope,model);
            }
            String url = getContractDataService().getUrlFromContractOption(contract);
            if (auditScope.equals(ScopeEnum.DOMAIN)) {
                tanaguruExecutor.auditSite(
                    contract,
                    url,
                    getClientIpAddress(),
                    getUserParamSet(auditSetUpCommand, contract.getId(),-1,url),
                    locale
                    );
                model.addAttribute(TgolKeyStore.TESTED_URL_KEY, url);
            } else if (auditScope.equals(ScopeEnum.SCENARIO)) {
                tanaguruExecutor.auditScenario(
                    contract,
                    auditSetUpCommand.getScenarioId(),
                    getClientIpAddress(),
                    getUserParamSet(auditSetUpCommand, contract.getId(),-1,url),
                    locale
                    );
                model.addAttribute(TgolKeyStore.SCENARIO_NAME_KEY, auditSetUpCommand.getScenarioName());
                model.addAttribute(TgolKeyStore.SCENARIO_ID_KEY, auditSetUpCommand.getScenarioId());
            }
            model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
            model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
            return TgolKeyStore.AUDIT_IN_PROGRESS_VIEW_NAME;
        }
    }

    /**
     * This methods controls the validity of the form and launch an audit with
     * values populated by the user. In case of audit failure, an appropriate
     * message is displayed
     * @param pageAuditSetUpCommand
     * @param contract
     * @param locale
     * @param auditScope
     * @param model
     * @return
     */
    private String preparePageAudit(
            final AuditSetUpCommand auditSetUpCommand,
            final Contract contract,
            final Locale locale,
            final ScopeEnum auditScope,
            Model model) {
        Audit audit;
        boolean isPageAudit = true;
        // if the form is correct, we launch the audit
        try {
            if (auditScope.equals(ScopeEnum.FILE)) {
                audit = launchUploadAudit(contract, auditSetUpCommand, locale);
                isPageAudit = false;
            } else {
                audit = launchPageAudit(contract,auditSetUpCommand, locale);
            }
        } catch (KrashAuditException kae) {
            return TgolKeyStore.OUPS_VIEW_NAME;
        }
        // if the audit lasted more than expected, we return a "audit in progress"
        // page and send an email when it's ready
        if (audit == null) {
            model.addAttribute(TgolKeyStore.TESTED_URL_KEY, auditSetUpCommand.getUrlList().get(0));
            model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
            model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
            model.addAttribute(TgolKeyStore.IS_PAGE_AUDIT_KEY, isPageAudit);
            if (!getEmailSentToUserExclusionList().contains(contract.getUser().getEmail1())) {
                model.addAttribute(TgolKeyStore.IS_USER_NOTIFIED_KEY, true);
            }
            return TgolKeyStore.GREEDY_AUDIT_VIEW_NAME;
        }
        if (audit.getStatus() != AuditStatus.COMPLETED) {
            return prepareFailedAuditData(audit,model);
        }
        if (audit.getSubject() instanceof Site) {
            // in case of group of page, we display the list of audited pages
            model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, audit.getId());
            model.addAttribute(TgolKeyStore.STATUS_KEY, HttpStatusCodeFamily.f2xx);
            return TgolKeyStore.PAGE_LIST_XXX_VIEW_REDIRECT_NAME;
        } else if (audit.getSubject() instanceof Page) {
            model.addAttribute(TgolKeyStore.WEBRESOURCE_ID_KEY, audit.getSubject().getId());
            return TgolKeyStore.RESULT_PAGE_VIEW_REDIRECT_NAME;
        }
        throw new LostInSpaceException(getCurrentUser());
    }

    /**
     * This method launches the audit process using the tanaguru orchestrator
     * bean
     * @param contract
     * @param auditSetUpCommand
     * @param locale
     * @return
     */
    private Audit launchPageAudit(
            final Contract contract,
            final AuditSetUpCommand auditSetUpCommand,
            final Locale locale) {
        int urlCounter = 0;
        // 10 String are received from the form even if these String are empty.
        // We sort the string and only keep the not empty ones.
        List<String> trueUrl = new ArrayList<String>();
        for (String str : auditSetUpCommand.getUrlList()) {
            if (StringUtils.isNotBlank(str)) {
                trueUrl.add(urlCounter, str.trim());
                urlCounter++;
            }
        }
        Set<Parameter> paramSet = getUserParamSet(auditSetUpCommand, contract.getId(), trueUrl.size(), trueUrl.get(0));
        if (trueUrl.size() == 1) {
            LOGGER.debug("Launch " + trueUrl.get(0) + " audit in page mode");
            return tanaguruExecutor.auditPage(
                    contract,
                    trueUrl.get(0),
                    getClientIpAddress(),
                    paramSet, 
                    locale);
        } else if (trueUrl.size() > 1) {
            String[] finalUrlTab = new String[trueUrl.size()];
            for (int i = 0; i < trueUrl.size(); i++) {
                finalUrlTab[i] = trueUrl.get(i);
            }
            groupePagesName = extractGroupNameFromUrl(finalUrlTab[0]);
            LOGGER.debug("Launch " + groupePagesName + " audit in group of pages mode");
            return tanaguruExecutor.auditSite(
                    contract,
                    groupePagesName,
                    trueUrl,
                    getClientIpAddress(),
                    paramSet, 
                    locale);
        } else {
            return null;
        }
    }

    /**
     * 
     * @param contract
     * @param auditSetUpCommand
     * @param locale
     * @return 
     */
    private Audit launchUploadAudit(
            final Contract contract,
            final AuditSetUpCommand auditSetUpCommand, 
            final Locale locale) {
        
        Map<String, String> fileMap = auditSetUpCommand.getFileMap();

        return tanaguruExecutor.auditPageUpload(
                contract,
                fileMap,
                getClientIpAddress(),
                getUserParamSet(auditSetUpCommand, contract.getId(), fileMap.size(), null),
                locale);
    }

    /**
     * This methods extracts the name of a group of pages from an url
     * @param url
     * @return
     */
    private String extractGroupNameFromUrl(String url) {
        int fromIndex;
        if (url.startsWith(TgolKeyStore.HTTP_PREFIX)) {
            fromIndex = TgolKeyStore.HTTP_PREFIX.length();
        } else if (url.startsWith(TgolKeyStore.HTTPS_PREFIX)) {
            fromIndex = TgolKeyStore.HTTPS_PREFIX.length();
        } else {
            url = TgolKeyStore.HTTP_PREFIX + url;
            fromIndex = TgolKeyStore.HTTP_PREFIX.length();
        }
        if (url.indexOf(TgolKeyStore.SLASH_CHAR, fromIndex) != -1) {
            return url.substring(0, url.indexOf(TgolKeyStore.SLASH_CHAR, fromIndex));
        } else {
            return url;
        }
    }

    /**
     * This method gets the default parameters for an audit and eventually
     * override some of them in case of contract restriction.
     * @param auditSetUpCommand
     * @param contractId
     * @param nbOfPages
     * @param url
     * @return
     */
    private Set<Parameter> getUserParamSet(AuditSetUpCommand auditSetUpCommand, Long contractId, int nbOfPages, String url) {
        Set<Parameter> paramSet;
        Set<Parameter> userParamSet = new HashSet<Parameter>();
        if (auditSetUpCommand != null) {
            // The default parameter set corresponds to a site audit
            // If the launched audit is of any other type, we retrieve another 
            // parameter set 
            if (auditSetUpCommand.getScope().equals(ScopeEnum.SCENARIO)) {
                paramSet = getAuditScenarioParameterSet();
            } else if (!auditSetUpCommand.getScope().equals(ScopeEnum.DOMAIN)) {
                paramSet = getAuditPageParameterSet(nbOfPages);
            } else {
                paramSet = getDefaultParamSet();
            }
            for (Map.Entry<String, String> entry : auditSetUpCommand.getAuditParameter().entrySet()) {
                Parameter param = getParameterDataService().getParameter(parameterElementMap.get(entry.getKey()), entry.getValue());
                userParamSet.add(param);
            }
            paramSet = getParameterDataService().updateParameterSet(paramSet, userParamSet);
            paramSet = setLevelParameter(paramSet, auditSetUpCommand.getLevel());
        } else {
            paramSet = getDefaultParamSet();
            Collection<OptionElement> optionElementSet =
                    getContractDataService().read(contractId).getOptionElementSet();
            for (Parameter param : paramSet) {
                for (OptionElement optionElement : optionElementSet) {
                    if (optionElement.getOption().getCode().
                            equalsIgnoreCase(param.getParameterElement().getParameterElementCode())) {
                        param = getParameterDataService().getParameter(param.getParameterElement(), optionElement.getValue());
                        break;
                    }
                }
                userParamSet.add(param);
            }
            paramSet = getParameterDataService().updateParameterSet(paramSet, userParamSet);
        }
        paramSet = setProxyParameters(paramSet, url);
        paramSet = setUserParameters(paramSet, auditSetUpCommand.getLevel().split(";")[0]);
        return paramSet;
    }

    /**
     * 
     * @param paramSet
     * @param url
     * @return 
     */
    private Set<Parameter> setLevelParameter(Set<Parameter> paramSet, String level) {
        ParameterElement levelParameterElement =
                parameterElementDataService.getParameterElement(LEVEL_PARAM_KEY);
        Parameter levelParameter = getParameterDataService().getParameter(levelParameterElement, level);
        paramSet = getParameterDataService().updateParameter(paramSet, levelParameter);
        return paramSet;
    }
    
    /**
     * 
     * @param paramSet
     * @param url
     */
    private Set<Parameter> setProxyParameters(Set<Parameter> paramSet, String url) {
        if ((StringUtils.isEmpty(getHttpProxyHost()) && StringUtils.isEmpty(getHttpProxyPort()))) {
            return paramSet;
        }
        for (String urlExclusion : getProxyExclusionUrlList()) {
            if (StringUtils.isNotEmpty(urlExclusion) && url.contains(urlExclusion)) {
                return paramSet;
            }
        }
        if (StringUtils.isEmpty(url)){
            return paramSet;
        }
        try {
            Integer.valueOf(getHttpProxyPort());
        } catch (NumberFormatException nfe) {
            LOGGER.warn("Incorrect value of proxy Port : "+ getHttpProxyPort() +". Proxy parameters are ignored");
            return paramSet;
        }
        Set<Parameter> proxyParamSet = new HashSet<Parameter>();
        ParameterElement proxyHostParameterElement =
                parameterElementDataService.getParameterElement(PROXY_HOST_PARAM_KEY);
        ParameterElement proxyPortParameterElement =
                parameterElementDataService.getParameterElement(PROXY_PORT_PARAM_KEY);
        Parameter proxyHostParameter = getParameterDataService().getParameter(proxyHostParameterElement, getHttpProxyHost());
        Parameter proxyPortParameter = getParameterDataService().getParameter(proxyPortParameterElement, getHttpProxyPort());
        proxyParamSet.add(proxyHostParameter);
        proxyParamSet.add(proxyPortParameter);
        LOGGER.debug("paramSet.size() " + paramSet.size());
        LOGGER.debug("proxyParamSet " + proxyParamSet.size());
        for (Parameter param: paramSet) {
            if (param.getParameterElement().getParameterElementCode().equals(PROXY_HOST_PARAM_KEY)) {
                LOGGER.debug(param.getValue());
            } else if (param.getParameterElement().getParameterElementCode().equals(PROXY_PORT_PARAM_KEY)) {
                LOGGER.debug(param.getValue());
            }
        }
        paramSet = getParameterDataService().updateParameterSet(paramSet, proxyParamSet);
        LOGGER.debug("after update paramSet.size() " + paramSet.size());
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Audit is set to use proxy with parameters " + getHttpProxyHost() + " : " + getHttpProxyPort());
        }
        for (Parameter param: paramSet) {
            if (param.getParameterElement().getParameterElementCode().equals(PROXY_HOST_PARAM_KEY)) {
                LOGGER.debug(param.getValue());
            } else if (param.getParameterElement().getParameterElementCode().equals(PROXY_PORT_PARAM_KEY)) {
                LOGGER.debug(param.getValue());
            } 
        }
        return paramSet;
    }

    /**
     * The default parameter set embeds a depth value that corresponds to the
     * site audit. We need here to replace this parameter by a parameter value
     * equals to 0.
     * @return
     */
    private Set<Parameter> getAuditPageParameterSet(int nbOfPages) {
        if (auditPageParamSet == null) {
            Set<Parameter> paramSet = new HashSet<Parameter>();
            ParameterElement depthParameterElement = parameterElementDataService.getParameterElement(DEPTH_PARAM_KEY);
            ParameterElement maxDocParameterElement = parameterElementDataService.getParameterElement(MAX_DOCUMENT_PARAM_KEY);
            Parameter depthParameter = getParameterDataService().getParameter(depthParameterElement, DEPTH_PAGE_PARAM_VALUE);
            Parameter maxDocParameter = getParameterDataService().getParameter(maxDocParameterElement, String.valueOf(nbOfPages));
            paramSet.add(depthParameter);
            paramSet.add(maxDocParameter);
            auditPageParamSet = getParameterDataService().updateParameterSet(getDefaultParamSet(), paramSet);
        }
        return auditPageParamSet;
    }
    
    /**
     * The default parameter set embeds a depth value that corresponds to the
     * site audit. We need here to replace this parameter by a parameter value
     * equals to 0.
     * @return
     */
    private Set<Parameter> getAuditScenarioParameterSet() {
        Set<Parameter> scenarioParamSet = getDefaultParamSet();
        Set<Parameter> parameterToRemove = new HashSet<Parameter>();
        for (Parameter param : scenarioParamSet) {
            if (StringUtils.equals(param.getParameterElement().getParameterElementCode(), DEPTH_PARAM_KEY) ||
                    StringUtils.equals(param.getParameterElement().getParameterElementCode(), MAX_DOCUMENT_PARAM_KEY) ||
                    StringUtils.equals(param.getParameterElement().getParameterElementCode(), MAX_DURATION_PARAM_KEY) ||
                    StringUtils.equals(param.getParameterElement().getParameterElementCode(), EXCLUSION_URL_LIST_PARAM_KEY) ) {
                parameterToRemove.add(param);
            }
        }
        scenarioParamSet.removeAll(parameterToRemove);
        return scenarioParamSet;
    }

    /**
     * Some user options have to be converted as parameters and added to the
     * general audit parameters.
     * 
     * @param paramSet
     * @param referentialKey
     * @return 
     */
    private Set<Parameter> setUserParameters(Set<Parameter> paramSet, String referentialKey) {
        User user = getCurrentUser();
        Collection<OptionElement> optionElementSet = new HashSet<OptionElement>();
        for (String optionFamily : userOptionDependingOnReferential) {
            optionElementSet.addAll(optionElementDataService.getOptionElementFromUserAndFamilyCode(user, referentialKey+"_"+optionFamily));
        }
        for (String optionFamily : userOption) {
            optionElementSet.addAll(optionElementDataService.getOptionElementFromUserAndFamilyCode(user, optionFamily));
        }
        paramSet.addAll(getParameterDataService().getParameterSetFromOptionElementSet(optionElementSet));
        return paramSet;
    }
    
}
