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
package org.asqatasun.contentadapter.js;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.asqatasun.contentadapter.ContentAdapter;
import org.asqatasun.contentadapter.Resource;
import org.asqatasun.contentadapter.util.URLIdentifier;
import org.asqatasun.contentloader.Downloader;
import org.asqatasun.entity.audit.Content;
import org.asqatasun.entity.audit.SSP;
import org.asqatasun.entity.service.audit.ContentDataService;

/**
 * 
 * @author jkowalczyk
 */
public abstract class AbstractContentAdapter implements ContentAdapter {

    private Downloader downloader;
    public Downloader getDownloader() {
        return downloader;
    }
    
    public void setDownloader(Downloader downloader) {
        this.downloader = downloader;
    }

    protected Resource resource;
    
    private SSP ssp;
    @Override
    public SSP getSSP() {
        return ssp;
    }

    @Override
    public void setSSP(SSP ssp) {
        this.ssp = ssp;
        contentList = new ArrayList<>();
        try {
            urlIdentifier.setUrl(new URL(ssp.getURI()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(AbstractContentAdapter.class.getName()).warn(ex);
        }
    }

    private URLIdentifier urlIdentifier;
    public URLIdentifier getUrlIdentifier() {
        return urlIdentifier;
    }
    
    @Override
    public void setURLIdentifier(URLIdentifier urlIdentifier) {
        this.urlIdentifier = urlIdentifier;
    }
    
    private final ContentDataService contentDataService;
    public ContentDataService getContentDataService() {
        return contentDataService;
    }

    private List<Content> contentList = new ArrayList<>();
    @Override
    public List<Content> getContentList() {
        return contentList;
    }

    /**
     * 
     * @param urlIdentifier
     * @param downloader
     * @param contentDataService
     */
    public AbstractContentAdapter(
            URLIdentifier urlIdentifier,
            Downloader downloader,
            ContentDataService contentDataService) {
        super();
        this.urlIdentifier = urlIdentifier;
        this.downloader = downloader;
        this.contentDataService = contentDataService;
    }

}