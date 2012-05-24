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

import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author enzolalay
 */
public class ScenarioLoaderFactoryImpl implements ScenarioLoaderFactory {

    private ContentFactory contentFactory;
    public ContentFactory getContentFactory() {
        return contentFactory;
    }

    @Autowired
    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }

    private ContentDataService contentDataService;
    @Autowired
    public void setContentDataService (ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }

    private WebResourceFactory webResourceFactory;
    public WebResourceFactory getWebResourceFactory() {
        return webResourceFactory;
    }

    @Autowired
    public void setWebResourceFactory(WebResourceFactory webResourceFactory) {
        this.webResourceFactory = webResourceFactory;
    }

    private WebResourceDataService webResourceDataService;
    @Autowired
    public void setWebResourceDataService (WebResourceDataService webResourceDataService) {
        this.webResourceDataService = webResourceDataService;
    }

    private AuditDataService auditDataService;
    @Autowired
    public void setAuditDataService (AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }

    @Override
    public ScenarioLoader create(String scenario) {
        return new ScenarioLoaderImpl(
                auditDataService,
                contentFactory,
                contentDataService,
                webResourceFactory,
                webResourceDataService,
                scenario);
    }

}