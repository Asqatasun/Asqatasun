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
package org.asqatasun.service;

import javax.xml.bind.annotation.XmlTransient;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.subject.WebResource;

/**
 * 
 * @author jkowalczyk
 */
@XmlTransient
public interface CrawlerService {

    /**
     * @param audit  the current Audit
     * @param siteUrl the URL to crawl
     * @return the site crawled
     */
    WebResource crawlSite(Audit audit, String siteUrl);

}
