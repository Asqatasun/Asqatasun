/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.entity.dao.option;

import java.util.Collection;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import org.asqatasun.entity.dao.AbstractJPADAO;
import org.asqatasun.entity.option.Option;
import org.asqatasun.entity.option.OptionElementImpl;
import org.asqatasun.entity.user.User;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jkowalczyk
 */
@Repository("optionElementDAO")
public class OptionElementDAO extends AbstractJPADAO<OptionElementImpl, Long> {

    public OptionElementDAO() {
        super();
    }

    @Override
    protected Class<? extends OptionElementImpl> getEntityClass() {
        return OptionElementImpl.class;
    }
    
    private Class<? extends User> getUserEntityClass() {
        return User.class;
    }

    /**
     * 
     * @param value
     * @param option
     * @return 
     */
    public OptionElementImpl findOptionElementFromValueAndOption(String value, Option option) {
        Query query = entityManager.createQuery("SELECT oe FROM "
                + getEntityClass().getName() + " oe"
                + " WHERE oe.value=:value"
                + " AND oe.option=:option");
        query.setParameter("value", value);
        query.setParameter("option", option);
        try {
            return (OptionElementImpl) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    public Collection<OptionElementImpl> findOptionElementFromUserAndFamilyCode(User user, String optionFamilyCode) {
        Query query = entityManager.createQuery("SELECT distinct(u.optionElementSet) FROM "
                + getUserEntityClass().getName() + " u"
                + " LEFT JOIN u.optionElementSet oe"
                + " WHERE u=:user"
                + " AND oe.option.optionFamily.code=:optionFamilyCode");
        query.setParameter("user", user);
        query.setParameter("optionFamilyCode", optionFamilyCode);
        try {
            return (Collection<OptionElementImpl>) query.getResultList();
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    public Collection<OptionElementImpl> findOptionElementFromUser(User user) {
        Query query = entityManager.createQuery("SELECT u.optionElementSet FROM "
                + getUserEntityClass().getName() + " u"
                + " WHERE u=:user");
        query.setParameter("user", user);
        try {
            return (Collection<OptionElementImpl>) query.getResultList();
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }


}
