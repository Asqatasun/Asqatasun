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
package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.SSPImpl;
import org.opens.tanaguru.entity.subject.Page;
import java.util.Date;

/**
 * 
 * @author jkowalczyk
 */
public class SSPFactoryImpl implements SSPFactory {

    public SSPFactoryImpl() {
        super();
    }

    @Override
    public SSP create() {
        return new SSPImpl();
    }

    @Override
    public SSP create(Date dateOfLoading, String uri) {
        return new SSPImpl(dateOfLoading, uri);
    }

    @Override
    public SSP create(Date dateOfLoading, String uri, String sourceCode,
            Page page) {
        return new SSPImpl(dateOfLoading, uri, sourceCode, page);
    }
    
}