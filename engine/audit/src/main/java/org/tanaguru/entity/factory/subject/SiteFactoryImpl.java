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
 */package org.tanaguru.entity.factory.subject;

import org.tanaguru.entity.subject.Site;
import org.tanaguru.entity.subject.SiteImpl;

/**
 * 
 * @author jkowalczyk
 */
public class SiteFactoryImpl implements SiteFactory {

    public SiteFactoryImpl() {
        super();
    }

    @Override
    public Site create() {
        return new SiteImpl();
    }

    @Override
    public Site create(String url) {
        Site site = this.create();
        site.setURL(url);
        return site;
    }

}