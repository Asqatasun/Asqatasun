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
package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.opens.tanaguru.entity.dao.reference.ReferenceDAO;

/**
 * 
 * @author jkowalczyk
 */
public class ReferenceDataServiceImpl extends AbstractGenericDataService<Reference, Long> implements
        ReferenceDataService {

    public ReferenceDataServiceImpl() {
        super();
    }

    @Override
    public Reference read(Long key) {
        Reference entity = super.read(key);
        return entity;
    }

    @Override
    public Reference getByCode(String code) {
        return ((ReferenceDAO)entityDao).retrieveByCode(code);
    }

}