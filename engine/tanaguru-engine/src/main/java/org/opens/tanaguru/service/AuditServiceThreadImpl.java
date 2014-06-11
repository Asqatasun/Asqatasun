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
package org.opens.tanaguru.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.service.command.AuditCommand;
import org.opens.tanaguru.service.messagin.JmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author enzolalay
 */
//@Service
//@Lazy
public class AuditServiceThreadImpl implements AuditServiceThread {

    private Audit audit;
    private AuditCommand auditCommand;
    
    @Autowired
    private AuditServiceThread auditServiceThread;

    private Set<AuditServiceThreadListener> listeners;
    public Set<AuditServiceThreadListener> getListeners() {
        return listeners;
    }

    

    /**
     * 
     * @param audit 
     */
    public AuditServiceThreadImpl(
            Audit audit) {
        super();
        this.audit = audit;
    }
    
    /**
     * 
     * @param auditCommand 
     */
    public AuditServiceThreadImpl(AuditCommand auditCommand) {
        super();
        this.auditCommand = auditCommand;
    }

    @Override
    public Audit getAudit() {
        if (audit == null) {
            return auditCommand.getAudit();
        }
        return audit;
    }

    @Override
    public void add(AuditServiceThreadListener listener) {
        if (listeners == null) {
            listeners = new HashSet<AuditServiceThreadListener>();
        }
        listeners.add(listener);
    }

    @Override
    public void remove(AuditServiceThreadListener listener) {
        if (listeners == null) {
            return;
        }
        listeners.remove(listener);
    }

    @Override
    public void run() {
        
    	//FIXME :Taoufiq
    	try {
            init();
            loadContent();
            adaptContent();
            process();
            consolidate();
            analyse();
            fireAuditCompleted();
            callTheAquitementFinishProcess();
        } catch (Exception e) {
            fireAuditException(e);
        }
    }

    @Autowired
    private JmsProducer jmsProducer;
    
    private void callTheAquitementFinishProcess() {
    	System.out.println("avant de passer au JMS"+ getAudit().getStatus().toString());
    	 jmsProducer.sendMessageAudit("TestAuditFinished");
		
		
	}



	@Override
    public void init() {
        auditCommand.init();
        
    }

    @Override
    public void loadContent() {
        auditCommand.loadContent();
    }

    @Override
    public void adaptContent() {
        auditCommand.adaptContent();
    }

    @Override
    public void process() {
        auditCommand.process();
    }

    @Override
    public void consolidate() {
        auditCommand.consolidate();
    }
    
    @Override
    public void analyse() {
        auditCommand.analyse();
    }

    private void fireAuditCompleted() {
        if (listeners == null) {
            return;
        }
        for (AuditServiceThreadListener listener : listeners) {
            listener.auditCompleted(this);
        }
        //TODO :envoi JMS
    }

    private void fireAuditException(Exception e) {
        if (listeners == null) {
            return;
        }
        for (AuditServiceThreadListener listener : listeners) {
            listener.auditCrashed(this, e);
        }
    }

}