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
package org.tanaguru.service;

import java.util.List;
import java.util.Map;
import org.tanaguru.contentloader.ContentLoader;
import org.tanaguru.contentloader.ContentLoaderFactory;
import org.tanaguru.contentloader.DownloaderFactory;
import org.tanaguru.entity.audit.Content;
import org.tanaguru.entity.service.audit.ContentDataService;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.util.factory.DateFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author jkowalczyk
 */
public class ContentLoaderServiceImpl implements ContentLoaderService {

    private ContentDataService contentDataService;
    @Override
    @Autowired
    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }

    private ContentLoaderFactory contentLoaderFactory;
    @Override
    @Autowired
    public void setContentLoaderFactory(ContentLoaderFactory contentLoaderFactory) {
        this.contentLoaderFactory = contentLoaderFactory;
    }

    private DownloaderFactory downloaderFactory;
    @Override
    @Autowired
    public void setDownloaderFactory(DownloaderFactory downloaderFactory) {
        this.downloaderFactory = downloaderFactory;
    }
    
    private DateFactory dateFactory;
    @Override
    @Autowired
    public void setDateFactory(DateFactory dateFactory) {
        this.dateFactory = dateFactory;
    }

    public ContentLoaderServiceImpl() {
        super();
    }

    @Override
    public List<Content> loadContent(WebResource webResource) {
        ContentLoader contentLoader = contentLoaderFactory.create(
                contentDataService, 
                downloaderFactory.create(), 
                dateFactory,
                null);
        contentLoader.setWebResource(webResource);
        contentLoader.run();
        return contentLoader.getResult();
    }

    @Override
    public List<Content> loadContent(WebResource webResource, Map<String, String> fileMap) {
        ContentLoader contentLoader = contentLoaderFactory.create(
                contentDataService, 
                null, 
                dateFactory,
                fileMap);
        contentLoader.setWebResource(webResource);
        contentLoader.run();
        return contentLoader.getResult();
    }

}