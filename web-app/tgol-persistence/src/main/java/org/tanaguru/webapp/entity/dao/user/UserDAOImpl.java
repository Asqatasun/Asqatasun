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

import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.tanaguru.webapp.entity.user.User;
import org.tanaguru.webapp.entity.user.UserImpl;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author jkowalczyk
 */
public class UserDAOImpl extends AbstractJPADAO<User, Long> implements UserDAO {

    public UserDAOImpl() {
        super();
    }

    @Override
    protected Class<? extends User> getEntityClass() {
        return UserImpl.class;
    }

    @Override
    public User read(Long key) {
        if (key == null) {
            return null;
        }
        Query query = entityManager.createQuery("SELECT u FROM "
                + getEntityClass().getName() + " u"
                + " left join fetch u.contractSet c"
                + " WHERE u.id = :id");
        query.setParameter("id", key);
        try {
            return (User)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User findUserFromEmail(String email) {
        Query query = entityManager.createQuery("SELECT u FROM "
                + getEntityClass().getName() + " u"
                + " left join fetch u.contractSet c"
//                + " left join fetch c.actSet a"
                + " WHERE u.email = :email");
        query.setParameter("email", email);
        try {
            return (User)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User findUserFromName(String name) {
        Query query = entityManager.createQuery("SELECT u FROM "
                + getEntityClass().getName() + " u"
                + " left join fetch u.contractSet c"
                + " WHERE u.name = :name");
        query.setParameter("name", name);
        try {
            return (User)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean isAccountActivated(String email) {
        Query query = entityManager.createQuery("SELECT u FROM "
                + getEntityClass().getName() + " u"
                + " WHERE u.email = :email");
        query.setParameter("email", email);
        try {
            return ((User)query.getSingleResult()).isAccountActivated();
        } catch (NoResultException e) {
            return false;
        }
    }

}