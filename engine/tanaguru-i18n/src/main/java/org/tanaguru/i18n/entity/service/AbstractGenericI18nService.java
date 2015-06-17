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
package org.tanaguru.i18n.entity.service;

import java.io.Serializable;
import org.tanaguru.sdk.entity.Entity;
import org.tanaguru.sdk.entity.i18n.InternationalizedEntity;
import org.tanaguru.sdk.entity.i18n.Language;
import org.tanaguru.sdk.entity.i18n.dao.GenericI18nDAO;
import org.tanaguru.sdk.entity.i18n.service.GenericI18nService;
import org.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author jkowalczyk
 * @param <E>
 * @param <K>
 */
public abstract class AbstractGenericI18nService<E extends Entity, K extends Serializable>
        extends AbstractGenericDataService<E, K> implements
        GenericI18nService<E, K> {

    protected GenericI18nDAO<E, K> i18nDao;

    public AbstractGenericI18nService() {
        super();
    }

    @Override
    public void internationalize(Language language, E entity) {
        InternationalizedEntity<? extends E> i18nContent = i18nDao.find(
                language, entity);
        if (i18nContent != null) {
            mergeI18nContent(entity, i18nContent);
        }
    }

    protected abstract void mergeI18nContent(E entity,
            InternationalizedEntity<? extends E> i18nContent);

    @Override
    public void setI18nDao(GenericI18nDAO<E, K> i18nDao) {
        this.i18nDao = i18nDao;
    }

}