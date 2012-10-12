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
package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;
import java.util.Collection;
import java.util.Date;

/**
 * 
 * @author jkowalczyk
 */
public interface AuditDataService extends GenericDataService<Audit, Long> {

    /**
     * 
     * @param date
     * @return
     */
    Audit create(Date date);

    /**
     *
     * @param status
     *            the status to find
     * @return the collection of the audits that have the status
     */
    Collection<Audit> findAll(AuditStatus status);

    /**
     * 
     * @param id
     * @return an instance of audit with its subject (instance of webresource)
     */
    Audit getAuditWithWebResource(Long id);
    
    /**
     * 
     * @param id
     * @return
     */
    Audit getAuditWithTest(Long id);

}