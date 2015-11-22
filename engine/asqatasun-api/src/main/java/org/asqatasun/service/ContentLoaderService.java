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
package org.asqatasun.service;

import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlTransient;
import org.asqatasun.contentloader.ContentLoaderFactory;
import org.asqatasun.contentloader.DownloaderFactory;
import org.asqatasun.entity.audit.Content;
import org.asqatasun.entity.service.audit.ContentDataService;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.util.factory.DateFactory;

/**
 * 
 * @author jkowalczyk
 */
@XmlTransient
public interface ContentLoaderService {// TODO Write javadoc

    /**
     *
     * @param webResource
     * @return
     */
    List<Content> loadContent(WebResource webResource);

    /**
     *
     * @param webResource
     * @param fileMap
     * @return
     */
    List<Content> loadContent(WebResource webResource, Map<String, String> fileMap);

    /**
     *
     * @param contentDataService
     */
    void setContentDataService(ContentDataService contentDataService);

    /**
     *
     * @param contentLoaderFactory
     */
    void setContentLoaderFactory(ContentLoaderFactory contentLoaderFactory);

    /**
     *
     * @param downloaderFactory
     */
    void setDownloaderFactory(DownloaderFactory downloaderFactory);
    
    /**
     *
     * @param dateFactory
     */
    void setDateFactory(DateFactory dateFactory);

}