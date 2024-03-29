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
package org.asqatasun.service;

import java.util.HashSet;
import java.util.Set;

import org.asqatasun.entity.audit.Audit;
import org.asqatasun.service.command.AuditCommand;

/**
 *
 * @author enzolalay
 */
public class AuditServiceThreadImpl implements AuditServiceThread {

    private Audit audit;
    private AuditCommand auditCommand;

    private Set<AuditServiceThreadListener> listeners;

    public Set<AuditServiceThreadListener> getListeners() {
        return listeners;
    }

    /**
     *
     * @param audit
     */
    public AuditServiceThreadImpl(Audit audit) {
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
            listeners = new HashSet<>();
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
            crawl();
            loadContent();
            adaptContent();
            process();
            consolidate();
            analyse();
            fireAuditCompleted();
        } catch (Exception e) {
            fireAuditException(e);
        }
    }

    @Override
    public void init() {auditCommand.init();}

    @Override
    public void crawl() {auditCommand.crawl();}

    @Override
    public void loadContent() {
        auditCommand.loadContent();
    }

    @Override
    public void adaptContent() {auditCommand.adaptContent();}

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
