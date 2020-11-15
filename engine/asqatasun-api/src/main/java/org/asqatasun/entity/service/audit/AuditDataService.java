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
package org.asqatasun.entity.service.audit;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.audit.Tag;
import org.asqatasun.entity.service.GenericDataService;

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
     * @param tags
     *            the status to find
     * @return the collection of the audits that matches the given tags
     */
    List<Audit> findAllByTags(List<String> tags);

    /**
     * 
     * @param id
     * @return
     */
    Audit getAuditWithTest(Long id);

}
