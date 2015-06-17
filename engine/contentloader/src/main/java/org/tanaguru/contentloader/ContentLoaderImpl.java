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
package org.tanaguru.contentloader;

import java.util.ArrayList;
import java.util.List;
import org.tanaguru.entity.audit.Content;
import org.tanaguru.entity.service.audit.ContentDataService;
import org.tanaguru.entity.subject.Page;
import org.tanaguru.entity.subject.Site;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.util.factory.DateFactory;

/**
 * 
 * @author jkowalczyk
 */
public class ContentLoaderImpl implements ContentLoader {

    private static final int HTTP_CODE_OK = 200;
    private final ContentDataService contentDataService;
    private final DateFactory dateFactory;
    private final Downloader downloader;
    private List<Content> result;
    private WebResource webResource;
    @Override
    public void setWebResource(WebResource webResource) {
        this.webResource = webResource;
    }

    /**
     * Constructor
     * 
     * @param contentDataService
     * @param downloader
     * @param dateFactory 
     */
    ContentLoaderImpl(
            ContentDataService contentDataService, 
            Downloader downloader, 
            DateFactory dateFactory) {
        super();
        this.contentDataService = contentDataService;
        this.downloader = downloader;
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

    private List<Content> run(WebResource webResource) {// TODO Handle exceptions like 404, 403, 500, ...
        List<Content> localResult = new ArrayList<>();

        if (webResource instanceof Page) {
            downloader.setURL(webResource.getURL());
            downloader.run();
            String stringContent = downloader.getResult();
            Content content = contentDataService.getSSP(
                    dateFactory.createDate(),
                    webResource.getURL(),
                    stringContent,
                    (Page) webResource,
                    HTTP_CODE_OK);
            localResult.add(content);
        }
        if (webResource instanceof Site) {
            Site site = (Site) webResource;
            for (WebResource component : site.getComponentList()) {
                localResult.addAll(run(component));
            }
        }

        return localResult;
    }

}