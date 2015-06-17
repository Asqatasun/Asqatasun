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
package org.tanaguru.persistence.i18n.reference;

import org.tanaguru.i18n.entity.dao.reference.LevelI18nDAO;
import org.tanaguru.entity.reference.Level;
import org.tanaguru.i18n.entity.reference.LevelI18nImpl;
import org.tanaguru.persistence.i18n.AbstractJPAI18nDAO;

/**
 * 
 * @author jkowalczyk
 */
public class LevelI18nDAOImpl extends AbstractJPAI18nDAO<Level, Long> implements
        LevelI18nDAO {

    public LevelI18nDAOImpl() {
        super();
    }

    @Override
    protected Class<LevelI18nImpl> getEntityClass() {
        return LevelI18nImpl.class;
    }

}