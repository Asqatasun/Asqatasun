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
package org.opens.tanaguru.service;

import java.util.List;
import java.util.Map;
import org.opens.tanaguru.contentloader.ContentLoader;
import org.opens.tanaguru.contentloader.ContentLoaderFactory;
import org.opens.tanaguru.contentloader.DownloaderFactory;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.util.factory.DateFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author jkowalczyk
 */
public class ContentLoaderServiceImpl implements ContentLoaderService {

    private ContentFactory contentFactory;
    @Override
    @Autowired
    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
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
                contentFactory, 
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
                contentFactory, 
                null, 
                dateFactory,
                fileMap);
        contentLoader.setWebResource(webResource);
        contentLoader.run();
        return contentLoader.getResult();
    }

}