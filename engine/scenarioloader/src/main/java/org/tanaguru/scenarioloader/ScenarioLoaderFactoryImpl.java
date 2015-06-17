/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.scenarioloader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.tanaguru.entity.service.audit.ContentDataService;
import org.tanaguru.entity.service.audit.PreProcessResultDataService;
import org.tanaguru.entity.service.parameterization.ParameterDataService;
import org.tanaguru.entity.service.subject.WebResourceDataService;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.util.factory.DateFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author enzolalay
 */
public class ScenarioLoaderFactoryImpl implements ScenarioLoaderFactory {

//    private SnapshotDataService snapshotDataService;
//    public SnapshotDataService getSnapshotDataService() {
//        return snapshotDataService;
//    }
//
//    @Autowired
//    public void setSnapshotDataService(SnapshotDataService snapshotDataService) {
//        this.snapshotDataService = snapshotDataService;
//    }
//    
//    private SnapshotFactory snapshotFactory;
//    public SnapshotFactory getSnapshotFactory() {
//        return snapshotFactory;
//    }
//
//    @Autowired
//    public void setSnapshotFactory(SnapshotFactory snapshotFactory) {
//        this.snapshotFactory = snapshotFactory;
//    }
    
    private WebResourceDataService webResourceDataService;
    public WebResourceDataService getWebResourceDataService() {
        return webResourceDataService;
    }

    @Autowired
    public void setWebResourceDataService(WebResourceDataService webResourceDataService) {
        this.webResourceDataService = webResourceDataService;
    }
    
    private ParameterDataService parameterDataService;
    public ParameterDataService getParameterDataService() {
        return parameterDataService;
    }

    @Autowired
    public void setParameterDataService(ParameterDataService parameterDataService) {
        this.parameterDataService = parameterDataService;
    }
    
    private ContentDataService contentDataService;
    public ContentDataService getContentDataService() {
        return contentDataService;
    }

    @Autowired
    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }
    
    private PreProcessResultDataService preProcessResultDataService;
    public PreProcessResultDataService getPreProcessResultDataService() {
        return preProcessResultDataService;
    }

    @Autowired
    public void setPreProcessResultDataService(PreProcessResultDataService preProcessResultDataService) {
        this.preProcessResultDataService = preProcessResultDataService;
    }
    
//    private FirefoxDriverObjectPool firefoxDriverObjectPool;
//    public FirefoxDriverObjectPool getFirefoxDriverObjectPool() {
//        return firefoxDriverObjectPool;
//    }
//
//    @Autowired
//    public void setFirefoxDriverObjectPool(FirefoxDriverObjectPool firefoxDriverObjectPool) {
//        this.firefoxDriverObjectPool = firefoxDriverObjectPool;
//    }
    
    private Map<String, String> jsScriptMap;
    public Map<String, String> getJsScriptMap() {
        return jsScriptMap;
    }

    public void setJsScriptMap(Map<String, String> jsScriptMap) {
        if (this.jsScriptMap == null) {
            this.jsScriptMap = new HashMap<>();
        }
        for (Map.Entry<String, String> entry : jsScriptMap.entrySet()) {
            try {
                this.jsScriptMap.put(entry.getKey(), IOUtils.toString(getClass().getResourceAsStream(entry.getValue())));
            } catch (IOException ex) {
                Logger.getLogger(this.getClass()).warn(ex);
            }
        }
    }
    
    private DateFactory dateFactory;
    public DateFactory getDateFactory() {
        return dateFactory;
    }

    @Autowired
    public void setDateFactory(DateFactory dateFactory) {
        this.dateFactory = dateFactory;
    }

    int pageLoadDriverTimeout = -1;
    public void setPageLoadDriverTimeout(int pageLoadDriverTimeout) {
        this.pageLoadDriverTimeout = pageLoadDriverTimeout;
    }
    
    @Override
    public ScenarioLoader create(WebResource mainWebResource, String scenario) {
        ScenarioLoaderImpl scenarioLoader = new ScenarioLoaderImpl(
                mainWebResource, 
                scenario);
        scenarioLoader.setContentDataService(contentDataService);
        scenarioLoader.setDateFactory(dateFactory);
        scenarioLoader.setWebResourceDataService(webResourceDataService);
        scenarioLoader.setPreProcessResultDataService(preProcessResultDataService);
        scenarioLoader.setJsScriptMap(jsScriptMap);
        scenarioLoader.setPageLoadDriverTimeout(pageLoadDriverTimeout);
        scenarioLoader.setParameterDataService(parameterDataService);
//        scenarioLoader.setFirefoxDriverObjectPool(firefoxDriverObjectPool);
        return scenarioLoader;
    }

}