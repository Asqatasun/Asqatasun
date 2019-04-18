/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2019  Asqatasun.org
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

import com.sebuilder.interpreter.Script;
import com.sebuilder.interpreter.Step;
import com.sebuilder.interpreter.factory.ScriptFactory;
import com.sebuilder.interpreter.factory.ScriptFactory.SuiteException;
import com.sebuilder.interpreter.factory.TestRunFactory;
import com.sebuilder.interpreter.steptype.Get;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.mozilla.universalchardet.UniversalDetector;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.asqatasun.contentloader.HarFileContentLoaderFactory;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.Content;
import org.asqatasun.entity.audit.PreProcessResult;
import org.asqatasun.entity.audit.SSP;
import org.asqatasun.entity.parameterization.ParameterElement;
import org.asqatasun.entity.service.audit.ContentDataService;
import org.asqatasun.entity.service.audit.PreProcessResultDataService;
import org.asqatasun.entity.service.parameterization.ParameterDataService;
import org.asqatasun.entity.service.subject.WebResourceDataService;
import org.asqatasun.entity.subject.Page;
import org.asqatasun.entity.subject.Site;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.scenarioloader.exception.ScenarioLoaderException;
import org.asqatasun.sebuilder.interpreter.NewPageListener;
import org.asqatasun.sebuilder.interpreter.exception.TestRunException;
import org.asqatasun.sebuilder.interpreter.factory.TgStepTypeFactory;
import org.asqatasun.sebuilder.interpreter.factory.TgTestRunFactory;
import org.asqatasun.sebuilder.tools.FirefoxDriverObjectPool;
import org.asqatasun.sebuilder.tools.ProfileFactory;
import org.asqatasun.util.factory.DateFactory;

/**
 *
 * @author jkowalczyk
 */
public class ScenarioLoaderImpl implements ScenarioLoader, NewPageListener {

    private static final Logger LOGGER = Logger.getLogger(ScenarioLoaderImpl.class);
    private static final String UFT8 = "UTF-8";
    private final List<Content> result = new ArrayList<>();
    private int pageRank = 1;

    private static final int SCENARIO_IMPLICITELY_WAIT_TIMEOUT = 60;

    /** The script factory instance */
    private final ScriptFactory scriptFactory = new ScriptFactory();
    
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
    
    /**
     * 
     */
    private final ProfileFactory profileFactory;
    
    private WebResourceDataService webResourceDataService;
    public void setWebResourceDataService(WebResourceDataService webResourceDataService) {
        this.webResourceDataService = webResourceDataService;
    }
    
    private ParameterDataService parameterDataService;
    public void setParameterDataService(ParameterDataService parameterDataService) {
        this.parameterDataService = parameterDataService;
    }
    
    private ContentDataService contentDataService;
    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }
    
    private PreProcessResultDataService preProcessResultDataService;
    public void setPreProcessResultDataService(PreProcessResultDataService preProcessResultDataService) {
        this.preProcessResultDataService = preProcessResultDataService;
    }
    
    private DateFactory dateFactory;
    public void setDateFactory(DateFactory dateFactory) {
        this.dateFactory = dateFactory;
    }
    
    List<SSPInfo> sspInfoList = new LinkedList<>();
    
    private Map<String, String> jsScriptMap;
    public Map<String, String> getJsScriptMap() {
        return jsScriptMap;
    }

    public void setJsScriptMap(Map<String, String> jsScriptMap) {
        this.jsScriptMap = jsScriptMap;
    }
    
    private FirefoxDriverObjectPool firefoxDriverObjectPool;
    public FirefoxDriverObjectPool getFirefoxDriverObjectPool() {
        return firefoxDriverObjectPool;
    }

    public void setFirefoxDriverObjectPool(FirefoxDriverObjectPool firefoxDriverObjectPool) {
        this.firefoxDriverObjectPool = firefoxDriverObjectPool;
    }
    
    int implicitelyWaitDriverTimeout = -1;
    public void setImplicitelyWaitDriverTimeout(int implicitelyWaitDriverTimeout) {
        this.implicitelyWaitDriverTimeout = implicitelyWaitDriverTimeout;
    }

    int pageLoadDriverTimeout = -1;
    public void setPageLoadDriverTimeout(int pageLoadDriverTimeout) {
        this.pageLoadDriverTimeout = pageLoadDriverTimeout;
    }
    
    /**
     * The scenario
     */
    private final String scenario;

    ScenarioLoaderImpl(
            WebResource webResource, 
            String scenario) {
        super();
        this.scenario = scenario;
        this.profileFactory = ProfileFactory.getInstance();
        this.webResource = webResource;
    }
    
    ScenarioLoaderImpl(
            WebResource webResource, 
            String scenario,
            HarFileContentLoaderFactory harFileContentLoaderFactory) {
        super();
        this.scenario = scenario;
        this.profileFactory = ProfileFactory.getInstance();
        this.webResource = webResource;
    }

    @Override
    public void run() {
        try {
            LOGGER.debug("Launch Scenario "   + scenario );
            FirefoxProfile firefoxProfile;
            if (isScenarioOnlyLoadPage(scenario)) {
                LOGGER.debug("Audit page script");
                firefoxProfile = profileFactory.getOnlineProfile();
            } else {
                LOGGER.debug("Scenario script, images are loaded and implicitly "
                        + "wait timeout set");
                implicitelyWaitDriverTimeout = SCENARIO_IMPLICITELY_WAIT_TIMEOUT;
                firefoxProfile = profileFactory.getScenarioProfile();
            }

            Script script = getScriptFromScenario(scenario, firefoxProfile);
            try {
                if (script.run()) {
                    LOGGER.debug(webResource.getURL()  + " succeeded");
                } else {
                    LOGGER.debug(webResource.getURL() + " failed");
                }
            } catch (TestRunException tre) {
                // The TestRunException is caught but not as runtime, to audit
                // at least pages already fetched
                LOGGER.warn(tre.getMessage());
            } catch (RuntimeException re) {
                LOGGER.warn(re.getMessage());
                throw new ScenarioLoaderException(re);
            }    
            profileFactory.shutdownFirefoxProfile(firefoxProfile);
        } catch (IOException | JSONException | SuiteException ex) {
            LOGGER.warn(ex.getMessage());
            throw new ScenarioLoaderException(ex);
        }
    }

    /**
     * 
     * @param url
     * @param sourceCode 
     */
    private void fireNewSSP(
            String url, 
            String sourceCode, 
            byte[] snapshotContent, 
            Map<String, String> jsScriptMap) {

        LOGGER.debug("fire New SSP " + url);
        if (StringUtils.isEmpty(sourceCode)) {
            LOGGER.debug("Emtpy SSP " + url + " not saved");
            return;
        }
        String charset = UFT8;
        try {
            charset = extractCharset(IOUtils.toInputStream(sourceCode));
        } catch (IOException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
        }
        Page page = getWebResource(url);
        SSP ssp = contentDataService.getSSP(
                dateFactory.createDate(),
                url,
                sourceCode,
                page,
                HttpStatus.SC_OK);
        ssp.setCharset(charset);
        contentDataService.saveOrUpdate(ssp);
        result.add(ssp);
        
        Audit audit = null;
        if (page.getAudit() != null) {
            audit = page.getAudit();
        } else if (page.getParent().getAudit() != null) {
            audit = page.getParent().getAudit();
        }
        for (Map.Entry<String, String> entry : jsScriptMap.entrySet()) {
            PreProcessResult ppr = preProcessResultDataService.getPreProcessResult(
                    entry.getKey(), 
                    entry.getValue(),
                    audit, 
                    page);
            preProcessResultDataService.saveOrUpdate(ppr);
        }
    }
    
    @Override
    public void fireNewPage(
            String url, 
            String sourceCode, 
            byte[] snapshot, 
            Map<String, String> jsScriptMap) {
        fireNewSSP(url, sourceCode, snapshot, jsScriptMap);
    }

    /**
     * 
     * @param url
     * @return the page instance for a given URL
     */
    private Page getWebResource(String url) {
        Page page = null;
        if (webResource instanceof Page) {
            if (!StringUtils.equals(url, webResource.getURL())) {
                webResource.setURL(url);
            }
            page = (Page)webResource;
            page.setRank(1);
        } else if (webResource instanceof Site) {
            page = webResourceDataService.createPage(url);
            page.setRank(pageRank);
            pageRank++;
            ((Site) webResource).addChild(page);
        }
        page = (Page) webResourceDataService.saveOrUpdate(page);
        return page;
    }
    
    /**
     * Inner class that handle an Url and its source code
     */
    private class SSPInfo {
        
        private String url;
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        
        private String sourceCode;
        public String getSourceCode() {
            return sourceCode;
        }

        public void setSourceCode(String sourceCode) {
            this.sourceCode = sourceCode;
        }
        
        public SSPInfo(String url, String sourceCode) {
            this.url = url;
            this.sourceCode = sourceCode;
        }
    }

    /**
     * 
     * @param firefoxProfile
     * @return an initialised instance of TestRunFactory
     */
    private TestRunFactory initTestRunFactory (FirefoxProfile firefoxProfile){
        TgTestRunFactory testRunFactory = new TgTestRunFactory();
        testRunFactory.addNewPageListener(this);
        testRunFactory.setFirefoxProfile(firefoxProfile);
        testRunFactory.setJsScriptMap(jsScriptMap);
        if (implicitelyWaitDriverTimeout != -1) {
            testRunFactory.setImplicitlyWaitDriverTimeout(implicitelyWaitDriverTimeout);
        }
        testRunFactory.setPageLoadDriverTimeout(pageLoadDriverTimeout);
        
        testRunFactory.setScreenHeight(
                Integer.valueOf(
                        parameterDataService.getParameter(
                                webResource.getAudit(), ParameterElement.SCREEN_HEIGHT_KEY).getValue()));
        testRunFactory.setScreenWidth(
                Integer.valueOf(
                        parameterDataService.getParameter(
                                webResource.getAudit(), ParameterElement.SCREEN_WIDTH_KEY).getValue()));
//      ((TgTestRunFactory)testRunFactory).setFirefoxDriverObjectPool(firefoxDriverObjectPool);
        return testRunFactory;
    }

    /**
     * 
     * @param scenario
     * @param firefoxProfile
     * @return an initialised Script ready to be run.
     * 
     * @throws IOException
     * @throws JSONException 
     */
    private Script getScriptFromScenario(String scenario, FirefoxProfile firefoxProfile) throws IOException, JSONException, SuiteException {
        scriptFactory.setTestRunFactory(initTestRunFactory(firefoxProfile));
        scriptFactory.setStepTypeFactory(new TgStepTypeFactory());
        return scriptFactory.parse(scenario);
    }
 
    /**
     * Parse the steps of the scenario to determine whether it only contains 
     * Get steps. 
     * 
     * @param scenario
     * @return
     * @throws IOException
     * @throws JSONException 
     */
    private boolean isScenarioOnlyLoadPage(String scenario)  throws IOException, JSONException, SuiteException{
        Script script = scriptFactory.parse(scenario);
        for (Step step : script.steps) {
            if (!(step.type instanceof Get)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method extracts the charset from the html source code.
     * If the charset is not specified, it is set to UTF-8 by default
     * @param is
     * @return
     */
    private static String extractCharset(InputStream is) throws java.io.IOException {
        byte[] buf = new byte[4096];
        UniversalDetector detector = new UniversalDetector(null);
        int nread;
        while ((nread = is.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        detector.dataEnd();

        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            LOGGER.debug("Detected encoding = " + encoding);
        } else {
            LOGGER.debug("No encoding detected.");
        }

        detector.reset();
        if (encoding != null && isValidCharset(encoding)) {
            return encoding;
        } else {
            return UFT8;
        }
    }

    /**
     * This methods tests if a charset is valid regarding the charset nio API.
     * @param charset
     * @return
     */
    private static boolean isValidCharset(String charset) {
        try {
            Charset.forName(charset);
        } catch (UnsupportedCharsetException e) {
            return false;
        }
        return true;
    }
}
