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
package org.asqatasun.entity.audit.factory;

import org.asqatasun.entity.audit.*;
import org.springframework.stereotype.Component;

/**
 * 
 * @author jkowalczyk
 */
@Component("processResultFactory")
public class ProcessResultFactoryImpl implements ProcessResultFactory {

    @Override
    public ProcessResult create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DefiniteResult createDefiniteResult() {
        return new DefiniteResultImpl();
    }

    @Override
    public IndefiniteResult createIndefiniteResult() {
        return new IndefiniteResultImpl();
    }

}
