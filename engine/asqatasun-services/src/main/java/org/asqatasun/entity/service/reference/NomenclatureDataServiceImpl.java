/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.entity.service.reference;

import java.util.HashMap;
import java.util.Map;
import org.asqatasun.entity.dao.reference.NomenclatureDAO;
import org.asqatasun.entity.reference.Nomenclature;
import org.asqatasun.entity.reference.NomenclatureElement;
import org.asqatasun.entity.service.AbstractGenericDataService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 
 * @author jkowalczyk
 */
@Service("nomenclatureDataService")
public class NomenclatureDataServiceImpl extends AbstractGenericDataService<Nomenclature, Long> implements
        NomenclatureDataService {

    private Map<String, Nomenclature> nomenclatureMapByCode = new HashMap<>();

    public NomenclatureDataServiceImpl() {
        super();
    }

    @Override
    public Nomenclature findByCode(String code) {
        if (nomenclatureMapByCode.containsKey(code)) {
            return nomenclatureMapByCode.get(code);
        } else {
            Nomenclature nom = ((NomenclatureDAO) entityDao).retrieveByCode(code);
            nomenclatureMapByCode.put(code, nom);
            return nom;
        }
    }

    @Override
    @Transactional
    public Nomenclature read(Long key) {
        Nomenclature entity = super.read(key);
        for (NomenclatureElement element : entity.getElementList()) {
        }
        return entity;
    }

}
