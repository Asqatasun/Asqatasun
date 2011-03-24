/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.contentadapter.css;

import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.contentloader.Downloader;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;

/**
 *
 * @author enzolalay
 */
public class CSSContentAdapterFactoryImpl implements CSSContentAdapterFactory {

    public CSSContentAdapter create(ContentFactory contentFactory, URLIdentifier urlIdentifier, Downloader downloader) {
        return new CSSContentAdapterImpl(contentFactory, urlIdentifier, downloader, CSSParserFactory.create());
    }
}
