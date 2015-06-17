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
package org.tanaguru.sdk.entity.i18n.service;

import org.tanaguru.sdk.entity.Entity;
import org.tanaguru.sdk.entity.i18n.Language;
import org.tanaguru.sdk.entity.i18n.dao.GenericI18nDAO;
import org.tanaguru.sdk.entity.service.GenericDataService;
import java.io.Serializable;

/**
 * 
 * @param <E>
 * @param <K>
 * @author jkowalczyk
 */
public interface GenericI18nService<E extends Entity, K extends Serializable>
        extends GenericDataService<E, K> {

    /**
     *
     * @param language
     *            the language of the internationalized entity to find
     * @param entity
     *            the entity to be internationalized
     */
    void internationalize(Language language, E entity);

    /**
     *
     * @param i18nDao
     *            the internationalization DAO to set
     */
    void setI18nDao(GenericI18nDAO<E, K> i18nDao);

}