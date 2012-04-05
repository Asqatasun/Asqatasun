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
package org.opens.tgol.orchestrator;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.service.AuditService;
import org.opens.tanaguru.service.AuditServiceListener;
import org.opens.tgol.emailsender.EmailSender;
import org.opens.tgol.entity.contract.Act;
import org.opens.tgol.entity.contract.ActStatus;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.factory.contract.ActFactory;
import org.opens.tgol.entity.product.Scope;
import org.opens.tgol.entity.product.ScopeEnum;
import org.opens.tgol.entity.service.contract.ActDataService;
import org.opens.tgol.entity.service.product.ScopeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author jkowalczyk
 */
public class TanaguruOrchestratorImpl implements TanaguruOrchestrator {

    private static final Logger LOGGER = Logger.getLogger(TanaguruOrchestratorImpl.class);
    private AuditService auditService;
    private ActDataService actDataService;
    private ActFactory actFactory;
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private Map<ScopeEnum, Scope> scopeMap = new EnumMap<ScopeEnum, Scope>(ScopeEnum.class);
    private void initializeScopeMap(ScopeDataService ScopeDataService) {
        for (Scope scope : ScopeDataService.findAll()) {
            scopeMap.put(scope.getCode(), scope);
        }
    }
    
    private EmailSender emailSender;
    /*
     * keys to send the user an email at the end of an audit.
     */
    private static final String RECIPIENT_KEY =  "recipient";
    private static final String SUCCESS_SUBJECT_KEY =  "success-subject";
    private static final String ERROR_SUBJECT_KEY =  "error-subject";
    private static final String URL_TO_REPLACE =  "#webresourceUrl";
    private static final String PROJECT_NAME_TO_REPLACE =  "#projectName";
    private static final String PROJECT_URL_TO_REPLACE =  "#projectUrl";
    private static final String SUCCESS_MSG_CONTENT_KEY =  "success-content";
    private static final String SITE_ERROR_MSG_CONTENT_KEY =  "site-error-content";
    private static final String PAGE_ERROR_MSG_CONTENT_KEY =  "page-error-content";
    private static final String BUNDLE_NAME = "email-content-I18N";
    private static final int DEFAULT_AUDIT_DELAY = 30000;

    private String webappUrl;
    public String getWebappUrl() {
        return webappUrl;
    }

    public void setWebappUrl(String webappUrl) {
        this.webappUrl = webappUrl;
    }
    
    private String siteResultUrlSuffix;
    public String getSiteResultUrlSuffix() {
        return siteResultUrlSuffix;
    }

    public void setSiteResultUrlSuffix(String siteResultUrlSuffix) {
        this.siteResultUrlSuffix = siteResultUrlSuffix;
    }
    
    private String pageResultUrlSuffix;
    public String getPageResultUrlSuffix() {
        return pageResultUrlSuffix;
    }

    public void setPageResultUrlSuffix(String pageResultUrlSuffix) {
        this.pageResultUrlSuffix = pageResultUrlSuffix;
    }
    
    private String groupResultUrlSuffix;
    public String getGroupResultUrlSuffix() {
        return groupResultUrlSuffix;
    }

    public void setGroupResultUrlSuffix(String groupResultUrlSuffix) {
        this.groupResultUrlSuffix = groupResultUrlSuffix;
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
    
    private List<String> emailSentToUserExclusionList = new ArrayList<String>();
    public void setEmailSentToUserExclusionRawList(String emailSentToUserExclusionRawList) {
        this.emailSentToUserExclusionList.addAll(Arrays.asList(emailSentToUserExclusionRawList.split(";")));
    }
    
    @Autowired
    public TanaguruOrchestratorImpl(
            AuditService auditService,
            ActDataService actDataService,
            ActFactory actFactory,
            ScopeDataService scopeDataService,
            ThreadPoolTaskExecutor threadPoolTaskExecutor,
            EmailSender emailSender) {
        this.auditService = auditService;
        this.actDataService = actDataService;
        this.actFactory = actFactory;
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
        LOGGER.info("auditPage ");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("pageUrl " + pageUrl);
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
        LOGGER.info("auditPage Upload");
        if (LOGGER.isDebugEnabled()) {
            for (String str : fileMap.values()) {
                LOGGER.debug("files " + str);
            }
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
        LOGGER.info("auditSite");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("siteUrl " + siteUrl);
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
    public Audit auditSite(
            Contract contract,
            String siteUrl,
            final List<String> pageUrlList,
            String clientIp,
            Set<Parameter> parameterSet, 
            Locale locale) {
        LOGGER.info("auditGroupOfPages");
        if (LOGGER.isDebugEnabled()) {
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
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    LOGGER.error("", ex);
                }
                if (auditTimeoutThread.isDurationExceedsDelay()) {
                    LOGGER.debug("Audit Duration ExceedsDelay. The audit result "
                            + "is now managed in an asynchronous way.");
                    break;
                }
            }
            return auditTimeoutThread.getAudit();
        }
    }

    private void sendEmail(Act act, Locale locale) {
        LOGGER.debug("c'est quoi ce delire");
        String emailTo = act.getContract().getUser().getEmail1();
        if (this.emailSentToUserExclusionList.contains(emailTo)) {
            LOGGER.debug("Email not set cause user " + emailTo + " belongs to "
                    + "exlusion list");
            return;
        }
        LOGGER.debug("c'est quoi ce delire 2 " +locale.getLanguage());
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        LOGGER.debug("c'est quoi ce delire  3 " );
        String emailFrom = bundle.getString(RECIPIENT_KEY);
        Set<String> emailToSet = new HashSet<String>();
        emailToSet.add(emailTo);
        if (act.getStatus().equals(ActStatus.COMPLETED)) {
            String emailSubject = bundle.getString(SUCCESS_SUBJECT_KEY).replaceAll(PROJECT_NAME_TO_REPLACE, act.getContract().getLabel());
            StringBuilder emailMessage = new StringBuilder();
            emailMessage.append(computeSuccessfulMessageOnActTerminated(act, bundle));
            emailSender.sendEmail(emailFrom, emailToSet, emailSubject, emailMessage.toString());
            LOGGER.debug("success email sent to " + emailTo);
        } else if (act.getStatus().equals(ActStatus.ERROR)) {
            String emailSubject = bundle.getString(ERROR_SUBJECT_KEY).replaceAll(PROJECT_NAME_TO_REPLACE, act.getContract().getLabel());
            StringBuilder emailMessage = new StringBuilder();
            emailMessage.append(computeFailureMessageOnActTerminated(act, bundle));
            emailSender.sendEmail(emailFrom, emailToSet, emailSubject, emailMessage.toString());
            LOGGER.debug("error email sent " + emailTo);
        }
     }

    /**
     *
     * @param act
     * @param bundle
     * @return
     */
    private String computeSuccessfulMessageOnActTerminated(Act act, ResourceBundle bundle) {
        String messageContent =
                    bundle.getString(SUCCESS_MSG_CONTENT_KEY).
                    replaceAll(URL_TO_REPLACE, buildResultUrl(act));
        return messageContent.replaceAll(PROJECT_NAME_TO_REPLACE, act.getContract().getLabel());
    }

    /**
     * 
     * @param act
     * @param bundle
     * @return
     */
    private String computeFailureMessageOnActTerminated(Act act, ResourceBundle bundle) {
        String messageContent;
        if (act.getScope().getCode().equals(ScopeEnum.DOMAIN)) {
            messageContent = bundle.getString(SITE_ERROR_MSG_CONTENT_KEY);
        } else {
            messageContent = bundle.getString(PAGE_ERROR_MSG_CONTENT_KEY);
            messageContent = messageContent.replaceAll(PROJECT_URL_TO_REPLACE, buildContractUrl(act.getContract()));
        }
        messageContent = messageContent.replaceAll(URL_TO_REPLACE, buildResultUrl(act));
        return messageContent.replaceAll(PROJECT_NAME_TO_REPLACE, act.getContract().getLabel());
    }

    /**
     * 
     * @param act
     * @return 
     */
    private String buildResultUrl (Act act) {
        StringBuilder strb = new StringBuilder();
        strb.append(webappUrl);
        ScopeEnum scope = act.getScope().getCode();
        if (scope.equals(ScopeEnum.DOMAIN)) {
            strb.append(siteResultUrlSuffix);
        } else if (scope.equals(ScopeEnum.GROUPOFPAGES) || scope.equals(ScopeEnum.GROUPOFFILES)) {
            strb.append(groupResultUrlSuffix);
        } else if (scope.equals(ScopeEnum.FILE) || scope.equals(ScopeEnum.PAGE)) {
            strb.append(pageResultUrlSuffix);
        }
        strb.append(act.getWebResource().getId());
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
     */
    private abstract class AuditThread implements Runnable, AuditServiceListener {

        private AuditService auditService;
        public AuditService getAuditService() {
            return auditService;
        }

        public void setAuditService(AuditService auditService) {
            this.auditService = auditService;
        }
        
        private Set<Parameter> parameterSet = new HashSet<Parameter>();
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
        
        private Map<Audit, Long> auditExecutionList = new ConcurrentHashMap<Audit, Long>();
        public Map<Audit, Long> getAuditExecutionList() {
            return auditExecutionList;
        }

        public void setAuditExecutionList(Map<Audit, Long> auditExecutionList) {
            this.auditExecutionList = auditExecutionList;
        }
        
        private Map<Long, Audit> auditCompletedList = new ConcurrentHashMap<Long, Audit>();
        public Map<Long, Audit> getAuditCompletedList() {
            return auditCompletedList;
        }

        public void setAuditCompletedList(Map<Long, Audit> auditCompletedList) {
            this.auditCompletedList = auditCompletedList;
        }
        
        private Map<Long, AbstractMap.SimpleImmutableEntry<Audit, Exception>> auditCrashedList = new HashMap<Long, AbstractMap.SimpleImmutableEntry<Audit, Exception>>();
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
            currentAudit = this.waitForAuditToComplete(currentAudit);
            this.getAuditService().remove(this);
            this.getCurrentAct().setWebResource(currentAudit.getSubject());
            onActTerminated(this.getCurrentAct(), currentAudit);
        }
        
        /**
         * 
         * @return 
         */
        public abstract Audit launchAudit();
        
        @Override
        public void auditCompleted(Audit audit) {
            LOGGER.debug("AUDIT COMPLETED:" + audit + "," + audit.getSubject().getURL() + "," + (long) (audit.getDateOfCreation().getTime() / 1000));
            Audit auditCompleted = null;
            for (Audit auditRunning : this.auditExecutionList.keySet()) {
                if (auditRunning.getSubject().getURL().equals(audit.getSubject().getURL())
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
            LOGGER.error("AUDIT CRASHED:" + audit + "," + audit.getSubject().getURL() + "," + (long) (audit.getDateOfCreation().getTime() / 1000), exception);
            Audit auditCrashed = null;
            for (Audit auditRunning : this.auditExecutionList.keySet()) {
                if (auditRunning.getSubject().getURL().equals(audit.getSubject().getURL())
                        && (long) (auditRunning.getDateOfCreation().getTime() / 1000) == (long) (audit.getDateOfCreation().getTime() / 1000)) {
                    auditCrashed = auditRunning;
                    break;
                }
            }
            if (auditCrashed != null) {
                Long token = this.auditExecutionList.get(auditCrashed);
                this.auditExecutionList.remove(auditCrashed);
                this.auditCrashedList.put(token, new AbstractMap.SimpleImmutableEntry<Audit, Exception>(audit, exception));
            }
        }

        protected Audit waitForAuditToComplete(Audit audit) {
            LOGGER.debug("WAIT FOR AUDIT TO COMPLETE:" + audit + "," + audit.getSubject().getURL() + "," + (long) (audit.getDateOfCreation().getTime() / 1000));
            Long token = new Date().getTime();
            this.getAuditExecutionList().put(audit, token);
            while (!this.getAuditCompletedList().containsKey(token) && !this.getAuditCrashedList().containsKey(token)) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    LOGGER.error("", ex);
                }
            }
            if ((audit = this.getAuditCompletedList().get(token)) != null) {
                this.getAuditCompletedList().remove(token);
                return audit;
            }
            auditCrashed(this.getAuditCrashedList().get(token).getKey(), this.getAuditCrashedList().get(token).getValue());
            return null;
        }
        
//        @Override
        protected void onActTerminated(Act act, Audit audit) {
            LOGGER.debug("act is terminated");
            this.audit = audit;
            Date endDate = new Date();
            act.setEndDate(endDate);
            if (audit.getStatus().equals(AuditStatus.COMPLETED)) {
                act.setStatus(ActStatus.COMPLETED);
            } else {
                act.setStatus(ActStatus.ERROR);
            }
            actDataService.saveOrUpdate(act);
        }
    }
    
    /**
     * Inner class in charge of launching a site audit in a thread. At the end 
     * the audit, an email has to be sent to the user with the audit info.
     */
    private class AuditSiteThread extends AuditThread {

        private String siteUrl;
        
        public AuditSiteThread(
                String siteUrl,
                AuditService auditService,
                Act act,
                Set<Parameter> parameterSet, 
                Locale locale) {
            super(auditService, act, parameterSet, locale);
            this.siteUrl = siteUrl;
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
            sendEmail(act, getLocale());
            LOGGER.info("site audit terminated");
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
            if (isAuditTerminatedAfterTimeout()) {
                sendEmail(act, getLocale());
            }
        }

        public boolean isDurationExceedsDelay() {
            long currentDuration = new Date().getTime() - getStartDate().getTime();
            if (currentDuration > delay) {
                LOGGER.debug("Audit Duration has exceeded synchronous delay " + delay);
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

        private String siteUrl;
        private List<String> pageUrlList = new ArrayList<String>();
        
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
        }

        @Override
        public Audit launchAudit() {
            Audit audit = null;
            if (CollectionUtils.isNotEmpty(this.pageUrlList)) {
                audit = this.getAuditService().auditSite(
                        this.siteUrl,
                        this.pageUrlList,
                        this.getParameterSet());
            }
            return audit;
        }

    }
    
    /**
     * Inner class in charge of launching a page audit in a thread.
     */
    private class AuditPageThread extends AuditTimeoutThread {

        private String pageUrl;
        
        public AuditPageThread(
                String pageUrl,
                AuditService auditService,
                Act act,
                Set<Parameter> parameterSet,
                Locale locale,
                int delay) {
            super(auditService, act, parameterSet, locale, delay);
            this.pageUrl = pageUrl;
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
        
    }
    
    /**
     * Inner class in charge of launching a upload pages audit in a thread.
     */
    private class AuditPageUploadThread extends AuditTimeoutThread {

        Map<String, String> pageMap = new HashMap<String, String>();
        
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
            }
        }

        @Override
        public Audit launchAudit() {
            Audit audit = null;
            if (MapUtils.isNotEmpty(pageMap)) {
                audit = this.getAuditService().auditPageUpload(
                        this.pageMap,
                        this.getParameterSet());
            }
            return audit;
        }
        
    }

}