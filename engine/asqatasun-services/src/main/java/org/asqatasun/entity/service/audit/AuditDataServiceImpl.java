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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.audit.factory.AuditFactory;
import org.asqatasun.entity.dao.audit.AuditDAO;
import org.asqatasun.entity.service.AbstractGenericDataService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author jkowalczyk
 */
@Service("auditDataService")
public class AuditDataServiceImpl extends AbstractGenericDataService<Audit, Long> implements AuditDataService {

    public AuditDataServiceImpl() {
        super();
    }

    @Override
    public Audit create(Date date) {
        return ((AuditFactory) entityFactory).create(date);
    }

    @Override
    public Collection<Audit> findAll(AuditStatus status) {
        return ((AuditDAO) entityDao).findAll(status);
    }

    @Override
    public List<Audit> findAllByTags(List<String> tags) {
        List<Audit> audits = new ArrayList<>();
        for (String tag: tags) {
            if (audits.isEmpty()) {
                audits = ((AuditDAO) entityDao).findAllByTag(tag);
            } else {
                audits = (List<Audit>) CollectionUtils.intersection(audits, ((AuditDAO) entityDao).findAllByTag(tag));
            }
        }
        return audits;
    }

    @Override
    public Audit getAuditWithTest(Long id) {
        return ((AuditDAO) entityDao).findAuditWithTest(id);
    }

}
