/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.contentadapter.css;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.apache.log4j.Logger;
import org.opens.tanaguru.contentadapter.AdaptationListener;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class ExternalCSSRetrieverImpl implements ExternalCSSRetriever, AdaptationListener {

    private static final Logger LOGGER = Logger.getLogger(ExternalCSSRetrieverImpl.class);
    private Map<Long, Collection<StylesheetContent>> externalCssMap =
            new HashMap<Long, Collection<StylesheetContent>>();

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
        removeExternalCSS(audit);
    }

    /**
     * This methods retrieves all the external css associated with an audit.
     * Due to optimisation reasons, this operation has to be done only once.
     * In this case, this operation is realized when the adaptation phase starts.
     * @param audit
     */
    private void retrieveExternalCSS(Audit audit) {
        externalCssMap.put(
                audit.getId(),
                contentDataService.getExternalStylesheetFromAudit(audit));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Retrieved " + externalCssMap.get(audit.getId()).size()+
                    " external css  for the audit n° "+ audit.getId());
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

}