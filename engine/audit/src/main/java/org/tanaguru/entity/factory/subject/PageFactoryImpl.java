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
package org.tanaguru.entity.factory.subject;

import org.tanaguru.entity.subject.Page;
import org.tanaguru.entity.subject.PageImpl;

/**
 * 
 * @author jkowalczyk
 */
public class PageFactoryImpl implements PageFactory {

    public PageFactoryImpl() {
        super();
    }

    @Override
    public Page create() {
        return new PageImpl();
    }

    @Override
    public Page create(String url) {
        Page page = new PageImpl();
        page.setURL(url);
        return page;
    }

}