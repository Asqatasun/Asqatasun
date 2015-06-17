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

import com.phloc.css.decl.CascadingStyleSheet;
import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.tanaguru.contentadapter.css.CSSContentAdapter;
import org.tanaguru.entity.audit.*;
import org.tanaguru.service.ProcessRemarkService;

/**
 * 
 * @author jkowalczyk
 */
public class CSSHandlerImpl implements CSSHandler {

    private boolean initialized = false;
    private SSP ssp;
    private Map<String, CascadingStyleSheet> styleMap;
    private Collection<StylesheetContent> cssOnErrorSet;
    
    private ProcessRemarkService processRemarkService;
    @Override
    public void setProcessRemarkService(ProcessRemarkService processRemarkService) {
        this.processRemarkService = processRemarkService;
    }

    public CSSHandlerImpl() {
        super();
    }

    public CSSHandlerImpl(SSP ssp) {
        this.ssp = ssp;
    }

    private void initialize() {
        if (initialized) {
            return;
        }
        initializeStyleSet();
        initializeCssOnErrorSet();
        XStream xstream = new XStream();
        for (RelatedContent relatedContent : ssp.getRelatedContentSet()) {
            if (relatedContent instanceof StylesheetContent) {
                StylesheetContent sc = (StylesheetContent) relatedContent;
                if (isStylesheetTestable(sc)) {
                    styleMap.put(((Content) relatedContent).getURI(),
                                    (CascadingStyleSheet) xstream.fromXML(
                                        (sc).getAdaptedContent()));
                } else {
                    addStylesheetOnError(sc);
                }
            }
        }
        initialized = true;
    }
    
    /**
     * This method initializes or re-initializes the set of stylesheets on error
     * related with a css
     */
    private void initializeCssOnErrorSet() {
        if (cssOnErrorSet == null) {
            cssOnErrorSet = new ArrayList<>();
        }
        cssOnErrorSet.clear();
    }

    /**
     * This method initializes or re-initializes the set of stylesheets related
     * with a css
     */
    private void initializeStyleSet() {
        if (styleMap == null) {
            styleMap = new HashMap<>();
        }
        styleMap.clear();
    }
    
    public void addStylesheetOnError(StylesheetContent css) {
        cssOnErrorSet.add(css);
    }
    
    @Override
    public CSSHandler beginSelection() {
        initialize();
        return this;
    }

    @Override
    public Collection<ProcessRemark> getRemarkList() {
        return processRemarkService.getRemarkList();
    }

    @Override
    public void setSSP(SSP ssp) {
        this.ssp = ssp;
        initialized = false;
    }

    @Override
    public Map<String, CascadingStyleSheet> getStyleSheetMap() {
        return styleMap;
    }

    @Override
    public Collection<StylesheetContent> getStyleSheetOnError() {
        return cssOnErrorSet;
    }

    /**
     * 
     * @param stylesheetContent
     * @return whether the current stylesheet can be tested
     */
    private boolean isStylesheetTestable(StylesheetContent stylesheetContent) {
        return isStylesheetStatusOk(stylesheetContent) && 
                !isStylesheetNull(stylesheetContent) 
                && !isStylesheetOnError(stylesheetContent);
    }
    
    /**
     * 
     * @param stylesheetContent
     * @return whether the current stylesheet http status code returned 200
     */
    private boolean isStylesheetStatusOk(StylesheetContent stylesheetContent) {
        return stylesheetContent.getHttpStatusCode() == HttpStatus.SC_OK;
    }
    
    /**
     * 
     * @param stylesheetContent
     * @return whether the current stylesheet is null
     */
    private boolean isStylesheetNull(StylesheetContent stylesheetContent) {
        return stylesheetContent.getAdaptedContent() == null;
    }
    
    /**
     * 
     * @param stylesheetContent
     * @return whether an error occured while parsing the current stylesheet
     */
    private boolean isStylesheetOnError(StylesheetContent stylesheetContent) {
        return stylesheetContent.getAdaptedContent().
                startsWith(CSSContentAdapter.CSS_ON_ERROR);
    }
}
