/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.Tag;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.service.command.AuditCommand;
import org.asqatasun.service.command.factory.AuditCommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author jkowalczyk
 */
@Service
public class AuditServiceImpl implements AuditService, AuditServiceListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuditServiceImpl.class);
    private AuditServiceThreadFactory auditServiceThreadFactory;
    private AuditServiceThreadQueue auditServiceThreadQueue;
    private AuditCommandFactory auditCommandFactory;

    /**
     * the listeners of AuditService result
     */
    private Set<AuditServiceListener> listeners;
    public Set<AuditServiceListener> getListeners() {
        return listeners;
    }

    @Autowired
    public AuditServiceImpl(
        AuditServiceThreadFactory auditServiceThreadFactory,
        AuditCommandFactory auditCommandFactory,
        AuditServiceThreadQueue auditServiceThreadQueue) {
        this.auditServiceThreadFactory = auditServiceThreadFactory;
        this.auditCommandFactory = auditCommandFactory;
        this.auditServiceThreadQueue = auditServiceThreadQueue;
    }

    @Override
    public void add(AuditServiceListener listener) {
        if (listeners == null) {
            listeners = new HashSet<>();
        }
        listeners.add(listener);
    }

    @Override
    public void remove(AuditServiceListener listener) {
        if (listeners == null) {
            return;
        }
        listeners.remove(listener);
    }

    @Override
    public Audit auditScenario(String scenarioName, String scenario, Set<Parameter> paramSet, List<Tag> tagList) {
        LOGGER.debug("auditScenario");
        AuditCommand auditCommand = auditCommandFactory.create(scenarioName, scenario, paramSet, tagList);
        auditServiceThreadQueue.addScenarioAudit(auditCommand);
        auditServiceThreadQueue.add(this);
        return auditCommand.getAudit();
    }

    @Override
    public Audit auditPage(String pageUrl, Set<Parameter> paramSet, List<Tag> tagList) {
        LOGGER.debug("auditpage");
        AuditCommand auditCommand = auditCommandFactory.create(pageUrl, paramSet, tagList, false);
        auditServiceThreadQueue.addPageAudit(auditCommand);
        auditServiceThreadQueue.add(this);
        return auditCommand.getAudit();
    }
    
    @Override
    public Audit auditPageUpload(Map<String, String> fileMap, Set<Parameter> paramSet, List<Tag> tagList) {
        LOGGER.debug("auditpageupload");
        AuditCommand auditCommand = auditCommandFactory.create(fileMap, paramSet, tagList);
        auditServiceThreadQueue.addPageUploadAudit(auditCommand);
        auditServiceThreadQueue.add(this);
        return auditCommand.getAudit();
    }

    @Override
    public Audit auditSite(String siteUrl, Set<Parameter> paramSet, List<Tag> tagList) {
        LOGGER.debug("auditSite");
        AuditCommand auditCommand = auditCommandFactory.create(siteUrl, paramSet, tagList, true);
        auditServiceThreadQueue.addSiteAudit(auditCommand);
        auditServiceThreadQueue.add(this);
        return auditCommand.getAudit();
    }

    @Override
    public Audit auditSite(String siteUrl, List<String> pageUrlList, Set<Parameter> paramSet, List<Tag> tagList) {
        LOGGER.debug("auditGroupOfPages");
        AuditCommand auditCommand = auditCommandFactory.create(siteUrl, pageUrlList, paramSet, tagList);
        auditServiceThreadQueue.addPageAudit(auditCommand);
        auditServiceThreadQueue.add(this);
        return auditCommand.getAudit();
    }

    @Override
    public Audit audit(Audit audit) {
        AuditServiceThread auditServiceThread = getInitialisedAuditServiceThread(audit);
        auditServiceThread.run();
        return auditServiceThread.getAudit();
    }

    /**
     *
     * @param audit
     * @return
     */
    private AuditServiceThread getInitialisedAuditServiceThread(Audit audit) {
    	  
        return auditServiceThreadFactory.create(audit);
    }

    @Override
    public void auditCompleted(Audit audit) {
        fireAuditCompleted(audit);
    }

    private void fireAuditCompleted(Audit audit) {
        if (listeners == null) {
            return;
        }
        for (AuditServiceListener listener : listeners) {
            listener.auditCompleted(audit);
        }
    }

    @Override
    public void auditCrashed(Audit audit, Exception exception) {
        if (listeners == null) {
            return;
        }
        for (AuditServiceListener listener : listeners) {
            listener.auditCrashed(audit, exception);
        }
    }

}
