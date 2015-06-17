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
package org.tanaguru.entity.service.reference;

import java.util.HashMap;
import java.util.Map;
import org.tanaguru.entity.dao.reference.ReferenceDAO;
import org.tanaguru.entity.reference.Reference;
import org.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public class ReferenceDataServiceImpl extends AbstractGenericDataService<Reference, Long> implements
        ReferenceDataService {

    private Map<String, Reference> referenceMap = new HashMap<String,Reference>();
    
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
        if (referenceMap.containsKey(code)) {
            return referenceMap.get(code);
        }
        Reference reference = ((ReferenceDAO)entityDao).retrieveByCode(code);
        referenceMap.put(code, reference);
        return reference;
    }

}