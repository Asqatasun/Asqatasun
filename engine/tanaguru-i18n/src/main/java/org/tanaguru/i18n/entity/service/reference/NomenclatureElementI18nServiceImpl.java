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
package org.asqatasun.i18n.entity.service.reference;

import org.asqatasun.entity.reference.NomenclatureElement;
import org.asqatasun.i18n.entity.reference.NomenclatureElementI18n;
import org.asqatasun.i18n.entity.service.AbstractGenericI18nService;
import org.asqatasun.sdk.entity.i18n.InternationalizedEntity;

/**
 * 
 * @author jkowalczyk
 */
public class NomenclatureElementI18nServiceImpl extends AbstractGenericI18nService<NomenclatureElement, Long> implements
        NomenclatureElementI18nService {

    public NomenclatureElementI18nServiceImpl() {
        super();
    }

    @Override
    protected void mergeI18nContent(NomenclatureElement entity,
            InternationalizedEntity<? extends NomenclatureElement> i18nContent) {
        entity.setLabel(((NomenclatureElementI18n) i18nContent).getValue());
    }

}