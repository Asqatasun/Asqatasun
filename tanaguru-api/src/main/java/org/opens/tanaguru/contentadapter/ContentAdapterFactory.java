/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.contentadapter;

import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.contentloader.Downloader;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;

/**
 *
 * @author enzolalay
 */
public interface ContentAdapterFactory {// TODO Write javadoc

    ContentAdapter create(ContentFactory contentFactory, URLIdentifier urlIdentifier, Downloader downloader);
}
