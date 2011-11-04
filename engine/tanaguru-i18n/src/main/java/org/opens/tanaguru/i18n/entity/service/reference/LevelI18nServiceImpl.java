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
package org.opens.tanaguru.i18n.entity.service.reference;

import org.opens.tanaguru.entity.reference.Level;
import org.opens.tanaguru.i18n.entity.reference.LevelI18n;
import org.opens.tanaguru.i18n.entity.service.AbstractGenericI18nService;
import org.opens.tanaguru.sdk.entity.i18n.InternationalizedEntity;

/**
 * 
 * @author jkowalczyk
 */
public class LevelI18nServiceImpl extends AbstractGenericI18nService<Level, Long> implements LevelI18nService {

    public LevelI18nServiceImpl() {
        super();
    }

    @Override
    protected void mergeI18nContent(Level entity,
            InternationalizedEntity<? extends Level> i18nContent) {
        entity.setDescription(((LevelI18n) i18nContent).getDescription());
        entity.setLabel(((LevelI18n) i18nContent).getLabel());
    }

}