/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.webapp.entity.dao.user;

import java.util.Collection;
import javax.persistence.Query;
import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.tanaguru.webapp.entity.user.Role;
import org.tanaguru.webapp.entity.user.RoleImpl;

/**
 *
 * @author jkowalczyk
 */
public class RoleDAOImpl extends AbstractJPADAO<Role, Long> implements RoleDAO {

    public RoleDAOImpl() {
        super();
    }

    @Override
    protected Class<? extends Role> getEntityClass() {
        return RoleImpl.class;
    }

    @Override
    public Collection<Role> findAllChildRole(Long key) {
        if (key == null) {
            return null;
        }
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName() + " r"
                + " left join fetch u.childRoleSet cr"
                + " WHERE r.id = :id");
        query.setParameter("id", key);
        Role user = (Role)query.getSingleResult();
        return user.getChildRoleSet();
    }


}