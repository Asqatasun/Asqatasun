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
public class ContentLoaderFactoryImpl implements ContentLoaderFactory {

    @Override
    public ContentLoader create(ContentFactory contentFactory, Downloader downloader) {
        return new ContentLoaderImpl(contentFactory, downloader);
    }
}
