/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.contentadapter.css;

import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.contentloader.Downloader;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.service.audit.ContentDataService;

/**
 *
 * @author enzolalay
 */
public class CSSContentAdapterFactoryImpl implements CSSContentAdapterFactory {

    private ExternalCSSRetriever externalCSSRetriever;

    @Override
    public CSSContentAdapter create(
            ContentFactory contentFactory,
            URLIdentifier urlIdentifier,
            Downloader downloader,
            ContentDataService contentDataService) {
        return new CSSContentAdapterImpl(
                contentFactory,
                urlIdentifier,
                downloader,
                CSSParserFactory.create(),
                contentDataService,
                getExternalCSSRetriever());
    }

    @Override
    public ExternalCSSRetriever getExternalCSSRetriever() {
        return externalCSSRetriever;
    }

    @Override
    public void setExternalCSSRetriever(ExternalCSSRetriever externalCSSRetriever) {
        this.externalCSSRetriever = externalCSSRetriever;
    }

}