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
package org.opens.tanaguru.entity.dao.reference;

import javax.persistence.Query;

import org.opens.tanaguru.entity.reference.Rule;
import org.opens.tanaguru.entity.reference.RuleImpl;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import org.opens.tanaguru.entity.reference.RulePackage;

/**
 * 
 * @author jkowalczyk
 */
public class RuleDAOImpl extends AbstractJPADAO<Rule, Long> implements RuleDAO {

    public RuleDAOImpl() {
        super();
    }

    @Override
    protected Class<? extends Rule> getEntityClass() {
        return RuleImpl.class;
    }

    @Override
    public Rule retrieve(Test test) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName() + " r, IN(r.testList) t"
                + " WHERE t = :test");
        query.setParameter("test", test);
        return (Rule) query.getSingleResult();
    }

    public Collection<Rule> retrieveAll(RulePackage owningPackage,
            String className) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName()
                + " r WHERE r.owningPackage = :owningPackage AND r.className = :className");
        query.setParameter("owningPackage", owningPackage);
        query.setParameter("className", className);
        return query.getResultList();
    }

}