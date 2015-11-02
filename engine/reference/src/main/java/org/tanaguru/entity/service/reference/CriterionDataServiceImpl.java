/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.tanaguru.entity.service.reference;

import org.tanaguru.entity.dao.reference.CriterionDAO;
import org.tanaguru.entity.reference.Criterion;
import org.tanaguru.entity.reference.Level;
import org.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public class CriterionDataServiceImpl extends AbstractGenericDataService<Criterion, Long> implements
        CriterionDataService {

    public CriterionDataServiceImpl() {
        super();
    }

    @Override
    public Criterion read(Long key) {
        Criterion entity = super.read(key);
        return entity;
    }
    
    @Override
    public Level getCriterionLevel(Criterion criterion) {
        return ((CriterionDAO)entityDao).findCriterionLevel(criterion);
    }

}