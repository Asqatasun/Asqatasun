/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.tanaguru.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.service.command.AuditCommand;
import org.tanaguru.service.command.factory.AuditCommandFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author jkowalczyk
 */
public class AuditServiceImpl implements AuditService, AuditServiceListener {
	
    private AuditServiceThreadFactory auditServiceThreadFactory;
    @Autowired
    public void setAuditServiceThreadFactory(AuditServiceThreadFactory auditServiceThreadFactory) {
        this.auditServiceThreadFactory = auditServiceThreadFactory;
    }
    
    private AuditServiceThreadQueue auditServiceThreadQueue;
    @Autowired
    public void setAuditServiceThreadQueue(AuditServiceThreadQueue auditServiceThreadQueue) {
        if (this.auditServiceThreadQueue != null) {
            this.auditServiceThreadQueue.remove(this);
        }
        this.auditServiceThreadQueue = auditServiceThreadQueue;
        this.auditServiceThreadQueue.add(this);
    }

    private AuditCommandFactory auditCommandFactory;
    @Autowired
    public void setAuditCommandFactory(AuditCommandFactory auditCommandFactory) {
        this.auditCommandFactory = auditCommandFactory;
    }
    
    /**
     * the listeners of AuditService result
     */
    private Set<AuditServiceListener> listeners;
    public Set<AuditServiceListener> getListeners() {
        return listeners;
    }

    public AuditServiceImpl() {
        super();
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
    public Audit auditScenario(String scenarioName, String scenario, Set<Parameter> paramSet) {
        Logger.getLogger(this.getClass()).debug("auditScenario");
        AuditCommand auditCommand = auditCommandFactory.create(scenarioName, scenario, paramSet);
        auditServiceThreadQueue.addScenarioAudit(auditCommand);
        return auditCommand.getAudit();
    }

    @Override
    public Audit auditPage(String pageUrl, Set<Parameter> paramSet) {
        Logger.getLogger(this.getClass()).debug("auditpage");
        AuditCommand auditCommand = auditCommandFactory.create(pageUrl, paramSet, false);
        auditServiceThreadQueue.addPageAudit(auditCommand);
        return auditCommand.getAudit();
    }
    
    @Override
    public Audit auditPageUpload(Map<String, String> fileMap, Set<Parameter> paramSet) {
        Logger.getLogger(this.getClass()).debug("auditpageupload");
        AuditCommand auditCommand = auditCommandFactory.create(fileMap, paramSet);
        auditServiceThreadQueue.addPageUploadAudit(auditCommand); 
        return auditCommand.getAudit();
    }

    @Override
    public Audit auditSite(String siteUrl, Set<Parameter> paramSet) {
        Logger.getLogger(this.getClass()).debug("auditSite");
        AuditCommand auditCommand = auditCommandFactory.create(siteUrl, paramSet, true);
        auditServiceThreadQueue.addSiteAudit(auditCommand); 
        return auditCommand.getAudit();
    }

    @Override
    public Audit auditSite(String siteUrl, List<String> pageUrlList, Set<Parameter> paramSet) {
        Logger.getLogger(this.getClass()).debug("auditGroupOfPages");
        AuditCommand auditCommand = auditCommandFactory.create(siteUrl, pageUrlList, paramSet);
        auditServiceThreadQueue.addPageAudit(auditCommand); 
        return auditCommand.getAudit();
    }

    @Override
    public Audit audit(Audit audit) {
        AuditServiceThread auditServiceThread = getInitialisedAuditServiceThread(audit);
        auditServiceThread.run();
        return auditServiceThread.getAudit();
    }

    @Override
    public Audit init(Audit audit) {
        AuditServiceThread auditServiceThread = getInitialisedAuditServiceThread(audit);
        auditServiceThread.init();
        return auditServiceThread.getAudit();
    }

    @Override
    public Audit crawl(Audit audit) {
        AuditServiceThread auditServiceThread = getInitialisedAuditServiceThread(audit);
        auditServiceThread.loadContent();
        return auditServiceThread.getAudit();
    }

    @Override
    public Audit loadContent(Audit audit) {
        AuditServiceThread auditServiceThread = getInitialisedAuditServiceThread(audit);
        auditServiceThread.loadContent();
        return auditServiceThread.getAudit();
    }

    @Override
    public Audit loadScenario(Audit audit) {
        AuditCommand auditCommand = auditCommandFactory.create(null, null, true);
        auditCommand.setAudit(audit);
        return audit;
    }

    @Override
    public Audit adaptContent(Audit audit) {
        AuditCommand auditCommand = auditCommandFactory.create(null, null, true);
        auditCommand.setAudit(audit);
        auditCommand.adaptContent();
        return audit;
    }

    @Override
    public Audit process(Audit audit) {
        AuditCommand auditCommand = auditCommandFactory.create(null, null, true);
        auditCommand.setAudit(audit);
        auditCommand.process();
        return audit;
    }

    @Override
    public Audit consolidate(Audit audit) {
        AuditCommand auditCommand = auditCommandFactory.create(null, null, true);
        auditCommand.setAudit(audit);
        auditCommand.consolidate();
        return audit;
    }

    @Override
    public Audit analyse(Audit audit) {
        AuditCommand auditCommand = auditCommandFactory.create(null, null, true);
        auditCommand.setAudit(audit);
        auditCommand.analyse();
        return audit;
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