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
package org.tanaguru.entity.dao.audit;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Query;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.PreProcessResult;
import org.tanaguru.entity.audit.PreProcessResultImpl;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 * 
 * @author jkowalczyk
 */
public class PreProcessResultDAOImpl extends AbstractJPADAO<PreProcessResult, Long>
        implements PreProcessResultDAO {

    public PreProcessResultDAOImpl() {
        super();
    }

    @Override
    protected Class<PreProcessResultImpl> getEntityClass() {
        return PreProcessResultImpl.class;
    }

    @Override
    public String findPreProcessResultByKeyAndWebResource(String key, WebResource webResource) {
        Query query = entityManager.createQuery("SELECT ppr.preProcessValue FROM "
                + getEntityClass().getName() + " ppr "
                + " WHERE "
                + " ppr.preProcessKey = :key AND"
                + " ppr.subject = :webResource");
        query.setParameter("key", key);
        query.setParameter("webResource", webResource);
        return query.getSingleResult().toString();
    }

    @Override
    public String findPreProcessResultByKeyAndAudit(String key, Audit audit) {
        Query query = entityManager.createQuery("SELECT ppr.preProcessValue FROM "
                + getEntityClass().getName() + " ppr "
                + " WHERE "
                + " ppr.preProcessKey = :key AND"
                + " ppr.audit = :audit");
        query.setParameter("key", key);
        query.setParameter("audit", audit);
        return query.getSingleResult().toString();
    }

    @Override
    public void deleteAllPreProcessResultByAudit(Audit audit) {
        Query query = entityManager.createQuery("SELECT ppr FROM "
                + getEntityClass().getName() + " ppr "
                + " WHERE "
                + " ppr.audit = :audit");
        query.setParameter("audit", audit);
        Set<PreProcessResult> pprToDelete = new HashSet<PreProcessResult>();
        pprToDelete.addAll(query.getResultList());
        for (PreProcessResult ppr : pprToDelete) {
            this.delete(ppr.getId());
        }
    }

    @Override
    public void deleteAllPreProcessResultByWebResource(WebResource webResource) {
        Query query = entityManager.createQuery("SELECT ppr FROM "
                + getEntityClass().getName() + " ppr "
                + " WHERE "
                + " ppr.subject = :webResource");
        query.setParameter("webResource", webResource);
        Set<PreProcessResult> pprToDelete = new HashSet<PreProcessResult>();
        pprToDelete.addAll(query.getResultList());
        this.delete(pprToDelete);
    }

    @Override
    public Collection<PreProcessResult> findPreProcessResultFromAudit(Audit audit) {
        Query query = entityManager.createQuery("SELECT ppr FROM "
                + getEntityClass().getName() + " ppr "
                + " WHERE "
                + " ppr.audit = :audit");
        query.setParameter("audit", audit);
        return query.getResultList();
    }
    
}