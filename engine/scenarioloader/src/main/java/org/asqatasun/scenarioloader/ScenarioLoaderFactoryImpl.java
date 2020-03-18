/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2015  Asqatasun.org
 *
 *  This file is part of Asqatasun.
 *
 *  Asqatasun is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.scenarioloader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.asqatasun.entity.service.audit.ContentDataService;
import org.asqatasun.entity.service.audit.PreProcessResultDataService;
import org.asqatasun.entity.service.parameterization.ParameterDataService;
import org.asqatasun.entity.service.subject.WebResourceDataService;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.util.factory.DateFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author enzolalay
 */
@Component("scenarioLoaderFactory")
public class ScenarioLoaderFactoryImpl implements ScenarioLoaderFactory {

    private final static Logger LOGGER = LoggerFactory.getLogger(ScenarioLoaderFactoryImpl.class);

    private WebResourceDataService webResourceDataService;
    private ParameterDataService parameterDataService;
    private ContentDataService contentDataService;
    private PreProcessResultDataService preProcessResultDataService;
    private DateFactory dateFactory;

    @Autowired
    public ScenarioLoaderFactoryImpl(WebResourceDataService webResourceDataService,
                                     ParameterDataService parameterDataService,
                                     ContentDataService contentDataService,
                                     PreProcessResultDataService preProcessResultDataService,
                                     DateFactory dateFactory) {
        this.webResourceDataService = webResourceDataService;
        this.parameterDataService = parameterDataService;
        this.contentDataService = contentDataService;
        this.preProcessResultDataService = preProcessResultDataService;
        this.dateFactory = dateFactory;
    }

    @Value("${colorExtractor:/js/jsExtractor.js}")
    private String colorExtractor;
    public Map<String, String> getJsScriptMap() {
        Map<String, String> jsScriptMap = new HashMap<>();
        try {
            jsScriptMap.put("colorExtractor", IOUtils.toString(getClass().getResourceAsStream(colorExtractor)));
        } catch (IOException ex) {
            LOGGER.warn(ex.getMessage());
        }
        return jsScriptMap;
    }

    @Value("${app.engine.loader.timeout:-1}")
    int pageLoadDriverTimeout;

    @Override
    public ScenarioLoader create(WebResource mainWebResource, String scenario) {
        ScenarioLoaderImpl scenarioLoader = new ScenarioLoaderImpl(
            mainWebResource,
            scenario);
        scenarioLoader.setContentDataService(contentDataService);
        scenarioLoader.setDateFactory(dateFactory);
        scenarioLoader.setWebResourceDataService(webResourceDataService);
        scenarioLoader.setPreProcessResultDataService(preProcessResultDataService);
        scenarioLoader.setJsScriptMap(getJsScriptMap());
        scenarioLoader.setPageLoadDriverTimeout(pageLoadDriverTimeout);
        scenarioLoader.setParameterDataService(parameterDataService);
//        scenarioLoader.setFirefoxDriverObjectPool(firefoxDriverObjectPool);
        return scenarioLoader;
    }

}
