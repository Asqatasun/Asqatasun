/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
import org.asqatasun.contentloader.ContentLoader;
import org.asqatasun.contentloader.ContentLoaderFactory;
import org.asqatasun.entity.audit.Content;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.util.factory.DateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author jkowalczyk
 */
@Service("contentLoaderService")
public class ContentLoaderServiceImpl implements ContentLoaderService {

    private ContentLoaderFactory contentLoaderFactory;
    private DateFactory dateFactory;

    @Autowired
    public ContentLoaderServiceImpl(ContentLoaderFactory contentLoaderFactory, DateFactory dateFactory) {
        this.contentLoaderFactory = contentLoaderFactory;
        this.dateFactory = dateFactory;
    }

    @Override
    public List<Content> loadContent(WebResource webResource, Map<String, String> fileMap) {
        ContentLoader contentLoader = contentLoaderFactory.create(fileMap, dateFactory);
        contentLoader.setWebResource(webResource);
        contentLoader.run();
        return contentLoader.getResult();
    }

}
