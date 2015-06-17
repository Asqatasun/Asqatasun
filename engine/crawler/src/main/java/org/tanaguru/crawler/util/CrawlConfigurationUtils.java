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
package org.tanaguru.crawler.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.httpclient.URIException;
import org.apache.log4j.Logger;
import org.archive.net.UURIFactory;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.util.http.HttpRequestHandler;
import org.w3c.dom.Document;

/**
 *
 * @author jkowalczyk
 */
public class CrawlConfigurationUtils {

    Map<String, HeritrixConfigurationModifier> paramModifierMap = Collections.EMPTY_MAP;
    /**
     *
     * @param paramModifierMap
     */
    public void setParameterModifierMap(Map<String, HeritrixConfigurationModifier> paramModifierMap) {
        this.paramModifierMap = paramModifierMap;
    }
    
    List<HeritrixConfigurationModifier> proxyModifierList = Collections.EMPTY_LIST;
    /**
     *
     * @param proxyModifierList
     */
    public void setProxyModifierList(List<HeritrixConfigurationModifier> proxyModifierList) {
        this.proxyModifierList = proxyModifierList;
    }
    
    HeritrixConfigurationModifier urlModifier;
    /**
     *
     * @param urlModifier
     */
    public void setUrlModifier(HeritrixConfigurationModifier urlModifier) {
        this.urlModifier = urlModifier;
    }

    /**
     *
     * @return the HeritrixConfigurationModifier used to specify the Url the
     * audit is about.
     */
    public HeritrixConfigurationModifier getUrlModifier() {
        return this.urlModifier;
    }

    /**
     * The holder that handles the unique instance of CrawlConfigurationUtils
     */
    private static class CrawlConfigurationUtilsHolder {

        private static final CrawlConfigurationUtils INSTANCE =
                new CrawlConfigurationUtils();
    }

    /**
     * Private constructor
     */
    private CrawlConfigurationUtils() {
    }

    /**
     * Singleton pattern based on the "Initialization-on-demand holder idiom".
     * See
     *
     * @http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * @return the unique instance of CrawlConfigurationUtils
     */
    public static CrawlConfigurationUtils getInstance() {
        return CrawlConfigurationUtilsHolder.INSTANCE;
    }

    /**
     * 
     * @param document
     * @param parameters
     * @param urlList
     * @return
     * @throws URIException 
     */
    public Document setUpDocument(
            Document document, 
            Set<Parameter> parameters, 
            Collection<String> urlList) throws URIException {
        document = modifyValue(
                getUrlModifier(), 
                document, 
                getUrlListAsUniqueString(urlList), 
                "");
        for (Parameter parameter : parameters) {
            document = modifyHeritrixParameter(document, parameter, urlList.iterator().next());
        }
        if (HttpRequestHandler.getInstance().isProxySet(urlList.iterator().next())) {
            for (HeritrixConfigurationModifier hcm : proxyModifierList) {
                // to respect the hcm interface, the method is called with 3 parameters
                // but the last 2 parameters are ignored (so set as empty strings). 
                // The modifier is already set  with the proxy parameters set 
                // by spring configuration on startup
                document = hcm.modifyDocument(document, "", "");
            }
        }
        return document;
    }
    
    /**
     *
     * @param document
     * @param parameter
     * @param url
     * @return the document with the parameter modified
     */
    public Document modifyHeritrixParameter(Document document, Parameter parameter, String url) {
        Logger.getLogger(this.getClass()).debug(parameter.getValue() + " " + parameter.getParameterElement().getParameterElementCode());
        HeritrixConfigurationModifier hpf =
                paramModifierMap.get(parameter.getParameterElement().getParameterElementCode());
        return modifyValue(hpf, document, parameter.getValue(), url);
    }

    /**
     *
     * @param hcm
     * @param document
     * @param value
     * @param url
     * @return the document with the parameter modified
     */
    public Document modifyValue(HeritrixConfigurationModifier hcm, Document document, String value, String url) {
        if (hcm != null && !value.equals("-1")) {
            Logger.getLogger(this.getClass()).debug("Modifier found for value " + value);
            return hcm.modifyDocument(document, value, url);
        } else {
            return document;
        }
    }

    /**
     * 
     * @param urlList
     * @return
     * @throws URIException 
     */
    private String getUrlListAsUniqueString(Collection<String> urlList) throws URIException {
        StringBuilder urls = new StringBuilder();
        for (String url : urlList) {
            // first convert the URI in unicode
            urls.append(UURIFactory.getInstance(url).getEscapedURI());
            urls.append("\r");
        }
        return urls.toString();
    }

}