/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.contentadapter.css;

import org.opens.tanaguru.contentadapter.ContentAdapterFactory;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.contentloader.Downloader;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.service.audit.ContentDataService;

/**
 *
 * @author enzolalay
 */
public interface CSSContentAdapterFactory extends ContentAdapterFactory {// TODO Write javadoc

    /**
     * 
     * @param contentFactory
     * @param urlIdentifier
     * @param downloader
     * @param contentDataService
     * @return
     */
    @Override
    CSSContentAdapter create(
            ContentFactory contentFactory,
            URLIdentifier urlIdentifier,
            Downloader downloader,
            ContentDataService contentDataService);

    /**
     *
     * @return
     *      the external css retriever instance
     */
    ExternalCSSRetriever getExternalCSSRetriever();

    /**
     * Set the external css retriever instance
     * @param externalCSSRetriever
     */
    void setExternalCSSRetriever(ExternalCSSRetriever externalCSSRetriever);
}
