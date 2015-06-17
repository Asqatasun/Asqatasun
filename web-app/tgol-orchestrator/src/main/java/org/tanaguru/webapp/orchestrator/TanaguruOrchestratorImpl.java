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
package org.tanaguru.webapp.orchestrator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.tanaguru.emailsender.EmailSender;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.AuditStatus;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.entity.service.audit.AuditDataService;
import org.tanaguru.service.AuditService;
import org.tanaguru.service.AuditServiceListener;
import org.tanaguru.webapp.entity.contract.*;
import org.tanaguru.webapp.entity.factory.contract.ActFactory;
import org.tanaguru.webapp.entity.scenario.Scenario;
import org.tanaguru.webapp.entity.service.contract.ActDataService;
import org.tanaguru.webapp.entity.service.contract.ScopeDataService;
import org.tanaguru.webapp.entity.service.scenario.ScenarioDataService;
import org.tanaguru.webapp.exception.KrashAuditException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author jkowalczyk
 */
public class TanaguruOrchestratorImpl implements TanaguruOrchestrator {

    private static final Logger LOGGER = Logger.getLogger(TanaguruOrchestratorImpl.class);
    private final AuditService auditService;
    private final ActDataService actDataService;
    private final AuditDataService auditDataService;
    private final ScenarioDataService scenarioDataService;
    private final ActFactory actFactory;
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private final Map<ScopeEnum, Scope> scopeMap = new EnumMap<>(ScopeEnum.class);
    private void initializeScopeMap(ScopeDataService ScopeDataService) {
        for (Scope scope : ScopeDataService.findAll()) {
            scopeMap.put(scope.getCode(), scope);
        }
    }
    
    private final EmailSender emailSender;
    /*
     * keys to send the user an email at the end of an audit.
     */
    private static final String RECIPIENT_KEY =  "recipient";
    private static final String SUCCESS_SUBJECT_KEY =  "success-subject";
    private static final String ERROR_SUBJECT_KEY =  "error-subject";
    private static final String KRASH_SUBJECT_KEY =  "krash-subject";
    private static final String KRASH_ADMIN_SUBJECT_KEY =  "krash-admin-subject";
    private static final String URL_TO_REPLACE =  "#webresourceUrl";
    private static final String AUDIT_URL_TO_REPLACE =  "#unknown";
    private static final String EXCEPTION_TO_REPLACE =  "#exception";
    private static final String PROJECT_NAME_TO_REPLACE =  "#projectName";
    private static final String USER_EMAIL_TO_REPLACE =  "#emailUser";
    private static final String PROJECT_URL_TO_REPLACE =  "#projectUrl";
    private static final String HOST_TO_REPLACE =  "#host";
    private static final String SUCCESS_MSG_CONTENT_KEY =  "success-content";
    private static final String SITE_ERROR_MSG_CONTENT_KEY =  "site-error-content";
    private static final String PAGE_ERROR_MSG_CONTENT_KEY =  "page-error-content";
    private static final String KRASH_MSG_CONTENT_KEY =  "krash-content";
    private static final String KRASH_ADMIN_MSG_CONTENT_KEY =  "krash-admin-content";
    private static final String BUNDLE_NAME = "i18n/email-content-I18N";
    private static final int DEFAULT_AUDIT_DELAY = 30000;

    private String webappUrl;
    public String getWebappUrl() {
        return webappUrl;
    }

    public void setWebappUrl(String webappUrl) {
        this.webappUrl = webappUrl;
    }
    
    private String pageResultUrlSuffix;
    public String getPageResultUrlSuffix() {
        return pageResultUrlSuffix;
    }

    public void setPageResultUrlSuffix(String pageResultUrlSuffix) {
        this.pageResultUrlSuffix = pageResultUrlSuffix;
    }
    
    private String contractUrlSuffix;
    public String getContractUrlSuffix() {
        return contractUrlSuffix;
    }

    public void setContractUrlSuffix(String contractUrlSuffix) {
        this.contractUrlSuffix = contractUrlSuffix;
    }
    
    private int delay = DEFAULT_AUDIT_DELAY;
    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    private final List<String> emailSentToUserExclusionList = new ArrayList<>();
    public void setEmailSentToUserExclusionRawList(String emailSentToUserExclusionRawList) {
        this.emailSentToUserExclusionList.addAll(Arrays.asList(emailSentToUserExclusionRawList.split(";")));
    }
    
    private final List<String> krashReportMailList = new ArrayList<>();
    public void setKrashReportMailList(String krashReportMailList) {
        this.krashReportMailList.addAll(Arrays.asList(krashReportMailList.split(";")));
    }
    
    private boolean isAllowedToSendKrashReport = true;
    public void setIsAllowedToSendKrashReport(boolean isAllowedToSendKrashReport) {
        this.isAllowedToSendKrashReport = isAllowedToSendKrashReport;
    }

    @Autowired
    public TanaguruOrchestratorImpl(
            AuditService auditService,
            AuditDataService auditDataService,
            ActDataService actDataService,
            ActFactory actFactory,
            ScopeDataService scopeDataService,
            ScenarioDataService scenarioDataService, 
            ThreadPoolTaskExecutor threadPoolTaskExecutor,
            EmailSender emailSender) {
        this.auditService = auditService;
        this.actDataService = actDataService;
        this.auditDataService = auditDataService;
        this.actFactory = actFactory;
        this.scenarioDataService = scenarioDataService;
        initializeScopeMap(scopeDataService);
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.emailSender = emailSender;
    }

    @Override
    public Audit auditPage(
            Contract contract,
            String pageUrl,
            String clientIp,
            Set<Parameter> parameterSet, 
            Locale locale) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Page audit on " + pageUrl);
            for (Parameter param : parameterSet) {
                LOGGER.debug("param " + param.getValue() + " "+
                        param.getParameterElement().getParameterElementCode());
            }
        }
        Act act = createAct(contract, ScopeEnum.PAGE, clientIp);
        AuditTimeoutThread auditPageThread =
                new AuditPageThread(
                    pageUrl, 
                    auditService, 
                    act, 
                    parameterSet, 
                    locale,
                    delay);
        Audit audit = submitAuditAndLaunch(auditPageThread, act);
        return audit;
    }

    @Override
    public Audit auditPageUpload(
            Contract contract,
            Map<String, String> fileMap,
            String clientIp,
            Set<Parameter> parameterSet, 
            Locale locale) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("auditPage Upload on " + fileMap.size() + " files");
            for (Parameter param : parameterSet) {
                LOGGER.debug("param " + param.getValue() + " "+
                        param.getParameterElement().getParameterElementCode());
            }
        }
        Act act;
        if (fileMap.size()>1) {
            act = createAct(contract, ScopeEnum.GROUPOFFILES, clientIp);
        } else {
            act = createAct(contract, ScopeEnum.FILE, clientIp);
        }
        AuditTimeoutThread auditPageUploadThread =
                new AuditPageUploadThread(
                    fileMap, 
                    auditService, 
                    act, 
                    parameterSet, 
                    locale,
                    delay);
        Audit audit = submitAuditAndLaunch(auditPageUploadThread, act);
        return audit;
    }

    @Override
    public void auditSite(
            Contract contract,
            String siteUrl,
            String clientIp,
            Set<Parameter> parameterSet, 
            Locale locale) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Site audit on " + siteUrl);
            for (Parameter param : parameterSet) {
                LOGGER.debug("param " + param.getValue() + " "+
                        param.getParameterElement().getParameterElementCode());
            }
        }
        Act act = createAct(contract, ScopeEnum.DOMAIN, clientIp);
        AuditThread auditSiteThread =
                new AuditSiteThread(
                    siteUrl,
                    auditService,
                    act,
                    parameterSet, 
                    locale);
        threadPoolTaskExecutor.submit(auditSiteThread);
    }
    
    @Override
    public void auditScenario(
            Contract contract,
            Long idScenario,
            String clientIp,
            Set<Parameter> parameterSet, 
            Locale locale) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Scenarion audit on scenario with id" + idScenario);
            for (Parameter param : parameterSet) {
                LOGGER.debug("param " + param.getValue() + " "+
                        param.getParameterElement().getParameterElementCode());
            }
        }
        Act act = createAct(contract, ScopeEnum.SCENARIO, clientIp);
        Scenario scenario = scenarioDataService.read(idScenario);
        AuditThread auditScenarioThread =
                new AuditScenarioThread(
                    scenario.getLabel(),
                    scenario.getContent(),
                    auditService,
                    act,
                    parameterSet, 
                    locale);
        threadPoolTaskExecutor.submit(auditScenarioThread);
    }

    @Override
    public Audit auditSite(
            Contract contract,
            String siteUrl,
            final List<String> pageUrlList,
            String clientIp,
            Set<Parameter> parameterSet, 
            Locale locale) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Group of pages audit");
            for (String str :pageUrlList) {
                LOGGER.debug("pageUrl " + str);
            }
            for (Parameter param : parameterSet) {
                LOGGER.debug("param " + param.getValue() + " "+
                        param.getParameterElement().getParameterElementCode());
            }
        }
        Act act = createAct(contract, ScopeEnum.GROUPOFPAGES, clientIp);
        AuditTimeoutThread auditPageThread = 
                new AuditGroupOfPagesThread(
                    siteUrl, 
                    pageUrlList, 
                    auditService, 
                    act, 
                    parameterSet, 
                    locale,
                    delay);
        Audit audit = submitAuditAndLaunch(auditPageThread, act);
        return audit;
    }

    /**
     * 
     * @param auditTimeoutThread
     * @param act
     * @return 
     */
    private Audit submitAuditAndLaunch(AuditTimeoutThread auditTimeoutThread, Act act) {
        synchronized (auditTimeoutThread) {
            Future submitedThread = threadPoolTaskExecutor.submit(auditTimeoutThread);
            while (submitedThread!=null && !submitedThread.isDone()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    LOGGER.error("", ex);
                }
                if (auditTimeoutThread.isDurationExceedsDelay()) {
                    LOGGER.debug("Audit Duration ExceedsDelay. The audit result "
                            + "is now managed in an asynchronous way.");
                    break;
                }
            }
            if (null != auditTimeoutThread.getException()) {
                LOGGER.error("new KrashAuditException()");
                throw new KrashAuditException(auditTimeoutThread.getException());
            }
            return auditTimeoutThread.getAudit();
        }
    }

    /**
     * Send an email when an audit terminates
     * 
     * @param act
     * @param locale
     * @param exception 
     */
    private void sendAuditResultEmail(Act act, Locale locale) {
        String emailTo = act.getContract().getUser().getEmail1();
        if (this.emailSentToUserExclusionList.contains(emailTo)) {
            LOGGER.info("Email not set cause user " + emailTo + " belongs to "
                    + "exlusion list");
            return;
        }
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        String emailFrom = bundle.getString(RECIPIENT_KEY);
        Set<String> emailToSet = new HashSet<>();
        emailToSet.add(emailTo);
        if (act.getStatus().equals(ActStatus.COMPLETED)) {
            sendSuccessfulMessageOnActTerminated(act, bundle, emailFrom, emailToSet, getProjectNameFromAct(act));
        } else if (act.getStatus().equals(ActStatus.ERROR)) {
            sendFailureMessageOnActTerminated(act, bundle, emailFrom, emailToSet, getProjectNameFromAct(act));
        }
     }

    /**
     * 
     * @param act
     * @param locale
     * @param exception 
     */
    private void sendKrashAuditEmail(Act act, Locale locale, Exception exception) {
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        String emailFrom = bundle.getString(RECIPIENT_KEY);
        String projectName = getProjectNameFromAct(act);
        
        if (isAllowedToSendKrashReport && CollectionUtils.isNotEmpty(krashReportMailList)) {
            Set<String> emailToSet = new HashSet<>();
            emailToSet.addAll(krashReportMailList);
            String host ="";
            try { 
                host = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException uhe) {}

            String msgSubject = bundle.getString(KRASH_ADMIN_SUBJECT_KEY);
            msgSubject = StringUtils.replace(msgSubject, PROJECT_NAME_TO_REPLACE, projectName);
            msgSubject = StringUtils.replace(msgSubject, HOST_TO_REPLACE, host);
            
            String msgContent = bundle.getString(KRASH_ADMIN_MSG_CONTENT_KEY);
            msgContent = StringUtils.replace(msgContent, PROJECT_NAME_TO_REPLACE, projectName);
            msgContent = StringUtils.replace(msgContent, USER_EMAIL_TO_REPLACE, act.getContract().getUser().getEmail1());
            msgContent = StringUtils.replace(msgContent, HOST_TO_REPLACE, host);
            msgContent = StringUtils.replace(msgContent, EXCEPTION_TO_REPLACE, ExceptionUtils.getStackTrace(exception));
            if (act.getAudit().getSubject() != null) {
                msgContent = StringUtils.replace(msgContent, AUDIT_URL_TO_REPLACE, act.getAudit().getSubject().getURL());
            }
            LOGGER.info("krash email sent to "+krashReportMailList + " on audit n° " + act.getAudit().getId());
            sendEmail(emailFrom, emailToSet, msgSubject, msgContent);
        }

        String emailTo = act.getContract().getUser().getEmail1();
        if (this.emailSentToUserExclusionList.contains(emailTo)) {
            LOGGER.info("Email not set cause user " + emailTo + " belongs to "
                    + "exlusion list");
            return;
        }
        Set<String> emailToSet = new HashSet<>();
        emailToSet.add(emailTo);
        String msgSubject = bundle.getString(KRASH_SUBJECT_KEY);
        msgSubject = StringUtils.replace(msgSubject, PROJECT_NAME_TO_REPLACE, projectName);
        String msgContent = bundle.getString(KRASH_MSG_CONTENT_KEY);
        msgContent = StringUtils.replace(msgContent, PROJECT_NAME_TO_REPLACE, projectName);
        msgContent = StringUtils.replace(msgContent, PROJECT_URL_TO_REPLACE, buildContractUrl(act.getContract()));
        LOGGER.info("krash email sent to [" + emailTo+"]" + " on audit n° " + act.getAudit().getId());
        sendEmail(emailFrom, emailToSet, msgSubject, msgContent);
    }
    
    /**
     * 
     * @param act
     * @param bundle
     * @param emailFrom
     * @param emailTo
     * @param projectName 
     */
    private void sendSuccessfulMessageOnActTerminated(
            Act act,
            ResourceBundle bundle, 
            String emailFrom, 
            Set<String> emailTo, 
            String projectName) {
        
        String emailSubject = bundle.getString(SUCCESS_SUBJECT_KEY).
                replaceAll(PROJECT_NAME_TO_REPLACE, projectName);
        
        String messageContent = bundle.getString(SUCCESS_MSG_CONTENT_KEY);
        messageContent = messageContent.replaceAll(URL_TO_REPLACE, buildResultUrl(act));
        messageContent = messageContent.replaceAll(PROJECT_NAME_TO_REPLACE, projectName);
        
        LOGGER.info("success email sent to " + emailTo+ " on audit n° " + act.getAudit().getId());
        sendEmail(emailFrom, emailTo, emailSubject, messageContent);
    }

    /**
     * 
     * @param act
     * @param bundle
     * @return
     */
    private void sendFailureMessageOnActTerminated(
            Act act, 
            ResourceBundle bundle, 
            String emailFrom, 
            Set<String> emailTo,
            String projectName) {
        String emailSubject = bundle.getString(ERROR_SUBJECT_KEY).replaceAll(PROJECT_NAME_TO_REPLACE, projectName);
        String messageContent;
        if (act.getScope().getCode().equals(ScopeEnum.DOMAIN)) {
            messageContent = bundle.getString(SITE_ERROR_MSG_CONTENT_KEY);
        } else {
            messageContent = bundle.getString(PAGE_ERROR_MSG_CONTENT_KEY);
        }
        messageContent = messageContent.replaceAll(PROJECT_URL_TO_REPLACE, buildContractUrl(act.getContract()));
        messageContent = messageContent.replaceAll(URL_TO_REPLACE, buildResultUrl(act));
        messageContent = messageContent.replaceAll(PROJECT_NAME_TO_REPLACE, projectName);
        LOGGER.info("failure email sent to " + emailTo + " on audit n° " + act.getAudit().getId());
        sendEmail(emailFrom, emailTo, emailSubject, messageContent);
    }
    
    /**
     * 
     * @param emailFrom
     * @param emailToSet
     * @param emailSubject
     * @param emailMessage 
     */
    private void sendEmail(
            String emailFrom, 
            Set<String> emailToSet, 
            String emailSubject, 
            String emailMessage) {
        emailSender.sendEmail(
                emailFrom, 
                emailToSet, 
                Collections.EMPTY_SET, 
                StringUtils.EMPTY,
                emailSubject, 
                emailMessage);
    }
    
    /**
     * 
     * @param act
     * @return 
     */
    private String buildResultUrl (Act act) {
        StringBuilder strb = new StringBuilder();
        strb.append(webappUrl);
        strb.append(pageResultUrlSuffix);
        strb.append(act.getAudit().getId());
        return strb.toString();
    }
    
    /**
     * 
     * @param act
     * @return 
     */
    private String buildContractUrl (Contract contract) {
        StringBuilder strb = new StringBuilder();
        strb.append(webappUrl);
        strb.append(contractUrlSuffix);
        strb.append(contract.getId());
        return strb.toString();
    }
    
    /**
     * This method initializes an act instance and persists it.
     * @param contract
     * @param scope
     * @return
     */
    private Act createAct(Contract contract, ScopeEnum scope, String clientIp) {
        Date beginDate = new Date();
        Act act = actFactory.createAct(beginDate, contract);
        act.setStatus(ActStatus.RUNNING);
        act.setScope(scopeMap.get(scope));
        act.setClientIp(clientIp);
        actDataService.saveOrUpdate(act);
        return act;
    }
    
    /**
     * 
     * @param act
     * @return 
     */
    private String getProjectNameFromAct(Act act) {
        StringBuilder projectName = new StringBuilder();
        projectName.append(act.getContract().getLabel());
        if (act.getScope().getCode().equals(ScopeEnum.SCENARIO)) {
            projectName.append(" - ");
            // the name of the scenario is persisted on engine's side as the URL
            // of the parent WebResource
            projectName.append(act.getAudit().getSubject().getURL());
        }
        return projectName.toString();
    }
    
    /**
     * 
     */
    private abstract class AuditThread implements Runnable, AuditServiceListener {

        private AuditService auditService;
        public AuditService getAuditService() {
            return auditService;
        }

        public void setAuditService(AuditService auditService) {
            this.auditService = auditService;
        }
        
        private Set<Parameter> parameterSet = new HashSet<>();
        public Set<Parameter> getParameterSet() {
            return parameterSet;
        }

        public void setParameterSet(Set<Parameter> parameterSet) {
            this.parameterSet = parameterSet;
        }
        
        private Act currentAct;
        public Act getCurrentAct() {
            return currentAct;
        }

        public void setCurrentAct(Act currentAct) {
            this.currentAct = currentAct;
        }
        
        private Map<Audit, Long> auditExecutionList = new ConcurrentHashMap<>();
        public Map<Audit, Long> getAuditExecutionList() {
            return auditExecutionList;
        }

        public void setAuditExecutionList(Map<Audit, Long> auditExecutionList) {
            this.auditExecutionList = auditExecutionList;
        }
        
        private Map<Long, Audit> auditCompletedList = new ConcurrentHashMap<>();
        public Map<Long, Audit> getAuditCompletedList() {
            return auditCompletedList;
        }

        public void setAuditCompletedList(Map<Long, Audit> auditCompletedList) {
            this.auditCompletedList = auditCompletedList;
        }
        
        private Map<Long, AbstractMap.SimpleImmutableEntry<Audit, Exception>> auditCrashedList = new HashMap<>();
        public Map<Long, SimpleImmutableEntry<Audit, Exception>> getAuditCrashedList() {
            return auditCrashedList;
        }

        public void setAuditCrashedList(Map<Long, SimpleImmutableEntry<Audit, Exception>> auditCrashedList) {
            this.auditCrashedList = auditCrashedList;
        }

        private Date startDate;
        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }
        
        private Audit audit = null;
        public Audit getAudit() {
            return audit;
        }

        public void setAudit(Audit audit) {
            this.audit = audit;
        }
        
        private Locale locale = null;
        public Locale getLocale() {
            return locale;
        }

        public void setLocale(Locale locale) {
            this.locale = locale;
        }
        
        private Exception exception = null;
        public Exception getException() {
            return exception;
        }

        public void setException(Exception exception) {
            this.exception = exception;
        }
        
        public AuditThread(
                AuditService auditService,
                Act act,
                Set<Parameter> parameterSet, 
                Locale locale) {
            if (parameterSet != null) {
                this.parameterSet.addAll(parameterSet);
            }
            this.auditService = auditService;
            this.currentAct = act;
            this.locale = locale;
            startDate = act.getBeginDate();
        }
        
        @Override
        public void run() {
            this.getAuditService().add(this);
            Audit currentAudit = launchAudit();
            this.getCurrentAct().setAudit(currentAudit);
            actDataService.saveOrUpdate(this.getCurrentAct());
            currentAudit = this.waitForAuditToComplete(currentAudit);
            this.getAuditService().remove(this);
            onActTerminated(this.getCurrentAct(), currentAudit);
        }
        
        /**
         * 
         * @return 
         */
        public abstract Audit launchAudit();
        
        @Override
        public void auditCompleted(Audit audit) {
            LOGGER.debug("AUDIT COMPLETED:" + audit + "," + audit.getSubject().getURL() + "," + (long) (audit.getDateOfCreation().getTime() / 1000) + audit.getId());
            Audit auditCompleted = null;
            for (Audit auditRunning : this.auditExecutionList.keySet()) {
                if (auditRunning.getId().equals(audit.getId())
                        && (long) (auditRunning.getDateOfCreation().getTime() / 1000) == (long) (audit.getDateOfCreation().getTime() / 1000)) {
                    auditCompleted = auditRunning;
                    break;
                }
            }
            if (auditCompleted != null) {
                Long token = this.auditExecutionList.get(auditCompleted);
                this.auditExecutionList.remove(auditCompleted);
                this.auditCompletedList.put(token, audit);
            }
        }

        @Override
        public void auditCrashed(Audit audit, Exception exception) {
            String url = "";
            if (audit.getSubject() != null) {
                url = audit.getSubject().getURL();
            }
            LOGGER.error("AUDIT CRASHED:" + audit + "," + url + "," + (long) (audit.getDateOfCreation().getTime() / 1000), exception);
            Audit auditCrashed = null;
            for (Audit auditRunning : this.auditExecutionList.keySet()) {
                if (auditRunning.getId().equals(audit.getId())
                        && (long) (auditRunning.getDateOfCreation().getTime() / 1000) == (long) (audit.getDateOfCreation().getTime() / 1000)) {
                    auditCrashed = auditRunning;
                    break;
                }
            }
            if (auditCrashed != null) {
                Long token = this.auditExecutionList.get(auditCrashed);
                this.auditExecutionList.remove(auditCrashed);
                this.auditCrashedList.put(token, new AbstractMap.SimpleImmutableEntry<>(audit, exception));
                this.exception = exception;
            }
        }

        protected Audit waitForAuditToComplete(Audit audit) {
            LOGGER.debug("WAIT FOR AUDIT TO COMPLETE:" + audit + "," + (long) (audit.getDateOfCreation().getTime() / 1000));
            Long token = new Date().getTime();
            this.getAuditExecutionList().put(audit, token);
            // while the audit is not seen as completed or crashed
            while (!this.getAuditCompletedList().containsKey(token) && !this.getAuditCrashedList().containsKey(token)) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    LOGGER.error("", ex);
                }
            }
            if ((audit = this.getAuditCompletedList().get(token)) != null) {
                this.getAuditCompletedList().remove(token);
                return audit;
            }
            if ((audit = this.getAuditCrashedList().get(token).getKey()) != null) {
                this.getAuditCrashedList().remove(token);
                return audit;
            }
            return null;
        }
        
        protected void onActTerminated(Act act, Audit audit) {
            this.audit = audit;
            Date endDate = new Date();
            act.setEndDate(endDate);
            if (audit.getStatus().equals(AuditStatus.COMPLETED)) {
                act.setStatus(ActStatus.COMPLETED);
            } else {
                act.setStatus(ActStatus.ERROR);
            }
            act = actDataService.saveOrUpdate(act);
            if (exception != null) {
                sendKrashAuditEmail(act, locale, exception);
                actDataService.delete(act.getId());
                auditDataService.delete(audit.getId());
                this.audit = null;
            }
        }
    }
    
    /**
     * Inner class in charge of launching a site audit in a thread. At the end 
     * the audit, an email has to be sent to the user with the audit info.
     */
    private class AuditSiteThread extends AuditThread {

        private final String siteUrl;
        
        public AuditSiteThread(
                String siteUrl,
                AuditService auditService,
                Act act,
                Set<Parameter> parameterSet, 
                Locale locale) {
            super(auditService, act, parameterSet, locale);
            this.siteUrl = siteUrl;
            LOGGER.info("Launching audit site on " + this.siteUrl);
        }

        @Override
        public Audit launchAudit() {
            Audit audit = this.getAuditService().auditSite(
                        this.siteUrl,
                        this.getParameterSet());
            return audit;
        }
        
        @Override
        protected void onActTerminated(Act act, Audit audit) {
            super.onActTerminated(act, audit);
            if (getException() == null) {
                sendAuditResultEmail(act, getLocale());
            }
            LOGGER.info("Audit site terminated on " + this.siteUrl);
        }

    }
    
    /**
     * Inner class in charge of launching a site audit in a thread. At the end 
     * the audit, an email has to be sent to the user with the audit info.
     */
    private class AuditScenarioThread extends AuditThread {

        private final String scenarioName;
        private final String scenario;
        
        public AuditScenarioThread(
                String scenarioName,
                String scenario,
                AuditService auditService,
                Act act,
                Set<Parameter> parameterSet, 
                Locale locale) {
            super(auditService, act, parameterSet, locale);
            this.scenario = scenario;
            this.scenarioName = scenarioName;
            LOGGER.info("Launching audit scenario " + this.scenarioName);
        }

        @Override
        public Audit launchAudit() {
            Audit audit = this.getAuditService().auditScenario(
                        this.scenarioName,
                        this.scenario,
                        this.getParameterSet());
            return audit;
        }
        
        @Override
        protected void onActTerminated(Act act, Audit audit) {
            super.onActTerminated(act, audit);
            if (getException() == null) {
                sendAuditResultEmail(act, getLocale());
            }
            LOGGER.info("Audit scenario terminated on " + this.scenarioName);
        }

    }

    /**
     * Abstract Inner class in charge of launching an audit in a thread. This 
     * thread has to expose the current audit as an attribute. If the
     * audit is not terminated in a given delay, this attribute returns null and
     * an email has to be sent to inform the user.
     */
    private abstract class AuditTimeoutThread extends AuditThread {
        
        private boolean isAuditTerminatedAfterTimeout = false;
        public boolean isAuditTerminatedAfterTimeout() {
            return isAuditTerminatedAfterTimeout;
        }

        public void setAuditTerminatedAfterTimeout(boolean isAuditTerminatedBeforeTimeout) {
            this.isAuditTerminatedAfterTimeout = isAuditTerminatedBeforeTimeout;
        }
        
        private int delay = DEFAULT_AUDIT_DELAY;
        
        public AuditTimeoutThread (
                AuditService auditService,
                Act act,
                Set<Parameter> parameterSet, 
                Locale locale,
                int delay) {
            super(auditService, act, parameterSet, locale);
            this.delay = delay;
        }

        @Override
        protected void onActTerminated(Act act, Audit audit) {
            super.onActTerminated(act, audit);
            if (isAuditTerminatedAfterTimeout() && getException() == null) {
                sendAuditResultEmail(act, getLocale());
            }
        }

        public boolean isDurationExceedsDelay() {
            long currentDuration = new Date().getTime() - getStartDate().getTime();
            if (currentDuration > delay) {
                LOGGER.info("Audit Duration has exceeded synchronous delay " + delay);
                isAuditTerminatedAfterTimeout = true;
                return true;
            }
            return false;
        }

    }
    
    /**
     * 
     */
    private class AuditGroupOfPagesThread extends AuditTimeoutThread {

        private final String siteUrl;
        private final List<String> pageUrlList = new ArrayList<>();
        
        public AuditGroupOfPagesThread(
                String siteUrl,
                List<String> pageUrlList,
                AuditService auditService,
                Act act,
                Set<Parameter> parameterSet, 
                Locale locale,
                int delay) {
            super(auditService, act, parameterSet, locale, delay);
            this.siteUrl = siteUrl;
            if (pageUrlList != null) {
                this.pageUrlList.addAll(pageUrlList);
            }
            LOGGER.info("Launching audit group of Pages on " + this.pageUrlList);
        }

        @Override
        public Audit launchAudit() {
            Audit audit = null;
            if (!this.pageUrlList.isEmpty()) {
                audit = this.getAuditService().auditSite(
                        this.siteUrl,
                        this.pageUrlList,
                        this.getParameterSet());
            }
            return audit;
        }
        
        @Override
        protected void onActTerminated(Act act, Audit audit) {
            super.onActTerminated(act, audit);
            LOGGER.info("Audit group of page terminated on " + this.pageUrlList);
        }
        
    }
    
    /**
     * Inner class in charge of launching a page audit in a thread.
     */
    private class AuditPageThread extends AuditTimeoutThread {

        private final String pageUrl;
        
        public AuditPageThread(
                String pageUrl,
                AuditService auditService,
                Act act,
                Set<Parameter> parameterSet,
                Locale locale,
                int delay) {
            super(auditService, act, parameterSet, locale, delay);
            this.pageUrl = pageUrl;
            LOGGER.info("Launching audit Page on " + pageUrl);
        }

        @Override
        public Audit launchAudit() {
            Audit audit = null;
            if (this.pageUrl != null) {
                audit = this.getAuditService().auditPage(
                        this.pageUrl,
                        this.getParameterSet());
            }
            return audit;
        }
        
        @Override
        protected void onActTerminated(Act act, Audit audit) {
            super.onActTerminated(act, audit);
            LOGGER.info("Audit page terminated on " + this.pageUrl);
        }
        
    }
    
    /**
     * Inner class in charge of launching a upload pages audit in a thread.
     */
    private class AuditPageUploadThread extends AuditTimeoutThread {

        private final Map<String, String> pageMap = new HashMap<>();
        
        public AuditPageUploadThread(
                Map<String, String> pageMap,
                AuditService auditService,
                Act act,
                Set<Parameter> parameterSet, 
                Locale locale,
                int delay) {
            super(auditService, act, parameterSet, locale, delay);
            if (pageMap != null) {
                this.pageMap.putAll(pageMap);
                LOGGER.info("Launching audit files on " + pageMap.keySet());
            }
        }

        @Override
        public Audit launchAudit() {
            Audit audit = null;
            if (!pageMap.isEmpty()) {
                audit = this.getAuditService().auditPageUpload(
                        this.pageMap,
                        this.getParameterSet());
            }
            return audit;
        }
        
        @Override
        protected void onActTerminated(Act act, Audit audit) {
            super.onActTerminated(act, audit);
            LOGGER.info("Audit files terminated on " + this.pageMap.keySet());
        }
        
    }

}