/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.service.mock;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.AuditStatus;
import org.tanaguru.entity.service.audit.AuditDataService;
import org.tanaguru.sdk.entity.dao.GenericDAO;
import org.tanaguru.sdk.entity.factory.GenericFactory;

/**
 *
 * @author jkowalczyk
 */
public class MockAuditDataService implements AuditDataService {

    private Map<Long, Audit> auditMap = new LinkedHashMap<Long, Audit>();

    private Long id = Long.valueOf(1);
    
    @Override
    public Audit create(Date date) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Audit> findAll(AuditStatus status) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Audit getAuditWithTest(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Audit create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(Audit entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Audit entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Collection<Audit> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Audit> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Audit read(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Audit saveOrUpdate(Audit entity) {
        entity.setId(id);
        auditMap.put(id, entity);
        id++;
        return entity;
    }

    @Override
    public Collection<Audit> saveOrUpdate(Collection<Audit> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<Audit, Long> dao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<Audit> factory) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Audit update(Audit entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    
}