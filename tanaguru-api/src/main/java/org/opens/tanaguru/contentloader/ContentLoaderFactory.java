/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.contentloader;

import org.opens.tanaguru.entity.factory.audit.ContentFactory;

/**
 *
 * @author enzolalay
 */
public interface ContentLoaderFactory {// TODO Write javadoc

    ContentLoader create(ContentFactory contentFactory, Downloader downloader);
}
