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
package org.asqatasun.webapp.orchestrator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.Future;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.asqatasun.emailsender.EmailSender;
import org.asqatasun.entity.contract.ScopeEnum;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.service.AuditService;
import org.asqatasun.service.AuditServiceListener;
import org.asqatasun.entity.contract.*;
import org.asqatasun.entity.factory.contract.ActFactory;
import org.asqatasun.entity.scenario.Scenario;
import org.asqatasun.entity.service.contract.ActDataService;
import org.asqatasun.entity.service.contract.ScopeDataService;
import org.asqatasun.entity.service.scenario.ScenarioDataService;
import org.asqatasun.webapp.exception.KrashAuditException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @author jkowalczyk
 */
@Service("asqatasunOrchestrator")
public class AsqatasunOrchestrator {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsqatasunOrchestrator.class);
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
    private static final String PAGE_RESULT_URL_SUFFIX = "home/contract/audit-result.html?audit=";
    private static final String CONTRACT_URL_SUFFIX = "home/contract.html?cr=";

    @Value("${app.webapp.ui.config.webAppUrl}")
    private String webappUrl;
    @Value("${app.webapp.ui.config.orchestrator.ayncDelay}")
    private int delay;
    @Value("${app.webapp.ui.config.orchestrator.emailSentToUserExclusionList}")
    private List<String> emailSentToUserExclusionList;
    @Value("${app.webapp.ui.config.orchestrator.krashReportMailList}")
    private List<String> krashReportMailList;
    @Value("${app.webapp.ui.config.orchestrator.enableKrashReport}")
    private boolean isAllowedToSendKrashReport = true;

    private final AuditService auditService;
    private final ActDataService actDataService;
    private final AuditDataService auditDataService;
    private final ScenarioDataService scenarioDataService;
    private final ActFactory actFactory;
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private final ReloadableResourceBundleMessageSource messageSource;
    private final Map<ScopeEnum, Scope> scopeMap = new EnumMap<>(ScopeEnum.class);
    private final EmailSender emailSender;
    private void initializeScopeMap(ScopeDataService ScopeDataService) {
        for (Scope scope : ScopeDataService.findAll()) {
            scopeMap.put(scope.getCode(), scope);
        }
    }

    public AsqatasunOrchestrator(
            AuditService auditService,
            AuditDataService auditDataService,
            ActDataService actDataService,
            ActFactory actFactory,
            ScopeDataService scopeDataService,
            ScenarioDataService scenarioDataService,
            ThreadPoolTaskExecutor threadPoolTaskExecutor,
            ReloadableResourceBundleMessageSource messageSource,
            EmailSender emailSender) {
        this.auditService = auditService;
        this.actDataService = actDataService;
        this.auditDataService = auditDataService;
        this.actFactory = actFactory;
        this.scenarioDataService = scenarioDataService;
        initializeScopeMap(scopeDataService);
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.emailSender = emailSender;
        this.messageSource = messageSource;
    }

    public Audit auditPage(
        Contract contract,
        String pageUrl,
        String clientIp,
        Set<Parameter> parameterSet,
        Locale locale) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Page audit on " + pageUrl);
            for (Parameter param : parameterSet) {
                LOGGER.debug("param " + param.getValue() + " " +
                    param.getParameterElement().getParameterElementCode());
            }
        }
        Act act = createAct(contract, ScopeEnum.PAGE, clientIp);
        AuditTimeoutThread auditPageThread =
                new AuditPageThread(pageUrl, auditService, act, parameterSet, locale, delay);
        return submitAuditAndLaunch(auditPageThread);
    }

    public Audit auditPageUpload(
        Contract contract,
        Map<String, String> fileMap,
        String clientIp,
        Set<Parameter> parameterSet,
        Locale locale) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("auditPage Upload on " + fileMap.size() + " files");
            for (Parameter param : parameterSet) {
                LOGGER.debug("param " + param.getValue() + " " +
                    param.getParameterElement().getParameterElementCode());
            }
        }
        Act act;
        if (fileMap.size() > 1) {
            act = createAct(contract, ScopeEnum.GROUPOFFILES, clientIp);
        } else {
            act = createAct(contract, ScopeEnum.FILE, clientIp);
        }
        AuditTimeoutThread auditPageUploadThread =
                new AuditPageUploadThread(fileMap, auditService, act, parameterSet, locale, delay);
        return submitAuditAndLaunch(auditPageUploadThread);
    }

    public void auditSite(
        Contract contract,
        String siteUrl,
        String clientIp,
        Set<Parameter> parameterSet,
        Locale locale) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Site audit on " + siteUrl);
            for (Parameter param : parameterSet) {
                LOGGER.debug("param " + param.getValue() + " " +
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

    public void auditScenario(
        Contract contract,
        Long idScenario,
        String clientIp,
        Set<Parameter> parameterSet,
        Locale locale) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Scenarion audit on scenario with id" + idScenario);
            for (Parameter param : parameterSet) {
                LOGGER.debug("param " + param.getValue() + " " +
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

    public Audit auditSite(
        Contract contract,
        String siteUrl,
        final List<String> pageUrlList,
        String clientIp,
        Set<Parameter> parameterSet,
        Locale locale) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Group of pages audit");
            for (String str : pageUrlList) {
                LOGGER.debug("pageUrl " + str);
            }
            for (Parameter param : parameterSet) {
                LOGGER.debug("param " + param.getValue() + " " +
                    param.getParameterElement().getParameterElementCode());
            }
        }
        Act act = createAct(contract, ScopeEnum.GROUPOFPAGES, clientIp);
        AuditTimeoutThread auditPageThread = 
                new AuditGroupOfPagesThread(siteUrl, pageUrlList, auditService, act, parameterSet, locale, delay);
        return submitAuditAndLaunch(auditPageThread);
    }

    /**
     * @param auditTimeoutThread
     * @return
     */
    private Audit submitAuditAndLaunch(AuditTimeoutThread auditTimeoutThread) {
        synchronized (auditTimeoutThread) {
            Future submitedThread = threadPoolTaskExecutor.submit(auditTimeoutThread);
            while (!submitedThread.isDone()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    LOGGER.error("", ex);
                }
                if (auditTimeoutThread.isDurationExceedsDelay()) {
                    LOGGER.debug("Audit Duration ExceedsDelay. The audit result is now managed in an asynchronous way.");
                    // null as a return is seen by the controller as an audit in progress
                    return null;
                }
            }
            if (null != auditTimeoutThread.exception) {
                LOGGER.error("new KrashAuditException()");
                throw new KrashAuditException(auditTimeoutThread.exception);
            }
            return auditTimeoutThread.audit;
        }
    }

    /**
     * Send an email when an audit terminates
     *
     * @param act
     * @param locale
     */
    private void sendAuditResultEmail(Act act, Locale locale) {
        String emailTo = act.getContract().getUser().getEmail1();
        if (this.emailSentToUserExclusionList.contains(emailTo)) {
            LOGGER.info("Email not set cause user " + emailTo + " belongs to "
                + "exlusion list");
            return;
        }
        String emailFrom = messageSource.getMessage(RECIPIENT_KEY, null, locale);

        Set<String> emailToSet = new HashSet<>();
        emailToSet.add(emailTo);
        if (act.getStatus().equals(ActStatus.COMPLETED)) {
            sendSuccessfulMessageOnActTerminated(act, locale, emailFrom, emailToSet, getProjectNameFromAct(act));
        } else if (act.getStatus().equals(ActStatus.ERROR)) {
            sendFailureMessageOnActTerminated(act, locale, emailFrom, emailToSet, getProjectNameFromAct(act));
        }
    }

    /**
     * @param act
     * @param locale
     * @param exception
     */
    private void sendKrashAuditEmail(Act act, Locale locale, Exception exception) {

        String emailFrom = messageSource.getMessage(RECIPIENT_KEY, null, locale);
        String projectName = getProjectNameFromAct(act);
        String host = getLocalhostname();

        if (isAllowedToSendKrashReport && CollectionUtils.isNotEmpty(krashReportMailList)) {
            Set<String> emailToSet = new HashSet<>();
            emailToSet.addAll(krashReportMailList);

            String msgSubject = messageSource.getMessage(KRASH_ADMIN_SUBJECT_KEY, null, locale);
            msgSubject = StringUtils.replace(msgSubject, PROJECT_NAME_TO_REPLACE, projectName);
            msgSubject = StringUtils.replace(msgSubject, HOST_TO_REPLACE, host);

            String msgContent = messageSource.getMessage(KRASH_ADMIN_MSG_CONTENT_KEY, null, locale);
            msgContent = StringUtils.replace(msgContent, PROJECT_NAME_TO_REPLACE, projectName);
            msgContent = StringUtils.replace(msgContent, USER_EMAIL_TO_REPLACE, act.getContract().getUser().getEmail1());
            msgContent = StringUtils.replace(msgContent, HOST_TO_REPLACE, host);
            msgContent = StringUtils.replace(msgContent, EXCEPTION_TO_REPLACE, ExceptionUtils.getStackTrace(exception));
            if (act.getAudit().getSubject() != null) {
                msgContent = StringUtils.replace(msgContent, AUDIT_URL_TO_REPLACE, act.getAudit().getSubject().getURL());
            }
            LOGGER.info("krash email sent to " + krashReportMailList + " on audit n° " + act.getAudit().getId());
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

        String msgSubject = messageSource.getMessage(KRASH_SUBJECT_KEY, null, locale);
        msgSubject = StringUtils.replace(msgSubject, PROJECT_NAME_TO_REPLACE, projectName);
        msgSubject = StringUtils.replace(msgSubject, HOST_TO_REPLACE, host);

        String msgContent = messageSource.getMessage(KRASH_MSG_CONTENT_KEY, null, locale);
        msgContent = StringUtils.replace(msgContent, PROJECT_NAME_TO_REPLACE, projectName);
        msgContent = StringUtils.replace(msgContent, PROJECT_URL_TO_REPLACE, buildContractUrl(act.getContract()));

        LOGGER.info("krash email sent to [" + emailTo + "]" + " on audit n° " + act.getAudit().getId());
        sendEmail(emailFrom, emailToSet, msgSubject, msgContent);
    }

    /**
     * @param act
     * @param locale
     * @param emailFrom
     * @param emailTo
     * @param projectName
     */
    private void sendSuccessfulMessageOnActTerminated(
        Act act,
        Locale locale,
        String emailFrom,
        Set<String> emailTo,
        String projectName) {

        String host = getLocalhostname();
        LOGGER.debug("Hostname " + host);
        LOGGER.debug("SUCCESS_SUBJECT_KEY " + messageSource.getMessage(SUCCESS_SUBJECT_KEY, null, locale));
        String emailSubject = messageSource.getMessage(SUCCESS_SUBJECT_KEY, null, locale).
            replaceAll(PROJECT_NAME_TO_REPLACE, projectName).
            replaceAll(HOST_TO_REPLACE, host);
        LOGGER.debug("emailSubject " + emailSubject);

        String messageContent = messageSource.getMessage(SUCCESS_MSG_CONTENT_KEY, null, locale);
        messageContent = messageContent.replaceAll(URL_TO_REPLACE, buildResultUrl(act));
        messageContent = messageContent.replaceAll(PROJECT_NAME_TO_REPLACE, projectName);

        LOGGER.info("success email sent to " + emailTo + " on audit n° " + act.getAudit().getId());
        sendEmail(emailFrom, emailTo, emailSubject, messageContent);
    }

    /**
     * @param act
     * @param locale locale
     * @return
     */
    private void sendFailureMessageOnActTerminated(
        Act act,
        Locale locale,
        String emailFrom,
        Set<String> emailTo,
        String projectName) {

        String host = getLocalhostname();
        String emailSubject = messageSource.getMessage(ERROR_SUBJECT_KEY, null, locale).
            replaceAll(PROJECT_NAME_TO_REPLACE, projectName).
            replaceAll(HOST_TO_REPLACE, host);
        String messageContent;
        if (act.getScope().getCode().equals(ScopeEnum.DOMAIN)) {
            messageContent = messageSource.getMessage(SITE_ERROR_MSG_CONTENT_KEY, null, locale);
        } else {
            messageContent = messageSource.getMessage(PAGE_ERROR_MSG_CONTENT_KEY, null, locale);
        }
        messageContent = messageContent.replaceAll(PROJECT_URL_TO_REPLACE, buildContractUrl(act.getContract()));
        messageContent = messageContent.replaceAll(URL_TO_REPLACE, buildResultUrl(act));
        messageContent = messageContent.replaceAll(PROJECT_NAME_TO_REPLACE, projectName);
        LOGGER.info("failure email sent to " + emailTo + " on audit n° " + act.getAudit().getId());
        sendEmail(emailFrom, emailTo, emailSubject, messageContent);
    }

    /**
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
            Collections.emptySet(),
            StringUtils.EMPTY,
            emailSubject,
            emailMessage);
    }

    /**
     * getLocalhostname Grab hostname of host running Asqatasun
     * (used in emails to identify from which server the email comes from)
     */
    private String getLocalhostname() {
        String host = "";
        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException uhe) {
        }
        return host;
    }


    /**
     * @param act
     * @return
     */
    private String buildResultUrl(Act act) {
        StringBuilder strb = new StringBuilder();
        strb.append(webappUrl);
        strb.append(PAGE_RESULT_URL_SUFFIX);
        strb.append(act.getAudit().getId());
        return strb.toString();
    }

    /**
     * 
     * @param contract
     * @return 
     */
    private String buildContractUrl(Contract contract) {
        StringBuilder strb = new StringBuilder();
        strb.append(webappUrl);
        strb.append(CONTRACT_URL_SUFFIX);
        strb.append(contract.getId());
        return strb.toString();
    }

    /**
     * This method initializes an act instance and persists it.
     *
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
        act = actDataService.saveOrUpdate(act);
        return act;
    }

    /**
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

        protected AuditService auditService;
        protected Set<Parameter> parameterSet = new HashSet<>();
        protected Act currentAct;
        protected Date startDate;
        protected Audit audit = null;
        protected Locale locale;
        protected Exception exception = null;
        protected boolean isTerminated = false;
        
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
            auditService.add(this);
            audit = launchAudit();
            currentAct.setAudit(audit);
            currentAct = actDataService.saveOrUpdate(currentAct);
            this.waitForAuditToComplete(audit);
            auditService.remove(this);
            onActTerminated(currentAct);
        }

        /**
         * @return
         */
        public abstract Audit launchAudit();

        @Override
        public void auditCompleted(Audit audit) {

            LOGGER.info("AUDIT COMPLETED:" + audit.getId() + "," + audit.getSubject().getURL() + "," + (audit.getDateOfCreation().getTime() / 1000) + audit.getId());
            LOGGER.info("Current audit is " + this.audit.getId());
            if (this.audit.getId().equals(audit.getId())) {
                isTerminated = true;
                // retrieve instance of completed audit
                this.audit = audit;
            } else {
                LOGGER.debug("Another audit completed, current is " + this.audit.getId());
            }
        }

        @Override
        public void auditCrashed(Audit audit, Exception exception) {
            String url = "";
            if (audit.getSubject() != null) {
                url = audit.getSubject().getURL();
            }
            LOGGER.info("AUDIT CRASHED:" + audit + "," + url + "," + (audit.getDateOfCreation().getTime() / 1000), exception);
            if (this.audit.getId().equals(audit.getId())) {
                this.isTerminated = true;
                this.exception = exception;
                this.audit = audit;
            } else {
                LOGGER.debug("Another audit crashed, current is " + this.audit.getId());
            }
        }

        protected void waitForAuditToComplete(Audit audit) {
            LOGGER.debug("WAIT FOR AUDIT TO COMPLETE:" + audit + "," + (audit.getDateOfCreation().getTime() / 1000));
            // while the audit is not seen as completed or crashed
            while (!isTerminated) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    LOGGER.error("", ex);
                }
            }
        }
        
        protected void onActTerminated(Act act) {
            Date endDate = new Date();
            act.setEndDate(endDate);
            if (audit.getStatus().equals(AuditStatus.COMPLETED)) {
                act.setStatus(ActStatus.COMPLETED);
            } else {
                act.setStatus(ActStatus.ERROR);
            }
            actDataService.saveOrUpdate(act);
            // saveOrUpdate is not necessary, the entity will be saved at the end of the session
            if (exception != null) {
                sendKrashAuditEmail(act, locale, exception);
                actDataService.delete(act.getId());
                auditDataService.delete(audit.getId());
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
            return auditService.auditSite(this.siteUrl, parameterSet, Collections.EMPTY_LIST);
        }

        @Override
        protected void onActTerminated(Act act) {
            super.onActTerminated(act);
            if (exception == null) {
                sendAuditResultEmail(act, locale);
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
            return auditService.auditScenario(scenarioName, scenario, parameterSet, Collections.EMPTY_LIST);
        }

        @Override
        protected void onActTerminated(Act act) {
            super.onActTerminated(act);
            if (exception == null) {
                sendAuditResultEmail(act, locale);
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
        
        protected boolean isAuditTerminatedAfterTimeout = false;
        private int delay;
        
        public AuditTimeoutThread (
                AuditService auditService,
                Act act,
                Set<Parameter> parameterSet, 
                Locale locale,
                int delay) {
            super(auditService, act, parameterSet, locale);
            this.delay = delay;
        }

        public boolean isAuditTerminatedAfterTimeout() {
            return isAuditTerminatedAfterTimeout;
        }

        public void setAuditTerminatedAfterTimeout(boolean isAuditTerminatedBeforeTimeout) {
            this.isAuditTerminatedAfterTimeout = isAuditTerminatedBeforeTimeout;
        }

        @Override
        protected void onActTerminated(Act act) {
            super.onActTerminated(act);
            if (isAuditTerminatedAfterTimeout && exception == null) {
                sendAuditResultEmail(act, locale);
            }
        }

        public boolean isDurationExceedsDelay() {
            long currentDuration = new Date().getTime() - startDate.getTime();
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
                audit = auditService.auditSite(siteUrl, pageUrlList, parameterSet, Collections.EMPTY_LIST);
            }
            return audit;
        }

        @Override
        protected void onActTerminated(Act act) {
            super.onActTerminated(act);
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
                audit = auditService.auditPage(pageUrl, parameterSet, Collections.EMPTY_LIST);
            }
            return audit;
        }

        @Override
        protected void onActTerminated(Act act) {
            super.onActTerminated(act);
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
                audit = auditService.auditPageUpload(pageMap, parameterSet, Collections.EMPTY_LIST);
            }
            return audit;
        }

        @Override
        protected void onActTerminated(Act act) {
            super.onActTerminated(act);
            LOGGER.info("Audit files terminated on " + this.pageMap.keySet());
        }

    }

}
