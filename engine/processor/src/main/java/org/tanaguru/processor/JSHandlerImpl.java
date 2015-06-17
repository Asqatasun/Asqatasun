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
package org.tanaguru.processor;

import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.tanaguru.contentadapter.RsrcLocator;
import org.tanaguru.contentadapter.js.JSResource;
import org.tanaguru.entity.audit.JavascriptContent;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.RelatedContent;
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.entity.service.audit.ProcessRemarkDataService;

/**
 * 
 * @author jkowalczyk
 */
public class JSHandlerImpl implements JSHandler {

    private boolean initialized = false;
    private Set<JSResource> javaScriptSet;
    private ProcessRemarkDataService processRemarkDataService;
    private Collection<ProcessRemark> remarkList;
    private Collection<JSResource> selectedJSList;
    private SSP ssp;

    public JSHandlerImpl() {
        super();
    }

    public JSHandlerImpl(SSP ssp) {
        this.ssp = ssp;
    }

    @Override
    public JSHandler beginSelection() {
        initialize();

        selectedJSList = new ArrayList<>();
        remarkList = new ArrayList<>();
        return this;
    }

    public ProcessRemarkDataService getProcessRemarkDataService() {
        return processRemarkDataService;
    }

    @Override
    public Collection<ProcessRemark> getRemarkList() {
        return remarkList;
    }

    @Override
    public SSP getSSP() {
        return ssp;
    }

    private void initialize() {
        if (initialized) {
            return;
        }

        XStream xstream = new XStream();

        for (RelatedContent relatedContent : ssp.getRelatedContentSet()) {
            if (relatedContent instanceof JavascriptContent) {
                if (javaScriptSet == null) {
                    javaScriptSet = new HashSet<>();
                }
                javaScriptSet.addAll(
                        (Set<JSResource>) xstream.fromXML(
                        ((JavascriptContent) relatedContent).getAdaptedContent()));
            }
        }
        initialized = true;
    }

    @Override
    public JSHandler selectAllJS() {
        selectedJSList = javaScriptSet;
        return this;
    }

    @Override
    public JSHandler selectExternalJS() {
        Collection<JSResource> externalJsList = new ArrayList<>();
        for (JSResource jsResource : javaScriptSet) {
            RsrcLocator location = jsResource.getRsrcLocator();
            if (location.getRsrcLocatorType() == RsrcLocator.EXTERNAL) {
                externalJsList.add(jsResource);
            }
        }
        selectedJSList = externalJsList;
        return this;
    }

    @Override
    public JSHandler selectInlineJS() {
        Collection<JSResource> inlineJsList = new ArrayList<>();
        for (JSResource jsResource : javaScriptSet) {
            RsrcLocator location = jsResource.getRsrcLocator();
            if (location.getRsrcLocatorType() == RsrcLocator.INLINE) {
                inlineJsList.add(jsResource);
            }
        }
        selectedJSList = inlineJsList;
        return this;
    }

    @Override
    public JSHandler selectLocalJS() {
        Collection<JSResource> localJsList = new ArrayList<>();
        for (JSResource jsResource : javaScriptSet) {
            RsrcLocator location = jsResource.getRsrcLocator();
            if (location.getRsrcLocatorType() == RsrcLocator.LOCAL) {
                localJsList.add(jsResource);
            }
        }
        selectedJSList = localJsList;
        return this;
    }

    @Override
    public void setProcessRemarkDataService(
            ProcessRemarkDataService processRemarkDataService) {
        this.processRemarkDataService = processRemarkDataService;
    }

    @Override
    public void setRemarkList(Collection<ProcessRemark> remarkList) {
        this.remarkList = remarkList;
    }

    @Override
    public void setSSP(SSP ssp) {
        this.ssp = ssp;
        initialized = false;
    }

}