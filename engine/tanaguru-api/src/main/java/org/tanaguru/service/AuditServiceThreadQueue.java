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

import org.tanaguru.service.command.AuditCommand;

/**
 *
 * @author enzolalay
 */
public interface AuditServiceThreadQueue {// TODO Write javadoc

    /**
     *
     * @param max
     */
    void setPageAuditExecutionListMax(int max);

    /**
     *
     * @param max
     */
    void setSiteAuditExecutionListMax(int max);

    /**
     *
     * @param max
     */
    void setScenarioAuditExecutionListMax(int max);

    /**
     *
     * @param max
     */
    void setUploadAuditExecutionListMax(int max);

    /**
     *
     * @param auditCommand
     */
    void addPageAudit(AuditCommand auditCommand);

    /**
     *
     * @param auditCommand
     */
    void addScenarioAudit(AuditCommand auditCommand);

    /**
     *
     * @param auditCommand
     */
    void addPageUploadAudit(AuditCommand auditCommand);

    /**
     *
     * @param auditCommand
     */
    void addSiteAudit(AuditCommand auditCommand);

    /**
     *
     */
    void processWaitQueue();

    /**
     *
     * @param listener
     */
    void add(AuditServiceListener listener);

    /**
     *
     * @param listener
     */
    void remove(AuditServiceListener listener);

}