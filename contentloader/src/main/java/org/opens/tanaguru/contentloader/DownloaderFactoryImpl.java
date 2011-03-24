/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.contentloader;

/**
 *
 * @author enzolalay
 */
public class DownloaderFactoryImpl implements DownloaderFactory {

    @Override
    public Downloader create() {
        return new DownloaderImpl();
    }
}
