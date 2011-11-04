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
package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessRemarkImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Query;
import org.opens.tanaguru.entity.audit.ProcessResult;

/**
 * 
 * @author jkowalczyk
 */
public class ProcessRemarkDAOImpl extends AbstractJPADAO<ProcessRemark, Long>
        implements ProcessRemarkDAO {

    public ProcessRemarkDAOImpl() {
        super();
    }

    @Override
    protected Class<ProcessRemarkImpl> getEntityClass() {
        return ProcessRemarkImpl.class;
    }

    @Override
    public Collection<ProcessRemark> retrieveAllByProcessResult(
            ProcessResult processResult) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName() + " r"
                + " left join fetch r.elementList e"
                + " WHERE r.processResult = :processResult");
        query.setParameter("processResult", processResult);
        Set setItems = new LinkedHashSet(query.getResultList());
        return setItems;
    }
}
