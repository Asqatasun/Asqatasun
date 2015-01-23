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
package org.opens.tanaguru.contentloader;

import java.util.ArrayList;
import java.util.Map;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import java.util.Date;
import java.util.List;
import org.apache.commons.httpclient.HttpStatus;
import org.opens.tanaguru.util.FileNaming;
import org.opens.tanaguru.util.factory.DateFactory;

/**
 * 
 * @author jkowalczyk
 */
public class FileContentLoaderImpl implements ContentLoader {

    /**
     * THe content factory instance needed to create the SSP
     */
    private ContentFactory contentFactory;
    
    /**
     * The fileMap to work on
     */
    private Map<String, String> fileMap;
    
    /**
     * The list of created content
     */
    private List<Content> result;
    
    private WebResource webResource;
    public WebResource getWebResource() {
        return this.webResource;
    }
    
    @Override
    public void setWebResource(WebResource webResource) {
        this.webResource = webResource;
    }
    
    /**
     * The dateFactory instance
     */
    private DateFactory dateFactory;

    /**
     * Constructor
     * 
     * @param contentFactory
     * @param fileMap
     * @param contentDataService
     */
    FileContentLoaderImpl(
            ContentFactory contentFactory,
            Map<String, String> fileMap, 
            DateFactory dateFactory) {
        super();
        this.contentFactory = contentFactory;
        this.fileMap = fileMap;
        this.dateFactory = dateFactory;
    }

    @Override
    public List<Content> getResult() {
        return result;
    }

    @Override
    public void run() {
        result = run(webResource);
    }

    /**
     * 
     * @param webResource
     * @return 
     */
    private List<Content> run(WebResource webResource) {
        List<Content> localResult = new ArrayList<Content>();
        if (webResource instanceof Page) {
            Content content = contentFactory.createSSP(
                    dateFactory.createDate(),
                    webResource.getURL(),
                    fileMap.get(FileNaming.removeFilePrefix(webResource.getURL())),
                    (Page) webResource,
                    HttpStatus.SC_OK);
            localResult.add(content);
        } else if (webResource instanceof Site) {
            Site site = (Site) webResource;
            for (WebResource component : site.getComponentList()) {
                localResult.addAll(run(component));
            }
        }
        return localResult;
    }

}