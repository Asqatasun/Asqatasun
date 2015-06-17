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
package org.tanaguru.nomenclatureloader;

import org.tanaguru.entity.reference.Nomenclature;
import org.tanaguru.entity.service.reference.NomenclatureDataService;

/**
 * 
 * @author jkowalczyk
 */
public class NomenclatureLoaderImpl implements NomenclatureLoader {

    private String code;
    private NomenclatureDataService nomenclatureDataService;
    private Nomenclature result;

    public NomenclatureLoaderImpl() {
        super();
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Nomenclature getResult() {
        return result;
    }

    @Override
    public void run() {
        result = nomenclatureDataService.findByCode(code);
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public void setNomenclatureDataService(
            NomenclatureDataService nomenclatureDataService) {
        this.nomenclatureDataService = nomenclatureDataService;
    }

}