/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
package org.opens.tgol.entity.decorator.tanaguru.reference;

import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.opens.tgol.entity.dao.tanaguru.reference.TgolLevelDAO;
import org.opens.tanaguru.entity.reference.Level;
import org.opens.tanaguru.entity.service.reference.LevelDataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class LevelDataServiceDecoratorImpl extends AbstractGenericDataService<Level, Long>
        implements LevelDataServiceDecorator{

    private LevelDataService decoratedLevelDataService; // the Level data service instance being decorated
    private TgolLevelDAO tgolLevelDAO;

    @Autowired
    public LevelDataServiceDecoratorImpl (LevelDataService decoratedLevelDataService) {
        this.decoratedLevelDataService = decoratedLevelDataService;
    }

    @Override
    public Level getLevelByCode(String code) {
        return tgolLevelDAO.retrieveLevelByCode(code);
    }

    @Override
    public Level getByCode(String code) {
        return decoratedLevelDataService.getByCode(code);
    }

}