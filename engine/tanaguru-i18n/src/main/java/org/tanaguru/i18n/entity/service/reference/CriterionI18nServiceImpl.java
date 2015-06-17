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
package org.tanaguru.i18n.entity.service.reference;

import org.tanaguru.entity.reference.Criterion;
import org.tanaguru.i18n.entity.reference.CriterionI18n;
import org.tanaguru.i18n.entity.service.AbstractGenericI18nService;
import org.tanaguru.sdk.entity.i18n.InternationalizedEntity;

/**
 * 
 * @author jkowalczyk
 */
public class CriterionI18nServiceImpl extends AbstractGenericI18nService<Criterion, Long> implements
        CriterionI18nService {

    public CriterionI18nServiceImpl() {
        super();
    }

    @Override
    protected void mergeI18nContent(Criterion entity,
            InternationalizedEntity<? extends Criterion> i18nContent) {
        entity.setDescription(((CriterionI18n) i18nContent).getDescription());
        entity.setLabel(((CriterionI18n) i18nContent).getLabel());
    }

}