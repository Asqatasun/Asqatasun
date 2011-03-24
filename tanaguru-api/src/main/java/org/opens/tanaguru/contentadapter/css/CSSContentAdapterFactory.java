/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.contentadapter.css;

import org.opens.tanaguru.contentadapter.ContentAdapterFactory;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.contentloader.Downloader;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;

/**
 *
 * @author enzolalay
 */
public interface CSSContentAdapterFactory extends ContentAdapterFactory {// TODO Write javadoc

    CSSContentAdapter create(ContentFactory contentFactory, URLIdentifier urlIdentifier, Downloader downloader);
}
