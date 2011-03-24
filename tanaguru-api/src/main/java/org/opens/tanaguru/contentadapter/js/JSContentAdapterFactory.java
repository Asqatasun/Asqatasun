/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.contentadapter.js;

import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.contentloader.Downloader;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;

/**
 *
 * @author enzolalay
 */
public interface JSContentAdapterFactory extends ContentFactory {// TODO Write javadoc

    JSContentAdapter create(ContentFactory contentFactory, URLIdentifier urlIdentifier, Downloader donwloader);
}
