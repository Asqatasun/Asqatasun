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
package org.tanaguru.webapp.entity.factory.contract;

import java.util.Date;
import org.tanaguru.webapp.entity.contract.Act;
import org.tanaguru.webapp.entity.contract.ActImpl;
import org.tanaguru.webapp.entity.contract.Contract;

/**
 *
 * @author jkowalczyk
 */
public class ActFactoryImpl implements ActFactory {

    @Override
    public Act createAct(Date date, Contract contract) {
        Act act = create();
        act.setBeginDate(date);
        act.setContract(contract);
        return act;
    }

    @Override
    public Act create() {
        return new ActImpl();
    }

}