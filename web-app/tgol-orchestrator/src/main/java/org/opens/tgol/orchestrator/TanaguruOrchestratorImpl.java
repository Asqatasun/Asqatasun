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

import org.opens.tgol.emailsender.EmailSender;
import org.opens.tgol.entity.contract.Act;
import org.opens.tgol.entity.contract.ActStatus;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.factory.contract.ActFactory;
import org.opens.tgol.entity.product.Scope;
import org.opens.tgol.entity.product.ScopeEnum;
import org.opens.tgol.entity.service.contract.ActDataService;
import org.opens.tgol.entity.service.product.ScopeDataService;
import org.opens.tgol.entity.user.User;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.service.AuditService;
import org.opens.tanaguru.service.AuditServiceListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author jkowalczyk
 */
public class TanaguruOrchestratorImpl
        implements TanaguruOrchestrator, ActThreadListener, ActThreadMaster, AuditServiceListener {

    private static final Logger LOGGER = Logger.getLogger(TanaguruOrchestratorImpl.class);
    private AuditService auditService;
    private ActDataService actDataService;
    private ActFactory actFactory;
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private Collection<ActThreadListener> actThreadListenerSet = new HashSet<ActThreadListener>();
    private Map<ScopeEnum, Scope> scopeMap = new HashMap<ScopeEnum, Scope>();
    private Map<Audit, Long> auditExecutionList = new ConcurrentHashMap<Audit, Long>();
    private Map<Long, Audit> auditCompletedList = new ConcurrentHashMap<Long, Audit>();
    private Map<Long, AbstractMap.SimpleImmutableEntry<Audit, Exception>> auditCrashedList = new HashMap<Long, AbstractMap.SimpleImmutableEntry<Audit, Exception>>();
    private EmailSender emailSender;
    /*
     * keys to send the user an email at the end of an audit.
     */
    private static final String RECIPIENT_KEY =  "recipient";
    private static final String SUCCESS_SUBJECT_KEY =  "success-subject";
    private static final String ERROR_SUBJECT_KEY =  "error-subject";
    private static final String URL_TO_REPLACE =  "#webresourceUrl";
    private static final String PROJECT_NAME_TO_REPLACE =  "#projectName";
    private static final String SUCCESS_MSG_CONTENT_KEY =  "success-content";
    private static final String ERROR_MSG_CONTENT_KEY =  "error-content";
    private static final String URL_PARAM = "?wr=";
    private ResourceBundle emailContentResourceBundle = ResourceBundle.getBundle("email-content-I18N");
    private String webresourceUrlPrefix;
    public String getWebresourceUrlPrefix() {
        return webresourceUrlPrefix;
    }

    public void setWebresourceUrlPrefix(String webresourceUrlPrefix) {
        this.webresourceUrlPrefix = webresourceUrlPrefix;
    }
    
    @Override
    public void setActThreadListenerSet(Collection<ActThreadListener> actThreadListenerSet) {
        this.actThreadListenerSet = actThreadListenerSet;
    }

    @Override
    public void addActThreadListener(ActThreadListener actThreadListener) {
        this.actThreadListenerSet.add(actThreadListener);
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
        this.auditService.add(this);
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
            Set<Parameter> parameterSet) {
        LOGGER.debug("auditPage ");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("pageUrl " + pageUrl);
            for (Parameter param : parameterSet) {
                LOGGER.debug("param " + param.getValue() + " "+
                        param.getParameterElement().getParameterElementCode());
            }
        }
        Date beginDate = new Date();
        Act act = actFactory.createAct(beginDate, contract);
        act.setScope(scopeMap.get(ScopeEnum.PAGE));
        act.setClientIp(clientIp);
        Audit audit = auditService.auditPage(
                pageUrl,
                parameterSet);
        try {
            audit = waitForAuditToComplete(audit);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        act.setEndDate(new Date());
        act.setWebResource(audit.getSubject());
        if (audit.getStatus().equals(AuditStatus.COMPLETED)) {
            act.setStatus(ActStatus.COMPLETED);
        } else {
            act.setStatus(ActStatus.ERROR);
        }
        actDataService.saveOrUpdate(act);
        return audit;
    }

    @Override
    public Audit auditPageUpload(
            Contract contract,
            Map<String, String> fileMap,
            String clientIp,
            Set<Parameter> parameterSet) {
        LOGGER.debug("auditPage Upload");
        if (LOGGER.isDebugEnabled()) {
            for (String str : fileMap.values()) {
                LOGGER.debug("files " + str);
            }
            for (Parameter param : parameterSet) {
                LOGGER.debug("param " + param.getValue() + " "+
                        param.getParameterElement().getParameterElementCode());
            }
        }
        Date beginDate = new Date();
        Act act = actFactory.createAct(beginDate, contract);
        if (fileMap.size()>1) {
            act.setScope(scopeMap.get(ScopeEnum.GROUPOFFILES));
        } else {
            act.setScope(scopeMap.get(ScopeEnum.FILE));
        }
        act.setClientIp(clientIp);
        Audit audit = auditService.auditPageUpload(
                fileMap,
                parameterSet);
        try {
            audit = waitForAuditToComplete(audit);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        act.setEndDate(new Date());
        act.setWebResource(audit.getSubject());
        if (audit.getStatus().equals(AuditStatus.COMPLETED)) {
            act.setStatus(ActStatus.COMPLETED);
        } else {
            act.setStatus(ActStatus.ERROR);
        }
        actDataService.saveOrUpdate(act);
        return audit;
    }

    @Override
    public void auditSite(
            Contract contract,
            String siteUrl,
            String clientIp,
            Set<Parameter> parameterSet) {
        LOGGER.info("auditSite");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("siteUrl " + siteUrl);
            for (Parameter param : parameterSet) {
                LOGGER.debug("param " + param.getValue() + " "+
                        param.getParameterElement().getParameterElementCode());
            }
        }
        Act act = createAct(contract, ScopeEnum.DOMAIN, clientIp);
        AuditSiteThread auditSiteThread =
                new AuditSiteThread(
                siteUrl,
                null,
                auditService,
                this,
                act,
                parameterSet);
        auditSiteThread.add(this);
        threadPoolTaskExecutor.submit(auditSiteThread);
        return;
    }

    @Override
    public void auditSiteBg(
            Contract contract,
            String siteUrl,
            final List<String> pageUrlList,
            String clientIp,
            Set<Parameter> parameterSet) {
        LOGGER.info("auditGroupOfPagesInBg");
        if (LOGGER.isDebugEnabled()) {
            for (Parameter param : parameterSet) {
                LOGGER.debug("param " + param.getValue() + " "+
                        param.getParameterElement().getParameterElementCode());
            }
        }
        Act act = createAct(contract, ScopeEnum.GROUPOFPAGES, clientIp);
        AuditSiteThread auditSiteThread =
                new AuditSiteThread(
                siteUrl,
                pageUrlList,
                auditService,
                this,
                act,
                parameterSet);
        auditSiteThread.add(this);
        threadPoolTaskExecutor.submit(auditSiteThread);
        return;
    }

    @Override
    public Audit auditSite(
            Contract contract,
            String siteUrl,
            final List<String> pageUrlList,
            String clientIp,
            Set<Parameter> parameterSet) {
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
        Audit audit = auditService.auditSite(
                siteUrl,
                pageUrlList,
                parameterSet);
        try {
            try {
                audit = waitForAuditToComplete(audit);
            } catch (Exception ex) {
                Logger.getLogger(this.getClass()).error(ex);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        act.setEndDate(new Date());
        act.setWebResource(audit.getSubject());
        if (audit.getStatus().equals(AuditStatus.COMPLETED)) {
            act.setStatus(ActStatus.COMPLETED);
        } else {
            act.setStatus(ActStatus.ERROR);
        }
        actDataService.saveOrUpdate(act);
        return audit;
    }

    @Override
    public boolean isAuditRunning(Contract contract) {
        return !actDataService.getRunningActsByContract(contract).isEmpty();
    }

    @Override
    public boolean isAuditRunning(User user) {
        for (Contract contract : user.getContractSet()) {
            if (!actDataService.getRunningActsByContract(contract).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onActTerminated(Act act, Audit audit) {
        LOGGER.info("act is terminated");
        Date endDate = new Date();
        act.setEndDate(endDate);
        if (audit.getStatus().equals(AuditStatus.COMPLETED)) {
            act.setStatus(ActStatus.COMPLETED);
        } else {
            act.setStatus(ActStatus.ERROR);
        }
        actDataService.saveOrUpdate(act);
        LOGGER.info("actThreadListenerSet.size()  " + actThreadListenerSet.size());
        LOGGER.info("actThreadListenerSet.isEmpty()  " + actThreadListenerSet.isEmpty());
        for (ActThreadListener actThreadListener : actThreadListenerSet) {
            actThreadListener.onActTerminated(act, audit);
        }
        sendEmail(act);
    }

    private void sendEmail(Act act) {
        String emailFrom = emailContentResourceBundle.getString(RECIPIENT_KEY);
        Set<String> emailToSet = new HashSet<String>();
        emailToSet.add(act.getContract().getUser().getEmail1());
        if (act.getStatus().equals(ActStatus.COMPLETED)) {
            String emailSubject = emailContentResourceBundle.getString(SUCCESS_SUBJECT_KEY);
            StringBuilder emailMessage = new StringBuilder();
            emailMessage.append(computeSuccessfulMessageOnActTerminated(act.getWebResource().getId(), act.getContract().getLabel()));
            emailSender.sendEmail(emailFrom, emailToSet, emailSubject, emailMessage.toString());
        } else if (act.getStatus().equals(ActStatus.ERROR)) {
            String emailSubject = emailContentResourceBundle.getString(ERROR_SUBJECT_KEY);
            StringBuilder emailMessage = new StringBuilder();
            emailMessage.append(computeFailureMessageOnActTerminated(act.getWebResource().getId(), act.getContract().getLabel()));
            emailSender.sendEmail(emailFrom, emailToSet, emailSubject, emailMessage.toString());
        }
        
    }

    /**
     *
     * @param webResourceId
     * @param contractLabel
     * @return
     */
    private String computeSuccessfulMessageOnActTerminated(
            Long webResourceId,
            String contractLabel) {
        String messageContent =
                    emailContentResourceBundle.getString(SUCCESS_MSG_CONTENT_KEY).
                    replaceAll(URL_TO_REPLACE, webresourceUrlPrefix+URL_PARAM+webResourceId);
        return messageContent.replaceAll(PROJECT_NAME_TO_REPLACE, contractLabel);
    }

    /**
     * 
     * @param webResourceId
     * @param contractLabel
     * @return
     */
    private String computeFailureMessageOnActTerminated(
            Long webResourceId,
            String contractLabel) {
        String messageContent =
                    emailContentResourceBundle.getString(ERROR_MSG_CONTENT_KEY).
                    replaceAll(URL_TO_REPLACE, webresourceUrlPrefix+URL_PARAM+webResourceId);
        return messageContent.replaceAll(PROJECT_NAME_TO_REPLACE, contractLabel);
    }

    @Override
    public void onActExecution(Act act) {
        for (ActThreadListener actThreadListener : actThreadListenerSet) {
            actThreadListener.onActExecution(act);
        }
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

    private void initializeScopeMap(ScopeDataService ScopeDataService) {
        for (Scope scope : ScopeDataService.findAll()) {
            scopeMap.put(scope.getCode(), scope);
        }
    }

    private Audit waitForAuditToComplete(Audit audit) throws Exception {
        LOGGER.debug("WAIT FOR AUDIT TO COMPLETE:" + audit + "," + audit.getSubject().getURL() + "," + (long) (audit.getDateOfCreation().getTime() / 1000));
        Long token = new Date().getTime();
        this.auditExecutionList.put(audit, token);
        while (!this.auditCompletedList.containsKey(token) && !this.auditCrashedList.containsKey(token)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                LOGGER.error("", ex);
            }
        }
        if ((audit = this.auditCompletedList.get(token)) != null) {
            this.auditCompletedList.remove(token);
            return audit;
        }
        throw this.auditCrashedList.get(token).getValue();
    }

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

    private class AuditSiteThread implements Runnable, AuditServiceListener {

        private String siteUrl;
        private List<String> pageUrlList = new ArrayList<String>();
        private AuditService auditService;
        private ActThreadListener auditSiteThreadListener;
        private Set<Parameter> parameterSet = new HashSet<Parameter>();
        private Act currentAct;
        private Map<Audit, Long> auditExecutionList = new ConcurrentHashMap<Audit, Long>();
        private Map<Long, Audit> auditCompletedList = new ConcurrentHashMap<Long, Audit>();
        private Map<Long, AbstractMap.SimpleImmutableEntry<Audit, Exception>> auditCrashedList = new HashMap<Long, AbstractMap.SimpleImmutableEntry<Audit, Exception>>();
        private Set<AuditServiceListener> listeners;

        public void add(AuditServiceListener listener) {
            if (this.listeners == null) {
                this.listeners = new HashSet<AuditServiceListener>();
            }
            this.listeners.add(listener);
        }

        public void remove(AuditServiceListener listener) {
            if (this.listeners == null) {
                return;
            }
            this.listeners.remove(listener);
        }

        public AuditSiteThread(
                String siteUrl,
                List<String> pageUrlList,
                AuditService auditService,
                ActThreadListener auditSiteThreadListener,
                Act act,
                Set<Parameter> parameterSet) {
            this.siteUrl = siteUrl;
            if (pageUrlList != null) {
                this.pageUrlList.addAll(pageUrlList);
            }
            if (parameterSet != null) {
                this.parameterSet.addAll(parameterSet);
            }
            this.auditService = auditService;
            this.auditSiteThreadListener = auditSiteThreadListener;
            this.currentAct = act;
        }

        @Override
        public void run() {
            this.auditService.add(this);
            Audit audit = null;
            this.auditSiteThreadListener.onActExecution(this.currentAct);
            if (pageUrlList != null) {
                audit = this.auditService.auditSite(
                        this.siteUrl,
                        this.pageUrlList,
                        this.parameterSet);
            } else {
                audit = this.auditService.auditSite(
                        this.siteUrl,
                        this.parameterSet);
            }
            audit = this.waitForAuditToComplete(audit);
            this.auditService.remove(this);
            this.currentAct.setWebResource(audit.getSubject());
            this.auditSiteThreadListener.onActTerminated(this.currentAct, audit);
        }

        private Audit waitForAuditToComplete(Audit audit) {
            LOGGER.debug("WAIT FOR AUDIT TO COMPLETE:" + audit + "," + audit.getSubject().getURL() + "," + (long) (audit.getDateOfCreation().getTime() / 1000));
            Long token = new Date().getTime();
            this.auditExecutionList.put(audit, token);
            while (!this.auditCompletedList.containsKey(token) && !this.auditCrashedList.containsKey(token)) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    LOGGER.error("", ex);
                }
            }
            if ((audit = this.auditCompletedList.get(token)) != null) {
                this.auditCompletedList.remove(token);
                return audit;
            }
            fireAuditCrashed(this.auditCrashedList.get(token).getKey(), this.auditCrashedList.get(token).getValue());
            return null;
        }

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

        private void fireAuditCrashed(Audit audit, Exception exception) {
            if (this.listeners == null) {
                return;
            }
            for (AuditServiceListener listener : this.listeners) {
                listener.auditCrashed(audit, exception);
            }
        }
    }
}
