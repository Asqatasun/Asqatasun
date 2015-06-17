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
package org.tanaguru.contentadapter.css;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.apache.log4j.Logger;
import org.tanaguru.contentadapter.AdaptationListener;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.entity.audit.StylesheetContent;
import org.tanaguru.entity.service.audit.ContentDataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class ExternalCSSRetrieverImpl implements ExternalCSSRetriever, AdaptationListener {

    private static final Logger LOGGER = Logger.getLogger(ExternalCSSRetrieverImpl.class);
    private Map<Long, Collection<StylesheetContent>> externalCssMap =
            new HashMap<Long, Collection<StylesheetContent>>();

//    private static final String CSS_ON_ERROR = "CSS_ON_ERROR";
    private boolean persistOnTheFly = true;
    private ContentDataService contentDataService;

    @Autowired
    public ExternalCSSRetrieverImpl(ContentDataService contentDataService){
        this.contentDataService = contentDataService;
    }

    @Override
    public Collection<StylesheetContent> getExternalCSS(SSP ssp) {
        if (externalCssMap.containsKey(ssp.getAudit().getId())) {
            if (LOGGER.isDebugEnabled()) {
               LOGGER.debug("External css found in the local map");
            }
            return externalCssMap.get(ssp.getAudit().getId());
        } else {
            if (LOGGER.isDebugEnabled()) {
               LOGGER.debug("External css cannot be found in the local map");
            }
            return new HashSet<StylesheetContent>();
        }
    }

    @Override
    public void adaptationStarted(Audit audit) {
        retrieveExternalCSS(audit);
    }

    @Override
    public void adaptationCompleted(Audit audit) {
        checkAllExternalCssHaveBeenAdapted(audit);
        removeExternalCSS(audit);
    }

    /**
     * This methods retrieves all the external css associated with an audit.
     * Due to optimisation reasons, this operation has to be done only once.
     * In this case, this operation is realized when the adaptation phase starts.
     * @param audit
     */
    private void retrieveExternalCSS(Audit audit) {
        if (persistOnTheFly) {
            externalCssMap.put(
                    audit.getId(),
                    contentDataService.getExternalStylesheetFromAudit(audit));
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Retrieved " + externalCssMap.get(audit.getId()).size()+
                    " external css  for the audit n "+ audit.getId());
        }
    }

    /**
     * This methods removes all the external css associated with a given audit
     * In this case, this operation is realized when the adaptation phase is completed.
     *
     * @param audit
     */
    private void removeExternalCSS(Audit audit) {
        externalCssMap.remove(audit.getId());
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Remove External CSS for the audit n° " + audit.getId());
        }
    }

    /**
     * At the end of the adaptation, if some css haven't been encountered for any 
     * reason, 
     *
     * @param audit
     */
    private void checkAllExternalCssHaveBeenAdapted(Audit audit) {
        if (!externalCssMap.containsKey(audit.getId())) {
            LOGGER.debug("No Css found for the id " + audit.getId());
            return;
        }
        Collection<StylesheetContent> externalStyleSheet = externalCssMap.get(audit.getId());
        for (StylesheetContent stylesheetContent : externalStyleSheet) {
            if (stylesheetContent.getAdaptedContent() == null || stylesheetContent.getAdaptedContent().isEmpty()) {
                stylesheetContent.setAdaptedContent(CSSContentAdapter.CSS_ON_ERROR);
                if (persistOnTheFly) {
                    contentDataService.saveOrUpdate(stylesheetContent);
                }
                if (LOGGER.isDebugEnabled()) {
                   LOGGER.debug(stylesheetContent.getURI() + " hasn't been adapted"
                           + "for any reason and is set on error");
                }
            }
        }
    }

    @Override
    public void addNewStylesheetContent(SSP ssp, StylesheetContent stylesheetContent) {
        if (!externalCssMap.containsKey(ssp.getAudit().getId())) {
            LOGGER.debug(" the local external css Map doesn't contain any entry for"
                    + "the ssp Id "  + ssp.getAudit().getId());
            return;
        }
        for (StylesheetContent css : externalCssMap.get(ssp.getAudit().getId())) {
            if (stylesheetContent.getURI().equals(css.getURI())) {
                LOGGER.debug("the css " + stylesheetContent.getURI() + 
                " already exists in the externalCssMap");
                return;
            }
        }
        externalCssMap.get(ssp.getAudit().getId()).add(stylesheetContent);
        LOGGER.debug(stylesheetContent.getURI() + 
                " has been added to the externalCssMap");
    }
 
}