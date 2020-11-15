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
package org.asqatasun.contentloader;

import java.util.Map;
import org.asqatasun.entity.service.audit.ContentDataService;
import org.asqatasun.util.factory.DateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author jkowalczyk
 */
@Component("fileContentLoaderFactory")
public class FileContentLoaderFactoryImpl implements ContentLoaderFactory {

    private final ContentDataService contentDataService;

    @Autowired
    public FileContentLoaderFactoryImpl(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }

    @Override
    public ContentLoader create(Map<String, String> fileMap, DateFactory dateFactory) {
        return new FileContentLoaderImpl(contentDataService, dateFactory, fileMap);
    }

}
