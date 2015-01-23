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
package org.opens.tgol.entity.dao.tanaguru.reference;

import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.List;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.dao.reference.LevelDAO;
import org.opens.tanaguru.entity.reference.Level;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.TestImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class implements the TgolTestDAO interface.
 * @author jkowalczyk
 */
public class TgolTestDAOImpl extends AbstractJPADAO<Test, Long>
        implements TgolTestDAO {

    /**
     * The gold level code
     */
    private static final String GOLD_LEVEL = "Or";
    /**
     * The silver level code
     */
    private static final String SILVER_LEVEL = "Ar";
    /**
     * The bronze level id index
     */
    private static final int BRONZE_ID_INDEX = 1;
    /**
     * The bronze level
     */
    private Level bronzeLevel;

    /**
     * Default constructor
     */
    @Autowired
    public TgolTestDAOImpl (LevelDAO levelDAO) {
        super();
        bronzeLevel = levelDAO.read(Long.valueOf(BRONZE_ID_INDEX));
    }

    @Override
    protected Class<? extends Test> getEntityClass() {
        return TestImpl.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Test> retrieveAllByLevel(Level level) {
        if (StringUtils.equalsIgnoreCase(level.getCode(), GOLD_LEVEL)) {
            return this.findAll();
        } else  {
            StringBuilder queryStr = new StringBuilder();
            queryStr.append("SELECT t FROM ");
            queryStr.append(getEntityClass().getName());
            queryStr.append(" t WHERE t.level = :bronzeLevel");
            if (StringUtils.equalsIgnoreCase(level.getCode(), SILVER_LEVEL)) {
                queryStr.append(" OR t.level = :silverLevel");
            }
            Query query = entityManager.createQuery(queryStr.toString());
            query.setParameter("bronzeLevel", bronzeLevel);
            if (StringUtils.equalsIgnoreCase(level.getCode(), SILVER_LEVEL)) {
                query.setParameter("silverLevel", level);
            }
            query.setHint("org.hibernate.cacheable", "true");
            return query.getResultList();
        }
    }

}