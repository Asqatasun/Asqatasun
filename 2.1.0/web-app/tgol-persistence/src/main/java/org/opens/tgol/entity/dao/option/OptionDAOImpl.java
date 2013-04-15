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
package org.opens.tgol.entity.dao.option;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.tgol.entity.option.Option;
import org.opens.tgol.entity.option.OptionImpl;

/**
 *
 * @author jkowalczyk
 */
public class OptionDAOImpl extends AbstractJPADAO<Option, Long>
        implements OptionDAO {

    public OptionDAOImpl() {
        super();
    }

    @Override
    protected Class<? extends Option> getEntityClass() {
        return OptionImpl.class;
    }

    @Override
    public Option findOptionFromCode(String optionCode) {
        Query query = entityManager.createQuery("SELECT o FROM "
                + getEntityClass().getName() + " o"
                + " WHERE o.code = :optionCode");
        query.setParameter("optionCode", optionCode);
        try {
            return (Option) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (NonUniqueResultException nure) {
            return null;
        }
    }

}