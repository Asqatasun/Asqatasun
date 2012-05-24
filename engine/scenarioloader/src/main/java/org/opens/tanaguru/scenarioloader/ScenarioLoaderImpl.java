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
package org.opens.tanaguru.scenarioloader;

import java.util.ArrayList;
import java.util.Date;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import java.util.List;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;

/**
 * 
 * @author jkowalczyk
 */
public class ScenarioLoaderImpl implements ScenarioLoader {

    private List<Content> result;
    @Override
    public List<Content> getResult() {
        return result;
    }
    
    private WebResource webResource;
    public WebResource getWebResource() {
        return webResource;
    }
    
    @Override
    public void setWebResource(WebResource webResource) {
        this.webResource = webResource;
    }

    private ContentFactory contentFactory;
    @Override
    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }

    private ContentDataService contentDataService;
    @Override
    public void setContentDataService (ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }
    
    private WebResourceDataService webResourceDataService;
    @Override
    public void setWebResourceDataService (WebResourceDataService webResourceDataService) {
        this.webResourceDataService = webResourceDataService;
    }

    /**
     * The scenario
     */
    private String scenario;

    ScenarioLoaderImpl(
            AuditDataService auditDataService,
            ContentFactory contentFactory,
            ContentDataService contentDataService,
            WebResourceFactory webResourceFactory,
            WebResourceDataService webResourceDataService,
            String scenario) {
        super();

        this.contentFactory = contentFactory;
        this.contentDataService = contentDataService;
        
        this.webResourceDataService = webResourceDataService;

        this.scenario = scenario;
    }

    @Override
    public void run() {
        result = run(webResource);
    }

    private List<Content> run(WebResource webResource) {
        return new ArrayList<Content>();
    }

}